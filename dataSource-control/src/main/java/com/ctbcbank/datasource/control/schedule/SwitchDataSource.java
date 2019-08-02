package com.ctbcbank.datasource.control.schedule;

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

import com.ctbcbank.datasource.control.properties.DynamicDataSourceProperties;

@Component
@EnableScheduling
public class SwitchDataSource {
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
	private Logger logger = LoggerFactory.getLogger("dataSource-monitor");
	
	private static String dataSource = "main"; // main and backup
	private static int mainDBConnectFailcount = 0;
	private static int reConnecttoMainDBcount = 0;
	
	@Scheduled(cron = "0/15 * * * * ?")
	public void run() {
		try {
			configJdbcTemplate.queryForMap("select 1");
			if(mainDBConnectFailcount>=3) {
				reConnecttoMainDBcount++;
				if(reConnecttoMainDBcount>=3) {
					mainDBConnectFailcount = 0;
					reConnecttoMainDBcount = 0;
					dataSource = "main";
				}
			}
			else
				mainDBConnectFailcount = 0;
		}catch(Exception e) {
			System.out.println(e.toString());
			mainDBConnectFailcount++;
			if(mainDBConnectFailcount>=3) {
				dataSource = "backup";
			}
		}
		String[] ip = dynamicDataSourceProperties.getIp();
		if(dataSource.equals("main")) {
			for(int i=0;i<ip.length;i++) {
				httpGet("http://" + ip[i] + "ivr-repo-gateway/datasource/switch?dataSourceRegion=main");
				logger.info(ip[i] + "dataSource connect to Taipei");
			}
		}
		else {
			for(int i=0;i<ip.length;i++) {
				httpGet("http://" + ip[i] + "ivr-repo-gateway/datasource/switch?dataSourceRegion=backup");
				logger.info(ip[i] + "dataSource connect to Taichung");
			}
		}
	}

	public String httpGet(String url) {
		String result = StringUtils.EMPTY;
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			result = "httpGet fail";
		}
		logger.info(result);
		return result;
	}
}
