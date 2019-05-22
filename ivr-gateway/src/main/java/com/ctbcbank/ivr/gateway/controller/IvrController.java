package com.ctbcbank.ivr.gateway.controller;

import java.net.InetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.EsbCommandOut;
import com.ctbcbank.visual.ivr.esb.model.EsbIn;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;
import com.ctbcbank.visual.ivr.service.EsbCommandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "溝通ESB主機")
@RestController //@RestController註解等價於@Controller+@ResponseBody的結合，使用這個註解的類裡面的方法都以json格式輸出。
@RequestMapping(value = "/ivr")
public class IvrController {
	@Autowired
	private EsbCommandService esbCommandService;
	@Autowired
	private Log log;
	
	@ApiOperation(value = "連接ESB主機", notes = "接收json格式的電文資料，轉成xml格式，再送進ESB主機。並接收ESB給的回傳值")
	@PostMapping("/command") //@PostMapping = @RequestMapping(method = RequestMethod.POST)
	public EsbCommandOut command(@ApiParam(required = true, value = "電文內容(json格式)") @RequestBody final EsbIn esbIn) throws Exception{
		long ivrInTime = System.currentTimeMillis();
		String UUID = java.util.UUID.randomUUID().toString();
		EsbCommandOut esbCommandOut = null;
		ProcessResult processResult = null;
		String hostAddress = StringUtils.EMPTY;
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
			log.writeError(esbIn, e.toString(), Log.IVRGATEWAY);
			processResult.setProcessResultEnum(ProcessResultEnum.SYSTEM_ERROR);
		}
		esbCommandOut.setServiceName(esbIn.getServiceName());
		processResult.setCallUUID(esbIn.getCallUUID());
		processResult.setConnID(esbIn.getConnID());
		processResult.setGvpSessionID(esbIn.getGvpSessionID());
		processResult.setApServerName(hostAddress);
		long ivrOutTime = System.currentTimeMillis();
		log.writeTimeLog(esbIn.getConnID(), UUID, "IVR", ivrInTime, ivrOutTime);
		return esbCommandOut;
	} 
}
