package com.ctbcbank.ivr.repo.gateway.model.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入簡訊資料")
public class MqIn extends RequestModel{
	private static final long serialVersionUID = -3211128052438978222L;
	
	//IVR message組成Header
	@ApiModelProperty(value = "交易日期")
	@JsonProperty("Tx_date")
	private String tx_date;//8
	@ApiModelProperty(value = "樣板代碼", required = true)
	@JsonProperty("Type")
	private String type;//3
	@ApiModelProperty(value = "交易時間")
	@JsonProperty("Tx_time")
	private String tx_time;//6
	//IVR message組成Content
	@ApiModelProperty(value = "ID", required = true)
	@JsonProperty("Id")
	private String id;
	@ApiModelProperty(value = "手機號碼", required = true)
	@JsonProperty("Mobilno")
	private String mobilno;
	@ApiModelProperty(value = "姓名", required = true)
	@JsonProperty("Name")
	private String name;
	
	public String getTx_date() {
		return tx_date;
	}
	public void setTx_date(String tx_date) {
		this.tx_date = tx_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTx_time() {
		return tx_time;
	}
	public void setTx_time(String tx_time) {
		this.tx_time = tx_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobilno() {
		return mobilno;
	}
	public void setMobilno(String mobilno) {
		this.mobilno = mobilno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getdata() {
		return "ID:"+id+" Mobilno:"+mobilno+" Name:"+name+" TX_DATE:"+tx_date+" TX_TIME:"+tx_time;
	}
	
}
