package com.ctbcbank.ivr.gateway.shanghai.voice;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

public class ChangePasswordIn extends RequestModel{
	private static final long serialVersionUID = -6147976169758703910L;
	private String transNo;
	private String customerAccount;
	private String countryCode;
	private String oldPassword;
	private String newPassword;
	private String licenseKey;
	private String channelId;
	
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	@Override
	public String toString() {
		String result = "TransNo=" + getTransNo() + 
						",CustomerAccount=" + getCustomerAccount() +
						",OldPassword=" + getOldPassword() +
						",NewPassword=" + getNewPassword() +
						",LicenseKey=" + getLicenseKey() +
						",ChannelId=" + getChannelId();
		return result;
	}
}
