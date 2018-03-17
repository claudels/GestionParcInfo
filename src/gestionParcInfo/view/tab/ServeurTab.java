package gestionParcInfo.view.tab;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ServeurTab extends JPanel implements Observer{
	private JTable TBL_serveur;
	public ServeurTab() {
	
		super();
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane SCRLPANE_serveur = new JScrollPane();
		SCRLPANE_serveur.setBounds(10, 49, 686, 351);
		this.add(SCRLPANE_serveur);
		
		TBL_serveur = new JTable();
		TBL_serveur.setModel(new DefaultTableModel(
			new Object[][] {
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
				"SN_S", "Designation", "Charge"
			}
		));
		TBL_serveur.getColumnModel().getColumn(0).setMaxWidth(100);
		SCRLPANE_serveur.setViewportView(TBL_serveur);
		
		
		JButton BTN_ajouterSer = new JButton("Ajouter");
		BTN_ajouterSer.setBounds(607, 13, 89, 23);
		this.add(BTN_ajouterSer);
		
		JButton BTN_supprimerSer = new JButton("Supprimer");
		BTN_supprimerSer.setBounds(503, 13, 93, 23);
		this.add(BTN_supprimerSer);
}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}