package com.ctbcbank.ivr.repo.gateway.enumeration;

public enum ProcessResultEnum {
	SYSTEM_ERROR(ProcessStatus.FAIL, "APP-001-000", "系統忙碌中!"),
	SEND_SUCCESS(ProcessStatus.SUCCESS, "APP-001-001", "送出簡訊成功"),
	ADD_SUCCESS(ProcessStatus.SUCCESS, "APP-001-002", "查無資料");
	private ProcessStatus status;
	private String code;
	private String message;

	private ProcessResultEnum(ProcessStatus status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public String getStatus() {
		return status.getStatus();
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
