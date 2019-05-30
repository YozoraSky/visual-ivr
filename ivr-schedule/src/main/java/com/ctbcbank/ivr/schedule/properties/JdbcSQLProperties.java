package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:jdbc-sql.properties" })
public class JdbcSQLProperties {
	@Value("${detailLog.insertStatusSQL}")
	private String detailLogInsertStatusSQL;
	@Value("${detailLog.updateStatusSQL}")
	private String detailLogUpdateStatusSQL;
	@Value("${detailLog.selectStatusSQL}")
	private String detailLogSelectStatusSQL;
	@Value("${detailLog.deleteDetailLog}")
	private String deleteDetailLog;
	
	public String getDeleteDetailLog() {
		return deleteDetailLog;
	}
	public void setDeleteDetailLog(String deleteDetailLog) {
		this.deleteDetailLog = deleteDetailLog;
	}
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
}
