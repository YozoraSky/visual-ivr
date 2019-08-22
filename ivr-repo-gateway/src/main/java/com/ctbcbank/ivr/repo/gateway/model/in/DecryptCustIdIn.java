package com.ctbcbank.ivr.repo.gateway.model.in;

import io.swagger.annotations.ApiModelProperty;

public class DecryptCustIdIn extends RequestModel{
	private static final long serialVersionUID = -5235097133023801L;
	@ApiModelProperty(value = "客戶ID", required = true)
	private String custId;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
}
