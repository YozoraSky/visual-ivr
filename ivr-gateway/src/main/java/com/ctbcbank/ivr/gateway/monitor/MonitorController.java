package com.ctbcbank.ivr.gateway.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class MonitorController {
	
	@RequestMapping("/monitor")
	public  @ResponseBody String MonitorConnection(){
		String ok = "ok";
		return ok;
	} 
}