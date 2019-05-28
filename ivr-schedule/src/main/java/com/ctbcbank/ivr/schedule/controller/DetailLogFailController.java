package com.ctbcbank.ivr.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.schedule.function.WriteDetailLog;

@RestController
@RequestMapping(value = "/schedule")
public class DetailLogFailController {
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping("/re_execute_detailLog")
	public void detailLog(String date) {
		//delete
		
		//delete
		WriteDetailLog detailLog = new  WriteDetailLog();
		detailLog.write();
	}
}
