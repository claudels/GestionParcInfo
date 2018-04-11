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

import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.view.fiche.Fiche.State;
import gestionParcInfo.view.tab.OrdinateurTab;

import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheImprimante extends Fiche {
	
	private DefaultTableModel tableModel;
	private Imprimantes imprimantes;
	
	private static final long serialVersionUID = 1L;
	private static final String[] columnsNames = {"SN_O", "Designation", "Employ\u00E9"};
	private Employes employes;
	private Ordinateurs ordinateurs;

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
	JButton BTN_deconnecter;
	
	
	/**
	 * Create the frame.
	 */
	public FicheImprimante(Fiche.State initialState) {
		super(initialState);
		initComponents();
		this.changeState(initialState);
	}
	
	public FicheImprimante(Fiche.State initialState,Imprimante imprimante, Employes employes) {
		this(initialState);
		employes=this.employes;
		
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.BTN_deconnecter.setVisible(newState != Fiche.State.CREATION);
		
		
		//Interdit à la visualisation
		this.TF_designation.setEditable(newState != Fiche.State.VISUALISATION);
		this.SPIN_resolution.setEnabled(newState != Fiche.State.VISUALISATION);
		
		
		//Autorisé pour création
		this.TF_SNI.setEditable(newState == Fiche.State.CREATION);
		
	}
	
	public void initComponents() {
		//Configuration fenetre
		setTitle("Imprimante");
		setBounds(100, 100, 494, 649);
		
		
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
		
		
		/*tableOrdinateur = new JTable();
		tableOrdinateur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableOrdinateur.setFillsViewportHeight(true);
		tableOrdinateur.setModel(this.tableModel);*/
		
		TABLE_ordisConnectes = new JTable();
		TABLE_ordisConnectes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		TABLE_ordisConnectes.setModel(this.tableModel);
		this.tableModel.setColumnIdentifiers(FicheImprimante.columnsNames);
		
		for(Ordinateur ordinateur : ordinateurs.getItems()) {
			String matricule = null;
			
			if(ordinateur.getProprietaire() != null)
				matricule = ordinateur.getProprietaire().getMatricule();
			
			Object[] rawData = new Object[FicheImprimante.columnsNames.length];
			rawData[0] = ordinateur.getSn();
			rawData[1] = ordinateur.getDesignation();
			rawData[2] = matricule;
			this.tableModel.addRow(rawData);
		}
		
		this.TABLE_ordisConnectes.setModel(this.tableModel);
		this.tableModel.fireTableDataChanged();
		SCRLLPANE_ordisConnectes.setViewportView(TABLE_ordisConnectes);
		
		
		btnAnnuler.setBounds(139, 566, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(230, 566, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(369, 566, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		
		BTN_deconnecter = new JButton("D\u00E9connecter");
		BTN_deconnecter.setEnabled(true);
		BTN_deconnecter.setBounds(10, 524, 111, 25);
		contentPane.add(BTN_deconnecter);
		
		
	}
}
