package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Korisnik;
import domen.Pozoriste;
import domen.TabelaRezervacije;
import domen.Termin;
import domen.Zaposleni;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import digitalclock.DigitalClock;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class KorisnikRezervacijaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfPretragaPredstave;
	
	private static int idTermin;
	
	private String korisnik;
	private String mail;
	
	private JRadioButton rbNaziv, rbDeoNaziva;
	ButtonGroup bg=new ButtonGroup();
	
	private int idPozorista;
	
	private JComboBox cbPozorista;
	
	private boolean pomocna = false;
	
	private static String izabraniDatum;
	private Date izabraniDatum1;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private int count=1;
	
	private static DefaultTableModel dtm = new DefaultTableModel();
	private ArrayList<TabelaRezervacije>alTabelaRezervacijePomocna = new ArrayList<>();
	
	private DatumFilterFrame dff = new DatumFilterFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorisnikRezervacijaFrame frame = new KorisnikRezervacijaFrame();
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
	public KorisnikRezervacijaFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 910, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 884, 342);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		table = new JTable(dtm);
		table.setFont(new Font("Gisha", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAlignmentX(CENTER_ALIGNMENT);
		scrollPane.setAlignmentY(CENTER_ALIGNMENT);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 11, 864, 320);
		panel.add(scrollPane);
		
		
		JLabel lblIzaberitePozoriste = new JLabel("Izaberite pozoriste:");
		lblIzaberitePozoriste.setForeground(Color.WHITE);
		lblIzaberitePozoriste.setFont(new Font("Gisha", Font.PLAIN, 16));
		lblIzaberitePozoriste.setBounds(10, 394, 135, 19);
		contentPane.add(lblIzaberitePozoriste);
		
		cbPozorista = new JComboBox();
		cbPozorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nazivPozorista = cbPozorista.getSelectedItem().toString();
				for(Pozoriste p:Kontroler.getInstanca().vratiPozoriste()) {
					if(nazivPozorista.equals(p.getNazivPozorista())) {
						idPozorista = p.getIdPozorista();
					}
				}
				
			}
		});
		cbPozorista.setFont(new Font("Gisha", Font.PLAIN, 16));
		cbPozorista.setBounds(228, 387, 198, 32);
		contentPane.add(cbPozorista);
		
		JLabel lblPretragaPredstave = new JLabel("Pretraga predstave:");
		lblPretragaPredstave.setForeground(Color.WHITE);
		lblPretragaPredstave.setFont(new Font("Gisha", Font.PLAIN, 16));
		lblPretragaPredstave.setBounds(10, 487, 135, 19);
		contentPane.add(lblPretragaPredstave);
		
		tfPretragaPredstave = new JTextField();
		tfPretragaPredstave.setFont(new Font("Gisha", Font.PLAIN, 12));
		tfPretragaPredstave.setColumns(10);
		tfPretragaPredstave.setBounds(228, 482, 198, 32);
		contentPane.add(tfPretragaPredstave);
		
		rbNaziv = new JRadioButton("Pretraga po nazivu", true);
		rbNaziv.setFont(new Font("Gisha", Font.PLAIN, 16));
		rbNaziv.setBounds(10, 426, 155, 32);
		contentPane.add(rbNaziv);
		bg.add(rbNaziv); 
		
		rbDeoNaziva = new JRadioButton("Pretraga po delu naziva", false);
		rbDeoNaziva.setFont(new Font("Gisha", Font.PLAIN, 16));
		rbDeoNaziva.setBounds(228, 426, 198, 32);
		contentPane.add(rbDeoNaziva);
		bg.add(rbDeoNaziva); 
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(464, 364, 10, 283);
		contentPane.add(separator);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu();
				
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(735, 424, 149, 34);
		contentPane.add(btnOsvezi);
		
		JButton btnRezervisi = new JButton("Rezervisi");
		btnRezervisi.setForeground(new Color(255, 0, 0));
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int red=table.getSelectedRow();
				String datum=(table.getValueAt(red, 0).toString());
				String vreme=(table.getValueAt(red, 1).toString());
				String nazivPredstave=table.getValueAt(red, 2).toString();
				for(TabelaRezervacije tr:Kontroler.getInstanca().vratiTabeluRezervacije()) {
					if(datum.equals(tr.getDatum().toString()) && vreme.equals(tr.getVreme().toString()) && nazivPredstave.equals(tr.getNazivPredstave())) {
						int idPredstave = tr.getIdPredstave();
						System.out.println("p"+idPredstave);
						for(Termin t:Kontroler.getInstanca().vratiTermine()) {
							if(idPredstave == t.getIdPredstave() && datum.equals(t.getDatumTermina().toString()) && vreme.equals(t.getVremeTermina().toString())) {
								idTermin = t.getIdTermina();
								System.out.println("t "+idTermin);
								
								RezervisiForma rf = new RezervisiForma();
								rf.setVisible(true);
								setVisible(false);
								dispose();
							}
						}
					}
				}
				}
		});
		btnRezervisi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnRezervisi.setBounds(528, 538, 149, 34);
		contentPane.add(btnRezervisi);
		
		
		
		JButton btnDatumFilter = new JButton("Datum filter");
		btnDatumFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(count == 1) {
					dff.setVisible(true);
					count++;
					System.out.println(count);
				}else if(count == 3) {
					dff.setVisible(false);
					dff.getDefaultCloseOperation();
					count = count - 3;	
					System.out.println(count);
				}
				count++;
			}
			
		});
		btnDatumFilter.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnDatumFilter.setBounds(528, 480, 149, 34);
		contentPane.add(btnDatumFilter);
		
		JButton btnDetaljiPredstave = new JButton("Detalji predstave");
		btnDetaljiPredstave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int redovi = table.getSelectedRow();
				String nazivPredstave = (table.getModel().getValueAt(redovi, 4).toString());
				System.out.println(nazivPredstave);
				if(nazivPredstave.equals("Neki to vole vruce")) {
				DetaljiPredstaveVruce dp = new DetaljiPredstaveVruce();
				dp.setVisible(true);
				setVisible(false);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Ne postoje detalji za trazenu predstavu.");
				}
				
			}
		});
		btnDetaljiPredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnDetaljiPredstave.setBounds(529, 424, 148, 34);
		contentPane.add(btnDetaljiPredstave);
		
		JButton btnMojeRezervacije = new JButton("Moje rezervacije");
		btnMojeRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MojeRezervacije mr = new MojeRezervacije();
				mr.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnMojeRezervacije.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnMojeRezervacije.setBounds(735, 538, 149, 34);
		contentPane.add(btnMojeRezervacije);
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pretraziPodatke();
				
			}
		});
		btnPretrazi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPretrazi.setBounds(10, 539, 416, 33);
		contentPane.add(btnPretrazi);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikMedjuForma kmf = new KorisnikMedjuForma(korisnik);
				kmf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.setBounds(528, 595, 149, 34);
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
		btnPocetnaStrana.setBounds(735, 595, 149, 34);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnPretraziDatum = new JButton("Pretrazi datum");
		btnPretraziDatum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				izabraniDatum = String.valueOf(df.format(DatumFilterFrame.getDateChooser().getDate()));
				System.out.println(izabraniDatum);
				Object[]redovi = new Object[8];
				dtm.setRowCount(0);
				Kontroler.getInstanca().vratiTabeluRezervacije();
				ArrayList<TabelaRezervacije>alTabR = Kontroler.getAlTabelaRezervacije();
				for(TabelaRezervacije tr:alTabR) {
					System.out.println(tr.getNazivPredstave().toString());
					if(izabraniDatum.equals(tr.getDatum().toString())) {
						redovi[0] = tr.getIdPredstave();
						redovi[1] = tr.getIdPozorista();
						redovi[2] = tr.getDatum();
						redovi[3] = tr.getVreme();
						redovi[4] = tr.getNazivPredstave();
						redovi[5] = tr.getNazivPozorista();
						redovi[6] = tr.getNazivSale();
						redovi[7] = tr.getCenaKarte();
						
						dtm.addRow(redovi);
					}
				}
				
			
			}
		});
		btnPretraziDatum.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPretraziDatum.setBounds(735, 480, 149, 34);
		contentPane.add(btnPretraziDatum);
		
		JLabel label = new JLabel("korisnik:");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Gisha", Font.ITALIC, 15));
		label.setBounds(10, 605, 86, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(KorisnikMedjuForma.getKorisnik());
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Gisha", Font.PLAIN, 15));
		label_1.setBounds(78, 605, 96, 24);
		contentPane.add(label_1);
		
		DigitalClock digitalClock = new DigitalClock();
		digitalClock.setBounds(735, 365, 149, 48);
		contentPane.add(digitalClock);
		
		JLabel lblNewLabel_1 = new JLabel("Rezervisi na vreme");
		lblNewLabel_1.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 22));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(500, 374, 207, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\wallhaven-560992.jpg"));
		lblNewLabel.setBounds(-86, -12, 1387, 717);
		contentPane.add(lblNewLabel);
		
		dtm.setColumnCount(0);
		
		Object[]kolone = new Object[6];
		kolone[0]="Datum Termina";
		kolone[1]="Vreme Termina";
		kolone[2]="Naziv Predstave";
		kolone[3]="Naziv Pozorista";
		kolone[4]="Naziv Sale";
		kolone[5]="Cena Karte";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		
		Kontroler.getInstanca().vratiTabeluRezervacije();
		alTabelaRezervacijePomocna = Kontroler.getAlTabelaRezervacije();
		
		osveziTabelu();
		
		srediFormu();
	}


	public static int getIdTermin() {
		return idTermin;
	}

	protected void pretraziPodatke() {
		// TODO Auto-generated method stub
		
		String naziv=tfPretragaPredstave.getText();
		Kontroler.getInstanca().vratiTabeluRezervacije();
		if(rbNaziv.isSelected()) {
			Object[]redovi = new Object[6];
			dtm.setRowCount(0);
			
			ArrayList<TabelaRezervacije>alTabR = Kontroler.getAlTabelaRezervacije();
			for(TabelaRezervacije tr:alTabR) {
				if(idPozorista==tr.getIdPozorista() && naziv.equals(tr.getNazivPredstave().toString())) {
					redovi[0] = tr.getDatum();
					redovi[1] = tr.getVreme();
					redovi[2] = tr.getNazivPredstave();
					redovi[3] = tr.getNazivPozorista();
					redovi[4] = tr.getNazivSale();
					redovi[5] = tr.getCenaKarte();
					
					dtm.addRow(redovi);
				}
			}
		}else if(rbDeoNaziva.isSelected()){
			Object[]redovi = new Object[6];
			dtm.setRowCount(0);
			ArrayList<TabelaRezervacije>alTabR = Kontroler.getAlTabelaRezervacije();
			for(TabelaRezervacije tr:alTabR) {
				if(idPozorista==tr.getIdPozorista() && tr.getNazivPredstave().toString().contains(naziv)) {
					redovi[0] = tr.getDatum();
					redovi[1] = tr.getVreme();
					redovi[2] = tr.getNazivPredstave();
					redovi[3] = tr.getNazivPozorista();
					redovi[4] = tr.getNazivSale();
					redovi[5] = tr.getCenaKarte();
					
					dtm.addRow(redovi);
				}
			}					
		}				
	
		
		
	}

	private void srediFormu() {
		// TODO Auto-generated method stub
		
			for(Pozoriste p: Kontroler.getInstanca().vratiPozoriste()) {
				cbPozorista.addItem(p.getNazivPozorista());
			}
		}
	
	private void osveziTabelu() {
		// TODO Auto-generated method stub
		Kontroler.getInstanca().vratiTabeluRezervacije();
		alTabelaRezervacijePomocna = Kontroler.getAlTabelaRezervacije();
		dtm.setRowCount(0);
		Object[]redovi = new Object[6];
		
		for(TabelaRezervacije tr:alTabelaRezervacijePomocna) {
			redovi[0] = tr.getDatum();
			redovi[1] = tr.getVreme();
			redovi[2] = tr.getNazivPredstave();
			redovi[3] = tr.getNazivPozorista();
			redovi[4] = tr.getNazivSale();
			redovi[5] = tr.getCenaKarte();
			
			dtm.addRow(redovi);
		}
	}

	public static void pretraziDatum(String izabraniDatum2) {
		// TODO Auto-generated method stub
		
		izabraniDatum = izabraniDatum2;
		System.out.println(izabraniDatum);
		dtm.setRowCount(0);
		Object[]redovi = new Object[8];
		Kontroler.getInstanca().vratiTabeluRezervacije();
		ArrayList<TabelaRezervacije>alTabR = Kontroler.getAlTabelaRezervacije();
		for(TabelaRezervacije tr:alTabR) {
			System.out.println(tr.getNazivPredstave().toString());
			if(izabraniDatum.equals(tr.getDatum().toString())) {
				redovi[0] = tr.getIdPredstave();
				redovi[1] = tr.getIdPozorista();
				redovi[2] = tr.getDatum();
				redovi[3] = tr.getVreme();
				redovi[4] = tr.getNazivPredstave();
				redovi[5] = tr.getNazivPozorista();
				redovi[6] = tr.getNazivSale();
				redovi[7] = tr.getCenaKarte();
				
				dtm.addRow(redovi);
			}
		}

	}
}
