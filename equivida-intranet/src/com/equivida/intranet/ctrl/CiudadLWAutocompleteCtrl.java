package com.equivida.intranet.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

import com.equivida.modelo.Ciudad;
import com.equivida.servicio.CiudadServicio;
import com.equivida.util.LWResources;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

@ManagedBean(name = "ciudadLWAutocompleteCtrl")
@SessionScoped
public class CiudadLWAutocompleteCtrl {

	private HashSet<String> ciudadConLWLista;
	
	private String jsonCiudades;
	
	private Short pais;//pais seleccionado
	
	public void cargarCiudades(){
		getJsonCiudades();
	}

	public String getJsonCiudades() {
		if (jsonCiudades == null) {
			String webHandlerCiudades = LWResources
					.getString("ciudades.webhandler"); //$NON-NLS-1$

			URL url;
			try {
				jsonCiudades = ""; //$NON-NLS-1$

				url = new URL(webHandlerCiudades);

				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				urlConn.setConnectTimeout(6000);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConn.getInputStream()));

				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					jsonCiudades += inputLine;
				}

				in.close();
			} catch (java.net.UnknownHostException e) {
				System.out
						.println("Error Connection Host: no puede cargar lista de ciudaddes de LW" //$NON-NLS-1$
								+ e);
				jsonCiudades="[]";
			} catch (SocketTimeoutException e) {
				System.out
						.println("Error Connection: no puede cargar lista de ciudaddes de LW" //$NON-NLS-1$
								+ e);
				jsonCiudades="[]";
			} catch (MalformedURLException e) {
				System.out
						.println("Error1: no puede cargar lista de ciudaddes de LW" //$NON-NLS-1$
								+ e);
				jsonCiudades="[]";
			} catch (IOException e) {
				System.out
						.println("Error2: no puede cargar lista de ciudaddes de LW" //$NON-NLS-1$
								+ e);
				jsonCiudades="[]";
			}
		}
		return jsonCiudades;
	}

	public void setJsonCiudades(String jsonCiudades) {
		this.jsonCiudades = jsonCiudades;
	}

	public HashSet<String> getCiudadConLWLista(short codPais,
			CiudadServicio ciudadServicio) {
		if (ciudadConLWLista == null || pais != codPais) {
			pais = codPais;
			String j = getJsonCiudades();

			ciudadConLWLista = new HashSet<String>();
			if (!j.equals("")) {

				try {
					JSONArray jsonArray = new JSONArray(j);
					int length = jsonArray.length();
					for (int cont = 0; cont < length; cont++) {
						JSONObject obj = jsonArray.getJSONObject(cont);
						String c = obj.get("Name").toString();
						ciudadConLWLista.add(c);
					}

				} catch (JSONException e) {
					// j="";
				}

			}

			// si no cargo nada en LW entonces carga de la bdd de la tabla
			// ciudad
			if (ciudadConLWLista.size() == 0) {

				// ahora se obtienen los datos de la bdd
				String[] criteriasAnd = { "pais.codPais" };
				CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS };
				Object[] params = new Object[] { codPais };
				Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

				List<Ciudad> respC = ciudadServicio.findByCriterias(criteria);

				for (Ciudad ciudad : respC) {
					ciudadConLWLista.add(ciudad.getCiudad());
				}

			}

		}
		return ciudadConLWLista;
	}

	public void setCiudadConLWLista(HashSet<String> ciudadConLWLista) {
		this.ciudadConLWLista = ciudadConLWLista;
	}
}
