/**
 * WsRCS_OFACSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public interface WsRCS_OFACSoap extends java.rmi.Remote {

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification.
     */
    public localhost.BusquedaIDVerificationBridgerResponseBusquedaIDVerificationBridgerResult busquedaIDVerificationBridger(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIDV, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de proporcionar información
     * sobre el estado del servicio Web
     */
    public java.lang.String probarWebService(java.lang.String rutaSetupDB) throws java.rmi.RemoteException;
    public boolean agregarAListaAceptados(java.lang.String idBusqueda, java.lang.String rutaSetupDB, java.lang.String tipoCuenta, java.lang.String numeroCuenta, int tipoDocumento, java.lang.String numeroId, java.lang.String direccion, java.lang.String ciudad, java.lang.String verificadoPor, java.lang.String division, java.lang.String comentario, java.lang.String empresa, boolean viaSF, javax.xml.rpc.holders.IntHolder codigoError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public java.lang.String politicaEstablecidaBatch(java.lang.String idPolitica, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public java.lang.String[] formatosBI(java.lang.String rutaFormatosBI, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification y CheckNames denominado BusquedaWire.
     */
    public localhost.BusquedaWireResponseBusquedaWireResult busquedaWire(java.lang.String licenciaEmpresa, java.lang.String contextoWire, java.lang.String separador, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification mediante FastCheck.
     */
    public localhost.BusquedaIDVerificationResponseBusquedaIDVerificationResult busquedaIDVerification(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * y enviar los resultados por Mail mediante FastCheck.
     */
    public localhost.RCSA_SearchListsResponseRCSA_SearchListsResult RCSA_SearchLists(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String metodoBusqueda, int idGrupoNotif, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification. (Retorna XML)
     */
    public localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult busquedaIDVerificationXML_Notification(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String metodoBusqueda, int idGrupoNotif, java.lang.String primaryKey, int codError, java.lang.String descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification. (Retorna XML)
     */
    public void busquedaIDVerificationXML(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, localhost.holders.BusquedaIDVerificationXMLResponseBusquedaIDVerificationXMLResultHolder busquedaIDVerificationXMLResult, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por ID Verification. (Retorna XML)
     */
    public localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult busquedaIDVerificationConsumoXML(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, java.lang.String primaryKey, int codError, java.lang.String descripcionError) throws java.rmi.RemoteException;
    public localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFile(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public localhost.BusquedaCheckNamesSFResponseBusquedaCheckNamesSFResult busquedaCheckNamesSF(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por CheckNames.
     */
    public localhost.BusquedaCheckNamesResponseBusquedaCheckNamesResult busquedaCheckNames(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * Alternate Search.
     */
    public localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearch(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * por Check Wire.
     */
    public localhost.BusquedaCheckWireResponseBusquedaCheckWireResult busquedaCheckWire(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String nombreArchivo, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método Scan Files
     */
    public java.lang.String scanFiles(java.lang.String idPolitica, java.lang.String archivoInput, java.lang.String archivoOutput, java.lang.String formatoArchivoInput, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String archivoFormatos, java.lang.String licenciaUsuario, java.lang.String servidorBdd, java.lang.String nombreBdd, java.lang.String usuarioBdd, java.lang.String passwordBdd, java.lang.String nombreTablaBdd, java.lang.Object[] columnasTablaBdd, java.lang.String origenDatos, boolean poseeCabecera, java.lang.String tipoOrigenInf, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Procesamiento de Resultados ScanFiles
     */
    public boolean procesarResultadosScanfiles(java.lang.String idBusquedaSF, java.lang.String licencia, java.lang.String idPolitica, java.lang.String usuario, java.lang.String perfilUsuario, javax.xml.rpc.holders.IntHolder codigoError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException;

    /**
     * Verificacion status de Archivo Output
     */
    public boolean verificarStatusArchivoOutput(java.lang.String idBusquedaSF, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * de mensajes Swift Standard.
     */
    public localhost.BusquedaCheckSwiftResponseBusquedaCheckSwiftResult busquedaCheckSwift(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * de mensajes Swift Standard.
     */
    public localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult busquedaCheckSwiftRawBics(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String[] BICs, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio Web que se encarga de realizar las búsquedas
     * de mensajes Swift Standard.
     */
    public boolean busquedaCheckSwiftRawBicsResul(java.lang.String licenciaEmpresa, java.lang.String mensajeSwift, java.lang.String[] BICs, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder numResult, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
}
