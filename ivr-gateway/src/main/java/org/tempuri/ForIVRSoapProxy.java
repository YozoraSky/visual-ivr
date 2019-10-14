package org.tempuri;

public class ForIVRSoapProxy implements org.tempuri.ForIVRSoap {
  private String _endpoint = null;
  private org.tempuri.ForIVRSoap forIVRSoap = null;
  
  public ForIVRSoapProxy() {
    _initForIVRSoapProxy();
  }
  
  public ForIVRSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initForIVRSoapProxy();
  }
  
  private void _initForIVRSoapProxy() {
    try {
      forIVRSoap = (new org.tempuri.ForIVRLocator()).getForIVRSoap();
      if (forIVRSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)forIVRSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)forIVRSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (forIVRSoap != null)
      ((javax.xml.rpc.Stub)forIVRSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.ForIVRSoap getForIVRSoap() {
    if (forIVRSoap == null)
      _initForIVRSoapProxy();
    return forIVRSoap;
  }
  
  public java.lang.String isValid(java.lang.String transNo, java.lang.String customerAccount, java.lang.String countryCode, java.lang.String licenseKey, java.lang.String customerPassword, java.lang.String channelID) throws java.rmi.RemoteException{
    if (forIVRSoap == null)
      _initForIVRSoapProxy();
    return forIVRSoap.isValid(transNo, customerAccount, countryCode, licenseKey, customerPassword, channelID);
  }
  
  public java.lang.String changePassword(java.lang.String transNo, java.lang.String customerAccount, java.lang.String countryCode, java.lang.String licenseKey, java.lang.String oldPassword, java.lang.String newPassword, java.lang.String channelID) throws java.rmi.RemoteException{
    if (forIVRSoap == null)
      _initForIVRSoapProxy();
    return forIVRSoap.changePassword(transNo, customerAccount, countryCode, licenseKey, oldPassword, newPassword, channelID);
  }
  
  
}