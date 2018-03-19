package domen;

import java.sql.Date;
import java.sql.Time;

public class TabelaRezervacije {
	
	private int idPredstave;
	private int idPozorista;
	private Date datum;
	private Time vreme;
	private String nazivPredstave;
	private String nazivPozorista;
	private String nazivSale;
	private double cenaKarte;
	public int getIdPredstave() {
		return idPredstave;
	}
	public void setIdPredstave(int idPredstave) {
		this.idPredstave = idPredstave;
	}
	public int getIdPozorista() {
		return idPozorista;
	}
	public void setIdPozorista(int idPozorista) {
		this.idPozorista = idPozorista;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Time getVreme() {
		return vreme;
	}
	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}
	public String getNazivPredstave() {
		return nazivPredstave;
	}
	public void setNazivPredstave(String nazivPredstave) {
		this.nazivPredstave = nazivPredstave;
	}
	public String getNazivPozorista() {
		return nazivPozorista;
	}
	public void setNazivPozorista(String nazivPozorista) {
		this.nazivPozorista = nazivPozorista;
	}
	public String getNazivSale() {
		return nazivSale;
	}
	public void setNazivSale(String nazivSale) {
		this.nazivSale = nazivSale;
	}
	public double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	
	

}
