package gestionParcInfo.view.fiche;

import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.Ordinateurs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Création de la fiche Imprimante.
 * @author Florian Lemarquand
 *
 */
public class FicheImprimante extends Fiche {
	
	private DefaultTableModel tableModel;
	private static final long serialVersionUID = 1L;
	private static final String[] columnsNames = {"SN_O", "Designation", "Employé"};
	private Ordinateurs ordinateurs;
	private ArrayList<Ordinateur> disconnectedOrdinateurs;

	//Labels statiques
	private JLabel staticLblSni;
	private JLabel staticLblUniteOrdinateur;
	private JLabel staticLblOrdisConnectesTitle;
	private JLabel staticLblTitle;
	private JLabel staticLblNbOrdiConnectes; 
	private JLabel staticLblDesignation; 
	private JLabel staticLblResolution;

	//Labels dynamique
	private JLabel lblNbOrdisConnectes;
	
	//TextFields
	private JTextField tfDesignation;
	private JTextField tfSni;
	
	//Spinner
	private JSpinner spinnerResolution;
	
	//Tableau ordinateur
	private JScrollPane scrlOrdisConnectes;
	private JTable tblOrdisConnectes;
	
	//Boutons
	JButton brnDeconnecter;
	
	
	/**
	 * Create the frame.
	 */
	public FicheImprimante(Fiche.State initialState) {
		super(initialState);
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(FicheImprimante.columnsNames);
		
		
		initComponents();
		this.changeState(initialState);
		
		
	}
	/**
	 * Constructeur de l'imprimante pour modification, donc imprimante existante.
	 * @param initialState
	 * 
	 * @param imprimante
	 * 
	 * @param ordinateurs
	 * 
	 * @param imprimantes
	 * 
	 */
	
