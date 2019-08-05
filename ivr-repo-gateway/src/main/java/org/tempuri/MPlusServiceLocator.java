/**
 * MPlusServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MPlusServiceLocator extends org.apache.axis.client.Service implements org.tempuri.MPlusService {

    public MPlusServiceLocator() {
    }


    public MPlusServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MPlusServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MPlusServiceSoap
    private java.lang.String MPlusServiceSoap_address = "http://172.24.30.96/MPlusWeb/MPlusService.asmx";

    public java.lang.String getMPlusServiceSoapAddress() {
        return MPlusServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MPlusServiceSoapWSDDServiceName = "MPlusServiceSoap";

    public java.lang.String getMPlusServiceSoapWSDDServiceName() {
        return MPlusServiceSoapWSDDServiceName;
    }

    public void setMPlusServiceSoapWSDDServiceName(java.lang.String name) {
        MPlusServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.MPlusServiceSoap getMPlusServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MPlusServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMPlusServiceSoap(endpoint);
    }

    public org.tempuri.MPlusServiceSoap getMPlusServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.MPlusServiceSoapStub _stub = new org.tempuri.MPlusServiceSoapStub(portAddress, this);
            _stub.setPortName(getMPlusServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMPlusServiceSoapEndpointAddress(java.lang.String address) {
        MPlusServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.MPlusServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.MPlusServiceSoapStub _stub = new org.tempuri.MPlusServiceSoapStub(new java.net.URL(MPlusServiceSoap_address), this);
                _stub.setPortName(getMPlusServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MPlusServiceSoap".equals(inputPortName)) {
            return getMPlusServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "MPlusService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "MPlusServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MPlusServiceSoap".equals(portName)) {
            setMPlusServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
