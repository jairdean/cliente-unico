package com.equivida.servicio.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.equivida.constant.Constantes;
import com.equivida.constant.EsClienteEnum;
import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.constant.TipoEmpleoEnum;
import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.dao.ContratanteDao;
import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.dto.ContratanteListaDto;
import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.dto.RespuestaSiseDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.RazonSocialException;
import com.equivida.exception.RcsException;
import com.equivida.helper.AuditoriaHelper;
import com.equivida.helper.ContratanteHelper;
import com.equivida.helper.ContratantePJHelper;
import com.equivida.helper.ContratantePNHelper;
import com.equivida.helper.HttpHelper;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Beneficiario;
import com.equivida.modelo.Canton;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.EstadoCivil;
import com.equivida.modelo.Ocupacion;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.PersonaPe;
import com.equivida.modelo.Profesion;
import com.equivida.modelo.Relacion;
import com.equivida.modelo.RucPersonaNatural;
import com.equivida.modelo.SectorMercado;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoContactoPreferido;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.modelo.TipoRiesgo;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.ActividadEconomicaServicio;
import com.equivida.servicio.BeneficiarioServicio;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.ConsultaGeneralCUServicio;
import com.equivida.servicio.ContactoPreferidoServicio;
import com.equivida.servicio.ContratanteServicio;
import com.equivida.servicio.DireccionElectronicaServicio;
import com.equivida.servicio.DireccionServicio;
import com.equivida.servicio.DireccionTelefonoServicio;
import com.equivida.servicio.EmpleoDependienteServicio;
import com.equivida.servicio.EmpleoIndependienteServicio;
import com.equivida.servicio.IngresoMensualAdicionalServicio;
import com.equivida.servicio.IngresoMensualServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.PerfilFinancieroJuridicaServicio;
import com.equivida.servicio.PerfilFinancieroNaturalServicio;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.PersonaServicio;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.RelacionServicio;
import com.equivida.servicio.RucPersonaNaturalServicio;
import com.equivida.servicio.SiseServicio;
import com.equivida.servicio.TelefonoServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.util.Criteria;

/**
 * @author Juan Ochoa
 * @author Daniel Cardenas
 */
@Stateless(name = "ContratanteServicio")
public class ContratanteServicioImpl implements ContratanteServicio {

	private Logger LOG = Logger.getLogger(ContratanteServicioImpl.class);

	@EJB
	private PersonaJuridicaServicio personaJuridicaServicio;
	@EJB
	private TipoIdentificacionServicio tipoIdentificacionServicio;
	@EJB
	private PersonaNaturalServicio personaNaturalServicio;
	@EJB
	private BeneficiarioServicio beneficiarioServicio;
	@EJB
	private RucPersonaNaturalServicio rucPersonaNaturalServicio;
	@EJB
	private PerfilFinancieroNaturalServicio perfilFinancieroNaturalServicio;
	@EJB
	private DireccionElectronicaServicio direccionElectronicaServicio;
	@EJB
	private RcsServicio rcsServicio;
	@EJB
	private PersonaServicio personaServicio;
	@EJB
	private DireccionServicio direccionServicio;
	@EJB
	private TelefonoServicio telefonoServicio;
	@EJB
	private IngresoMensualServicio ingresoMensualServicio;
	@EJB
	private IngresoMensualAdicionalServicio ingresoMensualAdicionalServicio;
	@EJB
	private EmpleoDependienteServicio empleoDependienteServicio;
	@EJB
	private EmpleoIndependienteServicio empleoIndependienteServicio;
	@EJB
	private ConsultaGeneralCUServicio consultaGeneralCUServicio;
	@EJB
	private CantonServicio cantonServicio;
	@EJB
	private PaisServicio paisServicio;
	@EJB
	private DireccionTelefonoServicio direccionTelefonoServicio;
	@EJB
	private WsDatosPersonaServicio wsDatosPersonaServicio;
	@EJB
	private SiseServicio siseServicio;
	@EJB
	private ContactoPreferidoServicio contactoPreferidoServicio;
	@EJB
	private DireccionTelefonoDao direccionTelefonoDao;
	@EJB
	private ContratanteDao contratanteDao;
	@EJB
	private ActividadEconomicaServicio actividadEconomicaServicio;
	@EJB
	private RelacionServicio relacionServicio;
	@EJB
	private PerfilFinancieroJuridicaServicio perfilFinancieroJuridicaServicio;

