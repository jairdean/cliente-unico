/**
 * ActualizacionesRegularesSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface ActualizacionesRegularesSoap extends java.rmi.Remote {

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Tipos de Ramos en Pivotal.
     */
    public java.lang.String consultarMaestroTiposRamo(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Ramos en Pivotal.
     */
    public java.lang.String consultarMaestroRamos(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Coberturas en Pivotal.
     */
    public java.lang.String consultarMaestroCoberturas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Productos en Pivotal.
     */
    public java.lang.String consultarMaestroProductos(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones en las relaciones
     * de Coberturas por Productos en Pivotal.
     */
    public java.lang.String consultarProductosCoberturas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Ocupaciones en Pivotal.
     */
    public java.lang.String consultarMaestroOcupaciones(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Maestros
     * de Profesiones en Pivotal.
     */
    public java.lang.String consultarMaestroProfesiones(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de las Personas
     * en Pivotal.
     */
    public java.lang.String consultarPersonas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Motivos
     * de las Personas en Pivotal.
     */
    public java.lang.String consultarMotivosPersonas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de los Deportes
     * de las Personas en Pivotal.
     */
    public java.lang.String consultarDeportesPersonas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones de Compañías en
     * Pivotal.
     */
    public java.lang.String consultarCompanias(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones en los Maestros
     * de Actividades Económicas en Pivotal.
     */
    public java.lang.String consultarMaestroActEconomicas(java.lang.String xmlData) throws java.rmi.RemoteException;

    /**
     * Este método permite registrar modificaciones en las relaciones
     * de Actividades Económicas por Compañía en Pivotal.
     */
    public java.lang.String consultarActEconomicasCompanias(java.lang.String xmlData) throws java.rmi.RemoteException;
}
