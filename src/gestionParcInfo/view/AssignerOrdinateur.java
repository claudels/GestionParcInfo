package gestionParcInfo.view;
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
import javax.swing.ListSelectionModel;

public class AssignerOrdinateur extends JFrame {

	private JPanel contentPane;
	
	//Tableaux
	private JTable TBL_assignerOrdinateur;
	
	//Label statique
	private JLabel staticLBL_matricule,staticLBL_Prenom,staticLBL_email,staticLBL_subtitle,staticLBL_tittle,staticLBL_nom   ;
	
	//Label dynamique
	private JLabel LBL_matricule,LBL_email,LBL_nom,LBL_prenom;
	
	//Boutton
	private JButton BTN_annuler,BTN_assigner;
	
	
	private JScrollPane SCRLPANE_assignerOrdinateur;
	/**
	 * Create the frame.
	 */
	public AssignerOrdinateur() {
		//Configuration fenetre
		setTitle("Assigner ordinateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configurations des labels statiques
		staticLBL_matricule = new JLabel("Matricule : ");
		staticLBL_matricule.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_matricule.setBounds(10, 62, 75, 14);
		contentPane.add(staticLBL_matricule);
		
		staticLBL_Prenom = new JLabel("Pr\u00E9nom : ");
		staticLBL_Prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_Prenom.setBounds(305, 89, 75, 14);
		contentPane.add(staticLBL_Prenom);
		
		staticLBL_email = new JLabel("E-mail : ");
		staticLBL_email.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_email.setBounds(10, 90, 75, 14);
		contentPane.add(staticLBL_email);
		
		staticLBL_subtitle = new JLabel("S\u00E9l\u00E9ctionnez un ordinateur et cliquez sur le bouton \"Assigner\" : ");
		staticLBL_subtitle.setBounds(10, 128, 377, 22);
		contentPane.add(staticLBL_subtitle);
		
		staticLBL_tittle = new JLabel("Assigner un ordinateur");
		staticLBL_tittle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_tittle.setBounds(108, 0, 351, 32);
		contentPane.add(staticLBL_tittle);
		
		staticLBL_nom = new JLabel("Nom : ");
		staticLBL_nom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_nom.setBounds(305, 61, 75, 14);
		contentPane.add(staticLBL_nom);
		
		
		//Configuration des labels dynamique
		LBL_matricule = new JLabel("###########");
		LBL_matricule.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_matricule.setBounds(92, 61, 129, 16);
		contentPane.add(LBL_matricule);
		
		
		
		LBL_email = new JLabel("############################");
		LBL_email.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_email.setBounds(92, 89, 215, 16);
		contentPane.add(LBL_email);
		
		LBL_nom = new JLabel("###########");
		LBL_nom.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_nom.setBounds(387, 60, 144, 16);
		contentPane.add(LBL_nom);
		
		
		
		LBL_prenom = new JLabel("###########");
		LBL_prenom.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_prenom.setBounds(387, 88, 144, 16);
		contentPane.add(LBL_prenom);
		
		//Configuration des boutons
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(325, 517, 89, 23);
		contentPane.add(BTN_annuler);
		
		BTN_assigner = new JButton("Assigner");
		BTN_assigner.setBounds(426, 517, 105, 23);
		contentPane.add(BTN_assigner);
		
		
		//Configuration du ScrollPane
		SCRLPANE_assignerOrdinateur = new JScrollPane();
		SCRLPANE_assignerOrdinateur.setBounds(12, 156, 519, 345);
		contentPane.add(SCRLPANE_assignerOrdinateur);
		
		//Configuration du tableau
		TBL_assignerOrdinateur = new JTable();
		TBL_assignerOrdinateur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TBL_assignerOrdinateur.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"SN_O", "D\u00E9signation", "RAM", "CPU"
			}
		));
		TBL_assignerOrdinateur.getColumnModel().getColumn(1).setPreferredWidth(132);
		TBL_assignerOrdinateur.getColumnModel().getColumn(2).setPreferredWidth(70);
		TBL_assignerOrdinateur.getColumnModel().getColumn(3).setPreferredWidth(80);
		SCRLPANE_assignerOrdinateur.setViewportView(TBL_assignerOrdinateur);
	}

}
