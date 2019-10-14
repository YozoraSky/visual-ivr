package com.ctbcbank.ivr.gateway.fax;

import java.util.Map;

import com.ctbcbank.visual.ivr.esb.model.RequestModel;

public class FaxIn extends RequestModel {
	private static final long serialVersionUID = -3509592836478835057L;
	private Map<String, Object> data;
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
