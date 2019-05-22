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

import org.junit.Test;

import com.ctbcbank.esb.EsbInvoke;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrq._01.IvCardProfChkRq;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrs._01.IvCardProfChkRs;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

public class IvCardProfChkServiceTest {
	@Test
	public void test() throws DatatypeConfigurationException, JAXBException, JMSException {
		ServiceEnvelope serviceEnvelope = new ServiceEnvelope();
		ServiceHeader serviceHeader = new ServiceHeader();
		serviceHeader.setServiceName("ivCardProfChk");
		serviceHeader.setServiceVersion("01");
		serviceHeader.setSourceID("CSDP");
		serviceHeader.setTransactionID("IVR20180704123456");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		serviceHeader.setRqTimestamp(xgcal);
		serviceEnvelope.setServiceHeader(serviceHeader);
		AnyContainer anyContainer = new AnyContainer();
		IvCardProfChkRq ivCardProfChkRq = new IvCardProfChkRq();
		IvCardProfChkRq.REQHDR reqhdr = new IvCardProfChkRq.REQHDR();
		reqhdr.setTrnNum("IVR20180704123456");
		reqhdr.setTrnCode("JCNV0001");
		reqhdr.setSystemId("JCNV");
		ivCardProfChkRq.setREQHDR(reqhdr);
		IvCardProfChkRq.REQBDY reqbdy = new IvCardProfChkRq.REQBDY();
		reqbdy.setFUNCD("CKI1");
		reqbdy.setIDNO("B101063165");
		reqbdy.setPSWD("3165");
		ivCardProfChkRq.setREQBDY(reqbdy);
		anyContainer.setAny(ivCardProfChkRq);
		serviceEnvelope.setServiceBody(anyContainer);
		JAXBContext jaxbContext = JAXBContext.newInstance(serviceEnvelope.getClass(), ivCardProfChkRq.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshaller.marshal(serviceEnvelope, stringWriter);
		EsbInvoke t = new EsbInvoke();
		String jmsResult = t.excute(stringWriter.toString());
		JAXBContext j = JAXBContext.newInstance(ServiceEnvelope.class, IvCardProfChkRs.class);
		Unmarshaller unmarshaller = j.createUnmarshaller();
		ServiceEnvelope result = (ServiceEnvelope) unmarshaller.unmarshal(new StringReader(jmsResult));
		if (result.getServiceBody().getAny() != null) {
			IvCardProfChkRs rs = (IvCardProfChkRs) result.getServiceBody().getAny();
			System.out.println(rs.getRESHDR().getRspCode());
		}
	}
}
