package gestionParcInfo.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;

public class ConnexionImprimanteOld extends JFrame{
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
	
	public ConnexionImprimanteOld(ArrayList<Imprimante> imprimanteToShow, Imprimantes imprimantes, FicheOrdinateur ficheOrdinateur) {
		this.imprimantes = imprimantes;
		this.ficheOrdinateur = ficheOrdinateur;
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ConnexionImprimanteOld.columnNames);
		
		//Remplissage table
		DecimalFormat f = new DecimalFormat("##0.00");
		
		for(Imprimante imprimante : imprimanteToShow) {
			Object[] rowData = new Object[ConnexionImprimanteOld.columnNames.length];
			rowData[0] = imprimante.getSn();
			rowData[1] = imprimante.getDesignation();
			rowData[2] = imprimante.getResolution();
			this.tableModel.addRow(rowData);
		}
		
		this.initComponents();
		
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
		staticLblTitle = new JLabel("Connexions serveurs");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(127, 0, 294, 32);
		contentPane.add(staticLblTitle);
		
		staticLblSubtitle = new JLabel("S\u00E9l\u00E9ctionnez un ou plusieurs serveurs puis cliquez sur le bouton \"Connecter\" : ");
		staticLblSubtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLblSubtitle);
		
		
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBounds(533, 495, 105, 23);
		contentPane.add(btnSauvegarder);
		
	
	}
	
	public ArrayList<Imprimante> getSelectedImprimante() {
		ArrayList<Imprimante> result = new ArrayList<>();
		
		int columnSNSIndex = this.tableConnexionImprimante.convertColumnIndexToView(this.tableModel.findColumn(ConnexionImprimanteOld.columnNames[0]));

		for(int rowIndex : this.tableConnexionImprimante.getSelectedRows()) {
			result.add(this.imprimantes.findBySN((String)this.tableModel.getValueAt(rowIndex, columnSNSIndex)));
		}
		
		return result;
	}
	

}
