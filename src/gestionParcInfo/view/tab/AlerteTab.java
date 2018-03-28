package gestionParcInfo.view.tab;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.model.Employes;

public class AlerteTab extends JPanel implements Observer {

	private static final String[] columnsNames = {"Code", "Message", "Matricule"};
	
	//Modèle table
	private DefaultTableModel tableModel;
	
	//Boutons
	private JButton btnSupprimer;
	
	//Tableaux
	private JScrollPane scrllpaneAlertes;
	private JTable tblAlerte;
	
	private static final long serialVersionUID = 1L;
	
	
	//TODO: FLO : Remanier tous le pour qu'il soit propre et rendre la vue Observatrice du modèle
	//TODO: FLO : Ne pas oublier de rendre accessible les bouton pour que le controleur récupère les événement (voir les autres vue)
	public AlerteTab() {
		super();
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(AlerteTab.columnsNames);
		
		initComponents();

	}
	private void initComponents() {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrllpaneAlertes = new JScrollPane();
		scrllpaneAlertes.setBounds(10, 49, 686, 351);
		this.add(scrllpaneAlertes);
		
		tblAlerte = new JTable();
		tblAlerte.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblAlerte.setFillsViewportHeight(true);
		tblAlerte.setModel(this.tableModel);
		
		tblAlerte.getColumnModel().getColumn(0).setPreferredWidth(45);
		tblAlerte.getColumnModel().getColumn(1).setPreferredWidth(185);
		tblAlerte.getColumnModel().getColumn(2).setPreferredWidth(62);
		scrllpaneAlertes.setViewportView(tblAlerte);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(603, 13, 93, 23);
		this.add(btnSupprimer);
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Alertes) {
			Alertes alertes = (Alertes)obs;
			
			this.tableModel = new DefaultTableModel();
			this.tableModel.setColumnIdentifiers(AlerteTab.columnsNames);
			
			for(Alerte alerte : alertes.getItems()) {
				String matricule = null;
				
				if(alerte.getEmploye() != null)
					matricule = alerte.getEmploye().getMatricule();
				
				Object rawData[] = new Object[AlerteTab.columnsNames.length];
				rawData[0] = alerte.getId();
				rawData[1] = alerte.getMessage();
				rawData[2] = matricule;
				
				
				this.tableModel.addRow(rawData);
			}
			
			this.tblAlerte.setModel(this.tableModel);
			this.tableModel.fireTableDataChanged();
		}
		
	}
}
