package com.ctbcbank.visual.ivr.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "shanghai.voice")
@PropertySource(value = { "classpath:webService.properties" })
public class ShanghaiVoiceProperties {
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
