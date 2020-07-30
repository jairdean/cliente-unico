package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsAsientoDiarioMayorDepreciacionMensual;


@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface AsientoDiarioMayorDepreciacionMensualWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsAsientoDiarioMayorDepreciacionMensual> llamarAsientoDiarioMayorDepreciacionMensualSp(
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "clave")	String clave, 
			@WebParam(name = "empCodigo") Integer empCodigo,
			@WebParam(name = "anio")	Integer anio, 
			@WebParam(name = "perCodigo") 	Integer perCodigo,
			@WebParam(name = "idAsientos")Integer idAsientos,
			@WebParam(name = "secuencia") Integer secuencia,
			@WebParam(name = "snMcb") 	Integer snMcb,
			@WebParam(name = "codSistema") BigDecimal codSistema,
			@WebParam(name = "codDestino") BigDecimal codDestino,
			@WebParam(name = "nroAsiento") BigDecimal nroAsiento,
			@WebParam(name = "codCtaCb") BigDecimal codCtaCb,
			@WebParam(name = "codDebCred") String codDebCred,
			@WebParam(name = "codAnalisis") BigDecimal codAnalisis,
			@WebParam(name = "codConcepto") BigDecimal codConcepto,
			@WebParam(name = "codMoneda") BigDecimal codMoneda,
			@WebParam(name = "impMo") BigDecimal impMo,
			@WebParam(name = "impEq") BigDecimal impEq,
			@WebParam(name = "impCambio") BigDecimal impCambio,
			@WebParam(name = "codSuc") BigDecimal codSuc,
			@WebParam(name = "nroCorrelaAnalisis") BigDecimal nroCorrelaAnalisis,
			@WebParam(name = "codCCosto") BigDecimal codCCosto,
			@WebParam(name = "txtDesc") String txtDesc,
			@WebParam(name = "fecAnticuacion") 	String fecAnticuacion,
			@WebParam(name = "codClaveConcepto") String codClaveConcepto,
			@WebParam(name = "fecMov") String fecMov,
			@WebParam(name = "codTipoRegistro") String codTipoRegistro);
			
}