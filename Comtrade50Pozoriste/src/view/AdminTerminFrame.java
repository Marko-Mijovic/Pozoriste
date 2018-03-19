package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Predstava;
import domen.Termin;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminTerminFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfDatumTermina;
	private JTextField tfVremeTermina;
	private JTextField tfUkupanBrojKarata;
	private JTextField tfIdPredstave;
	private JTextField tfPreostaliBrojKarta;
	
	private DefaultTableModel dtm = new DefaultTableModel();
	
	private int idTermina;
	private Date datumTermina;
	private Time vremeTermina;
	private int ukupanBrojKarata;
	private int idPredstave;
	private int preostaliBrojKarata;
	private double cenaKarte;
	private JTextField tfCenaKarte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTerminFrame frame = new AdminTerminFrame();
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
	public AdminTerminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 799, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 763, 134);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(dtm);
		table.setFont(new Font("Gisha", Font.PLAIN, 12));
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setViewportView(table);
		scrollPane_1.setBounds(10, 11, 743, 112);
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
		btnNazad.setBounds(624, 287, 149, 34);
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
		btnPocetnaStrana.setBounds(624, 348, 149, 34);
		contentPane.add(btnPocetnaStrana);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().unesiTermin(tfDatumTermina.getText(), tfVremeTermina.getText(), tfUkupanBrojKarata.getText() ,tfIdPredstave.getText(), tfPreostaliBrojKarta.getText(),tfCenaKarte.getText());
				osveziTabelu();
			}
		});
		btnUnesi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnUnesi.setBounds(412, 226, 149, 34);
		contentPane.add(btnUnesi);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().promeniTermin(idTermina, tfDatumTermina.getText(), tfVremeTermina.getText(), tfUkupanBrojKarata.getText(), tfIdPredstave.getText(), tfPreostaliBrojKarta.getText(), tfCenaKarte.getText());
				osveziTabelu();
				tfDatumTermina.setText("");
				tfVremeTermina.setText("");
				tfIdPredstave.setText("");
				tfUkupanBrojKarata.setText("");
				tfPreostaliBrojKarta.setText("");
				tfCenaKarte.setText("");
				
			}
		});
		btnPromeni.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnPromeni.setBounds(412, 287, 149, 34);
		contentPane.add(btnPromeni);
		
		JButton btnIzbrisi = new JButton("Izbrisi");
		btnIzbrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kontroler.getInstanca().izbrisiTermin(idTermina);
				osveziTabelu();
				tfDatumTermina.setText("");
				tfVremeTermina.setText("");
				tfIdPredstave.setText("");
				tfUkupanBrojKarata.setText("");
				tfPreostaliBrojKarta.setText("");
				tfCenaKarte.setText("");
			}
		});
		btnIzbrisi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnIzbrisi.setBounds(412, 348, 149, 34);
		contentPane.add(btnIzbrisi);
		
		JLabel lblNewLabel = new JLabel("datum termina:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 226, 114, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblVremeTermina = new JLabel("vreme termina:");
		lblVremeTermina.setForeground(Color.WHITE);
		lblVremeTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblVremeTermina.setBounds(20, 265, 114, 28);
		contentPane.add(lblVremeTermina);
		
		JLabel lblUkupanBrojKarata = new JLabel("ukupan broj karata:");
		lblUkupanBrojKarata.setForeground(Color.WHITE);
		lblUkupanBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblUkupanBrojKarata.setBounds(20, 304, 136, 35);
		contentPane.add(lblUkupanBrojKarata);
		
		JLabel lblIdpredstave = new JLabel("ID predstave:");
		lblIdpredstave.setForeground(Color.WHITE);
		lblIdpredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblIdpredstave.setBounds(20, 350, 114, 28);
		contentPane.add(lblIdpredstave);
		
		JLabel lblPreostaliBrojKarata = new JLabel("preostali broj karata:");
		lblPreostaliBrojKarata.setForeground(Color.WHITE);
		lblPreostaliBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblPreostaliBrojKarata.setBounds(20, 389, 149, 34);
		contentPane.add(lblPreostaliBrojKarata);
		
		tfDatumTermina = new JTextField();
		tfDatumTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfDatumTermina.setBounds(181, 226, 177, 28);
		contentPane.add(tfDatumTermina);
		tfDatumTermina.setColumns(10);
		
		tfVremeTermina = new JTextField();
		tfVremeTermina.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfVremeTermina.setColumns(10);
		tfVremeTermina.setBounds(181, 270, 177, 28);
		contentPane.add(tfVremeTermina);
		
		tfUkupanBrojKarata = new JTextField();
		tfUkupanBrojKarata.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfUkupanBrojKarata.setColumns(10);
		tfUkupanBrojKarata.setBounds(181, 312, 177, 28);
		contentPane.add(tfUkupanBrojKarata);
		
		tfIdPredstave = new JTextField();
		tfIdPredstave.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfIdPredstave.setColumns(10);
		tfIdPredstave.setBounds(181, 355, 177, 28);
		contentPane.add(tfIdPredstave);
		
		tfPreostaliBrojKarta = new JTextField();
		tfPreostaliBrojKarta.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfPreostaliBrojKarta.setColumns(10);
		tfPreostaliBrojKarta.setBounds(179, 397, 177, 28);
		contentPane.add(tfPreostaliBrojKarta);
		
		JButton btnOsvezi = new JButton("Osvezi");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				osveziTabelu();
				
			}
		});
		btnOsvezi.setFont(new Font("Gisha", Font.PLAIN, 15));
		btnOsvezi.setBounds(624, 226, 149, 34);
		contentPane.add(btnOsvezi);
		
		JLabel lblcenaKarte = new JLabel("cena karte:");
		lblcenaKarte.setForeground(Color.WHITE);
		lblcenaKarte.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblcenaKarte.setBounds(20, 434, 149, 34);
		contentPane.add(lblcenaKarte);
		
		tfCenaKarte = new JTextField();
		tfCenaKarte.setFont(new Font("Gisha", Font.PLAIN, 15));
		tfCenaKarte.setColumns(10);
		tfCenaKarte.setBounds(181, 442, 177, 28);
		contentPane.add(tfCenaKarte);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Eclipse Workspace\\Comtrade50\\Comtrade50Pozoriste\\jpgs\\columbus-1936633_1280.jpg"));
		lblNewLabel_1.setBounds(-236, -11, 1091, 532);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTermini = new JLabel("Termini");
		lblTermini.setForeground(Color.LIGHT_GRAY);
		lblTermini.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermini.setFont(new Font("Gisha", Font.ITALIC, 22));
		lblTermini.setBounds(646, 393, 162, 30);
		contentPane.add(lblTermini);
		
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
				String idTermina1 = (table.getModel().getValueAt(redovi, 0).toString());
				String datumTermina = (table.getModel().getValueAt(redovi, 1).toString());
				String vremeTermina = (table.getModel().getValueAt(redovi, 2).toString());
				String ukupanBrojKarata = (table.getModel().getValueAt(redovi, 3).toString());
				String idPredstave = (table.getModel().getValueAt(redovi, 4).toString());
				String preostaliBrojKarata = (table.getModel().getValueAt(redovi, 5).toString());
				String cenaKarte = (table.getModel().getValueAt(redovi, 7).toString());
				
				AdminTerminFrame.this.idTermina = Integer.valueOf(idTermina1);
				AdminTerminFrame.this.datumTermina = Date.valueOf(datumTermina);
				AdminTerminFrame.this.vremeTermina = Time.valueOf(vremeTermina);
				AdminTerminFrame.this.ukupanBrojKarata = Integer.valueOf(ukupanBrojKarata);
				AdminTerminFrame.this.idPredstave = Integer.valueOf(idPredstave);
				AdminTerminFrame.this.preostaliBrojKarata = Integer.valueOf(preostaliBrojKarata);
				AdminTerminFrame.this.cenaKarte = Double.valueOf(preostaliBrojKarata);
				
				tfDatumTermina.setText(datumTermina);
				tfVremeTermina.setText(vremeTermina);
				tfUkupanBrojKarata.setText(ukupanBrojKarata);
				tfIdPredstave.setText(idPredstave);
				tfPreostaliBrojKarta.setText(preostaliBrojKarata);
				tfCenaKarte.setText(cenaKarte);
				
			}
		});
		
		Object[]kolone = new Object[8];
		kolone[0]="ID Termina";
		kolone[1]="Datum Termina";
		kolone[2]="Vreme Termina";
		kolone[3]="Ukupan Broj Karata";
		kolone[4]="ID Predstave";
		kolone[5]="Preostali Broj Karata";
		kolone[6]="Naziv Predstave";
		kolone[7]="Cena Karte";
		
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		dtm.addColumn(kolone[6]);
		dtm.addColumn(kolone[7]);
		
		
		osveziTabelu();
		
	}

	private void osveziTabelu() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Object[]redovi = new Object[8];
		ArrayList<Termin>alTermin = new ArrayList<>();
		ArrayList<Predstava>alPredstava = new ArrayList<>();
		alTermin = Kontroler.getInstanca().vratiTermine();
		alPredstava = Kontroler.getInstanca().vratiPredstave();
		for(Termin t:alTermin) {
			redovi[0]=t.getIdTermina();
			redovi[1]=t.getDatumTermina();
			redovi[2]=t.getVremeTermina();
			redovi[3]=t.getUkupanBrojKarata();
			redovi[4]=t.getIdPredstave();
			redovi[5]=t.getPreostaliBrojKarata();
			redovi[7]=t.getCenaKarte();
			for(Predstava p:alPredstava) {
				if(t.getIdPredstave()==p.getIdPredstave()) {
					redovi[6]=p.getNazivPredstave();
				}
			}
			
			dtm.addRow(redovi);
			
		}
	}
}
