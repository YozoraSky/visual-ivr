package com.ctbcbank.ivr.gateway.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.EsbCommandOut;
import com.ctbcbank.visual.ivr.esb.model.EsbIn;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;
import com.ctbcbank.visual.ivr.service.EsbCommandService;


@RestController
@RequestMapping(value = "/ivr")
public class MockController {
	private Logger logger = LoggerFactory.getLogger("mock");
	
	@Autowired
	@Qualifier("mockEsbService")
	private EsbCommandService esbCommandService;
	
 	@PostMapping("/mockCommand")
	public EsbCommandOut command(@RequestBody final EsbIn esbIn) throws InterruptedException{
 		String UUID = java.util.UUID.randomUUID().toString();
 		EsbCommandOut esbCommandOut = null;
		ProcessResult processResult = null;
		String hostAddress = StringUtils.EMPTY;
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		logger.info("------------------------------" + "log start-" + strDate + "---------------------------");
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			esbCommandOut = esbCommandService.excute(esbIn, UUID);
			processResult = esbCommandOut.getProcessResult();
		}
		catch (Exception e) {
			if(esbCommandOut == null) {
				esbCommandOut = new EsbCommandOut();
				
			}
			processResult = esbCommandOut.getProcessResult();
			logger.error(e.getMessage(), e);
			processResult.setProcessResultEnum(ProcessResultEnum.SYSTEM_ERROR);
		}
		esbCommandOut.setServiceName(esbIn.getServiceName());
		processResult.setCallUUID(esbIn.getCallUUID());
		processResult.setConnID(esbIn.getConnID());
		processResult.setGvpSessionID(esbIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		logger.info("-----------------------------------------log end-----------------------------------------\n");
// 		Thread thread = Thread.currentThread();
// 		long current = System.currentTimeMillis();
// 		thread.sleep(75000);
// 		System.out.println("等待  " + (System.currentTimeMillis() - current));
		return esbCommandOut;
	} 
}
