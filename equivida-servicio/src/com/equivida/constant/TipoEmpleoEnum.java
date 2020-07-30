package com.equivida.constant;

/**
 * @author Daniel Cardenas
 * 
 */
public enum TipoEmpleoEnum {

	DEPENDIENTE(Integer.valueOf(-1).shortValue(), "dependiente"), INDEPENDIENTE(
			Integer.valueOf(-2).shortValue(), "independiente");

	/**
	 * @param tipoEmpleo
	 * @param keyBundle
	 */
	private TipoEmpleoEnum(short tipoEmpleo, String keyBundle) {
		this.tipoEmpleo = tipoEmpleo;
		this.keyBundle = keyBundle;
	}

	private short tipoEmpleo;

	private String keyBundle;

	public short getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(short tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	// public static List<TipoEmpleoEnum> getAsList() {
	// TipoEmpleoEnum[] arr = TipoEmpleoEnum.values();

	// return Arrays.asList(arr);
	// }

	/**
	 * @return the keyBundle
	 */
	public String getKeyBundle() {
		return keyBundle;
	}

	/**
	 * @param keyBundle
	 *            the keyBundle to set
	 */
	public void setKeyBundle(String keyBundle) {
		this.keyBundle = keyBundle;
	}
}