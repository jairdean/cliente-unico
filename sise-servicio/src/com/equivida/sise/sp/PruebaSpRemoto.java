package com.equivida.sise.sp;

import javax.ejb.Remote;

@Remote
public interface PruebaSpRemoto {

	void llamarPrueba();

	void insertDatos(String dato);
}
