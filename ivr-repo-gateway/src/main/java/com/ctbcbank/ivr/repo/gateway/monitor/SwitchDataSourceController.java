package com.ctbcbank.ivr.repo.gateway.monitor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctbcbank.ivr.repo.gateway.properties.KeyProperties;

@Controller
@RequestMapping(value = "/datasource")
public class SwitchDataSourceController {
	@Autowired
	private KeyProperties keyProperties;
	
	@RequestMapping("/switch")
	public @ResponseBody String MonitorConnection(String dataSource, String key){
		String result = StringUtils.EMPTY;
		if(key.equals(keyProperties.getKey())) {
			if(dataSource.equals("main")) {
				DynamicDataSource.setDataSource(dataSource);
				result = "dataSource connect to Taipei";
			}
			if(dataSource.equals("backup")) {
				DynamicDataSource.setDataSource(dataSource);
				result = "dataSource connect to Taichung";
			}
		}
		else
			result = "key is incorrect";
		return result;
	} 
}
