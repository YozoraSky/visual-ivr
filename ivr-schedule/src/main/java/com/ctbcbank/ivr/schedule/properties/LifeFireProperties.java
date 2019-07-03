package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:lifefire.properties" })
public class LifeFireProperties {
	@Value("${lifefire.searchsql}")
	private String searchsql;
	@Value("${lifefire.updatesql}")
	private String updatesql;
	@Value("${lifefire.ftphostname}")
	private String ftphostname;
	@Value("${lifefire.ftpusername}")
	private String ftpusername;
	@Value("${lifefire.ftppassword}")
	private String ftppassword;
	@Value("${lifefire.ftpsavepath}")
	private String ftpsavepath;
	public String getSearchsql() {
		return searchsql;
	}
	public void setSearchsql(String searchsql) {
		this.searchsql = searchsql;
	}
	public String getUpdatesql() {
		return updatesql;
	}
	public void setUpdatesql(String updatesql) {
		this.updatesql = updatesql;
	}
	public String getFtphostname() {
		return ftphostname;
	}
	public void setFtphostname(String ftphostname) {
		this.ftphostname = ftphostname;
	}
	public String getFtpusername() {
		return ftpusername;
	}
	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}
	public String getFtppassword() {
		return ftppassword;
	}
	public void setFtppassword(String ftppassword) {
		this.ftppassword = ftppassword;
	}
	public String getFtpsavepath() {
		return ftpsavepath;
	}
	public void setFtpsavepath(String ftpsavepath) {
		this.ftpsavepath = ftpsavepath;
	}
	public String getFtplocalFile() {
		return ftplocalFile;
	}
	public void setFtplocalFile(String ftplocalFile) {
		this.ftplocalFile = ftplocalFile;
	}
	@Value("${lifefire.ftplocalFile}")
	private String ftplocalFile;
}
