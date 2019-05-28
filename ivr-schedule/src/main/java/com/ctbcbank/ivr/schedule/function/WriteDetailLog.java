package com.ctbcbank.ivr.schedule.function;

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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.encrypt.DES;
import com.ctbcbank.ivr.schedule.properties.BatchDlogProperties;
import com.ctbcbank.ivr.schedule.properties.KeyProperties;

@Component
public class WriteDetailLog {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	private BatchDlogProperties batchDlogProperties;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KeyProperties keyproperties;
	
	public void write() {
		try {
			int totalSqlNum=0;
			String line;
			String sql;
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
					line = reader.readLine();
					sql = DES._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),keyproperties.getKey());
					sqlArray.add(sql);
					if(sqlArray.size()>=5) {
						try {
							jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
						}
						catch(DuplicateKeyException e) {
							//Don't do anything!
						}
						sqlArray.clear();
					}
					totalSqlNum++;
				}
				fr.close();
			}
			if(!sqlArray.isEmpty()) {
				try {
					jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
				}
				catch(DuplicateKeyException e) {
					//Don't do anything!
				}
				sqlArray.clear();
			}
			logger.info("Read " + NumFile + " Folder");
			logger.info(totalSqlNum + " sql columns are executed");
			jdbcTemplate.update(String.format("INSERT INTO A_schedule_status(Name,Date,Status,SuccessLineNumber) VALUES('DeatilLog',GATEDATE(),'s','%s')",totalSqlNum));
//			jdbcTemplate.execute(); // insert status "s", totalSqlNum, Date into DB
		} 
		catch (Exception e) {
//			logger.info(e.toString());
			e.printStackTrace();
			jdbcTemplate.update("INSERT INTO A_schedule_status(Name,Date,Status,SuccessLineNumber) VALUES('DeatilLog',GATEDATE(),'f','未完成')");
//			jdbcTemplate.execute(sql); // insert status "f", Date into DB
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
