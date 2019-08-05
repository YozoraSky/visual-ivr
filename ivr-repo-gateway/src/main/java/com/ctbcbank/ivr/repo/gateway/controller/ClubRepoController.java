package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/config/club")
@Api(tags = "針對Club data base進行操作")
public class ClubRepoController {
	@Autowired
	@Qualifier("ivrClubJdbcTemplate")
	private JdbcTemplate clubJdbcTemplate;
	@Autowired
	private Log log;
	
//	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
	@ApiOperation(value = "執行DB的預存程序", notes = "只能執行只有一個回傳值，且回傳值為字串的預存程序，可多個輸入參數。  \n注意:依照此格式呼叫API:call 預存程序名稱 (參數*N,?)  \n補充:?表示一個回傳值  \n範例:call sp_IsBlackCustomer ('0922813583',?)")
	@PostMapping("/procedure_1_string_output")
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
			String i = (String) clubJdbcTemplate.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cstmt = con.prepareCall("{" + repoModel.getSql() + "}");
					cstmt.registerOutParameter(1, Types.CHAR);
					return cstmt;
				}
			}, new CallableStatementCallback<Object>() {
				@Override
				public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.execute();
					String result = cs.getString(1);
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
