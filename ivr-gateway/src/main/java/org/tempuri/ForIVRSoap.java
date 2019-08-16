/**
 * ForIVRSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface ForIVRSoap extends java.rmi.Remote {
    public java.lang.String isValid(java.lang.String transNo, java.lang.String customerAccount, java.lang.String countryCode, java.lang.String licenseKey, java.lang.String customerPassword, java.lang.String channelID) throws java.rmi.RemoteException;
    public java.lang.String changePassword(java.lang.String transNo, java.lang.String customerAccount, java.lang.String countryCode, java.lang.String licenseKey, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String channelID) throws java.rmi.RemoteException;
}
