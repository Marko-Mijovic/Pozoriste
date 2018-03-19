package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class KorisnikMedjuForma extends JFrame {

	private JPanel contentPane;
	
	private static String korisnik;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				KorisnikMedjuForma frame = new KorisnikMedjuForma();
	//				frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	
	public KorisnikMedjuForma(String korisnickoIme) {
		this.korisnik=korisnickoIme;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 618, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel naziv = new JLabel("Korisnik medjuforma");
		naziv.setForeground(Color.LIGHT_GRAY);
		naziv.setHorizontalAlignment(SwingConstants.CENTER);
		naziv.setFont(new Font("Gisha", Font.ITALIC, 22));
		naziv.setBounds(378, 8, 214, 27);
		contentPane.add(naziv);
		
		
		
		JButton btnRezervacija = new JButton("Rezervacija");
		btnRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikRezervacijaFrame krf = new KorisnikRezervacijaFrame();
				krf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnRezervacija.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnRezervacija.setBounds(10, 78, 149, 34);
		contentPane.add(btnRezervacija);
		
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
		btnNazad.setBounds(10, 200, 149, 34);
		contentPane.add(btnNazad);
		
		JLabel lblNewLabel = new JLabel(korisnik);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(63, 12, 96, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblKorisnik = new JLabel("korisnik:");
		lblKorisnik.setForeground(Color.WHITE);
		lblKorisnik.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorisnik.setFont(new Font("Gisha", Font.ITALIC, 15));
		lblKorisnik.setBounds(10, 11, 86, 24);
		contentPane.add(lblKorisnik);
		
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
		btnMojeRezervacije.setBounds(443, 78, 149, 34);
		contentPane.add(btnMojeRezervacije);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\music-2617224_640.jpg"));
		lblNewLabel_1.setBounds(0, -97, 753, 530);
		contentPane.add(lblNewLabel_1);
	}

	public static String getKorisnik() {
		return korisnik;
	}
}
