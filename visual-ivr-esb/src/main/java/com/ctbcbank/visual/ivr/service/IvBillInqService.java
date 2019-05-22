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

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrq._01.IvBillInqRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrs._01.IvBillInqRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@Service
public class IvBillInqService extends EsbCommonService {
	private Logger logger = LoggerFactory.getLogger(IvBillInqService.class);
	@Autowired
	private EsbInvoke esbInvoke;

	public IvBillInqRs ivBillInq(final IvBillInqRq ivBillInqRq) throws Exception {
		IvBillInqRs ivBillInqRs = null;
		// reqest header set default
		String systemId = super.getSystemId();
		String trnNum = getTransactionID();
		String trnCode = "JCNV0010";
		if (ivBillInqRq != null) {
			IvBillInqRq.REQHDR reqhdr = ivBillInqRq.getREQHDR();
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
			IvBillInqRq newIvBillInqRq = new IvBillInqRq();
			IvBillInqRq.REQHDR newReqhdr = new IvBillInqRq.REQHDR();
			newReqhdr.setSystemId(systemId);
			newReqhdr.setTrnNum(trnNum);
			newReqhdr.setTrnCode(trnCode);
			newIvBillInqRq.setREQHDR(newReqhdr);
			IvBillInqRq.REQBDY newReqbdy = new IvBillInqRq.REQBDY();
			if (ivBillInqRq.getREQBDY() != null) {
				BeanUtils.copyProperties(ivBillInqRq.getREQBDY(), newReqbdy);
				newIvBillInqRq.setREQBDY(newReqbdy);
			}
			anyContainer.setAny(newIvBillInqRq);
			serviceEnvelope.setServiceBody(anyContainer);
			// Object to xml
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceEnvelope.class, IvBillInqRq.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(serviceEnvelope, stringWriter);
			logger.info("=========ivBillInq input========= : {}", stringWriter.toString());
			String jmsResult = esbInvoke.excute(stringWriter.toString());
			logger.info("=========ivBillInq output========= : {}", jmsResult);
			// xml to Object
			if (StringUtils.isNotBlank(jmsResult)) {
				JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, IvBillInqRs.class);
				Unmarshaller unmarshaller = j.createUnmarshaller();
				ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
				if (result.getServiceBody() != null && result.getServiceBody().getAny() != null) {
					ivBillInqRs = (IvBillInqRs) result.getServiceBody().getAny();
				}
			}
		}
		return ivBillInqRs;
	}

	@Override
	public ServiceHeader getServiceHeader() {
		ServiceHeader serviceHeader = super.getServiceHeader();
		serviceHeader.setServiceName(EsbServiceName.IV_BILL_INQ.getName());
		return serviceHeader;
	}
}
