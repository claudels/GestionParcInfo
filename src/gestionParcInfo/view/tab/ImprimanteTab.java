package gestionParcInfo.view.tab;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.model.Imprimantes;

public class ImprimanteTab extends JPanel implements Observer {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] columnsNames = {"SN_I", "Designation", "Ordinateurs connectés"};
	
	//Boutons
	private JButton btnAjouter;
	private JButton btnSupprimer;
	
	//Tableau
	private JTable tblImprimante;
	private DefaultTableModel tableModel;
	private JScrollPane scrllpaneImprimante;
	
	/**
	 * Constructeur de l'onglet imprimante.
	 */
	public ImprimanteTab() {
		super();
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ImprimanteTab.columnsNames);
		
		initComponents();
	}
	
	public JButton getBtnAjouter() {
		return btnAjouter;
	}
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	
	/**
	 * Retourne les numéros de séries des imprimantes selectionnées.
	 * @return ArrayList Liste des uméros de série
	 */
	public ArrayList<String> getSNsImprimanteSelected() {
		ArrayList<String> serialNumbers = new ArrayList<>();
		
		int column = this.tableModel.findColumn(ImprimanteTab.columnsNames[0]);
		int columnIndex = this.tblImprimante.convertColumnIndexToView(column);
		
		for (int index : this.tblImprimante.getSelectedRows()) {
			serialNumbers.add((String)this.tblImprimante.getValueAt(index, columnIndex));
		}
		
		return serialNumbers;
	}
	
	/**
	 * Initialisation des objects composant la fenètre.
	 */
	public void initComponents() {
		//Panel
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Tableau
		scrllpaneImprimante = new JScrollPane();
		scrllpaneImprimante.setBounds(10, 49, 686, 351);
		this.add(scrllpaneImprimante);
		
		tblImprimante = new JTable();
		tblImprimante.setModel(this.tableModel);
		tblImprimante.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblImprimante.setFillsViewportHeight(true);
		
		tblImprimante.getColumnModel().getColumn(0).setPreferredWidth(63);
		tblImprimante.getColumnModel().getColumn(0).setMinWidth(5);
		tblImprimante.getColumnModel().getColumn(0).setMaxWidth(100);
		scrllpaneImprimante.setViewportView(tblImprimante);
		
		//Boutons
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(607, 13, 89, 23);
		this.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(502, 13, 98, 23);
		this.add(btnSupprimer);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Imprimantes) {
			Imprimantes imprimantes = (Imprimantes)obs;
			this.tableModel = new DefaultTableModel() {
				   public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				   }
			};
			this.tableModel.setColumnIdentifiers(ImprimanteTab.columnsNames);
			
			for (Imprimante imprimante : imprimantes.getItems()) {
				Object[] rawData = new Object[ImprimanteTab.columnsNames.length];
				rawData[0] = imprimante.getSn();
				rawData[1] = imprimante.getDesignation();
				rawData[2] = imprimantes.countOrdinateurs(imprimante);
				
				this.tableModel.addRow(rawData);
			}
			
			this.tblImprimante.setModel(this.tableModel);
			this.tableModel.fireTableDataChanged();
		}
	}

	public JTable getTableImprimante() {
		
		return tblImprimante;
	}

	public String getSnImprimanteClicked() {
		int columnIndex = this.tblImprimante.convertColumnIndexToView(this.tableModel.findColumn(ImprimanteTab.columnsNames[0]));
		return (String)this.tblImprimante.getValueAt(this.tblImprimante.getSelectedRow(), columnIndex);
	}
}
