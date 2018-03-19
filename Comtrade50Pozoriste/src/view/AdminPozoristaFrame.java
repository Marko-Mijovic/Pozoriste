package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Pozoriste;
import domen.Predstava;
import kontroler.Kontroler;
import digitalclock.DigitalClock;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminPozoristaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNazivPozorista;
	private JTextField tfAdresa;
	private JTextField tfCenaKarte;
	
	private JTextPane tpOpisPozorista;
	
	private DefaultTableModel dtm = new DefaultTableModel();
	
	private int idPozorista;
	private String nazivPozorista;
	private String adresa;
	private double cena;
	private String opis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPozoristaFrame frame = new AdminPozoristaFrame();
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
	public AdminPozoristaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 799, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 763, 153);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(dtm);
		table.setFont(new Font("Gisha", Font.PLAIN, 12));
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setViewportView(table);
		scrollPane_1.setBounds(10, 11, 743, 131);
		panel.add(scrollPane_1);
		
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminMedjuForma amf = new AdminMedjuForma();
				amf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNazad.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnNazad.setBounds(624, 398, 149, 34);
		contentPane.add(btnNazad);
		
		JButton btnPocetnaStrana = new JButton("Pocetna strana");
		btnPocetnaStrana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminLoginFrame alf = new AdminLoginFrame();
				alf.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnPocetnaStrana.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPocetnaStrana.setBounds(624, 464, 149, 34);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().unesiPozoriste(tfNazivPozorista.getText(), tfAdresa.getText(), tfCenaKarte.getText(), tpOpisPozorista.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste uneli novo pozoriste");
				osveziTabelu();
				tfNazivPozorista.setText("");
				tfAdresa.setText("");
				tfCenaKarte.setText("");
				tpOpisPozorista.setText("");
			}
		});
		btnUnesi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnUnesi.setBounds(412, 337, 149, 34);
		contentPane.add(btnUnesi);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().promeniPozoriste(idPozorista, tfNazivPozorista.getText(), tfAdresa.getText(), tfCenaKarte.getText(), tpOpisPozorista.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste promenili podatke pozorista");
				osveziTabelu();
				tfNazivPozorista.setText("");
				tfAdresa.setText("");
				tfCenaKarte.setText("");
				tpOpisPozorista.setText("");
				
			}
		});
		btnPromeni.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPromeni.setBounds(412, 398, 149, 34);
		contentPane.add(btnPromeni);
		
		JButton btnIzbrisi = new JButton("Izbrisi");
		btnIzbrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().izbrisiPozoriste(idPozorista);
				JOptionPane.showMessageDialog(null, "Uspesno ste izbrisali pozoriste");
				osveziTabelu();
				tfNazivPozorista.setText("");
				tfAdresa.setText("");
				tfCenaKarte.setText("");
				tpOpisPozorista.setText("");
			}
		});
		btnIzbrisi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnIzbrisi.setBounds(412, 464, 149, 34);
		contentPane.add(btnIzbrisi);
		
		JLabel lblNewLabel = new JLabel("naziv pozorista:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 336, 114, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblVremeTermina = new JLabel("adresa:");
		lblVremeTermina.setForeground(Color.WHITE);
		lblVremeTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblVremeTermina.setBounds(20, 400, 114, 28);
		contentPane.add(lblVremeTermina);
		
		JLabel lblPreostaliBrojKarata = new JLabel("cena karte:");
		lblPreostaliBrojKarata.setForeground(Color.WHITE);
		lblPreostaliBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblPreostaliBrojKarata.setBounds(20, 464, 149, 34);
		contentPane.add(lblPreostaliBrojKarata);
		
		tfNazivPozorista = new JTextField();
		tfNazivPozorista.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfNazivPozorista.setBounds(144, 336, 244, 34);
		contentPane.add(tfNazivPozorista);
		tfNazivPozorista.setColumns(10);
		
		tfAdresa = new JTextField();
		tfAdresa.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfAdresa.setColumns(10);
		tfAdresa.setBounds(144, 400, 244, 34);
		contentPane.add(tfAdresa);
		
		tfCenaKarte = new JTextField();
		tfCenaKarte.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfCenaKarte.setColumns(10);
		tfCenaKarte.setBounds(144, 464, 244, 34);
		contentPane.add(tfCenaKarte);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu();
				
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(624, 337, 149, 34);
		contentPane.add(btnOsvezi);
		
		JLabel lblOpis = new JLabel("opis pozorista:");
		lblOpis.setForeground(Color.WHITE);
		lblOpis.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblOpis.setBounds(20, 175, 149, 34);
		contentPane.add(lblOpis);
		
		tpOpisPozorista = new JTextPane();
		tpOpisPozorista.setBounds(20, 206, 367, 119);
		contentPane.add(tpOpisPozorista);
		
		JLabel lblPozorista = new JLabel("Pozorista");
		lblPozorista.setForeground(Color.LIGHT_GRAY);
		lblPozorista.setHorizontalAlignment(SwingConstants.LEFT);
		lblPozorista.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblPozorista.setBounds(10, 499, 137, 26);
		contentPane.add(lblPozorista);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\Mondanaro-Theater_Fall-2014_JM_01.jpg"));
		lblNewLabel_1.setBounds(-549, -273, 1540, 930);
		contentPane.add(lblNewLabel_1);
		
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
				String idPozorista1 = (table.getModel().getValueAt(redovi, 0).toString());
				String nazivPozorista1 = (table.getModel().getValueAt(redovi, 1).toString());
				String adresa1 = (table.getModel().getValueAt(redovi, 2).toString());
				String cena1 = (table.getModel().getValueAt(redovi, 3).toString());
				String opis1 = (table.getModel().getValueAt(redovi, 4).toString());
				
				idPozorista = Integer.valueOf(idPozorista1);
				nazivPozorista = nazivPozorista1;
				adresa = adresa1;
				cena = Double.valueOf(cena1);
				opis = opis1;
				
				tfNazivPozorista.setText(nazivPozorista1);
				tfAdresa.setText(adresa1);
				tfCenaKarte.setText(cena1);
				tpOpisPozorista.setText(opis1);
			}
		});
		
		Object[]kolone = new Object[4];
		kolone[0]="ID Pozorista";
		kolone[1]="Naziv Pozorista";
		kolone[2]="Adresa";
		kolone[3]="Opis Pozorista";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);

		osveziTabelu();
		
	}

	private void osveziTabelu() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Object[]redovi = new Object[4];
		ArrayList<Pozoriste>alPozoriste = new ArrayList<>();
		alPozoriste = Kontroler.getInstanca().vratiPozorista();
		for(Pozoriste p:alPozoriste) {
			redovi[0]=p.getIdPozorista();
			redovi[1]=p.getNazivPozorista();
			redovi[2]=p.getAdresa();
			redovi[3]=p.getOpisPozorista();
			
			dtm.addRow(redovi);
			
		}
	}
}