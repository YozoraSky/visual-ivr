package com.ctbcbank.ivr.gateway.fax;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:fax.properties" })
public class FaxProperties {
	@Value("${fax.main_ip}")
	private String main_ip;
	@Value("${fax.backup_ip}")
	private String backup_ip;
	@Value("${fax.connectTimeout}")
	private int connectTimeout;
	@Value("${fax.soTimeout}")
	private int soTimeout;
	
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public String getMain_ip() {
		return main_ip;
	}
	public void setMain_ip(String main_ip) {
		this.main_ip = main_ip;
	}
	public String getBackup_ip() {
		return backup_ip;
	}
	public void setBackup_ip(String backup_ip) {
		this.backup_ip = backup_ip;
	}

}
