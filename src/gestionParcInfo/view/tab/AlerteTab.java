package gestionParcInfo.view.tab;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AlerteTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable TBL_alerte;
	
	
	public AlerteTab() {
		super();
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 49, 686, 351);
		this.add(scrollPane_4);
		
		TBL_alerte= new JTable();
		TBL_alerte.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, ""},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Code", "Message", "Matricule"
			}
		));
		scrollPane_4.setViewportView(TBL_alerte);
		
		JButton button_3 = new JButton("Supprimer");
		button_3.setBounds(603, 13, 93, 23);
		this.add(button_3);
	}
}
