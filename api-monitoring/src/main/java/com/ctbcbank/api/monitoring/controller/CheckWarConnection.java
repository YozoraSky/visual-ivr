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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.api.monitoring.properties.MonitoringProperties;

@Component
@EnableScheduling
public class CheckWarConnection {
	private static int ivr_authBackup_gateway = 0;
	private static int ivr_gateway = 0;
	private static int ivr_mq_gateway = 0;
	private static int ivr_repo_gateway = 0;
	private static int ivr_schedule = 0;
	private Logger logger = LoggerFactory.getLogger("monitor");
	
	@Autowired
	private MonitoringProperties monitoringProperties;
	
	@Scheduled(initialDelay=60000, fixedRate=30000)
	public void run(){
		String result;
		String[] wars = monitoringProperties.getWar();
		try {
			File file = new File(monitoringProperties.getFile());
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(String war : wars) {
				result = httpPost(monitoringProperties.getUrl().replace("@war", war), war);
				bw.write(war +"-----" + result);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			logger.info("error : ",e);
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
			while((line = bufferReader.readLine())!=null) {
				stringBuilder.append(line);
			}
			bufferReader.close();
			result = stringBuilder.toString();
			switch(war) {
			case "ivr-authBackup-gateway":ivr_authBackup_gateway=0;break;
			case "ivr-gateway":ivr_gateway=0;break;
			case "ivr-mq-gateway":ivr_mq_gateway=0;break;
			case "ivr-repo-gateway":ivr_repo_gateway=0;break;
			case "ivr-schedule":ivr_schedule=0;break;
			}
			return result;
		}
		catch(Exception e){
			logger.error(e.toString() + " : " +  war);
			switch(war) {
				case "ivr-authBackup-gateway":ivr_authBackup_gateway++;break;
				case "ivr-gateway":ivr_gateway++;break;
				case "ivr-mq-gateway":ivr_mq_gateway++;break;
				case "ivr-repo-gateway":ivr_repo_gateway++;break;
				case "ivr-schedule":ivr_schedule++;break;
			}
			if(ivr_authBackup_gateway>=3||
			   ivr_gateway>=3||
			   ivr_mq_gateway>=3||
			   ivr_repo_gateway>=3||
			   ivr_schedule>=3)
				return "Error";
			else
				return "Warning";
		}
	}
}
