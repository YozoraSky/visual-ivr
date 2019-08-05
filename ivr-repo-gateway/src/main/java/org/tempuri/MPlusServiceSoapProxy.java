package org.tempuri;

public class MPlusServiceSoapProxy implements org.tempuri.MPlusServiceSoap {
  private String _endpoint = null;
  private org.tempuri.MPlusServiceSoap mPlusServiceSoap = null;
  
  public MPlusServiceSoapProxy() {
    _initMPlusServiceSoapProxy();
  }
  
  public MPlusServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMPlusServiceSoapProxy();
  }
  
  private void _initMPlusServiceSoapProxy() {
    try {
      mPlusServiceSoap = (new org.tempuri.MPlusServiceLocator()).getMPlusServiceSoap();
      if (mPlusServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mPlusServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mPlusServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mPlusServiceSoap != null)
      ((javax.xml.rpc.Stub)mPlusServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.MPlusServiceSoap getMPlusServiceSoap() {
    if (mPlusServiceSoap == null)
      _initMPlusServiceSoapProxy();
    return mPlusServiceSoap;
  }
  
  public java.lang.String sendMessage(java.lang.String mPlusGroupId, java.lang.String noticeTitle, java.lang.String noticeContent, org.tempuri.IvrCallLog oData, java.util.Calendar nowTime) throws java.rmi.RemoteException{
    if (mPlusServiceSoap == null)
      _initMPlusServiceSoapProxy();
    return mPlusServiceSoap.sendMessage(mPlusGroupId, noticeTitle, noticeContent, oData, nowTime);
  }
  
  
}