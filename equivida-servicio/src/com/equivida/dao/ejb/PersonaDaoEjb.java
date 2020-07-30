package com.equivida.dao.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.PersonaDao;
import com.equivida.dao.TipoIdentificacionDao;
import com.equivida.dto.CompaniaCrmDto;
import com.equivida.dto.PersonaCrmDto;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.Persona;
import com.equivida.modelo.TipoIdentificacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PersonaDao")
public class PersonaDaoEjb extends GenericDaoEjb<Persona, Integer> implements PersonaDao {

	@javax.ejb.EJB
	private TipoIdentificacionDao identificacionDao;

	public PersonaDaoEjb() {
		super(Persona.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PersonaCrmDto obtenerpersonaCrm(Long secPersona) {

		String sql = " SELECT PN.COD_TIPO_IDENTIFICACION, PN.IDENTIFICACION, PN.SEC_PERSONA, PN.PRIMER_NOMBRE,  PN.SEGUNDO_NOMBRE,   PN.APELLIDO_PATERNO, PN.APELLIDO_MATERNO, "
				+ " PN.FCH_NACIMIENTO, EC.ESTADO_CIVIL,  PN.SEXO, PN.SEC_PROFESION,  'A' NIVEL_EDUCACION,  PN.COD_OCUPACION, "
				+ " (SELECT A.PRIMER_NOMBRE || ',' || A.SEGUNDO_NOMBRE || ',' ||    A.APELLIDO_PATERNO || ',' ||    A.APELLIDO_MATERNO "
				+ " FROM equivida.RELACION B INNER JOIN equivida.PERSONA_NATURAL  A ON B.SEC_PERSONA_RELACION = A.SEC_PERSONA AND B.ESTADO= 'A' WHERE B.COD_TIPO_PARENTESCO IN (15) "
				+ " AND B.SEC_PERSONA_PRINCIPAL = PN.SEC_PERSONA and ESTADO = 'A') as conyuge ,  "
				+ " PN.NUM_HIJOS, CP.HORA_INICIO_CONTACTO,  CP.HORA_FIN_CONTACTO, ' ' TIPO_EMPLEO, "
				+ " (SELECT NEGOCIO_EMPRESA || ',' || CARGO  " + " FROM equivida.EMPLEO_DEPENDIENTE A "
				+ " WHERE A.SEC_PERSONA_NATURAL = PN.SEC_PERSONA_NATURAL AND "
				+ "   A.SEC_EMPLEO_DEPENDIENTE IN (SELECT MAX(X.SEC_EMPLEO_DEPENDIENTE) " + " FROM  "
				+ " equivida.EMPLEO_DEPENDIENTE X "
				+ " WHERE A.SEC_PERSONA_NATURAL = X.SEC_PERSONA_NATURAL)) DEPENDIENTE, "
				+ " (SELECT ' ' || ',' || CARGO " + "  FROM equivida.EMPLEO_INDEPENDIENTE A "
				+ "  WHERE A.SEC_PERSONA_NATURAL = PN.SEC_PERSONA_NATURAL AND "
				+ "  A.SEC_EMPLEO_INDEPENDIENTE = (SELECT MAX(X.SEC_EMPLEO_INDEPENDIENTE) "
				+ " FROM equivida.EMPLEO_INDEPENDIENTE X WHERE A.SEC_PERSONA_NATURAL = X.SEC_PERSONA_NATURAL)) INDEPENDIENTE, "
				+ " (SELECT DIRECCION_ELECTRONICA  " + "  FROM " + " equivida.DIRECCION_ELECTRONICA  A " + "  WHERE "
				+ " COD_TIPO_DIRECCION_ELECTRONICA = 1  "
				+ " AND SEC_DIRECCION_ELECTRONICA = (SELECT MAX(X.SEC_DIRECCION_ELECTRONICA) FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA  and X.COD_TIPO_DIRECCION_ELECTRONICA = 1) "
				+ " AND A.SEC_PERSONA = PN.SEC_PERSONA) EMAIL ,  " + "( SELECT A.COD_TIPO_DIRECCION_ELECTRONICA"
				+ "  FROM equivida.DIRECCION_ELECTRONICA  A " + "INNER JOIN equivida.tipo_direccion_electronica TD"
				+ "   ON A.COD_TIPO_DIRECCION_ELECTRONICA = TD.COD_TIPO_DIRECCION_ELECTRONICA "
				+ " WHERE A.COD_TIPO_DIRECCION_ELECTRONICA = 1  "
				+ "  AND SEC_DIRECCION_ELECTRONICA = (SELECT MAX(X.SEC_DIRECCION_ELECTRONICA) FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA  and X.COD_TIPO_DIRECCION_ELECTRONICA = 1) "
				+ " AND A.SEC_PERSONA = PN.SEC_PERSONA) TIPO_EMAIL , " + " (SELECT C.TIPO_DIRECCION   "
				+ " FROM equivida.DIRECCION D LEFT JOIN equivida.TIPO_DIRECCION C ON D.COD_TIPO_DIRECCION = C.COD_TIPO_DIRECCION "
				+ " WHERE  D.SEC_DIRECCION IN (SELECT MAX(X.SEC_DIRECCION)   "
				+ " FROM equivida.DIRECCION  X WHERE X.SEC_PERSONA = D.SEC_PERSONA) AND D.SEC_PERSONA = PN.SEC_PERSONA) TIPO_DIR, "
				+ " (SELECT P.COD_PAIS || ',' ||  '' || ',' || P.SEC_PROVINCIA || ',' || D.SEC_CANTON || ',' || D.CIUDAD || ',' || '' || ',' || D.BARRIO|| ',' || D.PRINCIPAL || ',' || D.NUMERO || ',' || D.SECUNDARIA || ',' || D.EDIFICIO || ',' || D.LONGITUD || ',' || D.LATITUD || ',' || D.REFERENCIA "
				+ " FROM equivida.DIRECCION D LEFT JOIN equivida.CANTON C ON D.SEC_CANTON = C.SEC_CANTON LEFT JOIN equivida.PROVINCIA P ON P.SEC_PROVINCIA = C.SEC_PROVINCIA  "
				+ " WHERE  D.SEC_DIRECCION IN (SELECT MAX(X.SEC_DIRECCION)   "
				+ " FROM equivida.DIRECCION  X WHERE X.SEC_PERSONA = D.SEC_PERSONA) AND D.SEC_PERSONA = PN.SEC_PERSONA) DIRECCION , "
				+ " (SELECT  TT.TIPO_TELEFONO  "
				+ " FROM equivida.TELEFONO T  LEFT JOIN equivida.TIPO_TELEFONO TT ON T.COD_TIPO_TELEFONO = TT.COD_TIPO_TELEFONO "
				+ " WHERE T.SEC_TELEFONO  IN (SELECT MAX(X.SEC_TELEFONO)  "
				+ " FROM equivida.TELEFONO X WHERE X.SEC_PERSONA = T.SEC_PERSONA) AND T.SEC_PERSONA = PN.SEC_PERSONA) TIP_TELEF , "
				+ " (SELECT  T.COD_PAIS|| ',' || T.COD_AREA|| ',' || T.NRO_TELEFONO || ',' || T.EXT_TELEFONO FROM equivida.TELEFONO T  "
				+ " WHERE T.SEC_TELEFONO  IN (SELECT MAX(X.SEC_TELEFONO)  "
				+ " FROM equivida.TELEFONO X WHERE X.SEC_PERSONA = T.SEC_PERSONA) AND T.SEC_PERSONA = PN.SEC_PERSONA) TELEFONO ,  "
				+ " PN.PRIMER_NOMBRE || ' ' || PN.APELLIDO_PATERNO CONTACTO ,  " + " (SELECT DIRECCION_ELECTRONICA   "
				+ " FROM  " + " equivida.DIRECCION_ELECTRONICA  A  " + " WHERE   "
				+ " COD_TIPO_DIRECCION_ELECTRONICA = 2 AND  "
				+ " SEC_DIRECCION_ELECTRONICA = (SELECT MAX(X.SEC_DIRECCION_ELECTRONICA)  "
				+ " FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA and  X.COD_TIPO_DIRECCION_ELECTRONICA = 2) AND A.SEC_PERSONA = PN.SEC_PERSONA ) EMAIL_CONTACTO ,  "
				+ " IM.MNT_INGRESO_MENSUAL  " + " FROM equivida.PERSONA_NATURAL PN  "
				+ " LEFT JOIN equivida.ESTADO_CIVIL EC ON PN.COD_ESTADO_CIVIL = EC.COD_ESTADO_CIVIL  "
				+ " LEFT JOIN equivida.CONTACTO_PREFERIDO CP ON PN.SEC_PERSONA = CP.SEC_PERSONA  "
				+ " LEFT JOIN equivida.INGRESO_MENSUAL IM ON PN.SEC_PERSONA_NATURAL = IM.SEC_PERSONA_NATURAL  "
				+ " WHERE PN.SEC_PERSONA = '" + secPersona + "'  ";

		System.out.println("sql para crm:");
		System.out.println(sql);

		Query query = em.createNativeQuery(sql);

		List<PersonaCrmDto> lista = new ArrayList<PersonaCrmDto>();

		List<Object> objList = query.getResultList();
		for (Iterator<Object> iterator = objList.iterator(); iterator.hasNext();) {
			Object[] obj = (Object[]) iterator.next();
			PersonaCrmDto dto = new PersonaCrmDto();

			dto.setCodTipoIdentificacion(obj[0].toString());
			TipoIdentificacion ti = identificacionDao.load(dto.getCodTipoIdentificacion().charAt(0));
			dto.setSecTipoIdentificacion(ti.getSecTipoIdentificacion());

			dto.setIdentificacion(obj[1].toString());
			dto.setSecuencialPersona(Integer.parseInt(obj[2].toString()));
			dto.setPrimerNombre(obj[3].toString());
			dto.setSegundoNombre(obj[4].toString());
			dto.setApellidoPaterno(obj[5].toString());
			dto.setApellidoMaterno(obj[6].toString());
			dto.setFechaNacimiento((Date) obj[7]);
			dto.setEstadoCivil(obj[8].toString());
			dto.setSexo(obj[9].toString());
			dto.setSecuencialProfesion((Short) obj[10]);
			dto.setNivelEducacion(obj[11].toString());
			dto.setCodOcupacion((Short) obj[12]);
			if (obj[13] != null) {
				String nombresApellidos[] = ((String) obj[13]).split(",");
				if (nombresApellidos.length == 4) {
					dto.setConyugeNombre1(nombresApellidos[0]);
					dto.setConyugeNombre2(nombresApellidos[1]);
					dto.setConyugeApePat(nombresApellidos[2]);
					dto.setConyugeApeMat(nombresApellidos[3]);
				}
			}
			dto.setNumHijos((Short) obj[14]);
			if (obj[15] != null) {
				String hora = ContactoPreferido.obtenerFormatoHora((Short) obj[15]);
				dto.setHoraInicioContacto(hora);
			} else {
				dto.setHoraInicioContacto("08:00");
			}
			if (obj[16] != null) {
				String hora = ContactoPreferido.obtenerFormatoHora((Short) obj[16]);
				dto.setHoraFinContacto(hora);
			} else {
				dto.setHoraFinContacto("18:00");
			}
			dto.setTipoEmpleo(obj[17].toString());
			if (obj[18] != null) {
				dto.setDependiente(obj[18].toString());
			}
			if (obj[19] != null) {
				dto.setIndependiente(obj[19].toString());
			}
			dto.setEmail((String) obj[20]);
			if (obj[21] != null) {
				dto.setTipoEmail(((Short) obj[21]).toString());
			}
			dto.setTipoDireccion((String) obj[22]);
			dto.setDireccion((String) obj[23]);
			dto.setTipoTelefono((String) obj[24]);
			dto.setTelefono((String) obj[25]);
			dto.setContacto((String) obj[26]);
			dto.setEmailContacto((String) obj[27]);
			if (obj[28] != null) {
				dto.setMontoIngresoMensual((BigDecimal) obj[28]);
			} else {
				dto.setMontoIngresoMensual(BigDecimal.ZERO);
			}

			// valores calculados
			if (dto.getDependiente() != null && !dto.getDependiente().trim().equals("")) {
				String[] arr = dto.getDependiente().split(",");
				dto.setRelacionLaboral("Dependiente");
				dto.setEmpresaTrabaja(arr[0]);
				dto.setCargo(arr[1]);
			}
			if (dto.getIndependiente() != null && !dto.getIndependiente().trim().equals("")) {
				String[] arr = dto.getIndependiente().split(",");
				dto.setRelacionLaboral("Independiente");
				dto.setEmpresaTrabaja(arr[0]);
				dto.setCargo(arr[1]);
			}

			// direccion
			if (dto.getDireccion() != null && !dto.getDireccion().trim().equals("")) {
				String[] arr = dto.getDireccion().split(",");

				System.out.println("arr.length:" + arr.length);

				for (String a : arr) {
					System.out.println(a);
				}

				dto.setCodPais(arr[0]);
				dto.setRegion(arr[1]);
				dto.setCodProv(arr[2]);
				dto.setCodCanton(arr[3]);
				dto.setCiudad(arr[4]);
				dto.setCodParr(arr[5]);
				dto.setBarrio(arr[6]);
				dto.setDirCalle1(arr[7]);
				dto.setDirNumero(arr[8]);
				dto.setDirCalle2(arr[9]);
				dto.setDirEdif(arr[10]);
				dto.setLongitud(arr[11]);
				dto.setLatitud(arr[12]);
				if (arr.length > 13) {
					dto.setReferencia(arr[13]);
				}

			} // fin direccion

			// telefono
			if (dto.getTelefono() != null && !dto.getTelefono().trim().equals("")) {
				String[] arr = dto.getTelefono().split(",");
				dto.setCodTelPais(arr[0]);
				dto.setCodArea(arr[1]);
				dto.setTel(arr[2]);
				if (arr.length > 3) {
					dto.setTelExt(arr[3]);
				}

			} // fin telefono

			lista.add(dto);
		}

		PersonaCrmDto respuesta = null;

		if (lista.size() > 0) {
			respuesta = lista.get(0);
			if (lista.size() > 1) {
				respuesta = lista.get(0);
				System.out.println("ERROR no puede haber dos personas con el mismo secuencial");
			}
		}

		return respuesta;

	}

	@SuppressWarnings("unchecked")
	@Override
	public CompaniaCrmDto obtenerCompaniaCrm(Long secCompania) {
		String sql = "SELECT PN.COD_TIPO_IDENTIFICACION, PN.IDENTIFICACION, PN.SEC_PERSONA, PN.PRIMER_NOMBRE,  PN.SEGUNDO_NOMBRE"
				+ ",   PN.APELLIDO_PATERNO, PN.APELLIDO_MATERNO,  PN.FCH_NACIMIENTO, EC.ESTADO_CIVIL,  PN.SEXO"
				+ ", PN.SEC_PROFESION,  'A' NIVEL_EDUCACION,  PN.COD_OCUPACION,  (SELECT A.PRIMER_NOMBRE || ',' || A.SEGUNDO_NOMBRE || ',' ||    A.APELLIDO_PATERNO || ',' ||    A.APELLIDO_MATERNO"
				+ " FROM equivida.RELACION B INNER JOIN equivida.PERSONA_NATURAL  A ON B.SEC_PERSONA_RELACION = A.SEC_PERSONA WHERE B.COD_TIPO_PARENTESCO IN (15) "
				+ " AND B.SEC_PERSONA_PRINCIPAL = PN.SEC_PERSONA) as conyuge ,   PN.NUM_HIJOS, CP.HORA_INICIO_CONTACTO,  CP.HORA_FIN_CONTACTO"
				+ ", ' ' TIPO_EMPLEO, (SELECT NEGOCIO_EMPRESA || ',' || CARGO  FROM equivida.EMPLEO_DEPENDIENTE A WHERE A.SEC_PERSONA_NATURAL = PN.SEC_PERSONA_NATURAL"
				+ " AND A.SEC_EMPLEO_DEPENDIENTE IN (SELECT MAX(X.SEC_EMPLEO_DEPENDIENTE) FROM  equivida.EMPLEO_DEPENDIENTE X WHERE A.SEC_PERSONA_NATURAL = X.SEC_PERSONA_NATURAL)) DEPENDIENTE"
				+ ", (SELECT ' ' || ',' || CARGO FROM equivida.EMPLEO_INDEPENDIENTE A WHERE A.SEC_PERSONA_NATURAL = PN.SEC_PERSONA_NATURAL AND"
				+ " A.SEC_EMPLEO_INDEPENDIENTE = (SELECT MAX(X.SEC_EMPLEO_INDEPENDIENTE) FROM equivida.EMPLEO_INDEPENDIENTE X WHERE A.SEC_PERSONA_NATURAL = X.SEC_PERSONA_NATURAL)) INDEPENDIENTE"
				+ ", (SELECT DIRECCION_ELECTRONICA  FROM equivida.DIRECCION_ELECTRONICA  A WHERE  COD_TIPO_DIRECCION_ELECTRONICA = 1"
				+ " AND SEC_DIRECCION_ELECTRONICA = (SELECT MAX(X.SEC_DIRECCION_ELECTRONICA) FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA) AND A.SEC_PERSONA = PN.SEC_PERSONA"
				+ ") EMAIL , (SELECT DIRECCION_ELECTRONICA  FROM equivida.DIRECCION_ELECTRONICA  A WHERE  COD_TIPO_DIRECCION_ELECTRONICA = 2"
				+ " AND SEC_DIRECCION_ELECTRONICA = (SELECT MAX(X.SEC_DIRECCION_ELECTRONICA) FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA) AND A.SEC_PERSONA = PN.SEC_PERSONA"
				+ ") EMAIL_ALTERNO , 'DOMICILIO' TIPO_DIR , (SELECT P.COD_PAIS || ',' ||  '' || ',' || P.SEC_PROVINCIA || ',' || D.SEC_CANTON || ',' || D.CIUDAD || ',' || '' || ',' || D.BARRIO"
				+ "|| ',' || D.PRINCIPAL || ',' || D.NUMERO || ',' || D.SECUNDARIA || ',' || D.EDIFICIO || ',' || D.LONGITUD || ',' || D.LATITUD || ',' || D.REFERENCIA FROM equivida.DIRECCION D"
				+ " LEFT JOIN equivida.CANTON C ON D.SEC_CANTON = C.SEC_CANTON LEFT JOIN equivida.PROVINCIA P ON P.SEC_PROVINCIA = C.SEC_PROVINCIA WHERE  D.SEC_DIRECCION IN (SELECT MAX(X.SEC_DIRECCION) "
				+ " FROM equivida.DIRECCION  X WHERE X.SEC_PERSONA = D.SEC_PERSONA) AND D.SEC_PERSONA = PN.SEC_PERSONA) DIRECCION , 'DOMICILIO' TIP_TELEF , (SELECT  T.COD_PAIS|| ',' || T.COD_AREA"
				+ "|| ',' || T.NRO_TELEFONO || ',' || T.EXT_TELEFONO FROM equivida.TELEFONO T WHERE T.SEC_TELEFONO  IN (SELECT MAX(X.SEC_TELEFONO) FROM equivida.TELEFONO X WHERE X.SEC_PERSONA = T.SEC_PERSONA)"
				+ " AND T.SEC_PERSONA = PN.SEC_PERSONA) TELEFONO , PN.PRIMER_NOMBRE || ' ' || PN.APELLIDO_PATERNO CONTACTO , (SELECT DIRECCION_ELECTRONICA  FROM equivida.DIRECCION_ELECTRONICA  A WHERE  COD_TIPO_DIRECCION_ELECTRONICA = 2"
				+ " AND SEC_DIRECCION_ELECTRONICA = (SELECT MIN(X.SEC_DIRECCION_ELECTRONICA) FROM equivida.DIRECCION_ELECTRONICA X WHERE X.SEC_PERSONA = A.SEC_PERSONA) AND A.SEC_PERSONA = PN.SEC_PERSONA "
				+ ") EMAIL_CONTACTO , IM.MNT_INGRESO_MENSUAL FROM equivida.PERSONA_NATURAL PN LEFT JOIN equivida.ESTADO_CIVIL EC ON PN.COD_ESTADO_CIVIL = EC.COD_ESTADO_CIVIL"
				+ " LEFT JOIN equivida.CONTACTO_PREFERIDO CP ON PN.SEC_PERSONA = CP.SEC_PERSONA LEFT JOIN equivida.INGRESO_MENSUAL IM ON PN.SEC_PERSONA_NATURAL = IM.SEC_PERSONA_NATURAL"
				+ " WHERE PN.SEC_PERSONA = '" + secCompania + "' ";

		Query query = em.createNativeQuery(sql);

		List<CompaniaCrmDto> lista = new ArrayList<CompaniaCrmDto>();

		List<Object> objList = query.getResultList();
		for (Iterator<Object> iterator = objList.iterator(); iterator.hasNext();) {
			Object[] obj = (Object[]) iterator.next();
			CompaniaCrmDto dto = new CompaniaCrmDto();

			dto.setCodTipoIdentificacion(obj[0].toString());
			dto.setIdentificacion(obj[1].toString());
			dto.setSecuencialPersona(Integer.parseInt(obj[2].toString()));
			dto.setPrimerNombre(obj[3].toString());
			dto.setSegundoNombre(obj[4].toString());
			dto.setApellidoPaterno(obj[5].toString());
			dto.setApellidoMaterno(obj[6].toString());
			dto.setFechaNacimiento((Date) obj[7]);
			dto.setEstadoCivil(obj[8].toString());
			dto.setSexo(obj[9].toString());
			dto.setSecuencialProfesion((Short) obj[10]);
			dto.setNivelEducacion(obj[11].toString());
			dto.setCodOcupacion((Short) obj[12]);
			dto.setConyuge((String) obj[13]);
			dto.setNumHijos((Short) obj[14]);
			if (obj[15] != null) {
				String hora = ContactoPreferido.obtenerFormatoHora((Short) obj[15]);
				dto.setHoraInicioContacto(hora);
			}
			if (obj[16] != null) {
				String hora = ContactoPreferido.obtenerFormatoHora((Short) obj[16]);
				dto.setHoraFinContacto(hora);
			}
			// dto.setHoraInicioContacto((Short) obj[15]);
			// dto.setHoraFinContacto((Short) obj[16]);
			dto.setTipoEmpleo(obj[17].toString());
			if (obj[18] != null) {
				dto.setDependiente(obj[18].toString());
			}
			if (obj[19] != null) {
				dto.setIndependiente(obj[19].toString());
			}
			dto.setEmail((String) obj[20]);
			dto.setEmailAlterno((String) obj[21]);
			dto.setTipoDireccion((String) obj[22]);
			dto.setDireccion((String) obj[23]);
			dto.setTipoTelefono((String) obj[24]);
			dto.setTelefono((String) obj[25]);
			dto.setContacto((String) obj[26]);
			dto.setEmailContacto((String) obj[27]);
			dto.setMontoIngresoMensual((BigDecimal) obj[28]);

			// valores calculados
			if (dto.getDependiente() != null && !dto.getDependiente().trim().equals("")) {
				String[] arr = dto.getDependiente().split(",");
				dto.setRelacionLaboral("Dependiente");
				dto.setEmpresaTrabaja(arr[0]);
				dto.setCargo(arr[1]);
			}
			if (dto.getIndependiente() != null && !dto.getIndependiente().trim().equals("")) {
				String[] arr = dto.getIndependiente().split(",");
				dto.setRelacionLaboral("Independiente");
				dto.setEmpresaTrabaja(arr[0]);
				dto.setCargo(arr[1]);
			}

			// direccion
			if (dto.getDireccion() != null && !dto.getDireccion().trim().equals("")) {
				String[] arr = dto.getDireccion().split(",");

				System.out.println("arr.length:" + arr.length);

				for (String a : arr) {
					System.out.println(a);
				}

				dto.setCodPais(arr[0]);
				dto.setRegion(arr[1]);
				dto.setCodProv(arr[2]);
				dto.setCodCanton(arr[3]);
				dto.setCiudad(arr[4]);
				dto.setCodParr(arr[5]);
				dto.setBarrio(arr[6]);
				dto.setDirCalle1(arr[7]);
				dto.setDirNumero(arr[8]);
				dto.setDirCalle2(arr[9]);
				dto.setDirEdif(arr[10]);
				dto.setLongitud(arr[11]);
				dto.setLatitud(arr[12]);
				if (arr.length > 13) {
					dto.setReferencia(arr[13]);
				}

			} // fin direccion

			// telefono
			if (dto.getTelefono() != null && !dto.getTelefono().trim().equals("")) {
				String[] arr = dto.getTelefono().split(",");
				dto.setCodTelPais(arr[0]);
				dto.setCodArea(arr[1]);
				dto.setTel(arr[2]);
				if (arr.length > 3) {
					dto.setTelExt(arr[3]);
				}

			} // fin telefono

			lista.add(dto);
		}

		CompaniaCrmDto respuesta = null;

		if (lista.size() > 0) {
			respuesta = lista.get(0);
			if (lista.size() > 1) {
				respuesta = lista.get(0);
				System.out.println("ERROR no puede haber dos personas con el mismo secuencial");
			}
		}

		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> obtenerPersonaByDocumento(List<String> numeroDocumento) {
		String ejbql = "select new map(pn.identificacion as cedula, pn.persona.secPersona as secuencial) from PersonaNatural pn where pn.identificacion  in :listaDocumentos ";
		Query query = em.createQuery(ejbql);
		query.setParameter("listaDocumentos", numeroDocumento);

		List<Map<String, Object>> ret = query.getResultList();

		System.out.println("encontro total:" + ret.size());

		Map<String, Integer> resp = new HashMap<String, Integer>();
		for (Map<String, Object> m : ret) {
			resp.put((String) m.get("cedula"), (Integer) m.get("secuencial"));
		}

		return resp;
	}
}
