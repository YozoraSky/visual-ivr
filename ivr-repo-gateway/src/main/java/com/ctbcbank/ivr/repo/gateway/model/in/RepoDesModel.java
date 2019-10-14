package com.ctbcbank.ivr.repo.gateway.model.in;

import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

public class RepoDesModel extends RequestModel{
	private static final long serialVersionUID = 7484850738832840688L;
	@ApiModelProperty(value = "E為加密, D為解密", required = true)
	private String type;
	@ApiModelProperty(value = "加密資料", required = true)
	private Map<String, Object> data;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
