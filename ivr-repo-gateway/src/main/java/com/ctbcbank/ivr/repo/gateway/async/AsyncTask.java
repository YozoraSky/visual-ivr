package com.ctbcbank.ivr.repo.gateway.async;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

@Component
public class AsyncTask {
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
			MPlusServiceSoapProxy mPlusServiceSoapProxy = new MPlusServiceSoapProxy();
			IvrCallLog oData = new IvrCallLog();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			if(!mPlusIn.getIvrCallLog().getStartTime().equals(StringUtils.EMPTY)) {
				startTime.setTimeInMillis(sdf.parse(mPlusIn.getIvrCallLog().getStartTime()).getTime());
			}
			if(!mPlusIn.getIvrCallLog().getEndTime().equals(StringUtils.EMPTY)) {
				endTime.setTimeInMillis(sdf.parse(mPlusIn.getIvrCallLog().getEndTime()).getTime());
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
			String result = mPlusServiceSoapProxy.sendMessage(mPlusIn.getGroupId(), mPlusIn.getTitle(), mPlusIn.getContent(), oData, rightNow);
//			為了寫log 所以set值進mPlusIn物件(讓mPlusIn的toString有值可以印出來)
			mPlusIn.setNowTime(sdf.format(date));
			log.writeMPlusLog(mPlusIn, result);
		}
		catch(Exception e) {
			log.writeError(mPlusIn, e, Log.IVRMPLUSGATEWAY);
		}
	}
}
