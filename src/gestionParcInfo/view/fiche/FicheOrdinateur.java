package gestionParcInfo.view.fiche;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.view.ConnexionImprimante;
import gestionParcInfo.view.ConnexionServeur;

import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheOrdinateur extends Fiche implements ActionListener {
	private static final String[] columnsTableServeurs = {"SN_S", "D\u00E9signation", "Mémoire restante (Go)"};
	private static final String[] columnsTableImprimante = {"SN_I", "D\u00E9signation", "R\u00E9solution"};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Tableaux
	private JScrollPane scrllpaneImprimante, scrllpaneServeurs;
	private JTable tableImprimante, tableServeurs;
	private DefaultTableModel tableModelServeurs, tableModelImprimante;
	
	//ComboBox
	private JComboBox<String> cmbboxAssignedTo;
	private DefaultComboBoxModel<String> cmbboxModel;

	//Spinners
	private JSpinner spinnerRAM, spinnerCPU;
	
	//TextFields
	private JTextField tfSNO, tfDesignation, tfDateAttribution, tfDateRestitution;
	
	//Labels statiques
	private JLabel staticLblUniteTemps, staticLblTitle, staticLblSNO, staticLblDateAttribution, staticLblDateRestitution, staticLblImprimanteTitle, staticLblServeursTitle, staticLblAChanger, staticLblARetourner, staticLblTempsUtilisation, staticLblAssignedTo, staticLblDesignation, staticLblCPU, staticLblRAM;

	//Labels dynamiques
	private JLabel lblAChanger, lblJoursUtilisation, lblARetourner;
	
	//Boutons
	private JButton btnConnecterImprimante, btnDeconnecterImprimante, btnConnecterServeurs, btnDeconnecterServeurs;
	
	//Modèles
	private Employes employes;
	private OrdinateurServeurLinks ordinateurServeurLinks;
	private Serveurs serveurs;
	private Imprimantes imprimantes;
	
	//ArrayList contenant les ajouts ou suppressions de liens vers les serveurs
	private ArrayList<Serveur> deletedLinks;
	private HashMap<Serveur, Integer> addedLinks;

	
	
	//Formulaire de connexion aux serveurs
	private ConnexionServeur connexionServeurForm = null;
	private ConnexionImprimante connexionImprimanteForm = null;

	
	/**
	 * Constructeur pour un nouvel ordinateur
	 * @param employes
	 * @param ordinateurServeurLinks
	 * @param serveurs
	 */
	public FicheOrdinateur(Employes employes, OrdinateurServeurLinks ordinateurServeurLinks, Serveurs serveurs, Imprimantes imprimantes) {
		super(Fiche.State.CREATION);
		Fiche.State initialState = Fiche.State.CREATION;
		
		//Modèles
		this.employes = employes;
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.serveurs = serveurs;
		this.imprimantes = imprimantes;
		
		//Listes de delta
		this.addedLinks = new HashMap<Serveur, Integer>();
		this.deletedLinks = new ArrayList<Serveur>();
		
		//Modèle de la combobox
		this.cmbboxModel = new DefaultComboBoxModel<String>();
		this.cmbboxModel.addElement(null);
		
		for(Employe employe : employes.getItems()) {
			this.cmbboxModel.addElement(employe.getMatricule());
		}
		
		//Modèle tables imprimante et serveurs
		this.tableModelServeurs = new DefaultTableModel();
		this.tableModelImprimante = new DefaultTableModel();
		this.tableModelServeurs.setColumnIdentifiers(FicheOrdinateur.columnsTableServeurs);
		this.tableModelImprimante.setColumnIdentifiers(FicheOrdinateur.columnsTableImprimante);
		
		initComponents();
		this.changeState(initialState);
	}
	
	/**
	 * Constructeur pour un ordinateur existant
	 * @param initialState
	 * @param ordinateur
	 * @param employes
	 * @param ordinateurServeurLinks
	 * @param serveurs
	 * @wbp.parser.constructor
	 */
	public FicheOrdinateur(Fiche.State initialState, Ordinateur ordinateur, Employes employes, OrdinateurServeurLinks ordinateurServeurLinks, Serveurs serveurs, Ordinateurs ordinateurs, Imprimantes imprimantes) {
		super(initialState);
		
		this.employes = employes;
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.serveurs = serveurs;
		this.imprimantes = imprimantes;
		
		//Listes de delta
		this.addedLinks = new HashMap<Serveur, Integer>();
		this.deletedLinks = new ArrayList<Serveur>();
		
		//Modèle de la combobox
		this.cmbboxModel = new DefaultComboBoxModel<String>();
		this.cmbboxModel.addElement(null);
		
		for(Employe employe : employes.getItems()) {
			this.cmbboxModel.addElement(employe.getMatricule());
			if(ordinateur.getProprietaire() != null && ordinateur.getProprietaire().getMatricule().equals(employe.getMatricule())) {
				this.cmbboxModel.setSelectedItem(employe.getMatricule());
			}
		}
		
		//Modèle tables imprimante et serveurs
		this.tableModelServeurs = new DefaultTableModel();
		this.tableModelImprimante = new DefaultTableModel();
		this.tableModelServeurs.setColumnIdentifiers(FicheOrdinateur.columnsTableServeurs);
		this.tableModelImprimante.setColumnIdentifiers(FicheOrdinateur.columnsTableImprimante);
		
		//Ajout des liens existants
		Object[] rowDataLink = new Object[FicheOrdinateur.columnsTableServeurs.length];
		
		for(OrdinateurServeurLink ordinateurServeurLink : ordinateurServeurLinks.findBySNO(ordinateur.getSn())) {
			rowDataLink[0] = ordinateurServeurLink.getServeur().getSn();
			rowDataLink[1] = ordinateurServeurLink.getServeur().getDesignation();
			rowDataLink[2] = ordinateurServeurLink.getServeur().getMemoire() - this.serveurs.calculerSommeQuotas(ordinateurServeurLink.getServeur())/1024;
			
			this.tableModelServeurs.addRow(rowDataLink);
		}
		
		initComponents();
		this.changeState(initialState);
		
		//On initialise les champs du formulaire avec les attributs de l'ordinateur 
		this.tfSNO.setText(ordinateur.getSn());
		this.tfDesignation.setText(ordinateur.getDesignation());
		
		String tempsUtilisation = null;
		
		if(ordinateur.getDateRestitution() != null) {
			this.tfDateRestitution.setText(Ordinateur.dateFormatterJavaToOracle.format(ordinateur.getDateRestitution()));
			tempsUtilisation = Long.toString(TimeUnit.MILLISECONDS.toDays(ordinateur.getDateRestitution().getTime() - ordinateur.getDateAttribution().getTime()));
		}
		if(ordinateur.getDateAttribution() != null) {
			this.tfDateAttribution.setText(Ordinateur.dateFormatterJavaToOracle.format(ordinateur.getDateAttribution()));
			if(tempsUtilisation == null)
				tempsUtilisation = Long.toString(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - ordinateur.getDateAttribution().getTime()));
		}
			
		this.lblJoursUtilisation.setText(tempsUtilisation);
		this.lblAChanger.setText(ordinateurs.ordinateurMustBeChanged(ordinateur) ? "OUI" : "NON");
		this.lblARetourner.setText(ordinateurs.ordinateurMustBeReturned(ordinateur) ? "OUI" : "NON");
		this.spinnerCPU.setValue(ordinateur.getCpu());
		this.spinnerRAM.setValue(ordinateur.getRam());
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.lblAChanger.setVisible(newState != Fiche.State.CREATION);
		this.lblARetourner.setVisible(newState != Fiche.State.CREATION);
		this.lblJoursUtilisation.setText((newState == Fiche.State.CREATION)?"0":"##");
		this.staticLblAChanger.setVisible(newState != Fiche.State.CREATION);
		this.staticLblARetourner.setVisible(newState != Fiche.State.CREATION);
		
		//Interdit à la visualisation
		this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
		this.cmbboxAssignedTo.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinnerCPU.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinnerRAM.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnAnnuler.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnConnecterImprimante.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnConnecterServeurs.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnDeconnecterImprimante.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnDeconnecterServeurs.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnSauver.setEnabled(newState != Fiche.State.VISUALISATION);
		
		//Autorisé pour création
		this.tfSNO.setEditable(newState == Fiche.State.CREATION);
		
	}
	
	public JButton getBtnConnecterImprimante() {
		return btnConnecterImprimante;
	}
	
	public JButton getBtnConnecterServeurs() {
		return btnConnecterServeurs;
	}
	
	public JButton getBtnDeconnecterImprimante() {
		return btnDeconnecterImprimante;
	}
	
	public JButton getBtnDeconnecterServeurs() {
		return btnDeconnecterServeurs;
	}

	public String getSN() {
		return this.tfSNO.getText();
	}
	
	public String getDesignation() {
		return this.tfDesignation.getText();
	}
	
	public int getRAM() {
		return (int)this.spinnerRAM.getValue();
	}
	
	public double getCPU() {
		return (double)this.spinnerCPU.getValue();
	}
	
	public Employe getProprietaire() {
		return this.employes.findByMatricule((String)this.cmbboxAssignedTo.getModel().getSelectedItem());
	}
	
	public HashMap<Serveur, Integer> getAddedLinks() {
		return addedLinks;
	}
	
	public ArrayList<Serveur> getDeletedLinks() {
		return deletedLinks;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		
		
		if(e.getSource() == this.btnConnecterServeurs) {
			if(this.connexionServeurForm == null) {
				//On détermine les serveurs pour lesquels on peut se connecter
				ArrayList<Serveur> serveursDisponibles = new ArrayList<>(this.serveurs.getItems());
				
				int columnSNSIndex = this.tableServeurs.convertColumnIndexToView(this.tableModelServeurs.findColumn(FicheOrdinateur.columnsTableServeurs[0]));
				for(int rowIndex = 0; rowIndex < this.tableModelServeurs.getRowCount(); rowIndex++) {
					Serveur serveur = this.serveurs.findBySN((String) this.tableModelServeurs.getValueAt(rowIndex, columnSNSIndex));
					if(serveursDisponibles.contains(serveur))
						serveursDisponibles.remove(serveur);
				}
				
				//On ouvre le formulaire
				this.connexionServeurForm = new ConnexionServeur(serveursDisponibles, this.serveurs, this);
				this.connexionServeurForm.getBtnSauvegarder().addActionListener(this);
				this.connexionServeurForm.setVisible(true);
			}
			
			this.connexionServeurForm.toFront();
		}
		if(e.getSource() == this.btnConnecterImprimante) {
			if(this.connexionImprimanteForm == null) {
				
				//On ouvre le formulaire
				this.connexionImprimanteForm = new ConnexionImprimante(this.imprimantes, this);
				this.connexionImprimanteForm.getBtnSauvegarder().addActionListener(this);
				this.connexionImprimanteForm.setVisible(true);
			}
			
			this.connexionImprimanteForm.toFront();
		}
		if(this.connexionServeurForm != null && e.getSource() == this.connexionServeurForm.getBtnSauvegarder()) {
			//On récupère les numéros de série séléctionnés
			for(Serveur serveur : this.connexionServeurForm.getSelectedServeurs()) {
				//On ajoute le liens aux liens ajoutés
				this.addedLinks.put(serveur, this.connexionServeurForm.getQuotaSelected());
				
				//On ajoute la ligne à la table
				Object[] rowData = new Object[FicheOrdinateur.columnsTableServeurs.length];
				rowData[0] = serveur.getSn();
				rowData[1] = serveur.getDesignation();
				rowData[2] = serveur.getMemoire() - (this.serveurs.calculerSommeQuotas(serveur) + this.connexionServeurForm.getQuotaSelected())/1024;
				this.tableModelServeurs.addRow(rowData);
				this.tableModelServeurs.fireTableDataChanged();
			}
			
			//Fermeture du formulaire
			this.connexionServeurForm.dispose();
			this.connexionServeurForm = null;
		}
		if(e.getSource() == this.btnDeconnecterServeurs) {
			int deletedRowsCounter = 0;
			int columnSNSIndex = this.tableServeurs.convertColumnIndexToView(this.tableModelServeurs.findColumn(FicheOrdinateur.columnsTableServeurs[0]));
			
			for(int rowIndex : this.tableServeurs.getSelectedRows()) {
				Serveur serveur = this.serveurs.findBySN((String) this.tableServeurs.getValueAt(rowIndex - deletedRowsCounter, columnSNSIndex));
				if(this.addedLinks.containsKey(serveur)) {
					this.addedLinks.remove(serveur);
				}else {
					this.deletedLinks.add(serveur);
				}
				this.tableModelServeurs.removeRow(rowIndex - deletedRowsCounter);
				deletedRowsCounter++;
			}
		}
		}
	
	
	public void initComponents() {
		
		//Configuration de la fenêtre
		setTitle("Ordinateur");
		setBounds(100, 100, 608, 715);
		
		//Configuration des labels statiques
		staticLblSNO = new JLabel("SN_O :");
		staticLblSNO.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSNO.setBounds(64, 62, 63, 14);
		contentPane.add(staticLblSNO);
		
		staticLblDateAttribution = new JLabel("Date d'attribution :");
		staticLblDateAttribution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDateAttribution.setBounds(12, 82, 117, 22);
		contentPane.add(staticLblDateAttribution);
		
		staticLblDateRestitution = new JLabel("Date de restitution :");
		staticLblDateRestitution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDateRestitution.setBounds(0, 110, 128, 22);
		contentPane.add(staticLblDateRestitution);
		
		staticLblAChanger = new JLabel("A changer :");
		staticLblAChanger.setBounds(53, 184, 76, 22);
		contentPane.add(staticLblAChanger);
		
		staticLblARetourner = new JLabel("A retourner : ");
		staticLblARetourner.setBounds(223, 184, 81, 22);
		contentPane.add(staticLblARetourner);
		
		staticLblDesignation = new JLabel("Designation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(43, 151, 86, 20);
		contentPane.add(staticLblDesignation);
		
		staticLblCPU = new JLabel("CPU : ");
		staticLblCPU.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblCPU.setBounds(346, 114, 63, 14);
		contentPane.add(staticLblCPU);
		
		staticLblAssignedTo = new JLabel("Assign\u00E9 \u00E0 : ");
		staticLblAssignedTo.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblAssignedTo.setBounds(328, 54, 81, 22);
		contentPane.add(staticLblAssignedTo);
		
		staticLblRAM = new JLabel(" RAM : ");
		staticLblRAM.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblRAM.setBounds(346, 86, 63, 14);
		contentPane.add(staticLblRAM);
		
		staticLblTempsUtilisation = new JLabel("Temps d'utilisation :");
		staticLblTempsUtilisation.setBounds(406, 151, 147, 20);
		contentPane.add(staticLblTempsUtilisation);
		
		staticLblImprimanteTitle = new JLabel("Imprimante connect\u00E9e :");
		staticLblImprimanteTitle.setBounds(10, 248, 169, 22);
		contentPane.add(staticLblImprimanteTitle);
		
		staticLblServeursTitle = new JLabel("Serveurs connect\u00E9s :");
		staticLblServeursTitle.setBounds(10, 346, 169, 22);
		contentPane.add(staticLblServeursTitle);
		
		staticLblUniteTemps = new JLabel("Jours");
		staticLblUniteTemps.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblUniteTemps.setFont(new Font("Tahoma", Font.BOLD, 18));
		staticLblUniteTemps.setBounds(487, 184, 63, 20);
		contentPane.add(staticLblUniteTemps);
		
		staticLblTitle = new JLabel("Ordinateur");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(219, 13, 154, 32);
		contentPane.add(staticLblTitle);
		
		//Configuration TextFields
		tfSNO = new JTextField();
		tfSNO.setBounds(139, 59, 117, 20);
		contentPane.add(tfSNO);
		tfSNO.setColumns(10);
		
		tfDesignation = new JTextField();
		tfDesignation.setColumns(0);
		tfDesignation.setBounds(139, 151, 234, 20);
		contentPane.add(tfDesignation);
		
		tfDateAttribution = new JTextField();
		tfDateAttribution.setEditable(false);
		tfDateAttribution.setColumns(10);
		tfDateAttribution.setBounds(139, 84, 117, 20);
		contentPane.add(tfDateAttribution);
		
		tfDateRestitution = new JTextField();
		tfDateRestitution.setEditable(false);
		tfDateRestitution.setColumns(10);
		tfDateRestitution.setBounds(139, 110, 117, 20);
		contentPane.add(tfDateRestitution);
		
		//Configuration Spinners
		spinnerRAM = new JSpinner();
		spinnerRAM.setModel(new SpinnerNumberModel(4, 2, 64, 1));
		spinnerRAM.setBounds(421, 84, 117, 20);
		contentPane.add(spinnerRAM);
		
		spinnerCPU = new JSpinner();
		spinnerCPU.setModel(new SpinnerNumberModel(3.1, 2.0, 5.0, 0.1));
		spinnerCPU.setBounds(421, 112, 117, 20);
		contentPane.add(spinnerCPU);
		
		//Configuration ComboBox
		cmbboxAssignedTo = new JComboBox<>();
		cmbboxAssignedTo.setEditable(true);
		cmbboxAssignedTo.setBounds(421, 55, 117, 20);
		this.cmbboxAssignedTo.setModel(this.cmbboxModel);
		contentPane.add(cmbboxAssignedTo);
		
		//Configuration Labels dynamiques
		lblAChanger = new JLabel("NON");
		lblAChanger.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAChanger.setBounds(135, 184, 63, 20);
		contentPane.add(lblAChanger);
		
		lblARetourner = new JLabel("NON");
		lblARetourner.setHorizontalAlignment(SwingConstants.RIGHT);
		lblARetourner.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblARetourner.setBounds(284, 184, 63, 20);
		contentPane.add(lblARetourner);
		
		lblJoursUtilisation = new JLabel("#####");
		lblJoursUtilisation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJoursUtilisation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJoursUtilisation.setBounds(396, 184, 86, 20);
		contentPane.add(lblJoursUtilisation);
		
		//Configuration des tableaux
		scrllpaneImprimante = new JScrollPane();
		scrllpaneImprimante.setBounds(10, 276, 452, 51);
		contentPane.add(scrllpaneImprimante);
		
		scrllpaneServeurs = new JScrollPane();
		scrllpaneServeurs.setBounds(10, 374, 567, 180);
		contentPane.add(scrllpaneServeurs);
		
		tableImprimante = new JTable();
		tableImprimante.setFillsViewportHeight(true);
		tableImprimante.setModel(this.tableModelImprimante);
		tableImprimante.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrllpaneImprimante.setViewportView(tableImprimante);
		
		tableServeurs = new JTable();
		tableServeurs.setFillsViewportHeight(true);
		tableServeurs.setModel(this.tableModelServeurs);
		tableServeurs.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableServeurs.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableServeurs.getColumnModel().getColumn(2).setPreferredWidth(70);
		scrllpaneServeurs.setViewportView(tableServeurs);
		
		//Configuration des boutons
		btnAnnuler.setBounds(242, 632, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(333, 632, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(472, 632, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		btnConnecterImprimante = new JButton("Connecter");
		btnConnecterImprimante.setBounds(466, 276, 111, 25);
		contentPane.add(btnConnecterImprimante);
		
		btnDeconnecterImprimante = new JButton("D\u00E9connecter");
		btnDeconnecterImprimante.setBounds(466, 302, 111, 25);
		contentPane.add(btnDeconnecterImprimante);
		
		btnConnecterServeurs = new JButton("Connecter");
		btnConnecterServeurs.setBounds(10, 564, 111, 25);
		contentPane.add(btnConnecterServeurs);
		
		btnDeconnecterServeurs = new JButton("D\u00E9connecter");
		btnDeconnecterServeurs.setBounds(127, 564, 111, 25);
		contentPane.add(btnDeconnecterServeurs);
	}
}
