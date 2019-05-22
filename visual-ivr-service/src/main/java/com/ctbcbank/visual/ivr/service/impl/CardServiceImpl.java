package com.ctbcbank.visual.ivr.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.visual.ivr.service.CardService;
import com.ctbcbank.visual.ivr.service.IvBillInqService;
import com.ctbcbank.visual.ivr.service.IvCardProfChkService;
import com.ctbcbank.visual.ivr.service.model.IvBillInq;
import com.ctbcbank.visual.ivr.service.model.IvBillInqIn;
import com.ctbcbank.visual.ivr.service.model.IvCardProfChk;
import com.ctbcbank.visual.ivr.service.model.IvCardProfChkIn;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrq._01.IvBillInqRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrs._01.IvBillInqRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrq._01.IvCardProfChkRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrs._01.IvCardProfChkRs;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	IvBillInqService ivBillInqService;
	@Autowired
	IvCardProfChkService ivCardProfChkService;

	@Override
	public IvBillInq ivBillInq(final IvBillInqIn ivBillInqIn) throws Exception {
		final IvBillInq ivBillInq = new IvBillInq();
		final IvBillInqRq ivBillInqRq = new IvBillInqRq();
		if (ivBillInqIn.getREQHDR() != null) {
			IvBillInqRq.REQHDR esbReqHdr = new IvBillInqRq.REQHDR();
			BeanUtils.copyProperties(ivBillInqIn.getREQHDR(), esbReqHdr);
			ivBillInqRq.setREQHDR(esbReqHdr);
		}
		if (ivBillInqIn.getREQBDY() != null) {
			IvBillInqRq.REQBDY esbReqBdy = new IvBillInqRq.REQBDY();
			BeanUtils.copyProperties(ivBillInqIn.getREQBDY(), esbReqBdy);
			ivBillInqRq.setREQBDY(esbReqBdy);
		}
		IvBillInqRs ivBillInqRs = ivBillInqService.ivBillInq(ivBillInqRq);
		if (ivBillInqRs != null) {
			IvBillInq.RESHDR reshdr = new IvBillInq.RESHDR();
			IvBillInq.RESBDY resbdy = new IvBillInq.RESBDY();
			if (ivBillInqRs.getRESHDR() != null) {
				BeanUtils.copyProperties(ivBillInqRs.getRESHDR(), reshdr);
			}
			if (ivBillInqRs.getRESBDY() != null && ivBillInqRs.getRESBDY().getBDYREC() != null) {
				for (IvBillInqRs.RESBDY.BDYREC bdyrec : ivBillInqRs.getRESBDY().getBDYREC()) {
					IvBillInq.RESBDY.BDYREC newBDYREC = new IvBillInq.RESBDY.BDYREC();
					BeanUtils.copyProperties(bdyrec, newBDYREC);
					resbdy.getBDYREC().add(newBDYREC);
				}
			}
			ivBillInq.setRESHDR(reshdr);
			ivBillInq.setRESBDY(resbdy);
		}
		return ivBillInq;
	}

	@Override
	public IvCardProfChk ivCardProfChk(final IvCardProfChkIn ivCardProfChkIn) throws Exception {
		final IvCardProfChk ivCardProfChk = new IvCardProfChk();
		final IvCardProfChkRq ivCardProfChkRq = new IvCardProfChkRq();
		if (ivCardProfChkIn.getREQHDR() != null) {
			IvCardProfChkRq.REQHDR esbReqHdr = new IvCardProfChkRq.REQHDR();
			BeanUtils.copyProperties(ivCardProfChkIn.getREQHDR(), esbReqHdr);
			ivCardProfChkRq.setREQHDR(esbReqHdr);
		}
		if (ivCardProfChkIn.getREQBDY() != null) {
			IvCardProfChkRq.REQBDY esbReqBdy = new IvCardProfChkRq.REQBDY();
			BeanUtils.copyProperties(ivCardProfChkIn.getREQBDY(), esbReqBdy);
			ivCardProfChkRq.setREQBDY(esbReqBdy);
		}
		IvCardProfChkRs ivCardProfChkRs = ivCardProfChkService.ivCardProfChk(ivCardProfChkRq);
		if (ivCardProfChkRs != null) {
			IvCardProfChk.RESHDR reshdr = new IvCardProfChk.RESHDR();
			IvCardProfChk.RESBDY resbdy = new IvCardProfChk.RESBDY();
			if (ivCardProfChkRs.getRESHDR() != null) {
				BeanUtils.copyProperties(ivCardProfChkRs.getRESHDR(), reshdr);
			}
			if (ivCardProfChkRs.getRESBDY() != null && ivCardProfChkRs.getRESBDY().getBDYREC() != null) {
				for (IvCardProfChkRs.RESBDY.BDYREC bdyrec : ivCardProfChkRs.getRESBDY().getBDYREC()) {
					IvCardProfChk.RESBDY.BDYREC newBDYREC = new IvCardProfChk.RESBDY.BDYREC();
					BeanUtils.copyProperties(bdyrec, newBDYREC);
					resbdy.getBDYREC().add(newBDYREC);
				}
			}
			ivCardProfChk.setRESHDR(reshdr);
			ivCardProfChk.setRESBDY(resbdy);
		}
		return ivCardProfChk;
	}
}
