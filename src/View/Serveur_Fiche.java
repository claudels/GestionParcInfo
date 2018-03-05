package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Serveur_Fiche extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Serveur_Fiche frame = new Serveur_Fiche();
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
	public Serveur_Fiche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Modify");
		button.setBounds(502, 11, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Save");
		button_1.setEnabled(false);
		button_1.setBounds(502, 231, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.setBounds(403, 231, 89, 23);
		contentPane.add(button_2);
		
		JLabel lblSns = new JLabel("SN_S");
		lblSns.setBounds(14, 82, 63, 14);
		contentPane.add(lblSns);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(101, 79, 86, 20);
		contentPane.add(textField);
		
		Label label_1 = new Label("Description");
		label_1.setBounds(14, 102, 76, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Memory");
		label_2.setBounds(14, 130, 76, 22);
		contentPane.add(label_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"3000", "5000", "10000"}));
		spinner.setBounds(101, 129, 86, 20);
		contentPane.add(spinner);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 104, 86, 20);
		contentPane.add(textField_1);
		
		Label label_3 = new Label("NbPcConnected");
		label_3.setBounds(14, 155, 81, 22);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(101, 157, 86, 20);
		contentPane.add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 61, 398, 141);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"SN_O", "Description", "Quota", "Employe", "Disconnect"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, Integer.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
	}
}
