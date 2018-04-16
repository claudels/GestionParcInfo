package gestionParcInfo.view;
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

import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.view.fiche.FicheOrdinateur;

public class ConnexionImprimante extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] columnNames = {"SN_I", "D\u00E9signation", "Resolution"};
	
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
	private JLabel staticLblSNO;
	private JLabel staticLblDesignation;
	private JLabel staticLblProprietaire;
	private JLabel lblSNO;
	private JLabel lblProprietaire;
	private JLabel lblDesignation;
	
	public ConnexionImprimante(Imprimantes imprimantes, FicheOrdinateur ficheOrdinateur) {
		this.imprimantes = imprimantes;
		this.ficheOrdinateur = ficheOrdinateur;
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ConnexionImprimante.columnNames);
		
		//Remplissage table
		DecimalFormat f = new DecimalFormat("##0.00");
		
		for(Imprimante imprimante : imprimantes.getItems()) {
			Object[] rowData = new Object[ConnexionImprimante.columnNames.length];
			rowData[0] = imprimante.getSn();
			rowData[1] = imprimante.getDesignation();
			rowData[2] = imprimante.getResolution();
			this.tableModel.addRow(rowData);
		}
		
		this.initComponents();
		
		this.lblSNO.setText(this.ficheOrdinateur.getSn());
		this.lblDesignation.setText(this.ficheOrdinateur.getDesignation());
		if(this.ficheOrdinateur.getProprietaire() != null)
			this.lblProprietaire.setText(this.ficheOrdinateur.getProprietaire().getMatricule());
	}	
	public JButton getBtnSauvegarder() {
		return btnSauvegarder;
	}
	
	private void initComponents() {
		setTitle("Employ\u00E9");
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
		
		staticLblSubtitle = new JLabel("S\u00E9l\u00E9ctionnez une ou plusieurs imprimante(s) puis cliquez sur le bouton \"Connecter\" : ");
		staticLblSubtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLblSubtitle);
		
		staticLblSNO = new JLabel("SN_O : ");
		staticLblSNO.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSNO.setBounds(226, 62, 75, 14);
		contentPane.add(staticLblSNO);
		
		staticLblDesignation = new JLabel("D\u00E9signation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(198, 90, 103, 14);
		contentPane.add(staticLblDesignation);
		
		staticLblProprietaire = new JLabel("Propri\u00E9taire : ");
		staticLblProprietaire.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblProprietaire.setBounds(212, 120, 89, 14);
		contentPane.add(staticLblProprietaire);
		
		//Configuration labels dynamiques
		lblSNO = new JLabel();
		lblSNO.setText((String) null);
		lblSNO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSNO.setBounds(307, 61, 115, 16);
		contentPane.add(lblSNO);
		
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
	
	public Imprimante getSelectedImprimante() {
		int columnSNSIndex = this.tableConnexionImprimante.convertColumnIndexToView(this.tableModel.findColumn(ConnexionImprimante.columnNames[0]));
		int rowIndex = this.tableConnexionImprimante.getSelectedRow();
		return this.imprimantes.findBySN((String)this.tableModel.getValueAt(rowIndex, columnSNSIndex));
	}
	

}