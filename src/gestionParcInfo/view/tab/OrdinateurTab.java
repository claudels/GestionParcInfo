package gestionParcInfo.view.tab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OrdinateurTab extends JPanel{
	private JPanel contentPane;
	private JTable TBL_ordinateur;
	private JTable TBL_imprimante;
	

	
	/*JPanel PNL_Ordinateurs = new JPanel();
	PNL_Ordinateurs.this.setLayout(null);
	PNL_Ordinateurs.setBorder(new EmptyBorder(5, 5, 5, 5));*/
	
	public OrdinateurTab() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane SCRLPANE_ordinateur = new JScrollPane();
		SCRLPANE_ordinateur.setBounds(10, 45, 686, 351);
		this.add(SCRLPANE_ordinateur);
		
		TBL_ordinateur = new JTable();
		TBL_ordinateur.setCellSelectionEnabled(true);
		TBL_ordinateur.setColumnSelectionAllowed(true);
		TBL_ordinateur.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, Boolean.FALSE, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"SN_O", "Designation", "Employ\u00E9", "A changer", "A retourner"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TBL_ordinateur.getColumnModel().getColumn(0).setPreferredWidth(45);
		TBL_ordinateur.getColumnModel().getColumn(1).setPreferredWidth(185);
		TBL_ordinateur.getColumnModel().getColumn(2).setPreferredWidth(62);
		TBL_ordinateur.getColumnModel().getColumn(3).setPreferredWidth(58);
		TBL_ordinateur.getColumnModel().getColumn(4).setPreferredWidth(45);
		SCRLPANE_ordinateur.setViewportView(TBL_ordinateur);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		JButton BTN_ajouterOrd = new JButton("Ajouter");
		BTN_ajouterOrd.setBounds(607, 13, 89, 23);
		this.add(BTN_ajouterOrd);
		
		JButton BTN_retournerOrd = new JButton("Retourner");
		BTN_retournerOrd.setBounds(506, 13, 89, 23);
		this.add(BTN_retournerOrd);
		
		JButton BTN_supprimerOrd = new JButton("Supprimer");
		BTN_supprimerOrd.setBounds(401, 13, 93, 23);
		this.add(BTN_supprimerOrd);
		
	}
}
