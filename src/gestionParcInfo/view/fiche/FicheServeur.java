package gestionParcInfo.view.fiche;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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

import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Serveurs;
/**
 * Gestion de la fiche Serveur
 * @author Florian Lemarquand
 */
public class FicheServeur extends Fiche implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] columnsNames = {"SN_O", "Description", "Quota (Mo)", "Employe"};
	
	//Champs texte
	private JTextField tfNumeroDeSerie;
	private JTextField tfDesignation;
	
	//Tableaux
	private JTable tableOrdinateur;
	private DefaultTableModel tableModel;
	
	//labels statiques
	private JLabel staticLblMemoire;
	private JLabel staticLblUniteMemoire;
	private JLabel staticLblNumeroDeSerie;
	private JLabel staticLblOrdinateursConnectés;
	private JLabel staticLblTitle;
	private JLabel staticLbldesignation;
	private JLabel staticLblCharge;
	private JLabel staticLblPourcent;
	private JLabel staticLblNbOrdinateursConnectes;
	
	//labels dynamique
	private JLabel lblBnOrdinateursConnectés;
	private JLabel lblCharge;
	
	//Bouttons
	private JButton btnDeconnecter; 
	
	//Spinner
	private JSpinner spinMemoire;
	
	//ScrollPane
	private JScrollPane scrlpaneOrdinateursConnectés;
	
	//Liste des liens à supprimer 
	private ArrayList<OrdinateurServeurLink> linksToDelete;
	
	//Modèles
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	//Serveur en cours de visualisation ou modification
	private Serveur serveur;
	
	/**
	 * Constructeur par défaut
	 * @param initialState
	 */
	public FicheServeur(Fiche.State initialState) {
		super(initialState);
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(FicheServeur.columnsNames);
		
		initComponents();
		this.btnDeconnecter.addActionListener(this);
		this.changeState(initialState);
	}
	
	/**
	 * Constructeur pour la modification ou la visualisation d'un serveur
	 * @param initialState
	 * @param serveur
	 * @param ordinateurServeurLinks
	 * @wbp.parser.constructor
	 */
	public FicheServeur(Fiche.State initialState, Serveur serveur, Serveurs serveurs, OrdinateurServeurLinks ordinateurServeurLinks) {
		this(initialState);
		
		this.linksToDelete = new ArrayList<OrdinateurServeurLink>();
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.serveur = serveur;
		
		//Ajout des liens avec les ordinateurs
		Object[] rowDataLink = new Object[FicheServeur.columnsNames.length];
		for(OrdinateurServeurLink ordinateurServeurLink : ordinateurServeurLinks.findBySns(serveur.getSn())) {
			rowDataLink[0] = ordinateurServeurLink.getOrdinateur().getSn();
			rowDataLink[1] = ordinateurServeurLink.getOrdinateur().getDesignation();
			rowDataLink[2] = ordinateurServeurLink.getQuota();
			
			if(ordinateurServeurLink.getOrdinateur().getProprietaire() != null)
				rowDataLink[3] = ordinateurServeurLink.getOrdinateur().getProprietaire().getMatricule();
			
			this.tableModel.addRow(rowDataLink);
		}
		
		DecimalFormat f = new DecimalFormat("##0.00");
		
		//Initialisation des informations sur le serveur
		this.tfDesignation.setText(serveur.getDesignation());
		this.tfNumeroDeSerie.setText(serveur.getSn());
		this.spinMemoire.setValue(serveur.getMemoire());
		this.lblBnOrdinateursConnectés.setText(Long.toString(serveurs.countOrdinateursLinked(serveur)));
		this.lblCharge.setText(f.format(serveurs.calculerChargeServeur(serveur)*100));
		
		this.changeState(initialState);
	}
	
	public String getSN() {
		return this.tfNumeroDeSerie.getText();
	}
	
	public String getDesignation() {
		return this.tfDesignation.getText();
	}
	
	public long getMemoire() {
		return (long)this.spinMemoire.getValue();
	}
	
	public ArrayList<OrdinateurServeurLink> getLinksToDelete() {
		return linksToDelete;
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.btnDeconnecter.setVisible(newState != Fiche.State.CREATION);
		
		
		//Interdit à la visualisation
		this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
		this.tfNumeroDeSerie.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinMemoire.setEnabled(newState != Fiche.State.VISUALISATION);
		this.btnDeconnecter.setEnabled(newState != Fiche.State.VISUALISATION);
		
		//Autorisé pour création
		this.tfNumeroDeSerie.setEditable(newState == Fiche.State.CREATION);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == this.btnDeconnecter) {
			int deletedRowsCounter = 0;
			int columnSNOIndex = this.tableOrdinateur.convertColumnIndexToView(this.tableModel.findColumn(FicheServeur.columnsNames[0]));
			
			for(int rowIndex : this.tableOrdinateur.getSelectedRows()) {
				OrdinateurServeurLink ordinateurServeurLink = this.ordinateurServeurLinks.findBySnoAndSns((String) this.tableOrdinateur.getValueAt(rowIndex - deletedRowsCounter, columnSNOIndex), this.serveur.getSn());
				this.linksToDelete.add(ordinateurServeurLink);
				this.tableModel.removeRow(rowIndex - deletedRowsCounter);
				deletedRowsCounter++;
			}
		}
	}
	
	public void initComponents() {
		setTitle("Serveur");
		setBounds(100, 100, 830, 446);
	
		
		//Configuration des labels statiques
		staticLblNumeroDeSerie = new JLabel("Num\u00E9ro de s\u00E9rie :");
		staticLblNumeroDeSerie.setBounds(53, 198, 123, 23);
		contentPane.add(staticLblNumeroDeSerie);
		
		staticLblOrdinateursConnectés = new JLabel("Ordinateurs connect\u00E9s : ");
		staticLblOrdinateursConnectés.setBounds(241, 30, 147, 22);
		contentPane.add(staticLblOrdinateursConnectés);
		
		staticLblTitle = new JLabel("Serveur");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(12, 0, 100, 32);
		contentPane.add(staticLblTitle);
		
		staticLbldesignation = new JLabel("D\u00E9signation :");
		staticLbldesignation.setBounds(53, 254, 123, 23);
		contentPane.add(staticLbldesignation);
		
		staticLblCharge = new JLabel("Charge \r\n(Somme quotas / M\u00E9moire) :");
		staticLblCharge.setBounds(12, 59, 223, 23);
		contentPane.add(staticLblCharge);
		
		staticLblPourcent = new JLabel("%");
		staticLblPourcent.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblPourcent.setBounds(118, 88, 47, 32);
		contentPane.add(staticLblPourcent);
		
		staticLblNbOrdinateursConnectes = new JLabel("Nombre d'ordinateurs connect\u00E9s : ");
		staticLblNbOrdinateursConnectes.setBounds(12, 126, 206, 22);
		contentPane.add(staticLblNbOrdinateursConnectes);
		
		staticLblUniteMemoire = new JLabel("Go");
		staticLblUniteMemoire.setBounds(180, 333, 24, 16);
		contentPane.add(staticLblUniteMemoire);
		
		staticLblMemoire = new JLabel("M\u00E9moire : ");
		staticLblMemoire.setBounds(53, 311, 76, 22);
		contentPane.add(staticLblMemoire);
		
		//Configuration des label dynamique
		lblBnOrdinateursConnectés = new JLabel("#####");
		lblBnOrdinateursConnectés.setHorizontalAlignment(SwingConstants.CENTER);
		lblBnOrdinateursConnectés.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBnOrdinateursConnectés.setBounds(12, 154, 192, 32);
		contentPane.add(lblBnOrdinateursConnectés);
		
		lblCharge = new JLabel("##");
		lblCharge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCharge.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCharge.setBounds(35, 88, 80, 32);
		contentPane.add(lblCharge);
		
		//Configuration des bouttons	
		btnDeconnecter = new JButton("D\u00E9connecter");
		btnDeconnecter.setEnabled(true);
		btnDeconnecter.setBounds(688, 30, 110, 23);
		contentPane.add(btnDeconnecter);
		
		btnAnnuler.setBounds(458, 366, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(549, 366, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(688, 366, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		//Configuration des champs de texte
		tfNumeroDeSerie = new JTextField();
		tfNumeroDeSerie.setEditable(false);
		tfNumeroDeSerie.setColumns(10);
		tfNumeroDeSerie.setBounds(53, 221, 123, 20);
		contentPane.add(tfNumeroDeSerie);
		
		tfDesignation = new JTextField();
		tfDesignation.setEditable(false);
		tfDesignation.setColumns(10);
		tfDesignation.setBounds(53, 278, 123, 20);
		contentPane.add(tfDesignation);
		
		//Configuration des spinner
		spinMemoire = new JSpinner();
		spinMemoire.setEnabled(false);
		spinMemoire.setModel(new SpinnerNumberModel(new Long(1000), new Long(0), new Long(50000), new Long(500)));
		spinMemoire.setBounds(53, 333, 123, 20);
		contentPane.add(spinMemoire);
		
		//Configuration des ScrollPane
		scrlpaneOrdinateursConnectés = new JScrollPane();
		scrlpaneOrdinateursConnectés.setBounds(240, 59, 553, 294);
		contentPane.add(scrlpaneOrdinateursConnectés);
		
		//Configuration des tableaux
		tableOrdinateur = new JTable();
		tableOrdinateur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableOrdinateur.setFillsViewportHeight(true);
		tableOrdinateur.setModel(this.tableModel);
		tableOrdinateur.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableOrdinateur.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrlpaneOrdinateursConnectés.setViewportView(tableOrdinateur);
	}
}
