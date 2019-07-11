package com.ctbcbank.ivr.detaiLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//@Component
@EnableScheduling
@PropertySource(value = { "classpath:batchDlog.properties" })
public class BatchDetailLog {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	DetailLog detailLog;
	
	@Scheduled(cron="${batchDlog.cron.msg}")
	public void run(){
		detailLog.insert();
		logger.info("#$$%%%%$$#");
	}
}
