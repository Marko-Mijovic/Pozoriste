package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Korisnik;
import domen.Predstava;
import domen.Rezervacija;
import domen.TabelaRezervacije;
import domen.Termin;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import digitalclock.DigitalClock;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class MojeRezervacije extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int idKorisnik;
	private String korisnickoIme;
	
	private JLabel label_2;
	
	private int idTermina;
	private int brojUlaznica;
	
	private double ukupnaCena;
	
	private int idRezervacijeOtkaz;
	
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTextField tfUkupnaCena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MojeRezervacije frame = new MojeRezervacije();
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
	public MojeRezervacije() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 804, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 752, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 11, 732, 188);
		panel.add(scrollPane);
		
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
		btnNazad.setBounds(10, 570, 198, 38);
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
		btnPocetnaStrana.setBounds(565, 570, 198, 38);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnOtkazi = new JButton("Otkazi rezervaciju");
		btnOtkazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().otkaziRezervaciju(idRezervacijeOtkaz);
				System.out.println(idRezervacijeOtkaz);
				int redovi = table.getSelectedRow();
				
				idTermina = Integer.valueOf(table.getModel().getValueAt(redovi, 1).toString());
				brojUlaznica =- Integer.valueOf(table.getModel().getValueAt(redovi, 2).toString());
				System.out.println(brojUlaznica);
				Kontroler.getInstanca().azurirajStanje(idTermina, brojUlaznica);
				osveziFormu();
				
			}
		});
		btnOtkazi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOtkazi.setBounds(10, 493, 204, 38);
		contentPane.add(btnOtkazi);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziFormu();
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(565, 493, 198, 38);
		contentPane.add(btnOsvezi);
		
		JButton btnRezervacije = new JButton("Korisnik portal");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(Korisnik k:Kontroler.getAlKorisnik()) {
					if(idKorisnik == k.getIdKorisnika()) {
						korisnickoIme = k.getKorisnickoIme();
						
					}
				}
				KorisnikMedjuForma kmf = new KorisnikMedjuForma(korisnickoIme);
				kmf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnRezervacije.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnRezervacije.setBounds(286, 570, 204, 38);
		contentPane.add(btnRezervacije);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(565, 303, 198, 153);
		contentPane.add(calendar);
		
		JLabel label = new JLabel("Danasnji datum:");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		label.setBounds(563, 272, 215, 30);
		contentPane.add(label);
		
		DigitalClock digitalClock = new DigitalClock();
		digitalClock.setBounds(564, 232, 198, 49);
		contentPane.add(digitalClock);
		
		tfUkupnaCena = new JTextField();
		tfUkupnaCena.setHorizontalAlignment(SwingConstants.CENTER);
		tfUkupnaCena.setEditable(false);
		tfUkupnaCena.setBounds(430, 494, 111, 38);
		contentPane.add(tfUkupnaCena);
		tfUkupnaCena.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ukupna cena rezervacija:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(224, 493, 166, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("korisnik:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Gisha", Font.ITALIC, 15));
		label_1.setBounds(59, 256, 86, 24);
		contentPane.add(label_1);
		
		label_2 = new JLabel("<dynamic>");
		label_2.setText(KorisnikMedjuForma.getKorisnik());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Gisha", Font.PLAIN, 15));
		label_2.setBounds(112, 257, 96, 24);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\wallhaven-162795.jpg"));
		lblNewLabel.setBounds(-368, -132, 1289, 1248);
		contentPane.add(lblNewLabel);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int redovi = table.getSelectedRow();
				idRezervacijeOtkaz = (Integer.valueOf(table.getModel().getValueAt(redovi, 0).toString()));
			}
		});
		dtm.setColumnCount(0);
		
		Object[]kolone = new Object[7];
		kolone[0] = "ID Rezervacije";
		kolone[1]= "ID Termina";
		kolone[2] = "Broj Ulaznica";
		kolone[3] = "naziv predstave";
		kolone[4] = "datum";
		kolone[5] = "vreme";
		kolone[6] = "Ukupna Cena";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		
		osveziFormu();
	}

	private void osveziFormu() {
		// TODO Auto-generated method stub
		ukupnaCena=0;
		dtm.setRowCount(0);
		String nazivKorisnika = KorisnikMedjuForma.getKorisnik();
		for(Korisnik k:Kontroler.getInstanca().vratiKorisnike()) {
			if(nazivKorisnika.equals(k.getKorisnickoIme())) {
			idKorisnik = k.getIdKorisnika();
			}
		}
		for(Rezervacija r:Kontroler.getInstanca().vratiRezervacije()) {
			
		if(idKorisnik == r.getIdKorisnika()) {
			
			for(Termin t:Kontroler.getInstanca().vratiTermine()) {
					if(r.getIdTermina()==t.getIdTermina()) {
						
						
						Object[]redovi = new Object[7];
						redovi[0]=r.getIdRezervacije();
						redovi[1]=r.getIdTermina();
						redovi[2]=r.getBrojUlaznica();
						for(Predstava p:Kontroler.getInstanca().vratiPredstave()) {
							if(t.getIdPredstave()==p.getIdPredstave()) {
						redovi[3]=p.getNazivPredstave();
							}
						}
						redovi[4]=t.getDatumTermina();
						redovi[5]=t.getVremeTermina();
						redovi[6]=r.getUkupnaCena();
						
						ukupnaCena += Double.valueOf(r.getUkupnaCena());
						
						dtm.addRow(redovi);
						}
					}
			}
		}
		tfUkupnaCena.setText(String.valueOf(ukupnaCena));
	}
}
	

