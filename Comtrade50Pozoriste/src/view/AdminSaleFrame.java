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
import domen.Sala;
import kontroler.Kontroler;
import javax.swing.SwingConstants;
import digitalclock.DigitalClock;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;

public class AdminSaleFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNazivSale;
	private JTextField tfKapacitetSale;
	private JTextField tfIdPozorista;
	
	private DefaultTableModel dtm = new DefaultTableModel();
	
	private int idSale;
	private String nazivSale;
	private int kapacitetSale;
	private int idPozorista;
	private String nazivPozorista;
	
	private JTextField tfNazivPozorista;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSaleFrame frame = new AdminSaleFrame();
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
	public AdminSaleFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 693, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 660, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(dtm);
		table.setFont(new Font("Gisha", Font.PLAIN, 12));
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setViewportView(table);
		scrollPane_1.setBounds(10, 11, 640, 123);
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
		btnNazad.setBounds(516, 236, 149, 34);
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
		btnPocetnaStrana.setBounds(516, 302, 149, 34);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().unesiSalu(tfNazivSale.getText(), tfKapacitetSale.getText() ,tfIdPozorista.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste uneli novu salu");
				osveziTabelu();
				tfNazivSale.setText("");
				tfKapacitetSale.setText("");
				tfIdPozorista.setText("");
				tfNazivPozorista.setText("");
			}
		});
		btnUnesi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnUnesi.setBounds(357, 175, 149, 34);
		contentPane.add(btnUnesi);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().promeniSalu(idSale, tfNazivSale.getText(), tfKapacitetSale.getText(), tfIdPozorista.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste promenili podatke sale");
				osveziTabelu();
				tfNazivSale.setText("");
				tfKapacitetSale.setText("");
				tfIdPozorista.setText("");
				tfNazivPozorista.setText("");

				
			}
		});
		btnPromeni.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPromeni.setBounds(357, 236, 149, 34);
		contentPane.add(btnPromeni);
		
		JButton btnIzbrisi = new JButton("Izbrisi");
		btnIzbrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().izbrisiSalu(idSale);
				JOptionPane.showMessageDialog(null, "Uspesno ste izbrisali salu");
				osveziTabelu();
				tfNazivSale.setText("");
				tfKapacitetSale.setText("");
				tfIdPozorista.setText("");
				tfNazivPozorista.setText("");

			}
		});
		btnIzbrisi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnIzbrisi.setBounds(357, 302, 149, 34);
		contentPane.add(btnIzbrisi);
		
		JLabel lblNewLabel = new JLabel("naziv sale:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 173, 114, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblVremeTermina = new JLabel("kapacitet sale:");
		lblVremeTermina.setForeground(SystemColor.text);
		lblVremeTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblVremeTermina.setBounds(27, 239, 114, 28);
		contentPane.add(lblVremeTermina);
		
		JLabel lblIdpredstave = new JLabel("id pozorista:");
		lblIdpredstave.setForeground(SystemColor.text);
		lblIdpredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblIdpredstave.setBounds(27, 305, 114, 28);
		contentPane.add(lblIdpredstave);
		
		tfNazivSale = new JTextField();
		tfNazivSale.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfNazivSale.setBounds(143, 175, 177, 28);
		contentPane.add(tfNazivSale);
		tfNazivSale.setColumns(10);
		
		tfKapacitetSale = new JTextField();
		tfKapacitetSale.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfKapacitetSale.setColumns(10);
		tfKapacitetSale.setBounds(144, 239, 177, 28);
		contentPane.add(tfKapacitetSale);
		
		tfIdPozorista = new JTextField();
		tfIdPozorista.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfIdPozorista.setColumns(10);
		tfIdPozorista.setBounds(144, 305, 177, 28);
		contentPane.add(tfIdPozorista);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu();
				
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(516, 175, 149, 34);
		contentPane.add(btnOsvezi);
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setForeground(Color.LIGHT_GRAY);
		lblSale.setHorizontalAlignment(SwingConstants.LEFT);
		lblSale.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblSale.setBounds(27, 417, 137, 26);
		contentPane.add(lblSale);
		
		JLabel lblNazivPozorista = new JLabel("naziv pozorista:");
		lblNazivPozorista.setForeground(SystemColor.text);
		lblNazivPozorista.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNazivPozorista.setBounds(27, 378, 114, 28);
		contentPane.add(lblNazivPozorista);
		
		tfNazivPozorista = new JTextField();
		tfNazivPozorista.setBackground(Color.LIGHT_GRAY);
		tfNazivPozorista.setEditable(false);
		tfNazivPozorista.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfNazivPozorista.setColumns(10);
		tfNazivPozorista.setBounds(143, 378, 363, 28);
		contentPane.add(tfNazivPozorista);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\216_Florida_Theater_Stage_2.178122759_large.jpg"));
		lblNewLabel_1.setBounds(-328, 0, 1005, 501);
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
				String idSale1 = (table.getModel().getValueAt(redovi, 0).toString());
				String nazivSale1 = (table.getModel().getValueAt(redovi, 1).toString());
				String kapacitetSale1 = (table.getModel().getValueAt(redovi, 2).toString());
				String idPozorista1 = (table.getModel().getValueAt(redovi, 3).toString());
				String nazivPozorista1 = (table.getModel().getValueAt(redovi, 4).toString());
				
				idSale = Integer.valueOf(idSale1);
				nazivSale = nazivSale1;
				kapacitetSale = Integer.valueOf(kapacitetSale1);
				idPozorista = Integer.valueOf(idPozorista1);
				nazivPozorista = nazivPozorista1;
				
				tfNazivSale.setText(nazivSale1);
				tfKapacitetSale.setText(kapacitetSale1);
				tfIdPozorista.setText(idPozorista1);
				tfNazivPozorista.setText(nazivPozorista1);
			}
		});
		
		Object[]kolone = new Object[5];
		kolone[0]="ID Sale";
		kolone[1]="Naziv Sale";
		kolone[2]="Kapacitet Sale";
		kolone[3]="ID Pozorista";
		kolone[4]="Naziv Pozorista";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);		
		
		osveziTabelu();
		
	}

	private void osveziTabelu() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Object[]redovi = new Object[5];
		ArrayList<Sala>alSala = new ArrayList<>();
		alSala = Kontroler.getInstanca().vratiSale();
		for(Sala s:alSala) {
			redovi[0]=s.getIdSale();
			redovi[1]=s.getNazivSale();
			redovi[2]=s.getKapacitetSale();
			redovi[3]=s.getIdPozorista();
			idPozorista = s.getIdPozorista();
			for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()) {
				if(idPozorista == p.getIdPozorista()) {
					redovi[4]=p.getNazivPozorista();
				}
			}
			
			dtm.addRow(redovi);
			
		}
	}
}
