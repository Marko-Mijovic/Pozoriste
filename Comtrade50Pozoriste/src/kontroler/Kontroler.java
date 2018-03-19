package kontroler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import broker.DBKomunikacija;
import domen.Korisnik;
import domen.Pozoriste;
import domen.Predstava;
import domen.Rezervacija;
import domen.Sala;
import domen.TabelaRezervacije;
import domen.Termin;
import domen.Zaposleni;

public class Kontroler {
	
	public static Kontroler instanca;
	
	private static ArrayList<Korisnik>alKorisnik;
	
	private static ArrayList<Zaposleni>alZaposleni;
	
	private static ArrayList<Pozoriste>alPozoriste;
	
	private static ArrayList<Predstava>alPredstava;
	
	private static ArrayList<Rezervacija>alRezervacije;
	
	private static ArrayList<Sala>alSala;
	
	private static ArrayList<Termin>alTermin;
	
	private static ArrayList<TabelaRezervacije>alTabelaRezervacije;
	
	private static int count, countLozinka, countTelefon, countMail, countPrezime;
	
	private static int idTermina;
	
	private Kontroler() {
		
	}
	
	public static ArrayList<Termin> getAlTermin() {
		return alTermin;
	}

	public static Kontroler getInstanca() {
		if(instanca == null) {
			instanca = new Kontroler();
		}
		return instanca;	
	}

