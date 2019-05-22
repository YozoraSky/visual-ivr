package com.ctbcbank.visual.ivr.esb.enumeraion;

public enum ProcessResultEnum {
	SYSTEM_ERROR(ProcessStatus.FAIL, "APP-001-000", "API錯誤!"),
	QUERY_SUCCESS(ProcessStatus.SUCCESS, "APP-001-001", "查詢成功"),
	DATA_NOT_FOUND(ProcessStatus.SUCCESS, "APP-001-002", "查無資料"),
	NO_RECEIVE_DATA(ProcessStatus.FAIL, "APP-001-003", "因為逾時所以未收到資料");
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
