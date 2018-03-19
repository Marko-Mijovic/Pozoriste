package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Korisnik;
import kontroler.Kontroler;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KorisnikLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoIme;

	private static String korisnickoIme;
	private static char[] lozinka;
	private static int idKorisnika;
	private boolean pomocna;
	private JPasswordField pfLozinka;
	

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
	public KorisnikLoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 565, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		// ====================================================================================================================
		JButton btnPrijaviSe = new JButton("Prijavi se");
		btnPrijaviSe.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPrijaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					korisnickoIme = tfKorisnickoIme.getText();
					lozinka = pfLozinka.getPassword();
					if(tfKorisnickoIme.getText().equals("") || pfLozinka.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
					}else {
						int countIme = 0;
						
						Kontroler.getInstanca().proveriKorisnika(tfKorisnickoIme.getText(),pfLozinka.getPassword());
						countIme = Kontroler.getCount();
						System.out.println(countIme);
						//if (countIme == 1) {
							//JOptionPane.showMessageDialog(null, "Korisnik pronadjen, pristup dozvoljen");
						if (countIme == 1) {
							korisnickoIme = tfKorisnickoIme.getText();
							KorisnikMedjuForma kmf = new KorisnikMedjuForma(korisnickoIme);
							kmf.setVisible(true);
							setVisible(false);
							dispose();
						
						} else {
							JOptionPane.showMessageDialog(null, "Korisnicko ime ili lozinka nisu ispravno uneti");
							//tfKorisnickoIme.setText("");
							//pfLozinka.setText("");
						}

					}

					}catch (Exception ex) {
				}
			}
		});

		// ===================================================================================================================

		btnPrijaviSe.setBounds(200, 344, 149, 34);
		contentPane.add(btnPrijaviSe);

		JButton btnKreirajNalog = new JButton("Kreiraj nalog");
		btnKreirajNalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				KorisnikSignupFrame ksf = new KorisnikSignupFrame();
				ksf.setVisible(true);
				setVisible(false);
				dispose();

			}
		});
		btnKreirajNalog.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnKreirajNalog.setBounds(10, 344, 149, 34);
		contentPane.add(btnKreirajNalog);

		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
		lblKorisnickoIme.setForeground(Color.WHITE);
		lblKorisnickoIme.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblKorisnickoIme.setBounds(43, 55, 149, 34);
		contentPane.add(lblKorisnickoIme);

		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setForeground(Color.WHITE);
		lblLozinka.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblLozinka.setBounds(43, 103, 149, 34);
		contentPane.add(lblLozinka);

		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.setBounds(200, 60, 149, 26);
		contentPane.add(tfKorisnickoIme);
		tfKorisnickoIme.setColumns(10);

		JButton btnAdmin = new JButton("Admin portal");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminLoginFrame alf = new AdminLoginFrame();
				alf.setVisible(true);
				setVisible(false);
				dispose();

			}
		});
		btnAdmin.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnAdmin.setBounds(389, 344, 149, 34);
		contentPane.add(btnAdmin);

		pfLozinka = new JPasswordField();
		pfLozinka.setBounds(200, 108, 149, 26);
		contentPane.add(pfLozinka);

		JLabel lblKorisnikPortal = new JLabel("Korisnik portal");
		lblKorisnikPortal.setForeground(Color.LIGHT_GRAY);
		lblKorisnikPortal.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorisnikPortal.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblKorisnikPortal.setBounds(374, 11, 164, 26);
		contentPane.add(lblKorisnikPortal);
		
		JButton btnPrikaziLozinku = new JButton("prikazi lozinku");
		btnPrikaziLozinku.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pfLozinka.setEchoChar((char) 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pfLozinka.setEchoChar('•');
			}
		});
		btnPrikaziLozinku.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPrikaziLozinku.setBounds(389, 103, 149, 34);
		contentPane.add(btnPrikaziLozinku);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\Theater.jpg"));
		lblNewLabel_1.setBounds(-22, -13, 571, 436);
		contentPane.add(lblNewLabel_1);
	}

	public static String getKorisnickoIme() {
		return korisnickoIme;
	}

	public static int getIdKorisnika() {
		return idKorisnika;
	}

	
	
}
