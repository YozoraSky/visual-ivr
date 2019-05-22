package com.ctbcbank.visual.ivr.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.esb.EsbInvoke;
import com.ctbcbank.visual.ivr.esb.enumeraion.EsbServiceName;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrq._01.IvCardProfChkRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrs._01.IvCardProfChkRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Service
public class IvCardProfChkService extends EsbCommonService {
	private Logger logger = LoggerFactory.getLogger(IvCardProfChkService.class);
	@Autowired
	private EsbInvoke esbInvoke;

	public IvCardProfChkRs ivCardProfChk(final IvCardProfChkRq ivCardProfChkRq) throws Exception {
		IvCardProfChkRs ivCardProfChkRs = null;
		// reqest header set default
		String systemId = super.getSystemId();
		String trnNum = getTransactionID();
		String trnCode = "JCNV0001";
		if (ivCardProfChkRq != null) {
			IvCardProfChkRq.REQHDR reqhdr = ivCardProfChkRq.getREQHDR();
			if (reqhdr != null) {
				if (StringUtils.isNotBlank(reqhdr.getSystemId())) {
					systemId = reqhdr.getSystemId();
				}
				if (StringUtils.isNotBlank(reqhdr.getTrnNum())) {
					trnNum = reqhdr.getTrnNum();
				}
				if (StringUtils.isNotBlank(reqhdr.getTrnCode())) {
					trnCode = reqhdr.getTrnCode();
				}
			}
			
			ServiceEnvelope serviceEnvelope = new ServiceEnvelope();
			ServiceHeader serviceHeader = this.getServiceHeader();
			serviceHeader.setTransactionID(trnNum);
			serviceEnvelope.setServiceHeader(serviceHeader);
			
			// set reqest body from input
			AnyContainer anyContainer = new AnyContainer();
			IvCardProfChkRq newIvCardProfChkRq = new IvCardProfChkRq();
			IvCardProfChkRq.REQHDR newReqhdr = new IvCardProfChkRq.REQHDR();
			newReqhdr.setSystemId(systemId);
			newReqhdr.setTrnNum(trnNum);
			newReqhdr.setTrnCode(trnCode);
			newIvCardProfChkRq.setREQHDR(newReqhdr);
			IvCardProfChkRq.REQBDY newReqbdy = new IvCardProfChkRq.REQBDY();
			// JCNV0001 輸入CKI1 JCNV0028 輸入CKI3
			newReqbdy.setFUNCD("CKI1");
			if (ivCardProfChkRq.getREQBDY() != null) {
				BeanUtils.copyProperties(ivCardProfChkRq.getREQBDY(), newReqbdy);
				newIvCardProfChkRq.setREQBDY(newReqbdy);
			}
			anyContainer.setAny(newIvCardProfChkRq);
			serviceEnvelope.setServiceBody(anyContainer);
			
			// Object to xml
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class, IvCardProfChkRq.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(serviceEnvelope, stringWriter);
			logger.info("=========ivCardProfChk input========= : {}", stringWriter.toString());
			String jmsResult = esbInvoke.excute(stringWriter.toString());
			logger.info("=========ivCardProfChk output========= : {}", jmsResult);
			// xml to Object
			if(StringUtils.isNotBlank(jmsResult)) {
				JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, IvCardProfChkRs.class);
				Unmarshaller unmarshaller = j.createUnmarshaller();
				ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
				if (result.getServiceBody() != null && result.getServiceBody().getAny() != null) {
					ivCardProfChkRs = (IvCardProfChkRs) result.getServiceBody().getAny();
				}
			}

		}
		return ivCardProfChkRs;
	}

	@Override
	public ServiceHeader getServiceHeader() {
		ServiceHeader serviceHeader = super.getServiceHeader();
		serviceHeader.setServiceName(EsbServiceName.IV_CARD_PROF_CHK.getName());
		return serviceHeader;
	}
}
