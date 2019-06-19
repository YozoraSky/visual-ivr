package com.ctbcbank.ivr.repo.gateway.detailLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.repo.gateway.encrypt.DES;
import com.ctbcbank.ivr.repo.gateway.properties.BatchDlogProperties;
import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

@Component
public class DetailLog {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	private BatchDlogProperties batchDlogProperties;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KeyProperties keyproperties;
	
	public Boolean execute(String date) {
		String line;
		String sql = StringUtils.EMPTY;
		int totalSqlNum=0;
		Boolean status;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			List<?> list = jdbcTemplate.queryForList(batchDlogProperties.getDetailLogSelectStatusSQL()
																  .replace("@date", date)
																  .replace("@hostAddress", hostAddress));
			if(list.isEmpty())
				jdbcTemplate.execute(batchDlogProperties.getDetailLogInsertStatusSQL().replace("@date", date).replace("@hostAddress", hostAddress));
			logger.info(date.replace("-", "") + "-detailLog");
			int NumFile = checkFile(date.replace("-", ""), batchDlogProperties.getLogPath());
			//每個資料的處理
			List<String> sqlArray = new ArrayList<String>();
			long time = System.currentTimeMillis();
			for(int i = 0;i <= (NumFile-1); i++) {
				String readFileName = date.replace("-", "") + "." + i + ".txt";
				FileReader fr = new FileReader(batchDlogProperties.getLogPath() + "detailLog." + readFileName);
				BufferedReader reader = new BufferedReader(fr);
				//讀取檔案內容資料和資料庫處理
				while (reader.ready()) {
					line = reader.readLine();
					try {
						sql = DES._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),keyproperties.getKey());
					}
					catch(StringIndexOutOfBoundsException e) {
						sql = line;
						logger.info(e.toString());
						logger.info("Error sql : " + sql);
					}
					sqlArray.add(sql);
					if(sqlArray.size()>=5000) {
						jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
						totalSqlNum += sqlArray.size();
						sqlArray.clear();
					}
				}
				reader.close();
			}
			if(!sqlArray.isEmpty()) {
				jdbcTemplate.batchUpdate(sqlArray.toArray(new String[0]));
				totalSqlNum += sqlArray.size();
				sqlArray.clear();
			}
			logger.info("DetailLog insert time : " + (System.currentTimeMillis()-time));
			logger.info("Read " + NumFile + " Folder");
			logger.info(totalSqlNum + " sql columns are executed");
			jdbcTemplate.update(batchDlogProperties
								.getDetailLogUpdateStatusSQL()
								.replace("@status", "completed")
								.replace("@LineNumber", String.valueOf(totalSqlNum))
								.replace("@date", date)
								.replace("@hostAddress", hostAddress));
			status = true;
		} 
		catch (Exception e) {
			status = false;
			logger.error("---ERROR--- : ",e);
			jdbcTemplate.update(batchDlogProperties
								.getDetailLogUpdateStatusSQL()
								.replace("@status", "fail")
								.replace("@LineNumber", String.valueOf(totalSqlNum))
								.replace("@date", date)
								.replace("@hostAddress", hostAddress));
		}
		return status;
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
	
	public Boolean delete(String date) {
		int count=-1;
		Boolean status;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long time = System.currentTimeMillis();
//			刪除舊的DetailLog
			while(count!=0) {
				count = jdbcTemplate.update(batchDlogProperties.getDeleteDetailLog().replace("@date", date).replace("@hostAddress", hostAddress));
			}
			logger.info("DetailLog delete time : " + (System.currentTimeMillis()-time));
			status = true;
		}
		catch(Exception e) {
			logger.info(e.toString());
			status = false;
		}
		return status;
	}
	
	public Boolean insert() {
		//抓前一天(一天有86400000毫秒)
		long time = System.currentTimeMillis()-86400000;
		Date now = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return execute(sdf.format(now));
	}
	
	public Boolean insert(String date) {
		return execute(date);
	}
}
