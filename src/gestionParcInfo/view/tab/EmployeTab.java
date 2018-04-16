package gestionParcInfo.view.tab;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.model.Employes;

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


/**
 * Onglet de gestion des employés.
 * @author seb
 *
 */
public class EmployeTab extends JPanel implements Observer {

  private static final long serialVersionUID = 1L;

  private static final String[] columnsNames = {"Matricule", "Nom", "Prénom", "Nombre de PCs", "PCs à retourner"};

	//Boutons
	JButton btnAjouter;
	JButton btnSupprimer;
	JButton btnAlerter;
	//Tableau
	private JTable tblEmploye;
	private DefaultTableModel tableModel;
	private JScrollPane scrllpaneEmployes;
	
	/**
	 * Création de l'onglet.
	 */
	public EmployeTab() {

		super();
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
		if (obs instanceof Employes) {
			Employes employes = (Employes)obs;
			this.tableModel = new DefaultTableModel() {
				   public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				   }
			};
			this.tableModel.setColumnIdentifiers(EmployeTab.columnsNames);
			
		
			for (Employe employe : employes.getItems()) {
				Object[] rawData = new Object[EmployeTab.columnsNames.length];
				
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
	
	/**
	 * Retourne les matricules des lignes séléctionnées dans la table.
	 * @return ArrayList
	 */
	public ArrayList<String> getMatriculeEmployeSelected() {
		ArrayList<String> matricule = new ArrayList<>();
		
		int column = this.tableModel.findColumn(EmployeTab.columnsNames[0]);
		int columnIndex = this.tblEmploye.convertColumnIndexToView(column);
		
		for (int index : this.tblEmploye.getSelectedRows()) {
			matricule.add((String)this.tblEmploye.getValueAt(index, columnIndex));
		}
		
		return matricule;
	}
	
	/**
	 * Retourne le matricule de la ligne que l'employé à séléctionnée.
	 * @return String
	 */
	public String getMatriculeEmployeClicked() {
	  int column = this.tableModel.findColumn(EmployeTab.columnsNames[0]);
		int columnIndex = this.tblEmploye.convertColumnIndexToView(column);
		return (String)this.tblEmploye.getValueAt(this.tblEmploye.getSelectedRow(), columnIndex);
	}
	
	public JTable getTableEmploye() {
		return tblEmploye;
	}
}
