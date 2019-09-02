package com.ctbcbank.datasource.monitor.object;

public class Radio {
	//main or backup
	private String connection;
	//auto or manual
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
