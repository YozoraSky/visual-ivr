package com.ctbcbank.ivr.schedule.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.schedule.sftp.FTPUtil;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@PostMapping("/command")
	public void command(){
		FTPUtil ftp = new FTPUtil("192.168.31.215","ineb","yprec370",21);
		try {
			ftp.login();
			ftp.downloadFile("/", "321.txt", "C:/Users/050303/Desktop/12345.txt");
			ftp.upLoadFile("/", "32123.txt", "C:/Users/050303/Desktop/12345.txt");
			ftp.logout();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
