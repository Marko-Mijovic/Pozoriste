package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Predstava;
import domen.Termin;
import kontroler.Kontroler;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminPredstavaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNazivPredstave;
	private JTextField tfReziser;
	private JTextField tfIdSale;
	private JTextField tfZanr;
	
	private JTextPane tpOpis;
	private JTextPane tpGlumci;
	
	private DefaultTableModel dtm = new DefaultTableModel();
	
	private int idPredstave;
	private String nazivPredstave;
	private String reziser;
	private String glumci;
	private int idSale;
	private String zanr;
	private String opis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPredstavaFrame frame = new AdminPredstavaFrame();
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
	public AdminPredstavaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 799, 600);
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
		btnNazad.setBounds(624, 403, 149, 34);
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
		btnPocetnaStrana.setBounds(624, 469, 149, 34);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().unesiPredstavu(tfNazivPredstave.getText(), tfReziser.getText(), tpGlumci.getText() ,tfIdSale.getText(), tfZanr.getText(), tpOpis.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste uneli novu predstavu");
				osveziTabelu();
				tfNazivPredstave.setText("");
				tfReziser.setText("");
				tpGlumci.setText("");
				tfIdSale.setText("");
				tfZanr.setText("");
				tpOpis.setText("");
			}
		});
		btnUnesi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnUnesi.setBounds(412, 342, 149, 34);
		contentPane.add(btnUnesi);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().promeniPredstavu(idPredstave, tfNazivPredstave.getText(), tfReziser.getText(), tpGlumci.getText(), tfIdSale.getText(), tfZanr.getText(), tpOpis.getText());
				JOptionPane.showMessageDialog(null, "Uspesno ste promenili predstavu");
				osveziTabelu();
				tfNazivPredstave.setText("");
				tfReziser.setText("");
				tpGlumci.setText("");
				tfIdSale.setText("");
				tfZanr.setText("");
				tpOpis.setText("");
				
			}
		});
		btnPromeni.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPromeni.setBounds(412, 403, 149, 34);
		contentPane.add(btnPromeni);
		
		JButton btnIzbrisi = new JButton("Izbrisi");
		btnIzbrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().izbrisiPredstavu(idPredstave);
				JOptionPane.showMessageDialog(null, "Uspesno ste izbrisali predstavu");
				osveziTabelu();
				tfNazivPredstave.setText("");
				tfReziser.setText("");
				tpGlumci.setText("");
				tfIdSale.setText("");
				tfZanr.setText("");
				tpOpis.setText("");
			}
		});
		btnIzbrisi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnIzbrisi.setBounds(412, 469, 149, 34);
		contentPane.add(btnIzbrisi);
		
		JLabel lblNewLabel = new JLabel("naziv predstave:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 175, 114, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblVremeTermina = new JLabel("reziser:");
		lblVremeTermina.setForeground(Color.WHITE);
		lblVremeTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblVremeTermina.setBounds(20, 239, 114, 28);
		contentPane.add(lblVremeTermina);
		
		JLabel lblUkupanBrojKarata = new JLabel("glumci:");
		lblUkupanBrojKarata.setForeground(Color.WHITE);
		lblUkupanBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblUkupanBrojKarata.setBounds(20, 315, 136, 35);
		contentPane.add(lblUkupanBrojKarata);
		
		JLabel lblIdpredstave = new JLabel("id sale:");
		lblIdpredstave.setForeground(Color.WHITE);
		lblIdpredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblIdpredstave.setBounds(20, 406, 114, 28);
		contentPane.add(lblIdpredstave);
		
		JLabel lblPreostaliBrojKarata = new JLabel("zanr:");
		lblPreostaliBrojKarata.setForeground(Color.WHITE);
		lblPreostaliBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblPreostaliBrojKarata.setBounds(20, 469, 149, 34);
		contentPane.add(lblPreostaliBrojKarata);
		
		tfNazivPredstave = new JTextField();
		tfNazivPredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfNazivPredstave.setBounds(181, 175, 177, 28);
		contentPane.add(tfNazivPredstave);
		tfNazivPredstave.setColumns(10);
		
		tfReziser = new JTextField();
		tfReziser.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfReziser.setColumns(10);
		tfReziser.setBounds(181, 239, 177, 28);
		contentPane.add(tfReziser);
		
		tfIdSale = new JTextField();
		tfIdSale.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfIdSale.setColumns(10);
		tfIdSale.setBounds(181, 406, 177, 28);
		contentPane.add(tfIdSale);
		
		tfZanr = new JTextField();
		tfZanr.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfZanr.setColumns(10);
		tfZanr.setBounds(179, 475, 179, 28);
		contentPane.add(tfZanr);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu();
				
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(624, 342, 149, 34);
		contentPane.add(btnOsvezi);
		
		JLabel lblOpis = new JLabel("opis:");
		lblOpis.setForeground(Color.WHITE);
		lblOpis.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblOpis.setBounds(412, 167, 149, 34);
		contentPane.add(lblOpis);
		
		tpOpis = new JTextPane();
		tpOpis.setBounds(412, 205, 361, 112);
		contentPane.add(tpOpis);
		
		tpGlumci = new JTextPane();
		tpGlumci.setBounds(181, 315, 177, 61);
		contentPane.add(tpGlumci);
		
		JLabel lblPredstave = new JLabel("Predstave");
		lblPredstave.setForeground(Color.LIGHT_GRAY);
		lblPredstave.setHorizontalAlignment(SwingConstants.LEFT);
		lblPredstave.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblPredstave.setBounds(10, 525, 137, 26);
		contentPane.add(lblPredstave);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\12728857_1057563034306095_5129155121710202318_n.jpg"));
		lblNewLabel_1.setBounds(-107, -61, 960, 773);
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
				String idPredstave1 = (table.getModel().getValueAt(redovi, 0).toString());
				String nazivPredstave1 = (table.getModel().getValueAt(redovi, 1).toString());
				String reziser1 = (table.getModel().getValueAt(redovi, 2).toString());
				String glumci1 = (table.getModel().getValueAt(redovi, 3).toString());
				String idSale1 = (table.getModel().getValueAt(redovi, 4).toString());
				String zanr1 = (table.getModel().getValueAt(redovi, 5).toString());
				String opis1 = (table.getModel().getValueAt(redovi, 6).toString());
				
				idPredstave = Integer.valueOf(idPredstave1);
				nazivPredstave = nazivPredstave1;
				reziser = reziser1;
				glumci = glumci1;
				idSale = Integer.valueOf(idSale1);
				zanr = zanr1;
				opis = opis1;
				
				tfNazivPredstave.setText(nazivPredstave1);
				tfReziser.setText(reziser1);
				tpGlumci.setText(glumci1);
				tfIdSale.setText(idSale1);
				tfZanr.setText(zanr1);
				tpOpis.setText(opis1);
			}
		});
		
		Object[]kolone = new Object[7];
		kolone[0]="ID Predstave";
		kolone[1]="Naziv Predstave";
		kolone[2]="Reziser";
		kolone[3]="Glumci";
		kolone[4]="ID Sale";
		kolone[5]="Zanr";
		kolone[6]="Opis Predstave";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		
		
		osveziTabelu();
		
	}

	private void osveziTabelu() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Object[]redovi = new Object[7];
		ArrayList<Predstava>alPredstava = new ArrayList<>();
		alPredstava = Kontroler.getInstanca().vratiPredstave();
		for(Predstava p:alPredstava) {
			redovi[0]=p.getIdPredstave();
			redovi[1]=p.getNazivPredstave();
			redovi[2]=p.getReziser();
			redovi[3]=p.getGlumci();
			redovi[4]=p.getIdSale();
			redovi[5]=p.getZanr();
			redovi[6]=p.getOpis();
			
			dtm.addRow(redovi);
			
		}
	}
}