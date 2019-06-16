package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource(value = { "classpath:exrate.properties" })
public class ExRateProperties {
	@Value("${exrate_ivr.json}")
	private String Json;
	@Value("${exrate_ivr.url}")
	private String url;
	@Value("${exrate_ivr.sql}")
	private String sql;
	@Value("${exrate_ivr.batchsql}")
	private String batchsql;
	public String getJson() {
		return Json;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getBatchsql() {
		return batchsql;
	}
	public void setBatchsql(String batchsql) {
		this.batchsql = batchsql;
	}
	public void setJson(String json) {
		Json = json;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
