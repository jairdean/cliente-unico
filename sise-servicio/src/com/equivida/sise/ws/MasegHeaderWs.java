package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.MasegHeader;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MasegHeaderWs {

	MasegHeader ws_sise_insert_maseg_header_edm(
			@WebParam(name = "id_persona") BigDecimal id_persona,
			@WebParam(name = "cod_figura_aseg") BigDecimal cod_figura_aseg,
			@WebParam(name = "cod_tipo_aseg") BigDecimal cod_tipo_aseg,
			@WebParam(name = "cod_imp_aseg") BigDecimal cod_imp_aseg,
			@WebParam(name = "cod_tipo_agente") BigDecimal cod_tipo_agente,
			@WebParam(name = "cod_agente") BigDecimal cod_agente,
			@WebParam(name = "fec_alta") String fec_alta,
			@WebParam(name = "fec_baja") String fec_baja,
			@WebParam(name = "cod_ocupacion") BigDecimal cod_ocupacion,
			@WebParam(name = "sn_aviso_vto") Integer sn_aviso_vto,
			@WebParam(name = "cod_aseg_vinc") String cod_aseg_vinc,
			@WebParam(name = "fec_ult_modif") String fec_ult_modif,
			@WebParam(name = "cod_usuario") String cod_usuario,
			@WebParam(name = "txt_nom_factura") String txt_nom_factura,
			@WebParam(name = "pje_tasa_fianzas") BigDecimal pje_tasa_fianzas,
			@WebParam(name = "sn_consorcio") Integer sn_consorcio,
			@WebParam(name = "cod_moneda") BigDecimal cod_moneda,
			@WebParam(name = "imp_sueldo") BigDecimal imp_sueldo,
			@WebParam(name = "cod_deporte") BigDecimal cod_deporte,
			@WebParam(name = "txt_edificio") String txt_edificio,
			@WebParam(name = "txt_urbanizacion") String txt_urbanizacion,
			@WebParam(name = "txt_sector") String txt_sector,
			@WebParam(name = "txt_nombres_conyugue") String txt_nombres_conyugue,
			@WebParam(name = "txt_apellido_conyugue") String txt_apellido_conyugue,
			@WebParam(name = "cod_tipo_doc_conyugue") BigDecimal cod_tipo_doc_conyugue,
			@WebParam(name = "nro_doc_conyugue") String nro_doc_conyugue,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}