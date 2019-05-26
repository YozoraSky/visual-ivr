package com.ctbcbank.ivr.schedule.batch;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.encrypt.DES;
import com.ctbcbank.ivr.schedule.properties.BatchDlogProperties;
import com.ctbcbank.ivr.schedule.properties.KeyProperties;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:batchDlog.properties" })
public class DetailLog {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	private BatchDlogProperties batchDlogProperties;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KeyProperties keyproperties;
	
	@Scheduled(cron="${batchDlog.cron.msg}")
	public void run(){
		try {
			int data=0;
			//抓前一天(一天有86400000毫秒)
			long time = System.currentTimeMillis()-86400000;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date(time);
			int NumFile = checkFile(sdf.format(now), batchDlogProperties.getLogPath());
			//每個資料的處理
			List<String> sqlArray = new ArrayList<String>();
			for(int i = 0;i <= (NumFile-1); i++) {
				String readFileName = sdf.format(now) + "." + i + ".txt";
				//若是Linux系統需去除"C:"
				FileReader fr = new FileReader(batchDlogProperties.getLogPath() + "detailLog." + readFileName);
				LineNumberReader reader = new LineNumberReader(fr);
				//讀取檔案內容資料和資料庫處理
				while (reader.ready()) {
					String line = reader.readLine();
					String sql = DES._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),keyproperties.getKey());
					sqlArray.add(sql);
					if(sqlArray.size()>=5000) {
						jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
						sqlArray.clear();
					}
					data++;
				}
				fr.close();
			}
			if(!sqlArray.isEmpty()) {
				jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
				sqlArray.clear();
			}
			logger.info("Read " + NumFile + " Folder");
			logger.info(data + " sql columns are executed");
		} 
		catch (Exception e) {
			logger.info(e.toString());
		}       
	}
	public int checkFile(String data,String folderPath) throws Exception{
		 int Numdata = 0;
	        File folder = new File(folderPath);
	        String[] list = folder.list();           
	          for(int i = 0; i < list.length; i++){
	              if(data.equals(list[i].toString().substring(10,18)))
	            	  Numdata++;
	        }
	        return Numdata;
	}
}
