package com.ctbcbank.visual.ivr.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:esb-env.properties" })
public class EsbProperties {
	@Value("${esb.provider.context.factory}")
	private String providerContextFactory;
	@Value("${esb.connection.factory}")
	private String connectionFactory;
	@Value("${esb.provider.url}")
	private String providerUrl;
	@Value("${esb.username}")
	private String username;
	@Value("${esb.p1}")
	private String esbP1;
	@Value("${esb.sendQueueName}")
	private String queueName;
	@Value("${esb.replyQueueName}")
	private String replayQueueName;
	@Value("${esb.timeout}")
	private Integer timeout;

	public String getProviderContextFactory() {
		return providerContextFactory;
	}

	public void setProviderContextFactory(String providerContextFactory) {
		this.providerContextFactory = providerContextFactory;
	}

	public String getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(String connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEsbP1() {
		return esbP1;
	}

	public void setEsbP1(String esbP1) {
		this.esbP1 = esbP1;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getReplayQueueName() {
		return replayQueueName;
	}

	public void setReplayQueueName(String replayQueueName) {
		this.replayQueueName = replayQueueName;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
