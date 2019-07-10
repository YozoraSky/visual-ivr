package com.ctbcbank.ivr.gateway.authbackup;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

public class AuthInsertIVRDataIn extends RequestModel{
	private static final long serialVersionUID = 8149591455531863589L;
	
	private String cardNumber;
	private String amount;
	private String retlId;
	private String trackExpirationDate;
	private String transactionId;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRetlId() {
		return retlId;
	}
	public void setRetlId(String retlId) {
		this.retlId = retlId;
	}
	public String getTrackExpirationDate() {
		return trackExpirationDate;
	}
	public void setTrackExpirationDate(String trackExpirationDate) {
		this.trackExpirationDate = trackExpirationDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
