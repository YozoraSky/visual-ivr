package com.ctbcbank.ivr.schedule.batch;

import java.io.BufferedReader;
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
	private final String insertSQL = "INSERT INTO IVRScheduleStatus() VALUES()";
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
			int totalSqlNum=0;
			//抓前一天(一天有86400000毫秒)
			long time = System.currentTimeMillis()-86400000;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date(time);
			int NumFile = checkFile(sdf.format(now), batchDlogProperties.getLogPath());
			//每個資料的處理
			List<String> sqlArray = new ArrayList<String>();
			String tempFileNum = (String) jdbcTemplate.queryForMap(insertSQL).get("FileNum");
			String tempLineNum = (String) jdbcTemplate.queryForMap(insertSQL).get("LineNum");
			int fileNum = 0;
			int lineNum = 0;
			if(tempFileNum!=null) {
				fileNum = Integer.parseInt(tempFileNum);
			}
			if(tempLineNum!=null) {
				lineNum = Integer.parseInt(tempLineNum);
			}
			for(int i = fileNum;i <= (NumFile-1); i++) {
				jdbcTemplate.execute(insertSQL); //insert i into fileNum
				String readFileName = sdf.format(now) + "." + i + ".txt";
				FileReader fr = new FileReader(batchDlogProperties.getLogPath() + "detailLog." + readFileName);
				BufferedReader bufferedReader = new BufferedReader(fr);
				LineNumberReader reader = new LineNumberReader(bufferedReader);
				//讀取檔案內容資料和資料庫處理
				while (reader.ready()) {
					String line = reader.readLine();
					if(reader.getLineNumber()>lineNum) {
						String sql = DES._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),keyproperties.getKey());
						sqlArray.add(sql);
						if(sqlArray.size()>=5000) {
							jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
							jdbcTemplate.execute(sql); // insert reader.getLineNumber() into LineNum
							sqlArray.clear();
						}
						totalSqlNum++;
					}
				}
				fr.close();
			}
			jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
			sqlArray.clear();
			jdbcTemplate.execute(insertSQL); // insert reader.getLineNumber() into LineNum && insert status "s"
			logger.info("Read " + NumFile + " Folder");
			logger.info(totalSqlNum + " sql columns are executed");
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
