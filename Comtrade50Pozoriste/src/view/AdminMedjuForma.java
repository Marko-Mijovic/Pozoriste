package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminMedjuForma extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMedjuForma frame = new AdminMedjuForma();
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
	public AdminMedjuForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 550, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminLoginFrame alf = new AdminLoginFrame();
				alf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNazad.setBounds(10, 212, 191, 38);
		contentPane.add(btnNazad);
		
		JButton btnTermini = new JButton("Termini");
		btnTermini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminTerminFrame atf = new AdminTerminFrame();
				atf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnTermini.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnTermini.setBounds(10, 75, 191, 38);
		contentPane.add(btnTermini);
		
		JButton btnPredstave = new JButton("Predstave");
		btnPredstave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPredstavaFrame apf = new AdminPredstavaFrame();
				apf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnPredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPredstave.setBounds(333, 75, 191, 38);
		contentPane.add(btnPredstave);
		
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
		btnPocetnaStrana.setBounds(333, 212, 191, 38);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnPozorista = new JButton("Pozorista");
		btnPozorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPozoristaFrame apf = new AdminPozoristaFrame();
				apf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnPozorista.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPozorista.setBounds(10, 145, 191, 38);
		contentPane.add(btnPozorista);
		
		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminSaleFrame asf = new AdminSaleFrame();
				asf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnSale.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnSale.setBounds(333, 145, 191, 38);
		contentPane.add(btnSale);
		
		JLabel lblAdminMedjuForma = new JLabel("Admin medju forma");
		lblAdminMedjuForma.setForeground(Color.LIGHT_GRAY);
		lblAdminMedjuForma.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminMedjuForma.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblAdminMedjuForma.setBounds(310, 0, 214, 27);
		contentPane.add(lblAdminMedjuForma);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\auditorium-2584269_640.jpg"));
		lblNewLabel.setBounds(-63, -51, 695, 402);
		contentPane.add(lblNewLabel);
	}
}
