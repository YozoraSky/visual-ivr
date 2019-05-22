package com.ctbcbank.visual.ivr.esb.model;

import java.io.Serializable;

import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
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
//	@JsonProperty >>重新命名
//	除了重新命名，加上@JsonProperty註解之後 return回去的變數還會依照這邊變數的順序來進行排序
	@JsonProperty("ConnID")
	@ApiModelProperty(value = "ConnID", required = true)
	private String connID;
	@JsonProperty("CallUUID")
	@ApiModelProperty(value = "CallUUID", required = true)
	private String callUUID;
	@JsonProperty("GVPSessionID")
	@ApiModelProperty(value = "GVPSessionID", required = true)
	private String gvpSessionID;
	@JsonProperty("APServerName")
	@ApiModelProperty(value = "伺服器位址", required = true)
	private String apServerName;
	@JsonProperty("TrackingID")
	@ApiModelProperty(value = "ESB回傳的特殊ID", required = true)
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
