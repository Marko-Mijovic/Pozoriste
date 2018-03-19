package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domen.Korisnik;
import domen.Pozoriste;
import kontroler.Kontroler;
import javax.swing.ImageIcon;
import java.awt.Color;

public class KorisnikSignupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoIme;
	
	private String korisnickoIme;
	private char[] lozinka;
	private JPasswordField pfLozinka;
	private JTextField tfTelefon;
	private JTextField tfMail;
	
	private String telefon;
	private String mail;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSignupFrame frame = new AdminSignupFrame();
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
	public KorisnikSignupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 564, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKreiraj = new JButton("Kreiraj!");
		btnKreiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korisnickoIme = tfKorisnickoIme.getText();
				lozinka = pfLozinka.getPassword();
				telefon = tfTelefon.getText();
				mail = tfMail.getText();
				if(korisnickoIme.equals("") || lozinka.equals("") || telefon.equals("") || mail.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
				}else {
					try {
						
						int countIme = 0;
						int countLozinka = 0;
						int countTelefon = 0;
						int countMail = 0;
						
						Kontroler.getInstanca().proveriKorisnika(tfKorisnickoIme.getText());
						countIme = Kontroler.getCount();
						System.out.println(countIme);
						if (countIme == 1) {
							JOptionPane.showMessageDialog(null, "Ovo korisnicko ime vec postoji, uzmite drugo");
							tfKorisnickoIme.setText("");
						} else if (countIme > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate user, access denied");
						} else {
							JOptionPane.showMessageDialog(null, "Uspesno ste uneli korisnicko ime");
						}
	
						Kontroler.getInstanca().proveriLozinku(pfLozinka.getText());
						countLozinka = Kontroler.getCountLozinka();
						System.out.println(countLozinka);
						if (countLozinka == 1) {
							JOptionPane.showMessageDialog(null, "Ova lozinka vec postoji, uzmite drugu");
							pfLozinka.setText("");
						} else if (countLozinka > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate password, access denied");
						} else {
							JOptionPane.showMessageDialog(null, "Uspesno ste uneli lozinku");
						}
						
						Kontroler.getInstanca().proveriTelefon(tfTelefon.getText());
						countTelefon = Kontroler.getCountTelefon();
						System.out.println(countTelefon);
						if (countTelefon == 1) {
							JOptionPane.showMessageDialog(null, "Ovaj broj telefona vec postoji, uzmite drugi");
							tfTelefon.setText("");
						} else if (countTelefon > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate password, access denied");
						} else {
							JOptionPane.showMessageDialog(null, "Uspesno ste uneli broj telefona");
						}
						
						Kontroler.getInstanca().proveriMail(tfMail.getText());
						countMail = Kontroler.getCountMail();
						System.out.println(countMail);
						if (countMail == 1) {
							JOptionPane.showMessageDialog(null, "Ovaj mail vec postoji, uzmite drugi");
							tfMail.setText("");
						} else if (countMail > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate password, access denied");
						} else {
							JOptionPane.showMessageDialog(null, "Uspesno ste uneli mail");
						}
	
						if (countIme == 0 && countLozinka == 0 && countTelefon == 0 && countMail == 0) {
							Kontroler.getInstanca().unesiKorisnika(tfKorisnickoIme.getText(), pfLozinka.getPassword(), tfTelefon.getText(), tfMail.getText());
							JOptionPane.showMessageDialog(null, "Korisnik uspesno kreiran");
							tfKorisnickoIme.setText("");
							pfLozinka.setText("");
							tfTelefon.setText("");
							tfMail.setText("");
						}
	
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Dogodila se greska.");
					}
				}
			}
		});
				
		btnKreiraj.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnKreiraj.setBounds(200, 222, 149, 34);
		contentPane.add(btnKreiraj);
		
		JLabel lblKorisnickoIme = new JLabel("Ime korisnika:");
		lblKorisnickoIme.setForeground(Color.WHITE);
		lblKorisnickoIme.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblKorisnickoIme.setBounds(43, 21, 149, 34);
		contentPane.add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setForeground(Color.WHITE);
		lblLozinka.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblLozinka.setBounds(43, 66, 149, 34);
		contentPane.add(lblLozinka);
		
		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.setBounds(200, 26, 149, 26);
		contentPane.add(tfKorisnickoIme);
		tfKorisnickoIme.setColumns(10);
		
		pfLozinka = new JPasswordField();
		pfLozinka.setBounds(200, 71, 149, 26);
		contentPane.add(pfLozinka);
		
		JCheckBox cbPrikaziLozinku = new JCheckBox("prikazi lozinku");
		cbPrikaziLozinku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPrikaziLozinku.isSelected()) {
					pfLozinka.setEchoChar((char)0);
				}else {
					pfLozinka.setEchoChar('•');
				}
				
			}
		});
		cbPrikaziLozinku.setBounds(389, 71, 137, 26);
		contentPane.add(cbPrikaziLozinku);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setForeground(Color.WHITE);
		lblMail.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblMail.setBounds(43, 156, 149, 34);
		contentPane.add(lblMail);
		
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
		btnNazad.setBounds(389, 222, 149, 34);
		contentPane.add(btnNazad);
		
		JLabel lblNaslov = new JLabel("Korisnik signup");
		lblNaslov.setForeground(Color.LIGHT_GRAY);
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblNaslov.setBounds(365, 11, 161, 26);
		contentPane.add(lblNaslov);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setForeground(Color.WHITE);
		lblTelefon.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblTelefon.setBounds(43, 111, 149, 34);
		contentPane.add(lblTelefon);
		
		tfTelefon = new JTextField();
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(200, 119, 149, 26);
		contentPane.add(tfTelefon);
		
		tfMail = new JTextField();
		tfMail.setColumns(10);
		tfMail.setBounds(200, 164, 149, 26);
		contentPane.add(tfMail);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\Theater.jpg"));
		lblNewLabel.setBounds(-25, -239, 941, 723);
		contentPane.add(lblNewLabel);
		
		
	}

}
