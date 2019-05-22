package com.ctbcbank.ivr.repo.gateway.enumeration;

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