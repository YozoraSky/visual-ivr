package com.ctbcbank.ivr.repo.gateway.model.out;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class ResultOutIDPriority extends ReturnModel {
	private static final long serialVersionUID = -4171587312167616530L;
	
	@ApiModelProperty(value = "回傳的資料內容", required = true)
	private List<Map<String, Object>> dataList;
	@ApiModelProperty(value = "不同的customerID數量", required = true)
	private String totalList;
	@ApiModelProperty(value = "銀行客戶等級為01(BAMS)的數量", required = true)
	private String m3count;

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public String getTotalList() {
		return totalList;
	}

	public void setTotalList(String totalList) {
		this.totalList = totalList;
	}

	public String getM3count() {
		return m3count;
	}

	public void setM3count(String m3count) {
		this.m3count = m3count;
	}
}

