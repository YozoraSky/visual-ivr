package com.ctbcbank.ivr.repo.gateway.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:key.properties" })
public class KeyProperties {
	@Value("${key}")
	private String key;
	@Value("${aes.iv}")
	private String aesIV;
	@Value("${aes.key}")
	private String aesKey;
	
	public String getAesIV() {
		return aesIV;
	}

	public void setAesIV(String aesIV) {
		this.aesIV = aesIV;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
