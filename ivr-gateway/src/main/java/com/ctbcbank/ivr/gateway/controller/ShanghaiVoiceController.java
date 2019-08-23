package com.ctbcbank.ivr.gateway.controller;

import java.net.InetAddress;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.ForIVRSoapProxy;

import com.ctbcbank.ivr.gateway.shanghai.voice.ChangePasswordIn;
import com.ctbcbank.ivr.gateway.shanghai.voice.IsValidIn;
import com.ctbcbank.ivr.gateway.shanghai.voice.ShanghaiVoiceOut;
import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

@Api(tags = "連接上海語音API")
@RestController
@RequestMapping(value = "/shangHai_voice")
public class ShanghaiVoiceController {
	@Autowired
	private Log log;

	@ApiOperation(value = "密碼是否有效", notes = "核申密碼是否有效")
	@PostMapping("/isValid")
	public ShanghaiVoiceOut isValid(@ApiParam(required = true, value = "輸入資料") @RequestBody IsValidIn isValidIn) {
		long ivrInTime = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();
		ShanghaiVoiceOut shanghaiVoiceOut = new ShanghaiVoiceOut();
		ProcessResult processResult = shanghaiVoiceOut.getProcessResult();
		ForIVRSoapProxy forIVRSoapProxy;
		XMLSerializer xmlSerializer = new XMLSerializer();
		String result = StringUtils.EMPTY;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			forIVRSoapProxy = new ForIVRSoapProxy();
			long shangHaiVoiceInTime = System.currentTimeMillis();
			result = forIVRSoapProxy.isValid(isValidIn.getTransNo(), isValidIn.getCustomerAccount(),
					isValidIn.getCountryCode(), isValidIn.getLicenseKey(), isValidIn.getCustomerPassword(),
					isValidIn.getChannelId());
			long shangHaiVoiceOutTime = System.currentTimeMillis();
			log.writeTimeLog(isValidIn.getConnID(), uuid, "IVRSHANGHAIVOICE", shangHaiVoiceInTime,
					shangHaiVoiceOutTime);
			JSON json = xmlSerializer.read(result);
			shanghaiVoiceOut.setData(json.toString());
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeShangHaiVoiceInfo(isValidIn, isValidIn.toString(), json.toString());
		} catch (Exception e) {
			log.writeError(isValidIn, e, Log.IVRSHANGCHIVOICEGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(isValidIn.getCallUUID());
		processResult.setConnID(isValidIn.getConnID());
		processResult.setGvpSessionID(isValidIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(isValidIn.getConnID(), uuid, "IVR", ivrInTime, ivrOutTime);
		return shanghaiVoiceOut;
	}

	@ApiOperation(value = "更改密碼", notes = "更改密碼")
	@PostMapping("/changePassword")
	public ShanghaiVoiceOut changePassword(@ApiParam(required = true, value = "輸入資料") @RequestBody ChangePasswordIn changePasswordIn) {
		long ivrInTime = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();
		ShanghaiVoiceOut shanghaiVoiceOut = new ShanghaiVoiceOut();
		ProcessResult processResult = shanghaiVoiceOut.getProcessResult();
		ForIVRSoapProxy forIVRSoapProxy;
		XMLSerializer xmlSerializer = new XMLSerializer();
		String result = StringUtils.EMPTY;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			forIVRSoapProxy = new ForIVRSoapProxy();
			long shangHaiVoiceInTime = System.currentTimeMillis();
			result = forIVRSoapProxy.changePassword(changePasswordIn.getTransNo(),
					changePasswordIn.getCustomerAccount(), changePasswordIn.getCountryCode(),
					changePasswordIn.getLicenseKey(), changePasswordIn.getOldPassword(),
					changePasswordIn.getNewPassword(), changePasswordIn.getChannelId());
			long shangHaiVoiceOutTime = System.currentTimeMillis();
			log.writeTimeLog(changePasswordIn.getConnID(), uuid, "IVRSHANGHAIVOICE", shangHaiVoiceInTime,
					shangHaiVoiceOutTime);
			JSON json = xmlSerializer.read(result);
			shanghaiVoiceOut.setData(json.toString());
			processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
			log.writeShangHaiVoiceInfo(changePasswordIn, changePasswordIn.toString(), json.toString());
		} catch (Exception e) {
			log.writeError(changePasswordIn, e, Log.IVRSHANGCHIVOICEGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(changePasswordIn.getCallUUID());
		processResult.setConnID(changePasswordIn.getConnID());
		processResult.setGvpSessionID(changePasswordIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(changePasswordIn.getConnID(), uuid, "IVR", ivrInTime, ivrOutTime);
		return shanghaiVoiceOut;
	}
}
