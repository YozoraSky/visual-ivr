package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;

import com.ctbcbank.visual.ivr.service.enumeration.ProcessResultEnum;

public class ProcessResult implements Serializable {
	private static final long serialVersionUID = -2799468947793712209L;
	private String status;
	private String returnCode;
	private String returnMessage;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public void setProcessResultEnum(ProcessResultEnum processResultEnum) {
		this.status = processResultEnum.getStatus();
		this.returnCode = processResultEnum.getCode();
		this.returnMessage = processResultEnum.getMessage();
	}
}
