package org.tempuri;

public class ActualizacionesRegularesSoapProxy implements org.tempuri.ActualizacionesRegularesSoap {
  private String _endpoint = null;
  private org.tempuri.ActualizacionesRegularesSoap actualizacionesRegularesSoap = null;
  
  public ActualizacionesRegularesSoapProxy() {
    _initActualizacionesRegularesSoapProxy();
  }
  
  public ActualizacionesRegularesSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initActualizacionesRegularesSoapProxy();
  }
  
  private void _initActualizacionesRegularesSoapProxy() {
    try {
      actualizacionesRegularesSoap = (new org.tempuri.ActualizacionesRegularesLocator()).getActualizacionesRegularesSoap();
      if (actualizacionesRegularesSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)actualizacionesRegularesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)actualizacionesRegularesSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (actualizacionesRegularesSoap != null)
      ((javax.xml.rpc.Stub)actualizacionesRegularesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.ActualizacionesRegularesSoap getActualizacionesRegularesSoap() {
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap;
  }
  
  public java.lang.String consultarMaestroTiposRamo(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroTiposRamo(xmlData);
  }
  
  public java.lang.String consultarMaestroRamos(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroRamos(xmlData);
  }
  
  public java.lang.String consultarMaestroCoberturas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroCoberturas(xmlData);
  }
  
  public java.lang.String consultarMaestroProductos(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroProductos(xmlData);
  }
  
  public java.lang.String consultarProductosCoberturas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarProductosCoberturas(xmlData);
  }
  
  public java.lang.String consultarMaestroOcupaciones(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroOcupaciones(xmlData);
  }
  
  public java.lang.String consultarMaestroProfesiones(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroProfesiones(xmlData);
  }
  
  public java.lang.String consultarPersonas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarPersonas(xmlData);
  }
  
  public java.lang.String consultarMotivosPersonas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMotivosPersonas(xmlData);
  }
  
  public java.lang.String consultarDeportesPersonas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarDeportesPersonas(xmlData);
  }
  
  public java.lang.String consultarCompanias(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarCompanias(xmlData);
  }
  
  public java.lang.String consultarMaestroActEconomicas(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarMaestroActEconomicas(xmlData);
  }
  
  public java.lang.String consultarActEconomicasCompanias(java.lang.String xmlData) throws java.rmi.RemoteException{
    if (actualizacionesRegularesSoap == null)
      _initActualizacionesRegularesSoapProxy();
    return actualizacionesRegularesSoap.consultarActEconomicasCompanias(xmlData);
  }
  
  
}