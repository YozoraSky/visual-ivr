package com.ctbcbank.ivr.repo.gateway.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:batchDlog.properties" })
public class BatchDlogProperties {
	@Value("${batchDlog.log.path}")
	private String logPath;
	@Value("${batchDlog.insertStatusSQL}")
	private String detailLogInsertStatusSQL;
	@Value("${batchDlog.updateStatusSQL}")
	private String detailLogUpdateStatusSQL;
	public String getDetailLogInsertStatusSQL() {
		return detailLogInsertStatusSQL;
	}

	public void setDetailLogInsertStatusSQL(String detailLogInsertStatusSQL) {
		this.detailLogInsertStatusSQL = detailLogInsertStatusSQL;
	}

	public String getDetailLogUpdateStatusSQL() {
		return detailLogUpdateStatusSQL;
	}

	public void setDetailLogUpdateStatusSQL(String detailLogUpdateStatusSQL) {
		this.detailLogUpdateStatusSQL = detailLogUpdateStatusSQL;
	}

	public String getDetailLogSelectStatusSQL() {
		return detailLogSelectStatusSQL;
	}

	public void setDetailLogSelectStatusSQL(String detailLogSelectStatusSQL) {
		this.detailLogSelectStatusSQL = detailLogSelectStatusSQL;
	}

	public String getDeleteDetailLog() {
		return deleteDetailLog;
	}

	public void setDeleteDetailLog(String deleteDetailLog) {
		this.deleteDetailLog = deleteDetailLog;
	}

	@Value("${batchDlog.selectStatusSQL}")
	private String detailLogSelectStatusSQL;
	@Value("${batchDlog.deleteDetailLog}")
	private String deleteDetailLog;

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}	
}
