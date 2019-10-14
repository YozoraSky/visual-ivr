package com.ctbcbank.datasource.monitor.object;

public class DataSourceStatus {
	//main or backup
	private static String connection = "main";
	private static boolean autoOrNot = true; 
	
	public static String getConnection() {
		return connection;
	}

	public static void setConnection(String connection) {
		DataSourceStatus.connection = connection;
	}
	public static boolean getAutoOrNot() {
		return autoOrNot;
	}

	public static void setAutoOrNot(boolean autoOrNot) {
		DataSourceStatus.autoOrNot = autoOrNot;
	}
}
