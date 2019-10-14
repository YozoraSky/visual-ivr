package com.ctbcbank.ivr.gateway.shanghai.voice;

import com.ctbcbank.visual.ivr.esb.model.ReturnModel;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class ShanghaiVoiceOut extends ReturnModel {
	private static final long serialVersionUID = 251526177544692590L;
	@JsonRawValue
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
