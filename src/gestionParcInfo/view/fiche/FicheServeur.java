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
import javax.swing.SpinnerListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

public class FicheServeur extends JFrame {
	
	private JPanel contentPane;
	
	//Champs texte
	private JTextField TF_numéroDeSerie, TF_designation;
	
	//Tableaux
	private JTable TBL_serveur;
	
	//labels statiques
	private JLabel staticLBL_numeroDeSerie, staticLBL_ordinateursConnectés, staticLBL_title, staticLBL_designation, staticLBL_charge, staticLBL_pourcent, staticLBL_nbOrdinateursConnectés;
	
	//labels dynamique
	private JLabel LBL_bnOrdinateursConnectés, LBL_memoire,LBL_charge;
	
	//Bouttons
	private JButton BTN_sauvegarder,BTN_annuler,BTN_deconnecter; 
	
	//Toggle bouton
	private JToggleButton TGLBTN_modeVisualisation;
	
	//Spinner
	private JSpinner SPIN_memoire;
	
	//ScrollPane
	private JScrollPane SCRLPANE_ordinateursConnectés;
	
	
	/**
	 * Create the frame.
	 */
	public FicheServeur() {
		setTitle("Serveur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration des labels statiques
		staticLBL_numeroDeSerie = new JLabel("Num\u00E9ro de s\u00E9rie :");
		staticLBL_numeroDeSerie.setBounds(53, 198, 123, 23);
		contentPane.add(staticLBL_numeroDeSerie);
		
		staticLBL_ordinateursConnectés = new JLabel("Ordinateurs connect\u00E9s : ");
		staticLBL_ordinateursConnectés.setBounds(241, 30, 147, 22);
		contentPane.add(staticLBL_ordinateursConnectés);
		
		staticLBL_title = new JLabel("Serveur");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(12, 0, 100, 32);
		contentPane.add(staticLBL_title);
		
		staticLBL_designation = new JLabel("D\u00E9signation :");
		staticLBL_designation.setBounds(53, 254, 123, 23);
		contentPane.add(staticLBL_designation);
		
		staticLBL_charge = new JLabel("Charge \r\n(Somme quotas / M\u00E9moire) :");
		staticLBL_charge.setBounds(12, 59, 223, 23);
		contentPane.add(staticLBL_charge);
		
		staticLBL_pourcent = new JLabel("%");
		staticLBL_pourcent.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_pourcent.setBounds(118, 88, 47, 32);
		contentPane.add(staticLBL_pourcent);
		
		staticLBL_nbOrdinateursConnectés = new JLabel("Nombre d'ordinateurs connect\u00E9s : ");
		staticLBL_nbOrdinateursConnectés.setBounds(12, 126, 206, 22);
		contentPane.add(staticLBL_nbOrdinateursConnectés);
		
		//Configuration des label dynamique
		LBL_bnOrdinateursConnectés = new JLabel("#####");
		LBL_bnOrdinateursConnectés.setHorizontalAlignment(SwingConstants.CENTER);
		LBL_bnOrdinateursConnectés.setFont(new Font("Tahoma", Font.BOLD, 26));
		LBL_bnOrdinateursConnectés.setBounds(12, 154, 192, 32);
		contentPane.add(LBL_bnOrdinateursConnectés);
		
		LBL_memoire = new JLabel("M\u00E9moire : ");
		LBL_memoire.setBounds(53, 311, 76, 22);
		contentPane.add(LBL_memoire);
		
		LBL_charge = new JLabel("##");
		LBL_charge.setFont(new Font("Tahoma", Font.BOLD, 26));
		LBL_charge.setBounds(69, 88, 47, 32);
		contentPane.add(LBL_charge);
		
		//Configuration des bouttons
		BTN_sauvegarder = new JButton("Sauvegarder");
		BTN_sauvegarder.setEnabled(false);
		BTN_sauvegarder.setBounds(688, 366, 105, 23);
		contentPane.add(BTN_sauvegarder);
		
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(458, 366, 89, 23);
		contentPane.add(BTN_annuler);
		
		BTN_deconnecter = new JButton("D\u00E9connecter");
		BTN_deconnecter.setEnabled(false);
		BTN_deconnecter.setBounds(688, 30, 105, 23);
		contentPane.add(BTN_deconnecter);
		
		//Configuration des toggle boutton
		TGLBTN_modeVisualisation = new JToggleButton("Mode visualisation");
		TGLBTN_modeVisualisation.setBounds(549, 366, 137, 23);
		contentPane.add(TGLBTN_modeVisualisation);
		
		//Configuration des champs de texte
		TF_numéroDeSerie = new JTextField();
		TF_numéroDeSerie.setEditable(false);
		TF_numéroDeSerie.setColumns(10);
		TF_numéroDeSerie.setBounds(53, 221, 123, 20);
		contentPane.add(TF_numéroDeSerie);
		
		TF_designation = new JTextField();
		TF_designation.setEditable(false);
		TF_designation.setColumns(10);
		TF_designation.setBounds(53, 278, 123, 20);
		contentPane.add(TF_designation);
		
		//Configuration des spinner
		SPIN_memoire = new JSpinner();
		SPIN_memoire.setEnabled(false);
		SPIN_memoire.setModel(new SpinnerListModel(new String[] {"3000", "5000", "10000"}));
		SPIN_memoire.setBounds(53, 333, 123, 20);
		contentPane.add(SPIN_memoire);
		
		//Configuration des ScrollPane
		SCRLPANE_ordinateursConnectés = new JScrollPane();
		SCRLPANE_ordinateursConnectés.setBounds(240, 59, 553, 294);
		contentPane.add(SCRLPANE_ordinateursConnectés);
		
		//Configuration des tableaux
		TBL_serveur = new JTable();
		TBL_serveur.setRowSelectionAllowed(false);
		TBL_serveur.setFillsViewportHeight(true);
		TBL_serveur.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"SN_O", "Description", "Quota", "Employe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TBL_serveur.getColumnModel().getColumn(0).setPreferredWidth(150);
		TBL_serveur.getColumnModel().getColumn(1).setPreferredWidth(200);
		SCRLPANE_ordinateursConnectés.setViewportView(TBL_serveur);
	}
}
