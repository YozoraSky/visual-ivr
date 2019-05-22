package com.ctbcbank.ivr.gateway.line;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:line.properties" })
public class LineProperties {
	@Value("${lineBcSendUrl}")
	private String lineBcSendUrl;
	@Value("${lineBcGetUidUrl}")
	private String lineBcGetUidUrl;
	@Value("${lineCCUrl}")
	private String lineCcUrl;
	@Value("${lineBcSendString}")
	private String lineBcSendString;
	@Value("${lineBcGetUidString}")
	private String lineBcGetUidString;
	@Value("${lineCCString}")
	private String lineCcString;
	
	public String getLineBcSendUrl() {
		return lineBcSendUrl;
	}
	public void setLineBcSendUrl(String lineBcSendUrl) {
		this.lineBcSendUrl = lineBcSendUrl;
	}
	public String getLineBcGetUidUrl() {
		return lineBcGetUidUrl;
	}
	public void setLineBcGetUidUrl(String lineBcGetUidUrl) {
		this.lineBcGetUidUrl = lineBcGetUidUrl;
	}
	public String getLineCcUrl() {
		return lineCcUrl;
	}
	public void setLineCcUrl(String lineCCUrl) {
		this.lineCcUrl = lineCCUrl;
	}
	public String getLineBcSendString() {
		return lineBcSendString;
	}
	public void setLineBcSendString(String lineBcSendString) {
		this.lineBcSendString = lineBcSendString;
	}
	public String getLineBcGetUidString() {
		return lineBcGetUidString;
	}
	public void setLineBcGetUidString(String lineBcGetUidString) {
		this.lineBcGetUidString = lineBcGetUidString;
	}
	public String getLineCcString() {
		return lineCcString;
	}
	public void setLineCcString(String lineCCString) {
		this.lineCcString = lineCCString;
	}
}
