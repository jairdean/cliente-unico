package com.equivida.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.equivida.constant.EstadoEnum;
import com.equivida.modelo.MotivoSeguro;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.TipoMotivoSeguro;

/**
 * Calse utillitaria para persona
 * 
 * @author Daniel Cardenas
 *
 */
public class PersonaUtil {

	/**
	 * Inicializa motivos de seguro
	 * 
	 * @param personaNatural
	 * @param tipoMotivoSeguroLista
	 * @param motivoBDDList
	 */
	public static void inicializarMotivosSeguro(PersonaNatural personaNatural,
			Collection<TipoMotivoSeguro> tipoMotivoSeguroLista,
			Collection<MotivoSeguro> motivoBDDList) {

		System.out.println("tipo motivo seg total:"
				+ tipoMotivoSeguroLista.size());

		List<MotivoSeguro> motivoSeguroLista = new ArrayList<MotivoSeguro>();

		for (TipoMotivoSeguro tipoMotivoSeguro : tipoMotivoSeguroLista) {

			MotivoSeguro ms = null;
			if (motivoBDDList != null) {
				for (MotivoSeguro bdd : motivoBDDList) {
					if (bdd.getTipoMotivoSeguro().getCodTipoMotivo()
							.compareTo(tipoMotivoSeguro.getCodTipoMotivo()) == 0) {
						ms = bdd;
						break;
					}
				}
			}
			// si no encontro crea una nueva para que aparezca en el formulario
			if (ms == null) {
				ms = new MotivoSeguro();
				ms.setPersona(personaNatural.getPersona());
				ms.setSeleccionado(false);
				ms.setEstado(EstadoEnum.INACTIVO.getCodigo());
				ms.setTipoMotivoSeguro(tipoMotivoSeguro);
			}

			motivoSeguroLista.add(ms);
		}

		personaNatural.getPersona().setMotivoSeguroFormularioCollection(
				motivoSeguroLista);

	}

}
