/**
 * ForIVRLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ForIVRLocator extends org.apache.axis.client.Service implements org.tempuri.ForIVR {

    public ForIVRLocator() {
    }


    public ForIVRLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ForIVRLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ForIVRSoap
    private java.lang.String ForIVRSoap_address = "http://192.168.33.40:8084/IVR/ForIVR.asmx";

    public java.lang.String getForIVRSoapAddress() {
        return ForIVRSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ForIVRSoapWSDDServiceName = "ForIVRSoap";

    public java.lang.String getForIVRSoapWSDDServiceName() {
        return ForIVRSoapWSDDServiceName;
    }

    public void setForIVRSoapWSDDServiceName(java.lang.String name) {
        ForIVRSoapWSDDServiceName = name;
    }

    public org.tempuri.ForIVRSoap getForIVRSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ForIVRSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getForIVRSoap(endpoint);
    }

    public org.tempuri.ForIVRSoap getForIVRSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.ForIVRSoapStub _stub = new org.tempuri.ForIVRSoapStub(portAddress, this);
            _stub.setPortName(getForIVRSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setForIVRSoapEndpointAddress(java.lang.String address) {
        ForIVRSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.ForIVRSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.ForIVRSoapStub _stub = new org.tempuri.ForIVRSoapStub(new java.net.URL(ForIVRSoap_address), this);
                _stub.setPortName(getForIVRSoapWSDDServiceName());
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
        if ("ForIVRSoap".equals(inputPortName)) {
            return getForIVRSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ForIVR");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ForIVRSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ForIVRSoap".equals(portName)) {
            setForIVRSoapEndpointAddress(address);
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
