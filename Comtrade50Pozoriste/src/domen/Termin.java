package domen;

import java.sql.Date;
import java.sql.Time;

public class Termin {
	
	private int idTermina;
	private Date datumTermina;
	private Time vremeTermina;
	private int ukupanBrojKarata;
	private int idPredstave;
	private int preostaliBrojKarata;
	private double cenaKarte;
	
	public double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public int getIdTermina() {
		return idTermina;
	}
	public void setIdTermina(int idTermina) {
		this.idTermina = idTermina;
	}
	public Date getDatumTermina() {
		return datumTermina;
	}
	public void setDatumTermina(Date datumTermina) {
		this.datumTermina = datumTermina;
	}
	public Time getVremeTermina() {
		return vremeTermina;
	}
	public void setVremeTermina(Time vremeTermina) {
		this.vremeTermina = vremeTermina;
	}
	public int getUkupanBrojKarata() {
		return ukupanBrojKarata;
	}
	public void setUkupanBrojKarata(int ukupanBrojKarata) {
		this.ukupanBrojKarata = ukupanBrojKarata;
	}
	public int getIdPredstave() {
		return idPredstave;
	}
	public void setIdPredstave(int idPredstave) {
		this.idPredstave = idPredstave;
	}
	public int getPreostaliBrojKarata() {
		return preostaliBrojKarata;
	}
	public void setPreostaliBrojKarata(int preostaliBrojKarata) {
		this.preostaliBrojKarata = preostaliBrojKarata;
	}

	
}
