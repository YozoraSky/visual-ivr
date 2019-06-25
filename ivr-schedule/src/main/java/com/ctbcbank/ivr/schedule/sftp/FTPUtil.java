package com.ctbcbank.ivr.schedule.sftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	private FTPClient ftpClient;
	private String host;
	private String username;
	private String password;
	private int port;
	
	public FTPUtil(String host, String username, String password, int port) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	public void login(){
		ftpClient = new FTPClient(); 
        try {
        	ftpClient.connect(host, port);
		    ftpClient.login(username, password);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
		    if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
		    	System.out.println("成功連線");
		    }
		    else
		    	System.out.println("連線失敗");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void logout() {
		try {
			if(ftpClient!=null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* 下載檔案。
	* @param directory 下載目錄  
	* @param downloadFile 下載的檔案 
	* @param saveFile 本地端檔案路徑 
	*/
	public void downloadFile(String directory, String downloadFile, String saveFile) {
			FileOutputStream fout;
			try {
				fout = new FileOutputStream(saveFile);
				ftpClient.changeWorkingDirectory(directory);
				ftpClient.retrieveFile(downloadFile, fout);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**  
	* 將輸入流的資料上傳到sftp作為檔案
	* @param directory 上傳到該目錄  
	* @param fileName  上傳的檔名  
	* @param localFile 本地端檔案路徑  
	*/  
	public void upLoadFile(String directory, String fileName, String localFile) {
		FileInputStream fin;
		try {
			fin = new FileInputStream(localFile);
			ftpClient.changeWorkingDirectory(directory);
			ftpClient.storeFile(fileName, fin);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
