package com.ctbcbank.ivr.schedule.sftp;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
import java.util.Vector;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.Channel;  
import com.jcraft.jsch.ChannelSftp;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.JSchException;  
import com.jcraft.jsch.Session;  
import com.jcraft.jsch.SftpException; 
/** 
* 類說明 sftp工具類
*/
public class SFTPUtil { 
	private ChannelSftp sftp;  
	private Session session;  
	/** SFTP 登入使用者名稱*/    
	private String username; 
	/** SFTP 登入密碼*/    
	private String password;  
	/** 私鑰 */    
	private String privateKey;  
	/** SFTP 伺服器地址IP地址*/    
	private String host;  
	/** SFTP 埠*/  
	private int port;  
	/**  
	 * 構造基於密碼認證的sftp物件  
	 */    
	public SFTPUtil(String username, String password, String host, int port) {  
		this.username = username;  
		this.password = password;  
		this.host = host;  
		this.port = port;  
	} 
/**  
* 構造基於祕鑰認證的sftp物件 
*/  
	public SFTPUtil(String username, String host, int port, String privateKey) {  
		this.username = username;  
		this.host = host;  
		this.port = port;  
		this.privateKey = privateKey;  
		}  
	public SFTPUtil(){}  
/** 
* 連線sftp伺服器 
*/  
	public void login(){  
		try {  
			JSch jsch = new JSch();  
			if (privateKey != null) {  
				jsch.addIdentity(privateKey);// 設定私鑰  
			}  
			session = jsch.getSession(username, host, port);  
			if (password != null) {  
				session.setPassword(password);    
			}  
			Properties config = new Properties();  
			config.put("StrictHostKeyChecking", "no");  
			session.setConfig(config);  
			session.connect();  
			Channel channel = session.openChannel("ftp");  
			channel.connect();  
			sftp = (ChannelSftp) channel;  
		} catch (JSchException e) {  
			e.printStackTrace();
		}  
	}    
/** 
* 關閉連線 server  
*/  
	public void logout(){  
		if (sftp != null) {  
			if (sftp.isConnected()) {  
				sftp.disconnect();  
			}  
		}  
		if (session != null) {  
			if (session.isConnected()) {  
				session.disconnect();  
			}  
		}  
	}  
/**  
* 將輸入流的資料上傳到sftp作為檔案。檔案完整路徑=basePath directory
* @param basePath  伺服器的基礎路徑 
* @param directory  上傳到該目錄  
* @param sftpFileName  sftp端檔名  
* @param in   輸入流  
*/  
	public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{  
		try {   
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) { 
			//目錄不存在，則建立資料夾
			String [] dirs=directory.split("/");
			String tempPath=basePath;
			for(String dir:dirs){
				if(null== dir || "".equals(dir)) continue;
				tempPath =dir;
				try{ 
					sftp.cd(tempPath);
				}catch(SftpException ex){
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
	}  
	sftp.put(input, sftpFileName);  //上傳檔案
} 
/** 
* 下載檔案。
* @param directory 下載目錄  
* @param downloadFile 下載的檔案 
* @param saveFile 存在本地的路徑 
*/    
	public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException{  
		if (directory != null && !"".equals(directory)) {  
			sftp.cd(directory);  
		}  
		File file = new File(saveFile);  
		sftp.get(downloadFile, new FileOutputStream(file));  
	}  
/**  
* 下載檔案 
* @param directory 下載目錄 
* @param downloadFile 下載的檔名 
* @return 位元組陣列 
*/  
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException{  
		if (directory != null && !"".equals(directory)) {  
			sftp.cd(directory);  
		}  
		InputStream is = sftp.get(downloadFile);  
		byte[] fileData = IOUtils.toByteArray(is);  
		return fileData;  
	}  
/** 
* 刪除檔案 
* @param directory 要刪除檔案所在目錄 
* @param deleteFile 要刪除的檔案 
*/  
	public void delete(String directory, String deleteFile) throws SftpException{  
		sftp.cd(directory);  
		sftp.rm(deleteFile);  
	}  
/** 
* 列出目錄下的檔案 
* @param directory 要列出的目錄 
* @param sftp 
*/  
	public Vector<?> listFiles(String directory) throws SftpException {  
		return sftp.ls(directory);  
	}  
}
