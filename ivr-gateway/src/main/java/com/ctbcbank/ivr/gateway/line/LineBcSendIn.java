package com.ctbcbank.ivr.gateway.line;

import java.util.List;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "輸入資料")
public class LineBcSendIn extends RequestModel {
	private static final long serialVersionUID = -4135624805116809472L;
	
	@ApiModelProperty(value = "序號", required = true)
	private String transactionId;
	@ApiModelProperty(value = "Line UID", required = true)
	private String uid;
	@ApiModelProperty(value = "樣板代號", required = true)
	private String templateId;
	@ApiModelProperty(value = "通路代號", required = true)
	private String channelId;
	@ApiModelProperty(value = "動態欄位，可傳入多個", required = true)
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
}
