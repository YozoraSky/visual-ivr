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

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendinqrq._01.CsBillReSendInqRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendinqrs._01.CsBillReSendInqRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Service
public class CsBillReSendInqService extends EsbCommonService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EsbInvoke esbInvoke;

	public CsBillReSendInqRs csBillReSendInq(final CsBillReSendInqRq csBillReSendInqRq) throws Exception {
		CsBillReSendInqRs csBillReSendInqRs = null;
		// reqest header set default
		if (csBillReSendInqRq != null) {
			CsBillReSendInqRq.REQHDR reqhdr = csBillReSendInqRq.getREQHDR();
			ServiceEnvelope serviceEnvelope = this.getServiceEnvelope();
			if (reqhdr != null) {
				if (StringUtils.isNotBlank(csBillReSendInqRq.getREQHDR().getTrnNum())) {
					reqhdr.setTrnNum(csBillReSendInqRq.getREQHDR().getTrnNum());
					serviceEnvelope.getServiceHeader().setTransactionID(csBillReSendInqRq.getREQHDR().getTrnNum());
				}
				else {
					reqhdr.setTrnNum(this.getTransactionID());
				}
			}
			// set reqest body from input
			AnyContainer anyContainer = new AnyContainer();
			anyContainer.setAny(csBillReSendInqRq);
			serviceEnvelope.setServiceBody(anyContainer);
			// Object to xml
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class, CsBillReSendInqRq.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(serviceEnvelope, stringWriter);
			logger.info("=========csBillReSendInq input========= : {}", stringWriter.toString());
			String jmsResult = esbInvoke.excute(stringWriter.toString());
			logger.info("=========csBillReSendInq output========= : {}", jmsResult);
			// xml to Object
			if (StringUtils.isNotBlank(jmsResult)) {
				JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, CsBillReSendInqRs.class);
				Unmarshaller unmarshaller = j.createUnmarshaller();
				ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
				if (result.getServiceBody() != null && result.getServiceBody().getAny() != null) {
					csBillReSendInqRs = (CsBillReSendInqRs) result.getServiceBody().getAny();
				}
			}
		}
		return csBillReSendInqRs;
	}

	@Override
	public ServiceHeader getServiceHeader() {
		ServiceHeader serviceHeader = super.getServiceHeader();
		serviceHeader.setServiceName("csBillReSendInq");
		return serviceHeader;
	}
}
