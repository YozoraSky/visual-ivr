package com.ctbcbank.visual.ivr.esb.enumeraion;

public enum ProcessStatus {
	SUCCESS("s"), WARNING("w"), FAIL("f");
	private String status;

	private ProcessStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
