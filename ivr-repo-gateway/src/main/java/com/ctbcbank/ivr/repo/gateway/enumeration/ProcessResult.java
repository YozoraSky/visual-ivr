package com.ctbcbank.ivr.repo.gateway.enumeration;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ProcessResult implements Serializable {
	private static final long serialVersionUID = -2799468947793712209L;
	@ApiModelProperty(value = "狀態", required = true)
	private String status;
	@ApiModelProperty(value = "回應碼", required = true)
	private String returnCode;
	@ApiModelProperty(value = "回應訊息", required = true)
	private String returnMessage;
	@ApiModelProperty(value = "ConnID", required = true)
	@JsonProperty("ConnID")
	private String connID;
	@ApiModelProperty(value = "CallUUID", required = true)
	@JsonProperty("CallUUID")
	private String callUUID;
	@ApiModelProperty(value = "GVPSessionID", required = true)
	@JsonProperty("GVPSessionID")
	private String gvpSessionID;
	@ApiModelProperty(value = "伺服器位址", required = true)
	@JsonProperty("APServerName")
	private String apServerName;
	@ApiModelProperty(value = "DB回傳的特殊ID", required = true)
	@JsonProperty("TrackingID")
	private String trackingID;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getConnID() {
		return connID;
	}

	public void setConnID(String connID) {
		this.connID = connID;
	}

	public String getCallUUID() {
		return callUUID;
	}

	public void setCallUUID(String callUUID) {
		this.callUUID = callUUID;
	}

	public String getGvpSessionID() {
		return gvpSessionID;
	}

	public void setGvpSessionID(String gvpSessionID) {
		this.gvpSessionID = gvpSessionID;
	}

	public String getApServerName() {
		return apServerName;
	}

	public void setApServerName(String apServerName) {
		this.apServerName = apServerName;
	}

	public String getTrackingID() {
		return trackingID;
	}

	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}

	public void setProcessResultEnum(ProcessResultEnum processResultEnum) {
		this.status = processResultEnum.getStatus();
		this.returnCode = processResultEnum.getCode();
		this.returnMessage = processResultEnum.getMessage();
	}
}
