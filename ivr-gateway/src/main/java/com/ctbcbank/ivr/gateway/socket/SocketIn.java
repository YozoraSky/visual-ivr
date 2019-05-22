package com.ctbcbank.ivr.gateway.socket;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class SocketIn extends RequestModel{
	private static final long serialVersionUID = -3552581933557550255L;
	
	@ApiModelProperty(value = "iso8583報文", required = true)
	private String socketData;
	public String getSocketData() {
		return socketData;
	}
	public void setSocketData(String socketData) {
		this.socketData = socketData;
	}
}
