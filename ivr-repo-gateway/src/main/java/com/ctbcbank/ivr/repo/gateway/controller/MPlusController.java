package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.IvrCallLog;
import org.tempuri.MPlusServiceSoapProxy;

import com.ctbcbank.ivr.repo.gateway.encrypt.Log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "連接M plus API")
@RestController // @RestController註解等價於@Controller+@ResponseBody的結合，使用這個註解的類裡面的方法都以json格式輸出。
@RequestMapping(value = "/mplus")
public class MPlusController {
	@Autowired
	private Log log;

	@ApiOperation(value = "", notes = "")
	@PostMapping("/test")
	public void test() {
		String result = StringUtils.EMPTY;
		String hostAddress = StringUtils.EMPTY;
		MPlusServiceSoapProxy mPlusServiceSoapProxy;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			mPlusServiceSoapProxy = new MPlusServiceSoapProxy();
			IvrCallLog oData = new IvrCallLog();
			long mPlusInTime = System.currentTimeMillis();
//			mPlusServiceSoapProxy.sendMessage(mPlusGroupId, noticeTitle, noticeContent, oData, nowTime);
			long mPlusOutTime = System.currentTimeMillis();
		}catch(Exception e) {
			
		}
	}
}
