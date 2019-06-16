package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.async.AsyncTask;
import com.ctbcbank.ivr.repo.gateway.encrypt.DES;
import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessStatus;
import com.ctbcbank.ivr.repo.gateway.model.in.RepoModel;
import com.ctbcbank.ivr.repo.gateway.model.in.SplunkIn;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOut;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOutStatus;
import com.ctbcbank.ivr.repo.gateway.properties.JdbcNamedQueryProperties;
import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/log")
@Api(tags = "針對Log data base進行操作")
public class LogRepoController {
	private Logger loggerA = LoggerFactory.getLogger("splunk_A");
	private Logger loggerB = LoggerFactory.getLogger("splunk_B");
	private Logger ivrDetailLog = LoggerFactory.getLogger("ivr_detail_log");
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcNamedQueryProperties jdbcNamedQueryProperties;
	@Autowired
	private AsyncTask task;
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "新增，刪除，修改", notes = "執行新增,刪除,修改的sql語句，且不會有回傳結果")
	@PostMapping("/execute")
	public ResultOut execute(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultInfo = new ResultOut();
		ProcessResult processResult = resultInfo.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			jdbcTemplate.execute(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			processResult.setProcessResultEnum(ProcessResultEnum.EDIT_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		}
		catch (Exception e) {
			log.writeError(repoModel, e.toString());
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultInfo;
	}
	
	@ApiOperation(value = "新增，刪除，修改(不建議使用)", notes = "異步執行新增,刪除,修改的sql語句，且不會有回傳結果")
	@PostMapping("/executeThread")
	public ResultOutStatus executeThread(@ModelAttribute final RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		try {
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultOutStatus resultOut = new ResultOutStatus();
		if(!repoModel.getSql().equals(StringUtils.EMPTY)) {
			resultOut.setStatus(ProcessStatus.SUCCESS.getStatus());
//			new Thread() {
//				@Override
//				public void run() {
//					try {
//						jdbcTemplate.execute(repoModel.getSql());
//					catch (Exception e) {
//						logger.error(e.getMessage(), e);
//					}
//				}
//			}.start();
			task.jdbcExecute(jdbcTemplate, repoModel);
		}
		else {
			resultOut.setStatus(ProcessStatus.FAIL.getStatus());
		}
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
//	功能同executeThread 暫時使用而已。
	@ApiOperation(value = "功能等同executeThread", notes = "開發階段API")
	@PostMapping("/IVRDetailLog")
	public ResultOutStatus IVRDetailLog(@ModelAttribute final RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		try {
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultOutStatus resultOut = new ResultOutStatus();
		if(!repoModel.getSql().equals(StringUtils.EMPTY)) {
			resultOut.setStatus(ProcessStatus.SUCCESS.getStatus());
			task.jdbcExecute(jdbcTemplate, repoModel);
		}
		else {
			resultOut.setStatus(ProcessStatus.FAIL.getStatus());
		}	
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
//	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
	@ApiOperation(value = "查詢", notes = "執行查詢的sql語句，會有回傳結果。  \n補充:可執行內容只有select的預存程序(即預存程序中不可含有insert,update......等等)，須按照MSSQL的預存程序呼叫方法來呼叫。")
	@PostMapping("/query")
	public ResultOut query(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultInfo = new ResultOut();
		ProcessResult processResult = resultInfo.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			List<Map<String, Object>> dataList = jdbcTemplate.queryForList(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> m:dataList) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(Map.Entry<String, Object> entry:m.entrySet()) {
					String k = entry.getKey();
					if(entry.getValue()!=null) {
						String v = entry.getValue().toString();
//						判斷v是否為日期形式，若是的話，去掉其後面的毫秒!，以避免在composer收到的日期形式為毫秒。
						try {
							Date date = sdf.parse(v);
							m.put(k, sdf.format(date));
						} 
						catch (Exception e) {
						}
					}
				}
				list.add(m);
			}
			if (!dataList.isEmpty())
				resultInfo.setDataList(list);
			else
				resultInfo.setDataList(new ArrayList<Map<String, Object>>());
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), list);
		}
		catch (Exception e) {
			log.writeError(repoModel, e.toString());
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultInfo;
	}
	
//	@ApiOperation(value = "特殊insert", notes = "執行特定的insert語句，insert into IVRHostTxnLog")
//	@PostMapping("/writeIVRHostTxnLog")
//	public ResultOut writeIVRHostTxnLog(@ApiParam(required = true, value = "json格式物件") @RequestBody RepoIn repoIn) {
//		ResultOut resultInfo = new ResultOut();
//		ProcessResult processResult = resultInfo.getProcessResult();
//		String hostAddress = StringUtils.EMPTY;
//		try {
//			InetAddress iAddress = InetAddress.getLocalHost();
//			hostAddress = iAddress.getHostAddress();
//			jdbcTemplate.update(jdbcNamedQueryProperties.getWriteIVRHostTxnLog(), repoIn.getDatas());
//			processResult.setProcessResultEnum(ProcessResultEnum.ADD_SUCCESS);
//			log.writeInfo(repoIn);
//		}
//		catch (Exception e) {
//			log.writeError(repoIn, e.toString());
//			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
//			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
//			processResult.setReturnMessage(e.getMessage());
//		}
//		processResult.setCallUUID(repoIn.getCallUUID());
//		processResult.setConnID(repoIn.getConnID());
//		processResult.setGvpSessionID(repoIn.getGvpSessionID());
//		processResult.setApServerName(hostAddress);
//		return resultInfo;
//	}
	
	@ApiOperation(value = "紀錄splunk log", notes = "把splunkA和splunkB的sql語句分別寫成2個log檔案")
	@PostMapping("/writeSplunkLog")
	public ResultOutStatus writeSplunkLog(@ModelAttribute SplunkIn splunkIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOutStatus resultOutStatus = new ResultOutStatus();
		loggerA.info(splunkIn.getSplunk_a());
		loggerB.info(splunkIn.getSplunk_b());
		resultOutStatus.setStatus("s");
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(splunkIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOutStatus;
	}
	
//	之後會更名回IVRDetailLog
//	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
	@ApiOperation(value = "紀錄insert log", notes = "目前尚未啟用! 用於把許多要記錄進logDB的資訊先寫成log檔案  \n補充:此log檔案之後會給ivr-schedule跑批次  \n補充:和executeThread區別在於executeThread是及時寫log進DB，而此API則是先把log存起來，晚上再跑批次來寫進logDB")
	@PostMapping("/IVRDetailLog_notUsed")
	public ResultOutStatus writeLog(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOutStatus resultOutStatus = new ResultOutStatus();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			String sql = repoModel.getSql().replace("[ProcessDate]", "[ProcessDate],[HostAddress]")
										   .replace("getdate()", "getdate(),'" + hostAddress + "'");
			ivrDetailLog.info(DES._EncryptByDES(sql, keyProperties.getKey()) + "#");
			resultOutStatus.setStatus("s");
		} catch (Exception e) {
			resultOutStatus.setStatus("f");
			e.printStackTrace();
		}
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOutStatus;
	}
}
