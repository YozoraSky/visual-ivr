package com.ctbcbank.ivr.schedule.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.function.WriteDetailLog;


@Component
@EnableScheduling
@PropertySource(value = { "classpath:batchDlog.properties" })
public class DetailLog {
	@Autowired
	WriteDetailLog detailLog;
	
	@Scheduled(cron="${batchDlog.cron.msg}")
	public void run(){
		long time = System.currentTimeMillis();
		detailLog.write();
		System.out.println("execute time : "+(System.currentTimeMillis()-time));
	}
}
