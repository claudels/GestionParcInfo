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
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;

public class ConnexionServeur extends JFrame {
	
	//Fenetre principale
	private JPanel contentPane;
	
	//Tableaux
	private JTable TBL_connexionServeur;

	//Labels statiques
	private JLabel staticLBL_title,staticLBL_subtitle,staticLBL_sn_o, staticLBL_designation, staticLBL_proprietaire, staticLBL_quota  ;

	//Labels dynamique
	private JLabel LBL_sn_o, LBL_designation, LBL_proprietaire, LBL_mo ;
	
	//Bouttons
	private JButton BTN_annuler,BTN_sauvegarder  ;
	
	// Bouttons toggle
	private JToggleButton TGLBTN_mode;
	
	//Spinner
	private JSpinner SPIN_quotas ;
	
	//ScrollPane
	private JScrollPane SCRLPANE_connexionServeur;
	
	
	/**
	 * Create the frame.
	 */
	public ConnexionServeur() {
		setTitle("Employ\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration des labels statiques
		staticLBL_title = new JLabel("Connexions serveurs");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(127, 0, 294, 32);
		contentPane.add(staticLBL_title);
		
		staticLBL_subtitle = new JLabel("S\u00E9l\u00E9ctionnez un ou plusieurs serveurs puis cliquez sur le bouton \"Connecter\" : ");
		staticLBL_subtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLBL_subtitle);
		
		staticLBL_sn_o = new JLabel("SN_O : ");
		staticLBL_sn_o.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_sn_o.setBounds(125, 66, 75, 14);
		contentPane.add(staticLBL_sn_o);
		
		staticLBL_designation = new JLabel("D\u00E9signation : ");
		staticLBL_designation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_designation.setBounds(97, 94, 103, 14);
		contentPane.add(staticLBL_designation);
		
		staticLBL_proprietaire = new JLabel("Propri\u00E9taire : ");
		staticLBL_proprietaire.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_proprietaire.setBounds(111, 124, 89, 14);
		contentPane.add(staticLBL_proprietaire);
		
		staticLBL_quota = new JLabel("Quotas : ");
		staticLBL_quota.setHorizontalAlignment(SwingConstants.LEFT);
		staticLBL_quota.setBounds(409, 452, 69, 14);
		contentPane.add(staticLBL_quota);
		
		//Configuration des labels dynamique
		LBL_sn_o = new JLabel("###########");
		LBL_sn_o.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_sn_o.setBounds(206, 65, 115, 16);
		contentPane.add(LBL_sn_o);
		
		LBL_designation = new JLabel("############################");
		LBL_designation.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_designation.setBounds(206, 93, 215, 16);
		contentPane.add(LBL_designation);
		
		LBL_proprietaire = new JLabel("###########");
		LBL_proprietaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		LBL_proprietaire.setBounds(206, 123, 125, 16);
		contentPane.add(LBL_proprietaire);
		
		LBL_mo = new JLabel("MO");
		LBL_mo.setHorizontalAlignment(SwingConstants.LEFT);
		LBL_mo.setBounds(615, 452, 19, 14);
		contentPane.add(LBL_mo);
		
		//Configuration des bouttons toggles
		TGLBTN_mode = new JToggleButton("Mode visualisation");
		TGLBTN_mode.setBounds(394, 495, 137, 23);
		contentPane.add(TGLBTN_mode);
		
		//Configuration des bouttons
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(303, 495, 89, 23);
		contentPane.add(BTN_annuler);
		
		BTN_sauvegarder = new JButton("Sauvegarder");
		BTN_sauvegarder.setEnabled(false);
		BTN_sauvegarder.setBounds(533, 495, 105, 23);
		contentPane.add(BTN_sauvegarder);
		
		//Configuration des spinner
		SPIN_quotas = new JSpinner();
		SPIN_quotas.setModel(new SpinnerNumberModel(100, null, 1000000, 50));
		SPIN_quotas.setBounds(466, 448, 137, 22);
		contentPane.add(SPIN_quotas);
		
		//Configuration du scroll pane
		SCRLPANE_connexionServeur = new JScrollPane();
		SCRLPANE_connexionServeur.setBounds(12, 199, 624, 240);
		contentPane.add(SCRLPANE_connexionServeur);
		
		//Configuration du tableau
		TBL_connexionServeur = new JTable();
		TBL_connexionServeur.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"SN_S", "D\u00E9signation", "PCs connect\u00E9s", "Charge", "M\u00E9moire disponible"
			}
		));
		TBL_connexionServeur.getColumnModel().getColumn(1).setPreferredWidth(132);
		TBL_connexionServeur.getColumnModel().getColumn(2).setPreferredWidth(70);
		TBL_connexionServeur.getColumnModel().getColumn(3).setPreferredWidth(80);
		TBL_connexionServeur.getColumnModel().getColumn(4).setPreferredWidth(120);
		SCRLPANE_connexionServeur.setViewportView(TBL_connexionServeur);
	}
}
