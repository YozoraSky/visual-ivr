package com.ctbcbank.ivr.gateway.fax;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;

public class FaxOut extends ReturnModel{
	private static final long serialVersionUID = 1285174103885812456L;
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
