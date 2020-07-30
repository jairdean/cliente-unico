package com.equivida.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.dao.PersonaDao;
import com.equivida.dao.PersonaJuridicaDao;
import com.equivida.dao.RelacionDao;
import com.equivida.helper.ContratanteHelper;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Relacion;
import com.equivida.servicio.PerfilFinancieroJuridicaServicio;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PersonaJuridicaServicio")
public class PersonaJuridicaServicioImpl extends GenericServiceImpl<PersonaJuridica, Integer>
		implements PersonaJuridicaServicio {

	@EJB
	private PersonaDao personaDao;

	@EJB
	private PersonaJuridicaDao personaJuridicaDao;

	@EJB
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB
	private DireccionTelefonoDao direccionTelefonoDao;

	@EJB
	private PerfilFinancieroJuridicaServicio perfilFinancieroJuridicaServicio;

	@EJB
	private RelacionDao relacionDao;

	public GenericDao<PersonaJuridica, Integer> getDao() {
		return personaJuridicaDao;
	}

	@Override
	public PersonaJuridica findByPk(Integer secPersonaJuridica, boolean loadRepresentanteLegal, boolean loadDirecciones,
			boolean loadDireccionesConTelefonos, boolean loadPerfilFinanciero, boolean loadAccionistas) {
		PersonaJuridica personaJuridica = this.findByPk(secPersonaJuridica);

		if (loadDirecciones) {
			Collection<Direccion> dirCollection = personaJuridica.getPersona().getDireccionCollection();
			dirCollection.size();

			if (loadDireccionesConTelefonos) {
				for (Direccion direccion : dirCollection) {
					if (direccion != null) {
						String[] criteriasAnd = { "tipoParentescoRelacion.secPersona", "direccion.secDireccion" };
						CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
								CriteriaTypeEnum.INTEGER_EQUALS };
						Object[] params = new Object[] { personaJuridica.getPersona().getSecPersona(),
								direccion.getSecDireccion() };
						Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

						Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
								.findByCriterias(criteria);
						direccion.setDireccionTelefonoCollection(direccionTelefonoCollection);

					}
				}
			}

		}

		cargarIniciarPerfilFinanciero(loadPerfilFinanciero, personaJuridica);

		cargarAccionistas(loadAccionistas, personaJuridica, EstadoEnum.ACTIVO);

		return personaJuridica;
	}

	private void cargarIniciarPerfilFinanciero(boolean loadPerfilFinanciero, PersonaJuridica personaJuridica) {
		if (loadPerfilFinanciero) {
			PerfilFinancieroJuridica pfj = null;
			if (personaJuridica.getSecPersonaJuridica() != null) {
				pfj = perfilFinancieroJuridicaServicio
						.buscarPorSecPersonaJuridica(personaJuridica.getSecPersonaJuridica());
			}
			if (pfj == null) {
				pfj = new PerfilFinancieroJuridica();
				pfj.setPatrimonio(BigDecimal.ZERO);
				pfj.setEgresos(BigDecimal.ZERO);
				pfj.setUtilidad(BigDecimal.ZERO);
			}

			personaJuridica.setPerfilFinancieroJuridicaTransient(pfj);
		}
	}

	/**
	 * @param loadAccionistas
	 * @param personaJuridica
	 * @param estado          null todos los estados, A solo activos, I inactivos
	 */
	private void cargarAccionistas(boolean loadAccionistas, PersonaJuridica personaJuridica, EstadoEnum estado) {
		if (loadAccionistas) {

			List<Relacion> accLista = new ArrayList<Relacion>();
			List<Relacion> accListaFinal = new ArrayList<Relacion>();

			if (personaJuridica.getPersona().getSecPersona() != null) {
				short tipo[] = Constantes.IDS_TIPO_RELACION_ACCIONISTAS;

				accLista = relacionDao.buscarPorTipoPersonaPrincipal(tipo, personaJuridica.getPersona().getSecPersona(),
						estado);
				for (Relacion r : accLista) {
					if (r.getPersona().getTipoIdentificacion().isCedula()) {
						// entonces carga persona natural transient
						r.getPersona().setPersonaNaturalTransient(
								personaNaturalServicio.obtenerPersonaNaturalByPersona(r.getPersona().getSecPersona()));
						if (r.getPersona().getPersonaNaturalTransient() != null) {
							accListaFinal.add(r);
						}
					} else {
						PersonaJuridica relPj = this.obtenerPersonaJuridicaByPersona(r.getPersona().getSecPersona());

						r.getPersona().setPersonaNaturalTransient(new PersonaNatural());

						if (relPj != null) {

							r.getPersona().setPersonaJuridicaTransient(relPj);
							r.getPersona().getPersonaNaturalTransient()
									.setTipoIdentificacion(relPj.getTipoIdentificacion());
							r.getPersona().getPersonaNaturalTransient().setIdentificacion(relPj.getIdentificacion());
							r.getPersona().getPersonaNaturalTransient().setApellidoPaterno(relPj.getRazonSocial());

							accListaFinal.add(r);

						}
					}
				}
			}

			personaJuridica.setAccionistaListTra(accListaFinal);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.PersonaJuridicaServicio#findNoDocumento(java.lang
	 * .String, boolean, boolean, boolean)
	 */
	@Override
	public PersonaJuridica findByNoDocumento(String noDocumento, boolean loadRepresentanteLegal,
			boolean loadDirecciones, boolean loadDireccionesConTelefonos, boolean loadPerfilFinanciero,
			boolean loadAccionistas) {
		PersonaJuridica personaJuridica = personaJuridicaDao.obtenerPorNoDocumento(noDocumento);

		if (personaJuridica != null) {
			if (loadDirecciones) {
				Collection<Direccion> dirCollection = personaJuridica.getPersona().getDireccionCollection();
				dirCollection.size();
				// inicia telefonos
				if (personaJuridica.getPersona().getTelefonoCollection() != null) {
					personaJuridica.getPersona().getTelefonoCollection().size();
				}

				if (loadDireccionesConTelefonos) {
					for (Direccion direccion : dirCollection) {
						if (direccion != null) {
							String[] criteriasAnd = { "persona.secPersona", "direccion.secDireccion" };
							CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,
									CriteriaTypeEnum.INTEGER_EQUALS };
							Object[] params = new Object[] { personaJuridica.getPersona().getSecPersona(),
									direccion.getSecDireccion() };
							Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

							Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
									.findByCriterias(criteria);
							direccion.setDireccionTelefonoCollection(direccionTelefonoCollection);

						}
					}
				}

				// inicializa celular
				ContratanteHelper.identificarCelular(personaJuridica.getPersona());

			}

			// Cargamos relaciones

			// representante legal
			List<Relacion> representantes = relacionDao.buscarPorTipoPersonaPrincipal(
					Constantes.TIPO_RELACION_REP_LEGAL, personaJuridica.getPersona().getSecPersona(),
					EstadoEnum.ACTIVO);

			if (representantes != null && !representantes.isEmpty()) {

				Relacion representante = representantes.get(0);

				PersonaNatural p = personaNaturalServicio
						.obtenerPersonaNaturalByPersonaFormulario(representante.getPersona().getSecPersona());

				representante.getPersona().setPersonaNaturalTransient(p);

				// conyuge
				Persona conyuge = personaNaturalServicio.obtenerConyuge(p.getPersona().getSecPersona());
				if (conyuge != null) {

					PersonaNatural pnconyuge = personaNaturalServicio
							.obtenerPersonaNaturalByPersona(conyuge.getSecPersona());
					conyuge.setPersonaNaturalTransient(pnconyuge);

					// si esta guardado en persona natural
					if (conyuge.getPersonaNaturalTransient() != null
							&& conyuge.getPersonaNaturalTransient().getSecPersonaNatural() != null) {
						p.setConyuge(conyuge.getPersonaNaturalTransient());
					} else {
						// si solo esta en persona
						PersonaNatural pnC = new PersonaNatural();
						pnC.setPersona(conyuge);
						pnC.setTipoIdentificacion(conyuge.getTipoIdentificacion());
						p.setConyuge(pnC);
					}
				} else {

					p.setConyuge(null);
					p.setTieneConyuge(false);
				}

				personaJuridica.setRepresentante(representante.getPersona());
			} else {
				ContratanteHelper.inicializarRepresentanteLegal(personaJuridica);
			}

			cargarIniciarPerfilFinanciero(loadPerfilFinanciero, personaJuridica);

			cargarAccionistas(loadAccionistas, personaJuridica, EstadoEnum.ACTIVO);

		}

		return personaJuridica;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.servicio.PersonaJuridicaServicio#crearPersonaJuridica(com
	 * .equivida.modelo.Persona, com.equivida.modelo.PersonaJuridica)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void crearPersonaJuridica(Persona persona, PersonaJuridica personaJuridica) {
		// 1. Persiste persona
		personaDao.save(persona);

		// 2. Persiste persona juridica
		personaJuridicaDao.save(personaJuridica);
	}

	@Override
	public void persitir(PersonaJuridica personaJuridica) {
		if (personaJuridica.getSecPersonaJuridica() == null) {
			create(personaJuridica);
		} else {
			update(personaJuridica);
		}

	}

	@Override
	public PersonaJuridica obtenerPersonaJuridicaByPersona(Integer secPersona) {

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<PersonaJuridica> lista = personaJuridicaDao.findByCriterias(criteria);

		PersonaJuridica personaJuridica = null;

		if (lista.size() >= 1) {
			personaJuridica = lista.get(0);
			if (lista.size() > 1) {
				System.out.println("WARN: existe mas una persona natural atada a una persona ");
			}
		}

		return personaJuridica;
	}
}