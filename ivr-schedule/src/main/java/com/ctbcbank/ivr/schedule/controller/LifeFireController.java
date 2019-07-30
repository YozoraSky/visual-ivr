package com.ctbcbank.ivr.schedule.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.schedule.encrypt.DES;
import com.ctbcbank.ivr.schedule.module.TimeIn;
import com.ctbcbank.ivr.schedule.properties.KeyProperties;
import com.ctbcbank.ivr.schedule.properties.LifeFireProperties;
import com.ctbcbank.ivr.schedule.sftp.FTPUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "執行LifeFire的批次")
@RestController
@RequestMapping(value = "/batch")
public class LifeFireController {
	private String ok = "ok";
	@Autowired
	@Qualifier("ivrLogNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private LifeFireProperties lifefireproperties;
	@Autowired
	private KeyProperties keyProperties;
	@ApiOperation(value = "連接fax API", notes = "")
	@PostMapping("/lifefire")
	public String lifefire(@RequestBody TimeIn timein) {
		try {
			int Sum_Money = 0;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("date", timein.getTime());
			List<Map<String , Object>> DateList = namedParameterJdbcTemplate.queryForList(lifefireproperties.getSearchsql(), params);
			if(DateList.isEmpty())
				throw new Exception("No date excute");
			BufferedWriter bw = new BufferedWriter(new FileWriter(lifefireproperties.getFtplocalFile()+"AMOT"+ timein.getTime() +".txt"));
			String[] RecordId = new String[DateList.size()];
			for(int index=0 ; index < DateList.size() ; index++) {
				RecordId[index]= (String)DateList.get(index).get("RecordId");
				String CardNo=DES._DecryptByDES((String) DateList.get(index).get("CardNo"),keyProperties.getKey());
				int Amount=(int) DateList.get(index).get("Amount");
				String AuthCode=(String) DateList.get(index).get("AuthCode");
				AMOTLog(timein.getTime(),CardNo,Amount,AuthCode,bw);
				Sum_Money += Amount;
			}
			bw.flush();
			bw.close();
			ACCLog(timein.getTime(),DateList.size(), Sum_Money);
			String fileName="AMOT"+timein.getTime()+".txt";
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
			return ok;
		}catch(Exception e) {
			return e.toString();
		}		
	}
	public void ACCLog(String date,int count, int sum_Money) throws IOException {
		//處理民國
		int year = Integer.parseInt(date.substring(0, 4));
		String y = String.valueOf(year -1911);
		File orginFile = new File(lifefireproperties.getOriginalpath()+"ACC"+date+".txt");
		if(orginFile.exists())
			orginFile.delete();
		orginFile.createNewFile();
		BufferedWriter obw = new BufferedWriter(new FileWriter(orginFile));
		obw.write(y+"/"+date.substring(4, 6)+"/"+date.substring(6,8)+"\n"+"筆數："+count+"    金額："+sum_Money);
		obw.flush();
		obw.close();
	}
	public void AMOTLog(String date,String cardNo, int amount, String authCode, BufferedWriter bw) throws IOException {
		String fdate=Formatdate(date, false).substring(1, Formatdate("yyyyMMdd", false).length());
		bw.write("8220101613126"+cardNo+FormatZero(amount, 10)+authCode+"4"+fdate + "\n");
	}
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
