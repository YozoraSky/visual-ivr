package com.ctbcbank.ivr.repo.gateway.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.repo.gateway.model.in.MPlusIn;
import com.ctbcbank.ivr.repo.gateway.model.in.MqIn;
import com.ctbcbank.ivr.repo.gateway.model.in.RequestModel;
import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

@Component
public class Log {
	private Logger loggerRepo = LoggerFactory.getLogger("ivr-repo-gateway");
	private Logger logger_mq = LoggerFactory.getLogger("ivr-mq-geteway");
	private Logger logger_mPlus = LoggerFactory.getLogger("mPlus");
	private Logger logger_time = LoggerFactory.getLogger("time-log");
	private Logger ivrDetailLog = LoggerFactory.getLogger("ivr_detail_log");
	private Logger loggerA = LoggerFactory.getLogger("splunk_A");
	private Logger loggerB = LoggerFactory.getLogger("splunk_B");
	public final static int INPUT = 0;
	public final static int OUTPUT = 1;
	@Autowired
	private KeyProperties keyProperties;
	@Value("${log.isEncrypt}")
	private boolean isEncrypt;
	public final static int IVRREPOGATEWAY = 10;
	public final static int IVRMQGATEWAY = 11;
	public final static int IVRMPLUSGATEWAY = 12;
	
	public Logger logger(int loggerName) {
		switch(loggerName) {
			case 10:return loggerRepo;
			case 11:return logger_mq;
			case 12:return logger_mPlus;
		}
		return null;
	}
	
	public void writeMPlusLog(MPlusIn mPlusIn, String output) {
		logger_mPlus.info("input : {}#\n"
	  			  		+ "output : {}#\n"
	  			  		+ "CallUUID : {}#\n"
	  			  		+ "ConnID : {}#\n"
	  			  		+ "GvpSessionID : {}#\n"
	  			  		+ "#$$%%%%$$#", 
	  			  		mPlusIn.toString(),
	  			  		output,
	  			  		mPlusIn.getCallUUID(),
	  			  		mPlusIn.getConnID(),
	  			  		mPlusIn.getGvpSessionID());
	}
	
	public void writeMqLog(MqIn mqin,String mqdata,String msg, String charset, String channel, String queuemanagername,String queuename, String mq_host, int mq_port) throws Exception {
	 logger_mq.info("========={} input========= : {}# \n"
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
				  mqin.getType(), EncryptByDES(mqdata),
				  EncryptByDES(msg),
				  charset,
				  channel,
				  queuemanagername,
				  queuename,
				  mq_host,
				  mq_port,
				  mqin.getCallUUID(), 
				  mqin.getConnID(), 
				  mqin.getGvpSessionID());
	}
	
	public void writeTimeLog(String connId, String key, String type, long in, long out) {
		logger_time.info("{}, {}, {}, {}, {}", connId, key, type, in, out);
	}
	
	public void writeInfo(RequestModel requestModel) {
		loggerRepo.info("CallUUID : {}#\n"
				  + "ConnID : {}#\n"
				  + "GvpSessionID : {}#\n"
				  + "#$$%%%%$$#",
				  requestModel.getCallUUID(),
				  requestModel.getConnID(),
				  requestModel.getGvpSessionID());
	}
	
	public void writeInfo(RequestModel requestModel, Object msg, int type) throws Exception {
		if(type == INPUT) {
			loggerRepo.info("input : {}#\n"
					  + "CallUUID : {}#\n"
					  + "ConnID : {}#\n"
					  + "GvpSessionID : {}#\n"
					  + "#$$%%%%$$#", 
					  EncryptByDES(String.valueOf(msg)),
					  requestModel.getCallUUID(),
					  requestModel.getConnID(),
					  requestModel.getGvpSessionID());
		}
		if(type == OUTPUT) {
			loggerRepo.info("output : {}#\n"
					  + "CallUUID : {}#\n"
					  + "ConnID : {}#\n"
					  + "GvpSessionID : {}#\n"
					  + "#$$%%%%$$#", 
					  EncryptByDES(String.valueOf(msg)),
					  requestModel.getCallUUID(),
					  requestModel.getConnID(),
					  requestModel.getGvpSessionID());
		}
	}
	
	public void writeInfo(RequestModel requestModel, Object input, Object output) throws Exception {
		loggerRepo.info("input : {}#\n"
				  + "output : {}#\n"
				  + "CallUUID : {}#\n"
				  + "ConnID : {}#\n"
				  + "GvpSessionID : {}#\n"
				  + "#$$%%%%$$#", 
				  EncryptByDES(String.valueOf(input)),
				  EncryptByDES(String.valueOf(output)),
				  requestModel.getCallUUID(),
				  requestModel.getConnID(),
				  requestModel.getGvpSessionID());
	}
	
	public void writeInfo(RequestModel requestModel, Object input, Object output, Object M3count, Object TotalList) throws Exception {
		loggerRepo.info("input : {}#\n"
				  + "output : {}#\n"
				  + "M3count : {}#\n"
				  + "TotalList : {}#\n"
				  + "CallUUID : {}#\n"
				  + "ConnID : {}#\n"
				  + "GvpSessionID : {}#\n"
				  + "#$$%%%%$$#", 
				  EncryptByDES(String.valueOf(input)),
				  EncryptByDES(String.valueOf(output)),M3count,TotalList,
				  requestModel.getCallUUID(),
				  requestModel.getConnID(),
				  requestModel.getGvpSessionID());
	}
	
	public void writeDetailLog(String sql) throws Exception {
		ivrDetailLog.info(DES._EncryptByDES(sql, keyProperties.getKey()) + "#");
	}
	
	public void writeSplunkLog(String splunk_a, String splunk_b) {
		loggerA.info(splunk_a);
		loggerB.info(splunk_b);
	}
	
	public void writeError(RequestModel requestModel, Exception error, int loggerName) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		error.printStackTrace(pw);
		logger(loggerName).error("---ERROR--- : {}"
				   			   + "CallUUID : {}#\n"
				   			   + "ConnID : {}#\n"
				   			   + "GvpSessionID : {}#\n"
				   			   + "#$$%%%%$$#",
				   			   sw.toString(),
				   			   requestModel.getCallUUID(),
				   			   requestModel.getConnID(),
				   			   requestModel.getGvpSessionID());
	}
	
	public String EncryptByDES(String str) throws Exception {
		if(isEncrypt)
			return DES._EncryptByDES(str, keyProperties.getKey());
		else
			return str;
	}
}
