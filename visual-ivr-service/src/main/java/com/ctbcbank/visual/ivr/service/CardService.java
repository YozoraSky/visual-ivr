package com.ctbcbank.visual.ivr.service;

import com.ctbcbank.visual.ivr.service.model.IvBillInq;
import com.ctbcbank.visual.ivr.service.model.IvBillInqIn;
import com.ctbcbank.visual.ivr.service.model.IvCardProfChk;
import com.ctbcbank.visual.ivr.service.model.IvCardProfChkIn;

public interface CardService {
	IvBillInq ivBillInq(final IvBillInqIn ivBillInqIn) throws Exception;
	
	IvCardProfChk ivCardProfChk(final IvCardProfChkIn ivCardProfChkIn) throws Exception;
}
