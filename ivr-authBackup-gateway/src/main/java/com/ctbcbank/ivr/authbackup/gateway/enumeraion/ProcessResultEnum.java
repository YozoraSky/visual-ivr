package com.ctbcbank.ivr.authbackup.gateway.enumeraion;

public enum ProcessResultEnum {
	SYSTEM_ERROR(ProcessStatus.FAIL, "APP-001-000", "API錯誤!"),
	UPDATE_SUCCESS(ProcessStatus.SUCCESS, "APP-001-001","更新成功"),
	UPDATE_FAIL(ProcessStatus.FAIL, "APP-001-002", "更新失敗"),
	INSERT_SUCCESS(ProcessStatus.SUCCESS, "APP-001-003","新增成功");
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
