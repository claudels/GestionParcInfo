package gestionParcInfo.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class GestionParc extends JFrame {

	private JPanel contentPane;
	private JTable TBL_ordinateur;
	private JTable TBL_imprimante;
	private JTable table3;
	private JTable table4;
	private JTable table5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionParc frame = new GestionParc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionParc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 713, 450);
		contentPane.add(tabbedPane);
		
		JPanel PNL_ordinateur = new JPanel();
		PNL_ordinateur.setLayout(null);
		PNL_ordinateur.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Ordinateurs", null, PNL_ordinateur, null);
		
		JScrollPane SCRLPANE_ordinateur = new JScrollPane();
		SCRLPANE_ordinateur.setBounds(10, 45, 686, 351);
		PNL_ordinateur.add(SCRLPANE_ordinateur);
		
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
		PNL_ordinateur.add(BTN_ajouterOrd);
		
		JButton BTN_retournerOrd = new JButton("Retourner");
		BTN_retournerOrd.setBounds(506, 13, 89, 23);
		PNL_ordinateur.add(BTN_retournerOrd);
		
		JButton BTN_supprimerOrd = new JButton("Supprimer");
		BTN_supprimerOrd.setBounds(401, 13, 93, 23);
		PNL_ordinateur.add(BTN_supprimerOrd);
		
		JPanel PNL_imprimante = new JPanel();
		PNL_imprimante.setLayout(null);
		PNL_imprimante.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Imprimantes", null, PNL_imprimante, null);
		
		JScrollPane SCRLPANE_imprimante = new JScrollPane();
		SCRLPANE_imprimante.setBounds(10, 49, 686, 351);
		PNL_imprimante.add(SCRLPANE_imprimante);
		
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
		PNL_imprimante.add(BTN_ajouterImp);
		
		JButton BTN_supprimerImp = new JButton("Supprimer");
		BTN_supprimerImp.setBounds(502, 13, 93, 23);
		PNL_imprimante.add(BTN_supprimerImp);
		
		JPanel PNL_serveur = new JPanel();
		PNL_serveur.setLayout(null);
		PNL_serveur.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Serveurs", null, PNL_serveur, null);
		
		JScrollPane SCRLPANE_serveur = new JScrollPane();
		SCRLPANE_serveur.setBounds(10, 49, 686, 351);
		PNL_serveur.add(SCRLPANE_serveur);
		
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
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
		table3.getColumnModel().getColumn(0).setMaxWidth(100);
		SCRLPANE_serveur.setViewportView(table3);
		
		
		JButton BTN_ajouterSer = new JButton("Ajouter");
		BTN_ajouterSer.setBounds(607, 13, 89, 23);
		PNL_serveur.add(BTN_ajouterSer);
		
		JButton BTN_supprimerSer = new JButton("Supprimer");
		BTN_supprimerSer.setBounds(503, 13, 93, 23);
		PNL_serveur.add(BTN_supprimerSer);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Employ\u00E9s", null, panel_3, null);
		panel_3.setLayout(null);
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 49, 686, 351);
		panel_3.add(scrollPane_3);
		
		table4 = new JTable();
		table4.setModel(new DefaultTableModel(
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
		scrollPane_3.setViewportView(table4);
		
		JButton btnAjouter_3 = new JButton("Ajouter");
		btnAjouter_3.setBounds(506, 13, 89, 23);
		panel_3.add(btnAjouter_3);
		
		JButton button_2 = new JButton("Supprimer");
		button_2.setBounds(401, 13, 93, 23);
		panel_3.add(button_2);
		
		JButton btnAlerter = new JButton("Alerter");
		btnAlerter.setBounds(607, 13, 89, 23);
		panel_3.add(btnAlerter);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Alertes", null, panel_4, null);
		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 49, 686, 351);
		panel_4.add(scrollPane_4);
		
		table5 = new JTable();
		table5.setModel(new DefaultTableModel(
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
		scrollPane_4.setViewportView(table5);
		
		JButton button_3 = new JButton("Supprimer");
		button_3.setBounds(603, 13, 93, 23);
		panel_4.add(button_3);
	}

}
