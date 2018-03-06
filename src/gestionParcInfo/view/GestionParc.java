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
	private JTable table1;
	private JTable table2;
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Ordinateurs", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 686, 351);
		panel.add(scrollPane);
		
		table1 = new JTable();
		table1.setCellSelectionEnabled(true);
		table1.setColumnSelectionAllowed(true);
		table1.setModel(new DefaultTableModel(
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
		table1.getColumnModel().getColumn(0).setPreferredWidth(45);
		table1.getColumnModel().getColumn(1).setPreferredWidth(185);
		table1.getColumnModel().getColumn(2).setPreferredWidth(62);
		table1.getColumnModel().getColumn(3).setPreferredWidth(58);
		table1.getColumnModel().getColumn(4).setPreferredWidth(45);
		scrollPane.setViewportView(table1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(607, 13, 89, 23);
		panel.add(btnAjouter);
		
		JButton btnRetourner = new JButton("Retourner");
		btnRetourner.setBounds(506, 13, 89, 23);
		panel.add(btnRetourner);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(401, 13, 93, 23);
		panel.add(btnSupprimer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Imprimantes", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 49, 686, 351);
		panel_1.add(scrollPane_1);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
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
		table2.getColumnModel().getColumn(0).setPreferredWidth(63);
		table2.getColumnModel().getColumn(0).setMinWidth(5);
		table2.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane_1.setViewportView(table2);
		JButton btnAjouter_1 = new JButton("Ajouter");
		btnAjouter_1.setBounds(607, 13, 89, 23);
		panel_1.add(btnAjouter_1);
		
		JButton button = new JButton("Supprimer");
		button.setBounds(502, 13, 93, 23);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Serveurs", null, panel_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 49, 686, 351);
		panel_2.add(scrollPane_2);
		
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
		scrollPane_2.setViewportView(table3);
		
		
		JButton btnAjouter_2 = new JButton("Ajouter");
		btnAjouter_2.setBounds(607, 13, 89, 23);
		panel_2.add(btnAjouter_2);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.setBounds(503, 13, 93, 23);
		panel_2.add(button_1);
		
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
