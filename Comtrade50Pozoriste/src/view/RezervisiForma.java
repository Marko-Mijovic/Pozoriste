package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import domen.Korisnik;
import domen.Pozoriste;
import domen.Predstava;
import domen.Rezervacija;
import domen.Sala;
import domen.Termin;
import kontroler.Kontroler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import digitalclock.DigitalClock;
import digitalclock.Main;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class RezervisiForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfBrojUlaznica;
	private JLabel lblCenaKarte;
	private JLabel lblSala;
	private JLabel lblVreme;
	private JLabel lblDatum;
	private JLabel lblPozoriste;
	private JLabel lblPredstava;
	
	private JPanel panel_6;
	
	private int trenutniPreostaliBrojKarta;
	
	private DigitalClock digitalClock;
	
	private String datumRezervacije;
	
	private int brojUlaznica;
	
	private double ukupnaCena;
	
	private static ArrayList<Rezervacija>alRezervacije = new ArrayList<>();
	
	private int idKorisnik;
	
	private int idTermina;
	private Date datumTermina;
	private Time vremeTermina;
	private int ukupanBrojKarata;
	private int idPredstave;
	private static int preostaliBrojKarata;
	
	private String nazivPredstave;
	private int idSale;
	private String nazivSale;
	private int idPozorista;
	private String nazivPozorista;
	private double cenaKarte;
	private JTextField tfUPDATEBROJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RezervisiForma frame = new RezervisiForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RezervisiForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 805, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		osveziFormu();
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikRezervacijaFrame krf = new KorisnikRezervacijaFrame();
				krf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.setBounds(39, 487, 191, 38);
		contentPane.add(btnNazad);
		
		JButton btnPocetnaStrana = new JButton("Pocetna strana");
		btnPocetnaStrana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikLoginFrame klf = new KorisnikLoginFrame();
				klf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnPocetnaStrana.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPocetnaStrana.setBounds(289, 487, 191, 38);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnRezervisi = new JButton("Rezervisi");
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String nazivKorisnika = KorisnikMedjuForma.getKorisnik();
				for(Korisnik k:Kontroler.getInstanca().vratiKorisnike()) {
					if(nazivKorisnika.equals(k.getKorisnickoIme())) {
					idKorisnik = k.getIdKorisnika();
					}
				}
				
				
				brojUlaznica = Integer.valueOf(tfBrojUlaznica.getText());
				if(brojUlaznica > preostaliBrojKarata) {
					JOptionPane.showMessageDialog(null, "Ne mozete kupiti toliko karata, preostalo je ukupno: "+preostaliBrojKarata+" karata.");
				}else if(brojUlaznica <= preostaliBrojKarata) {
				ukupnaCena = Double.valueOf(tfBrojUlaznica.getText())*cenaKarte;
				Kontroler.getInstanca().upisiRezervaciju(brojUlaznica, idKorisnik, idTermina, ukupnaCena);
				Kontroler.getInstanca().azurirajStanje(idTermina, brojUlaznica);
				
				for(Termin t:Kontroler.getInstanca().vratiTermine()) {
					if(idTermina == t.getIdTermina()) {
						tfUPDATEBROJ.setText(String.valueOf(preostaliBrojKarata - brojUlaznica));
					}
				}
				osveziTF();
				osveziFormu();
				JOptionPane.showMessageDialog(null, "Uspesna rezervacija!");
				tfBrojUlaznica.setText("");
				}
			}
			
		});
		btnRezervisi.setBackground(Color.RED);
		btnRezervisi.setForeground(Color.BLACK);
		btnRezervisi.setFont(new Font("Maiandra GD", Font.PLAIN, 30));
		btnRezervisi.setBounds(539, 487, 191, 38);
		contentPane.add(btnRezervisi);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(39, 11, 191, 101);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLabel1 = new JLabel("Predstava:");
		lblLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel1.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblLabel1.setBounds(10, 11, 171, 24);
		panel.add(lblLabel1);
		
		lblPredstava = new JLabel(nazivPredstave);
		System.out.println(nazivPredstave);
		lblPredstava.setHorizontalAlignment(SwingConstants.CENTER);
		lblPredstava.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblPredstava.setBounds(10, 46, 171, 24);
		panel.add(lblPredstava);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(289, 11, 191, 101);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblpoz1 = new JLabel("Pozoriste:");
		lblpoz1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpoz1.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblpoz1.setBounds(10, 11, 171, 24);
		panel_1.add(lblpoz1);
		
		lblPozoriste = new JLabel(nazivPozorista);
		System.out.println(nazivPozorista);
		lblPozoriste.setHorizontalAlignment(SwingConstants.CENTER);
		lblPozoriste.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblPozoriste.setBounds(-13, 46, 217, 24);
		panel_1.add(lblPozoriste);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GREEN, 2));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(539, 11, 191, 101);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDat = new JLabel("Datum:");
		lblDat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDat.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblDat.setBounds(10, 11, 171, 24);
		panel_2.add(lblDat);
		
		lblDatum = new JLabel(String.valueOf(datumTermina));
		System.out.println(datumTermina);
		lblDatum.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatum.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblDatum.setBounds(10, 46, 171, 24);
		panel_2.add(lblDatum);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.MAGENTA, 2));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(39, 133, 191, 101);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblVre = new JLabel("Vreme:");
		lblVre.setHorizontalAlignment(SwingConstants.CENTER);
		lblVre.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblVre.setBounds(10, 11, 171, 24);
		panel_3.add(lblVre);
		
		lblVreme = new JLabel(String.valueOf(vremeTermina));
		System.out.println(vremeTermina);
		lblVreme.setHorizontalAlignment(SwingConstants.CENTER);
		lblVreme.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblVreme.setBounds(10, 46, 171, 24);
		panel_3.add(lblVreme);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.ORANGE, 2));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(289, 133, 191, 101);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSa = new JLabel("Sala:");
		lblSa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSa.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblSa.setBounds(10, 11, 171, 24);
		panel_4.add(lblSa);
		
		lblSala = new JLabel(nazivSale);
		System.out.println(nazivSale);
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblSala.setBounds(10, 46, 171, 24);
		panel_4.add(lblSala);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.PINK, 2));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(539, 133, 191, 101);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblCen = new JLabel("Cena karte:");
		lblCen.setHorizontalAlignment(SwingConstants.CENTER);
		lblCen.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblCen.setBounds(10, 11, 171, 24);
		panel_5.add(lblCen);
		
		lblCenaKarte = new JLabel(String.valueOf(cenaKarte));
		System.out.println(cenaKarte);
		lblCenaKarte.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenaKarte.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		lblCenaKarte.setBounds(10, 46, 171, 24);
		panel_5.add(lblCenaKarte);
		
		JLabel lblRez = new JLabel("Broj karata za rezervaciju:");
		lblRez.setForeground(Color.WHITE);
		lblRez.setHorizontalAlignment(SwingConstants.CENTER);
		lblRez.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblRez.setBounds(527, 379, 215, 30);
		contentPane.add(lblRez);
		
		tfBrojUlaznica = new JTextField();
		tfBrojUlaznica.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfBrojUlaznica.setBounds(539, 420, 191, 38);
		contentPane.add(tfBrojUlaznica);
		tfBrojUlaznica.setColumns(10);
		
		panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		panel_6.setBorder(new LineBorder(Color.WHITE, 2));
		panel_6.setBounds(539, 271, 191, 97);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblPreostaloKarata = new JLabel("Preostalo karata:");
		lblPreostaloKarata.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreostaloKarata.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblPreostaloKarata.setBounds(-14, 11, 215, 30);
		panel_6.add(lblPreostaloKarata);
		
		tfUPDATEBROJ = new JTextField();
		tfUPDATEBROJ.setForeground(SystemColor.desktop);
		tfUPDATEBROJ.setBounds(10, 40, 174, 35);
		panel_6.add(tfUPDATEBROJ);
		tfUPDATEBROJ.setBackground(SystemColor.activeCaption);
		tfUPDATEBROJ.setHorizontalAlignment(SwingConstants.CENTER);
		tfUPDATEBROJ.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 26));
		tfUPDATEBROJ.setColumns(10);
		
		updateLabelu();
		
		digitalClock = new DigitalClock();
		digitalClock.setBounds(289, 305, 191, 153);
		contentPane.add(digitalClock);
		
		JLabel lblTrenutnoVreme = new JLabel("Trenutno vreme:");
		lblTrenutnoVreme.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrenutnoVreme.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblTrenutnoVreme.setBounds(276, 271, 215, 30);
		contentPane.add(lblTrenutnoVreme);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(39, 271, 191, 30);
		contentPane.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("POZURI!");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Leelawadee", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 171, 30);
		panel_7.add(lblNewLabel);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(289, 271, 191, 30);
		contentPane.add(panel_8);
		
		JTextPane txtpnVremeOtkucavaRezervisi = new JTextPane();
		txtpnVremeOtkucavaRezervisi.setText("VREME OTKUCAVA, REZERVISI KARTU STO PRE!     \u279E");
		txtpnVremeOtkucavaRezervisi.setForeground(Color.BLUE);
		txtpnVremeOtkucavaRezervisi.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		txtpnVremeOtkucavaRezervisi.setBackground(SystemColor.menu);
		txtpnVremeOtkucavaRezervisi.setBounds(39, 305, 191, 96);
		contentPane.add(txtpnVremeOtkucavaRezervisi);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\wallhaven-38647.jpg"));
		lblNewLabel_1.setBounds(-104, -21, 1077, 737);
		contentPane.add(lblNewLabel_1);
		
		
		osveziTF();
		osveziFormu();
	}

	private void osveziTF() {
		// TODO Auto-generated method stub
		idTermina = KorisnikRezervacijaFrame.getIdTermin();
		for(Termin t:Kontroler.getInstanca().vratiTermine()) {
			if(idTermina == t.getIdTermina()) {
				preostaliBrojKarata = t.getPreostaliBrojKarata();
				tfUPDATEBROJ.setText(String.valueOf(preostaliBrojKarata));
			}
		}
	}

	public static int getPreostaliBrojKarata() {
		return preostaliBrojKarata;
	}

	private int updateLabelu() {
		// TODO Auto-generated method stub
		int pbKarata = RezervisiForma.getPreostaliBrojKarata();
		return pbKarata;
	}

	private void osveziFormu() {
		// TODO Auto-generated method stub
		idTermina = KorisnikRezervacijaFrame.getIdTermin();
		System.out.println("idTermina "+idTermina);
		for(Termin t:Kontroler.getInstanca().vratiTermine()) {
			System.out.println("t.get id termina "+t.getIdTermina());;
			if(idTermina == t.getIdTermina()) {
				datumTermina = t.getDatumTermina();
				vremeTermina = t.getVremeTermina();
				ukupanBrojKarata = t.getUkupanBrojKarata();
				idPredstave = t.getIdPredstave();
				preostaliBrojKarata = t.getPreostaliBrojKarata();
				cenaKarte = t.getCenaKarte();
				System.out.println(preostaliBrojKarata);
		
				
			}
		}
		for(Predstava p:Kontroler.getInstanca().vratiPredstave()) {
			if(idPredstave == p.getIdPredstave()) {
				nazivPredstave = p.getNazivPredstave();
				idSale = p.getIdSale();
				
			}
		}
		for(Sala s:Kontroler.getInstanca().vratiSale()) {
			if(idSale == s.getIdSale()) {
				nazivSale = s.getNazivSale();
				idPozorista = s.getIdPozorista();
			}
		}
		for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()) {
			if(idPozorista == p.getIdPozorista()) {
				nazivPozorista = p.getNazivPozorista();
				
			}
		}
	}
}
