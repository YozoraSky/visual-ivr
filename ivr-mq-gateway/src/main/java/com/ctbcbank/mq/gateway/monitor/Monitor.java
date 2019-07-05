package com.ctbcbank.mq.gateway.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Monitor {
	private Logger logger = LoggerFactory.getLogger("monitor");
	
	@Scheduled(cron="0 0/30 * * * ?")
	public void run(){
		logger.info("正常運行中......");
	}
}
