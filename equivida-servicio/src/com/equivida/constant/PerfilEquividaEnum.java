package com.equivida.constant;

/**
 * Roles del sistema
 * 
 * @author Daniel Cardenas
 *
 */
public enum PerfilEquividaEnum {

	ClienteUnico, CU_ASIST_SUSCRIPCION, CU_SUSCRIPTOR, CU_EMISION, CU_SERV_CLIENTE, ConsultaCU, CU_CUMPLIMIENTO,

	/* para global o especialista */
	CU_ESPECIALISTA, CU_GLOBAL,

	/* para contratantes */
	CU_CONTRATANTE, CU_CONSULTA_CONTRATANTE,
	
	/* para carga de personas */
	CU_PERSONA_COLECTIVO;

}
