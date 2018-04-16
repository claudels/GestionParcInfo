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

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.view.ConnexionImprimante;
import gestionParcInfo.view.ConnexionServeur;

public class EmployeTab extends JPanel implements Observer{
	private static final String[] columnsNames = {"Matricule", "Nom", "Pr\u00E9nom", "Nombre de PCs", "PCs \u00E0 retourner"};

	//Boutons
	JButton btnAjouter, btnSupprimer, btnAlerter;
	
	//Tableau
	private JTable tblEmploye;
	private DefaultTableModel tableModel;
	private JScrollPane scrllpaneEmployes;
	private Employes employes;
	
	public EmployeTab(Employes employes) {
		super();
		this.employes = employes;
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(EmployeTab.columnsNames);
		
		initComponents();
	}
	
	public JButton getBtnAjouter() {
		return btnAjouter;
	}
	
	
	public JButton getBtnAlerter() {
		return btnAlerter;
	}
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	

	public ArrayList<Employe> getSelectedEmploye() {
		ArrayList<Employe> result = new ArrayList<>();
		
		int columnSNSIndex = this.tblEmploye.convertColumnIndexToView(this.tableModel.findColumn(EmployeTab.columnsNames[0]));

		for(int rowIndex : this.tblEmploye.getSelectedRows()) {
			result.add(this.employes.findByMatricule((String)this.tableModel.getValueAt(rowIndex, columnSNSIndex)));
		}
		
		return result;
	}
	
	private void initComponents() {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrllpaneEmployes = new JScrollPane();
		scrllpaneEmployes.setBounds(10, 49, 686, 351);
		this.add(scrllpaneEmployes);
		
		tblEmploye = new JTable();
		tblEmploye.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblEmploye.setFillsViewportHeight(true);
		tblEmploye.setModel(this.tableModel);
		scrllpaneEmployes.setViewportView(tblEmploye);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(506, 13, 89, 23);
		this.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(401, 13, 96, 23);
		this.add(btnSupprimer);
		
		btnAlerter = new JButton("Alerter");
		btnAlerter.setBounds(607, 13, 89, 23);
		this.add(btnAlerter);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Employes) {
			Employes employes = (Employes)obs;
			this.tableModel = new DefaultTableModel() {
				   public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				   }
			};
			this.tableModel.setColumnIdentifiers(EmployeTab.columnsNames);
			
		
			for(Employe employe : employes.getItems()) {
				Object rawData[] = new Object[EmployeTab.columnsNames.length];
				
				rawData[0] = employe.getMatricule();
				rawData[1] = employe.getNom();
				rawData[2] = employe.getPrenom();
				rawData[3] = employes.getNbOrdisOfEmploye(employe);
				rawData[4] = employes.getNbOrdisAChangerOfEmploye(employe);
				
				this.tableModel.addRow(rawData);
			}
			
			this.tblEmploye.setModel(this.tableModel);
			this.tableModel.fireTableDataChanged();
		}
	}
	
	public ArrayList<String> getMatEmployeSelected() {
		ArrayList<String> Matricule = new ArrayList<>();
		
		int columnIndex = this.tblEmploye.convertColumnIndexToView(this.tableModel.findColumn(EmployeTab.columnsNames[0]));
		
		for(int index : this.tblEmploye.getSelectedRows()) {
			Matricule.add((String)this.tblEmploye.getValueAt(index, columnIndex));
		}
		
		return Matricule;
}
	public String getMatriculeEmployeClicked() {
		int columnIndex = this.tblEmploye.convertColumnIndexToView(this.tableModel.findColumn(EmployeTab.columnsNames[0]));
		return (String)this.tblEmploye.getValueAt(this.tblEmploye.getSelectedRow(), columnIndex);
	}
public JTable getTableEmploye() {
		
		return tblEmploye;
	}
}
