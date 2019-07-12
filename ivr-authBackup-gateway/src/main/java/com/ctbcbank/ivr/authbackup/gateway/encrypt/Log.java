package com.ctbcbank.ivr.authbackup.gateway.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.authbackup.gateway.model.RequestModel;
import com.ctbcbank.ivr.authbackup.gateway.properties.KeyProperties;


@Component
public class Log {
	private Logger logger_time = LoggerFactory.getLogger("time-log");
	private Logger logger_authBackup = LoggerFactory.getLogger("authBackup");
	@Autowired
	private KeyProperties keyProperties;
	@Value("${log.isEncrypt}")
	private boolean isEncrypt;
	
	public void writeTimeLog(String connId, String key, String type, long in, long out) {
		logger_time.info("{}, {}, {}, {}, {}", connId, key, type, in, out);
	}
	
	public void writeAuthBackupInfo(RequestModel requestModel, Object input) throws Exception {
		logger_authBackup.info("input : {}#\n"
				  			  + "CallUUID : {}#\n"
				  			  + "ConnID : {}#\n"
				  			  + "GvpSessionID : {}#\n"
				  			  + "#$$%%%%$$#", 
				  			  EncryptByDES(String.valueOf(input)),
				  			  requestModel.getCallUUID(),
				  			  requestModel.getConnID(),
				  			  requestModel.getGvpSessionID());
	}
	
	public void writeError(RequestModel requestModel, Exception error) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		error.printStackTrace(pw);
		logger_authBackup.error("---ERROR--- : {}"
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
