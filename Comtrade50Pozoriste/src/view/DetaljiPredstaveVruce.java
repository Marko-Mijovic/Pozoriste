package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

public class DetaljiPredstaveVruce extends JFrame {

	private JPanel contentPane;
	private JLabel slika;
	private JButton btnNewButton;
	private JButton btnNazad;
	private JLabel lblNewLabel;
	private JLabel lblReziser;
	private JLabel lblGlumci;
	private JLabel lblOpis;
	private JLabel lblNekiToVole;
	private JLabel lblSnezanaVeskovic;
	private JLabel lblMjuzikl;
	private JTextPane txtpnBrodvejskiMjuziklPuslica;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetaljiPredstaveVruce frame = new DetaljiPredstaveVruce();
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
	public DetaljiPredstaveVruce() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 734, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 279, 349);
		contentPane.add(panel);
		panel.setLayout(null);
		
		slika = new JLabel("");
		slika.setHorizontalAlignment(SwingConstants.CENTER);
		slika.setBounds(10, 11, 259, 327);
		panel.add(slika);
		slika.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\neki-to-vole-vruce-209x3001.jpg"));
		
		btnNewButton = new JButton("Like \u2665");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Lajkovali ste predstavu :)");
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		btnNewButton.setBounds(67, 395, 170, 37);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(341, 11, 364, 349);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("naziv predstave:");
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 41, 110, 22);
		panel_1.add(lblNewLabel);
		
		lblReziser = new JLabel("reziser:");
		lblReziser.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblReziser.setBounds(10, 88, 85, 22);
		panel_1.add(lblReziser);
		
		lblGlumci = new JLabel("zanr:");
		lblGlumci.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblGlumci.setBounds(10, 134, 85, 22);
		panel_1.add(lblGlumci);
		
		lblOpis = new JLabel("opis:");
		lblOpis.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblOpis.setBounds(10, 180, 85, 22);
		panel_1.add(lblOpis);
		
		lblNekiToVole = new JLabel("Neki to vole vruce");
		lblNekiToVole.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNekiToVole.setBounds(130, 41, 225, 22);
		panel_1.add(lblNekiToVole);
		
		lblSnezanaVeskovic = new JLabel("Snezana Veskovic");
		lblSnezanaVeskovic.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblSnezanaVeskovic.setBounds(130, 88, 225, 22);
		panel_1.add(lblSnezanaVeskovic);
		
		lblMjuzikl = new JLabel("Mjuzikl");
		lblMjuzikl.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblMjuzikl.setBounds(130, 134, 115, 22);
		panel_1.add(lblMjuzikl);
		
		txtpnBrodvejskiMjuziklPuslica = new JTextPane();
		txtpnBrodvejskiMjuziklPuslica.setText("Brodvejski mjuzikl Puslica, izveden 1972 godine, zasniva se na scenariju za film Neki to vole vruce, koji su napisali Bili Vajlder i I. A. L. Dajmond, a prema originalnoj prici Roberta Torena. Liberto za Puslicu napisao je Piter Stoun, muziku Dzul Stajn, a songove Bob Meril.");
		txtpnBrodvejskiMjuziklPuslica.setFont(new Font("Gisha", Font.PLAIN, 12));
		txtpnBrodvejskiMjuziklPuslica.setBounds(10, 202, 345, 126);
		panel_1.add(txtpnBrodvejskiMjuziklPuslica);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 75, 345, 2);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 121, 345, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 167, 345, 2);
		panel_1.add(separator_2);
		
		btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KorisnikRezervacijaFrame krf = new KorisnikRezervacijaFrame();
				krf.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNazad.setForeground(Color.BLACK);
		btnNazad.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		btnNazad.setBounds(441, 395, 170, 37);
		contentPane.add(btnNazad);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\theatre-clipart-stage-background-5.jpg"));
		lblNewLabel_1.setBounds(-172, -45, 973, 1170);
		contentPane.add(lblNewLabel_1);
	}
}
