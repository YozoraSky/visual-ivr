package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:batchDlog.properties" })
public class BatchDlogProperties {
	@Value("${batchDlog.log.path}")
	private String logPath;

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}	
}
