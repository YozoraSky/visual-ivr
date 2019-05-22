package com.ctbcbank.esb.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.jms.JMSException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctbcbank.esb.EsbInvoke;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrq._01.IvBillInqRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrs._01.IvBillInqRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

@RunWith(SpringRunner.class)
public class IvBillInqServiceTest {
	// @Test
	public void test() throws DatatypeConfigurationException, JAXBException, JMSException {
		ServiceEnvelope serviceEnvelope = new ServiceEnvelope();
		ServiceHeader serviceHeader = new ServiceHeader();
		serviceHeader.setServiceName("ivBillInq");
		serviceHeader.setServiceVersion("01");
		serviceHeader.setSourceID("CSDP");
		serviceHeader.setTransactionID("IVR20180704123456");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		serviceHeader.setRqTimestamp(xgcal);
		serviceEnvelope.setServiceHeader(serviceHeader);
		AnyContainer anyContainer = new AnyContainer();
		IvBillInqRq ivBillInqRq = new IvBillInqRq();
		IvBillInqRq.REQHDR reqhdr = new IvBillInqRq.REQHDR();
		reqhdr.setSystemId("JCNV");
		reqhdr.setTrnNum("IVR20180704123456");
		reqhdr.setTrnCode("JCNV0010");
		ivBillInqRq.setREQHDR(reqhdr);
		IvBillInqRq.REQBDY reqbdy = new IvBillInqRq.REQBDY();
		reqbdy.setFUNCD("STMI");
		reqbdy.setIDNO("B101063165");
		ivBillInqRq.setREQBDY(reqbdy);
		anyContainer.setAny(ivBillInqRq);
		serviceEnvelope.setServiceBody(anyContainer);
		JAXBContext jaxbContext = JAXBContext.newInstance(serviceEnvelope.getClass(), ivBillInqRq.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshaller.marshal(serviceEnvelope, stringWriter);
		System.out.println(stringWriter.toString());
		EsbInvoke t = new EsbInvoke();
		String jmsResult = t.excute(stringWriter.toString());
		JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, IvBillInqRs.class);
		Unmarshaller unmarshaller = j.createUnmarshaller();
		ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
		if (result.getServiceBody().getAny() != null) {
			IvBillInqRs rs = (IvBillInqRs) result.getServiceBody().getAny();
			System.out.println(rs.getRESHDR().getRspCode());
		}
	}
}
