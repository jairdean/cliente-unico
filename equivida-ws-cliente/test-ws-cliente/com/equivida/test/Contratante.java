package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.ContratanteDto;
import com.equivida.sise.ws.client.ContratantesWs;
import com.equivida.sise.ws.client.RsContratante;
import com.equivida.sise.ws.client.impl.ContratanteWsImplServiceLocator;

public class Contratante {
	public static void main(String[] args) throws ServiceException, RemoteException {
		System.out.println("-----------------------1");
		String address = "http://localhost:8080/sise-servicio-1.0/ContratanteWsImpl";
		URL url;
		try {
			url = new URL(address);

			System.out.println("-----------------------2");
			ContratanteWsImplServiceLocator locator = new ContratanteWsImplServiceLocator();
			ContratantesWs servicio = locator.getContratanteWsImplPort(url);

			System.out.println("-----------------------3");
			System.out.println("-----------------------4");

			ContratanteDto contratanteData = armarJuridica();
			//ContratanteDto contratanteData = armarNatural();

			System.out.println("=================================ANTES DE CONSUMIR");

			RsContratante response = servicio.ws_sise_sp_contratante(contratanteData);

			System.out.println("=================================CONSUMIDO");

			System.out.println("Id persona:" + response.getId_persona_wkf());
			System.out.println("Id proceso:" + response.getId_proceso_wkf());
			System.out.println("txt_personna:" + response.getTxt_id_persona_wkf());
			System.out.println("cod error:" + response.getCod_error());
			System.out.println("mensaje:" + response.getMessage_wkf());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ContratanteDto armarJuridica() {
		ContratanteDto contratanteData = new ContratanteDto();
		contratanteData.setTxt_apellido1("DESARROLLO DE APLICACIONES EMPRESARIALES SAVIASOFT CIA. LTDA.");
		contratanteData.setCod_tipo_doc(BigDecimal.valueOf(2));
		//contratanteData.setNro_doc("1712715497");
		contratanteData.setCod_tipo_iva(BigDecimal.ONE);
		contratanteData.setNro_nit("1792138361001");
		contratanteData.setTxt_sexo("M");
		contratanteData.setCod_est_civil(new BigDecimal("1"));
		contratanteData.setCod_tipo_persona("J");
		contratanteData.setCod_tipo_soc(BigDecimal.ONE);
		contratanteData.setCod_sector_mercado(BigDecimal.valueOf(9));
		contratanteData.setCod_tipo_empresa(BigDecimal.ONE);
		contratanteData.setCnt_tiempo_mercado(BigDecimal.valueOf(11));
		contratanteData.setTxt_nombres_rep_legal("DANIEL ESTEBAN");
		contratanteData.setTxt_apellidos_rep_legal("CARDENAS ZURITA");
		contratanteData.setCod_tipo_doc_rep_legal(BigDecimal.ONE);
		contratanteData.setNro_doc_rep_legal("1713470126");
		contratanteData.setIngresos_anuales(BigDecimal.valueOf(2));
		contratanteData.setCod_moneda_ing(BigDecimal.ONE);
		contratanteData.setFec_corte_ing("30/08/2019");
		contratanteData.setImp_total_activos(BigDecimal.valueOf(4));
		contratanteData.setImp_total_pasivos(BigDecimal.valueOf(5));
		contratanteData.setImp_prom_ing_mensual("0");
		contratanteData.setCod_actividad("035003005");//387
		//contratanteData.setCod_actividad("387");
		contratanteData.setSn_pep("0");
		contratanteData.setSn_relacion_laboral_pep("0");
		contratanteData.setCod_ingreso_pm(new BigDecimal("5"));
		return contratanteData;
	}
	
	public static ContratanteDto armarNatural() {
		ContratanteDto contratanteData = new ContratanteDto();
		contratanteData.setTxt_apellido1("OCHOA");
		contratanteData.setTxt_apellido2("VERA");
		contratanteData.setTxt_nombre("JUAN FRANCISCO");
		contratanteData.setCod_tipo_doc(BigDecimal.ONE);
		contratanteData.setNro_doc("1712715497");
		contratanteData.setCod_tipo_iva(BigDecimal.ONE);
		contratanteData.setNro_nit("1712715497001");
		contratanteData.setFec_nac("16/04/1979");
		contratanteData.setTxt_lugar_nac("QUITO");
		contratanteData.setTxt_sexo("M");
		contratanteData.setCod_est_civil(new BigDecimal("3"));
		contratanteData.setCod_tipo_persona("J");
		contratanteData.setImp_prom_ing_mensual("112.00");
		contratanteData.setCod_actividad("387");
		contratanteData.setCod_ingreso_pm(new BigDecimal("3"));
		contratanteData.setCod_tipo_soc(BigDecimal.ONE);
		contratanteData.setCod_sector_mercado(BigDecimal.ONE);
		return contratanteData;
	}
}
