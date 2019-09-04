package com.ctbcbank.visual.ivr.service;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;

import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope.ServiceEnvelope;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;

public class EsbCommonService {
	private final static String SYSTEM_ID = "JCNV";
	private final static String IVR_PREFIX = "IVR";
	private final static String ZERO_ONE = "01";
	private final static String SOURCE_ID = "CSDP";
	private final static String GS = "GS";
	private static int serialNum = 0;
	
	public static synchronized String getSerialNum() {
		serialNum++;
		if(serialNum == 10)
			serialNum = 0;
		return String.valueOf(serialNum);
	}
	
	public ServiceHeader getServiceHeader() {
		final ServiceHeader serviceHeader = new ServiceHeader();
		serviceHeader.setServiceVersion(ZERO_ONE);
		serviceHeader.setSourceID(SOURCE_ID);
//		serviceHeader.setTransactionID(this.getTransactionID());
		try {
			GregorianCalendar gcal = new GregorianCalendar();
			XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			serviceHeader.setRqTimestamp(xgcal);
		}
		catch (DatatypeConfigurationException e) {
		}
		return serviceHeader;
	}
	
	public ServiceEnvelope getServiceEnvelope() {
		final ServiceEnvelope serviceEnvelope = new ServiceEnvelope();
		serviceEnvelope.setServiceHeader(this.getServiceHeader());
		return serviceEnvelope;
	}

	public String getTransactionID() {
//		HHmmss代表流水號
		return IVR_PREFIX + DateTime.now().toString("yyyyMMddHHmmss");
	}
	
	public String getGSTransactionID() {
//		SSS+getSerialNum()代表流水號
		return GS + DateTime.now().toString("yyyyMMddHHmmssSSS") + getSerialNum();
	}
	
	public String getTranNo() {
		return GS + DateTime.now().toString("yyyyMMddHHmmssSS").substring(2);
	}
	
	public String getSystemId() {
		return SYSTEM_ID;
	}

}
