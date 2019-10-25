package com.ctbcbank.datasource.monitor.object;

//儲存目前的連線狀況。main為台北，backup為台中
public class DataSourceStatus {
//	main or backup
	private static String connection = "main";
//	true為自動切換，false為手動遷換
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
