package gestionParcInfo.view.fiche;

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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * gestion de la fiche Ordinateur.
 * @author Sebastien Claudel
 *
 */
public class FicheOrdinateur extends Fiche implements ActionListener, WindowListener {
	private static final String[] columnsTableServeurs = {"SN_S", "Désignation", "Mémoire restante (Go)"};
	private static final String[] columnsTableImprimante = {"SN_I", "Désignation", "Résolution"};
	
	
	private static final long serialVersionUID = 1L;
	
	//Tableaux
	private JScrollPane scrllpaneImprimante;
	private JScrollPane scrllpaneServeurs;
	private JTable tableImprimante;
	private JTable tableServeurs;
	private DefaultTableModel tableModelServeurs;
	private DefaultTableModel tableModelImprimante;
	
	//ComboBox
	private JComboBox<String> cmbboxAssignedTo;
	private DefaultComboBoxModel<String> cmbboxModel;

	//Spinners
	private JSpinner spinnerRam;
	private JSpinner spinnerCpu;
	
	//TextFields
	private JTextField tfSno; 
	private JTextField tfDesignation;
	private JTextField tfDateAttribution;
	private JTextField tfDateRestitution;
	
	//Labels statiques
	private JLabel staticLblUniteTemps;
	private JLabel staticLblTitle;
	private JLabel staticLblSno;
	private JLabel staticLblDateAttribution;
	private JLabel staticLblDateRestitution;
	private JLabel staticLblImprimanteTitle;
	private JLabel staticLblServeursTitle;
	private JLabel staticLblAChanger;
	private JLabel staticLblARetourner;
	private JLabel staticLblTempsUtilisation;
	private JLabel staticLblAssignedTo;
	private JLabel staticLblDesignation;
	private JLabel staticLblCpu;
	private JLabel staticLblRam;

	//Labels dynamiques
	private JLabel lblAChanger;
	private JLabel lblJoursUtilisation;
	private JLabel lblARetourner;
	
	//Boutons
	private JButton btnConnecterImprimante;
	private JButton btnDeconnecterImprimante;
	private JButton btnConnecterServeurs;
	private JButton btnDeconnecterServeurs;
	
	//Modèles
	private Employes employes;
	
	private Serveurs serveurs;
	private Imprimantes imprimantes;
	
	//ArrayList contenant les ajouts ou suppressions de liens vers les serveurs
	private ArrayList<Serveur> deletedLinks;
	private HashMap<Serveur, Integer> addedLinks;

	
	
