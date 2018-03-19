package domen;

import java.sql.Date;
import java.sql.Time;

public class Rezervacija {
	
	private int idRezervacije;
	private int brojUlaznica;
	private int idKorisnika;
	private int idTermina;
	
	private double ukupnaCena;
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public int getIdRezervacije() {
		return idRezervacije;
	}
	public void setIdRezervacije(int idRezervacije) {
		this.idRezervacije = idRezervacije;
	}
	public int getBrojUlaznica() {
		return brojUlaznica;
	}
	public void setBrojUlaznica(int brojUlaznica) {
		this.brojUlaznica = brojUlaznica;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public int getIdTermina() {
		return idTermina;
	}
	public void setIdTermina(int idTermina) {
		this.idTermina = idTermina;
	}
	
}
