/**
 * 
 */
package com.equivida.buenviaje.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.buenviaje.dto.Catalogo;
import com.equivida.buenviaje.dto.RespuestaCatalogo;
import com.equivida.dao.CantonDao;
import com.equivida.dao.EstadoCivilDao;
import com.equivida.dao.PaisDao;
import com.equivida.dao.ParroquiaDao;
import com.equivida.dao.ProvinciaDao;
import com.equivida.dao.TipoIdentificacionDao;
import com.equivida.modelo.Canton;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Parroquia;
import com.equivida.modelo.Provincia;
import com.equivida.modelo.TipoIdentificacion;

/**
 * @author juan
 *
 */
@WebService
@Stateless(name = "CatalogoServicio")
public class CatalogoServicioImpl implements CatalogoServicio,
		CatalogoServicioRemoto {

	@EJB(mappedName = "PaisDao/local")
	private PaisDao paisDao;

	@EJB(mappedName = "ProvinciaDao/local")
	private ProvinciaDao provinciaDao;

	@EJB(mappedName = "CantonDao/local")
	private CantonDao cantonDao;

	@EJB(mappedName = "ParroquiaDao/local")
	private ParroquiaDao parroquiaDao;

	@EJB(mappedName = "EstadoCivilDao/local")
	private EstadoCivilDao estadoCivilDao;

	@EJB(mappedName = "TipoIdentificacionDao/local")
	private TipoIdentificacionDao tipoIdentificacionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.buenviaje.ws.CatalogoServicio#consultarPaises()
	 */
	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarPaises() {
		List<Pais> paisList = paisDao.getAllNativeQuery();
		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (Pais p : paisList) {
			catalogoLista.add(new Catalogo(p.getCodPais().toString(), p
					.getPais()));
		}

		return armarRespuesta(catalogoLista, "pais");
	}

	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarProvincias(
			@WebParam(name = "codigoPais") String codigoPais) {
		List<Provincia> provinciaLista = provinciaDao
				.getByPaisNativeQuery(new Short(codigoPais));
		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (Provincia p : provinciaLista) {
			catalogoLista.add(new Catalogo(p.getSecProvincia().toString(), p
					.getProvincia()));
		}

		return armarRespuesta(catalogoLista, "provincia");
	}

	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarCantones(
			@WebParam(name = "codigoProvincia") String codigoProvincia) {
		List<Canton> cantonLista = cantonDao
				.getByProvinciaNativeQuery(new Short(codigoProvincia));
		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (Canton c : cantonLista) {
			catalogoLista.add(new Catalogo(c.getSecCanton().toString(), c
					.getCanton()));
		}

		return armarRespuesta(catalogoLista, "canton");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.buenviaje.ws.CatalogoServicio#consultarParroquias(java.lang
	 * .Short)
	 */
	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarParroquias(
			@WebParam(name = "codigoCanton") String codigoCanton) {
		List<Parroquia> parroquiaLista = parroquiaDao
				.getByCantonNativeQuery(new Short(codigoCanton));

		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (Parroquia p : parroquiaLista) {
			catalogoLista.add(new Catalogo(p.getSecParroquia().toString(), p
					.getParroquia()));
		}

		return armarRespuesta(catalogoLista, "parroquia");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.buenviaje.ws.CatalogoServicio#consultarEstadosCiviles()
	 */
	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarEstadosCiviles() {
		List<EstadoCivil> estadoCivilLista = estadoCivilDao.getAllNativeQuery();

		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (EstadoCivil e : estadoCivilLista) {
			catalogoLista.add(new Catalogo(e.getCodEstadoCivil().toString(), e
					.getEstadoCivil()));
		}

		return armarRespuesta(catalogoLista, "estado-civil");
	}

	@Override
	@WebMethod
	public @WebResult(name = "catalogos") RespuestaCatalogo consultarTiposIdentificacion() {
		List<TipoIdentificacion> tipoIdentificacionLista = tipoIdentificacionDao
				.getAllNativeQuery();

		List<Catalogo> catalogoLista = new ArrayList<Catalogo>();

		for (TipoIdentificacion t : tipoIdentificacionLista) {
			catalogoLista.add(new Catalogo(t.getCodTipoIdentificacion()
					.toString(), t.getTipoIdentificacion()));
		}

		return armarRespuesta(catalogoLista, "tipo-identificacion");
	}

	/**
	 * Arma la respuesta del catalogo.
	 * 
	 * @param catalogoLista
	 * @param tipo
	 * @return
	 */
	private RespuestaCatalogo armarRespuesta(List<Catalogo> catalogoLista,
			String tipo) {
		return new RespuestaCatalogo(catalogoLista, tipo);
	}
}
