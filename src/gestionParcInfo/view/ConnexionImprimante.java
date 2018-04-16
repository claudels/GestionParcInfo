package gestionParcInfo.view;

import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.view.fiche.FicheOrdinateur;

import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class ConnexionImprimante extends JFrame {
	
	/**
	 *serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] columnNames = {"SN_I", "Désignation", "Resolution"};
	
	//Fenetre principale
	private JPanel contentPane;
	
	//Tableaux
	private JTable tableConnexionImprimante;
	private DefaultTableModel tableModel;

	//Labels statiques
	private JLabel staticLblTitle;
	private JLabel staticLblSubtitle;
	

	//Labels dynamique
	
	private JButton btnSauvegarder;
	
	
	
	//ScrollPane
	private JScrollPane scrllpaneConnexionImprimante;
	
	//Serveurs
	private Imprimantes imprimantes;
	
	FicheOrdinateur ficheOrdinateur;
	private JLabel staticLblSno;
	private JLabel staticLblDesignation;
	private JLabel staticLblProprietaire;
	private JLabel lblSno;
	private JLabel lblProprietaire;
	private JLabel lblDesignation;
	
	/**
	 * Constructeur de la connexion à une imprimante.
	 * @param imprimantes
	 * 
	 * @param ficheOrdinateur
	 * 
	 */
	public ConnexionImprimante(Imprimantes imprimantes, FicheOrdinateur ficheOrdinateur) {
		this.imprimantes = imprimantes;
		this.ficheOrdinateur = ficheOrdinateur;
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ConnexionImprimante.columnNames);
		
		//Remplissage table
		DecimalFormat f = new DecimalFormat("##0.00");
		
		for (Imprimante imprimante : imprimantes.getItems()) {
			Object[] rowData = new Object[ConnexionImprimante.columnNames.length];
			rowData[0] = imprimante.getSn();
			rowData[1] = imprimante.getDesignation();
			rowData[2] = imprimante.getResolution();
			this.tableModel.addRow(rowData);
		}
		
		this.initComponents();
		
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
		staticLblTitle = new JLabel("Connexions imprimantes");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(127, 0, 359, 32);
		contentPane.add(staticLblTitle);
		
		staticLblSubtitle = new JLabel("Séctionnez une ou plusieurs imprimante(s) puis cliquez sur le bouton \"Connecter\" : ");
		staticLblSubtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLblSubtitle);
		
		staticLblSno = new JLabel("SN_O : ");
		staticLblSno.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSno.setBounds(226, 62, 75, 14);
		contentPane.add(staticLblSno);
		
		staticLblDesignation = new JLabel("Désignation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(198, 90, 103, 14);
		contentPane.add(staticLblDesignation);
		
		staticLblProprietaire = new JLabel("Propriétaire : ");
		staticLblProprietaire.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblProprietaire.setBounds(212, 120, 89, 14);
		contentPane.add(staticLblProprietaire);
		
		//Configuration labels dynamiques
		lblSno = new JLabel();
		lblSno.setText((String) null);
		lblSno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSno.setBounds(307, 61, 115, 16);
		contentPane.add(lblSno);
		
		lblProprietaire = new JLabel();
		lblProprietaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProprietaire.setBounds(307, 119, 125, 16);
		contentPane.add(lblProprietaire);
		
		lblDesignation = new JLabel();
		lblDesignation.setText((String) null);
		lblDesignation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDesignation.setBounds(307, 89, 215, 16);
		contentPane.add(lblDesignation);
		
		//Configuration du scroll pane
		scrllpaneConnexionImprimante = new JScrollPane();
		scrllpaneConnexionImprimante.setBounds(12, 199, 624, 240);
		contentPane.add(scrllpaneConnexionImprimante);
		
		//Configuration du tableau
		tableConnexionImprimante = new JTable();
		tableConnexionImprimante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tableConnexionImprimante.setModel(this.tableModel);
		scrllpaneConnexionImprimante.setViewportView(tableConnexionImprimante);
		
		//Configuration bouton
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(533, 495, 105, 23);
		contentPane.add(btnSauvegarder);

	}
	
	/**
	 * Retourne les imprmantes selecionnés.
	 * @return
	 */
	public Imprimante getSelectedImprimante() {
		int columnSNSIndex = this.tableConnexionImprimante.convertColumnIndexToView(this.tableModel.findColumn(ConnexionImprimante.columnNames[0]));
		int rowIndex = this.tableConnexionImprimante.getSelectedRow();
		return this.imprimantes.findBySN((String)this.tableModel.getValueAt(rowIndex, columnSNSIndex));
	}
	

}