	@EJB(mappedName = "PersonaEquividaServicio/local")
	private PersonaEquividaServicio personaEquividaServicio;
	@Resource
	private SessionContext sessionContext;// para auditoria

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.ContratanteServicio#verificarExistenciaPN(java.lang.
	 * String)
	 */
	@Override
	public PersonaNatural verificarExistenciaPN(String identificacion) {
		PersonaNatural pn = null;

		// 3. Se obtienen los 10 primeros digitos de la identificacion
		String numeroDocumento = identificacion.substring(0, 10);

		// Se busca como persona natural
		PersonaNatural personaNatural = personaNaturalServicio.obtenerPersonaNaturalByDocumento(numeroDocumento, true);

		if (personaNatural != null && personaNatural.getSecPersonaNatural() != null) {
			// Se inicializan relaciones que no estan en el metodo
			if (personaNatural.getIngresoMensualAdicionalCollection() != null) {
				personaNatural.getIngresoMensualAdicionalCollection().size();
			}

			if (personaNatural.getEstadoPersonaCollection() != null) {
				personaNatural.getEstadoPersonaCollection().size();
			}

			if (personaNatural.getDeportePracticaCollection() != null) {
				personaNatural.getDeportePracticaCollection().size();
			}

			// Se inicializa datos de PJ en base de datos de PN.
			pn = personaNatural;

			// Se inicializan los valores de la persona natural
			inicializarPersonaNaturalContratante(pn, identificacion);
		} else {
			// Se busca como beneficiario
			Beneficiario beneficiario = beneficiarioServicio.buscarPorNoDocumento(numeroDocumento);

			if (beneficiario != null && beneficiario.getSecBeneficiario() != null) {
				// Se inicializan datos de PN con datos de Beneficiario
				pn = new PersonaNatural();

				// Se inicializa con datos vacios
				ContratanteHelper.inicializarPersonaNaturalContratante(pn, identificacion, tipoIdentificacionServicio);

				// Se llenan los datos del beneficiario en la PN
				ContratanteHelper.inicializarPersonaNaturalDeBeneficiario(pn, beneficiario);
			} else {
				// Se busca en enriquecimiento
				RespuestaGeneralDto respuestaDto = consultaGeneralCUServicio.consultaGenerica(numeroDocumento,
						getRemoteUser(), identificacion);

				// Se inicializan datos de PN con datos de enriquecimiento
				pn = new PersonaNatural();
				ContratanteHelper.inicializarDeEnriquecimientoContrantante(pn, respuestaDto, identificacion,
						getRemoteUser(), direccionElectronicaServicio, tipoIdentificacionServicio);
			}
		}

		// Si no se encuentra en ninguna parte, se inicializa los datos de la persona
		// natural.
		if (pn == null || pn.getIdentificacion() == null || pn.getIdentificacion().trim().length() <= 0) {
			pn = new PersonaNatural();
			ContratanteHelper.inicializarPersonaNaturalContratante(pn, identificacion, tipoIdentificacionServicio);
		}

		return pn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.ContratanteServicio#verificarExistenciaPN(java.lang.
	 * Integer, java.lang.String)
	 */
	@Override
	public PersonaNatural verificarExistenciaPN(Integer secPersonaNatural, String ruc) {
		// Se busca como persona natural
		PersonaNatural pn = personaNaturalServicio.findByPkParaFormularioWeb(secPersonaNatural);
		if (pn.getPersona().getDireccionCollection() != null) {
			pn.getPersona().getDireccionCollection().size();
		}

		if (pn.getPersona().getTelefonoCollection() != null) {
			pn.getPersona().getTelefonoCollection().size();
		}

		if (pn.getActividadCollection() != null) {
			pn.getActividadCollection().size();
		}

		if (pn.getEmpleoDependienteCollection() != null) {
			pn.getEmpleoDependienteCollection().size();
		}

		if (pn.getEmpleoIndependienteCollection() != null) {
			pn.getEmpleoIndependienteCollection().size();
		}

		if (pn.getOtroEmpleoCollection() != null) {
			pn.getOtroEmpleoCollection().size();
		}

		if (pn.getIngresoMensualAdicionalCollection() != null) {
			pn.getIngresoMensualAdicionalCollection().size();
		}

		if (pn.getEstadoPersonaCollection() != null) {
			pn.getEstadoPersonaCollection().size();
		}

		if (pn.getDeportePracticaCollection() != null) {
			pn.getDeportePracticaCollection().size();
		}
		if (pn.getPersonaPeCollection() != null) {
			pn.getPersonaPeCollection().size();
		}

		// Se inicializan los valores para la persona
		inicializarPersonaNaturalContratante(pn, ruc);

		return pn;
	}

	/**
	 * Se inicializan datos de la persona natural para procesos de contratante.
	 * 
	 * @param pn
	 * @param ruc
	 */
	private void inicializarPersonaNaturalContratante(PersonaNatural pn, String ruc) {

		inicializarPersonaNatural(pn);

		// Se consulta si existe registro en tabla RUC_PERSONA_NATURAL para la persona
		// encontrada
		List<RucPersonaNatural> rucPnList = rucPersonaNaturalServicio.buscarPoIdentificacion(ruc,
				pn.getSecPersonaNatural());

		// inicia PEP

		iniciarPep(pn);

		if (rucPnList != null && !rucPnList.isEmpty()) {
			pn.setExisteRucPersonaNatural(true);
			pn.setRucPersonaNaturalTra(rucPnList.get(0));
		} else {
			pn.setExisteRucPersonaNatural(false);
			TipoIdentificacion tipoRuc = tipoIdentificacionServicio.findByPk(TipoIdentificacionEnum.RUC.getCodigo());

			RucPersonaNatural rucPn = new RucPersonaNatural();
			rucPn.setIdentificacion(ruc);
			rucPn.setPersonaNatural(pn);
			rucPn.setTipoIdentificacion(tipoRuc);

			pn.setRucPersonaNaturalTra(rucPn);
		}
	}

	/**
	 * Se inicializan datos de la persona natural para representante legal de
	 * contratante.
	 * 
	 * @param pn
	 */
	private void inicializarPersonaNatural(PersonaNatural pn) {
		// TODO: que se hace con ese contacto????
		iniciarDirecciones(pn);

		// inicia PEP
		iniciarPep(pn);

		// Se inicializan los valores de persona natural
		ContratanteHelper.inicializarPersonaNaturalEncontrada(pn, personaNaturalServicio,
				perfilFinancieroNaturalServicio, direccionElectronicaServicio);

		// Se quita enlace del objeto persona con la session para que no se pueda
		// insertar objetos creados en blanco
		personaNaturalServicio.detach(pn);

	}

	private void iniciarPep(PersonaNatural pn) {

		int totalPeActivos = 0;
		Collection<PersonaPe> personaPeCollecction = pn.getPersonaPeCollection();

		if (personaPeCollecction != null) {
			for (PersonaPe personaPe : personaPeCollecction) {
				// el bug de selectonemenu
				personaPe.setCategoriaPpe(new CategoriaPpe(personaPe.getCategoriaPpe().getCodCategoriaPpe()));
				if (personaPe.getActivo()) {
					totalPeActivos++;
				}
			}
		}

		// respuestaPep
		if (totalPeActivos > 0) {
			pn.setRespuestaPep(RespuestaEnum.SI.getCodigo() + "");
		} else {
			pn.setRespuestaPep(RespuestaEnum.NO.getCodigo() + "");
		}
	}

	/**
	 * Se inicializan los telefonos y las direcciones.
	 * 
	 * @param personaNatural
	 * @return
	 */
	private ContactoPreferido iniciarDirecciones(PersonaNatural personaNatural) {
		// direcciones
		Collection<Direccion> dirCollection = personaNatural.getPersona().getDireccionCollection();
		if (dirCollection != null)
			dirCollection.size();

		// recupero contacto preferido y le pongo en el transient
		personaNatural.getPersona().setContactoPreferidoTransient(
				contactoPreferidoServicio.obtenerPorPersona(personaNatural.getPersona().getSecPersona()));

		ContactoPreferido contactoPreferido = personaNatural.getPersona().getContactoPreferidoTransient();

		if (contactoPreferido != null) {
			contactoPreferido.ponerHorarioEnHorasMinutos();
		} else {
			// no tiene contacto preferido, fue realizado de la migracion
			contactoPreferido = new ContactoPreferido();
			contactoPreferido.setDireccion(new Direccion());
			contactoPreferido.setPersona(personaNatural.getPersona());
			contactoPreferido.setTelefono(new Telefono());
			contactoPreferido.setTipoContactoPreferido(new TipoContactoPreferido());
			personaNatural.getPersona().setContactoPreferidoTransient(contactoPreferido);
		}

		if (dirCollection != null)
			for (Direccion direccion : dirCollection) {
				if (direccion != null) {

					Short secCanton = direccion.getCanton().getSecCanton();
					String nombreCanton = direccion.getCanton().getCanton();
					Short secProvincia = direccion.getCanton().getProvincia().getSecProvincia();
					String nombreProv = direccion.getCanton().getProvincia().getProvincia();
					Short secPais = direccion.getCanton().getProvincia().getPais().getCodPais();
					String nombrePais = direccion.getCanton().getProvincia().getPais().getPais();

					direccion.setCanton(
							new Canton(secCanton, nombreCanton, secProvincia, nombreProv, secPais, nombrePais));

					// auditoria
					Direccion original = new Direccion();
					try {
						BeanUtils.copyProperties(original, direccion);
						direccion.setOriginal(original);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}

					String[] criteriasAnd = { "persona.secPersona", "direccion.secDireccion" };
					CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS };
					Object[] params = new Object[] { personaNatural.getPersona().getSecPersona(),
							direccion.getSecDireccion() };
					Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

					Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
							.findByCriterias(criteria);

					for (DireccionTelefono dt : direccionTelefonoCollection) {
						if (contactoPreferido.getTelefono().getSecTelefono() != null && dt.getTelefono()
								.getSecTelefono().compareTo(contactoPreferido.getTelefono().getSecTelefono()) == 0) {
							dt.getTelefono().setPrincipal(true);
						} else {
							dt.getTelefono().setPrincipal(false);
						}
						// feaso
						dt.getTelefono().setTipoTelefono(
								new TipoTelefono(dt.getTelefono().getTipoTelefono().getCodTipoTelefono()));

						// para pasar con asteriscos
						dt.getTelefono().setTelefonoConCodigoAreaAnterior(dt.getTelefono().getTelefonoConCodigoArea());

						// auditoria telefono con direccion
						Telefono originalT = new Telefono();
						try {
							BeanUtils.copyProperties(originalT, dt.getTelefono());
							dt.getTelefono().setOriginal(originalT);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(e);
						} catch (InvocationTargetException e) {
							throw new RuntimeException(e);
						}

						// auditoria direccion telefono
						DireccionTelefono originalDT = new DireccionTelefono();
						try {
							BeanUtils.copyProperties(originalDT, dt);
							dt.setOriginal(originalDT);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(e);
						} catch (InvocationTargetException e) {
							throw new RuntimeException(e);
						}
					}

					direccion.setDireccionTelefonoCollection(direccionTelefonoCollection);

				}
			}
		return contactoPreferido;
	}

	/**
	 * Se inicializan los telefonos y las direcciones de persona juridica
	 * 
	 * @param personaJuridica
	 * @return
	 */
	private ContactoPreferido iniciarDireccionesPJ(PersonaJuridica personaJuridica) {
		// direcciones
		Collection<Direccion> dirCollection = personaJuridica.getPersona().getDireccionCollection();
		if (dirCollection != null)
			dirCollection.size();

		// recupero contacto preferido y le pongo en el transient
		personaJuridica.getPersona().setContactoPreferidoTransient(
				contactoPreferidoServicio.obtenerPorPersona(personaJuridica.getPersona().getSecPersona()));

		ContactoPreferido contactoPreferido = personaJuridica.getPersona().getContactoPreferidoTransient();

		if (contactoPreferido != null) {
			contactoPreferido.ponerHorarioEnHorasMinutos();
		} else {
			// no tiene contacto preferido, fue realizado de la migracion
			contactoPreferido = new ContactoPreferido();
			contactoPreferido.setDireccion(new Direccion());
			contactoPreferido.setPersona(personaJuridica.getPersona());
			contactoPreferido.setTelefono(new Telefono());
			contactoPreferido.setTipoContactoPreferido(new TipoContactoPreferido());
			personaJuridica.getPersona().setContactoPreferidoTransient(contactoPreferido);
		}

		if (dirCollection != null)
			for (Direccion direccion : dirCollection) {
				if (direccion != null) {

					Short secCanton = direccion.getCanton().getSecCanton();
					String nombreCanton = direccion.getCanton().getCanton();
					Short secProvincia = direccion.getCanton().getProvincia().getSecProvincia();
					String nombreProv = direccion.getCanton().getProvincia().getProvincia();
					Short secPais = direccion.getCanton().getProvincia().getPais().getCodPais();
					String nombrePais = direccion.getCanton().getProvincia().getPais().getPais();

					direccion.setCanton(
							new Canton(secCanton, nombreCanton, secProvincia, nombreProv, secPais, nombrePais));

					// auditoria
					Direccion original = new Direccion();
					try {
						BeanUtils.copyProperties(original, direccion);
						direccion.setOriginal(original);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}

					String[] criteriasAnd = { "persona.secPersona", "direccion.secDireccion" };
					CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS };
					Object[] params = new Object[] { personaJuridica.getPersona().getSecPersona(),
							direccion.getSecDireccion() };
					Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

					Collection<DireccionTelefono> direccionTelefonoCollection = direccionTelefonoDao
							.findByCriterias(criteria);

					for (DireccionTelefono dt : direccionTelefonoCollection) {
						if (contactoPreferido.getTelefono().getSecTelefono() != null && dt.getTelefono()
								.getSecTelefono().compareTo(contactoPreferido.getTelefono().getSecTelefono()) == 0) {
							dt.getTelefono().setPrincipal(true);
						} else {
							dt.getTelefono().setPrincipal(false);
						}
						// feaso
						dt.getTelefono().setTipoTelefono(
								new TipoTelefono(dt.getTelefono().getTipoTelefono().getCodTipoTelefono()));

						// para pasar con asteriscos
						dt.getTelefono().setTelefonoConCodigoAreaAnterior(dt.getTelefono().getTelefonoConCodigoArea());

						// auditoria telefono con direccion
						Telefono originalT = new Telefono();
						try {
							BeanUtils.copyProperties(originalT, dt.getTelefono());
							dt.getTelefono().setOriginal(originalT);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(e);
						} catch (InvocationTargetException e) {
							throw new RuntimeException(e);
						}

						// auditoria direccion telefono
						DireccionTelefono originalDT = new DireccionTelefono();
						try {
							BeanUtils.copyProperties(originalDT, dt);
							dt.setOriginal(originalDT);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(e);
						} catch (InvocationTargetException e) {
							throw new RuntimeException(e);
						}
					}

					direccion.setDireccionTelefonoCollection(direccionTelefonoCollection);

				}
			}
		return contactoPreferido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.ContratanteServicio#verificarExistenciaPJ(java.lang.
	 * String)
	 */
	@Override
	public PersonaJuridica verificarExistenciaPJ(String identificacion) {
		PersonaJuridica pj = null;

		// 1. Se busca en la tabla PersonaJuridita
		pj = personaJuridicaServicio.findByNoDocumento(identificacion, true, true, true, true, true);
		System.out.println("PJ find::   " + pj);
		// Si no se encuentra en ninguna parte, se inicializa los datos de la persona
		// juridica.
		if (pj == null || pj.getIdentificacion() == null || pj.getIdentificacion().trim().length() <= 0) {
			pj = new PersonaJuridica();

			// se busca la razon social del servicio externo
			try {
				String rs = siseServicio.consultarRazonSocial(identificacion);
				if (rs != null && !rs.equals("null")) {
					pj.setRazonSocial(rs);
				}
			} catch (RazonSocialException e) {
				e.printStackTrace();
			}

			ContratanteHelper.inicializarPersonaJuridica(pj, identificacion, tipoIdentificacionServicio);
		} else {
			// inicia si ya tiene datos, por ejemplo actividad economica nivel 2 y nivel 1
			ActividadEconomica act = pj.getActividadEconomica();
			if (act != null) {
				actividadEconomicaServicio.ponerNivel2Nivel1(act);
			}
			if (pj.getPersona().getContactoPreferidoTransient() == null) {
				pj.getPersona().setContactoPreferidoTransient(new ContactoPreferido());
			}

			// inicia contacto preferido juridica
			iniciarDireccionesPJ(pj);

			ContratanteHelper.identificarCorreosElectronicosSolosPJ(pj.getPersona(), direccionElectronicaServicio);
		}

		return pj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.ContratanteServicio#persistirPN(com.equivida.modelo.
	 * PersonaNatural)
	 */
	@Override
	public BigDecimal persistirPN(PersonaNatural personaNatural) throws ContratanteException, RcsException {
		String remoteAddr = HttpHelper.getCurrentClientIpAddress();
		String remoteUser = getRemoteUser();
		boolean nuevo = personaNatural.getSecPersonaNatural() == null ? true : false;

		// 0. Se define un objeto Helper
		ContratantePNHelper ch = new ContratantePNHelper(personaNatural, rcsServicio, remoteAddr, remoteUser, nuevo,
				personaNaturalServicio, siseServicio, cantonServicio, personaEquividaServicio,
				actividadEconomicaServicio);

		System.out.println("1. Ejecuta validaciones");
		// 1. Se validan datos generales
		ch.validarPN();

		System.out.println("2. Valida Lsitas Reservadas");
		// 2. Se verifica personaNatural y Conyuge en Listas reservadas.
		ch.verificarRcs();

		System.out.println("3. Colocar Latitud LocationWorld");
		// 3. Se colocan la latitud y longitud
		ch.colocarLatitudLocationWorld();

		System.out.println("4. Tareas por registro nuevo");
		// 4. Se realizan tareas cuando es nuevo registro
		if (personaNatural.getSecPersonaNatural() == null) {
			ch.tareasRegistroNuevo();
		}

		System.out.println("5. Se ponen valores por defecto en campos NO nulos");
		// 5. Se llena campos no nulos que no tienen información con valores por
		// defecto
		ch.establecerValoresPorDefecto();

		System.out.println("6. Se llenan campos de auditoria");
		// 6. Se llenan datos en campos de auditoria
		String terminal = HttpHelper.getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();

		AuditoriaHelper ah = new AuditoriaHelper(remoteUser, terminal, programa, cuenta);
		ah.llenarAuditoriaContratantePN(personaNatural);

		System.out.println("7. Se persiste en la BDD");
		// 7. Se persiste en la BDD
		crearPN(personaNatural);

		System.out.println("8. Se llama a SP SISE");
		// 8. Se llama integra con SISE
		RespuestaSiseDto respuestaSise = ch.integrarSise();

		System.out.println("9. FIN llamada a SP SISE: " + respuestaSise);

		if (respuestaSise != null) {
			System.out.println("9. FIN llamada a SP SISE id persona: " + respuestaSise.getIdPersonaSise());
			System.out.println("9. FIN llamada a SP SISE cod aseg: " + respuestaSise.getCodAseguridadoSise());

			// 9. Se persiste el id sise en la tabla persona_equivida
			if (respuestaSise.getIdPersonaSise() != null) {
				System.out.println("10. Se llama persiste persona_equivida------------------------------------");
				// Se hace flush para que no se caiga la conexion
				ch.grabarPersonaEquivida(respuestaSise.getIdPersonaSise().longValue());
				System.out.println("11. FIN llama persiste persona_equivida------------------------------------");
			}
		} else {
			return null;
		}

		// devuelve el codigo de asegurado
		if (!respuestaSise.getErrorLista().isEmpty()) {
			return null;
		}
		return BigDecimal.valueOf(respuestaSise.getCodAseguridadoSise());
	}

	/**
	 * Se obtiene el usuario conectado que ejecuta el metodo.
	 * 
	 * @return
	 */
	private String getRemoteUser() {
		String remoteUser = Constantes.USUARIO_CRM;
		try {
			remoteUser = sessionContext.getCallerPrincipal().getName();
		} catch (Exception e) {
		}

		return remoteUser;
	}

	/**
	 * Se persiste inforemacion de persona juridica de manera synchronized.
	 * 
	 * @param personaNatural
	 */
	private synchronized void crearPNSync(PersonaNatural personaNatural) {
		Persona persona = personaNatural.getPersona();

		ContactoPreferido contactoPreferido = persona.getContactoPreferidoTransient();

		// 1. Se guarda la persona
		personaServicio.persitir(persona);

		// 2. Se guarda la persona natural
		personaNaturalServicio.persitir(personaNatural);

		// 3. Se persite rucPersonaNatural solo si es nuevo registro
		if (personaNatural.getRucPersonaNaturalTra().getSecRucPersonaNatural() == null) {
			rucPersonaNaturalServicio.create(personaNatural.getRucPersonaNaturalTra());
		}

		// 4. Se guarda persona de conyuge si lo hay
		if (personaNatural.isConConyuge()) {
			boolean nuevaPersona = persona.getSecPersona() == null ? true : false;
			personaNaturalServicio.ingresarActualizaConyuge(personaNatural, nuevaPersona);
			PersonaNatural conyuge = personaNatural.getConyuge();
			Persona personaConyuge = conyuge.getPersona();

			personaConyuge.setDenominacion(conyuge.getApellidosNombres());

			// personaServicio.persitir(personaConyuge);
			// personaNaturalServicio.persitir(conyuge);
		}

		// 5. Se guarda direcciones y telefonos de direcciones
		direccionServicio.persistir(persona.getDireccionCollection());

		// 5.1 Se persiste telefonos de direcciones
		Telefono preferido = null;
		Direccion preferidoDir = null;

		Collection<DireccionTelefono> dirTelefonoCol = new ArrayList<DireccionTelefono>();
		Collection<Telefono> telefonoCol = new ArrayList<Telefono>();
		for (Direccion d : persona.getDireccionCollection()) {
			if (d.getDireccionTelefonoCollection() != null && !d.getDireccionTelefonoCollection().isEmpty()) {
				for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
					dirTelefonoCol.add(dt);
					telefonoCol.add(dt.getTelefono());
					if (dt.getTelefono().getPrincipal()) {
						preferido = dt.getTelefono();
						preferidoDir = dt.getDireccion();
					}
				}
			}
		}
		telefonoServicio.persistir(telefonoCol); // 1ero se deben persistir todos los telefonos y luego la tabla de
													// rompimiento direccion_telefono.
		direccionTelefonoServicio.guardarLista(dirTelefonoCol);

		// 6. Se guarda mails
		direccionElectronicaServicio.persistir(persona.getEmailFacturacionElectronicaTra());
		if (persona.getEmailPersonalTra() != null && persona.getEmailPersonalTra().getDireccionElectronica() != null
				&& persona.getEmailPersonalTra().getDireccionElectronica().trim().length() > 0) {
			direccionElectronicaServicio.persistir(persona.getEmailPersonalTra());
		}

		// 7. Se guarda telefonos
		if (persona.getNoCelularSoloTra() != null && persona.getNoCelularSoloTra().getNroTelefono() != null
				&& persona.getNoCelularSoloTra().getNroTelefono().trim().length() > 0) {
			telefonoServicio.persistir(persona.getNoCelularSoloTra());
		}

		// 8. Se guarda Ingresos mensuales
		personaNatural.getIngresoMensual().setPersonaNatural(personaNatural);
		ingresoMensualServicio.persistir(personaNatural.getIngresoMensual());

		// 9. Se guarda ingreso mensual adicional
		if (personaNatural.getIngresoMensualAdicionalTra() != null
				&& personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional() != null
				&& personaNatural.getIngresoMensualAdicionalTra().getMntIngresoAdicional()
						.compareTo(BigDecimal.ZERO) > 0
				&& personaNatural.getIngresoMensualAdicionalTra().getFuenteIngresoAdicional() != null
				&& personaNatural.getIngresoMensualAdicionalTra().getFuenteIngresoAdicional().trim().length() > 0) {
			personaNatural.getIngresoMensualAdicionalTra().setPersonaNatural(personaNatural);
			personaNatural.getIngresoMensualAdicionalTra().setMntEgresoAdicional(BigDecimal.ZERO);
			ingresoMensualAdicionalServicio.persistir(personaNatural.getIngresoMensualAdicionalTra());
		}

		// 10. Se guarda empleo dependiente o independiente.
		if (personaNatural.getTipoEmpleo() == TipoEmpleoEnum.INDEPENDIENTE.getTipoEmpleo()) {
			personaNatural.getEmpleoIndependienteTra().setPersonaNatural(personaNatural);
			empleoIndependienteServicio.persistir(personaNatural.getEmpleoIndependienteTra());
		} else {
			personaNatural.getEmpleoDependienteTra().setPersonaNatural(personaNatural);
			empleoDependienteServicio.persistir(personaNatural.getEmpleoDependienteTra());
		}

		// 11. Se guarda el perfil financiero
		personaNatural.getPerfilFinancieroNatural().setSecPersonaNatural(personaNatural.getSecPersonaNatural());
		perfilFinancieroNaturalServicio.persistir(personaNatural.getPerfilFinancieroNatural());

		// 12. Contacto preferido
		// agrega telefono preferido
		contactoPreferido.setTelefono(preferido);
		contactoPreferido.setDireccion(preferidoDir);
		if (contactoPreferido.getSecContactoPreferido() == null) {
			contactoPreferido.setHorarioInicio((short) 0);
			contactoPreferido.setHorarioFin((short) 1);
			contactoPreferido.setPersona(persona);
			TipoContactoPreferido tipoContactoPreferido = new TipoContactoPreferido(
					Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO);
			contactoPreferido.setTipoContactoPreferido(tipoContactoPreferido);
			contactoPreferidoServicio.create(contactoPreferido);
		} else {
			contactoPreferidoServicio.updateLight(contactoPreferido);
		}
	}

	/**
	 * Se persiste inforemacion de persona juridica de manera synchronized.
	 * 
	 * @param personaNatural
	 */
	private synchronized void crearPJSync(PersonaJuridica personaJuridica) {
		Persona persona = personaJuridica.getPersona();

		ContactoPreferido contactoPreferido = persona.getContactoPreferidoTransient();
		ContactoPreferido contactoPreferidoRl = personaJuridica.getRepresentante().getContactoPreferidoTransient();

		boolean nuevaPersona = persona.getSecPersona() == null ? true : false;

		// 1. Se guarda la persona
		personaServicio.persitir(persona);

		// 1.1 Se guarda la persona juridica
		personaJuridicaServicio.persitir(personaJuridica);

		personaJuridica.setPersona(persona);

		// 1.2 Se guarda el perfil financiero
		personaJuridica.getPerfilFinancieroJuridicaTransient().setPersonaJuridica(personaJuridica);
		perfilFinancieroJuridicaServicio.persistir(personaJuridica.getPerfilFinancieroJuridicaTransient());

		// 2. Se guarda direcciones y telefonos de direcciones
		direccionServicio.persistir(persona.getDireccionCollection());

		// 2.1 Se persiste telefonos de direcciones
		Telefono preferido = null;
		Direccion preferidoDir = null;

		Collection<DireccionTelefono> dirTelefonoCol = new ArrayList<DireccionTelefono>();
		Collection<Telefono> telefonoCol = new ArrayList<Telefono>();
		for (Direccion d : persona.getDireccionCollection()) {
			if (d.getDireccionTelefonoCollection() != null && !d.getDireccionTelefonoCollection().isEmpty()) {
				for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
					dirTelefonoCol.add(dt);
					telefonoCol.add(dt.getTelefono());
					if (dt.getTelefono().getPrincipal()) {
						preferido = dt.getTelefono();
						preferidoDir = dt.getDireccion();
					}
				}
			}
		}

		telefonoServicio.persistir(telefonoCol); // 1ero se deben persistir todos los telefonos y luego la tabla de
													// rompimiento direccion_telefono.
		direccionTelefonoServicio.guardarLista(dirTelefonoCol);

		// 2.2. Se guarda mails
		if (personaJuridica.getEmailContacto() != null) {
			direccionElectronicaServicio.persistir(persona.getEmailFacturacionElectronicaTra());
			if (persona.getEmailPersonalTra() != null && persona.getEmailPersonalTra().getDireccionElectronica() != null
					&& persona.getEmailPersonalTra().getDireccionElectronica().trim().length() > 0) {
				direccionElectronicaServicio.persistir(persona.getEmailPersonalTra());
			}
		}

		// 2.3. Se guarda telefonos
		if (persona.getNoCelularSoloTra() != null && persona.getNoCelularSoloTra().getNroTelefono() != null
				&& persona.getNoCelularSoloTra().getNroTelefono().trim().length() > 0) {
			telefonoServicio.persistir(persona.getNoCelularSoloTra());
		}

		// 2.4 Contacto preferido
		// agrega telefono preferido
		contactoPreferido.setTelefono(preferido);
		contactoPreferido.setDireccion(preferidoDir);
		if (contactoPreferido.getSecContactoPreferido() == null) {
			contactoPreferido.setHorarioInicio((short) 0);
			contactoPreferido.setHorarioFin((short) 1);
			contactoPreferido.setPersona(persona);
			TipoContactoPreferido tipoContactoPreferido = new TipoContactoPreferido(
					Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO);
			contactoPreferido.setTipoContactoPreferido(tipoContactoPreferido);
			contactoPreferidoServicio.create(contactoPreferido);
		} else {
			contactoPreferidoServicio.updateLight(contactoPreferido);
		}

		// 3. Se guarda replegal
		boolean nuevaPersonaRL = personaJuridica.getRepresentante().getSecPersona() == null ? true : false;
		Persona pRL = personaJuridica.getRepresentante();
		personaServicio.persitir(pRL);
		personaNaturalServicio.persitir(personaJuridica.getRepresentante().getPersonaNaturalTransient());

		// 4. Se guarda persona de conyuge Rep Legal si lo hay

		PersonaNatural pnRL = personaJuridica.getRepresentante().getPersonaNaturalTransient();

		guardarRelacionRl(nuevaPersona, persona, pRL);

		if (pnRL.isConConyuge()) {
			personaNaturalServicio.ingresarActualizaConyuge(pnRL, nuevaPersonaRL);
			PersonaNatural conyuge = pnRL.getConyuge();
			Persona personaConyuge = conyuge.getPersona();

			personaConyuge.setDenominacion(conyuge.getApellidosNombres());

		}

		// 5. Se guarda direcciones y telefonos de direcciones de Rep Legal
		direccionServicio.persistir(pRL.getDireccionCollection());

		// 5.1 Se persiste telefonos de direcciones
		Telefono preferidoRl = null;
		Direccion preferidoDirRl = null;

		Collection<DireccionTelefono> dirTelefonoColRl = new ArrayList<DireccionTelefono>();
		Collection<Telefono> telefonoColRl = new ArrayList<Telefono>();
		for (Direccion d : pRL.getDireccionCollection()) {
			if (d.getDireccionTelefonoCollection() != null && !d.getDireccionTelefonoCollection().isEmpty()) {
				for (DireccionTelefono dt : d.getDireccionTelefonoCollection()) {
					dirTelefonoColRl.add(dt);
					telefonoColRl.add(dt.getTelefono());
					if (dt.getTelefono().getPrincipal()) {
						preferidoRl = dt.getTelefono();
						preferidoDirRl = dt.getDireccion();
					}
				}
			}
		}

		telefonoServicio.persistir(telefonoColRl); // 1ero se deben persistir todos los telefonos y luego la tabla de
													// rompimiento direccion_telefono.
		direccionTelefonoServicio.guardarLista(dirTelefonoColRl);

		// 5.3 Se guarda telefonos
		if (pRL.getNoCelularSoloTra() != null && pRL.getNoCelularSoloTra().getNroTelefono() != null
				&& pRL.getNoCelularSoloTra().getNroTelefono().trim().length() > 0) {
			telefonoServicio.persistir(pRL.getNoCelularSoloTra());
		}

		// 5.4 Contacto preferido
		// agrega telefono preferido
		contactoPreferidoRl.setTelefono(preferidoRl);
		contactoPreferidoRl.setDireccion(preferidoDirRl);
		if (contactoPreferidoRl.getSecContactoPreferido() == null) {
			contactoPreferidoRl.setHorarioInicio((short) 0);
			contactoPreferidoRl.setHorarioFin((short) 1);
			contactoPreferidoRl.setPersona(pRL);
			TipoContactoPreferido tipoContactoPreferido = new TipoContactoPreferido(
					Constantes.TIPO_CONTACTO_PREFERIDO_TELEFONO);
			contactoPreferidoRl.setTipoContactoPreferido(tipoContactoPreferido);
			contactoPreferidoServicio.create(contactoPreferidoRl);
		} else {
			contactoPreferidoServicio.updateLight(contactoPreferidoRl);
		}

		// guardar accionistas
		guardarAccionistas(personaJuridica, persona);

	}

	private void guardarAccionistas(PersonaJuridica personaJuridica, Persona persona) {
		Collection<Relacion> accionistas = personaJuridica.getAccionistaListTra();
		for (Relacion relacion : accionistas) {

			if (relacion.getSecRelacion() == null) {
				// verifica primero si es juridica
				Persona perAcc = relacion.getPersona();
				PersonaNatural perNatAcc = perAcc.getPersonaNaturalTransient();
				// si no es juridica puede ser q venga de enriqucimieinto, y tal vez sea
				// nacesario guardar persona y persona natural
				if (perAcc.getPersonaJuridicaTransient() == null) {
					if (perAcc.getSecPersona() == null) {
						perAcc.setTipoIdentificacion(perNatAcc.getTipoIdentificacion());
						perAcc.setCliente(EsClienteEnum.NO.getCodigo());
						perAcc.setDenominacion(perNatAcc.getApellidosNombres());
						personaServicio.create(perAcc);
					}
					if (perNatAcc.getSecPersonaNatural() == null) {
						perNatAcc.setPersona(perAcc);
						perNatAcc.setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
						Calendar c = Calendar.getInstance();
						c.set(1801, 01, 01);
						perNatAcc.setFchNacimiento(c.getTime());
						perNatAcc.setProfesion(new Profesion(Constantes.PROFESION_NO_DISPONIBLE));
						perNatAcc.setOcupacion(new Ocupacion(Constantes.OCUPACION_ID_NO_DISPONIBLE));
						perNatAcc.setTipoRiesgo(new TipoRiesgo(Constantes.TIPO_RIESGO_ID_DEFAULT));
						perNatAcc.setMntSaldoMensual(BigDecimal.ZERO);
						perNatAcc.setIngresoAnualCollection(null);
						perNatAcc.setEmpleoDependienteCollection(null);
						perNatAcc.setEmpleoIndependienteCollection(null);
						perNatAcc.setEstadoCivil(new EstadoCivil(Constantes.ESTADO_CIVIL_ID_SOLTERO));
						perNatAcc.setPaisNacionalidad(new Pais(Constantes.PAIS_ID_ECUADOR));
						perNatAcc.setCiudadNacimiento(new Ciudad(Constantes.CIUDAD_ND));
						perNatAcc.setSexo(Constantes.ID_SEXO_MASCULINO);

						personaNaturalServicio.create(perNatAcc);
					}
				} else {
					PersonaJuridica pj = perAcc.getPersonaJuridicaTransient();
					pj.setPersona(perAcc);

					if (pj.getSecPersonaJuridica() == null) {
						pj.setRazonSocial(perAcc.getPersonaNaturalTransient().getApellidoPaterno());
						pj.setTipoIdentificacion(perAcc.getPersonaNaturalTransient().getTipoIdentificacion());
						pj.setIdentificacion(perAcc.getPersonaNaturalTransient().getIdentificacion());
						pj.setTipoPersonaJuridica(new TipoPersonaJuridica(Constantes.TIPO_PERSONA_JURIDICA_ND));
						pj.setActividadEconomica(new ActividadEconomica(Constantes.ACTIVIDAD_ECONOMICA_PJ_DEFAULT));
						pj.setSectorMercado(new SectorMercado(Constantes.SECTOR_MERCADO_OTROS));
						pj.setPais(new Pais(Constantes.PAIS_ID_NO_DISPONIBLE));
						Calendar cal = Calendar.getInstance();
						cal.set(1901,1,1);
						pj.setFchConstitucion(cal.getTime());
						pj.setNombreComercial("");
						pj.setObjetoSocial("");
						pj.setNombreContacto("");
						pj.setEmailContacto("");

						perAcc.setDenominacion(perAcc.getPersonaNaturalTransient().getApellidoPaterno());

						if (perAcc.getSecPersona() == null) {
							perAcc.setTipoIdentificacion(perAcc.getPersonaNaturalTransient().getTipoIdentificacion());
							perAcc.setCliente(EsClienteEnum.NO.getCodigo());
						}

						String remoteUser = getRemoteUser();
						String terminal = HttpHelper.getCurrentClientIpAddress();
						String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
						String cuenta = this.getClass().getSimpleName();

						AuditoriaHelper ah = new AuditoriaHelper(remoteUser, terminal, programa, cuenta);
						ah.llenarCamposAuditoriaPersonaJuridica(pj);

						personaServicio.persitir(perAcc);

						// direccionTelefonoDao.flush();
						pj.setPersona(new Persona(perAcc.getSecPersona()));

						System.out.println("pj:");
						LOG.info(">" + pj.toString());

						personaJuridicaServicio.persitir(pj);
						// pjGuardar = pj;
					}
				}

				relacion.setPersona(new Persona(perAcc.getSecPersona()));
				relacion.setPersona1(persona);
				relacion.setEstado(EstadoEnum.ACTIVO.getCodigo());
				relacionServicio.create(relacion);
			} else {

				if (relacion.getPersona().getPersonaJuridicaTransient() != null) {
					PersonaJuridica pj = relacion.getPersona().getPersonaJuridicaTransient();
					PersonaNatural pn = relacion.getPersona().getPersonaNaturalTransient();
					if (pj.getSecPersonaJuridica() == null) {
						pj.setRazonSocial(pn.getApellidoPaterno());
						pj.setTipoIdentificacion(pn.getTipoIdentificacion());
						pj.setIdentificacion(pn.getIdentificacion());
						pj.setPersona(relacion.getPersona());
						personaJuridicaServicio.create(pj);
					}
				}

				relacionServicio.update(relacion);
			}

		} // fin for
	}

	private void guardarRelacionRl(boolean nuevaPersona, Persona persona, Persona pRL) {
		if (nuevaPersona) {
			crearRelacionRl(persona, pRL);
		} else {

			List<Relacion> listaR = relacionServicio.buscarPorTipoPersonaPrincipal(Constantes.TIPO_RELACION_REP_LEGAL,
					persona.getSecPersona(), null);

			boolean encontroInactivo = false;
			for (Relacion r : listaR) {
				// pone inactivos a todos menos si ya eoncuentra el rep legal antes
				if (r.getPersona().getSecPersona().longValue() == pRL.getSecPersona()) {
					r.setPersona(pRL);
					r.setEstado(EstadoEnum.ACTIVO.getCodigo());
					encontroInactivo = true;
					relacionServicio.update(r);
				} else {
					r.setEstado(EstadoEnum.INACTIVO.getCodigo());
					relacionServicio.update(r);
				}
			}
			// si no encunetra entonces le crea
			if (!encontroInactivo) {
				crearRelacionRl(persona, pRL);
			}

		}
	}

	private void crearRelacionRl(Persona persona, Persona pRL) {
		Relacion r = new Relacion();
		r.setEstado(EstadoEnum.ACTIVO.getCodigo());
		r.setPersona(pRL);
		r.setPersona1(persona);
		r.setTipoParentescoRelacion(new TipoParentescoRelacion(Constantes.TIPO_RELACION_REP_LEGAL));
		relacionServicio.create(r);
	}

	@Override
	public List<ContratanteListaDto> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, int firstRows, int totalRows) {
		return contratanteDao.obtenerListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre,
				segundoNombre, firstRows, totalRows);
	}

	@Override
	public Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre) {
		return contratanteDao.obtenerTotalListadoPaginado(numeroDocumento, apellidoPaterno, apellidoMaterno,
				primerNombre, segundoNombre);
	}

	@Override
	public PersonaNatural verificarExistenciaRepresentanteLegal(String identificacion,
			TipoIdentificacionEnum tipoIdentificacionEnum) {
		PersonaNatural pn = null;

		String numeroDocumento = identificacion;

		// 3. Se obtienen los 10 primeros digitos de la identificacion
		if (identificacion.length() > 10) {
			numeroDocumento = identificacion.substring(0, 10);
		}

		// Se busca como persona natural
		PersonaNatural personaNatural = personaNaturalServicio.obtenerPersonaNaturalByDocumento(numeroDocumento, true);

		if (personaNatural != null && personaNatural.getSecPersonaNatural() != null) {
			// Se inicializan relaciones que no estan en el metodo
			if (personaNatural.getIngresoMensualAdicionalCollection() != null) {
				personaNatural.getIngresoMensualAdicionalCollection().size();
			}

			if (personaNatural.getEstadoPersonaCollection() != null) {
				personaNatural.getEstadoPersonaCollection().size();
			}

			if (personaNatural.getDeportePracticaCollection() != null) {
				personaNatural.getDeportePracticaCollection().size();
			}

			// Se inicializa datos de PJ en base de datos de PN.
			pn = personaNatural;

			// Se inicializan los valores de la persona natural
			inicializarPersonaNatural(pn);
		} else {
			// Se busca como beneficiario
			Beneficiario beneficiario = beneficiarioServicio.buscarPorNoDocumento(numeroDocumento);

			if (beneficiario != null && beneficiario.getSecBeneficiario() != null) {
				// Se inicializan datos de PN con datos de Beneficiario
				pn = new PersonaNatural();

				// Se inicializa con datos vacios
				ContratanteHelper.inicializarPersonaNatural(pn, identificacion, tipoIdentificacionServicio,
						tipoIdentificacionEnum);

				// Se llenan los datos del beneficiario en la PN
				ContratanteHelper.inicializarPersonaNaturalDeBeneficiario(pn, beneficiario);
			} else {
				// Se busca en enriquecimiento
				RespuestaGeneralDto respuestaDto = consultaGeneralCUServicio.consultaGenerica(numeroDocumento,
						getRemoteUser(), identificacion);

				// Se inicializan datos de PN con datos de enriquecimiento
				pn = new PersonaNatural();
				ContratanteHelper.inicializarDeEnriquecimiento(pn, respuestaDto, identificacion, getRemoteUser(),
						direccionElectronicaServicio);
			}
		}

		// Si no se encuentra en ninguna parte, se inicializa los datos de la persona
		// natural.
		if (pn == null || pn.getIdentificacion() == null || pn.getIdentificacion().trim().length() <= 0) {
			pn = new PersonaNatural();
			ContratanteHelper.inicializarPersonaNatural(pn, identificacion, tipoIdentificacionServicio,
					tipoIdentificacionEnum);
		}

		System.out.println("datos por default para representante legal:");

		// Se inicializa ocupacion cuando no tiene (viene solo de enriquecimiento)
		if (pn.getOcupacion() == null || pn.getOcupacion().getCodOcupacion() == null) {
			pn.setOcupacion(new Ocupacion(Constantes.OCUPACION_ID_NO_DISPONIBLE));
			System.out.println("ocupacion vacio, pone por default");
		}

		if (pn.getMntSaldoMensual() == null) {
			pn.setMntSaldoMensual(BigDecimal.ZERO);
			System.out.println("MntSaldoMensual vacio, pone  cero por default");
		}

		if (pn.getIngresoMensual() != null && pn.getIngresoMensual().getSecIngresoPersona() == null) {
			pn.setIngresoMensual(null);
			System.out.println("obj ingreso mensual vacio, pone null");
		}

		if (pn.getPersona().getContactoPreferidoTransient() == null) {
			ContactoPreferido contactoPreferido = new ContactoPreferido();
			contactoPreferido.setDireccion(new Direccion());
			contactoPreferido.setPersona(pn.getPersona());
			contactoPreferido.setTelefono(new Telefono());
			contactoPreferido.setTipoContactoPreferido(new TipoContactoPreferido());
			pn.getPersona().setContactoPreferidoTransient(contactoPreferido);
			System.out.println("contacto preferido");
		}

		return pn;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal persistirPJ(PersonaJuridica personaJuridica) throws ContratanteException, RcsException {
		String remoteAddr = HttpHelper.getCurrentClientIpAddress();
		String remoteUser = getRemoteUser();
		boolean nuevo = personaJuridica.getSecPersonaJuridica() == null ? true : false;

		// 0. Se define un objeto Helper
		ContratantePJHelper ch = new ContratantePJHelper(personaJuridica, rcsServicio, remoteAddr, remoteUser, nuevo,
				personaNaturalServicio, siseServicio, cantonServicio, personaEquividaServicio,
				actividadEconomicaServicio);

		System.out.println("1. Ejecuta validaciones");
		// 1. Se validan datos generales
		ch.validarPJ();

		System.out.println("2. Valida Lsitas Reservadas");
		// 2. Se verifica personaNatural y Conyuge en Listas reservadas.
		// ch.verificarRcs();

		System.out.println("3. Colocar Latitud LocationWorld");
		// 3. Se colocan la latitud y longitud
		ch.colocarLatitudLocationWorld();
		ch.colocarLatitudLocationWorldRL();

		System.out.println("4. Tareas por registro nuevo");
		// 4. Se realizan tareas cuando es nuevo registro
		if (personaJuridica.getSecPersonaJuridica() == null) {
			ch.tareasRegistroNuevo();
		}

		System.out.println("5. Se ponen valores por defecto en campos NO nulos");
		// 5. Se llena campos no nulos que no tienen información con valores por
		// defecto
		ch.establecerValoresPorDefecto();

		System.out.println("6. Se llenan campos de auditoria");
		// 6. Se llenan datos en campos de auditoria
		String terminal = HttpHelper.getCurrentClientIpAddress();
		String programa = Constantes.FORMULARIO_WEB_PROGRAMA_AUDITORIA;
		String cuenta = this.getClass().getSimpleName();

		AuditoriaHelper ah = new AuditoriaHelper(remoteUser, terminal, programa, cuenta);
		ah.llenarAuditoriaContratantePJ(personaJuridica);

		System.out.println("7. Se persiste en la BDD");
		// 7. Se persiste en la BDD
		crearPJ(personaJuridica);

		System.out.println("8. Se llama a SP SISE");
		// 8. Se llama integra con SICE
		RespuestaSiseDto respuestaSise = ch.integrarSise();

		System.out.println("9. FIN llamada a SP SISE: " + respuestaSise);

		if (respuestaSise != null) {
			System.out.println("9. FIN llamada a SP SISE id persona: " + respuestaSise.getIdPersonaSise());
			System.out.println("9. FIN llamada a SP SISE cod aseg: " + respuestaSise.getCodAseguridadoSise());

			// 9. Se persiste el id sise en la tabla persona_equivida
			if (respuestaSise.getIdPersonaSise() != null) {
				System.out.println("10. Se llama persiste persona_equivida------------------------------------");
				// Se hace flush para que no se caiga la conexion
				ch.grabarPersonaEquivida(respuestaSise.getIdPersonaSise().longValue());
				System.out.println("11. FIN llama persiste persona_equivida------------------------------------");
			}
		} else {
			return null;
		}

		// devuelve el codigo de asegurado
		if (!respuestaSise.getErrorLista().isEmpty()) {
			return null;
		}
		return BigDecimal.valueOf(respuestaSise.getCodAseguridadoSise());

	}

	@Override
	public void crearPJ(PersonaJuridica personaJuridica) {
		crearPJSync(personaJuridica);

	}

	@Override
	public void crearPN(PersonaNatural personaNatural) {
		crearPNSync(personaNatural);
	}
}