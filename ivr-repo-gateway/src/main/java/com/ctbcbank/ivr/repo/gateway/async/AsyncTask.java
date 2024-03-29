package com.ctbcbank.ivr.repo.gateway.async;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tempuri.IvrCallLog;
import org.tempuri.MPlusServiceSoapProxy;

import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.model.in.MPlusIn;
import com.ctbcbank.ivr.repo.gateway.model.in.RepoModel;
import com.ctbcbank.ivr.repo.gateway.monitor.DynamicDataSource;
import com.ctbcbank.ivr.repo.gateway.properties.MPlusProperties;

@Component
public class AsyncTask {
	@Autowired
	private DynamicDataSource dynamicDataSource;
	@Autowired
	private MPlusProperties mPlusProperties;
	@Autowired
	private Log log;
	@Async("myAsync")
	public void jdbcExecute(JdbcTemplate jdbcTemplate, RepoModel repoModel) {
		try {
			jdbcTemplate.execute(repoModel.getSql());
		}
		catch(Exception e) {
			log.writeError(repoModel, e, Log.IVRREPOGATEWAY);
		}
	}
	@Async("myAsync")
	public void mPlusExecute(MPlusIn mPlusIn) {
		try {
			MPlusServiceSoapProxy mPlusServiceSoapProxy = new MPlusServiceSoapProxy(mPlusProperties.getIp());
			IvrCallLog oData = new IvrCallLog();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			if(!mPlusIn.getIvrCallLog().getStartTime().equals(StringUtils.EMPTY)) {
//				轉時區?中信那邊會自動-8小時，所以補上8小時的毫秒(28800000)
				startTime.setTimeInMillis(sdf.parse(mPlusIn.getIvrCallLog().getStartTime()).getTime()+28800000);
			}
			if(!mPlusIn.getIvrCallLog().getEndTime().equals(StringUtils.EMPTY)) {
				endTime.setTimeInMillis(sdf.parse(mPlusIn.getIvrCallLog().getEndTime()).getTime()+28800000);
			}
			oData.setAgentExt(mPlusIn.getIvrCallLog().getAgentExt());
			oData.setAgentName(mPlusIn.getIvrCallLog().getAgentName());
			oData.setGroupId(mPlusIn.getIvrCallLog().getGroupId());
			oData.setGroupName(mPlusIn.getIvrCallLog().getGroupName());
			oData.setCustomerId(mPlusIn.getIvrCallLog().getCustomerId());
			oData.setCustomerName(mPlusIn.getIvrCallLog().getCustomerName());
			oData.setCallFrom(mPlusIn.getIvrCallLog().getCallFrom());
			oData.setStartTime(startTime);
			oData.setEndTime(endTime);
			Date date = new Date(System.currentTimeMillis());
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("IVRDynamicGroupId", mPlusIn.getIvrCallLog().getGroupId());
			List<Map<String, Object>> list = dynamicDataSource.getConfigNamedParameterJdbcTemplate().queryForList(mPlusProperties.getSql(), params);
			mPlusIn.setGroupId((String) list.get(0).get("MPlusGroupId"));
			mPlusIn.setTitle((String) list.get(0).get("NoticeTitle"));
			mPlusIn.setContent((String) list.get(0).get("NoticeContent"));
			String result = mPlusServiceSoapProxy.sendMessage(mPlusIn.getGroupId(), mPlusIn.getTitle(), mPlusIn.getContent(), oData, rightNow);
//			為了寫log 所以set值進mPlusIn物件(讓mPlusIn的toString有值可以印出來)
			mPlusIn.setNowTime(sdf.format(date));
			log.writeMPlusOutputLog(mPlusIn, result);
		}
		catch(Exception e) {
			log.writeError(mPlusIn, e, Log.IVRMPLUSGATEWAY);
		}
	}
}
;