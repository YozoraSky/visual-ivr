package com.ctbcbank.ivr.schedule.batch;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.auth.AUTHOW05;
import com.ctbcbank.ivr.schedule.auth.AUTHPQ25;
import com.ctbcbank.ivr.schedule.auth.AUTHPQ26;
import com.ctbcbank.ivr.schedule.auth.AUTHPQ27;
import com.ctbcbank.ivr.schedule.auth.AUTHPQ28;
import com.ctbcbank.ivr.schedule.auth.Auth;
import com.ctbcbank.ivr.schedule.properties.AuthProperties;
import com.ctbcbank.ivr.schedule.sftp.FTPUtil;

// 目前在測試環境上沒放上當日檔案，只有06/21的檔案，所以要測試只能用06/21的檔案去測試
//@Component
@EnableScheduling
@PropertySource(value = { "classpath:auth.properties" })
public class Auth_ivr {
	private Logger logger = LoggerFactory.getLogger("auth");
	
	@Autowired
	private AuthProperties authProperties;
	@Autowired
	@Qualifier("NamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Scheduled(cron="${auth.cron.msg}")
	public void run(){
		String password = new String(Base64.getDecoder().decode(authProperties.getPassword()));
		FTPUtil ftp = new FTPUtil(authProperties.getHost(), authProperties.getUsername(), password, 21);
		try {
			Auth authow05 = new AUTHOW05(jdbcTemplate, logger, authProperties);
			Auth authpq25 = new AUTHPQ25(jdbcTemplate, logger, authProperties);
			Auth authpq26 = new AUTHPQ26(jdbcTemplate, logger, authProperties);
			Auth authpq27 = new AUTHPQ27(jdbcTemplate, logger, authProperties);
			Auth authpq28 = new AUTHPQ28(jdbcTemplate, logger, authProperties);
			authow05.execute(ftp, authProperties.getRemotePath(),authProperties.getAuthow05LocalPath(), authProperties.getDeCompressKey());
			authpq25.execute(ftp, authProperties.getRemotePath(), authProperties.getAuthpq25LocalPath(), authProperties.getDeCompressKey());
			authpq26.execute(ftp, authProperties.getRemotePath(), authProperties.getAuthpq26LocalPath(), authProperties.getDeCompressKey());
			authpq27.execute(ftp, authProperties.getRemotePath(), authProperties.getAuthpq27LocalPath(), authProperties.getDeCompressKey());
			authpq28.execute(ftp, authProperties.getRemotePath(), authProperties.getAuthpq28LocalPath(), authProperties.getDeCompressKey());
		}
		catch(Exception e) {
			logger.error("---ERROR--- : ",e);
		}
		logger.info("#$$%%%%$$#");
	}
}
