package com.ctbcbank.ivr.gateway.authBackup;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;

public class AuthBackupOut extends ReturnModel{
	private static final long serialVersionUID = 2232675842599542465L;
	private String rspCode;
	private String transactionId;
	private String authApprvCode;
	public String getAuthApprvCode() {
		return authApprvCode;
	}
	public void setAuthApprvCode(String authApprvCode) {
		this.authApprvCode = authApprvCode;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
