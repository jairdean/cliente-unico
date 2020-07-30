package com.equivida.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

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

@WebService
@SOAPBinding(style = Style.RPC)
public interface ClienteUnicoWs {

	List<RsDatosPersonaJuridica> personaJuridica(
			@WebParam(name = "cod_tipo_identificacion") String cod_tipo_identificacion,
			@WebParam(name = "identificacion") String identificacion);

	List<RsDireccionElectronicaPersona> direccionElectronicaPersona(
			@WebParam(name = "secPersona") String secPersona);

	RsCreacionUsuario creacionPersona(
			@WebParam(name = "secPersona") Integer secPersona,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "password") String password,
			@WebParam(name = "secPerfil") Integer secPerfil,
			@WebParam(name = "secFigura") Integer secFigura,
			@WebParam(name = "modificar") String modificar);

	List<RsDireccionPersonaPW> direccionPersona(
			@WebParam(name = "secPersona") Integer secPersona);

	List<RsTelefonoPW> otrosTelefonos(
			@WebParam(name = "secPersona") Integer secPersona);

	RsPersonaNaturalPW personaNatural(
			@WebParam(name = "codTipoId") String codTipoId,
			@WebParam(name = "idPersona") String id);

	RsPreguntaUsuario preguntaUsuario(
			@WebParam(name = "secPersona") Integer secPersona,
			@WebParam(name = "secPregunta") Integer secPregunta,
			@WebParam(name = "respuesta") String respuesta);

	RsRespuestaPregunta respuestaPregunta(
			@WebParam(name = "secPersona") Integer secUsuario,
			@WebParam(name = "secPregunta") Integer secPregunta,
			@WebParam(name = "estado") String estado);

	List<RsTelefonoDireccionPW> telefonoDireccion(
			@WebParam(name = "secPersona") Integer secPersona);

	RsValidacionPregunta validacionPregunta(
			@WebParam(name = "secPersona") Integer secUsuario,
			@WebParam(name = "estado") String estado);

	RsDesencriptacion desencriptacion(@WebParam(name = "usuario") String usuario);

	RsPersonaNaturalPW consultarDatosPersona(
			@WebParam(name = "cedula") String cedula,
			@WebParam(name = "usuario") String usuario,
			@WebParam(name = "password") String password);
}