package com.ctbcbank.api.monitoring.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.api.monitoring.properties.FaxProperties;

@Component
@EnableScheduling
public class CheckFax {
	private Logger logger = LoggerFactory.getLogger("monitor");
	@Autowired
	private FaxProperties faxProperties;

	@Scheduled(cron = "0 0/30 * * * ?")
	public void run() {
		boolean[] retry = new boolean[1];
		String result = StringUtils.EMPTY;
		File file = new File(faxProperties.getFile());
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			for (int i = 0; i < 3; i++) {
				result = fax(retry);
				if (!retry[0])
					break;
			}
			writer.write("fax-----" + result);
			writer.write("\n");
			writer.close();
		} catch (FileNotFoundException e) {
			logger.info(e.toString());
		}
	}

	public String fax(boolean[] retry) {
		try {
			URL endpoint = new URL(faxProperties.getUrl());
			HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setReadTimeout(3000);
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestProperty("Content-Type", "Text");
			DataOutputStream outputStream = new DataOutputStream(httpConnection.getOutputStream());
			outputStream.write("TEST".getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
			DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferReader.close();
			String ok = stringBuilder.toString();
			retry[0] = false;
			return ok;
		} catch (Exception e) {
			retry[0] = true;
			logger.error(e.toString());
			return "Connection interruption";
		}
	}
}
