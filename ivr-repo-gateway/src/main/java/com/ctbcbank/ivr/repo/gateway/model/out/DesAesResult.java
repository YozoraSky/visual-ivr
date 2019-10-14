package com.ctbcbank.ivr.repo.gateway.model.out;

import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

public class DesAesResult extends ReturnModel{
	private static final long serialVersionUID = 4990701120737069116L;
	
	@ApiModelProperty(value = "加解密結果", required = true)
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
