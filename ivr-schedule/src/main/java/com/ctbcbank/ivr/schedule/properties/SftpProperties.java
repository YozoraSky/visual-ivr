package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:rate.properties" })
public class SftpProperties {
	@Value("${rate_ivr.host}")
	private String host;
	@Value("${rate_ivr.username}")
	private String username;
	@Value("${rate_ivr.password}")
	private String password;
	@Value("${rate_ivr.directory}")
	private String directory;
	@Value("${rate_ivr.downloadFile}")
	private String downloadFile;
	@Value("${rate_ivr.savePath}")
	private String savePath;
	@Value("${InterestRateCode.path}")
	private String interestRateCodePath;
	@Value("${NCCInterestRate_sql}")
	private String sql;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getInterestRateCodePath() {
		return interestRateCodePath;
	}
	public void setInterestRateCodePath(String interestRateCodePath) {
		this.interestRateCodePath = interestRateCodePath;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
