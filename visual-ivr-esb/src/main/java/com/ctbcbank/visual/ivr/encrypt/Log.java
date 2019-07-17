package com.ctbcbank.visual.ivr.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;
import com.ctbcbank.visual.ivr.properties.KeyProperties;

@Component
public class Log {
	private Logger logger_esb = LoggerFactory.getLogger("ivr-gateway");
	private Logger logger_line = LoggerFactory.getLogger("line");
	private Logger logger_socket = LoggerFactory.getLogger("socket");
	private Logger logger_fax = LoggerFactory.getLogger("fax");
	private Logger logger_time = LoggerFactory.getLogger("time-log");
	public final static int IVRGATEWAY = 10;
	public final static int IVRLINEGATEWAY = 11;
	public final static int IVRSOCKETGATEWAY = 12;
	public final static int IVRFAXGATEWAY = 13;
	@Autowired
	private KeyProperties keyProperties;
	@Value("${log.isEncrypt}")
	private boolean isEncrypt;
	
	public Logger logger(int loggerName) {
		switch(loggerName) {
			case 10:return logger_esb;
			case 11:return logger_line;
			case 12:return logger_socket;
			case 13:return logger_fax;
		}
		return null;
	}
	
	public void writeTimeLog(String connId, String key, String type, long in, long out) {
		logger_time.info("{}, {}, {}, {}, {}", connId, key, type, in, out);
	}
	
	public void writeInfo(RequestModel requestModel, Object input, Object output, int loggerName) throws Exception {
		logger(loggerName).info("input : {}#\n"
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
	
	public void writeFaxInfo(RequestModel requestModel, Object input, Object output, int loggerName) throws Exception {
		logger(loggerName).info("input : {}#\n"
				  			  + "output : {}#\n"
				  			  + "CallUUID : {}#\n"
				  			  + "ConnID : {}#\n"
				  			  + "GvpSessionID : {}#\n"
				  			  + "#$$%%%%$$#", 
				  			  EncryptByDES(String.valueOf(input)),
				  			  output,
				  			  requestModel.getCallUUID(),
				  			  requestModel.getConnID(),
				  			  requestModel.getGvpSessionID());
	}
	
	public void writeSocketInfo(RequestModel requestModel,Socket socket, Object input,  Object output, int loggerName) throws Exception {
		logger(loggerName).info("Connect success! {}:{}\n"
	  			  			  + "input : {}#\n"
	  			  			  + "output : {}#\n"
	  			  			  + "CallUUID : {}#\n"
	  			  			  + "ConnID : {}#\n"
	  			  			  + "GvpSessionID : {}#\n"
				  			  + "#$$%%%%$$#",
				  			  socket.getInetAddress().getHostAddress(),
				  			  socket.getPort(),
				  			  EncryptByDES(String.valueOf(input)),
				  			  EncryptByDES(String.valueOf(output)),
				  			  requestModel.getCallUUID(),
				  			  requestModel.getConnID(),
				  			  requestModel.getGvpSessionID());
				  			  
	}
	
	public void writeEsbInputInfo(RequestModel requestModel, String input, String serviceName, int loggerName) throws Exception{
		String xmlCiphertext = EncryptByDES(String.valueOf(input));
		logger(loggerName).info("========={} input========= : {}\n"
				  			  + "data length : {}#\n"
				  			  + "CallUUID : {}#\n"
				  			  + "ConnID : {}#\n"
				  			  + "GvpSessionID : {}#\n"
				  			  + "#$$%%%%$$#",
				  			  serviceName,
				  			  xmlCiphertext,
				  			  xmlCiphertext.length(),
				  			  requestModel.getCallUUID(),
				  			  requestModel.getConnID(),
				  			  requestModel.getGvpSessionID());
	}
	
	public void writeEsbOutputInfo(RequestModel requestModel, String output, String serviceName, int loggerName) throws Exception{
		String jmsResultCiphertext = EncryptByDES(String.valueOf(output));
		logger(loggerName).info("========={} output========= : {}\n"
				  			  + "data length : {}#\n"
				  			  + "CallUUID : {}#\n"
				  			  + "ConnID : {}#\n"
				  			  + "GvpSessionID : {}#\n"
				  			  + "#$$%%%%$$#",
				  			  serviceName,
				  			  jmsResultCiphertext,
				  			  jmsResultCiphertext.length(),
				  			  requestModel.getCallUUID(),
				  			  requestModel.getConnID(),
				  			  requestModel.getGvpSessionID());
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
