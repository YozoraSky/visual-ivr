package com.ctbcbank.ivr.repo.gateway.controller;

import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.encrypt.DES;
import com.ctbcbank.ivr.repo.gateway.encrypt.Log;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResult;
import com.ctbcbank.ivr.repo.gateway.enumeration.ProcessResultEnum;
import com.ctbcbank.ivr.repo.gateway.model.in.RepoDesModel;
import com.ctbcbank.ivr.repo.gateway.model.out.DesResult;
import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Des加解密")
@RestController // @RestController註解等價於@Controller+@ResponseBody的結合，使用這個註解的類裡面的方法都以json格式輸出。
@RequestMapping
public class DesController {
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private Log log;

	@ApiOperation(value = "利用Des來加解密資料", notes = "type=\"E\"為加密, Type=\"D\"為解密")
	@PostMapping("/des")
	public DesResult des(@ApiParam(required = true, value = "加密資料") @RequestBody final RepoDesModel repoDesModel) {
		DesResult desResult = new DesResult();
		ProcessResult processResult = desResult.getProcessResult();
		Map<String, Object> map = repoDesModel.getData();
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		String hostAddress = StringUtils.EMPTY;
		try {
			InetAddress iAddress = InetAddress.getLocalHost();
			hostAddress = iAddress.getHostAddress();
			if (repoDesModel.getType().equalsIgnoreCase("E")) {
				for (Entry<String, Object> entry : map.entrySet()) {
					String value = DES._EncryptByDES(String.valueOf(entry.getValue()), keyProperties.getKey());
					result.put(entry.getKey(), value);
				}
			}
			if(repoDesModel.getType().equalsIgnoreCase("D")) {
				for (Entry<String, Object> entry : map.entrySet()) {
					String value = DES._DecryptByDES(String.valueOf(entry.getValue()), keyProperties.getKey());
					result.put(entry.getKey(), value);
				}
			}
			processResult.setProcessResultEnum(ProcessResultEnum.DES_SUCCESS);
			desResult.setData(result);
		} catch (Exception e) {
			log.writeError(repoDesModel, e);
			processResult.setReturnCode(ProcessResultEnum.SYSTEM_ERROR.getCode());
			processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
			processResult.setReturnMessage(e.getMessage());
		}
		processResult.setApServerName(hostAddress);
		processResult.setConnID(repoDesModel.getConnID());
		processResult.setCallUUID(repoDesModel.getCallUUID());
		processResult.setGvpSessionID(repoDesModel.getGvpSessionID());
		return desResult;
	}

}
