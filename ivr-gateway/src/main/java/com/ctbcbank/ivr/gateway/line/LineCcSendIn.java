package com.ctbcbank.ivr.gateway.line;

import java.util.List;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class LineCcSendIn extends RequestModel {
	private static final long serialVersionUID = -4135624805116809472L;
	
	@ApiModelProperty(value = "序號", required = true)
	private String transactionId;
	@ApiModelProperty(value = "手機號碼", required = true)
	private String to;
	@ApiModelProperty(value = "樣板代號", required = true)
	private String templateId;
	@ApiModelProperty(value = "請帶\"IVR\"", required = true)
	private String channelId;
	@ApiModelProperty(value = "變動欄位", required = true)
	private List<String> fields;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
