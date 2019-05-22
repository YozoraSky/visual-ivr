package com.ctbcbank.ivr.repo.gateway.model.out;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class ResultOutStatus implements Serializable {
	private static final long serialVersionUID = -4171587312167616530L;
	
	@ApiModelProperty(value = "狀態", required = true)
	@JsonProperty("Status")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}