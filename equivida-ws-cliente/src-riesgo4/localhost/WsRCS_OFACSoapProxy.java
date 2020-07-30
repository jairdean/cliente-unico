package localhost;

public class WsRCS_OFACSoapProxy implements localhost.WsRCS_OFACSoap {
  private String _endpoint = null;
  private localhost.WsRCS_OFACSoap wsRCS_OFACSoap = null;
  
  public WsRCS_OFACSoapProxy() {
    _initWsRCS_OFACSoapProxy();
  }
  
  public WsRCS_OFACSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsRCS_OFACSoapProxy();
  }
  
  private void _initWsRCS_OFACSoapProxy() {
    try {
      wsRCS_OFACSoap = (new localhost.WsRCS_OFACLocator()).getwsRCS_OFACSoap();
      if (wsRCS_OFACSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsRCS_OFACSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsRCS_OFACSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsRCS_OFACSoap != null)
      ((javax.xml.rpc.Stub)wsRCS_OFACSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public localhost.WsRCS_OFACSoap getWsRCS_OFACSoap() {
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap;
  }
  
  public localhost.BusquedaIDVerificationBridgerResponseBusquedaIDVerificationBridgerResult busquedaIDVerificationBridger(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIDV, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaIDVerificationBridger(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIdv, pathProveedorIDV, identityVerificationSetupOption, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public java.lang.String probarWebService(java.lang.String rutaSetupDB) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.probarWebService(rutaSetupDB);
  }
  
  public boolean agregarAListaAceptados(java.lang.String idBusqueda, java.lang.String rutaSetupDB, java.lang.String tipoCuenta, java.lang.String numeroCuenta, int tipoDocumento, java.lang.String numeroId, java.lang.String direccion, java.lang.String ciudad, java.lang.String verificadoPor, java.lang.String division, java.lang.String comentario, java.lang.String empresa, boolean viaSF, javax.xml.rpc.holders.IntHolder codigoError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.agregarAListaAceptados(idBusqueda, rutaSetupDB, tipoCuenta, numeroCuenta, tipoDocumento, numeroId, direccion, ciudad, verificadoPor, division, comentario, empresa, viaSF, codigoError, descripcionError);
  }
  
  public java.lang.String politicaEstablecidaBatch(java.lang.String idPolitica, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.politicaEstablecidaBatch(idPolitica, codError, descripcionError);
  }
  
  public java.lang.String[] formatosBI(java.lang.String rutaFormatosBI, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.formatosBI(rutaFormatosBI, codError, descError);
  }
  
  public localhost.BusquedaWireResponseBusquedaWireResult busquedaWire(java.lang.String licenciaEmpresa, java.lang.String contextoWire, java.lang.String separador, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaWire(licenciaEmpresa, contextoWire, separador, idPolitica, userName, perfilUsuario, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaIDVerificationResponseBusquedaIDVerificationResult busquedaIDVerification(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaIDVerification(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIdv, pathProveedorIdv, identityVerificationSetupOption, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public localhost.RCSA_SearchListsResponseRCSA_SearchListsResult RCSA_SearchLists(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String metodoBusqueda, int idGrupoNotif, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.RCSA_SearchLists(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, metodoBusqueda, idGrupoNotif, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult busquedaIDVerificationXML_Notification(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String metodoBusqueda, int idGrupoNotif, java.lang.String primaryKey, int codError, java.lang.String descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaIDVerificationXML_Notification(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, metodoBusqueda, idGrupoNotif, primaryKey, codError, descripcionError);
  }
  
  public void busquedaIDVerificationXML(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, localhost.holders.BusquedaIDVerificationXMLResponseBusquedaIDVerificationXMLResultHolder busquedaIDVerificationXMLResult, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    wsRCS_OFACSoap.busquedaIDVerificationXML(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIdv, pathProveedorIdv, identityVerificationSetupOption, metodoBusqueda, busquedaIDVerificationXMLResult, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult busquedaIDVerificationConsumoXML(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, java.lang.String primaryKey, int codError, java.lang.String descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaIDVerificationConsumoXML(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIdv, pathProveedorIdv, identityVerificationSetupOption, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFile(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaIDVScanFile(licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentificacion, identificacion, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion, telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIdv, pathProveedorIdv, identityVerificationSetupOption, metodoBusqueda, idBusquedaSF, codError, descripcionError);
  }
  
  public localhost.BusquedaCheckNamesSFResponseBusquedaCheckNamesSFResult busquedaCheckNamesSF(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckNamesSF(licenciaEmpresa, nombreABuscar, direccion, ciudad, estado, codigoPostal, pais, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, metodoBusqueda, idBusquedaSF, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaCheckNamesResponseBusquedaCheckNamesResult busquedaCheckNames(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckNames(licenciaEmpresa, nombreABuscar, direccion, ciudad, estado, codigoPostal, pais, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearch(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaAlternateSearch(licenciaEmpresa, nombreABuscar, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaCheckWireResponseBusquedaCheckWireResult busquedaCheckWire(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String nombreArchivo, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckWire(licenciaEmpresa, nombreABuscar, nombreArchivo, idPolitica, userName, perfilUsuario, valoresDefault, rutaSetupDB, rutaReportes, rutaLog, nombreLog, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public java.lang.String scanFiles(java.lang.String idPolitica, java.lang.String archivoInput, java.lang.String archivoOutput, java.lang.String formatoArchivoInput, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String archivoFormatos, java.lang.String licenciaUsuario, java.lang.String servidorBdd, java.lang.String nombreBdd, java.lang.String usuarioBdd, java.lang.String passwordBdd, java.lang.String nombreTablaBdd, java.lang.Object[] columnasTablaBdd, java.lang.String origenDatos, boolean poseeCabecera, java.lang.String tipoOrigenInf, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.scanFiles(idPolitica, archivoInput, archivoOutput, formatoArchivoInput, userName, perfilUsuario, archivoFormatos, licenciaUsuario, servidorBdd, nombreBdd, usuarioBdd, passwordBdd, nombreTablaBdd, columnasTablaBdd, origenDatos, poseeCabecera, tipoOrigenInf, codError, descripcionError);
  }
  
  public boolean procesarResultadosScanfiles(java.lang.String idBusquedaSF, java.lang.String licencia, java.lang.String idPolitica, java.lang.String usuario, java.lang.String perfilUsuario, javax.xml.rpc.holders.IntHolder codigoError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.procesarResultadosScanfiles(idBusquedaSF, licencia, idPolitica, usuario, perfilUsuario, codigoError, descError);
  }
  
  public boolean verificarStatusArchivoOutput(java.lang.String idBusquedaSF, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.verificarStatusArchivoOutput(idBusquedaSF, codError, descError);
  }
  
  public localhost.BusquedaCheckSwiftResponseBusquedaCheckSwiftResult busquedaCheckSwift(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckSwift(licenciaEmpresa, mensajeSwift, idPolitica, userName, perfilUsuario, valoresDefault, rutaLog, nombreLog, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult busquedaCheckSwiftRawBics(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String[] BICs, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckSwiftRawBics(licenciaEmpresa, mensajeSwift, BICs, idPolitica, userName, perfilUsuario, valoresDefault, rutaLog, nombreLog, metodoBusqueda, primaryKey, codError, descripcionError);
  }
  
  public boolean busquedaCheckSwiftRawBicsResul(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String[] BICs, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder numResult, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException{
    if (wsRCS_OFACSoap == null)
      _initWsRCS_OFACSoapProxy();
    return wsRCS_OFACSoap.busquedaCheckSwiftRawBicsResul(licenciaEmpresa, mensajeSwift, BICs, idPolitica, userName, perfilUsuario, valoresDefault, rutaLog, nombreLog, metodoBusqueda, primaryKey, numResult, codError, descripcionError);
  }
  
  
}