package gestionParcInfo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AlerterEmploye extends JFrame {

	private JPanel contentPane;
	private JTextField tfMessageDalerte;
	private JButton btnAlerter;


	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public AlerterEmploye() {
		
	initComponents();
	
	}
	
	public JButton getBtnAlerter() {
	return btnAlerter;
	}
	
	public JTextField getTfMessageDalerte() {
		return tfMessageDalerte;
	}
	
	public String getMessage() {
		return tfMessageDalerte.getText();
	}

	/**
	 * Initialisation des objects qui composent la fenetre.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMessageDalerte = new JLabel("Message d'alerte : ");
		lblMessageDalerte.setBounds(10, 22, 182, 14);
		contentPane.add(lblMessageDalerte);
		
		JButton btnAlerter = new JButton("Alerter");
		btnAlerter.setBounds(308, 206, 89, 23);
		contentPane.add(btnAlerter);
		
		tfMessageDalerte = new JTextField();
		tfMessageDalerte.setBounds(10, 47, 387, 154);
		contentPane.add(tfMessageDalerte);
		tfMessageDalerte.setColumns(10);
		}
}
