/**
 * 
 */
package com.equivida.intranet.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equivida.constant.EmpresaEnum;
import com.equivida.intranet.ctrl.SesionCtrl;

/**
 * @author Daniel Cardenas
 *
 */
public class EmpresaFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;

		SesionCtrl sesionCtrl = (SesionCtrl) request.getSession().getAttribute("sesionCtrl");

		List<EmpresaEnum> empresas = null;

		if (request.isUserInRole("CU_PERSONA_MASIVO")) {
			System.out.println("tiene rol CU_PERSONA_MASIVO");
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendRedirect(request.getContextPath() + "/pages/cargaArchivo.jsf");
			return;
		}
		
		if (sesionCtrl == null) {

			empresas = new ArrayList<EmpresaEnum>();

			if (request.isUserInRole("CU_ESPECIALISTA")) {
				System.out.println("rol CU_ESPECIALISTA");
				empresas.add(EmpresaEnum.EQUIVIDA);
			}
			if (request.isUserInRole("CU_GLOBAL")) {
				System.out.println("rol CU_GLOBAL");
				empresas.add(EmpresaEnum.COLVIDA);
			}

			System.out.println("filter empresas: " + empresas.size());

			if (empresas.size() == 0 || empresas.size() > 1) {
				System.out.println("debe seleccionar o no tiene empresa");
				HttpServletResponse response = (HttpServletResponse) servletResponse;
				response.sendRedirect(request.getContextPath() + "/pages/seleccion.jsf");
				return;
			}
		} else {

			empresas = sesionCtrl.getEmpresas();

		}

		if (empresas.size() == 0) {
			System.out.println("no tiene empresa");
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendRedirect(request.getContextPath() + "/pages/seleccion.jsf");
			return;
		}

		if (empresas.size() > 1) {
			if (sesionCtrl != null && sesionCtrl.getEmpresa() == null) {
				System.out.println("seleccione empresa");
				HttpServletResponse response = (HttpServletResponse) servletResponse;
				response.sendRedirect(request.getContextPath() + "/pages/seleccion.jsf");
				return;
			}

		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("iniciando filtro para validar Especialista/Global");
	}
}