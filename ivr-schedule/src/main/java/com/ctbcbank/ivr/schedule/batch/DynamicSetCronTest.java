package com.ctbcbank.ivr.schedule.batch;

import java.io.File;
import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

//@Component
@EnableScheduling
public class DynamicSetCronTest implements SchedulingConfigurer{
	
	private final static String DEFAULT_CRON = "0 * * * * ?";
	private String cron = DEFAULT_CRON;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
		      @Override
		      public void run() {
		    	  System.out.println(cron);
		      }
		    }, new Trigger() {
				@Override
				public Date nextExecutionTime(TriggerContext triggerContext) {
				    CronTrigger trigger = new CronTrigger(cron);
				    Date nextExecDate = trigger.nextExecutionTime(triggerContext);
				    return nextExecDate;
				}
		    	
		    });
	}
	
	public int checkFile(String data,String folderPath) throws Exception{
		 int Numdata = 0;
	        File folder = new File(folderPath);
	        String[] list = folder.list();           
	          for(int i = 0; i < list.length; i++){
	              if(data.equals(list[i].toString().substring(10,18)))
	            	  Numdata++;
	        }
	        return Numdata;
	}
	
	public void setCron(String cron) {
		this.cron = cron;
	}

}
