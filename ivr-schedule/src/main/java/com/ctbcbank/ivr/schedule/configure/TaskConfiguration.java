package com.ctbcbank.ivr.schedule.configure;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class TaskConfiguration implements SchedulingConfigurer{
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		建立一個線程池調度器
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//		初始化線程池調度器
		scheduler.initialize();
//		設定線程池大小
		scheduler.setPoolSize(20);
//		設定線程前綴名稱
		scheduler.setThreadNamePrefix("Task-");
//		設定等待時間
		scheduler.setAwaitTerminationSeconds(60);
//		當調度器被shutdown時，等待當前任務完成
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
//		設定當任務被取消時，從當前調度器移除
		scheduler.setRemoveOnCancelPolicy(true);
//		設定任務註冊器的調度器
		taskRegistrar.setTaskScheduler(scheduler);
	}
}
