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
		setBounds(100, 100, 649, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 620, 450);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Computer", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 577, 351);
		panel.add(scrollPane);
		
		table1 = new JTable();
		table1.setCellSelectionEnabled(true);
		table1.setColumnSelectionAllowed(true);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, Boolean.FALSE, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, null, null, null},
				{null, null, null, Boolean.TRUE, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"SN_O", "Designation", "Owner", "ToChange", "ToReturn", "Delete"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, Boolean.class, Boolean.class, Object.class
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
		JButton button = new JButton("Add");
		button.setBounds(498, 11, 89, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Printer", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 49, 584, 351);
		panel_1.add(scrollPane_1);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"SN_I", "Designation", "NbComputerConnected", "Delete"
			}
		) {
			Class[] columnTypes1 = new Class[] {
				Long.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex1) {
				return columnTypes1[columnIndex1];
			}
		});
		table2.getColumnModel().getColumn(0).setPreferredWidth(63);
		table2.getColumnModel().getColumn(0).setMinWidth(5);
		table2.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane_1.setViewportView(table2);
		JButton button_1 = new JButton("Add");
		button_1.setBounds(505, 11, 89, 23);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Server", null, panel_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 49, 584, 351);
		panel_2.add(scrollPane_2);
		
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"SN_S", "Designation", "MemoryUsed / MemoryAvailable", "Delete"
			}
		) {
			Class[] columnTypes2 = new Class[] {
				Long.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex_2) {
				return columnTypes2[columnIndex_2];
			}
		});
		table3.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane_2.setViewportView(table3);
		
		
		JButton button_2 = new JButton("Add");
		button_2.setBounds(505, 11, 89, 23);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Employee", null, panel_3, null);
		panel_3.setLayout(null);
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 49, 577, 351);
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
				"Matricule", "Name", "First Name", "NbPcOwned", "PcToReturn", "PcToChange"
			}
		) {
			Class[] columnTypes3 = new Class[] {
				String.class, String.class, String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex_3) {
				return columnTypes3[columnIndex_3];
			}
		});
		scrollPane_3.setViewportView(table4);
		
		JButton button_3 = new JButton("Add");
		button_3.setBounds(498, 15, 89, 23);
		panel_3.add(button_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Warnings", null, panel_4, null);
		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 49, 584, 351);
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
		
		JButton button_4 = new JButton("Add");
		button_4.setBounds(505, 11, 89, 23);
		panel_4.add(button_4);
	}

}
