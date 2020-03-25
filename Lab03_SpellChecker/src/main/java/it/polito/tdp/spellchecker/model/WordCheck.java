package it.polito.tdp.spellchecker.model;

public class WordCheck {

	private String parola;
	private boolean stato;
	
	public WordCheck(String parola) {
		super();
		this.parola = parola;
		this.stato = false;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setStatus(boolean b) {
		this.stato=b;
	}
	
	public boolean getStatus() {
		return stato;
	}
}
