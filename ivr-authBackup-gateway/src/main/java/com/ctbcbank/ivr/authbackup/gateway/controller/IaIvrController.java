package com.ctbcbank.ivr.authbackup.gateway.controller;

import java.net.InetAddress;
import org.apache.commons.lang.StringUtils;
import org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrDataInfo;
import org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrSetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.authbackup.gateway.encrypt.Log;
import com.ctbcbank.ivr.authbackup.gateway.enumeraion.ProcessResult;
import com.ctbcbank.ivr.authbackup.gateway.enumeraion.ProcessResultEnum;
import com.ctbcbank.ivr.authbackup.gateway.model.AuthBackupOut;
import com.ctbcbank.ivr.authbackup.gateway.model.AuthInsertIVRDataIn;
import com.ctbcbank.ivr.authbackup.gateway.model.AuthSetBackupIn;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import Eai.IAcquirer.Ctcb.ServiceNameProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "備援授權的開啟和新增資料")
@RestController // @RestController註解等價於@Controller+@ResponseBody的結合，使用這個註解的類裡面的方法都以json格式輸出。
@RequestMapping(value = "/authBackup")
public class IaIvrController {
	@Autowired
	private Log log;

	@ApiOperation(value = "備援授權開關", notes = "開啟或關閉備援授權功能")
	@PostMapping("/setAuthBackupFromIvr")
	public AuthBackupOut setAuthBackupFromIvr(
			@ApiParam(required = true, value = "輸入資料") @RequestBody AuthSetBackupIn authSetBackupIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		AuthBackupOut authBackupOut = new AuthBackupOut();
		ProcessResult processResult = authBackupOut.getProcessResult();
		ServiceNameProxy client;
		IaIvrSetInfo result;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			client = new ServiceNameProxy();
			long authBackupInTime = System.currentTimeMillis();
			result = client.setAuthBackupFromIvr(authSetBackupIn.getType(), authSetBackupIn.getAmount(),
					authSetBackupIn.getModifiedDate(), authSetBackupIn.getTransactionId());
			long authBackupOutTime = System.currentTimeMillis();
			log.writeTimeLog(authSetBackupIn.getConnID(), UUID, "IVRAUTHBACKUP", authBackupInTime, authBackupOutTime);
			authBackupOut.setRspCode(result.getError().getCode());
			authBackupOut.setTransactionId(result.getTransactionId());
			if (result.getIsSuccess()) {
				processResult.setReturnCode(ProcessResultEnum.UPDATE_SUCCESS.getCode());
				processResult.setStatus(ProcessResultEnum.UPDATE_SUCCESS.getStatus());
				processResult.setReturnMessage(result.getError().getDescription());
			} else {
				processResult.setReturnCode(ProcessResultEnum.UPDATE_FAIL.getCode());
				processResult.setStatus(ProcessResultEnum.UPDATE_FAIL.getStatus());
				processResult.setReturnMessage(result.getError().getDescription());
			}
			String input = String.format("type : %s, amount : %s, modifiedDate : %s, transactionId : %s",
					authSetBackupIn.getType(), authSetBackupIn.getAmount(), authSetBackupIn.getModifiedDate(),
					authSetBackupIn.getTransactionId());
			log.writeAuthBackupInfo(authSetBackupIn, input);
		} catch (Exception e) {
			log.writeError(authSetBackupIn, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(authSetBackupIn.getCallUUID());
		processResult.setConnID(authSetBackupIn.getConnID());
		processResult.setGvpSessionID(authSetBackupIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(authSetBackupIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return authBackupOut;
	}

	@ApiOperation(value = "新增資料", notes = "必須先開啟備援授權功能，才能使用此功能")
	@PostMapping("/insertIVRData")
	public AuthBackupOut insertIVRData(
			@ApiParam(required = true, value = "輸入資料") @RequestBody AuthInsertIVRDataIn authInsertIVRDataIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		AuthBackupOut authBackupOut = new AuthBackupOut();
		ProcessResult processResult = authBackupOut.getProcessResult();
		ServiceNameProxy client;
		IaIvrDataInfo DataResult;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			client = new ServiceNameProxy();
			long authBackupInTime = System.currentTimeMillis();
			DataResult = client.insertIVRData(authInsertIVRDataIn.getCardNumber(), authInsertIVRDataIn.getAmount(),
					authInsertIVRDataIn.getRetlId(), authInsertIVRDataIn.getTrackExpirationDate(),
					authInsertIVRDataIn.getTransactionId());
			long authBackupOutTime = System.currentTimeMillis();
			log.writeTimeLog(authInsertIVRDataIn.getConnID(), UUID, "IVRAUTHBACKUP", authBackupInTime, authBackupOutTime);
			authBackupOut.setRspCode(DataResult.getError().getCode());
			authBackupOut.setTransactionId(DataResult.getTransactionId());
			authBackupOut.setAuthApprvCode(DataResult.getAuthApprvCode());
			processResult.setReturnCode(ProcessResultEnum.INSERT_SUCCESS.getCode());
			processResult.setStatus(ProcessResultEnum.INSERT_SUCCESS.getStatus());
			processResult.setReturnMessage(DataResult.getError().getDescription());
			String input = String.format("cardNumber : %s, amount : %s, retlId : %s, TrackExpirationDate : %s, transactionId : %s", 
					authInsertIVRDataIn.getCardNumber(), authInsertIVRDataIn.getAmount(), authInsertIVRDataIn.getRetlId(),authInsertIVRDataIn.getTrackExpirationDate(),
					authInsertIVRDataIn.getTransactionId());
			log.writeAuthBackupInfo(authInsertIVRDataIn, input);
		} catch (Exception e) {
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(authInsertIVRDataIn.getCallUUID());
		processResult.setConnID(authInsertIVRDataIn.getConnID());
		processResult.setGvpSessionID(authInsertIVRDataIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(authInsertIVRDataIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return authBackupOut;
	}
}
