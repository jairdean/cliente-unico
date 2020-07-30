/**
 * 
 */
package com.equivida.helper;

import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author juan
 *
 */
public class HttpHelper {

	/**
	 * Se obtiene la IP del usuario remoto.
	 * 
	 * @return
	 */
	public static String getCurrentClientIpAddress() {
		String remoteIp = "";
		try {
			HttpServletRequest request = (HttpServletRequest) PolicyContext
					.getContext("javax.servlet.http.HttpServletRequest");
			if (request != null) {
				remoteIp = request.getRemoteAddr();
			}
		} catch (PolicyContextException e) {
			System.out.println("ERROR al obtener ip:" + e);
		}
		// ServerConfigUtil.
		// String currentThreadName = Thread.currentThread().getName();
		// int begin = currentThreadName.indexOf('[') +1;
		// int end = currentThreadName.indexOf(']')-1;
		// String remoteClient = currentThreadName.substring(begin, end);
		return remoteIp;
	}
}
