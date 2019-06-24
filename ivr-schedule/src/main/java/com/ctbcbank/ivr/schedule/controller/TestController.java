package com.ctbcbank.ivr.schedule.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.schedule.sftp.FTPUtil;
import com.ctbcbank.ivr.schedule.sftp.SFTPUtil;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@PostMapping("/command")
	public String command(){
		FTPUtil ftp = new FTPUtil("172.24.15.19","AP_CTOB_GET","xn2qy6cj",21);
		String status;
		ftp.login();
		ftp.downloadFile("/DATA/RMS/AP/TCRM_TM1/", "rate_ivr.txt", "C:/Users/050303/Desktop/schedule/123.txt");
		status = "true";
		ftp.logout();
		return status;
	}
}
