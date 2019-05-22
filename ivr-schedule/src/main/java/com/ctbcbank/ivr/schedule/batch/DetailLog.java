package com.ctbcbank.ivr.schedule.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			long time = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date(time);
			//比對資料夾內有多少符合日期的txt  若是Linux系統需去除"C:"
			int NumFile = checkFile(sdf.format(now), batchDlogProperties.getLogPath());
			//每個資料的處理
			for(int i = 0;i <= (NumFile-1); i++) {
				String readFileName = sdf.format(now) + "." + i + ".txt";
				//若是Linux系統需去除"C:"
				FileReader fr = new FileReader(batchDlogProperties.getLogPath() + "detailLog." + readFileName);
				BufferedReader reader = new BufferedReader(fr);
					//讀取檔案內容資料和資料庫處理
					while (reader.ready()) {
						String line = reader.readLine();
						String sql = DES._DecryptByDES(
								line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),
								keyproperties.getKey());
						jdbcTemplate.execute(sql);
						data++;
						}
				fr.close();
			}
			logger.info("Read " + NumFile + " Folder");
			logger.info(data + " data columns are affected");
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