	public ArrayList<Korisnik> vratiKorisnike() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alKorisnik = DBKomunikacija.getBroker().vratiKorisnike();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alKorisnik;
	}

	public static ArrayList<Korisnik> getAlKorisnik() {
		return alKorisnik;
	}

	public ArrayList<Zaposleni> vratiZaposlene() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alZaposleni = DBKomunikacija.getBroker().vratiZaposlene();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alZaposleni;
	}

	public static ArrayList<Zaposleni> getAlZaposleni() {
		return alZaposleni;
	}

	public ArrayList<Pozoriste> vratiPozorista() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alPozoriste = DBKomunikacija.getBroker().vratiPozorista();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alPozoriste;
	}

	public void unesiZaposlenog(String ime, String prezime, char[] password, String idPozoristaP) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiZaposlenog(ime, prezime, password, idPozoristaP);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public static ArrayList<Pozoriste> getAlPozoriste() {
		return alPozoriste;
	}

	public void unesiKorisnika(String ime, char[] password, String telefon, String mail) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiKorisnika(ime, password, telefon, mail);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public int proveriKorisnika(String ime, char[] password) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		count=DBKomunikacija.getBroker().proveriKorisnika(ime, password);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return count;
	}

	public static int getCount() {
		return count;
	}

	public static int getCountLozinka() {
		return countLozinka;
	}
	
	public static int getCountTelefon() {
		return countTelefon;
	}

	public static int getCountMail() {
		return countMail;
	}

	public int proveriLozinku(String lozinka) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		countLozinka=DBKomunikacija.getBroker().proveriLozinku(lozinka);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return countLozinka;
	}

	public int proveriTelefon(String telefon) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		countTelefon=DBKomunikacija.getBroker().proveriTelefon(telefon);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return countTelefon;
	}	
	public int proveriMail(String mail) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		countMail=DBKomunikacija.getBroker().proveriMail(mail);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return countMail;
	}

	public int proveriImeZaposlenog(String ime) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		count=DBKomunikacija.getBroker().proveriImeZaposlenog(ime);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return count;
	}

	public int proveriPrezimeZaposlenog(String prezime) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		countPrezime=DBKomunikacija.getBroker().proveriPrezimeZaposlenog(prezime);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return countPrezime;
	}

	public int proveriLozinkuZaposlenog(String lozinka) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		countLozinka=DBKomunikacija.getBroker().proveriLozinkuZaposlenog(lozinka);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return countLozinka;
	}

	public int proveriKorisnika(String ime) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		count=DBKomunikacija.getBroker().proveriKorisnika(ime);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return count;
	}

	public int proveriZaposlenog(String ime, char[] password) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		count=DBKomunikacija.getBroker().proveriZaposlenog(ime, password);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return count;
	}

	public ArrayList<TabelaRezervacije> vratiTabeluRezervacije() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alTabelaRezervacije=DBKomunikacija.getBroker().vratiTabeluRezervacije();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		
		return alTabelaRezervacije;
	}

	public static ArrayList<TabelaRezervacije> getAlTabelaRezervacije() {
		return alTabelaRezervacije;
	}

	public ArrayList<Pozoriste> vratiPozoriste() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alPozoriste=DBKomunikacija.getBroker().vratiPozorista();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alPozoriste;
	}

	public static int getIdTermina() {
		return idTermina;
	}

	public ArrayList<Termin> vratiTermine() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alTermin=DBKomunikacija.getBroker().vratiTermine();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alTermin;
	}

	public void unesiTermin(String datum, String vreme, String ukupanBrojKarata, String idPredstave, String preostaliBrojKarata, String cenaKarte) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiTermin(datum, vreme, ukupanBrojKarata, idPredstave, preostaliBrojKarata, cenaKarte);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public ArrayList<Predstava> vratiPredstave() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alPredstava=DBKomunikacija.getBroker().vratiPredstave();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alPredstava;
	}

	public static ArrayList<Predstava> getAlPredstava() {
		return alPredstava;
	}

	public void izbrisiTermin(int idTermina) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiTermin(idTermina);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void promeniTermin(int idTermina, String datum, String vreme, String ukupanBrojKarata, String idPredstave, String preostaliBrojKarata, String cenaKarte) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().promeniTermin(idTermina, datum, vreme, ukupanBrojKarata, idPredstave, preostaliBrojKarata, cenaKarte);
		DBKomunikacija.getBroker().zatvoriKonekciju();	
	}

	public ArrayList<Sala> vratiSale() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alSala=DBKomunikacija.getBroker().vratiSale();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alSala;
	}

	public void upisiRezervaciju(int brojUlaznica, int idKorisnik, int idTermina , double ukupnaCena) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiRezervaciju(brojUlaznica, idKorisnik, idTermina, ukupnaCena);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void azurirajStanje(int idTermina, int brojUlaznica) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().azurirajStanje(idTermina, brojUlaznica);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public ArrayList<Rezervacija> vratiRezervacije() {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		alRezervacije=DBKomunikacija.getBroker().vratiRezervacije();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alRezervacije;
	}

	public void otkaziRezervaciju(int idRezervacijeOtkaz) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().otkaziRezervaciju(idRezervacijeOtkaz);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void unesiPredstavu(String nazivPredstave, String reziser, String glumci, String idSale, String zanr, String opis) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiPredstavu(nazivPredstave, reziser, glumci, idSale, zanr, opis);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void izbrisiPredstavu(int idPredstave) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiPredstavu(idPredstave);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		
	}

	public void promeniPredstavu(int idPredstave, String nazivPredstave, String reziser, String glumci, String idSale, String zanr,
			String opis) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().promeniPredstavu(idPredstave, nazivPredstave, reziser, glumci, idSale, zanr, opis);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void unesiPozoriste(String nazivPozorista, String adresa, String cena, String opis) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiPozoriste(nazivPozorista, adresa, cena, opis);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void promeniPozoriste(int idPozorista, String nazivPozorista, String adresa, String cena, String opis) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().promeniPozoriste(idPozorista, nazivPozorista, adresa, cena, opis);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void izbrisiPozoriste(int idPozorista) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiPozoriste(idPozorista);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void unesiSalu(String nazivSale, String kapacitetSale, String idPozorista) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().unesiSalu(nazivSale, kapacitetSale, idPozorista);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void promeniSalu(int idSale, String nazivSale, String kapacitetSale, String idPozorista) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().promeniSalu(idSale, nazivSale, kapacitetSale, idPozorista);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public void izbrisiSalu(int idSale) {
		// TODO Auto-generated method stub
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiSalu(idSale);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}


}
