package com.ctbcbank.ivr.repo.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.async.AsyncTask;
import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessStatus;
import com.ctbcbank.ivr.repo.gateway.model.in.MPlusIn;
import com.ctbcbank.ivr.repo.gateway.model.out.ResultOutStatus;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "連接M plus API")
@RestController // @RestController註解等價於@Controller+@ResponseBody的結合，使用這個註解的類裡面的方法都以json格式輸出。
public class MPlusController {
	@Autowired
	private Log log;
	@Autowired
	private AsyncTask task;

	@ApiOperation(value = "M plus API和IVR之間的橋樑", notes = "接收ivr的資訊，傳送給MPlus的API 再透過那個API去呼叫MPlus主機")
	@PostMapping("/mPlus")
	public ResultOutStatus MPlus(@RequestBody MPlusIn mPlusIn) {
		long mPlusInTime = System.currentTimeMillis();
		String UUID = Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
		ResultOutStatus resultOut = new ResultOutStatus();
		if(mPlusIn!=null) {
			resultOut.setStatus(ProcessStatus.SUCCESS.getStatus());
			task.mPlusExecute(mPlusIn);
		}
		else
			resultOut.setStatus(ProcessStatus.FAIL.getStatus());
		long mPlusOutTime = System.currentTimeMillis();
		log.writeTimeLog(mPlusIn.getConnID(), UUID, "IVR", mPlusInTime, mPlusOutTime);
		return resultOut;
	}
}
