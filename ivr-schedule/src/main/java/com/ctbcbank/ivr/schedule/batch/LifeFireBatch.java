package com.ctbcbank.ivr.schedule.batch;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.properties.LifeFireProperties;
import com.ctbcbank.ivr.schedule.sftp.FTPUtil;

//@Component
@EnableScheduling
@PropertySource(value = { "classpath:lifefire.properties" })
public class LifeFireBatch {
	private Logger logger = LoggerFactory.getLogger("life");
	private Logger AMOT_logger = LoggerFactory.getLogger("amot_life");
	private Logger ACC_logger = LoggerFactory.getLogger("acc_life");
	@Autowired
	@Qualifier("ivrLogNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private LifeFireProperties lifefireproperties;
	@Scheduled(cron="${lifefire.cron.msg}")
	public void run(){
		try {
			int Sum_Money = 0;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", (Formatdate("yyyyMMdd", false)));
			List<Map<String , Object>> DateList = namedParameterJdbcTemplate.queryForList(lifefireproperties.getSearchsql(), params);
			String[] RecordId = new String[DateList.size()];
			for(int index=0 ; index < DateList.size() ; index++) {
				RecordId[index]= (String)DateList.get(index).get("RecordId");
				String CardNo=(String) DateList.get(index).get("CardNo");
				int Amount=(int) DateList.get(index).get("Amount");
				String AuthCode=(String) DateList.get(index).get("AuthCode");
				AMOTLog(CardNo,Amount,AuthCode);
				Sum_Money += Amount;
			}
			ACCLog(DateList.size(), Sum_Money);
			String fileName="AMOT"+Formatdate("yyyyMMdd", false)+".txt";
			String password = new String(Base64.getDecoder().decode(lifefireproperties.getFtppassword()));
			FTPUtil ftp = new FTPUtil(lifefireproperties.getFtphostname(),lifefireproperties.getFtpusername(),password,21);
			ftp.login();
			ftp.upLoadFile(lifefireproperties.getFtpsavepath(), fileName, lifefireproperties.getFtplocalFile()+fileName);
			ftp.logout();
			params.clear();
			for(int index=0 ; index < RecordId.length ; index++) {
				params.put("RecordId", RecordId[index]);
				namedParameterJdbcTemplate.update(lifefireproperties.getUpdatesql(), params);
			}
			logger.info(Formatdate("yyyy/MM/dd", true)+" Finish");
		}
		catch(Exception e) {
			logger.error("---ERROR--- : ",e);
		}
	}
	private void ACCLog(int count, int sum_Money) {
		ACC_logger.info(Formatdate("yyyy/MM/dd", true));
		ACC_logger.info("筆數："+count+"    金額："+sum_Money);
	}
	private void AMOTLog(String cardNo, int amount, String authCode) {
		String date=Formatdate("yyyyMMdd", false).substring(1, Formatdate("yyyyMMdd", false).length());
		AMOT_logger.info("8220101613126"+cardNo+FormatZero(amount, 10)+authCode+"4"+date);
	}
	//amount 數字
	//number 總數
	//右靠左補0
	public String FormatZero(int amount, int number) {
		String String_amount = String.valueOf(amount);
			number = number - String_amount.length();
			for(int index=1;index<=number;index++)
				String_amount= "0"+String_amount;
			return String_amount;
	}
	// format 日期格式
	// Republic_Era 是否民國
	public String Formatdate(String format, Boolean Republic_Era) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(Republic_Era) {//民國年
			Calendar lastDate = Calendar.getInstance();
			lastDate.add(Calendar.YEAR, -1911);// 轉換民國
			return sdf.format(lastDate.getTime()).substring(1, sdf.format(lastDate.getTime()).length());
		}else {//西元年
			long Sendtime = System.currentTimeMillis();
			Date now = new Date(Sendtime);
			return sdf.format(now);
		}
	}
}
//商店代號(13)= 8220101613126, CardNo(16)sdr[0], Amount(10)sdr[1], AuthCode(6)sdr[2], "4", Txdate(6)AppointRunDate(yyyMMdd)
//8220101613126
//CardNo(16)sdr[0]
//Amount(10)sdr[1]
//AuthCode(6)sdr[2]
//4
//Txdate(6)AppointRunDate(yyyMMdd)