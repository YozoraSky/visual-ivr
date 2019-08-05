package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.ivr.repo.gateway.properties.MqProperties;
import com.ctbcbank.ivr.repo.gateway.mq.MqCombinedMessage;
import com.ctbcbank.ivr.repo.gateway.mq.MqHandlerfunction;
import com.ctbcbank.ivr.repo.gateway.model.in.MqIn;
import com.ctbcbank.ivr.repo.gateway.model.out.ReturnModel;
import com.ctbcbank.ivr.repo.gateway.monitor.DynamicDataSource;

@Api(tags = "發簡訊API[(IBM)Manager Queue Series]")
@RestController
@RequestMapping(value = "/mq")
public class MqController {
	@Autowired
	private MqProperties mq;
	@Autowired
	private DynamicDataSource dynamicDataSource;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "連接(IBM)Manager Queue Series", notes = "連接發簡訊平台，發送簡訊。")
	@PostMapping("/mqApplication")
	public ReturnModel MqApplitaction(@ApiParam(required = true, value = "簡訊資料(json格式)") @RequestBody final MqIn mqin) {
			long ivrInTime = System.currentTimeMillis();
			String UUID = java.util.UUID.randomUUID().toString();	
			ReturnModel returnModel = new ReturnModel();
			ProcessResult processResult = returnModel.getProcessResult();
			String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			if(mqin.getType().length()!=3)
				throw new Exception("Type length error: "+mqin.getType());
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("Type", mqin.getType());
			List<Map<String, Object>> dataList=dynamicDataSource.getConfigNamedParameterJdbcTemplate().queryForList(mq.getSql(),paramMap);
			if(dataList.isEmpty()) 
				throw new Exception("SQL not found data");
			int HeaderLength=Integer.parseInt((String)dataList.get(0).get("HeaderLength"));
			int DataLength=Integer.parseInt((String)dataList.get(0).get("DataLength"));
			String charset = (String) dataList.get(0).get("DataEncoding");
			String channel = (String) dataList.get(0).get("Channel");
			String queuemanagername = (String) dataList.get(0).get("QueueManager");
			String queuename = (String) dataList.get(0).get("QueueName");
			MqCombinedMessage mqcombinedmessage = new MqCombinedMessage(HeaderLength,DataLength);
			String msg = mqcombinedmessage.CombinedMessage(mqin);
			MqHandlerfunction mqhandlerfunction = new MqHandlerfunction(mq);
			//連線送出動作
			mqhandlerfunction.send(msg,charset,channel,queuemanagername,queuename);
			processResult.setProcessResultEnum(ProcessResultEnum.SEND_SUCCESS);
			log.writeMqLog(mqin,mqin.getdata(),msg,charset,channel,queuemanagername,queuename,mq.getHost(),mq.getPort());
		}catch(Exception e){
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
			log.writeError(mqin, e, Log.IVRMQGATEWAY);
		}
		processResult.setCallUUID(mqin.getCallUUID());
		processResult.setConnID(mqin.getConnID());
		processResult.setGvpSessionID(mqin.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(mqin.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return returnModel;
	}
}
