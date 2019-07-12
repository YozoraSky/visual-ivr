package com.ctbcbank.ivr.schedule.auth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

	private void cmd_EXE(String fileName, String directory, String deCompressKey) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process process;
		String[] cmd = { directory + fileName + ".EXE", "-y", "-g" + deCompressKey, "-w" + directory + fileName };
		process = runtime.exec(cmd);
		new Thread() {
			@Override
			public void run() {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"big5"));
					String line = null;
					while ((line = in.readLine()) != null) {
						logger.info("output : {}", line);
					}
					in.close();
				} catch (IOException e) {
					logger.info("cmd_EXE inputStream error msg : ",e);
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream(),"big5"));
					String line = null;
					while ((line = err.readLine()) != null) {
						logger.info("err : {}", line);
					}
				} catch (IOException e) {
					logger.info("cmd_EXE errorStream error msg : ",e);
				}
			}
		}.start();
		int i = process.waitFor();
		if(i==0)
			logger.info("{}.exe excute success", fileName);
		else
			logger.info("{}.exe excute fail : {}", fileName, i);
	}

	public void execute(FTPUtil ftp, String remotePath, String directory, String deCompressKey) throws Exception {
		File file = new File(directory);
		if(!file.exists())
			file.mkdirs();
		String fileName = getFileName();
		ftp.login();
		ftp.downloadFile(remotePath, fileName + ".EXE", directory + fileName + ".EXE");
		logger.info("{}.exe downLoad success", fileName);
		cmd_EXE(fileName, directory, deCompressKey);
		FileInputStream fileInputStream = new FileInputStream(directory + fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "big5");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		long time = System.currentTimeMillis();
		batchUpdate(bufferedReader);
		logger.info("batchUpdate time : " + (System.currentTimeMillis() - time));
		bufferedReader.close();
		ftp.logout();
	}

	public abstract void batchUpdate(BufferedReader bufferedReader) throws Exception;

	public abstract String getFileName();
}
