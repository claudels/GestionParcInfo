package gestionParcInfo.view.tab;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Serveurs;

public class ServeurTab extends JPanel implements Observer{
	private static final String[] columnsNames = {"SN_S", "Designation", "Charge"};
	
	//Tableau
	private JScrollPane scrllpaneServeur;
	private DefaultTableModel tableModel;
	private JTable tblServeur;
	
	//Boutons
	private JButton btnAJouter;
	private JButton btnSupprimer;
	
	
	public ServeurTab() {
		super();
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(ServeurTab.columnsNames);
		
		this.initComponents();
	}
	
	private void initComponents() {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrllpaneServeur = new JScrollPane();
		scrllpaneServeur.setBounds(10, 49, 686, 351);
		this.add(scrllpaneServeur);
		
		tblServeur = new JTable();
		tblServeur.setModel(this.tableModel);
		tblServeur.getColumnModel().getColumn(0).setMaxWidth(100);
		tblServeur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrllpaneServeur.setViewportView(tblServeur);
		
		
		btnAJouter = new JButton("Ajouter");
		btnAJouter.setBounds(607, 13, 89, 23);
		this.add(btnAJouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(503, 13, 96, 23);
		this.add(btnSupprimer);
	}
	
	public JButton getBtnAJouter() {
		return btnAJouter;
	}
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	
	
	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Serveurs) {
			Serveurs serveurs = (Serveurs)obs;
			this.tableModel = new DefaultTableModel();
			this.tableModel.setColumnIdentifiers(ServeurTab.columnsNames);
			
			DecimalFormat f = new DecimalFormat("##0.00");
			
			for(Serveur serveur : serveurs.getItems()) {
				Object[] rawData = new Object[ServeurTab.columnsNames.length];
				rawData[0] = serveur.getSn();
				rawData[1] = serveur.getDesignation();
				rawData[2] = f.format(serveurs.calculerChargeServeur(serveur)*100) + "%";
				
				this.tableModel.addRow(rawData);
			}
			
			this.tblServeur.setModel(this.tableModel);
			this.tableModel.fireTableDataChanged();
		}
	}
}