	public FicheImprimante(Fiche.State initialState,Imprimante imprimante,Ordinateurs ordinateurs,Imprimantes imprimantes) {
		this(initialState);
		this.ordinateurs = ordinateurs;
		this.disconnectedOrdinateurs = new ArrayList<Ordinateur>();
		for (Ordinateur ordinateur : ordinateurs.findOrdinateursByImprimante(imprimante)) {
			String matricule = null;
			
			if (ordinateur.getProprietaire() != null) {
				matricule = ordinateur.getProprietaire().getMatricule();
			}
			
			Object[] rawData = new Object[FicheImprimante.columnsNames.length];
			rawData[0] = ordinateur.getSn();
			rawData[1] = ordinateur.getDesignation();
			rawData[2] = matricule;
			this.tableModel.addRow(rawData);
		}
		
	this.tfSni.setText(imprimante.getSn());
		this.tfDesignation.setText(imprimante.getDesignation());
		this.lblNbOrdisConnectes.setText(Long.toString(imprimantes.countOrdinateurs(imprimante)));
		
	}
	/**
	 * Liste des actions à faire en fonction des boutons appuyé.
	 */
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if (e.getSource() == this.brnDeconnecter) {
			System.out.println("demande de deconnection");
			int deletedRowsCounter = 0;
			int column = this.tableModel.findColumn(FicheImprimante.columnsNames[0]);
			int columnSnsIndex = this.tblOrdisConnectes.convertColumnIndexToView(column);
			

			for (int rowIndex : this.tblOrdisConnectes.getSelectedRows()) {
				Ordinateur ordinateur = this.ordinateurs.findBySn((String) this.tblOrdisConnectes.getValueAt(rowIndex - deletedRowsCounter, columnSnsIndex));
				System.out.println(ordinateur.getSn());
				this.disconnectedOrdinateurs.add(ordinateur);
				this.tableModel.removeRow(rowIndex - deletedRowsCounter);
				deletedRowsCounter++;
			}
		}
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.brnDeconnecter.setVisible(newState != Fiche.State.CREATION);
		
		
		//Interdit à la visualisation
		this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
		this.spinnerResolution.setEnabled(newState != Fiche.State.VISUALISATION);
		this.brnDeconnecter.setEnabled(newState != Fiche.State.VISUALISATION);
		
		
		//Autorisé pour création
		this.tfSni.setEditable(newState == Fiche.State.CREATION);
		
	
		
	}
	
	public ArrayList<Ordinateur> getDisconnectedOrdinateurs() {
		return disconnectedOrdinateurs;
	}
	
	public JButton getBtnDeconnecter() {
		return brnDeconnecter;
	}
	
	public String getSn() {
		return this.tfSni.getText();
	}
	
	public String getDesignation() {
		return this.tfDesignation.getText();
	}
	
	public int getResolution() {
		return (int)this.spinnerResolution.getValue();
	}
	
	/**
	 * Initialisation des composants qui forment la fenetres.
	 */
	public void initComponents() {
		//Configuration fenetre
		setTitle("Imprimante");
		setBounds(100, 100, 494, 649);
		
		
		//Configuration labels statiques
		staticLblSni = new JLabel("SN_I : ");
		staticLblSni.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSni.setBounds(22, 62, 63, 14);
		contentPane.add(staticLblSni);
		
		staticLblUniteOrdinateur = new JLabel("Ordinateurs");
		staticLblUniteOrdinateur.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblUniteOrdinateur.setFont(new Font("Tahoma", Font.BOLD, 18));
		staticLblUniteOrdinateur.setBounds(340, 84, 124, 20);
		contentPane.add(staticLblUniteOrdinateur);
		
		staticLblOrdisConnectesTitle = new JLabel("Ordinateurs connectés : ");
		staticLblOrdisConnectesTitle.setBounds(10, 142, 173, 22);
		contentPane.add(staticLblOrdisConnectesTitle);
		
		staticLblTitle = new JLabel("Imprimante");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(163, 13, 185, 32);
		contentPane.add(staticLblTitle);
		
		staticLblNbOrdiConnectes = new JLabel("Nombre de PCs connectés : ");
		staticLblNbOrdiConnectes.setBounds(260, 56, 173, 22);
		contentPane.add(staticLblNbOrdiConnectes);
		
		staticLblDesignation = new JLabel("Désignation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(-15, 83, 100, 22);
		contentPane.add(staticLblDesignation);
		
		staticLblResolution = new JLabel("Résolution : ");
		staticLblResolution.setBounds(15, 108, 76, 22);
		contentPane.add(staticLblResolution);
		
		//Configuration des labels dynamiques
		lblNbOrdisConnectes = new JLabel("#####");
		lblNbOrdisConnectes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNbOrdisConnectes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNbOrdisConnectes.setBounds(210, 84, 124, 20);
		contentPane.add(lblNbOrdisConnectes);
		
		//Configuration des TextFields
		tfSni = new JTextField();
		tfSni.setEditable(false);
		tfSni.setColumns(10);
		tfSni.setBounds(97, 59, 143, 20);
		contentPane.add(tfSni);
		
		tfDesignation = new JTextField();
		tfDesignation.setEditable(false);
		tfDesignation.setColumns(10);
		tfDesignation.setBounds(97, 84, 143, 20);
		contentPane.add(tfDesignation);
		
		//Configuration spinner
		spinnerResolution = new JSpinner();
		spinnerResolution.setEnabled(false);
		spinnerResolution.setModel(new SpinnerNumberModel(150, 150, 300, 150));
		spinnerResolution.setBounds(97, 110, 86, 20);
		contentPane.add(spinnerResolution);
		
		//Configuration tableau ordinateurs
		scrlOrdisConnectes = new JScrollPane();
		scrlOrdisConnectes.setBounds(10, 170, 457, 349);
		contentPane.add(scrlOrdisConnectes);
		
		tblOrdisConnectes = new JTable();
		tblOrdisConnectes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblOrdisConnectes.setModel(this.tableModel);
		tblOrdisConnectes.setFillsViewportHeight(true);
		scrlOrdisConnectes.setViewportView(tblOrdisConnectes);
		
		
		btnAnnuler.setBounds(139, 566, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(230, 566, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(369, 566, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		
		brnDeconnecter = new JButton("Déconnecter");
		brnDeconnecter.setEnabled(true);
		brnDeconnecter.setBounds(10, 524, 111, 25);
		contentPane.add(brnDeconnecter);
		
		
	}
}
