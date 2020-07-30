package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsRegistroPresupuestoHeader;

@WebService
@SOAPBinding(style = Style.RPC)
public interface RegistroPresupuestoHeaderWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RsRegistroPresupuestoHeader llamarRegistroPresupuestoHeaderSp(
			@WebParam(name = "i_imp_id_sesion") BigDecimal i_imp_id_sesion,
			@WebParam(name = "i_imp_nro_pol") BigDecimal i_imp_nro_pol,
			@WebParam(name = "i_nro_aseg") BigDecimal i_nro_aseg,
			@WebParam(name = "i_nro_pariente") BigDecimal i_nro_pariente,
			@WebParam(name = "i_imp_tipo_identifaseg") BigDecimal i_imp_tipo_identifaseg,
			@WebParam(name = "i_txt_cedula") String i_txt_cedula,
			@WebParam(name = "i_imp_cod_plan") BigDecimal i_imp_cod_plan,
			@WebParam(name = "i_imp_cod_red") BigDecimal i_imp_cod_red,
			@WebParam(name = "i_imp_tipo_identifpres") BigDecimal i_imp_tipo_identifpres,
			@WebParam(name = "i_txt_identifpres") String i_txt_identifpres,
			@WebParam(name = "i_imp_cod_suc") BigDecimal i_imp_cod_suc,
			@WebParam(name = "i_imp_tipo_identifmdiag") BigDecimal i_imp_tipo_identifmdiag,
			@WebParam(name = "i_txt_identifmdiag") String i_txt_identifmdiag,
			@WebParam(name = "i_tipo_reg") String i_tipo_reg);

}