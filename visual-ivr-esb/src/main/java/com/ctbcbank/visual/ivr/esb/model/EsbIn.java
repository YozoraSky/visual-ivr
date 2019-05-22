package com.ctbcbank.visual.ivr.esb.model;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class EsbIn extends RequestModel {
	private static final long serialVersionUID = 5382885476140715778L;
	
	@ApiModelProperty(value = "電文名稱", required = true)
	private String serviceName;
	@ApiModelProperty(value = "電文資料", required = true)
	private Map<String, Object> data;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
