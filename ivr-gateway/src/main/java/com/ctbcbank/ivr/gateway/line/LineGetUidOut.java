package com.ctbcbank.ivr.gateway.line;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class LineGetUidOut extends ReturnModel{
	private static final long serialVersionUID = -1812149871261430930L;
	
	@ApiModelProperty(value = "Line UID", required = true)
	private String uid;

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
