package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsAsientoDiario;

@WebService
@SOAPBinding(style = Style.RPC)
public interface AsientoDiarioWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsAsientoDiario> llamarAsientoDiarioSp(

	@WebParam(name = "empCodigo") Integer empCodigo,
			@WebParam(name = "nroFactura") String nroFactura,
			@WebParam(name = "codigoProveedor") Integer codigoProveedor,
			@WebParam(name = "tipo") Integer tipo,
			@WebParam(name = "codSuc") Integer codSuc,
			@WebParam(name = "codCtaCb") String codCtaCb,
			@WebParam(name = "codDebCred") String codDebCred,
			@WebParam(name = "impMo") BigDecimal impMo,
			@WebParam(name = "impEq") BigDecimal impEq,
			@WebParam(name = "codMoneda") BigDecimal codMoneda,
			@WebParam(name = "impCambio") BigDecimal impCambio,
			@WebParam(name = "codAnalisis") Integer codAnalisis,
			@WebParam(name = "codConcepto") BigDecimal codConcepto,
			@WebParam(name = "codClaveConcepto") String codClaveConcepto,
			@WebParam(name = "txtDesc") String txtDesc,
			@WebParam(name = "fecha") String fecha,
			@WebParam(name = "codValor") BigDecimal codValor,
			@WebParam(name = "numValor") BigDecimal numValor,
			@WebParam(name = "codCCosto") BigDecimal codCCosto,
			@WebParam(name = "usrCodigo") Integer usrCodigo,
			@WebParam(name = "secuencia") Integer secuencia,
			@WebParam(name = "codTipoRegistro") String codTipoRegistro);

}