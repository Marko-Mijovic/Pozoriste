package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domen.Pozoriste;
import domen.Zaposleni;
import kontroler.Kontroler;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminSignupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfImeZaposlenog;
	
	private String imeZaposlenog;
	private String prezimeZaposlenog;
	private char[] lozinka;
	private boolean pomocna;
	private static String password = "pass";
	private JPasswordField pfLozinka;
	private JTextField tfPrezimeZaposlenog;
	private JComboBox comboBox;
	
	private int idPozorista;
	private String nazivPozorista;


	public int getIdPozorista() {
		return idPozorista;
	}

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
	public AdminSignupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 564, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKreiraj = new JButton("Kreiraj!");
		btnKreiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idPozoristaP = String.valueOf(idPozorista);
				try {
					imeZaposlenog = tfImeZaposlenog.getText();
					prezimeZaposlenog = tfPrezimeZaposlenog.getText();
					lozinka = pfLozinka.getPassword();
					
					int countIme = 0;
					int countPrezime = 0;
					int countLozinka = 0;
					
					Kontroler.getInstanca().proveriImeZaposlenog(tfImeZaposlenog.getText());
					countIme = Kontroler.getCount();
					System.out.println(countIme);
					if (countIme == 1) {
						JOptionPane.showMessageDialog(null, "Ovo ime zaposlenog vec postoji, uzmite drugo");
						tfImeZaposlenog.setText("");
					} else if (countIme > 1) {
						JOptionPane.showMessageDialog(null, "Duplicate user, access denied");
					} else {
						JOptionPane.showMessageDialog(null, "Uspesno ste uneli ime zaposlenog");
					}
					
					Kontroler.getInstanca().proveriPrezimeZaposlenog(tfPrezimeZaposlenog.getText());
					countPrezime = Kontroler.getCountTelefon();
					System.out.println(countPrezime);
					if (countPrezime == 1) {
						JOptionPane.showMessageDialog(null, "Ovo prezime zaposlenog vec postoji, uzmite drugo");
						tfPrezimeZaposlenog.setText("");
					} else if (countPrezime > 1) {
						JOptionPane.showMessageDialog(null, "Duplicate password, access denied");
					} else {
						JOptionPane.showMessageDialog(null, "Uspesno ste uneli prezime zaposlenog");
					}

					Kontroler.getInstanca().proveriLozinkuZaposlenog(pfLozinka.getText());
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
					
				
					
					
					if (countIme == 0 && countLozinka == 0 && countPrezime == 0) {
						Kontroler.getInstanca().unesiZaposlenog(tfImeZaposlenog.getText(),tfPrezimeZaposlenog.getText(), pfLozinka.getPassword(), idPozoristaP);
						JOptionPane.showMessageDialog(null, "Uspesno ste kreirali account zaposlenog!");
						tfImeZaposlenog.setText("");
						tfPrezimeZaposlenog.setText("");
						pfLozinka.setText("");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
				}
			}
		});
				
		btnKreiraj.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnKreiraj.setBounds(200, 222, 149, 34);
		contentPane.add(btnKreiraj);
		
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
		
		JCheckBox cbPrikaziLozinku = new JCheckBox("prikazi lozinku");
		cbPrikaziLozinku.setFont(new Font("Gisha", Font.PLAIN, 12));
		cbPrikaziLozinku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPrikaziLozinku.isSelected()) {
					pfLozinka.setEchoChar((char)0);
				}else {
					pfLozinka.setEchoChar('•');
				}
				
			}
		});
		cbPrikaziLozinku.setBounds(389, 108, 137, 26);
		contentPane.add(cbPrikaziLozinku);
		
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
				
				AdminLoginFrame alf = new AdminLoginFrame();
				alf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.setBounds(389, 222, 149, 34);
		contentPane.add(btnNazad);
		
		JLabel lblNaslov = new JLabel("Admin signup");
		lblNaslov.setForeground(Color.LIGHT_GRAY);
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblNaslov.setBounds(389, 11, 137, 26);
		contentPane.add(lblNaslov);
		
		JLabel lblPozoriste = new JLabel("Pozoriste");
		lblPozoriste.setForeground(Color.WHITE);
		lblPozoriste.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblPozoriste.setBounds(43, 145, 149, 34);
		contentPane.add(lblPozoriste);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nazivPozorista = comboBox.getSelectedItem().toString();                //ovde smo dodelili nazivPozorista
				for(Pozoriste p:Kontroler.getAlPozoriste()) {				//ovde smo dodelili idPozorista
					if(nazivPozorista == p.getNazivPozorista()) {						// njih cemo posle da prosledjujemo
						idPozorista = p.getIdPozorista();
					}
				}
				

			}
		});
		comboBox.setBounds(200, 150, 338, 29);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\theater-432045_640.jpg"));
		lblNewLabel.setBounds(-50, -46, 660, 364);
		contentPane.add(lblNewLabel);
		
		osveziComboBox();
	}

	private void osveziComboBox() {
		// TODO Auto-generated method stub
		for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()){
			if(comboBox.equals(p.getNazivPozorista())) {
				
			}else {
			//comboBox.addItem(p.getNazivPozorista());
				comboBox.addItem(p.getNazivPozorista());
		}
		}
	}
}
