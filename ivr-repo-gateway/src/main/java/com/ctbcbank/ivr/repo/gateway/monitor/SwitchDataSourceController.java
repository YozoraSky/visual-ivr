package com.ctbcbank.ivr.repo.gateway.monitor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/datasource")
public class SwitchDataSourceController {
	
	@RequestMapping("/switch")
	public @ResponseBody String MonitorConnection(String dataSource){
		String result = StringUtils.EMPTY;
		DynamicDataSource.setDataSource(dataSource);
		if(dataSource.equals("main"))
			result = "dataSource connect to Taipei";
		if(dataSource.equals("backup"))
			result = "dataSource connect to Taichung";
		return result;
	} 
}
