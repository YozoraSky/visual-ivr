package com.ctbcbank.ivr.schedule.sftp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	private FTPClient ftp;
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
        ftp = new FTPClient(); 
        try {
			ftp.connect(host, port);
		    int reply = ftp.getReplyCode(); 
		    System.out.println(FTPReply.isPositiveCompletion(reply)); 
		    ftp.login(username, password);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void logout() {
		try {
			if(ftp!=null) {
				ftp.logout();
				ftp.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* 下載檔案。
	* @param directory 下載目錄  
	* @param downloadFile 下載的檔案 
	* @param saveFile 存在本地的路徑 
	*/
	public void downloadFile(String directory, String downloadFile, String saveFile) {
			FileOutputStream fout;
			try {
				fout = new FileOutputStream(saveFile);
				ftp.retrieveFile(directory + downloadFile, fout);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
