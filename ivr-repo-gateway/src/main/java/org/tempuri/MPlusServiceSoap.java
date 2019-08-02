/**
 * MPlusServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface MPlusServiceSoap extends java.rmi.Remote {
    public java.lang.String sendMessage(java.lang.String mPlusGroupId, java.lang.String noticeTitle, java.lang.String noticeContent, org.tempuri.IvrCallLog oData, java.util.Calendar nowTime) throws java.rmi.RemoteException;
}
