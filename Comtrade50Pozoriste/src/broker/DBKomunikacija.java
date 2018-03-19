package broker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import domen.Korisnik;
import domen.Pozoriste;
import domen.Predstava;
import domen.Rezervacija;
import domen.Sala;
import domen.TabelaRezervacije;
import domen.Termin;
import domen.Zaposleni;

public class DBKomunikacija {
	
	public static DBKomunikacija broker;
	
	private int brojKarata;
	
	private Connection con;
	
	private DBKomunikacija() {
		ucitajDriver();
	}
	

	private void ucitajDriver() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DBKomunikacija getBroker() {
		if(broker == null) {
			broker = new DBKomunikacija();
		}
		return broker;
	}
	
	public void otvoriKonekciju() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/pozoriste1", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void zatvoriKonekciju() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Korisnik> vratiKorisnike() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Korisnik>alKorisnik = new ArrayList<>();
		
		String sql = "SELECT * FROM korisnik";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
			Korisnik k = new Korisnik();
			k.setIdKorisnika(rs.getInt("idKorisnika"));
			k.setKorisnickoIme(rs.getString("korisnickoIme"));
			k.setLozinka(rs.getString("lozinka"));
			k.setTelefon(rs.getString("telefon"));
			k.setMail(rs.getString("mail"));
			
