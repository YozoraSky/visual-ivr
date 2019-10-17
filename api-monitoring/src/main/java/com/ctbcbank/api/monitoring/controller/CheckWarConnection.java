package com.ctbcbank.api.monitoring.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.api.monitoring.properties.MonitoringProperties;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:monitor.properties" })
public class CheckWarConnection {
	private static int ivr_gateway = 0;
	private static int ivr_repo_gateway = 0;
	private Logger logger = LoggerFactory.getLogger("monitor");

	@Autowired
	private MonitoringProperties monitoringProperties;

	@Scheduled(initialDelayString = "${monitor.initialDelay}", fixedRateString = "${monitor.fixedRate}")
	public void run() {
		String[] wars = monitoringProperties.getWar();
		String results[] = new String[wars.length];
		try {
			for (int i=0;i<wars.length;i++) {
				results[i] = wars[i] + "-----";
				results[i] += httpPost(monitoringProperties.getUrl().replace("@war", wars[i]), wars[i]);
			}
			File file = new File(monitoringProperties.getFile());
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(String result:results) {
				bw.write(result + "\n");
			}
			bw.close();
		} catch (Exception e) {
			logger.info("error : ", e);
		}
	}

	public String httpPost(String url, String war) {
		try {
			String result = StringUtils.EMPTY;
			URL endpoint = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setReadTimeout(5000);
			httpConnection.setConnectTimeout(5000);
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestProperty("Content-Type", "Text");
			DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferReader.close();
			result = stringBuilder.toString();
			switch (war) {
			case "ivr-gateway":
				ivr_gateway = 0;
				break;
			case "ivr-repo-gateway":
				ivr_repo_gateway = 0;
				break;
			}
			return result;
		} catch (Exception e) {
			logger.error(e.toString() + " : " + war);
			String result = StringUtils.EMPTY;
			switch (war) {
			case "ivr-gateway":
				ivr_gateway++;
				if(ivr_gateway>=monitoringProperties.getWarningTimes())
					result = "Error";
				else
					result = "Warning";
				break;
			case "ivr-repo-gateway":
				ivr_repo_gateway++;
				if(ivr_repo_gateway>=monitoringProperties.getWarningTimes())
					result = "Error";
				else
					result = "Warning";
				break;
			}
			return result;
		}
	}
}
