package com.ctbcbank.ivr.authbackup.gateway.model;

public class AuthBackupOut extends ReturnModel{
	private static final long serialVersionUID = 2232675842599542465L;
	private String code;
	private String transactionId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
