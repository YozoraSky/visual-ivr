package com.ctbcbank.ivr.gateway.socket;

import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class ParseISO8583 {
	
	public String getRspCode(String hexString) throws ISOException {
		// Load package from resources directory.
		InputStream is = getClass().getResourceAsStream("/fields.xml");
		GenericPackager packager = new GenericPackager(is);
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);
		isoMsg.unpack(hexString.getBytes());
//		System.out.printf("MTI = %s%n", isoMsg.getMTI());
//		for (int i = 1; i <= isoMsg.getMaxField(); i++) {
//			if (isoMsg.hasField(i)) {
//				System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
//			}
//		}
		return isoMsg.getString(39);
	}
}
