package com.ctbcbank.visual.ivr.esb.enumeraion;

public enum EsbServiceName {
	IV_BILL_INQ("ivBillInq"), IV_CARD_PROF_CHK("ivCardProfChk");
	private String name;

	private EsbServiceName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
