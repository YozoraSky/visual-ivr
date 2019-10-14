package com.ctbcbank.datasource.monitor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dynamicDataSource")
@PropertySource(value = { "classpath:dynamicDataSource.properties" })
public class DynamicDataSourceProperties {
	private String[] ip;
	private int mainDataBaseFailCount;
	private int reconnectToMainDataBaseCount;
	
	public String[] getIp() {
		return ip;
	}

	public void setIp(String[] ip) {
		this.ip = ip;
	}
	public int getMainDataBaseFailCount() {
		return mainDataBaseFailCount;
	}

	public void setMainDataBaseFailCount(int mainDataBaseFailCount) {
		this.mainDataBaseFailCount = mainDataBaseFailCount;
	}

	public int getReconnectToMainDataBaseCount() {
		return reconnectToMainDataBaseCount;
	}

	public void setReconnectToMainDataBaseCount(int reconnectToMainDataBaseCount) {
		this.reconnectToMainDataBaseCount = reconnectToMainDataBaseCount;
	}
}
