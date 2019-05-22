package com.ctbcbank.ivr.schedule.batch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.properties.SftpProperties;
import com.ctbcbank.ivr.schedule.sftp.SFTPUtil;

//@Component
@EnableScheduling
@PropertySource(value = { "classpath:rate.properties" })
public class Rate_ivr {
	private Logger logger = LoggerFactory.getLogger("rate_ivr");
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SftpProperties sftpProperties;
	
	@Scheduled(cron="${rate_ivr.cron.msg}")
	public void run(){
		SFTPUtil sftp = new SFTPUtil(sftpProperties.getUsername(),sftpProperties.getPassword(),sftpProperties.getHost(),22);
		sftp.login();
		try {
			long time = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss.SSS");
			Date now = new Date(time);
			String saveFileName = sdf.format(now) + ".txt";
			sftp.download(sftpProperties.getDirectory(), sftpProperties.getDownloadFile(), sftpProperties.getSavePath() + "/" + saveFileName);
			logger.info("sftp download success");
			sftp.logout();
			
			List<Map<String, String>> interestRateCode = getInterestRateCode();
			
			InputStream input = new FileInputStream(sftpProperties.getSavePath() + "/" + saveFileName);
			InputStreamReader inputStreamReader = new InputStreamReader(input,"big5");
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String productName;
			String magicCode;
			String rate;
			String sdate;
			String edate;
			String line;
			byte[] temp;
			int insertDataNum = 0;
			while((line = reader.readLine())!=null) {
				temp = line.getBytes("big5");
				//magicCode後兩碼的空白不要去掉，因為之後要和csv檔的magicCode去做對比
				magicCode = new String(Arrays.copyOfRange(temp, 1, 1+11),"big5");
				productName = new String(Arrays.copyOfRange(temp, 26, 26+40),"big5").trim();
				rate = new String(Arrays.copyOfRange(temp, 86, 86+7),"big5").trim();
				sdate = new String(Arrays.copyOfRange(temp, 196, 196+8),"big5").trim();
				edate = new String(Arrays.copyOfRange(temp, 204, 204+8),"big5").trim();
				if(!productName.equals("")) {
					for(int i=0;i<interestRateCode.size();i++) {
						if(magicCode.equals(interestRateCode.get(i).get("MagicCode").replace("-", " ")) && 
						   productName.equals(interestRateCode.get(i).get("ProductName"))) {
							insertDataNum++;
							String processDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()));
//							String uuid = UUID.randomUUID().toString();
							String sql = sftpProperties.getSql();
							sql = sql.replace("@CurrencyId", interestRateCode.get(i).get("CurrencyId"))
									 .replace("@ProductCode", interestRateCode.get(i).get("ProductCode"))
									 .replace("@RateType", interestRateCode.get(i).get("RateType"))
									 .replace("@TermType", interestRateCode.get(i).get("TermType"))
									 .replace("@Term", interestRateCode.get(i).get("Term"))
									 .replace("@AmountType", interestRateCode.get(i).get("AmountType"))
									 .replace("@ProductName", productName)
									 .replace("@Rate", rate)
									 .replace("@StartDate", sdate)
									 .replace("@EndDate", edate)
									 .replace("@ProcessDate", processDate)
									 .replace("@IVR", interestRateCode.get(i).get("IVR"));
							jdbcTemplate.execute(sql);
							logger.info(sql);
						}
					}
				}
			}
			reader.close();
			logger.info("insert data numer : " + insertDataNum);
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
	}
	
	private List<Map<String, String>> getInterestRateCode() throws Exception{
		InputStream input = new FileInputStream(sftpProperties.getInterestRateCodePath());
		InputStreamReader inputStreamReader = new InputStreamReader(input,"big5");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		String[] interestRateCode;
		String[] key = null;
		List<Map<String, String>> interestRateCodeMap = new ArrayList<Map<String, String>>();
		Map<String, String> tempMap;
		int count = 0;
		int num = 0;
		while((line = reader.readLine())!=null) {
			interestRateCode = line.split(",");
			if(count == 0)
				key = interestRateCode;
			else{
				tempMap = new HashMap<String, String>();
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				tempMap.put(key[num], interestRateCode[num++]);
				interestRateCodeMap.add(tempMap);
				num = 0;
			}
			count++;
		}
		reader.close();
//		for(int i=0;i<interestRateCodeMap.size();i++) {
//			System.out.println(interestRateCodeMap.get(i));
//		}
		return interestRateCodeMap;
	}
}
