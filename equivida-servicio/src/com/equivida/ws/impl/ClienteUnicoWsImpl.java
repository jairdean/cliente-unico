package com.equivida.ws.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.dto.portal.RsCreacionUsuario;
import com.equivida.dto.portal.RsDatosPersonaJuridica;
import com.equivida.dto.portal.RsDesencriptacion;
import com.equivida.dto.portal.RsDireccionElectronicaPersona;
import com.equivida.dto.portal.RsDireccionPersonaPW;
import com.equivida.dto.portal.RsPersonaNaturalPW;
import com.equivida.dto.portal.RsPreguntaUsuario;
import com.equivida.dto.portal.RsRespuestaPregunta;
import com.equivida.dto.portal.RsTelefonoDireccionPW;
import com.equivida.dto.portal.RsTelefonoPW;
import com.equivida.dto.portal.RsValidacionPregunta;
import com.equivida.jdbc.CreacionUsuarioPwSp;
import com.equivida.jdbc.DatosPersonaJuridicaSp;
import com.equivida.jdbc.DesencriptacionPwSp;
import com.equivida.jdbc.DireccionElectronicaPersonaSp;
import com.equivida.jdbc.DireccionPersonaPwSp;
import com.equivida.jdbc.OtrosTelefonosPwSp;
import com.equivida.jdbc.PersonaNaturalPwSp;
import com.equivida.jdbc.PreguntaUsuarioPwSp;
import com.equivida.jdbc.RespuestaPreguntaPwSp;
import com.equivida.jdbc.TelefonoConDireccionPwSp;
import com.equivida.jdbc.ValidacionPreguntaPwSp;
import com.equivida.ws.ClienteUnicoWs;

@Stateless(name = "ClienteUnicoWs")
@WebService(endpointInterface = "com.equivida.ws.ClienteUnicoWs")
@Remote(ClienteUnicoWs.class)
public class ClienteUnicoWsImpl implements ClienteUnicoWs {

	@EJB
	private DatosPersonaJuridicaSp datosPersonaJuridicaSp;

	@EJB
	private DireccionElectronicaPersonaSp direccionElectronicaPersonaSp;

	@EJB
	private CreacionUsuarioPwSp creacionUsuarioPwSp;

	@EJB
	private DireccionPersonaPwSp direccionPersonaPwSp;

	@EJB
	private OtrosTelefonosPwSp otrosTelefonosPwSp;

	@EJB
	private PersonaNaturalPwSp naturalPwSp;

	@EJB
	private PreguntaUsuarioPwSp preguntaUsuarioPwSp;

	@EJB
	private RespuestaPreguntaPwSp preguntaPwSp;

	@EJB
	private TelefonoConDireccionPwSp conDireccionPwSp;

	@EJB
	private ValidacionPreguntaPwSp validacionPwSp;

	@EJB
	private DesencriptacionPwSp desencriptacionPwSp;

