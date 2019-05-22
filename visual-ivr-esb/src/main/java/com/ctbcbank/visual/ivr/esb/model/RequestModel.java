package com.ctbcbank.visual.ivr.esb.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class RequestModel implements Serializable {
	private static final long serialVersionUID = 8864771505081462219L;
	@JsonProperty("ConnID")
	@ApiModelProperty(value = "ConnID", required = false)
	private String connID;
	@JsonProperty("CallUUID")
	@ApiModelProperty(value = "CallUUID", required = false)
	private String callUUID;
	@JsonProperty("GVPSessionID")
	@ApiModelProperty(value = "GVPSessionID", required = false)
	private String gvpSessionID;
	
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
}