			if(alKorisnik.contains(k.getIdKorisnika())) {
				
			}else {
			alKorisnik.add(k);
			}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alKorisnik;
	}

	public ArrayList<Zaposleni> vratiZaposlene() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Zaposleni>alZaposleni = new ArrayList<>();
		
		String sql = "SELECT * FROM zaposleni";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
			Zaposleni z = new Zaposleni();
			z.setIdZaposlenog(rs.getInt("idZaposleni"));
			z.setImeZaposlenog(rs.getString("imeZaposlenog"));
			z.setPrezimeZaposlenog(rs.getString("prezimeZaposlenog"));
			z.setLozinka(rs.getString("lozinka"));
			z.setIdPozorista(rs.getInt("idPozorista"));
			if(alZaposleni.contains(z.getIdZaposlenog())) {
				
			}else {
			alZaposleni.add(z);
			}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alZaposleni;
	}

	public ArrayList<Pozoriste> vratiPozorista() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Pozoriste>alPozoriste = new ArrayList<>();
		
		String sql = "SELECT * FROM pozoriste";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
			Pozoriste p = new Pozoriste();
			p.setIdPozorista(rs.getInt("idPozorista"));
			p.setNazivPozorista(rs.getString("nazivPozorista"));
			p.setAdresa(rs.getString("adresa"));
			p.setOpisPozorista(rs.getString("opisPozorista"));
			
			alPozoriste.add(p);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alPozoriste;
	}

	public void unesiZaposlenog(String ime, String prezime, char[] password, String idPozoristaP) {
		// TODO Auto-generated method stub
		String lozinka = String.valueOf(password);
		String sql = "INSERT INTO zaposleni(imeZaposlenog, prezimeZaposlenog, lozinka, idPozorista) VALUES (?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ime);
			ps.setString(2, prezime);
			ps.setString(3, lozinka);
			ps.setString(4, idPozoristaP);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void unesiKorisnika(String ime, char[] password, String telefon, String mail) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String lozinka = String.valueOf(password);
		String sql = "INSERT INTO korisnik(korisnickoIme, lozinka, telefon, mail) VALUES (?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ime);
			ps.setString(2, lozinka);
			ps.setString(3, telefon);
			ps.setString(4, mail);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public int proveriKorisnika(String ime, char[] password) { //=========================LOGIN PROVERA======================
		// TODO Auto-generated method stub
		String lozinka = String.valueOf(password);
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT korisnickoIme FROM korisnik WHERE korisnickoIme = '"+ime+"'AND lozinka = '"+lozinka+"'";
		System.out.println(sql);
		int count = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				count = count +1;
				System.out.println(count);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}return count;
		
	}
	
	public int proveriKorisnika(String ime) {//============================SIGNUP PROVERA=========================
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT korisnickoIme FROM korisnik WHERE korisnickoIme = '"+ime+"'";
		System.out.println(sql);
		int count = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				count = count +1;
				System.out.println(count);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}return count;
	}

	public int proveriLozinku(String lozinka) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT lozinka FROM korisnik WHERE lozinka = '"+lozinka+"'";
		System.out.println(sql);
		int countLozinka = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				countLozinka = countLozinka +1;
				System.out.println(countLozinka);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}
		return countLozinka;
	}

	public int proveriTelefon(String telefon) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT telefon FROM korisnik WHERE telefon = '"+telefon+"'";
		System.out.println(sql);
		int countTelefon = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				countTelefon = countTelefon +1;
				System.out.println(countTelefon);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}
		return countTelefon;
	}
	public int proveriMail(String mail) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT mail FROM korisnik WHERE mail = '"+mail+"'";
		System.out.println(sql);
		int countMail = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				countMail = countMail +1;
				System.out.println(countMail);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}
		return countMail;
	}

	public int proveriImeZaposlenog(String ime) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT imeZaposlenog FROM zaposleni WHERE imeZaposlenog = '"+ime+"'";
		System.out.println(sql);
		int count = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				count = count +1;
				System.out.println(count);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}return count;
		
	}
	
	public int proveriZaposlenog(String ime, char[] password) {//============================LOGIN PROVERA ZAPOSLENOG==========
		// TODO Auto-generated method stub
		String lozinka = String.valueOf(password);
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT imeZaposlenog FROM zaposleni WHERE imeZaposlenog = '"+ime+"'AND lozinka = '"+lozinka+"'";
		System.out.println(sql);
		int count = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				count = count +1;
				System.out.println(count);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}return count;
	}

	public int proveriPrezimeZaposlenog(String prezime) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT prezimeZaposlenog FROM zaposleni WHERE prezimeZaposlenog = '"+prezime+"'";
		System.out.println(sql);
		int countPrezime = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				countPrezime = countPrezime +1;
				System.out.println(countPrezime);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}return countPrezime;
	}

	public int proveriLozinkuZaposlenog(String lozinka) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String  sql = "SELECT lozinka FROM zaposleni WHERE lozinka = '"+lozinka+"'";
		System.out.println(sql);
		int countLozinka = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				countLozinka = countLozinka +1;
				System.out.println(countLozinka);
		}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}
		return countLozinka;
	}

	public ArrayList<TabelaRezervacije> vratiTabeluRezervacije() {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		Statement st = null;
		ArrayList<TabelaRezervacije>alTabelaRezervacije = new ArrayList<>();
		
		String sql = "SELECT predstava.idPredstave, pozoriste.idPozorista, termin.datumTermina"
				+ ", termin.vremeTermina, predstava.nazivPredstave, pozoriste.nazivPozorista"
				+ ", sala.nazivSale, termin.cenaKarte FROM pozoriste "
				+ "INNER JOIN sala on sala.idPozorista = pozoriste.idPozorista "
				+ "INNER JOIN predstava on predstava.idSale = sala.idSale "
				+ "INNER JOIN termin on termin.idPredstave = predstava.idPredstave ";
		
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				TabelaRezervacije tr = new TabelaRezervacije();
				tr.setIdPredstave(rs.getInt("idPredstave"));
				tr.setIdPozorista(rs.getInt("idPozorista"));
				tr.setDatum(rs.getDate("datumTermina"));
				tr.setVreme(rs.getTime("vremeTermina"));
				tr.setNazivPredstave(rs.getString("nazivPredstave"));
				tr.setNazivPozorista(rs.getString("nazivPozorista"));
				tr.setNazivSale(rs.getString("nazivSale"));
				tr.setCenaKarte(rs.getDouble("cenaKarte"));
				
				alTabelaRezervacije.add(tr);
				
			}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alTabelaRezervacije;
	}


	public ArrayList<Termin> vratiTermine() {
		// TODO Auto-generated method stub
		ArrayList<Termin>alTermin = new ArrayList<>();
		ResultSet rs = null;
		Statement st = null;
		
		String sql = "SELECT * FROM termin";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Termin t = new Termin();
				t.setIdTermina(rs.getInt("idTermina"));
				t.setDatumTermina(rs.getDate("datumTermina"));
				t.setVremeTermina(rs.getTime("vremeTermina"));
				t.setUkupanBrojKarata(rs.getInt("ukupanBrojKarata"));
				t.setIdPredstave(rs.getInt("idPredstave"));
				t.setPreostaliBrojKarata(rs.getInt("preostaliBrojKarata"));
				t.setCenaKarte(rs.getDouble("cenaKarte"));
				
				alTermin.add(t);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alTermin;
	}


	public void unesiTermin(String datum, String vreme, String ukupanBrojKarata, String idPredstave,
			String preostaliBrojKarata, String cenaKarte) {
		// TODO Auto-generated method stub
		
		//String lozinka = String.valueOf(password);
		String sql = "INSERT INTO termin(datumTermina, vremeTermina, ukupanBrojKarata, idPredstave, preostaliBrojKarata, cenaKarte) VALUES (?,?,?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, datum);
			ps.setString(2, vreme);
			ps.setString(3, ukupanBrojKarata);
			ps.setString(4, idPredstave);
			ps.setString(5, preostaliBrojKarata);
			ps.setString(6, cenaKarte);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public ArrayList<Predstava> vratiPredstave() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Predstava>alPredstave = new ArrayList<>();
		
		String sql = "SELECT * FROM predstava";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
			Predstava p = new Predstava();
			p.setIdPredstave(rs.getInt("idPredstave"));
			p.setNazivPredstave(rs.getString("nazivPredstave"));
			p.setReziser(rs.getString("reziser"));
			p.setGlumci(rs.getString("glumci"));
			p.setIdSale(rs.getInt("idSale"));
			p.setZanr(rs.getString("zanr"));
			p.setOpis(rs.getString("opis"));
			
			alPredstave.add(p);
			
			}
		} catch (SQLException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alPredstave;
		}


	public void izbrisiTermin(int idTermina) {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM termin where idTermina='"+idTermina+"'";
		System.out.println(sql);
		
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(sql);  
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void promeniTermin(int idTermina, String datum, String vreme, String ukupanBrojKarata, String idPredstave,
			String preostaliBrojKarata, String cenaKarte) {
		// TODO Auto-generated method stub
		String sql = "UPDATE termin SET datumTermina ='"+datum+"'"
				+ ", vremeTermina ='"+vreme+"', ukupanBrojKarata ='"+ukupanBrojKarata+"'"
						+ ", idPredstave ='"+idPredstave+"', preostaliBrojKarata ='"+preostaliBrojKarata+"'"
						+", cenaKarte ='"+cenaKarte+"'"
								+ " WHERE idTermina ='"+idTermina+"'";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArrayList<Sala> vratiSale() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Sala>alSala = new ArrayList<>();
		
		String sql = "SELECT * FROM sala";
		System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Sala s = new Sala();
				s.setIdSale(rs.getInt("idSale"));
				s.setNazivSale(rs.getString("nazivSale"));
				s.setKapacitetSale(rs.getInt("kapacitetSale"));
				s.setIdPozorista(rs.getInt("idPozorista"));
				
				alSala.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alSala;
	}


	public void upisiRezervaciju(int brojUlaznica, int idKorisnik, int idTermina, double ukupnaCena) {
		// TODO Auto-generated method stub
		String brojU = String.valueOf(brojUlaznica);
		String idKor = String.valueOf(idKorisnik);
		String idTer = String.valueOf(idTermina);
		String ukupCen = String.valueOf(ukupnaCena);
		System.out.println(brojU +" "+ idKor +" "+ idTer +" "+ ukupCen);
		String sql = "INSERT INTO rezervacija(brojUlaznica, idKorisnika, idTermina, ukupnaCena) VALUES (?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, brojU);
			ps.setString(2, idKor);
			ps.setString(3, idTer);
			ps.setString(4, ukupCen);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void azurirajStanje(int idTermina, int brojUlaznica) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		String sql1 = "SELECT termin.preostaliBrojKarata FROM termin WHERE termin.idTermina ='"+idTermina+"'";
		System.out.println(sql1);
		brojKarata = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql1);
			while(rs.next()) {
			brojKarata=rs.getInt("preostaliBrojKarata");
			System.out.println(brojKarata);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int noviPreostalibrojKarata = brojKarata-brojUlaznica;
		System.out.println("noviBroj "+noviPreostalibrojKarata);
		String sql = "UPDATE termin SET preostaliBrojKarata ='"+noviPreostalibrojKarata+"'WHERE idTermina ='"+idTermina+"'";
		System.out.println(sql);
		
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public ArrayList<Rezervacija> vratiRezervacije() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Rezervacija>alRezervacije = new ArrayList<>();
		String sql = "SELECT * FROM rezervacija";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Rezervacija r = new Rezervacija();
				r.setIdRezervacije(rs.getInt("idRezervacije"));
				r.setBrojUlaznica(rs.getInt("brojUlaznica"));
				r.setIdKorisnika(rs.getInt("idKorisnika"));
				r.setIdTermina(rs.getInt("idTermina"));
				r.setUkupnaCena(rs.getDouble("ukupnaCena"));
				
				alRezervacije.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alRezervacije;
	}


	public void otkaziRezervaciju(int idRezervacijeOtkaz) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM rezervacija WHERE idRezervacije ='"+idRezervacijeOtkaz+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void unesiPredstavu(String nazivPredstave, String reziser, String glumci, String idSale, String zanr,
			String opis) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO predstava(nazivPredstave, reziser, glumci, idSale, zanr, opis) VALUES (?,?,?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nazivPredstave);
			ps.setString(2, reziser);
			ps.setString(3, glumci);
			ps.setString(4, idSale);
			ps.setString(5, zanr);
			ps.setString(6, opis);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void izbrisiPredstavu(int idPredstave) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM predstava WHERE idPredstave ='"+idPredstave+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void promeniPredstavu(int idPredstave, String nazivPredstave, String reziser, String glumci, String idSale,
			String zanr, String opis) {
		// TODO Auto-generated method stub
		String sql = "UPDATE predstava SET  nazivPredstave ='"+nazivPredstave+"'"
				+ ", reziser ='"+reziser+"', glumci ='"+glumci+"'"
						+ ", idSale ='"+idSale+"', zanr ='"+zanr+"', opis ='"+opis+"'"
								+ " WHERE idPredstave ='"+idPredstave+"'";
	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void unesiPozoriste(String nazivPozorista, String adresa, String cena, String opis) {
		String sql = "INSERT INTO pozoriste(nazivPozorista, adresa, opisPozorista, cenaKarte) VALUES (?,?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nazivPozorista);
			ps.setString(2, adresa);
			ps.setString(3, opis);
			ps.setString(4, cena);

			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void promeniPozoriste(int idPozorista, String nazivPozorista, String adresa, String cena, String opis) {
		// TODO Auto-generated method stub
		String sql = "UPDATE pozoriste SET  nazivPozorista ='"+nazivPozorista+"'"
				+ ", adresa ='"+adresa+"', cenaKarte ='"+cena+"'"
						+ ", opisPozorista ='"+opis+"' WHERE idPozorista ='"+idPozorista+"'";
	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void izbrisiPozoriste(int idPozorista) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM pozoriste WHERE idPozorista ='"+idPozorista+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void unesiSalu(String nazivSale, String kapacitetSale , String idPozorista) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO sala(nazivSale, kapacitetSale, idPozorista) VALUES (?,?,?)";
		
		System.out.println(sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nazivSale);
			ps.setString(2, kapacitetSale);
			ps.setString(3, idPozorista);

			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	public void promeniSalu(int idSale, String nazivSale, String kapacitetSale, String idPozorista) {
		// TODO Auto-generated method stub
		String sql = "UPDATE sala SET  nazivSale ='"+nazivSale+"'"
				+ ", kapacitetSale ='"+kapacitetSale+"', idPozorista ='"+idPozorista+"'"
								+ " WHERE idSale ='"+idSale+"'";
	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void izbrisiSalu(int idSale) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM sala WHERE idSale ='"+idSale+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			System.out.println(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
