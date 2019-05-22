package com.ctbcbank.ivr.gateway.socket;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class SocketOut extends ReturnModel {
	private static final long serialVersionUID = -4171587312167616530L;
	@ApiModelProperty(value = "回傳代碼", required = true)
	private String rspCode;
	@ApiModelProperty(value = "回傳資料", required = true)
	private String data;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
}
