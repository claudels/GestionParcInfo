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
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheImprimante extends JFrame {

	private JPanel contentPane;

	//Labels statiques
	private JLabel staticLBL_SNI, staticLBL_uniteOrdinateur, staticLBL_ordisConnectesTitle, staticLBL_title, staticLBL_nbOrdiConnectes, staticLBL_designation, staticLBL_resolution;

	//Labels dynamique
	private JLabel LBL_nbOrdisConnectes;
	
	//TextFields
	private JTextField TF_designation, TF_SNI;
	
	//Spinner
	private JSpinner SPIN_resolution;
	
	//Tableau ordinateur
	private JScrollPane SCRLLPANE_ordisConnectes;
	private JTable TABLE_ordisConnectes;
	
	//Boutons
	JButton BTN_sauver, BTN_annuler, BTN_deconnecter;
	JToggleButton TGLBTN_mode;
	
	/**
	 * Create the frame.
	 */
	public FicheImprimante() {
		//Configuration fenetre
		setTitle("Imprimante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration labels statiques
		staticLBL_SNI = new JLabel("SN_I : ");
		staticLBL_SNI.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_SNI.setBounds(22, 62, 63, 14);
		contentPane.add(staticLBL_SNI);
		
		staticLBL_uniteOrdinateur = new JLabel("Ordinateurs");
		staticLBL_uniteOrdinateur.setHorizontalAlignment(SwingConstants.LEFT);
		staticLBL_uniteOrdinateur.setFont(new Font("Tahoma", Font.BOLD, 18));
		staticLBL_uniteOrdinateur.setBounds(340, 84, 124, 20);
		contentPane.add(staticLBL_uniteOrdinateur);
		
		staticLBL_ordisConnectesTitle = new JLabel("Ordinateurs connect\u00E9s : ");
		staticLBL_ordisConnectesTitle.setBounds(10, 142, 173, 22);
		contentPane.add(staticLBL_ordisConnectesTitle);
		
		staticLBL_title = new JLabel("Imprimante");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(163, 13, 185, 32);
		contentPane.add(staticLBL_title);
		
		staticLBL_nbOrdiConnectes = new JLabel("Nombre de PCs connect\u00E9s : ");
		staticLBL_nbOrdiConnectes.setBounds(260, 56, 173, 22);
		contentPane.add(staticLBL_nbOrdiConnectes);
		
		staticLBL_designation = new JLabel("D\u00E9signation : ");
		staticLBL_designation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_designation.setBounds(-15, 83, 100, 22);
		contentPane.add(staticLBL_designation);
		
		staticLBL_resolution = new JLabel("R\u00E9solution : ");
		staticLBL_resolution.setBounds(15, 108, 76, 22);
		contentPane.add(staticLBL_resolution);
		
		//Configuration des labels dynamiques
		LBL_nbOrdisConnectes = new JLabel("#####");
		LBL_nbOrdisConnectes.setHorizontalAlignment(SwingConstants.RIGHT);
		LBL_nbOrdisConnectes.setFont(new Font("Tahoma", Font.BOLD, 18));
		LBL_nbOrdisConnectes.setBounds(210, 84, 124, 20);
		contentPane.add(LBL_nbOrdisConnectes);
		
		//Configuration des TextFields
		TF_SNI = new JTextField();
		TF_SNI.setEditable(false);
		TF_SNI.setColumns(10);
		TF_SNI.setBounds(97, 59, 143, 20);
		contentPane.add(TF_SNI);
		
		TF_designation = new JTextField();
		TF_designation.setEditable(false);
		TF_designation.setColumns(10);
		TF_designation.setBounds(97, 84, 143, 20);
		contentPane.add(TF_designation);
		
		//Configuration spinner
		SPIN_resolution = new JSpinner();
		SPIN_resolution.setEnabled(false);
		SPIN_resolution.setModel(new SpinnerNumberModel(150, 150, 300, 150));
		SPIN_resolution.setBounds(97, 110, 86, 20);
		contentPane.add(SPIN_resolution);
		
		//Configuration tableau ordinateurs
		SCRLLPANE_ordisConnectes = new JScrollPane();
		SCRLLPANE_ordisConnectes.setBounds(10, 170, 457, 349);
		contentPane.add(SCRLLPANE_ordisConnectes);
		
		TABLE_ordisConnectes = new JTable();
		TABLE_ordisConnectes.setModel(new DefaultTableModel(
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
				{null, null, null},
			},
			new String[] {
				"SN_O", "Designation", "Employ\u00E9"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		SCRLLPANE_ordisConnectes.setViewportView(TABLE_ordisConnectes);
		
		//Configuration des boutons
		BTN_sauver = new JButton("Sauvegarder");
		BTN_sauver.setEnabled(false);
		BTN_sauver.setBounds(369, 566, 105, 23);
		contentPane.add(BTN_sauver);
		
		TGLBTN_mode = new JToggleButton("Mode visualisation");
		TGLBTN_mode.setBounds(230, 566, 137, 23);
		contentPane.add(TGLBTN_mode);
		
		BTN_annuler = new JButton("Annuler");
		BTN_annuler.setBounds(139, 566, 89, 23);
		contentPane.add(BTN_annuler);
		
		BTN_deconnecter = new JButton("D\u00E9connecter");
		BTN_deconnecter.setEnabled(false);
		BTN_deconnecter.setBounds(10, 524, 111, 25);
		contentPane.add(BTN_deconnecter);

	}
}
