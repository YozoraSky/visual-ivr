package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:ethan.properties" })
@ConfigurationProperties(prefix="sql")
public class EthanProperties {
	private String IVRCustomerSegementSql;
	private String CTISystemSettingSql;
	private String IVRTransferPoint2Sql;
	private String IVRTransferService2Sql;
	@Value("${IVRCustomerSegementPath}")
	private String IVRCustomerSegementPath;
	@Value("${CTISystemSettingPath}")
	private String CTISystemSettingPath;
	@Value("${IVRTransferPoint2Path}")
	private String IVRTransferPoint2Path;
	@Value("${IVRTransferService2Path}")
	private String IVRTransferService2Path;
	
	public String getCTISystemSettingPath() {
		return CTISystemSettingPath;
	}
	public void setCTISystemSettingPath(String cTISystemSettingPath) {
		CTISystemSettingPath = cTISystemSettingPath;
	}
	public String getIVRTransferPoint2Path() {
		return IVRTransferPoint2Path;
	}
	public void setIVRTransferPoint2Path(String iVRTransferPoint2Path) {
		IVRTransferPoint2Path = iVRTransferPoint2Path;
	}
	public String getIVRTransferService2Path() {
		return IVRTransferService2Path;
	}
	public void setIVRTransferService2Path(String iVRTransferService2Path) {
		IVRTransferService2Path = iVRTransferService2Path;
	}
	
	public String getIVRCustomerSegementPath() {
		return IVRCustomerSegementPath;
	}
	public void setIVRCustomerSegementPath(String iVRCustomerSegementPath) {
		IVRCustomerSegementPath = iVRCustomerSegementPath;
	}
	public String getIVRCustomerSegementSql() {
		return IVRCustomerSegementSql;
	}
	public void setIVRCustomerSegementSql(String iVRCustomerSegementSql) {
		IVRCustomerSegementSql = iVRCustomerSegementSql;
	}
	public String getCTISystemSettingSql() {
		return CTISystemSettingSql;
	}
	public void setCTISystemSettingSql(String cTISystemSettingSql) {
		CTISystemSettingSql = cTISystemSettingSql;
	}
	public String getIVRTransferPoint2Sql() {
		return IVRTransferPoint2Sql;
	}
	public void setIVRTransferPoint2Sql(String iVRTransferPoint2Sql) {
		IVRTransferPoint2Sql = iVRTransferPoint2Sql;
	}
	public String getIVRTransferService2Sql() {
		return IVRTransferService2Sql;
	}
	public void setIVRTransferService2Sql(String iVRTransferService2Sql) {
		IVRTransferService2Sql = iVRTransferService2Sql;
	}
}
