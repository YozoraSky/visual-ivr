package com.ctbcbank.ivr.schedule.batch;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.properties.ExRateProperties;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:exrate.properties" })
public class ExRate_ivr {
	private Logger logger = LoggerFactory.getLogger("exrate_ivr");
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ExRateProperties exRateProperties;
	@Scheduled(cron="${exrate_ivr.cron.msg}")
	public void run(){
		String uuid = UUID.randomUUID().toString();
		String url = StringUtils.EMPTY;
		JSONObject jsonObject = null;
		String sql;
		int check, success = 0, fail = 0;
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat nowdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
			url = exRateProperties.getUrl();
			long Sendtime = System.currentTimeMillis();
			Date now = new Date(Sendtime);
			String jsonString = exRateProperties.getJson().replace("@SendTime", nowdatetime.format(now));
			jsonObject = httpPost(url,jsonString);
			if(jsonObject.getJSONObject("processResult").get("status").equals("s")) {
				JSONArray exchangeRate= jsonObject.getJSONObject("data").getJSONObject("RESBDY").getJSONArray("BDYREC");
				String Ccy,CashInd,BuyRate,SellRate;
				for(int index = 0 ; index < exchangeRate.size() ; index ++) {
					Ccy = exchangeRate.getJSONObject(index).getString("Ccy");
					CashInd = exchangeRate.getJSONObject(index).getString("CashInd");
					BuyRate = Decimal_point(exchangeRate.getJSONObject(index).getString("BuyRate"));
					SellRate = Decimal_point(exchangeRate.getJSONObject(index).getString("SellRate"));
					sql = exRateProperties.getSql().replace("@UUId", uuid)
												   .replace("@Ccy", Ccy)
												   .replace("@CashInd", CashInd)
												   .replace("@BuyRate", BuyRate)
												   .replace("@SellRate", SellRate);
					check=jdbcTemplate.update(sql);//insert DB
					if(check==1) {
						success++;
						logger.info("Ccy:" + Ccy + 
									" CashInd:" + CashInd + 
									" BuyRate:" + BuyRate + 
									" SellRate:" + SellRate);
					}
					else {
						fail++;
						logger.info("Fail SQL:"+sql);
					}	
				}
				//批次狀態insert DB
				jdbcTemplate.update(exRateProperties.getBatchsql().replace("@UUId", uuid)
																  .replace("@FilePath", "exrate_ivr"+nowdate.format(now)+".txt")
																  .replace("@SCount", String.valueOf(success))
																  .replace("@FCount", String.valueOf(fail)));
				logger.info("\nBatchId:"+uuid+
							"\nFilePath:"+"exrate_ivr"+nowdate.format(now)+".txt"+
							"\nSuccessCount:"+success+
							"\nFailCount:"+fail+
							"\nurl:"+url+
							"\nsuccess");
			}
			else 
				logger.info(new Exception("主機忙線中").toString()+"--url:"+url);
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
	}
	public JSONObject httpPost(String url, String output) throws Exception{
		JSONObject jsonObject = null;
		URL endpoint = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
		httpConnection.setRequestMethod("POST");
		httpConnection.setDoOutput(true);
		httpConnection.setDoInput(true);
		httpConnection.setRequestProperty("Content-Type", "application/json");
		DataOutputStream outputStream = new DataOutputStream(httpConnection.getOutputStream());
		outputStream.write(output.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
		DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while((line = bufferReader.readLine())!=null) {
			stringBuilder.append(line);
		}
		bufferReader.close();
		jsonObject = JSONObject.fromObject(stringBuilder.toString());
		return jsonObject;
	}
	public String Decimal_point(String str) {
		StringBuffer sb = new StringBuffer(str);
		sb=sb.insert(2, ".");
		return sb.toString();
	}
}
