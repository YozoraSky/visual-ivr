package com.ctbcbank.ivr.schedule.sftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	
	public String login() throws Exception{
		ftpClient = new FTPClient();
        ftpClient.connect(host, port);
		ftpClient.login(username, password);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
			return "連線成功";
		else
		    return "連線失敗";
	}
	
	public void logout() throws Exception{
		if(ftpClient!=null) {
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}
	
	/** 
	* 下載檔案。
	* @param directory 下載目錄  
	* @param downloadFile 下載的檔案 
	* @param saveFile 本地端檔案路徑 
	*/
	public void downloadFile(String directory, String downloadFile, String saveFile) throws Exception{
		FileOutputStream fout;
		fout = new FileOutputStream(saveFile);
		ftpClient.changeWorkingDirectory(directory);
		if(isExisted(downloadFile)) {
			ftpClient.retrieveFile(downloadFile, fout);
			fout.close();
		}
		else {
			fout.close();
			throw new Exception("file is not found on the ftp server");
		}
	}
	
	public boolean isExisted(String fileName) throws Exception{
		String[] fileNames = ftpClient.listNames();
		boolean status = true;
		for(int i=0;i<fileNames.length;i++) {
			if(fileName.equals(fileNames[i]))
				status = true;
			else 
				status = false;
		}
		return status;
	}
	
	/**  
	* 將輸入流的資料上傳到sftp作為檔案
	* @param directory 上傳到該目錄  
	* @param fileName  上傳的檔名  
	* @param localFile 本地端檔案路徑  
	*/  
	public void upLoadFile(String directory, String fileName, String localFile) throws Exception{
		FileInputStream fin;
		fin = new FileInputStream(localFile);
		ftpClient.changeWorkingDirectory(directory);
		ftpClient.storeFile(fileName, fin);
		fin.close();
	}
}
