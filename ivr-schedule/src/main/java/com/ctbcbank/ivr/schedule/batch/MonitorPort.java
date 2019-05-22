package com.ctbcbank.ivr.schedule.batch;

import java.io.IOException;
import java.net.Socket;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@EnableAsync
@EnableScheduling
public class MonitorPort {
	
	@Async
	@Scheduled(fixedRate=1000)
	public void run() throws InterruptedException{
		Socket socket = null;
		Socket socketDB = null;
		String ip = "127.0.0.1";
		int port = 8080;
		try {
			socket = new Socket(ip,port);
			System.out.println("Server is running on port " + port);
			socket.close();
		}
		catch(IOException e) {
			System.out.println("No server on port " + port);
		}
		
		if(socket!=null || socketDB!=null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
