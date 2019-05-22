package com.ctbcbank.ivr.gateway.line;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class LineBcGetUidIn extends RequestModel {
	private static final long serialVersionUID = -4135624805116809472L;
	
	@ApiModelProperty(value = "身分證字號", required = true)
	private String nationalId;
	
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
}
