/* 
 * BaseCtrl.java
 * Copyright 2011 Saviasoft Cia. Ltda. 
 */
package com.equivida.intranet.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;

/**
 * @author Juan Ochoa
 * 
 */
public class BaseCtrl implements Serializable {

	private static final long serialVersionUID = 480593854785627958L;

	private String locale;

	public static final Locale DEFAULT_LOCALE = new Locale("es", "EC");

	protected String getHttpServletRequestParam(String paramName) {
		HttpServletRequest r = ((HttpServletRequest) getExternalContext().getRequest());

		return r.getParameter(paramName);
	}

	/**
	 * Returns servlet context context from faces context.
	 * 
	 * @return
	 */
	protected ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}

	/**
	 * Returns Jsf actual instance
	 * 
	 * @return
	 */
	protected FacesContext getFacesContext() {
		return (FacesContext.getCurrentInstance());
	}

	/**
	 * Returns External Context from actual Faces context
	 * 
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * Returns application request
	 * 
	 * @return
	 */
	protected HttpServletRequest getHttpServletRequest() {
		return ((HttpServletRequest) getExternalContext().getRequest());
	}

	/**
	 * Returns application session
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * Returns ServletResponse from external context
	 * 
	 * @return
	 */
	protected HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * Returns application context
	 * 
	 * @return
	 */
	protected String getContextPath() {
		return getHttpServletRequest().getContextPath();
	}

	/**
	 * Devulve el usuario que esta logeado.
	 * 
	 * @return
	 */
	protected String getRemoteUser() {
		return getExternalContext().getRemoteUser();
	}

	/**
	 * Obtiene la fecha actual.
	 * 
	 * @return
	 */
	protected String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar now = new GregorianCalendar();

		return sdf.format(now.getTime());
	}

	public String getFormatDateForView() {
		return "dd MMM yyyy";
	}

	public String getFormatDateForForm() {
		return "dd/MM/yyyy";
	}

	public String getFormatDateForFormWithHours() {
		return "dd/MM/yyyy HH:mm";
	}

	/**
	 * Obtiene la fecha actual.
	 * 
	 * @return
	 */
	public Date getCurrentDateObj() {
		Calendar now = new GregorianCalendar();

		return now.getTime();
	}

	/**
	 * Params se envia null si no hay parametros
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	protected String getBundleMensajes(String key, Object params[]) {
		Locale locale = getFacesContext().getViewRoot().getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle("com.equivida.intranet.recursos.mensajes", locale,
				getCurrentClassLoader(params));

		String mensaje = bundle.getString(key);

		if (params != null && params.length > 0) {
			MessageFormat mf = new MessageFormat(mensaje, locale);
			mensaje = mf.format(params, new StringBuffer(), null).toString();
		}

		return mensaje;
	}

	/**
	 * Params se envia null si no hay parametros
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	protected String getBundleEtiquetas(String key, Object params[]) {

		Locale locale = getFacesContext().getViewRoot().getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle("com.equivida.intranet.recursos.etiquetas", locale,
				getCurrentClassLoader(params));

		String mensaje = bundle.getString(key);

		if (params != null && params.length > 0) {
			MessageFormat mf = new MessageFormat(mensaje, locale);
			mensaje = mf.format(params, new StringBuffer(), null).toString();
		}

		return mensaje;
	}

	/**
	 * @param defaultObject
	 * @return
	 */
	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}

		return loader;
	}

	/**
	 * Returns true if a user have an specific role
	 * 
	 * @param role
	 * @return
	 */
	protected boolean isUserInRole(String role) {
		return getHttpServletRequest().isUserInRole(role);
	}

	public String getCurrentYear() {
		return DateUtils.getCurrentYear();
	}

	/**
	 * Agrega un mensaje de error para mostrarlo en pantalla.
	 * 
	 * @param componentId - null si se quiere mensaje global
	 * @param summary
	 * @param detail
	 */
	protected static void addErrorMessage(String componentId, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(componentId, facesMsg);
	}

	/**
	 * Agrega el mensaje de informacion para mostrarlo en pantalla.
	 * 
	 * @param summary the summary
	 * @param detail  the detail
	 */
	protected static void addInfoMessage(String componentId, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(componentId, facesMsg);
	}

	/**
	 * Gets the default time zone.
	 * 
	 * @return the default time zone
	 */
	protected String getDefaultTimeZone() {
		return TimeZone.getDefault().getID();
	}

	/**
	 * Gets the default locale.
	 * 
	 * @return the default locale
	 */
	protected Locale getDefaultLocale() {
		return DEFAULT_LOCALE;
	}

	public String getLocale() {
		if (locale == null) {
			locale = "es";
			Locale l = new Locale(locale);
			getFacesContext().getViewRoot().setLocale(l);
		}
		return locale;
	}

	public char getFiltroTodos() {
		return Constantes.FILTRO_TODOS;
	}

	public char getEstadoActivoCodigo() {
		return EstadoEnum.ACTIVO.getCodigo();
	}

	public char getRespuestaSiCodigo() {
		return RespuestaEnum.SI.getCodigo();
	}
}