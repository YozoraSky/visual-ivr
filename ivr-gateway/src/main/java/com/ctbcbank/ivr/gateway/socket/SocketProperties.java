package com.ctbcbank.ivr.gateway.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:socket.properties" })
public class SocketProperties {
	@Value("${socket.ip}")
	private String ip;
	@Value("${socket.port}")
	private int port;
	@Value("${socket.port2}")
	private int port2;
	@Value("${socket.port3}")
	private int port3;
	@Value("${socket.connectTimeout}")
	private int connectTimeout;
	@Value("${socket.soTimeout}")
	private int soTimeout;
	
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort2() {
		return port2;
	}
	public void setPort2(int port2) {
		this.port2 = port2;
	}
	public int getPort3() {
		return port3;
	}
	public void setPort3(int port3) {
		this.port3 = port3;
	}
}
