package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.async.AsyncTask;
import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessStatus;
import com.ctbcbank.ivr.repo.gateway.model.in.RepoModel;
import com.ctbcbank.ivr.repo.gateway.model.in.SplunkIn;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOut;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOutStatus;
import com.ctbcbank.ivr.repo.gateway.monitor.DynamicDataSource;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/log")
@Api(tags = "針對Log data base進行操作")
public class LogRepoController {
	@Autowired
	private DynamicDataSource dynamicDataSource;
	@Autowired
	private AsyncTask task;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "新增，刪除，修改", notes = "執行新增,刪除,修改的sql語句，且不會有回傳結果")
	@PostMapping("/execute")
	public ResultOut execute(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		ResultOut resultInfo = new ResultOut();
		ProcessResult processResult = resultInfo.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			dynamicDataSource.getLogJdbcTemplate().execute(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			processResult.setProcessResultEnum(ProcessResultEnum.EDIT_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		}
		catch (Exception e) {
			log.writeError(repoModel, e, Log.IVRREPOGATEWAY);
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
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
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
			task.jdbcExecute(dynamicDataSource.getLogJdbcTemplate(), repoModel);
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
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		try {
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultOutStatus resultOut = new ResultOutStatus();
		if(!repoModel.getSql().equals(StringUtils.EMPTY)) {
			resultOut.setStatus(ProcessStatus.SUCCESS.getStatus());
			task.jdbcExecute(dynamicDataSource.getLogJdbcTemplate(), repoModel);
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
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		ResultOut resultOut = new ResultOut();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			long DBInTime = System.currentTimeMillis();
			List<Map<String, Object>> dataList = dynamicDataSource.getLogJdbcTemplate().queryForList(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{2}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{1}$");
			Matcher match;
			for(Map<String, Object> m:dataList) {
				for(Map.Entry<String, Object> entry:m.entrySet()) {
					if(entry.getValue()!=null) {
						match = pattern.matcher(entry.getValue().toString());
						if(match.matches()) {
							Date date = sdf.parse(entry.getValue().toString());
							m.put(entry.getKey(), sdf.format(date));
						}
					}
				}
				list.add(m);
			}
			if (!dataList.isEmpty())
				resultOut.setDataList(list);
			else
				resultOut.setDataList(new ArrayList<Map<String, Object>>());
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), list);
		}
		catch (Exception e) {
			log.writeError(repoModel, e, Log.IVRREPOGATEWAY);
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
		return resultOut;
	}
	
	@ApiOperation(value = "紀錄splunk log", notes = "把splunkA和splunkB的sql語句分別寫成2個log檔案")
	@PostMapping("/writeSplunkLog")
	public ResultOutStatus writeSplunkLog(@ModelAttribute SplunkIn splunkIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		ResultOutStatus resultOutStatus = new ResultOutStatus();
		log.writeSplunkLog(splunkIn.getSplunk_a(), splunkIn.getSplunk_b());
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
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		ResultOutStatus resultOutStatus = new ResultOutStatus();
		try {
			log.writeDetailLog(repoModel.getSql());
			resultOutStatus.setStatus("s");
		} catch (Exception e) {
			resultOutStatus.setStatus("f");
		}
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOutStatus;
	}
}
