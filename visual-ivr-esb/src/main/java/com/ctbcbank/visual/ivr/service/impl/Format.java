package com.ctbcbank.visual.ivr.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ctbcbank.visual.ivr.esb.model.EsbIn;

public class Format {
	@SuppressWarnings("unchecked")
	public static void check(EsbIn esbIn) {
		if(esbIn.getServiceName().equals("csCmpgnListInq")) {
			Map<String, Object> HDR = new LinkedHashMap<String,Object>();
			HDR.put("TrnNum", ((Map<String, String>)esbIn.getData().get("REQHDR")).get("TrnNum"));
			HDR.put("TrnCode", ((Map<String, String>)esbIn.getData().get("REQHDR")).get("TrnCode"));
			Map<String, Object> m = new LinkedHashMap<String,Object>();
			m.put("REQHDR", HDR);
			m.put("REQBDY", esbIn.getData().get("REQBDY"));
			esbIn.setData(m);
		}
		if(esbIn.getServiceName().equals("csCmpgnApplyAdd")) {
			Map<String, Object> HDR = new LinkedHashMap<String,Object>();
			HDR.put("TrnNum", ((Map<String, String>)esbIn.getData().get("REQHDR")).get("TrnNum"));
			HDR.put("TrnCode", ((Map<String, String>)esbIn.getData().get("REQHDR")).get("TrnCode"));
			HDR.put("CustId", ((Map<String, String>)esbIn.getData().get("REQHDR")).get("CustId"));
			Map<String, Object> m = new LinkedHashMap<String,Object>();
			m.put("REQHDR", HDR);
			m.put("REQBDY", esbIn.getData().get("REQBDY"));
			esbIn.setData(m);
		}
	}
}
