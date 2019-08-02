package com.ctbcbank.ivr.gateway.controller;


import java.net.InetAddress;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.gateway.socket.SocketChannel;
import com.ctbcbank.ivr.gateway.socket.SocketIn;
import com.ctbcbank.ivr.gateway.socket.SocketOut;
import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "溝通Tandem主機")
@RestController
@RequestMapping(value = "/ivr")
public class SocketController {
	
	@Autowired
	private SocketChannel socketChannel;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "連接Tandem主機", notes = "利用socket，傳送iso8583報文")
	@PostMapping("/socket")
	public SocketOut socket(@ApiParam(required = true, value = "已組好的iso8583報文字串") @RequestBody final SocketIn socketIn) {
		long ivrInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		SocketOut socketOut = null;
		ProcessResult processResult = null;
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			socketOut = socketChannel.sendAndReceive(socketIn, UUID);
			processResult = socketOut.getProcessResult();
		} catch (Exception e) {
			if(socketOut==null)
				socketOut = new SocketOut();
			processResult = socketOut.getProcessResult();
			log.writeError(socketIn, e, Log.IVRSOCKETGATEWAY);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setCallUUID(socketIn.getCallUUID());
		processResult.setConnID(socketIn.getConnID());
		processResult.setGvpSessionID(socketIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(socketIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return socketOut;
	} 
}