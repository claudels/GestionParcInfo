package gestionParcInfo.view.fiche;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;

public class FicheEmploye extends JFrame {

	private JPanel contentPane;

	//labels statiques
	private JLabel staticLBL_matricule, staticLBL_nom,staticLBL_prenom, staticLBL_email, staticLBL_title, staticLBL_ordinateurs;

	//TextFields
	private JTextField TF_prenom, TF_nom, TF_matricule, TF_email;
	
	//Boutons
	private JButton BTN_assignerOrdinateur, BTN_annuler, BTN_sauver;
	private JToggleButton TGLBTN_mode;
	
	//Table ordinateurs
	private JScrollPane SCRLLPANE_ordinateurs;
	private JTable TABLE_ordinateurs;
	/**
	 * Create the frame.
	 */
	public FicheEmploye() {
		
		//Configuration fenêtre
		setTitle("Employ\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration label statiques
		staticLBL_matricule = new JLabel("Matricule : ");
		staticLBL_matricule.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_matricule.setBounds(10, 62, 75, 14);
		contentPane.add(staticLBL_matricule);
		
		staticLBL_nom = new JLabel("Nom : ");
		staticLBL_nom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_nom.setBounds(10, 82, 76, 22);
		contentPane.add(staticLBL_nom);
		
		staticLBL_prenom = new JLabel("Pr\u00E9nom : ");
		staticLBL_prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_prenom.setBounds(272, 57, 76, 22);
		contentPane.add(staticLBL_prenom);
		
		staticLBL_email = new JLabel("E-Mail : ");
		staticLBL_email.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_email.setBounds(266, 82, 81, 22);
		contentPane.add(staticLBL_email);
		
		staticLBL_title = new JLabel("Employ\u00E9");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(206, 0, 185, 32);
		contentPane.add(staticLBL_title);
		
		staticLBL_ordinateurs = new JLabel("Ordinateurs assign\u00E9s : ");
		staticLBL_ordinateurs.setBounds(10, 128, 173, 22);
		contentPane.add(staticLBL_ordinateurs);
		
		//Configuration TextFields
		TF_matricule = new JTextField();
		TF_matricule.setEditable(false);
		TF_matricule.setColumns(10);
		TF_matricule.setBounds(97, 59, 157, 20);
		contentPane.add(TF_matricule);
		
		TF_prenom = new JTextField();
		TF_prenom.setEditable(false);
		TF_prenom.setBounds(359, 56, 157, 20);
		contentPane.add(TF_prenom);
		
		TF_nom = new JTextField();
		TF_nom.setEditable(false);
		TF_nom.setColumns(10);
		TF_nom.setBounds(97, 84, 157, 20);
		contentPane.add(TF_nom);
		
		TF_email = new JTextField();
		TF_email.setEditable(false);
		TF_email.setColumns(10);
		TF_email.setBounds(359, 84, 157, 20);
		contentPane.add(TF_email);
		
		//Configuration boutons
		TGLBTN_mode = new JToggleButton("Mode visualisation");
		TGLBTN_mode.setBounds(284, 274, 137, 23);
		contentPane.add(TGLBTN_mode);
		
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(193, 274, 89, 23);
		contentPane.add(BTN_annuler);
		
		BTN_sauver = new JButton("Sauvegarder");
		BTN_sauver.setEnabled(false);
		BTN_sauver.setBounds(423, 274, 105, 23);
		contentPane.add(BTN_sauver);
		
		BTN_assignerOrdinateur = new JButton("Assigner un ordinateur");
		BTN_assignerOrdinateur.setEnabled(false);
		BTN_assignerOrdinateur.setBounds(10, 224, 198, 25);
		contentPane.add(BTN_assignerOrdinateur);
		
		//Configuration tableau ordinateurs
		SCRLLPANE_ordinateurs = new JScrollPane();
		SCRLLPANE_ordinateurs.setBounds(12, 156, 519, 63);
		contentPane.add(SCRLLPANE_ordinateurs);
		
		TABLE_ordinateurs = new JTable();
		TABLE_ordinateurs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"SN_O", "D\u00E9signation", "A changer", "A retourner", "Temps d'utilisation"
			}
		));
		TABLE_ordinateurs.getColumnModel().getColumn(1).setPreferredWidth(132);
		TABLE_ordinateurs.getColumnModel().getColumn(2).setPreferredWidth(70);
		TABLE_ordinateurs.getColumnModel().getColumn(3).setPreferredWidth(80);
		TABLE_ordinateurs.getColumnModel().getColumn(4).setPreferredWidth(120);
		SCRLLPANE_ordinateurs.setViewportView(TABLE_ordinateurs);
	}

}
