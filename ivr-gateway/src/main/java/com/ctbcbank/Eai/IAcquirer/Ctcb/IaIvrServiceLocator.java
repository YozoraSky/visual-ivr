/**
 * IaIvrServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ctbcbank.Eai.IAcquirer.Ctcb;

public class IaIvrServiceLocator extends org.apache.axis.client.Service implements com.ctbcbank.Eai.IAcquirer.Ctcb.IaIvrService {

    public IaIvrServiceLocator() {
    }


    public IaIvrServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IaIvrServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_ServiceName
    private java.lang.String BasicHttpBinding_ServiceName_address = "http://172.24.30.37/IaEai/IaIvrService.svc";

    public java.lang.String getBasicHttpBinding_ServiceNameAddress() {
        return BasicHttpBinding_ServiceName_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_ServiceNameWSDDServiceName = "BasicHttpBinding_ServiceName";

    public java.lang.String getBasicHttpBinding_ServiceNameWSDDServiceName() {
        return BasicHttpBinding_ServiceNameWSDDServiceName;
    }

    public void setBasicHttpBinding_ServiceNameWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_ServiceNameWSDDServiceName = name;
    }

    public com.ctbcbank.Eai.IAcquirer.Ctcb.ServiceName getBasicHttpBinding_ServiceName() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_ServiceName_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_ServiceName(endpoint);
    }

    public com.ctbcbank.Eai.IAcquirer.Ctcb.ServiceName getBasicHttpBinding_ServiceName(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ctbcbank.Eai.IAcquirer.Ctcb.BasicHttpBinding_ServiceNameStub _stub = new com.ctbcbank.Eai.IAcquirer.Ctcb.BasicHttpBinding_ServiceNameStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_ServiceNameWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_ServiceNameEndpointAddress(java.lang.String address) {
        BasicHttpBinding_ServiceName_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ctbcbank.Eai.IAcquirer.Ctcb.ServiceName.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ctbcbank.Eai.IAcquirer.Ctcb.BasicHttpBinding_ServiceNameStub _stub = new com.ctbcbank.Eai.IAcquirer.Ctcb.BasicHttpBinding_ServiceNameStub(new java.net.URL(BasicHttpBinding_ServiceName_address), this);
                _stub.setPortName(getBasicHttpBinding_ServiceNameWSDDServiceName());
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
        if ("BasicHttpBinding_ServiceName".equals(inputPortName)) {
            return getBasicHttpBinding_ServiceName();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://Ctcb.IAcquirer.Eai/", "IaIvrService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://Ctcb.IAcquirer.Eai/", "BasicHttpBinding_ServiceName"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_ServiceName".equals(portName)) {
            setBasicHttpBinding_ServiceNameEndpointAddress(address);
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
