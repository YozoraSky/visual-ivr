package com.ctbcbank.visual.ivr.service.enumeration;

public enum ProcessStatus {
	SUCCESS("success"), WARNING("warning"), FAIL("fail");
	private String status;

	private ProcessStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
