/**
 * WsRCS_OFACSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public interface WsRCS_OFACSoap extends java.rmi.Remote {

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por ID Verification.
     */
    public localhost.BusquedaIDVerificationResponseBusquedaIDVerificationResult busquedaIDVerification(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIDV, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por ID Verification.
     * 				(Retorna XML)
     */
    public localhost.BusquedaIDVerificationXMLResponseBusquedaIDVerificationXMLResult busquedaIDVerificationXML(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIDV, int identityVerificationSetupOption, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFile(java.lang.String licenciaEmpresa, java.lang.String tipoOrganizacion, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por CheckNames.
     */
    public localhost.BusquedaCheckNamesResponseBusquedaCheckNamesResult busquedaCheckNames(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public localhost.BusquedaCheckNamesSFResponseBusquedaCheckNamesSFResult busquedaCheckNamesSF(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, java.lang.String idBusquedaSF, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por Check Wire.
     */
    public localhost.BusquedaCheckWireResponseBusquedaCheckWireResult busquedaCheckWire(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String nombreArchivo, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por Check Wire.
     */
    public localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearch(java.lang.String licenciaEmpresa, java.lang.String nombreABuscar, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar cualquiera de los tipos de búsqueda
     * 				disponibles
     */
    public localhost.BusquedaInteligenteOFACResponseBusquedaInteligenteOFACResult busquedaInteligenteOFAC(java.lang.String licenciaEmpresa, java.lang.String firstName, java.lang.String middleName, java.lang.String lastName, int tipoIdentificacion, java.lang.String identificacion, java.lang.String direccion, java.lang.String ciudad, java.lang.String estado, java.lang.String codigoPostal, java.lang.String pais, java.lang.String fechaNacimiento, java.lang.String generacion, java.lang.String telfCasa, java.lang.String telfTrabajo, java.lang.String issuingCountry, java.lang.String expDate, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String valoresDefault, java.lang.String rutaSetupDB, java.lang.String rutaReportes, java.lang.String rutaLog, java.lang.String nombreLog, int proveedorIdv, java.lang.String pathProveedorIdv, int identityVerificationSetupOption, javax.xml.rpc.holders.StringHolder tipoBusquedaUsada, java.lang.String metodoBusqueda, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de proporcionar información sobre el estado
     * del
     * 				servicio Web
     */
    public java.lang.String probarWebService(java.lang.String rutaSetupDB) throws java.rmi.RemoteException;
    public boolean agregarAListaAceptados(java.lang.String idBusqueda, java.lang.String rutaSetupDB, java.lang.String tipoCuenta, java.lang.String numeroCuenta, int tipoDocumento, java.lang.String numeroId, java.lang.String direccion, java.lang.String ciudad, java.lang.String verificadoPor, java.lang.String division, java.lang.String comentario, java.lang.String empresa, boolean viaSF, javax.xml.rpc.holders.IntHolder codigoError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;

    /**
     * Método Scan Files
     */
    public java.lang.String scanFiles(java.lang.String idPolitica, java.lang.String archivoInput, java.lang.String archivoOutput, java.lang.String formatoArchivoInput, java.lang.String userName, java.lang.String perfilUsuario, java.lang.String archivoFormatos, java.lang.String licenciaUsuario, java.lang.String servidorBDD, java.lang.String nombreBDD, java.lang.String usuarioBDD, java.lang.String passwordBDD, java.lang.String nombreTablaBDD, java.lang.Object[] columnasTablaBDD, java.lang.String origenDatos, boolean poseeCabecera, java.lang.String tipoOrigenInf, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public java.lang.String politicaEstablecidaBatch(java.lang.String idPolitica, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
    public java.lang.String[] formatosBI(java.lang.String rutaFormatosBI, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descError) throws java.rmi.RemoteException;

    /**
     * Método del Servicio
     * 				Web que se encarga de realizar las búsquedas por ID Verification
     * y
     * 				CheckNames denominado BusquedaWire.
     */
    public localhost.BusquedaWireResponseBusquedaWireResult busquedaWire(java.lang.String licenciaEmpresa, java.lang.String contextoWire, java.lang.String separador, java.lang.String idPolitica, java.lang.String userName, java.lang.String perfilUsuario, javax.xml.rpc.holders.StringHolder primaryKey, javax.xml.rpc.holders.IntHolder codError, javax.xml.rpc.holders.StringHolder descripcionError) throws java.rmi.RemoteException;
}
