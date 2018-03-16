package gestionParcInfo.view.tab;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeTab extends JPanel{
	private JTable TBL_employe;
	public EmployeTab() {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 49, 686, 351);
		this.add(scrollPane_3);
		
		TBL_employe = new JTable();
		TBL_employe.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Matricule", "Nom", "Pr\u00E9nom", "Nombre de PCs", "PC \u00E0 retourner", "PC \u00E0 changer"
			}
		));
		scrollPane_3.setViewportView(TBL_employe);
		
		JButton btnAjouter_3 = new JButton("Ajouter");
		btnAjouter_3.setBounds(506, 13, 89, 23);
		this.add(btnAjouter_3);
		
		JButton button_2 = new JButton("Supprimer");
		button_2.setBounds(401, 13, 93, 23);
		this.add(button_2);
		
		JButton btnAlerter = new JButton("Alerter");
		btnAlerter.setBounds(607, 13, 89, 23);
		this.add(btnAlerter);
	}
	
}
