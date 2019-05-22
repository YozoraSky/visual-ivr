package com.ctbcbank.ivr.repo.gateway.model.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class IVRVRFunctionOut extends ReturnModel {
	private static final long serialVersionUID = -2593359812088161631L;
	
	@ApiModelProperty(value = "回傳符合搜尋條件的數目", required = true)
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
