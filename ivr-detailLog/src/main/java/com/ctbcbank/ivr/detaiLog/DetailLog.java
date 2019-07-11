package com.ctbcbank.ivr.detaiLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.detailLog.encrypt.DES;
import com.ctbcbank.ivr.detailLog.properties.BatchDlogProperties;
import com.ctbcbank.ivr.detailLog.properties.KeyProperties;

@Component
public class DetailLog {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	private BatchDlogProperties batchDlogProperties;
	@Autowired
	@Qualifier("ivrLogNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KeyProperties keyproperties;
	
	public Boolean execute(String logDate) {
		String line;
		String sql = StringUtils.EMPTY;
		int totalSqlNum=0;
		Boolean status;
		String hostAddress = StringUtils.EMPTY;
		Map<String, Object> map;
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date(System.currentTimeMillis()));
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			params.put("Date", logDate);
			params.put("HostAddress", hostAddress);
			List<?> list = namedParameterJdbcTemplate.queryForList(batchDlogProperties.getDetailLogSelectStatusSQL(), params);
			if(list.isEmpty())
				namedParameterJdbcTemplate.update(batchDlogProperties.getDetailLogInsertStatusSQL(), params);
			logger.info(logDate.replace("-", "") + "-detailLog");
			int NumFile = checkFile(logDate.replace("-", ""), batchDlogProperties.getLogPath());
			//每個資料的處理
			List<String> sqlArray = new ArrayList<String>();
			long time = System.currentTimeMillis();
			for(int i = 0;i <= (NumFile-1); i++) {
				String readFileName = logDate.replace("-", "") + "." + i + ".txt";
				FileReader fr = new FileReader(batchDlogProperties.getLogPath() + "detailLog." + readFileName);
				BufferedReader reader = new BufferedReader(fr);
				//讀取檔案內容資料和資料庫處理
				while ((line = reader.readLine()) != null) {
					try {
						sql = DES._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),keyproperties.getKey());
						sql = sql.replace("[ProcessDate]", "[ProcessDate],[HostAddress]")
								 .replace("getdate()", "getdate(),'" + hostAddress + "'");
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
			params.clear();
			params.put("ProcessDate", today + "%");
			params.put("HostAddress", hostAddress);
			map = namedParameterJdbcTemplate.queryForMap(batchDlogProperties.getDetailLogSelectCountSQL(), params);
			String count = String.valueOf(map.get("count"));
			params.clear();
			params.put("Status", "completed");
			params.put("LineNumber", String.valueOf(totalSqlNum));
			params.put("SuccessCount", count);
			params.put("Date", logDate);
			params.put("HostAddress", hostAddress);
			namedParameterJdbcTemplate.update(batchDlogProperties.getDetailLogUpdateStatusSQL(), params);
			logger.info("Read " + NumFile + " Folder");
			logger.info("Read " + totalSqlNum + " sql columns");
			logger.info(count + " sql columns was success to be insert");
			status = true;
		} 
		catch (Exception e) {
			status = false;
			logger.error("---ERROR--- : ",e);
			params.clear();
			params.put("ProcessDate", today + "%");
			params.put("HostAddress", hostAddress);
			map = namedParameterJdbcTemplate.queryForMap(batchDlogProperties.getDetailLogSelectCountSQL(), params);
			params.clear();
			params.put("Status", "completed");
			params.put("LineNumber", String.valueOf(totalSqlNum));
			params.put("SuccessCount", String.valueOf(map.get("count")));
			params.put("Date", logDate);
			params.put("HostAddress", hostAddress);
			namedParameterJdbcTemplate.update(batchDlogProperties.getDetailLogUpdateStatusSQL(), params);
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			取得後一天，因為若欲刪除的detailLog為6/19，則ProcessDate為6/20
			long tomorrow = sdf.parse(date).getTime() + 86400000;
			date = sdf.format(new Date(tomorrow));
			long time = System.currentTimeMillis();
			Map<String, Object> params = new HashMap<String, Object>();
			params.clear();
			params.put("ProcessDate", date + "%");
			params.put("HostAddress", hostAddress);
//			刪除舊的DetailLog
			while(count!=0) {
				count = namedParameterJdbcTemplate.update(batchDlogProperties.getDeleteDetailLog(), params);
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
