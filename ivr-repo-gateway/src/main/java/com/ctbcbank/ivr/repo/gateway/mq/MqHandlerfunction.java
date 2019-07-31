package com.ctbcbank.ivr.repo.gateway.mq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.ctbcbank.ivr.repo.gateway.properties.MqProperties;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
//import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MqHandlerfunction {
	private MqProperties mqProperties;
	private String host;
	private int port;
	private String Channel;
	private String QueueManagerName;
	private String QueueName;
	private boolean isInit;
	public boolean isInit() {
		return isInit;
	}
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	private MQQueueManager queueMgr = null;
	private MQQueue mqQueue = null; // internal use only.
	public MqHandlerfunction(MqProperties mq) {
		setInit(false);
		this.mqProperties = mq;
	}
	public void init(String channel, String queuemanagername, String queuename) throws Exception {
//		logger.info("MQHandler Initialize");
		host=mqProperties.getHost();
		port=mqProperties.getPort();
		this.Channel=channel;
		this.QueueManagerName=queuemanagername;
		this.QueueName=queuename;
	}
	@SuppressWarnings("unused")
	public void send(String msg, String charset, String channel,String queuemanagername, String queuename) throws Exception {
		
		init(channel, queuemanagername, queuename);
//		logger.info("Host : {}",host);
//		logger.info("Port : {}",port);
		boolean rc = connect();
//		logger.info("Queuename : "+queuename);
//		logger.info("connection status=" + rc);
		putMessage(msg, charset);
		disconnect();
	}
	private void setEnvirement() {
		MQEnvironment.hostname = host;
		MQEnvironment.port = port;
		MQEnvironment.channel = Channel;
		MQEnvironment.CCSID = 950;
		MQEnvironment.password = "";
		MQEnvironment.userID = "";
//		logger.info("Setting MQEnvironment finish");
		isInit = true;
	}

	public boolean connect() throws Exception {
//		logger.info("Require a connection to IBM MQ Server");
		if (!isInit)
			setEnvirement();
		if (queueMgr == null) {
			try {
//				logger.info("MQQueueManager : {}",QueueManagerName);
				queueMgr = new MQQueueManager(QueueManagerName);
				queueMgr.openOptions = MQC.MQMF_SEGMENTATION_ALLOWED;
//				open a message queue for output
				mqQueue = queueMgr.accessQueue(QueueName, MQC.MQOO_OUTPUT);
//				logger.info("connect success");
			} catch (MQException ex) {
				throw new Exception("connect error MQException "+ex.getMessage());
			}
		}
		return (queueMgr.isConnected() && (mqQueue.isOpen()));
	}
	public void putMessage(String message, String charset) throws Exception {
		if (!isInit)
			setEnvirement();
		if ((queueMgr == null) || (!queueMgr.isConnected()))
			throw new Exception("MQServer is not connected.");
		try {
			if (queueMgr.isConnected()) {
				// compose MQMessage
				MQMessage MQMsgA = new MQMessage();
				MQPutMessageOptions pmo = new MQPutMessageOptions();

				MQMsgA.clearMessage();
				MQMsgA.format = MQC.MQFMT_STRING;
				MQMsgA.write(message.getBytes(charset));
//				logger.info("write Success");
//				 MQMsgA.write(message.getBytes());
				mqQueue.put(MQMsgA, pmo);
//				logger.info("put Success");
			}
		} catch (MQException mqe) {
			disconnect();
			throw new Exception("putMessage error MQException "+mqe.getMessage());
		} catch (UnsupportedEncodingException uee) {
			disconnect();
			String msg = "UnsupportedEncoding : " + uee.getMessage();
			throw new Exception("putMessage error UnsupportedEncodingException "+msg);
		} catch (IOException ioe) {
			disconnect();
			throw new Exception("putMessage error IOException "+ioe.getMessage());
		}
	}

	public void disconnect() throws Exception {
		if (!isInit)
			setEnvirement();
		try {
			// close message queue
			if ((mqQueue != null) && (mqQueue.isOpen())) {
				mqQueue.close();
			}

			if ((queueMgr != null) && (queueMgr.isConnected())) {
				queueMgr.disconnect();
			}
		} catch (MQException ex) {
			throw new Exception("disconnect error MQException "+ex.getMessage());
		}
	}
}
