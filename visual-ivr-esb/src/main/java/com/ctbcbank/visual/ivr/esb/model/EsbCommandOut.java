package com.ctbcbank.visual.ivr.esb.model;

import com.fasterxml.jackson.annotation.JsonRawValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class EsbCommandOut extends ReturnModel {
	private static final long serialVersionUID = 4012500899156833086L;
	
	@ApiModelProperty(value = "電文名稱", required = true)
	private String serviceName;
//	@JsonRawValue 把字串序列化成json形式
	@JsonRawValue
	@ApiModelProperty(value = "電文內容", required = true)
	private Object data;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
