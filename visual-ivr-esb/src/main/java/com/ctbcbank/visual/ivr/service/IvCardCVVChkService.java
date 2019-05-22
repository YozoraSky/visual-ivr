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

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardcvvchkrq._01.IvCardCVVChkRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardcvvchkrs._01.IvCardCVVChkRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Service
public class IvCardCVVChkService extends EsbCommonService {
	private Logger logger = LoggerFactory.getLogger(IvCardCVVChkService.class);
	@Autowired
	private EsbInvoke esbInvoke;

	public ServiceEnvelope ivCardCVVChk(final IvCardCVVChkRq ivCardCVVChkRq) throws Exception {
		ServiceEnvelope result = null;
		// reqest header set default
		String systemId = super.getSystemId();
		String trnNum = getTransactionID();
		String trnCode = "JCNV0097";
		if (ivCardCVVChkRq != null) {
			IvCardCVVChkRq.REQHDR reqhdr = ivCardCVVChkRq.getREQHDR();
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
			IvCardCVVChkRq newIvCardCVVChkRq = new IvCardCVVChkRq();
			IvCardCVVChkRq.REQHDR newReqhdr = new IvCardCVVChkRq.REQHDR();
			newReqhdr.setSystemId(systemId);
			newReqhdr.setTrnNum(trnNum);
			newReqhdr.setTrnCode(trnCode);
			newIvCardCVVChkRq.setREQHDR(newReqhdr);
			IvCardCVVChkRq.REQBDY newReqbdy = new IvCardCVVChkRq.REQBDY();
			newReqbdy.setFUNCD("VCVV");
			if (ivCardCVVChkRq.getREQBDY() != null) {
				BeanUtils.copyProperties(ivCardCVVChkRq.getREQBDY(), newReqbdy);
				newIvCardCVVChkRq.setREQBDY(newReqbdy);
			}
			anyContainer.setAny(newIvCardCVVChkRq);
			serviceEnvelope.setServiceBody(anyContainer);
			// Object to xml
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class, IvCardCVVChkRq.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(serviceEnvelope, stringWriter);
			logger.info("=========ivCardCVVChk input========= : {}", stringWriter.toString());
			String jmsResult = esbInvoke.excute(stringWriter.toString());
			logger.info("=========ivCardCVVChk output========= : {}", jmsResult);
			// xml to Object
			if (StringUtils.isNotBlank(jmsResult)) {
				JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, IvCardCVVChkRs.class);
				Unmarshaller unmarshaller = j.createUnmarshaller();
				result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
			}
		}
		return result;
	}

	@Override
	public ServiceHeader getServiceHeader() {
		ServiceHeader serviceHeader = super.getServiceHeader();
		serviceHeader.setServiceName("ivCardCVVChk");
		return serviceHeader;
	}
}
