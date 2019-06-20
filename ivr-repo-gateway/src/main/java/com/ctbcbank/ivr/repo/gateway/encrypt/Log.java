package com.ctbcbank.ivr.repo.gateway.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.repo.gateway.model.in.RequestModel;
import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

@Component
public class Log {
	private Logger logger = LoggerFactory.getLogger("ivr-repo-gateway");
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
	
	public void writeTimeLog(String connId, String key, String type, long in, long out) {
		logger_time.info("{}, {}, {}, {}, {}", connId, key, type, in, out);
	}
	
	public void writeInfo(RequestModel requestModel) {
		logger.info("CallUUID : {}#\n"
				  + "ConnID : {}#\n"
				  + "GvpSessionID : {}#\n"
				  + "#$$%%%%$$#",
				  requestModel.getCallUUID(),
				  requestModel.getConnID(),
				  requestModel.getGvpSessionID());
	}
	
	public void writeInfo(RequestModel requestModel, Object msg, int type) throws Exception {
		if(type == INPUT) {
			logger.info("input : {}#\n"
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
			logger.info("output : {}#\n"
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
		logger.info("input : {}#\n"
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
		logger.info("input : {}#\n"
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
	
	public void writeError(RequestModel requestModel, Exception error) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		error.printStackTrace(pw);
		logger.error("---ERROR--- : {}"
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
