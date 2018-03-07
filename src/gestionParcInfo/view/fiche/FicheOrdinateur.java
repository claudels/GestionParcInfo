package gestionParcInfo.view.fiche;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.Label;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheOrdinateur extends JFrame {
	private JPanel contentPane;
	
	//Tableaux
	private JScrollPane SCRLLPANE_imprimante, SCRLLPANE_serveurs;
	private JTable TABLE_imprimante, TABLE_serveurs;
	
	//ComboBox
	private JComboBox<String> CMBBOX_assignedTo;

	//Spinners
	private JSpinner SPIN_RAM, SPIN_CPU;
	
	//TextFields
	private JTextField TF_SNO, TF_designation, TF_dateAttribution, TF_dateRestitution;
	
	//Labels statiques
	private JLabel staticLBL_uniteTemps, staticLBL_title, staticLBL_SNO, staticLBL_dateAttribution, staticLBL_dateRestitution, staticLBL_imprimanteTitle, staticLBL_serveursTitle, staticLBL_aChanger, staticLBL_aRetourner, staticLBL_tempsUtilisation, staticLBL_assignedTo, staticLBL_designation, staticLBL_CPU, staticLBL_RAM;

	//Labels dynamiques
	private JLabel LBL_aChanger, LBL_joursUtilisation, LBL_aRetourner;
	
	//Boutons
	private JButton BTN_annuler, BTN_sauver, BTN_connecterImprimante, BTN_deconnecterImprimante, BTN_connecterServeurs, BTN_deconnecterServeurs;
	private JToggleButton TGLBTN_mode;
	
	/**
	 * Create the frame.
	 */
	public FicheOrdinateur() {
		//Configuration de la fenêtre
		setTitle("Ordinateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 715);
		
		//Panel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration des labels statiques
		staticLBL_SNO = new JLabel("SN_O :");
		staticLBL_SNO.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_SNO.setBounds(64, 62, 63, 14);
		contentPane.add(staticLBL_SNO);
		
		staticLBL_dateAttribution = new JLabel("Date d'attribution :");
		staticLBL_dateAttribution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_dateAttribution.setBounds(12, 82, 117, 22);
		contentPane.add(staticLBL_dateAttribution);
		
		staticLBL_dateRestitution = new JLabel("Date de resitution :");
		staticLBL_dateRestitution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_dateRestitution.setBounds(0, 110, 128, 22);
		contentPane.add(staticLBL_dateRestitution);
		
		staticLBL_aChanger = new JLabel("A changer :");
		staticLBL_aChanger.setBounds(53, 184, 76, 22);
		contentPane.add(staticLBL_aChanger);
		
		staticLBL_aRetourner = new JLabel("A retourner : ");
		staticLBL_aRetourner.setBounds(223, 184, 81, 22);
		contentPane.add(staticLBL_aRetourner);
		
		staticLBL_designation = new JLabel("Designation : ");
		staticLBL_designation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_designation.setBounds(43, 151, 86, 20);
		contentPane.add(staticLBL_designation);
		
		staticLBL_CPU = new JLabel("CPU : ");
		staticLBL_CPU.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_CPU.setBounds(346, 114, 63, 14);
		contentPane.add(staticLBL_CPU);
		
		staticLBL_assignedTo = new JLabel("Assign\u00E9 \u00E0 : ");
		staticLBL_assignedTo.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_assignedTo.setBounds(328, 54, 81, 22);
		contentPane.add(staticLBL_assignedTo);
		
		staticLBL_RAM = new JLabel(" RAM : ");
		staticLBL_RAM.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_RAM.setBounds(346, 86, 63, 14);
		contentPane.add(staticLBL_RAM);
		
		staticLBL_tempsUtilisation = new JLabel("Temps d'utilisation :");
		staticLBL_tempsUtilisation.setBounds(406, 151, 147, 20);
		contentPane.add(staticLBL_tempsUtilisation);
		
		staticLBL_imprimanteTitle = new JLabel("Imprimante connect\u00E9e :");
		staticLBL_imprimanteTitle.setBounds(10, 248, 169, 22);
		contentPane.add(staticLBL_imprimanteTitle);
		
		staticLBL_serveursTitle = new JLabel("Serveurs connect\u00E9s :");
		staticLBL_serveursTitle.setBounds(10, 346, 169, 22);
		contentPane.add(staticLBL_serveursTitle);
		
		staticLBL_uniteTemps = new JLabel("Jours");
		staticLBL_uniteTemps.setHorizontalAlignment(SwingConstants.LEFT);
		staticLBL_uniteTemps.setFont(new Font("Tahoma", Font.BOLD, 18));
		staticLBL_uniteTemps.setBounds(487, 184, 63, 20);
		contentPane.add(staticLBL_uniteTemps);
		
		staticLBL_title = new JLabel("Ordinateur");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(219, 13, 154, 32);
		contentPane.add(staticLBL_title);
		
		//Configuration TextFields
		TF_SNO = new JTextField();
		TF_SNO.setEditable(false);
		TF_SNO.setBounds(139, 59, 117, 20);
		contentPane.add(TF_SNO);
		TF_SNO.setColumns(10);
		
		TF_designation = new JTextField();
		TF_designation.setEditable(false);
		TF_designation.setColumns(0);
		TF_designation.setBounds(139, 151, 234, 20);
		contentPane.add(TF_designation);
		
		TF_dateAttribution = new JTextField();
		TF_dateAttribution.setEditable(false);
		TF_dateAttribution.setColumns(10);
		TF_dateAttribution.setBounds(139, 84, 117, 20);
		contentPane.add(TF_dateAttribution);
		
		TF_dateRestitution = new JTextField();
		TF_dateRestitution.setEditable(false);
		TF_dateRestitution.setColumns(10);
		TF_dateRestitution.setBounds(139, 110, 117, 20);
		contentPane.add(TF_dateRestitution);
		
		//Configuration Spinners
		SPIN_RAM = new JSpinner();
		SPIN_RAM.setEnabled(false);
		SPIN_RAM.setModel(new SpinnerNumberModel(4, 4, 8, 4));
		SPIN_RAM.setBounds(421, 84, 117, 20);
		contentPane.add(SPIN_RAM);
		
		SPIN_CPU = new JSpinner();
		SPIN_CPU.setEnabled(false);
		SPIN_CPU.setModel(new SpinnerNumberModel(4096, 4096, 8192, 4096));
		SPIN_CPU.setBounds(421, 112, 117, 20);
		contentPane.add(SPIN_CPU);
		
		//Configuration ComboBox
		CMBBOX_assignedTo = new JComboBox<>();
		CMBBOX_assignedTo.setEnabled(false);
		CMBBOX_assignedTo.setEditable(true);
		CMBBOX_assignedTo.setBounds(421, 55, 117, 20);
		contentPane.add(CMBBOX_assignedTo);
		
		//Configuration Labels dynamiques
		LBL_aChanger = new JLabel("NON");
		LBL_aChanger.setFont(new Font("Tahoma", Font.BOLD, 18));
		LBL_aChanger.setBounds(135, 184, 63, 20);
		contentPane.add(LBL_aChanger);
		
		LBL_aRetourner = new JLabel("NON");
		LBL_aRetourner.setHorizontalAlignment(SwingConstants.RIGHT);
		LBL_aRetourner.setFont(new Font("Tahoma", Font.BOLD, 18));
		LBL_aRetourner.setBounds(284, 184, 63, 20);
		contentPane.add(LBL_aRetourner);
		
		LBL_joursUtilisation = new JLabel("#####");
		LBL_joursUtilisation.setHorizontalAlignment(SwingConstants.RIGHT);
		LBL_joursUtilisation.setFont(new Font("Tahoma", Font.BOLD, 18));
		LBL_joursUtilisation.setBounds(396, 184, 86, 20);
		contentPane.add(LBL_joursUtilisation);
		
		//Configuration des tableaux
		SCRLLPANE_imprimante = new JScrollPane();
		SCRLLPANE_imprimante.setBounds(10, 276, 452, 51);
		contentPane.add(SCRLLPANE_imprimante);
		
		JScrollPane SCRLLPANE_serveurs = new JScrollPane();
		SCRLLPANE_serveurs.setBounds(10, 374, 567, 180);
		contentPane.add(SCRLLPANE_serveurs);
		
		TABLE_imprimante = new JTable();
		TABLE_imprimante.setFillsViewportHeight(true);
		TABLE_imprimante.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"SN_I", "D\u00E9signation", "R\u00E9solution"
			}
		));
		TABLE_imprimante.getColumnModel().getColumn(1).setPreferredWidth(200);
		SCRLLPANE_imprimante.setViewportView(TABLE_imprimante);
		
		TABLE_serveurs = new JTable();
		TABLE_serveurs.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"SN_S", "D\u00E9signation", "Charge"
			}
		));
		TABLE_serveurs.getColumnModel().getColumn(0).setPreferredWidth(150);
		TABLE_serveurs.getColumnModel().getColumn(1).setPreferredWidth(200);
		TABLE_serveurs.getColumnModel().getColumn(2).setPreferredWidth(70);
		SCRLLPANE_serveurs.setViewportView(TABLE_serveurs);
		
		//Configuration des boutons
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(242, 632, 89, 23);
		contentPane.add(BTN_annuler);
		
		TGLBTN_mode = new JToggleButton("Mode visualisation");
		TGLBTN_mode.setBounds(333, 632, 137, 23);
		contentPane.add(TGLBTN_mode);
		
		BTN_sauver = new JButton("Sauvegarder");
		BTN_sauver.setEnabled(false);
		BTN_sauver.setBounds(472, 632, 105, 23);
		contentPane.add(BTN_sauver);
		
		BTN_connecterImprimante = new JButton("Connecter");
		BTN_connecterImprimante.setEnabled(false);
		BTN_connecterImprimante.setBounds(466, 276, 111, 25);
		contentPane.add(BTN_connecterImprimante);
		
		BTN_deconnecterImprimante = new JButton("D\u00E9connecter");
		BTN_deconnecterImprimante.setEnabled(false);
		BTN_deconnecterImprimante.setBounds(466, 302, 111, 25);
		contentPane.add(BTN_deconnecterImprimante);
		
		BTN_connecterServeurs = new JButton("Connecter");
		BTN_connecterServeurs.setEnabled(false);
		BTN_connecterServeurs.setBounds(10, 564, 111, 25);
		contentPane.add(BTN_connecterServeurs);
		
		BTN_deconnecterServeurs = new JButton("D\u00E9connecter");
		BTN_deconnecterServeurs.setEnabled(false);
		BTN_deconnecterServeurs.setBounds(127, 564, 111, 25);
		contentPane.add(BTN_deconnecterServeurs);
	}
}
