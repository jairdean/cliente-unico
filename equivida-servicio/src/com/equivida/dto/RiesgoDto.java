package com.equivida.dto;

import java.io.Serializable;

public class RiesgoDto implements Serializable {

	private static final long serialVersionUID = -6768823989345958274L;
	
	private boolean nacional;

	private String listing;
	
	private String file;
	
	private String score;
	
	public boolean isNacional() {
		return nacional;
	}

	public void setNacional(boolean nacional) {
		this.nacional = nacional;
	}

	public String getListing() {
		return listing;
	}

	public void setListing(String listing) {
		this.listing = listing;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
