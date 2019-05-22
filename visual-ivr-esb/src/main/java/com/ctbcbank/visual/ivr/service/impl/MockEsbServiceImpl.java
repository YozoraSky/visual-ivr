package com.ctbcbank.visual.ivr.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.EsbCommandOut;
import com.ctbcbank.visual.ivr.esb.model.EsbIn;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;
import com.ctbcbank.visual.ivr.service.EsbCommandService;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service("mockEsbService")
public class MockEsbServiceImpl implements EsbCommandService {
	private Logger logger = LoggerFactory.getLogger("mock");
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String GET_DATA = "select data from Atrip_Mock_ESB where ServiceName = '@serviceName' and SearchKey = '@searchKey'";
	private static final String FIND_SEARCH_KEY = "select SearchKey from Atrip_Mock_ESB where ServiceName = '@serviceName'";
	@Autowired
	DataSource dataSource;
	//	實作
	@Override
	public EsbCommandOut excute(EsbIn esbIn, String UUID) throws Exception {
		String sqlStmt = StringUtils.EMPTY;
		String result = StringUtils.EMPTY;
		String searchKey = StringUtils.EMPTY;
		EsbCommandOut esb = new EsbCommandOut();
//		設定回傳成功的訊息
		ProcessResult processResult = esb.getProcessResult();
		processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
//		esb.setProcessResult(processResult);
		logger.info("mockDB connect success!");
		logger.info("CallUUID: " + esbIn.getCallUUID());
		logger.info("ConnID: " + esbIn.getConnID());
		logger.info("getGvpSessionID: " + esbIn.getGvpSessionID());
		logger.info("ServiceName: " + esbIn.getServiceName());
		logger.info("date: " + esbIn.getData());
		
		
//		從esbIn撈出body的值
		Object t = esbIn.getData().get("REQBDY");
//		map的put函式會自動依照key進行排序
//		此為未排序過的map
		@SuppressWarnings("unchecked")
		Map<String,Object> requestBody = (Map<String, Object>)t;
		
//		以為已排序過的map
		Map<String,Object> sortedRequestBody = new HashMap<String, Object>();
		String k = "",v = "";
		for(Map.Entry<String, Object> entry:requestBody.entrySet()) {
			if(!(entry.getKey().equals("DateTime"))) {
				k = entry.getKey();
				if(esbIn.getServiceName().equals("ivDpstAcctForIdInq")) {
					if(k.equals("DateTime"))
						v = "";
					else
						v = (String)entry.getValue(); 
				}
				else
					v = (String)entry.getValue();
				sortedRequestBody.put(k, v);
			}
		}
		
		sqlStmt = FIND_SEARCH_KEY.replace("@serviceName",String.valueOf(esbIn.getServiceName()));
		
//		比對searchKey的值
		List<Map<String, Object>>  rows = jdbcTemplate.queryForList(sqlStmt);
		
//		把table中抓出的searchKey做格式處理，以方便比對
		Boolean chose = false;
		
		Map<String,Object> mockDB_searchKey = new HashMap<String,Object>();
		JSONObject jsonObject = null;
		Iterator<?> it;
		Object searchKeyObject;
		
		logger.info("\n");
		logger.info("comparison searchKey start------\n");
		for(Map<String,Object> row: rows) {
			searchKeyObject = row.get("SearchKey");
			try {
				jsonObject = JSONObject.fromObject(searchKeyObject);
			}
			catch(JSONException e) {
				logger.info("JSONException happened on " + String.valueOf(esbIn.getServiceName()) + 
							" from searckKey " + searchKeyObject.toString() + "\n");
			}
			if(jsonObject!=null) {
				it = jsonObject.keys();
				while(it.hasNext()) {
					String key = String.valueOf(it.next());
					if(!key.equals("DateTime")){
						String value = (String) jsonObject.get(key);
						mockDB_searchKey.put(key, value);
					}
				}
			}
			
			logger.info("from postman or composer searchKey:" + sortedRequestBody);
			logger.info("from mock_DB searchKey:            " + mockDB_searchKey + "\n");
			
			if(sortedRequestBody.equals(mockDB_searchKey)) {
				chose = true;
				logger.info("comparison success");
				searchKey = (String)row.get("SearchKey");
				break;
			}
			mockDB_searchKey.clear();
		}
		if(chose == false)
			logger.info("comparison fail");
		logger.info("comparison searchKey end------\n");
		
//		描述查詢Atrip_Mock_ESB的SQL語法
		sqlStmt = GET_DATA.replace("@serviceName", esbIn.getServiceName())
				   		  .replace("@searchKey", searchKey);
		
//		去Atrip_Mock_ESB撈取data的值
		rows = jdbcTemplate.queryForList(sqlStmt);
		for(Map<String,Object> row: rows) {
			result = (String)row.get("data");
		}
		if(result == StringUtils.EMPTY)
			result = "\"\"";
		
		logger.info("查詢到的資料: " + result);
		esb.setData(result.toString());
		esb.setServiceName(esbIn.getServiceName());
		System.out.println("DataSource = " + dataSource);
		return esb;
	}
}
