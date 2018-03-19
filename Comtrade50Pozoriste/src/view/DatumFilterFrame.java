package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import domen.TabelaRezervacije;
import kontroler.Kontroler;

import com.toedter.calendar.JCalendar;
import java.awt.Font;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class DatumFilterFrame extends JFrame {

	private JPanel contentPane;
	public static JDateChooser dateChooser;
	
	private String izabraniDatum;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatumFilterFrame frame = new DatumFilterFrame();
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
	public DatumFilterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(567, 437, 307, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setText("Izaberi datum");
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		dateChooser.getCalendarButton().setFont(new Font("Gisha", Font.PLAIN, 18));
		contentPane.add(dateChooser, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Pretrazi datum");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				izabraniDatum = String.valueOf(df.format(DatumFilterFrame.getDateChooser().getDate()));
				System.out.println(izabraniDatum);
				KorisnikRezervacijaFrame.pretraziDatum(izabraniDatum);
				setVisible(false);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Gisha", Font.PLAIN, 17));
		dateChooser.add(btnNewButton, BorderLayout.WEST);
		
		
	}

	public static JDateChooser getDateChooser() {
		return dateChooser;
	}

	


	}


