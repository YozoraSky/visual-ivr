package com.ctbcbank.api.monitoring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "monitor")
@PropertySource(value = { "classpath:monitor.properties" })
public class MonitoringProperties {
	
	private String war[];
	private String file;
	private String url;
	private int warningTimes;
	
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
	public int getWarningTimes() {
		return warningTimes;
	}
	public void setWarningTimes(int warningTimes) {
		this.warningTimes = warningTimes;
	}
	
}
