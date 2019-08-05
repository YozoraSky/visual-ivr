package Eai.IAcquirer.Ctcb;

public class ServiceNameProxy implements Eai.IAcquirer.Ctcb.ServiceName {
  private String _endpoint = null;
  private Eai.IAcquirer.Ctcb.ServiceName serviceName = null;
  
  public ServiceNameProxy() {
    _initServiceNameProxy();
  }
  
  public ServiceNameProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceNameProxy();
  }
  
  private void _initServiceNameProxy() {
    try {
      serviceName = (new Eai.IAcquirer.Ctcb.IaIvrServiceLocator()).getBasicHttpBinding_ServiceName();
      if (serviceName != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceName)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceName)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceName != null)
      ((javax.xml.rpc.Stub)serviceName)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Eai.IAcquirer.Ctcb.ServiceName getServiceName() {
    if (serviceName == null)
      _initServiceNameProxy();
    return serviceName;
  }
  
  public org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrSetInfo setAuthBackupFromIvr(java.lang.String type, java.lang.String amount, java.lang.String modifiedDate, java.lang.String transactionId) throws java.rmi.RemoteException{
    if (serviceName == null)
      _initServiceNameProxy();
    return serviceName.setAuthBackupFromIvr(type, amount, modifiedDate, transactionId);
  }
  
  public org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IaIvrDataInfo insertIVRData(java.lang.String cardNumber, java.lang.String amount, java.lang.String retlId, java.lang.String trackExpirationDate, java.lang.String transactionId) throws java.rmi.RemoteException{
    if (serviceName == null)
      _initServiceNameProxy();
    return serviceName.insertIVRData(cardNumber, amount, retlId, trackExpirationDate, transactionId);
  }
  
  
}