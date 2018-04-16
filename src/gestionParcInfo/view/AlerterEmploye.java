package gestionParcInfo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
<<<<<<< HEAD
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
=======
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


>>>>>>> refs/remotes/origin/master

public class AlerterEmploye extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
	private JButton btnAlerter;
	private JTextArea textAreaAlerte;
	private JLabel lblMessageDalerte;
	
	public AlerterEmploye() {
	  initComponents();
	}
	
	public JButton getBtnAlerter() {
<<<<<<< HEAD
	  return btnAlerter;
=======
	return btnAlerter;
	}
	
	public JTextField getTfMessageDalerte() {
		return tfMessageDalerte;
>>>>>>> refs/remotes/origin/master
	}
	
	public String getMessage() {
		return textAreaAlerte.getText();
	}

	/**
<<<<<<< HEAD
	 * Initilisation des composants graphique.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
=======
	 * Initialisation des objects qui composent la fenetre.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
>>>>>>> refs/remotes/origin/master
		setBounds(100, 100, 423, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMessageDalerte = new JLabel("Message d'alerte : ");
		lblMessageDalerte.setBounds(10, 22, 182, 14);
		contentPane.add(lblMessageDalerte);
		
		btnAlerter = new JButton("Alerter");
		btnAlerter.setBounds(308, 206, 89, 23);
		contentPane.add(btnAlerter);
		
		textAreaAlerte = new JTextArea();
		textAreaAlerte.setBounds(12, 40, 385, 156);
		contentPane.add(textAreaAlerte);
		}
}
