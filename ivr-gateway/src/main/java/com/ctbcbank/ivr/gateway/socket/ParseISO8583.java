package com.ctbcbank.ivr.gateway.socket;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import com.ctbcbank.visual.ivr.encrypt.Log;

public class ParseISO8583 {
	
	public String getRspCode(String hexString, SocketIn socketIn, Log log) {
		String rspCode = StringUtils.EMPTY;
		InputStream is = getClass().getResourceAsStream("/fields.xml");
		GenericPackager packager;
		try {
			packager = new GenericPackager(is);
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.unpack(hexString.getBytes());
			rspCode = isoMsg.getString(39);
		} catch (ISOException e) {
			log.writeError(socketIn, e, Log.IVRSOCKETGATEWAY);
		}
//		System.out.printf("MTI = %s%n", isoMsg.getMTI());
//		for (int i = 1; i <= isoMsg.getMaxField(); i++) {
//			if (isoMsg.hasField(i)) {
//				System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
//			}
//		}
		return rspCode;
	}
}
