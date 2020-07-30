package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsIngresoCitas;
import com.equivida.sise.sp.IngresoCitasSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class IngresCitasSpTest {

	static IngresoCitasSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (IngresoCitasSpRemoto) ContextUtil
				.lookup("java:IngresoCitasSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal i_imp_modo = BigDecimal.valueOf(3);
		BigDecimal i_imp_tipo_doc = BigDecimal.valueOf(1);
		String i_txt_nro_ced = "";
		BigDecimal i_imp_cod_aseg = BigDecimal.valueOf(0);
		String i_txt_apellido1 = "LOPEZ";
		String i_txt_apellido2 = "LOPEZ";

		
		try {
			List<RsIngresoCitas> lista= remoto.llamarIngresoCitasSp(i_imp_modo, i_imp_tipo_doc,
					i_txt_nro_ced, i_imp_cod_aseg, i_txt_apellido1,
					i_txt_apellido2);
			
			System.out.println("TOTAL"+lista.size());
			
			for (RsIngresoCitas respuesta : lista) {
				System.out.println("resp: " + respuesta.getAtendido());
				System.out.println(respuesta.getNumPoliza());
				System.out.println(respuesta.getNombreContratante());
				System.out.println(respuesta.getCodTipoDocumento());
				System.out.println(respuesta.getNumDocumento());
				System.out.println(respuesta.getPrimerApellido());
				System.out.println(respuesta.getSegundoApellido());
				System.out.println(respuesta.getNombreAsegurado());
				System.out.println(respuesta.getNumAsegurado());
				System.out.println(respuesta.getNumPariente());
				System.out.println(respuesta.getPlan());
				System.out.println(respuesta.getCodPlan());
				System.out.println(respuesta.getRed());
				System.out.println(respuesta.getCodRed());
				System.out.println(respuesta.getFechaBaja());
				System.out.println(respuesta.getTitular());
				System.out.println(respuesta.getMensaje());
			}

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
