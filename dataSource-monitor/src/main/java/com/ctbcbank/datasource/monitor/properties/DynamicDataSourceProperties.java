package com.ctbcbank.datasource.monitor.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:dynamicDataSource.properties" })
public class DynamicDataSourceProperties {
	@Value("${dynamicDataSource.ip}")
	private String[] ip;

	public String[] getIp() {
		return ip;
	}

	public void setIp(String[] ip) {
		this.ip = ip;
	}
}
