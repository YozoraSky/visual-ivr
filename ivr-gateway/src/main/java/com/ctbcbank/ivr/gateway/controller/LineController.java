package com.ctbcbank.ivr.gateway.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.gateway.line.LineGetUidOut;
import com.ctbcbank.ivr.gateway.line.LineBcGetUidIn;
import com.ctbcbank.ivr.gateway.line.LineBcSendIn;
import com.ctbcbank.ivr.gateway.line.LineCcSendIn;
import com.ctbcbank.ivr.gateway.line.LineProperties;
import com.ctbcbank.ivr.gateway.line.LineSendOut;
import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

@Api(tags = "溝通Line API")
@RestController
@RequestMapping(value = "/ivr")
public class LineController {
	@Autowired
	LineProperties lineProperties;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "連接BusinessConnectWebApi/push/send API", notes = "使用LINE UID推播")
	@PostMapping("/lineBcSend")
	public LineSendOut lineBcSend(@ApiParam(required = true, value = "line推播相關資料(json格式)") @RequestBody LineBcSendIn lineIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		LineSendOut out = new LineSendOut();
		ProcessResult processResult = out.getProcessResult();
		JSONObject jsonObject = null;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			String jsonString = lineProperties.getLineBcSendString()
											  .replace("@transactionId", lineIn.getTransactionId())
											  .replace("@uid", lineIn.getUid())
											  .replace("@templateId", lineIn.getTemplateId())
											  .replace("@channelId", lineIn.getChannelId());
			StringBuilder stringBuilder = new StringBuilder();
			if(lineIn.getFields().size()!=0) {
				for(int i=0;i<lineIn.getFields().size();i++) {
					stringBuilder.append("\"" + lineIn.getFields().get(i) + "\",");
				}
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
			}
			jsonString = jsonString.replace("@fields", stringBuilder.toString());
			long lineInTime = System.currentTimeMillis();
			jsonObject = httpPost(lineProperties.getLineBcSendUrl(),jsonString);
			long lineOutTime = System.currentTimeMillis();
			log.writeTimeLog(lineIn.getConnID(), UUID, "IVRLINE", lineInTime, lineOutTime);
			if(jsonObject!=null) {
				processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
				out.setResultCode(jsonObject.getString("resultCode"));
				out.setTransactionId(jsonObject.getString("transactionId"));
			}
			log.writeInfo(lineIn, jsonString, jsonObject, Log.IVRLINEGATEWAY);
		}catch(Exception e) {
			log.writeError(lineIn, e, Log.IVRLINEGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(lineIn.getCallUUID());
		processResult.setConnID(lineIn.getConnID());
		processResult.setGvpSessionID(lineIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(lineIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return out;
	}
	
	@ApiOperation(value = "連接BusinessConnectWebApi/push/getfolloweduid API", notes = "取得已綁定且同時為好友的客戶UID")
	@PostMapping("/lineBcGetUid")
	public LineGetUidOut lineBcGetUid(@ApiParam(required = true, value = "填寫客戶natioalId") @RequestBody LineBcGetUidIn lineIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		LineGetUidOut out = new LineGetUidOut();
		ProcessResult processResult = out.getProcessResult();
		JSONObject jsonObject = null;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			String jsonString = lineProperties.getLineBcGetUidString().replace("@nationalId", lineIn.getNationalId());
			long lineInTime = System.currentTimeMillis();
			jsonObject = httpPost(lineProperties.getLineBcGetUidUrl(),jsonString);
			long lineOutTime = System.currentTimeMillis();
			log.writeTimeLog(lineIn.getConnID(), UUID, "IVRLINE", lineInTime, lineOutTime);
			if(jsonObject!=null) {
				processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
				out.setUid(jsonObject.getString("uid"));
			}
			log.writeInfo(lineIn, jsonString, jsonObject, Log.IVRLINEGATEWAY);
		}catch(Exception e) {
			log.writeError(lineIn, e, Log.IVRLINEGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setConnID(lineIn.getConnID());
		processResult.setCallUUID(lineIn.getCallUUID());
		processResult.setGvpSessionID(lineIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(lineIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return out;	
	}
	
	@ApiOperation(value = "連接BusinessConnectWebApi/pnp/send API", notes = "使用手機號碼推播")
	@PostMapping("/lineCcSend")
	public LineSendOut lineCcSend(@ApiParam(required = true, value = "line推播相關資料(json格式)") @RequestBody LineCcSendIn lineIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		LineSendOut out = new LineSendOut();
		ProcessResult processResult = out.getProcessResult();
		JSONObject jsonObject = null;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			String jsonString = lineProperties.getLineCcString()
											  .replace("@transactionId", lineIn.getTransactionId())
											  .replace("@to", lineIn.getTo())
											  .replace("@templateId", lineIn.getTemplateId())
											  .replace("@channelId", lineIn.getChannelId());
			StringBuilder stringBuilder = new StringBuilder();
			if(lineIn.getFields().size()!=0) {
				for(int i=0;i<lineIn.getFields().size();i++) {
					stringBuilder.append("\"" + lineIn.getFields().get(i) + "\",");
				}
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
			}
			jsonString = jsonString.replace("@fields", stringBuilder.toString());
			//System.out.println(json);
			long lineInTime = System.currentTimeMillis();
			jsonObject = httpPost(lineProperties.getLineCcUrl(),jsonString);
			long lineOutTime = System.currentTimeMillis();
			log.writeTimeLog(lineIn.getConnID(), UUID, "IVRLINE", lineInTime, lineOutTime);
			if(jsonObject!=null) {
				processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
				out.setResultCode(jsonObject.getString("resultCode"));
				out.setTransactionId(jsonObject.getString("transactionId"));
			}
			log.writeInfo(lineIn, jsonString, jsonObject, Log.IVRLINEGATEWAY);
		}catch(Exception e) {
			log.writeError(lineIn, e, Log.IVRLINEGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setConnID(lineIn.getConnID());
		processResult.setCallUUID(lineIn.getCallUUID());
		processResult.setGvpSessionID(lineIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(lineIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return out;
	}
	
	public JSONObject httpPost(String url, String output) throws Exception{
		JSONObject jsonObject = null;
		URL endpoint = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
		httpConnection.setReadTimeout(11000);
		httpConnection.setRequestMethod("POST");
		httpConnection.setDoOutput(true);
		httpConnection.setDoInput(true);
		httpConnection.setRequestProperty("Content-Type", "application/json");
		DataOutputStream outputStream = new DataOutputStream(httpConnection.getOutputStream());
		outputStream.write(output.getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
		DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while((line = bufferReader.readLine())!=null) {
			stringBuilder.append(line);
		}
		bufferReader.close();
		jsonObject = JSONObject.fromObject(stringBuilder.toString());
		return jsonObject;
	}
}
