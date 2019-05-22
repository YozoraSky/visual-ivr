package com.ctbcbank.mq.gateway.object;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:mq.properties" })
public class MqProperties {
//	@Value("${ManagerNameSIT}")
//	private String queueManagerNameSIT;
//	@Value("${ManagerNameUAT}")
//	private String queueManagerNameUAT;
	@Value("${MQ.host}")
	private String host;
	@Value("${MQ.port}")
	private int port;
	@Value("${MQ.sql}")
	private String sql;
//	@Value("${Channel}")
//	private String channel;
//	@Value("${QueueName}")
//	private String queuename;

//	public String getQueueManagerNameSIT() {
//		return queueManagerNameSIT;
//	}
//
//	public void setQueueManagerNameSIT(String queueManagerNameSIT) {
//		this.queueManagerNameSIT = queueManagerNameSIT;
//	}
//
//	public String getQueueManagerNameUAT() {
//		return queueManagerNameUAT;
//	}
//
//	public void setQueueManagerNameUAT(String queueManagerNameUAT) {
//		this.queueManagerNameUAT = queueManagerNameUAT;
//	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
