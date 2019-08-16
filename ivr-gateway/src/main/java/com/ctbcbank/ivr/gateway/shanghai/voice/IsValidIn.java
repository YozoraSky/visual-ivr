package com.ctbcbank.ivr.gateway.shanghai.voice;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

public class IsValidIn extends RequestModel{
	private static final long serialVersionUID = 6014271994096609108L;
	private String transNo;
	private String customerAccount;
	private String countryCode;
	private String licenseKey;
	private String customerPassword;
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
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
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
						",CountryCode=" + getCountryCode() +
						",LicenseKey=" + getLicenseKey() +
						",CustomerPassword=" + getCustomerPassword() +
						",ChannelId=" + getChannelId();
		return result;
	}
}
