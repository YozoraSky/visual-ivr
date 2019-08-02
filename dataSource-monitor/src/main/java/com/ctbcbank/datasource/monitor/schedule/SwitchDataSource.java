package com.ctbcbank.datasource.monitor.schedule;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.datasource.monitor.properties.DynamicDataSourceProperties;
import com.ctbcbank.datasource.monitor.properties.KeyProperties;

@Component
@EnableScheduling
public class SwitchDataSource {
	private Logger logger = LoggerFactory.getLogger("dataSource-monitor");
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate configJdbcTemplate;
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate_backup")
	private JdbcTemplate configJdbcTemplate_backup;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate logJdbcTemplate;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate_backup")
	private JdbcTemplate logJdbcTemplate_backup;
	@Autowired
	private DynamicDataSourceProperties dynamicDataSourceProperties;
	@Autowired
	private KeyProperties keyProperties;
	
	private static String dataSource = "main"; //main abd backup
	private static int mainDBConnectFailCount = 0;
	private static int reConnectToMainDBCount = 0;
	
	@Scheduled(cron = "0/15 * * * * ?")
	public void run() {
		String alert = StringUtils.EMPTY;
		try {
			configJdbcTemplate.queryForMap("SELECT 1");
			if(mainDBConnectFailCount>=3) {
				reConnectToMainDBCount++;
				if(reConnectToMainDBCount>=3) {
					mainDBConnectFailCount = 0;
					reConnectToMainDBCount = 0;
					dataSource = "main";
				}
			}
			else
				mainDBConnectFailCount = 0;
		} catch(Exception e) {
			logger.info(e.toString());
			alert = " (warning)";
			mainDBConnectFailCount++;
			if(mainDBConnectFailCount>=3) {
				dataSource = "backup";
			}
		}
		String[] ip = dynamicDataSourceProperties.getIp();
		String result = StringUtils.EMPTY;
		if(dataSource.equals("main")) {
			for(int i=0;i<ip.length;i++) {
				result = httpGet("http://" + ip[i] + "ivr-repo-gateway/datasource/switch?dataSource=main&key=" + keyProperties.getKey());
				logger.info("http://" + ip[i] + " " + result + alert);
				alert = StringUtils.EMPTY;
			}
		}
		else {
			for(int i=0;i<ip.length;i++) {
				result = httpGet("http://" + ip[i] + "ivr-repo-gateway/datasource/switch?dataSource=backup&key=" + keyProperties.getKey());
				logger.info("http://" + ip[i] + " " + result);
			}
		}
	}
	
	public String httpGet(String url) {
		String result = StringUtils.EMPTY;
		try {
			URL endpoint = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setConnectTimeout(5000);
//			httpConnection.setReadTimeout(5000);
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestProperty("Content-Type", "Text");
			DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferedReader.close();
			result = stringBuilder.toString();
		} catch (Exception e) {
			result = "httpGet fail";
			logger.info("error : ", e);
		}
		return result;
	}
}
