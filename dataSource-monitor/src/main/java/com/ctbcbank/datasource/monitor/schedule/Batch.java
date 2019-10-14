package com.ctbcbank.datasource.monitor.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.datasource.monitor.object.DataSourceStatus;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:dynamicDataSource.properties" })
public class Batch {
	@Autowired
	private SwitchDataSource switchDataSource;
	
	@Scheduled(initialDelayString = "${dynamicDataSource.initialDelay}", fixedRateString = "${dynamicDataSource.fixedRate}")
	public void run() {
		if(DataSourceStatus.getAutoOrNot())
			switchDataSource.auto();
	}
}
