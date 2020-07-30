package com.equivida.crm.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface CrmWs {

	@WebMethod
	@WebResult(name = "respuesta")
	String procesar(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarPolizas(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarBeneficiariosPoliza(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarCoberturasPoliza(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarCupones(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarPagos(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesAniversariosPolizas(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConPolizasConCondicionPago(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConPolizasConAsesorActivoInactivo(
			@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConCuponesPendientesInimputadosPagoNoDirecto();

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConConductoBancoEquivida();

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesEnPeriodoDeGracia();

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConTarjetasExpiracion2meses();

	@WebMethod
	@WebResult(name = "respuesta")
	String ClientesConCuponesConPagoDirecto();

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarPersonas(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ActualizarDatosPersona(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarCompanias(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarEstadoCuenta(@WebParam(name = "xml") String xml);

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarEmpleados();

	@WebMethod
	@WebResult(name = "respuesta")
	String ConsultarCompromisosPagos(@WebParam(name = "xml") String xml);
}
