package com.ctbcbank.mq.gateway.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import com.ctbcbank.mq.software_enc.DES;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.mq.gateway.mqheadler.MqCombinedMessage;
import com.ctbcbank.mq.gateway.mqheadler.MqHandlerfunction;
import com.ctbcbank.mq.gateway.object.KeyProperties;
import com.ctbcbank.mq.gateway.object.MqIn;
import com.ctbcbank.mq.gateway.object.MqProperties;
import com.ctbcbank.mq.gateway.object.ReturnModel;

@Api(tags = "發簡訊API[(IBM)Manager Queue Series]")
@RestController
@RequestMapping(value = "/mq")
public class MqController {
	private Logger logger = LoggerFactory.getLogger("ivr-mq-geteway");
	private Logger logger_time = LoggerFactory.getLogger("time-log");
	@Autowired
	private MqProperties mq;
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KeyProperties keyProperties;
	
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
			String mqdata_Enc = DES._EncryptByDES(mqin.getdata(), keyProperties.getKey());
			if(mqin.getType().length()!=3)
				throw new Exception("Type length error: "+mqin.getType());
			String sql = mq.getSql().replace("{}", mqin.getType());
			List<Map<String, Object>> dataList=jdbcTemplate.queryForList(sql);
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
//			if(msg.length()!=HeaderLength+DataLength) {//要防呆檢核送簡訊MQ的欄位長度
//				mqout.setStatus("f");
//				mqout.setMassage(msg.toString());
//				logger.info("========={} input========= : {}# \n"
//						  + "msg_Enc : {}#\n"
//						  + "msg : {}#\n"
//						  + "charset : {}#\n"
//						  + "channel : {}#\n"
//						  + "queuemanagername : {}#\n"
//						  + "queuename : {}#\n"
//						  + "MQ Host : {}#\n"
//						  + "MQ Port : {}#\n"
//						  + "CallUUID : {}#\n"
//						  + "ConnID : {}#\n"
//						  + "GvpSessionID : {}#\n"
//						  + "MQ msg length error"
//						  + "#$$%%%%$$#", 
//						  mqin.getType(), mqdata_Enc, 
//						  DES._EncryptByDES(msg, key), 
//						  msg, 
//						  charset,
//						  channel,
//						  queuemanagername,
//						  queuename,
//						  mq.getHost(),
//						  mq.getPort(),
//						  mqin.getCallUUID(), 
//						  mqin.getConnID(), 
//						  mqin.getGvpSessionID());
//				return mqout;
//			}
			MqHandlerfunction mqhandlerfunction = new MqHandlerfunction(mq);
			//連線送出動作
			mqhandlerfunction.send(msg,charset,channel,queuemanagername,queuename);
			processResult.setProcessResultEnum(ProcessResultEnum.SEND_SUCCESS);
			logger.info("========={} input========= : {}# \n"
					  + "msg_Enc : {}#\n"
					  + "charset : {}#\n"
					  + "channel : {}#\n"
					  + "queuemanagername : {}#\n"
					  + "queuename : {}#\n"
					  + "MQ Host : {}#\n"
					  + "MQ Port : {}#\n"
					  + "CallUUID : {}#\n"
					  + "ConnID : {}#\n"
					  + "GvpSessionID : {}#\n"
					  + "MQ Success\n"
					  + "#$$%%%%$$#", 
					  mqin.getType(), mqdata_Enc, 
					  DES._EncryptByDES(msg, keyProperties.getKey()), 
					  charset,
					  channel,
					  queuemanagername,
					  queuename,
					  mq.getHost(),
					  mq.getPort(),
					  mqin.getCallUUID(), 
					  mqin.getConnID(), 
					  mqin.getGvpSessionID());
		}catch(Exception e){
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.error("{} ---ERROR--- : {}"
					  + "CallUUID : {}#\n"
					  + "ConnID : {}#\n"
					  + "GvpSessionID : {}#\n"
					  + "#$$%%%%$$#", 
					  mqin.getType(), 
					  sw.toString(),
					  mqin.getCallUUID(), 
					  mqin.getConnID(), 
					  mqin.getGvpSessionID());
		}
		processResult.setCallUUID(mqin.getCallUUID());
		processResult.setConnID(mqin.getConnID());
		processResult.setGvpSessionID(mqin.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		logger_time.info("{}, {}, {}, {}, {}", mqin.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return returnModel;
	}
}
