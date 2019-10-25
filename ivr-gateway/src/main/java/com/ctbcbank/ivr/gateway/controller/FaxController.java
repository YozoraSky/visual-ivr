package com.ctbcbank.ivr.gateway.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.UUID;

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
import com.ctbcbank.visual.ivr.esb.model.RequestModel;

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
		String uuid = UUID.randomUUID().toString();
		FaxOut faxOut = new FaxOut();
		ProcessResult processResult = faxOut.getProcessResult();
		String result = StringUtils.EMPTY;
		String hostAddress = StringUtils.EMPTY;
		String connect_ip = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			long faxInTime = System.currentTimeMillis();
			if (!checkConnectExecuteTimes(2, faxProperties.getMain_ip(), faxIn)) {
				if (!checkConnectExecuteTimes(2, faxProperties.getBackup_ip(), faxIn)) {
					throw new Exception("Taipei and Taichung servers hang up");
				} else
					connect_ip = faxProperties.getBackup_ip();
			} else
				connect_ip = faxProperties.getMain_ip();
			result = httpPost(connect_ip, JSONObject.fromObject(faxIn.getData()).toString(), faxIn);
			long faxOutTime = System.currentTimeMillis();
			log.writeTimeLog(faxIn.getConnID(), uuid, "IVRFAX", faxInTime, faxOutTime);
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			faxOut.setResult(result);
			log.writeFaxInfo(faxIn, JSONObject.fromObject(faxIn.getData()).toString(), result);
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
		log.writeTimeLog(faxIn.getConnID(), uuid, "IVR", ivrInTime, ivrOutTime);
		return faxOut;
	}

//	發送Http請求給傳真Server
	public String httpPost(String url, String output, RequestModel faxIn) {
		HttpURLConnection httpConnection = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL endpoint = new URL(url);
			httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setConnectTimeout(faxProperties.getConnectTimeout());
			httpConnection.setReadTimeout(faxProperties.getSoTimeout());
			httpConnection.setRequestMethod("POST");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestProperty("Content-Type", "application/json");
			outputStream = new DataOutputStream(httpConnection.getOutputStream());
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
			inputStream = new DataInputStream(httpConnection.getInputStream());
			inputStreamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (Exception e) {
			log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
		} finally {
			if(httpConnection!=null)
				httpConnection.disconnect();
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(bufferReader!=null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(inputStreamReader!=null) {
				try {
					inputStreamReader.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
		}
		return stringBuilder.toString();
	}

//	次數依據輸入參數(times)，決定要測試幾次連線是否成功
	public boolean checkConnectExecuteTimes(int times, String url, RequestModel faxIn) {
		boolean status = false;
		for (int i = 0; i < times; i++) {
			if (checkConnect(url, faxIn)) {
				status = true;
				break;
			}
		}
		return status;
	}

//	確認連線是否成功
	public boolean checkConnect(String url, RequestModel faxIn) {
		HttpURLConnection httpConnection = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferReader = null;
		try {
			URL endpoint = new URL(url);
			httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setConnectTimeout(faxProperties.getConnectTimeout());
			httpConnection.setReadTimeout(faxProperties.getSoTimeout());
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setRequestProperty("Content-Type", "Text");
			outputStream = new DataOutputStream(httpConnection.getOutputStream());
			outputStream.write("TEST".getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
			inputStream = new DataInputStream(httpConnection.getInputStream());
			inputStreamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			String ok = stringBuilder.toString();
			if (ok.equals("ok"))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			if(httpConnection!=null)
				httpConnection.disconnect();
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(bufferReader!=null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(inputStreamReader!=null) {
				try {
					inputStreamReader.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					log.writeError(faxIn, e, Log.IVRFAXGATEWAY);
				}
			}
		}
	}
}
