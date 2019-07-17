package com.ctbcbank.ivr.gateway.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.gateway.fax.FaxIn;
import com.ctbcbank.ivr.gateway.fax.FaxOut;
import com.ctbcbank.ivr.gateway.fax.FaxProperties;
import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@Api(tags = "溝通Fax API")
@RestController
@RequestMapping
public class FaxController {
	@Autowired
	private FaxProperties faxProperties;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "連接fax API", notes = "")
	@PostMapping("/fax")
	public FaxOut fax(@RequestBody FaxIn faxIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		FaxOut faxOut = new FaxOut();
		ProcessResult processResult = faxOut.getProcessResult();
		String result = StringUtils.EMPTY;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long faxInTime = System.currentTimeMillis();
			result = httpPost(faxProperties.getUrl(), JSONObject.fromObject(faxIn.getData()).toString());
			long faxOutTime = System.currentTimeMillis();
			log.writeTimeLog(faxIn.getConnID(), UUID, "IVRFAX", faxInTime, faxOutTime);
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			faxOut.setResult(result);
			log.writeFaxInfo(faxIn, faxIn.getData(), result, Log.IVRFAXGATEWAY);
		} catch (Exception e) {
			log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(faxIn.getCallUUID());
		processResult.setConnID(faxIn.getConnID());
		processResult.setGvpSessionID(faxIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(faxIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return faxOut;
	}
	
	public String httpPost(String url, String output) throws Exception{
		URL endpoint = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
		httpConnection.setReadTimeout(3000);
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
		return stringBuilder.toString();
	}
}
