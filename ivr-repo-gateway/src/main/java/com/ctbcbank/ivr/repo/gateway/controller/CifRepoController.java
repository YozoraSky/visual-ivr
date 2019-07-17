//package com.ctbcbank.ivr.repo.gateway.controller;
//
//import java.net.InetAddress;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
//import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
//import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
//import com.ctbcbank.ivr.repo.gateway.model.in.RepoModel;
//import com.ctbcbank.ivr.repo.gateway.model.out.ResultOut;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//@RequestMapping(value = "/config/cif")
//@Api(tags = "針對法金 data base進行操作")
//public class CifRepoController {
//	@Autowired
//	@Qualifier("ivrCifJdbcTemplate")
//	private JdbcTemplate cifJdbcTemplate;
//	@Autowired
//	private Log log;
//	
////	若要在@ApiOperation中的notes中換行，需要打 空格+空格+\n
//	@ApiOperation(value = "查詢", notes = "執行查詢的sql語句，會有回傳結果。  \n補充:可執行內容只有select的預存程序(即預存程序中不可含有insert,update......等等)，須按照MSSQL的預存程序呼叫方法來呼叫。")
//	@PostMapping("/query")
//	public ResultOut query(@ModelAttribute RepoModel repoModel) {
//		long ivrInTime = System.currentTimeMillis();
//		String UUID = java.util.UUID.randomUUID().toString();
//		ResultOut resultOut = new ResultOut();
//		ProcessResult processResult = resultOut.getProcessResult();
//		String hostAddress = StringUtils.EMPTY;
//		try {
//			InetAddress iAddress = InetAddress.getLocalHost();
//			hostAddress = iAddress.getHostAddress();
//			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//			long DBInTime = System.currentTimeMillis();
//			List<Map<String, Object>> dataList = cifJdbcTemplate.queryForList(repoModel.getSql());
//			long DBOutTime = System.currentTimeMillis();
//			log.writeTimeLog(repoModel.getConnID(), UUID, "IVRDB", DBInTime, DBOutTime);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}$|"
//											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{2}$|"
//											+ "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{1}$");
//			Matcher match;
//			for(Map<String, Object> m:dataList) {
//				for(Map.Entry<String, Object> entry:m.entrySet()) {
//					if(entry.getValue()!=null) {
//						match = pattern.matcher(entry.getValue().toString());
//						if(match.matches()) {
//							Date date = sdf.parse(entry.getValue().toString());
//							m.put(entry.getKey(), sdf.format(date));
//						}
//					}
//				}
//				list.add(m);
//			}
//			if (!dataList.isEmpty())
//				resultOut.setDataList(list);
//			else
//				resultOut.setDataList(new ArrayList<Map<String, Object>>());
//			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
//			log.writeInfo(repoModel, repoModel.getSql(), list);
//		}
//		catch (Exception e) {
//			log.writeError(repoModel, e);
//			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
//			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
//			processResult.setReturnMessage(e.getMessage());
//		}
//		processResult.setCallUUID(repoModel.getCallUUID());
//		processResult.setConnID(repoModel.getConnID());
//		processResult.setGvpSessionID(repoModel.getGvpSessionID());
//		processResult.setApServerName(hostAddress);
//		long ivrOutTime = System.currentTimeMillis();
//		log.writeTimeLog(repoModel.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
//		return resultOut;
//	}
//}
