package com.ctbcbank.ivr.schedule.auth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ctbcbank.ivr.schedule.sftp.FTPUtil;

public abstract class Auth {
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	protected Logger logger;

	public Auth(NamedParameterJdbcTemplate namedParameterJdbcTemplate, Logger logger) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.logger = logger;
	}
	
	private void cmd_EXE(String fileName, String localPath, String deCompressKey) throws Exception {
		Runtime runtime = Runtime.getRuntime(); 
		String[] cmd = {localPath + fileName + ".EXE", "-y", "-g" + deCompressKey, "-w" + localPath + fileName};
		runtime.exec(cmd);
		logger.info("{}.exe excute success", fileName);
	}
	
	public void execute(FTPUtil ftp, String remotePath, String localPath, String deCompressKey) throws Exception{
		String fileName = getFileName();
		ftp.login();
		ftp.downloadFile(remotePath, fileName + ".EXE", localPath + fileName + ".EXE");
		logger.info("{}.exe downLoad success",fileName);
		cmd_EXE(fileName, localPath, deCompressKey);
		FileInputStream fileInputStream = new FileInputStream(localPath + fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"big5");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		long time = System.currentTimeMillis();
		batchUpdate(bufferedReader);
		logger.info("DetailLog insert time : " + (System.currentTimeMillis()-time));
		bufferedReader.close();
//		jdbcTemplate.batchUpdate(sql.toArray(new String[0]));
		ftp.logout();
	}
	
	public abstract void batchUpdate(BufferedReader bufferedReader) throws Exception;
	public abstract String getFileName();
}
