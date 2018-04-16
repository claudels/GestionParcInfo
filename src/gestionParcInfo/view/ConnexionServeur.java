package gestionParcInfo.view;

import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.view.fiche.FicheOrdinateur;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Formulaire de connexion des serveurs aux ordinateurs.
 * @author seb
 *
 */
public class ConnexionServeur extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String[] columnNames = {"SN_S", "Désignation", "PCs connectés", "Charge", "Mémoire disponible (Go)"};
	
	//Fenetre principale
	private JPanel contentPane;
	
	//Tableaux
	private JTable tableConnexionServeur;
	private DefaultTableModel tableModel;

	//Labels statiques
	private JLabel staticLblTitle;
	private JLabel staticLblSubtitle;
	private JLabel staticLblSno;
	private JLabel staticLblDesignation;
	private JLabel staticLblProprietaire;
	private JLabel staticLblQuota;

	//Labels dynamique
	private JLabel lblSno;
	private JLabel lblDesignation;
	private JLabel lblProprietaire;
	private JLabel staticLblUniteQuota;
	private JButton btnSauvegarder;
	
	//Spinner
	private JSpinner spinnerQuota;
	
	//ScrollPane
	private JScrollPane scrllpaneConnexionServeur;
	
	//Serveurs
	private Serveurs serveurs;
	
	FicheOrdinateur ficheOrdinateur;
	
	/**
	 * Constructeur du formulaire pour la connexion des serveurs aux ordinateurs.
	 * @param serveursToShow Serveur disponibles
	 * @param serveurs Modèle des serveurs
	 * @param ficheOrdinateur Formulaire de l'ordinateur
	 */
	public ConnexionServeur(ArrayList<Serveur> serveursToShow, Serveurs serveurs, FicheOrdinateur ficheOrdinateur) {
		this.serveurs = serveurs;
		this.ficheOrdinateur = ficheOrdinateur;
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ConnexionServeur.columnNames);
		
		//Remplissage table
		DecimalFormat f = new DecimalFormat("##0.00");
		
		//On trie la liste par charge de serveur
		Collections.sort(serveursToShow, new Comparator<Serveur>() {
		  @Override
		  public int compare(Serveur s1, Serveur s2) {
		    return Double.compare(serveurs.calculerChargeServeur(s1), serveurs.calculerChargeServeur(s2));
		  }
		});
		
		
		for (Serveur serveur : serveursToShow) {
		  Object[] rowData = new Object[ConnexionServeur.columnNames.length];
			rowData[0] = serveur.getSn();
			rowData[1] = serveur.getDesignation();
			rowData[2] = serveurs.countOrdinateursLinked(serveur);
			rowData[3] = f.format(serveurs.calculerChargeServeur(serveur) * 100) + "%";
			rowData[4] = serveur.getMemoire() - serveurs.calculerSommeQuotas(serveur) / 1024;
			this.tableModel.addRow(rowData);
		}
		
		this.initComponents();
		
		//Modification du texte des label
		this.lblSno.setText(this.ficheOrdinateur.getSn());
		this.lblDesignation.setText(this.ficheOrdinateur.getDesignation());
		if (this.ficheOrdinateur.getProprietaire() != null) {
			this.lblProprietaire.setText(this.ficheOrdinateur.getProprietaire().getMatricule());
		}
	}
	
	public JButton getBtnSauvegarder() {
		return btnSauvegarder;
	}
	
	private void initComponents() {
		setTitle("Employé");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration des labels statiques
		staticLblTitle = new JLabel("Connexions serveurs");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(127, 0, 294, 32);
		contentPane.add(staticLblTitle);
		
		staticLblSubtitle = new JLabel("Séléctionnez un ou plusieurs serveurs puis cliquez sur le bouton Connecter : ");
		staticLblSubtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLblSubtitle);
		
		staticLblSno = new JLabel("SN_O : ");
		staticLblSno.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSno.setBounds(125, 66, 75, 14);
		contentPane.add(staticLblSno);
		
		staticLblDesignation = new JLabel("Désignation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(97, 94, 103, 14);
		contentPane.add(staticLblDesignation);
		
		staticLblProprietaire = new JLabel("Propriétaire : ");
		staticLblProprietaire.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblProprietaire.setBounds(111, 124, 89, 14);
		contentPane.add(staticLblProprietaire);
		
		staticLblQuota = new JLabel("Quotas : ");
		staticLblQuota.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblQuota.setBounds(409, 452, 69, 14);
		contentPane.add(staticLblQuota);
		
		//Configuration des labels dynamique
		lblSno = new JLabel();
		lblSno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSno.setBounds(206, 65, 115, 16);
		contentPane.add(lblSno);
		
		lblDesignation = new JLabel();
		lblDesignation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDesignation.setBounds(206, 93, 215, 16);
		contentPane.add(lblDesignation);
		
		lblProprietaire = new JLabel();
		lblProprietaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProprietaire.setBounds(206, 123, 125, 16);
		contentPane.add(lblProprietaire);
		
		staticLblUniteQuota = new JLabel("MO");
		staticLblUniteQuota.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblUniteQuota.setBounds(615, 452, 19, 14);
		contentPane.add(staticLblUniteQuota);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(533, 495, 105, 23);
		contentPane.add(btnSauvegarder);
		
		//Configuration des spinner
		spinnerQuota = new JSpinner();
		spinnerQuota.setModel(new SpinnerNumberModel(100, null, 1000000, 50));
		spinnerQuota.setBounds(466, 448, 137, 22);
		contentPane.add(spinnerQuota);
		
		//Configuration du scroll pane
		scrllpaneConnexionServeur = new JScrollPane();
		scrllpaneConnexionServeur.setBounds(12, 199, 624, 240);
		contentPane.add(scrllpaneConnexionServeur);
		
		//Configuration du tableau
		tableConnexionServeur = new JTable();
		this.tableConnexionServeur.setModel(this.tableModel);
		tableConnexionServeur.getColumnModel().getColumn(1).setPreferredWidth(132);
		tableConnexionServeur.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableConnexionServeur.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableConnexionServeur.getColumnModel().getColumn(4).setPreferredWidth(120);
		scrllpaneConnexionServeur.setViewportView(tableConnexionServeur);
	}
	
	/**
	 * Retourne les serveurs séléctionnés par l'utilisateur dans la table.
	 * @return ArrayList
	 */
	public ArrayList<Serveur> getSelectedServeurs() {
		ArrayList<Serveur> result = new ArrayList<>();
		
		int columnIndex = this.tableModel.findColumn(ConnexionServeur.columnNames[0]);
		int columnSnsIndex = this.tableConnexionServeur.convertColumnIndexToView(columnIndex);

		for (int rowIndex : this.tableConnexionServeur.getSelectedRows()) {
			result.add(this.serveurs.findBySN((String)this.tableModel.getValueAt(rowIndex, columnSnsIndex)));
		}
		
		return result;
	}
	
	public int getQuotaSelected() {
		return (int)this.spinnerQuota.getValue();
	}
}
