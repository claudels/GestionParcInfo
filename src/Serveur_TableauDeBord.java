import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Serveur_TableauDeBord extends JFrame {

	private JPanel contentPane;
	private JTable table3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Serveur_TableauDeBord frame = new Serveur_TableauDeBord();
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
	public Serveur_TableauDeBord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 49, 584, 351);
		contentPane.add(scrollPane_3);
		
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
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex_3) {
				return columnTypes[columnIndex_3];
			}
		});
		table3.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane_3.setViewportView(table3);
		
		JButton button = new JButton("Add");
		button.setBounds(505, 11, 89, 23);
		contentPane.add(button);
	}
}
