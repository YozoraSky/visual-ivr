package com.ctbcbank.ivr.repo.gateway.model.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class SplunkIn extends RequestModel {
	private static final long serialVersionUID = 5843309587019385768L;
	
	@ApiModelProperty(value = "sql語句", required = true)
	private String splunk_a;
	@ApiModelProperty(value = "sql語句", required = true)
	private String splunk_b;
	public String getSplunk_a() {
		return splunk_a;
	}
	public void setSplunk_a(String splunk_a) {
		this.splunk_a = splunk_a;
	}
	public String getSplunk_b() {
		return splunk_b;
	}
	public void setSplunk_b(String splunk_b) {
		this.splunk_b = splunk_b;
	}
	
}

