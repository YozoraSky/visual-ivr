package com.ctbcbank.datasource.monitor.object;

//儲存從前端獲得的使用者輸入
public class Radio {
	// main or backup
	private String connection;
	// auto or manual
	private String autoOrNot;

	public String getAutoOrNot() {
		return autoOrNot;
	}

	public void setAutoOrNot(String autoOrNot) {
		this.autoOrNot = autoOrNot;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}
}
