import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Imprimante_TableauDeBord extends JFrame {

	private JPanel contentPane;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Imprimante_TableauDeBord frame = new Imprimante_TableauDeBord();
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
	public Imprimante_TableauDeBord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 49, 584, 351);
		contentPane.add(scrollPane_1);
		
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
		
		JButton button1 = new JButton("Add");
		button1.setBounds(505, 11, 89, 23);
		contentPane.add(button1);
	}
}
