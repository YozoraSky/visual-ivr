package com.ctbcbank.visual.ivr.service.enumeration;

public enum ProcessResultEnum {
	SYSTEM_ERROR(ProcessStatus.FAIL, "APP-004-000", "系統忙碌中!"),
	QUERY_SUCCESS(ProcessStatus.SUCCESS, "APP-004-001", "查詢成功"),
	CALLBACK_SUCCESS(ProcessStatus.SUCCESS, "APP-004-002", "預約成功"),
	CALLBACK_FAIL(ProcessStatus.FAIL, "APP-004-003", "預約失敗"),
	DATA_NOT_FOUND(ProcessStatus.WARNING, "APP-004-004", "查無資料"),
	CHANNEL_FULL_LINE(ProcessStatus.WARNING, "APP-004-006", "很抱歉，客服均在忙線中，建議您可選擇其他渠道！"),
	ALREADY_CALLBACK(ProcessStatus.WARNING, "APP-004-007", "您已經有一筆預約資料:{0}，預計{1}回撥，若您要取消該筆預約請按確定鍵，若要重新預約，請先取消預約並再次點選預約服務，謝謝。"),
	FULL_CALLBACK(ProcessStatus.WARNING, "APP-004-008", "{MIN}分鐘預約服務已滿，建議您改選其他時段!!"),
	CANCEL_CALLBACK_SUCCESS(ProcessStatus.SUCCESS, "APP-004-009", "已取消成功"),
	CANCEL_CALLBACK_FAIL(ProcessStatus.FAIL, "APP-004-010", "取消失敗"),
	MORE_THAN_CALLBACK_TIME(ProcessStatus.WARNING, "APP-004-011", "已超過預約時段，時間為早上九點至下午六點，謝謝。");
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
