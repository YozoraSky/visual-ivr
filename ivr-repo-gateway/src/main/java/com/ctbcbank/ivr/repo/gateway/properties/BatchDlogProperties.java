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
	@Value("${batchDlog.selectStatusSQL}")
	private String detailLogSelectStatusSQL;
	@Value("${batchDlog.deleteDetailLog}")
	private String deleteDetailLog;
	@Value("${batcgDlog.selectCountSQL}")
	private String detailLogSelectCountSQL;
	
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

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	
	public String getDetailLogSelectCountSQL() {
		return detailLogSelectCountSQL;
	}

	public void setDetailLogSelectCountSQL(String detailLogSelectCountSQL) {
		this.detailLogSelectCountSQL = detailLogSelectCountSQL;
	}

}
