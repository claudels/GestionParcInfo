package gestionParcInfo.view.tab;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ImprimanteTab extends JPanel {
	private JTable TBL_imprimante;
	
	public ImprimanteTab() {
		super();
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane SCRLPANE_imprimante = new JScrollPane();
		SCRLPANE_imprimante.setBounds(10, 49, 686, 351);
		this.add(SCRLPANE_imprimante);
		
		TBL_imprimante = new JTable();
		TBL_imprimante.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"SN_I", "Designation", "Ordinateurs connect\u00E9s"
			}
		));
		TBL_imprimante.getColumnModel().getColumn(0).setPreferredWidth(63);
		TBL_imprimante.getColumnModel().getColumn(0).setMinWidth(5);
		TBL_imprimante.getColumnModel().getColumn(0).setMaxWidth(100);
		SCRLPANE_imprimante.setViewportView(TBL_imprimante);
		JButton BTN_ajouterImp = new JButton("Ajouter");
		BTN_ajouterImp.setBounds(607, 13, 89, 23);
		this.add(BTN_ajouterImp);
		
		JButton BTN_supprimerImp = new JButton("Supprimer");
		BTN_supprimerImp.setBounds(502, 13, 93, 23);
		this.add(BTN_supprimerImp);
	}
	}
