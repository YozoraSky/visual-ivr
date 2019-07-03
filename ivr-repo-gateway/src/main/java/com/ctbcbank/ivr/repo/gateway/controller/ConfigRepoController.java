package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.ivr.repo.gateway.model.in.RepoModel;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOut;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOutIDPriority;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

@RestController
@RequestMapping(value = "/config")
@Api(tags = "針對Config data base進行操作")
public class ConfigRepoController {
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "新增，刪除，修改", notes = "執行新增,刪除,修改的sql語句，且不會有回傳結果")
	@PostMapping("/execute")
	public ResultOut execute(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultOut = new ResultOut();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			jdbcTemplate.execute(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			processResult.setProcessResultEnum(ProcessResultEnum.EDIT_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), Log.INPUT);
		}
		catch (Exception e) {
			log.writeError(repoModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
//	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
	@ApiOperation(value = "查詢", notes = "執行查詢的sql語句，會有回傳結果。  \n補充:可執行內容只有select的預存程序(即預存程序中不可含有insert,update......等等)，須按照MSSQL的預存程序呼叫方法來呼叫。")
	@PostMapping("/query")
	public ResultOut query(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultOut = new ResultOut();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			long DBInTime = System.currentTimeMillis();
			List<Map<String, Object>> dataList = jdbcTemplate.queryForList(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{2}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{1}$");
			Matcher match;
			for(Map<String, Object> m:dataList) {
				for(Map.Entry<String, Object> entry:m.entrySet()) {
					if(entry.getValue()!=null) {
						match = pattern.matcher(entry.getValue().toString());
						if(match.matches()) {
							Date date = sdf.parse(entry.getValue().toString());
							m.put(entry.getKey(), sdf.format(date));
						}
					}
				}
				list.add(m);
			}
			if (!dataList.isEmpty())
				resultOut.setDataList(list);
			else
				resultOut.setDataList(new ArrayList<Map<String, Object>>());
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), list);
		}
		catch (Exception e) {
			log.writeError(repoModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
	@ApiOperation(value = "查詢", notes = "回傳多個結果集。  \n補充:可執行內容只有select的預存程序(即預存程序中不可含有insert,update......等等)，須按照MSSQL的預存程序呼叫方法來呼叫。")
	@PostMapping("/queryForMultipleResultSet")
	public ResultOut queryForMultipleResultSet(@ModelAttribute final RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultOut = new ResultOut();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> result = (List<Map<String, Object>>) jdbcTemplate.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cstmt = con.prepareCall(repoModel.getSql());
					return cstmt;
				}
			}, new CallableStatementCallback<Object>() {
				@Override
				public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			        boolean resultsAvailable = cs.execute();
			        while (resultsAvailable){
			        	ResultSet rs = cs.getResultSet();
			        	ResultSetMetaData rsmd = rs.getMetaData();
			        	while(rs.next()){
			        		Map<String, Object> map = new HashMap<String, Object>(); 
			        		int columnCount = rsmd.getColumnCount();
			        		for(int i=0;i<columnCount;i++){
			        			String columnName = rsmd.getColumnName(i+1);
			        			map.put(columnName, rs.getObject(i+1));
			        		}
			        		resultList.add(map);
			        	}
			        	resultsAvailable = cs.getMoreResults();
			        }
			        return resultList;
				}
			});
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			resultOut.setDataList(result);
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), result);
		}
		catch (Exception e) {
			log.writeError(repoModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
//	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
	@ApiOperation(value = "執行DB的預存程序", notes = "只能執行只有一個回傳值，且回傳值為整數的預存程序，可多個輸入參數。  \n注意:依照此格式呼叫API:call 預存程序名稱 (參數*N,?)  \n補充:?表示一個回傳值  \n範例:call sp_IsBlackCustomer ('0922813583',?)")
	@PostMapping("/procedure_1_integer_output")
	public ResultOut sp_WriteCTITaskList3(@ModelAttribute final RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOut resultOut = new ResultOut();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long DBInTime = System.currentTimeMillis();
			int i = (Integer) jdbcTemplate.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cstmt = con.prepareCall("{" + repoModel.getSql() + "}");
//					cstmt.setString(1, "0926111222");
//					cstmt.setString(2, "N101710178");
//					cstmt.setString(3, "0926111222");
//					cstmt.setString(4, "0");
//					cstmt.setString(5, "123");
					cstmt.registerOutParameter(1, Types.INTEGER);
					return cstmt;
				}
			}, new CallableStatementCallback<Object>() {
				@Override
				public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.execute();
					int result = cs.getInt(1);
					return result;
				}
			});
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			map.put("Value", String.valueOf(i));
			data.add(map);
			resultOut.setDataList(data);
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), data);
		}
		catch (Exception e) {
			log.writeError(repoModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
	
	@ApiOperation(value = "比較客戶ID優先度", notes = "執行查詢的sql語句，並把得到的資料依照客戶優先度高低去處理，找出最高優先度的第一筆顧客")
	@PostMapping("/compareCustomerIDPriority")
	public ResultOutIDPriority compareCustomerIDFromMobilephone(@ModelAttribute RepoModel repoModel) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		ResultOutIDPriority resultOut = new ResultOutIDPriority();
		ProcessResult processResult = resultOut.getProcessResult();
		String hostAddress = StringUtils.EMPTY;
		int idCount = 0;	//計算總共有幾筆不同的customerID
		String tbmas = StringUtils.EMPTY;
		int bmas = 0;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			long DBInTime = System.currentTimeMillis();
			List<Map<String, Object>> dataList = jdbcTemplate.queryForList(repoModel.getSql());
			long DBOutTime = System.currentTimeMillis();
			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{2}$|"
											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{1}$");
			Matcher match;
			if(!dataList.isEmpty()) {
				Set<String> customerIDSet = new HashSet<String>();
				String customerId = (String) dataList.get(0).get("customerId");
				customerId = customerId.substring(customerId.length() - 10, customerId.length());
				boolean comparison = true;
				for(Map<String, Object> m:dataList) {
					String temp = ((String)m.get("customerId"));
					temp = temp.substring(temp.length() - 10, temp.length());
					customerIDSet.add(temp);
					if(!temp.equals(customerId))
						comparison = false;
					if(comparison) {
						for(Map.Entry<String, Object> entry:m.entrySet()) {
							if(entry.getValue()!=null) {
								match = pattern.matcher(entry.getValue().toString());
								if(match.matches()) {
									Date date = sdf.parse(entry.getValue().toString());
									m.put(entry.getKey(), sdf.format(date));
								}
							}
						}
						list.add(m);
					}
					tbmas = ((String)m.get("BAMS")).trim();
					if(tbmas.equals("00")||tbmas.equals("01"))
						bmas++;
				}
				idCount = customerIDSet.size();
				if(comparison) {
					List<Integer> priority = new ArrayList<Integer>();
					for(Map<String,Object> m:list) {
						switch((String)m.get("Segment")) {
							case "SVP":priority.add(1);break;
							case "VIP":priority.add(2);break;
							case "VTB":priority.add(3);break;
							case "MASS":priority.add(4);break;
							default:priority.add(5);break;
						}
					}
					int	index = priority.indexOf(Collections.min(priority));
					result.add(list.get(index));
					resultOut.setM3count(String.valueOf(bmas));
					resultOut.setDataList(result);
					resultOut.setTotalList(String.valueOf(idCount));
				}
				else {
					resultOut.setM3count(String.valueOf(bmas));
					resultOut.setDataList(new ArrayList<Map<String, Object>>());
					resultOut.setTotalList(String.valueOf(idCount));
				}
			}
			else {
				resultOut.setM3count(String.valueOf(bmas));
				resultOut.setDataList(new ArrayList<Map<String, Object>>());
				resultOut.setTotalList(String.valueOf(idCount));
			}
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeInfo(repoModel, repoModel.getSql(), result, bmas, idCount);
		}
		catch (Exception e) {
			log.writeError(repoModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(repoModel.getCallUUID());
		processResult.setConnID(repoModel.getConnID());
		processResult.setGvpSessionID(repoModel.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return resultOut;
	}
}
