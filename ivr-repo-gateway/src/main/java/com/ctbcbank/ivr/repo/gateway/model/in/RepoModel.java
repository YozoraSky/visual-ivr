package com.ctbcbank.ivr.repo.gateway.model.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class RepoModel extends RequestModel {
	private static final long serialVersionUID = 1923207457465052382L;
	
	@ApiModelProperty(value = "sql語句", required = true)
	private String sql;
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getSql() {
		return sql;
	}
}
