package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domen.Korisnik;
import domen.Zaposleni;
import kontroler.Kontroler;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLoginFrame extends JFrame {


	private JPanel contentPane;
	private JTextField tfImeZaposlenog;
	
	private String imeZaposlenog;
	private String prezimeZaposlenog;
	private char[] lozinka;
	private boolean pomocna;
	private static String password = "pass";
	private JPasswordField pfLozinka;
	private JTextField tfPrezimeZaposlenog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorisnikLoginFrame frame = new KorisnikLoginFrame();
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
	public AdminLoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 564, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPrijaviteSe = new JButton("Prijavite se");
		btnPrijaviteSe.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPrijaviteSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imeZaposlenog = tfImeZaposlenog.getText();
				prezimeZaposlenog  = tfPrezimeZaposlenog.getText();
				lozinka = pfLozinka.getPassword();
				Kontroler.getInstanca().vratiZaposlene();
				try {
					imeZaposlenog = tfImeZaposlenog.getText();
					prezimeZaposlenog = tfPrezimeZaposlenog.getText();
					lozinka = pfLozinka.getPassword();
					
					int countIme = 0;
					
					Kontroler.getInstanca().proveriZaposlenog(tfImeZaposlenog.getText(),pfLozinka.getPassword());
					countIme = Kontroler.getCount();
					System.out.println(countIme);
				
					if (countIme == 1) {
						AdminMedjuForma amf = new AdminMedjuForma();
						amf.setVisible(true);
						setVisible(false);
						dispose();
					
					} else if (tfImeZaposlenog.getText().equals("") || tfPrezimeZaposlenog.getText().equals("") || pfLozinka.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
					} else {
						JOptionPane.showMessageDialog(null, "Korisnicko ime, prezime ili lozinka nisu ispravni!");
					}
					
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
				}
			}
		});
		btnPrijaviteSe.setBounds(200, 249, 149, 34);
		contentPane.add(btnPrijaviteSe);
		
		JButton btnKreirajNalog = new JButton("Kreiraj nalog");
		btnKreirajNalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminSignupFrame asf = new AdminSignupFrame();
				asf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnKreirajNalog.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnKreirajNalog.setBounds(389, 249, 149, 34);
		contentPane.add(btnKreirajNalog);
		
		JLabel lblImeZaposlenog = new JLabel("Ime zaposlenog:");
		lblImeZaposlenog.setForeground(Color.WHITE);
		lblImeZaposlenog.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblImeZaposlenog.setBounds(43, 21, 149, 34);
		contentPane.add(lblImeZaposlenog);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setForeground(Color.WHITE);
		lblLozinka.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblLozinka.setBounds(43, 100, 149, 34);
		contentPane.add(lblLozinka);
		
		tfImeZaposlenog = new JTextField();
		tfImeZaposlenog.setBounds(200, 26, 149, 26);
		contentPane.add(tfImeZaposlenog);
		tfImeZaposlenog.setColumns(10);
		
		pfLozinka = new JPasswordField();
		pfLozinka.setBounds(200, 108, 149, 26);
		contentPane.add(pfLozinka);
		
		JButton btnPrikaziLozinku = new JButton("prikazi lozinku");
		btnPrikaziLozinku.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pfLozinka.setEchoChar((char)0);
			}
		
			@Override
			public void mouseReleased(MouseEvent e) {
				pfLozinka.setEchoChar('•');
			}
		});
		btnPrikaziLozinku.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPrikaziLozinku.setBounds(389, 103, 149, 34);
		contentPane.add(btnPrikaziLozinku);
		
		JLabel lblPrezimeZaposlenog = new JLabel("Prezime zaposlenog:");
		lblPrezimeZaposlenog.setForeground(Color.WHITE);
		lblPrezimeZaposlenog.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblPrezimeZaposlenog.setBounds(43, 58, 149, 34);
		contentPane.add(lblPrezimeZaposlenog);
		
		tfPrezimeZaposlenog = new JTextField();
		tfPrezimeZaposlenog.setColumns(10);
		tfPrezimeZaposlenog.setBounds(200, 66, 149, 26);
		contentPane.add(tfPrezimeZaposlenog);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikLoginFrame klf = new KorisnikLoginFrame();
				klf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.setBounds(10, 249, 149, 34);
		contentPane.add(btnNazad);
		
		JLabel lblNaslov = new JLabel("Admin portal");
		lblNaslov.setForeground(Color.LIGHT_GRAY);
		lblNaslov.setBackground(Color.LIGHT_GRAY);
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblNaslov.setBounds(389, 11, 137, 26);
		contentPane.add(lblNaslov);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\theater-1477670_640.jpg"));
		lblNewLabel.setBounds(-35, -142, 732, 571);
		contentPane.add(lblNewLabel);
	}
}