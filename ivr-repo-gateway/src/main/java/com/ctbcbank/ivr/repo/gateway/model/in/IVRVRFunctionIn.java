package com.ctbcbank.ivr.repo.gateway.model.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class IVRVRFunctionIn extends RequestModel {
	private static final long serialVersionUID = 1178570009656737917L;
	
	@ApiModelProperty(value = "functionId", required = true)
	private String functionId;

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
