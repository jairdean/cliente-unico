package com.equivida.test;

import com.equivida.modelo.PersonaNatural;

public class NombresPersonaNatural {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonaNatural personaNatural = new PersonaNatural();
		
		personaNatural.setApellidoPaterno("Cardenas");
		personaNatural.setApellidoMaterno("Zurita");
		personaNatural.setPrimerNombre("Daniel");
		personaNatural.setSegundoNombre("Daniel");
		
		System.out.println(personaNatural.getApellidosNombres());
		

	}

}
