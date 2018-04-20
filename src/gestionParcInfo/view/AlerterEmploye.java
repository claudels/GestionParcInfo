package gestionParcInfo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Vue qui permet de rentrer un message assicié à l'alerte
 * @author Sebastien Claudel
 *
 */
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
	  return btnAlerter;
	}
	
	public String getMessage() {
		return textAreaAlerte.getText();
	}

	/**
	 * Initilisation des composants graphique.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
