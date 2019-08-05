package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/config/ctcberp")
@Api(tags = "針對CTCBERP data base進行操作")
public class CtcberpRepoController {
	@Autowired
	@Qualifier("ivrCtcberpJdbcTemplate")
	private JdbcTemplate ctcberpJdbcTemplate;
	@Autowired
	private Log log;
	
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
			List<Map<String, Object>> result = (List<Map<String, Object>>) ctcberpJdbcTemplate.execute(new CallableStatementCreator() {
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
			log.writeError(repoModel, e, Log.IVRREPOGATEWAY);
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
