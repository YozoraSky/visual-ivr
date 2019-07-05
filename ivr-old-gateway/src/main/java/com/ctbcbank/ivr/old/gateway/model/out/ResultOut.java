package com.ctbcbank.ivr.old.gateway.model.out;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class ResultOut extends ReturnModel {
	private static final long serialVersionUID = -4171587312167616530L;
	
	@ApiModelProperty(value = "回傳的資料內容", required = true)
	private List<Map<String, Object>> dataList;

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
}
