package com.ctbcbank.visual.ivr.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.esb.EsbInvoke;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendntfyrq._01.CsBillReSendNtfyRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendntfyrs._01.CsBillReSendNtfyRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Service
public class CsBillReSendNtfyService extends EsbCommonService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EsbInvoke esbInvoke;

	public CsBillReSendNtfyRs csBillReSendNtfy(final CsBillReSendNtfyRq csBillReSendNtfyRq) throws Exception {
		CsBillReSendNtfyRs csBillReSendNtfyRs = null;
		// reqest header set default
		if (csBillReSendNtfyRq != null) {
			CsBillReSendNtfyRq.REQHDR reqhdr = csBillReSendNtfyRq.getREQHDR();
			ServiceEnvelope serviceEnvelope = this.getServiceEnvelope();
			if (reqhdr != null) {
				if (StringUtils.isNotBlank(csBillReSendNtfyRq.getREQHDR().getTrnNum())) {
					reqhdr.setTrnNum(csBillReSendNtfyRq.getREQHDR().getTrnNum());
					serviceEnvelope.getServiceHeader().setTransactionID(csBillReSendNtfyRq.getREQHDR().getTrnNum());
				}
				else {
					reqhdr.setTrnNum(this.getTransactionID());
				}
			}
			// set reqest body from input
			AnyContainer anyContainer = new AnyContainer();
			anyContainer.setAny(csBillReSendNtfyRq);
			serviceEnvelope.setServiceBody(anyContainer);
			// Object to xml
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class, CsBillReSendNtfyRq.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(serviceEnvelope, stringWriter);
			logger.info("=========csBillReSendNtfy input========= : {}", stringWriter.toString());
			String jmsResult = esbInvoke.excute(stringWriter.toString());
			logger.info("=========csBillReSendNtfy output========= : {}", jmsResult);
			// xml to Object
			if (StringUtils.isNotBlank(jmsResult)) {
				JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, CsBillReSendNtfyRs.class);
				Unmarshaller unmarshaller = j.createUnmarshaller();
				ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
				if (result.getServiceBody() != null && result.getServiceBody().getAny() != null) {
					csBillReSendNtfyRs = (CsBillReSendNtfyRs) result.getServiceBody().getAny();
				}
			}
		}
		return csBillReSendNtfyRs;
	}

	@Override
	public ServiceHeader getServiceHeader() {
		ServiceHeader serviceHeader = super.getServiceHeader();
		serviceHeader.setServiceName("csBillReSendNtfy");
		return serviceHeader;
	}
}