	@Override
	@WebMethod
	public @WebResult(name = "PersonaJuridica")
	List<RsDatosPersonaJuridica> personaJuridica(
			String cod_tipo_identificacion, String identificacion) {

		List<RsDatosPersonaJuridica> respuesta = new ArrayList<RsDatosPersonaJuridica>();
		try {
			respuesta = datosPersonaJuridicaSp.llamarDatosPersonaJuridicaSp(
					cod_tipo_identificacion, identificacion);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "DirecionElectronicaPersona")
	List<RsDireccionElectronicaPersona> direccionElectronicaPersona(
			String identificacion) {
		List<RsDireccionElectronicaPersona> respuesta = new ArrayList<RsDireccionElectronicaPersona>();
		try {
			respuesta = direccionElectronicaPersonaSp
					.llamarDireccionElectronicaPersonaSp(identificacion);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "CreacionUsuario")
	RsCreacionUsuario creacionPersona(Integer secPersona, String usuario,
			String password, Integer secPerfil, Integer secFigura,
			String modificar) {
		RsCreacionUsuario respuesta = new RsCreacionUsuario();
		try {
			respuesta = creacionUsuarioPwSp.creacionUsuario(secPersona,
					usuario, password, secPerfil, secFigura, modificar);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "DireccionPersona")
	List<RsDireccionPersonaPW> direccionPersona(
			@WebParam(name = "secPersona") Integer secPersona) {
		List<RsDireccionPersonaPW> respuesta = new ArrayList<RsDireccionPersonaPW>();
		try {
			respuesta = direccionPersonaPwSp
					.consultaDireccionPersona(secPersona);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "OtrosTelefonos")
	List<RsTelefonoPW> otrosTelefonos(
			@WebParam(name = "secPersona") Integer secPersona) {
		List<RsTelefonoPW> respuesta = new ArrayList<RsTelefonoPW>();
		try {
			respuesta = otrosTelefonosPwSp.consultaOtrosTelefonos(secPersona);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "PersonaNatural")
	RsPersonaNaturalPW personaNatural(String codTipoId, String id) {
		RsPersonaNaturalPW respuesta = new RsPersonaNaturalPW();
		try {
			respuesta = naturalPwSp.consultaPersonaNatural(codTipoId, id);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "PreguntaUsuario")
	RsPreguntaUsuario preguntaUsuario(Integer secPersona, Integer secPregunta,
			String respuesta) {
		RsPreguntaUsuario resp = new RsPreguntaUsuario();
		try {
			resp = preguntaUsuarioPwSp.preguntaUsuario(secPersona, secPregunta,
					respuesta);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return resp;
	}

	@Override
	@WebMethod
	public @WebResult(name = "RespuestaPregunta")
	RsRespuestaPregunta respuestaPregunta(Integer secUsuario,
			Integer secPregunta, String estado) {
		RsRespuestaPregunta respuesta = new RsRespuestaPregunta();
		try {
			respuesta = preguntaPwSp.respuestaPregunta(secUsuario, secPregunta,
					estado);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "TelefonoConDireccion")
	List<RsTelefonoDireccionPW> telefonoDireccion(
			@WebParam(name = "secPersona") Integer secPersona) {
		List<RsTelefonoDireccionPW> respuesta = new ArrayList<RsTelefonoDireccionPW>();
		try {
			respuesta = conDireccionPwSp.consultaTelefonoDireccion(secPersona);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "ValidacionPregunta")
	RsValidacionPregunta validacionPregunta(Integer secUsuario, String estado) {
		RsValidacionPregunta respuesta = new RsValidacionPregunta();
		try {
			respuesta = validacionPwSp.validacionPregunta(secUsuario, estado);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "Desencriptacion")
	RsDesencriptacion desencriptacion(String usuario) {
		RsDesencriptacion respuesta = new RsDesencriptacion();
		try {
			respuesta = desencriptacionPwSp.desencriptacion(usuario);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

	@Override
	@WebMethod
	public @WebResult(name = "ConsultarDatosPersona")
	RsPersonaNaturalPW consultarDatosPersona(String cedula, String usuario,
			String clave) {
		RsPersonaNaturalPW respuesta = new RsPersonaNaturalPW();
		String usuarioServer = "";
		String claveServer = "";

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					"../server/equivida/conf/ws_ClienteUnico.properties"));

			usuarioServer = prop.getProperty("usuario");
			claveServer = prop.getProperty("clave");

		} catch (IOException ex) {
			respuesta.setCodigoResultado((short)1);
			respuesta.setMensaje("NO EXISTE ARCHIVO CREDENCIALES. "+ex.getMessage());
		}

		if (usuario.equals(usuarioServer) && clave.equals(claveServer)) {
			try {
				respuesta = naturalPwSp.consultarDatosPersona(cedula);
			} catch (SQLException e) {
				respuesta.setCodigoResultado((short)1);
				respuesta.setMensaje("ERROR SQL "+e.getMessage());
			}
		} else {
			respuesta.setCodigoResultado((short)1);
			respuesta.setMensaje("CREDENCIALES INCORRECTAS");
		}

		return respuesta;
	}

}