/**
 * ServiceName.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ctbcbank.Eai.IAcquirer.Ctcb;

public interface ServiceName extends java.rmi.Remote {
    public com.ctbcbank.org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrSetInfo setAuthBackupFromIvr(java.lang.String type, java.lang.String amount, java.lang.String modifiedDate, java.lang.String transactionId) throws java.rmi.RemoteException;
    public com.ctbcbank.org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrDataInfo insertIVRData(java.lang.String cardNumber, java.lang.String amount, java.lang.String retlId, java.lang.String trackExpirationDate, java.lang.String transactionId) throws java.rmi.RemoteException;
}
