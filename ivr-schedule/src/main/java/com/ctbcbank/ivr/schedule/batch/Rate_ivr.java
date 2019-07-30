package com.ctbcbank.ivr.schedule.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.properties.RateProperties;
import com.ctbcbank.ivr.schedule.sftp.FTPUtil;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:rate.properties" })
public class Rate_ivr {
	private Logger logger = LoggerFactory.getLogger("rate_ivr");
	@Autowired
	@Qualifier("ivrConfigNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private RateProperties rateProperties;

	@Scheduled(cron = "${rate_ivr.cron.msg}")
	public void run() {
		String password = new String(Base64.getDecoder().decode(rateProperties.getPassword()));
		FTPUtil ftp = new FTPUtil(rateProperties.getHost(), rateProperties.getUsername(), password, 21);
		String uuid = getUUIDByTimeBase().toString();
		try {
			logger.info(ftp.login());
			long time = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
			Date now = new Date(time);
			String saveFileName = "rate_ivr(" + sdf.format(now) + ").txt";
			File file = new File(rateProperties.getSavePath());
			if(!file.exists())
				file.mkdirs();
			ftp.downloadFile(rateProperties.getDirectory(), rateProperties.getDownloadFile(),
					rateProperties.getSavePath() + saveFileName);
			logger.info("ftp download success");
			ftp.logout();

			List<Map<String, String>> interestRateCode = getInterestRateCode();

			InputStream input = new FileInputStream(rateProperties.getSavePath() + saveFileName);
			InputStreamReader inputStreamReader = new InputStreamReader(input, "big5");
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String productName;
			String magicCode;
			String rate;
			String sdate;
			String edate;
			String line;
			byte[] temp;
			int[] status;
			List<MapSqlParameterSource> batchArgsForInsert = new ArrayList<MapSqlParameterSource>();
			while ((line = reader.readLine()) != null) {
				temp = line.getBytes("big5");
				// magicCode後兩碼的空白不要去掉，因為之後要和csv檔的magicCode去做對比
				magicCode = new String(Arrays.copyOfRange(temp, 1, 1 + 11), "big5");
				productName = new String(Arrays.copyOfRange(temp, 26, 26 + 40), "big5").trim();
				rate = new String(Arrays.copyOfRange(temp, 86, 86 + 7), "big5").trim();
				sdate = new String(Arrays.copyOfRange(temp, 196, 196 + 8), "big5").trim();
				edate = new String(Arrays.copyOfRange(temp, 204, 204 + 8), "big5").trim();
				if (!productName.equals("")) {
					for (int i = 0; i < interestRateCode.size(); i++) {
						if (magicCode.equals(interestRateCode.get(i).get("MagicCode").replace("-", " "))
								&& productName.equals(interestRateCode.get(i).get("ProductName"))) {
							MapSqlParameterSource parameters = new MapSqlParameterSource();
							parameters.addValue("CurrencyId", interestRateCode.get(i).get("CurrencyId"));
							parameters.addValue("BatchId", uuid);
							parameters.addValue("ProductCode", interestRateCode.get(i).get("ProductCode"));
							parameters.addValue("RateType", interestRateCode.get(i).get("RateType"));
							parameters.addValue("TermType", interestRateCode.get(i).get("TermType"));
							parameters.addValue("Term", interestRateCode.get(i).get("Term"));
							parameters.addValue("AmountType", interestRateCode.get(i).get("AmountType"));
							parameters.addValue("ProductName", productName);
							parameters.addValue("Rate", rate);
							parameters.addValue("StartDate", sdate);
							parameters.addValue("EndDate", edate);
							parameters.addValue("IVR", interestRateCode.get(i).get("IVR"));
							batchArgsForInsert.add(parameters);
						}
					}
				}
			}
			status = namedParameterJdbcTemplate.batchUpdate(rateProperties.getInterestRateSql(),
					batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
			int success = 0, fail = 0;
			for (int i : status) {
				if (i == 1)
					success++;
				else
					fail++;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("RecordId", uuid);
			params.put("FilePath", saveFileName);
			params.put("SuccessCount", String.valueOf(success));
			params.put("FailCount", String.valueOf(fail));
			namedParameterJdbcTemplate.update(rateProperties.getBatchSql(), params);
			reader.close();
			logger.info("insert data number : " + batchArgsForInsert.size());
		} catch (Exception e) {
			logger.error("---ERROR--- : ", e);
		}
		logger.info("#$$%%%%$$#");
	}

	private List<Map<String, String>> getInterestRateCode() throws Exception {
		InputStream input = new FileInputStream(rateProperties.getInterestRateCodePath());
		InputStreamReader inputStreamReader = new InputStreamReader(input, "big5");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		String[] interestRateCode;
		String[] key = null;
		List<Map<String, String>> interestRateCodeMap = new ArrayList<Map<String, String>>();
		Map<String, String> tempMap;
		int count = 0;
		int num = 0;
		while ((line = reader.readLine()) != null) {
			interestRateCode = line.split(",");
			if (count == 0)
				key = interestRateCode;
			else {
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
		return interestRateCodeMap;
	}
	
	public UUID getUUIDByTimeBase() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate();
	}
}
