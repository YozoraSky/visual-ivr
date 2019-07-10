package com.ctbcbank.ivr.authbackup.gateway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class AuthSetBackupIn extends RequestModel {
	private static final long serialVersionUID = -6911097106005149406L;
	
	@ApiModelProperty(value = "備援類別 1:正常授權，2:Outgoing備援授權，3:Base24備援授權", required = true)
	private String type;
	@ApiModelProperty(value = "備援授權額度", required = true)
	private String amount;
	@ApiModelProperty(value = "異動時間(yyyyMMddHHmmss)", required = true)
	private String ModifiedDate;
	@ApiModelProperty(value = "序號", required = true)
	private String transactionId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
