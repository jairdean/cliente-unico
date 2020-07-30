package com.equivida.www.services.clienteequivida.mpersona_juridica;

public class PortTypeProxy implements com.equivida.www.services.clienteequivida.mpersona_juridica.PortType {
  private String _endpoint = null;
  private com.equivida.www.services.clienteequivida.mpersona_juridica.PortType portType = null;
  
  public PortTypeProxy() {
    _initPortTypeProxy();
  }
  
  public PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPortTypeProxy();
  }
  
  private void _initPortTypeProxy() {
    try {
      portType = (new com.equivida.www.services.clienteequivida.mpersona_juridica.ClienteequividaMpersona_juridicaSrvLocator()).getPort();
      if (portType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)portType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)portType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (portType != null)
      ((javax.xml.rpc.Stub)portType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.www.services.clienteequivida.mpersona_juridica.PortType getPortType() {
    if (portType == null)
      _initPortTypeProxy();
    return portType;
  }
  
  public com.equivida.www.services.clienteequivida.mpersona_juridica.Response invoke(com.equivida.www.services.clienteequivida.mpersona_juridica.Request defaultInput) throws java.rmi.RemoteException{
    if (portType == null)
      _initPortTypeProxy();
    return portType.invoke(defaultInput);
  }
  
  
}