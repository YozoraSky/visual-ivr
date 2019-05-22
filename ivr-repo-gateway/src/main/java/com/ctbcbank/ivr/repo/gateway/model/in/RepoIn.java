package com.ctbcbank.ivr.repo.gateway.model.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class RepoIn extends RequestModel {
	private static final long serialVersionUID = -2712070809504892143L;
	
	@ApiModelProperty(value = "此insert語句需要的各種參數數值", required = true)
	private Object[] datas;

	public Object[] getDatas() {
		return datas;
	}

	public void setDatas(Object[] datas) {
		this.datas = datas;
	}
}
