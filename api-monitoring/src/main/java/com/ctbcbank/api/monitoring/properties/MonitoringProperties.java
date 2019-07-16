package com.ctbcbank.api.monitoring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:monitor.properties" })
public class MonitoringProperties {
	
	@Value("${monitor.war}")
	private String war[];
	@Value("${monitor.file}")
	private String file;
	@Value("${monitor.url}")
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getWar() {
		return war;
	}
	public void setWar(String[] war) {
		this.war = war;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
