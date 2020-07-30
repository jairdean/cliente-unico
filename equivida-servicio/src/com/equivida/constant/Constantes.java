package com.equivida.constant;

import java.math.BigDecimal;

public class Constantes {

	public static final String FORMULARIO_WEB_PROGRAMA_AUDITORIA = "CLIENTE EQUIVIDA WEB";

	public static Short SEC_ESTADO_ACEPTADO_CON_EXTRAPRIMA = 1;
	public static Short SEC_ESTADO_ACEPTADO_SIN_EXTRAPRIMA = 2;

	public static final short COD_SISTEMA_SISE = 1;

	public static final short COD_HOMOLOGACION_TIPO_PERSONA_NATURAL = 1;
	public static final short COD_HOMOLOGACION_TIPO_PERSONA_JURIDICA = 2;

	public static final double EQUIVALENCIA_KILOS_LIBRAS = 2.2f;

	public static final short MAYOR_DE_EDAD = 18;

	public static final short MAX_EDAD = 130;

	public static final short TIPO_DIRECCION_ID_DOMICILIO = 1;

	public static final short TIPO_DIRECCION_ID_TRABAJO = 2;
	// public static final short TIPO_DIRECCION_ID_RESIDENCIA = 2;

	// public static final short TIPO_DIRECCION_ID_NEGOCIO = 4;

	public static final short TIPO_DIRECCION_ELECTRONICA_ID_PERSONAL = 1;

	public static final short TIPO_DIRECCION_ELECTRONICA_ID_EMPRESARIAL = 2;

	public static final short TIPO_DIRECCION_ELECTRONICA_ID_TRABAJO = 3;

	public static final short TIPO_DIRECCION_ELECTRONICA_FAC_ELEC = 6;

	public static final short ESTADO_CIVIL_ID_SOLTERO = 1;

	public static final short PAIS_ID_ECUADOR = 56;

	public static final short CIUDAD_ND = 0;

	public static final short PAIS_ID_NO_DISPONIBLE = 0;
	public static final short PROVINCIA_ID_NO_DISPONIBLE = 0;
	public static final short CANTON_ID_NO_DISPONIBLE = 0;
	public static final short PARROQUIA_ID_NO_DISPONIBLE = 0;

	public static final short PROFESION_NO_DISPONIBLE = 0;

	public static final short RIESGO_NO_DISPONIBLE = 0;

	public static final short TIPO_TELEFONO_DOMICILIO = 1;

	public static final short TIPO_TELEFONO_OFICINA = 2;

	public static final short TIPO_TELEFONO_CELULAR = 6;
	// public static final short TIPO_TELEFONO_RESIDENCIA = 2;

	public static final short TIPO_TELEFONO_MOVIL_PERSONAL = 3;

	public static final short TIPO_TELEFONO_MOVIL_OFICINA = 4;

	public static final short TIPO_TELEFONO_FAX = 5;

	public static final char TIPO_IDENTIFICACION_RUC = 'R';

	public static final char TIPO_IDENTIFICACION_CEDULA = 'C';

	public static final char TIPO_IDENTIFICACION_PASAPORTE = 'P';

	public static final char TIPO_IDENTIFICACION_MATRICULA = 'M';

	public static final char TIPO_IDENTIFICACION_OTRO = 'O';

	public static final int PAGER_ENUM_NUM_REGISTROS = 10;

	public static final char FILTRO_TODOS = '*';

	public static final char TIPO_PARENTEZCO_FAMILIAR = 'S';

	public static final char TIPO_PARENTEZCO_NO_FAMILIAR = 'N';

	public static final short ACTIVIDAD_ECONOMICA_DEFAULT = 1;
	
	public static final short ACTIVIDAD_ECONOMICA_PJ_DEFAULT = 3;

	public static final short TIPO_PERSONA_JURIDICA_DEFAULT = 1;

	public static final short TIPO_PERSONA_JURIDICA_ND = 0;

	public static final short SECTOR_MERCADO_OTROS = 9;

	public static final short PROFESION_ID_DEFAULT = 1;

	public static final short OCUPACION_ID_DEFAULT = 1;

	public static final short OCUPACION_ID_NO_DISPONIBLE = 0;

	public static final short TIPO_RIESGO_ID_DEFAULT = 0;

	public static final BigDecimal MNT_SALDO_MENSUAL_DEFAULT = new BigDecimal(0);

	public static final short TIPO_NEGOCIO_DEFAULT = 1;

	public static final short TIPO_CONTACTO_PREFERIDO_TELEFONO = 1;

	public static final short TIPO_RELACION_CONYUGE = 15;

	public static final short TIPO_RELACION_PADRE = 1;

	public static final short TIPO_RELACION_MADRE = 2;

	public static final short TIPO_RELACION_HERMANO = 5;

	public static final short TIPO_RELACION_ACCIONISTA = 40;

	public static final short TIPO_RELACION_REP_LEGAL = 39;

	public static final short[] IDS_TIPO_RELACION_ACCIONISTAS = { 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };

	public static final short TIPO_RELACION_GERENTE = 19;

	public static final char ID_ENFERMEDAD = 'E';

	public static final char ID_NINGUNO = 'I';

	public static final char ID_SEXO_TODOS = 'T';

	public static final char ID_SEXO_MASCULINO = 'M';

	public static final String USUARIO_CRM = "audcrm";

	public static final short ID_TIPO_OTRA_OCUPACION_NINGUNA = 1;

	public static final char PREGUNTA_ID_TIPO_DATO_NUMERICO = '9';

	public static final char ID_ESTADO_RCS_PENDIENTE = 'P';

	public static final char ID_ESTADO_RCS_APROBADO = 'A';

	public static final char ID_ESTADO_RCS_RECHAZADO = 'R';

	public static final char CHAR_VACIO = '\u0000';

	public static final String MENSAJE_RCS_APROBADO = "CLIENTE HA SIDO APROBADO";
	public static final String MENSAJE_RCS_NO_APROBADO = "CLIENTE EN AN\u00C1LISIS, NECESITA DE AUTORIZACI\u00D3N";

	public static char getSexoOpuesto(char sexo) {
		char opuesto = 'M';
		if (sexo == 'M') {
			opuesto = 'F';
		}
		return opuesto;
	}
}