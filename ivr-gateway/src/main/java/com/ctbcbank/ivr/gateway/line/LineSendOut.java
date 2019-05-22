package com.ctbcbank.ivr.gateway.line;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "回傳結果")
public class LineSendOut extends ReturnModel{
	private static final long serialVersionUID = -3909557811937546196L;
	
	@ApiModelProperty(value = "回應碼", required = true)
	private String resultCode;
	@ApiModelProperty(value = "序號", required = true)
	private String transactionId;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
