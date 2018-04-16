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

import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Ordinateurs;

public class OrdinateurTab extends JPanel implements Observer{
	private static final String[] columnsNames = {"SN_O", "Designation", "Employ\u00E9", "A changer", "A retourner"};
	
	//Modèle table
	DefaultTableModel tableModel;
	
	//Boutons
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnRetourner;
	
	//Tableaux
	private JScrollPane scrllpaneOrdinateur;
	private JTable tableOrdinateur;
	
	public OrdinateurTab() {
		super();
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(OrdinateurTab.columnsNames);
		
		initComponents();
		
	}
	
	private void initComponents() {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrllpaneOrdinateur = new JScrollPane();
		scrllpaneOrdinateur.setBounds(10, 45, 686, 351);
		this.add(scrllpaneOrdinateur);
		
		tableOrdinateur = new JTable();
		tableOrdinateur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableOrdinateur.setFillsViewportHeight(true);
		tableOrdinateur.setModel(this.tableModel);
		
		tableOrdinateur.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableOrdinateur.getColumnModel().getColumn(1).setPreferredWidth(185);
		tableOrdinateur.getColumnModel().getColumn(2).setPreferredWidth(62);
		tableOrdinateur.getColumnModel().getColumn(3).setPreferredWidth(58);
		tableOrdinateur.getColumnModel().getColumn(4).setPreferredWidth(45);
		scrllpaneOrdinateur.setViewportView(tableOrdinateur);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(607, 13, 89, 23);
		this.add(btnAjouter);
		
		btnRetourner = new JButton("Retourner");
		btnRetourner.setBounds(506, 13, 96, 23);
		this.add(btnRetourner);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(401, 13, 96, 23);
		this.add(btnSupprimer);
	}
	
	public JTable getTableOrdinateur() {
		return tableOrdinateur;
	}
	
	public JButton getBtnAjouter() {
		return btnAjouter;
	}

	public JButton getBtnRetourner() {
		return btnRetourner;
	}
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	
	public ArrayList<String> getSNsOrdinateursSelected() {
		ArrayList<String> serialNumbers = new ArrayList<>();
		
		int columnIndex = this.tableOrdinateur.convertColumnIndexToView(this.tableModel.findColumn(OrdinateurTab.columnsNames[0]));
		
		for(int index : this.tableOrdinateur.getSelectedRows()) {
			serialNumbers.add((String)this.tableOrdinateur.getValueAt(index, columnIndex));
		}
		
		return serialNumbers;
	}
	
	public String getSNOrdinateurClicked() {
		int columnIndex = this.tableOrdinateur.convertColumnIndexToView(this.tableModel.findColumn(OrdinateurTab.columnsNames[0]));
		return (String)this.tableOrdinateur.getValueAt(this.tableOrdinateur.getSelectedRow(), columnIndex);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Ordinateurs) {
			Ordinateurs ordinateurs = (Ordinateurs)obs;
			this.tableModel = new DefaultTableModel(){
				 @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};
			this.tableModel.setColumnIdentifiers(OrdinateurTab.columnsNames);
			
			for(Ordinateur ordinateur : ordinateurs.getItems()) {
				String matricule = null;
				
				if(ordinateur.getProprietaire() != null)
					matricule = ordinateur.getProprietaire().getMatricule();
				
				Object[] rawData = new Object[OrdinateurTab.columnsNames.length];
				rawData[0] = ordinateur.getSn();
				rawData[1] = ordinateur.getDesignation();
				rawData[2] = matricule;
				rawData[3] = ordinateurs.ordinateurMustBeChanged(ordinateur);
				rawData[4] = ordinateurs.ordinateurMustBeReturned(ordinateur);
				this.tableModel.addRow(rawData);
			}
			
			this.tableOrdinateur.setModel(this.tableModel);
			this.tableModel.fireTableDataChanged();
		}
	}
}