	//Formulaire de connexion aux serveurs
	private ConnexionServeur connexionServeurForm = null;
	private ConnexionImprimante connexionImprimanteForm = null;

	
	/**
	 * Constructeur par default.
	 * @param employes
	 * 
	 * @param ordinateurServeurLinks
	 * 
	 * @param serveurs
	 * 
	 */
	public FicheOrdinateur(Fiche.State initialState, Employes employes, Serveurs serveurs, Imprimantes imprimantes) {
		super(initialState);
		
		//Modèles
		this.employes = employes;
		this.serveurs = serveurs;
		this.imprimantes = imprimantes;
		
		//Listes de delta
		this.addedLinks = new HashMap<Serveur, Integer>();
		this.deletedLinks = new ArrayList<Serveur>();
		
		//Modèle de la combobox
		this.cmbboxModel = new DefaultComboBoxModel<String>();
		this.cmbboxModel.addElement(null);
		
		for (Employe employe : employes.getItems()) {
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
	 * Constructeur pour un ordinateur existant.
	 * @param initialState
	 * 
	 * @param ordinateur
	 * 
	 * @param employes
	 * 
	 * @param ordinateurServeurLinks
	 * 
	 * @param serveurs
	 * 
	 */
	public FicheOrdinateur(Fiche.State initialState, Ordinateur ordinateur, Employes employes, OrdinateurServeurLinks ordinateurServeurLinks, Serveurs serveurs, Ordinateurs ordinateurs, Imprimantes imprimantes) {
		this(initialState, employes, serveurs, imprimantes);
		
	
		
		//Selection du matricule de l'employé associé"
		for (Employe employe : employes.getItems()) {
			if (ordinateur.getProprietaire() != null && ordinateur.getProprietaire().getMatricule().equals(employe.getMatricule())) {
				this.cmbboxModel.setSelectedItem(employe.getMatricule());
			}
		}
		
		//Ajout des liens existants
		Object[] rowDataLink = new Object[FicheOrdinateur.columnsTableServeurs.length];
		
		for (OrdinateurServeurLink ordinateurServeurLink : ordinateurServeurLinks.findBySno(ordinateur.getSn())) {
			rowDataLink[0] = ordinateurServeurLink.getServeur().getSn();
			rowDataLink[1] = ordinateurServeurLink.getServeur().getDesignation();
			rowDataLink[2] = ordinateurServeurLink.getServeur().getMemoire() - this.serveurs.calculerSommeQuotas(ordinateurServeurLink.getServeur()) / 1024;
			this.tableModelServeurs.addRow(rowDataLink);
		}
		
		//Ajout de l'imprimante
		if (ordinateur.getImprimante() != null) {
			Object[] rowDataImprimante = new Object[FicheOrdinateur.columnsTableImprimante.length];
			rowDataImprimante[0] = ordinateur.getImprimante().getSn();
			rowDataImprimante[1] = ordinateur.getImprimante().getDesignation();
			rowDataImprimante[2] = ordinateur.getImprimante().getResolution();
			this.tableModelImprimante.addRow(rowDataImprimante);
		}
		
		//On initialise les champs du formulaire avec les attributs de l'ordinateur 
		this.tfSno.setText(ordinateur.getSn());
		this.tfDesignation.setText(ordinateur.getDesignation());
		
		if (ordinateur.getDateRestitution() != null) {
			this.tfDateRestitution.setText(Ordinateur.dateFormatterJavaToOracle.format(ordinateur.getDateRestitution()));
		}
		if (ordinateur.getDateAttribution() != null) {
			this.tfDateAttribution.setText(Ordinateur.dateFormatterJavaToOracle.format(ordinateur.getDateAttribution()));
		}
		
		String tempsUtilisation = "0";
		if (ordinateur.countJoursUtilisation() != null) {
			tempsUtilisation = Long.toString(ordinateur.countJoursUtilisation());
		}
		this.lblJoursUtilisation.setText(tempsUtilisation);
		this.lblAChanger.setText(ordinateurs.ordinateurMustBeChanged(ordinateur) ? "OUI" : "NON");
		this.lblARetourner.setText(ordinateurs.ordinateurMustBeReturned(ordinateur) ? "OUI" : "NON");
		this.spinnerCpu.setValue(ordinateur.getCpu());
		this.spinnerRam.setValue(ordinateur.getRam());
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.lblAChanger.setVisible(newState != Fiche.State.CREATION);
		this.lblARetourner.setVisible(newState != Fiche.State.CREATION);
		this.lblJoursUtilisation.setText((newState == Fiche.State.CREATION) ? "0" : this.lblJoursUtilisation.getText());
		this.staticLblAChanger.setVisible(newState != Fiche.State.CREATION);
		this.staticLblARetourner.setVisible(newState != Fiche.State.CREATION);
		
		//Interdit à la visualisation
		this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
		this.cmbboxAssignedTo.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinnerCpu.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinnerRam.setEnabled(newState != Fiche.State.VISUALISATION);
		
		this.btnConnecterImprimante.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnConnecterServeurs.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnDeconnecterImprimante.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnDeconnecterServeurs.setEnabled(newState != Fiche.State.VISUALISATION);
		
		
		//Autorisé pour création
		this.tfSno.setEditable(newState == Fiche.State.CREATION);
		
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

	public String getSn() {
		return this.tfSno.getText();
	}
	
	public String getDesignation() {
		return this.tfDesignation.getText();
	}
	
	public int getRam() {
		return (int)this.spinnerRam.getValue();
	}
	
	public double getCpu() {
		return (double)this.spinnerCpu.getValue();
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
	
	/**
	 * Recupération de l'imprimante.
	 * @return
	 */
	public Imprimante getImprimante() {
		Imprimante imprimante = null;
		
		if (this.tableModelImprimante.getRowCount() > 0) {
		  int column = this.tableModelImprimante.findColumn(FicheOrdinateur.columnsTableImprimante[0]);
			int columnSniIndex = this.tableServeurs.convertColumnIndexToView(column);
			imprimante = this.imprimantes.findBySn((String)this.tableModelImprimante.getValueAt(0, columnSniIndex));
		}
		
		return imprimante;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource() == this.btnConnecterImprimante) {
			if (this.connexionImprimanteForm == null) {
				//On ouvre le formulaire
				this.connexionImprimanteForm = new ConnexionImprimante(this.imprimantes, this);
				this.connexionImprimanteForm.getBtnSauvegarder().addActionListener(this);
				this.connexionImprimanteForm.addWindowListener(this);
				this.connexionImprimanteForm.setVisible(true);
			}
			
			this.connexionImprimanteForm.toFront();
		}
		if (this.connexionImprimanteForm != null && e.getSource() == this.connexionImprimanteForm.getBtnSauvegarder()) {
			Imprimante imprimante = this.connexionImprimanteForm.getSelectedImprimante();
			
			//On met à jour la table
			Object[] rowData = new Object[FicheOrdinateur.columnsTableImprimante.length];
			rowData[0] = imprimante.getSn();
			rowData[1] = imprimante.getDesignation();
			rowData[2] = imprimante.getResolution();
			this.tableModelImprimante.setRowCount(0);
			this.tableModelImprimante.addRow(rowData);
			this.tableModelImprimante.fireTableDataChanged();
			
			//Fermeture du formulaire
			this.connexionImprimanteForm.dispose();
			this.connexionImprimanteForm = null;
		}
		
		if (e.getSource() == this.btnDeconnecterImprimante) {
			this.tableModelImprimante.setRowCount(0);
		}
		if (e.getSource() == this.btnConnecterServeurs) {
			if (this.connexionServeurForm == null) {
				//On détermine les serveurs pour lesquels on peut se connecter
				ArrayList<Serveur> serveursDisponibles = new ArrayList<>(this.serveurs.getItems());
				
				int column = this.tableModelImprimante.findColumn(FicheOrdinateur.columnsTableImprimante[0]);
				int columnSnsIndex = this.tableServeurs.convertColumnIndexToView(column);
				for (int rowIndex = 0; rowIndex < this.tableModelServeurs.getRowCount(); rowIndex++) {
					Serveur serveur = this.serveurs.findBySn((String) this.tableModelServeurs.getValueAt(rowIndex, columnSnsIndex));
					if (serveursDisponibles.contains(serveur)) {
						serveursDisponibles.remove(serveur);
					}
				}
				
				//On ouvre le formulaire
				this.connexionServeurForm = new ConnexionServeur(serveursDisponibles, this.serveurs, this);
				this.connexionServeurForm.getBtnSauvegarder().addActionListener(this);
				this.connexionServeurForm.addWindowListener(this);
				this.connexionServeurForm.setVisible(true);
			}
			
			this.connexionServeurForm.toFront();
		}
		if (this.connexionServeurForm != null && e.getSource() == this.connexionServeurForm.getBtnSauvegarder()) {
			//On récupère les numéros de série séléctionnés
			for (Serveur serveur : this.connexionServeurForm.getSelectedServeurs()) {
				//On ajoute le liens aux liens ajoutés
				this.addedLinks.put(serveur, this.connexionServeurForm.getQuotaSelected());
				
				//On ajoute la ligne à la table
				Object[] rowData = new Object[FicheOrdinateur.columnsTableServeurs.length];
				rowData[0] = serveur.getSn();
				rowData[1] = serveur.getDesignation();
				rowData[2] = serveur.getMemoire() - (this.serveurs.calculerSommeQuotas(serveur) + this.connexionServeurForm.getQuotaSelected()) / 1024;
				this.tableModelServeurs.addRow(rowData);
				this.tableModelServeurs.fireTableDataChanged();
			}
			
			//Fermeture du formulaire
			this.connexionServeurForm.dispose();
			this.connexionServeurForm = null;
		}
		if (e.getSource() == this.btnDeconnecterServeurs) {
			int deletedRowsCounter = 0;
			int columnSnsIndex = this.tableServeurs.convertColumnIndexToView(this.tableModelServeurs.findColumn(FicheOrdinateur.columnsTableServeurs[0]));
			
			for (int rowIndex : this.tableServeurs.getSelectedRows()) {
				Serveur serveur = this.serveurs.findBySn((String) this.tableServeurs.getValueAt(rowIndex - deletedRowsCounter, columnSnsIndex));
				if (this.addedLinks.containsKey(serveur)) {
					this.addedLinks.remove(serveur);
				} else {
					this.deletedLinks.add(serveur);
				}
				this.tableModelServeurs.removeRow(rowIndex - deletedRowsCounter);
				deletedRowsCounter++;
			}
		}
	}
	
	/**
	 * initialisation des composants.
	 */
	public void initComponents() {
		
		//Configuration de la fenêtre
		setTitle("Ordinateur");
		setBounds(100, 100, 608, 715);
		
		//Configuration des labels statiques
		staticLblSno = new JLabel("SN_O :");
		staticLblSno.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSno.setBounds(64, 62, 63, 14);
		contentPane.add(staticLblSno);
		
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
		
		staticLblCpu = new JLabel("CPU : ");
		staticLblCpu.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblCpu.setBounds(346, 114, 63, 14);
		contentPane.add(staticLblCpu);
		
		staticLblAssignedTo = new JLabel("Assigné : ");
		staticLblAssignedTo.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblAssignedTo.setBounds(328, 54, 81, 22);
		contentPane.add(staticLblAssignedTo);
		
		staticLblRam = new JLabel(" RAM : ");
		staticLblRam.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblRam.setBounds(346, 86, 63, 14);
		contentPane.add(staticLblRam);
		
		staticLblTempsUtilisation = new JLabel("Temps d'utilisation :");
		staticLblTempsUtilisation.setBounds(406, 151, 147, 20);
		contentPane.add(staticLblTempsUtilisation);
		
		staticLblImprimanteTitle = new JLabel("Imprimante connectée :");
		staticLblImprimanteTitle.setBounds(10, 248, 169, 22);
		contentPane.add(staticLblImprimanteTitle);
		
		staticLblServeursTitle = new JLabel("Serveurs connectés :");
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
		tfSno = new JTextField();
		tfSno.setBounds(139, 59, 117, 20);
		contentPane.add(tfSno);
		tfSno.setColumns(10);
		
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
		spinnerRam = new JSpinner();
		spinnerRam.setModel(new SpinnerNumberModel(4, 2, 64, 1));
		spinnerRam.setBounds(421, 84, 117, 20);
		contentPane.add(spinnerRam);
		
		spinnerCpu = new JSpinner();
		spinnerCpu.setModel(new SpinnerNumberModel(3.1, 2.0, 5.0, 0.1));
		spinnerCpu.setBounds(421, 112, 117, 20);
		contentPane.add(spinnerCpu);
		
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
		
		btnDeconnecterImprimante = new JButton("Déconnecter");
		btnDeconnecterImprimante.setBounds(466, 302, 111, 25);
		contentPane.add(btnDeconnecterImprimante);
		
		btnConnecterServeurs = new JButton("Connecter");
		btnConnecterServeurs.setBounds(10, 564, 111, 25);
		contentPane.add(btnConnecterServeurs);
		
		btnDeconnecterServeurs = new JButton("Déconnecter");
		btnDeconnecterServeurs.setBounds(127, 564, 111, 25);
		contentPane.add(btnDeconnecterServeurs);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (e.getSource() == this.connexionImprimanteForm) {
			this.connexionImprimanteForm = null;
		}
		if (e.getSource() == this.connexionServeurForm) {
			this.connexionServeurForm = null;
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
