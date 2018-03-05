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
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Imprimante_Fiche extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JSpinner textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Imprimante_Fiche frame = new Imprimante_Fiche();
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
	public Imprimante_Fiche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Modify");
		button.setBounds(505, 11, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Save");
		button_1.setEnabled(false);
		button_1.setBounds(505, 227, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.setBounds(406, 227, 89, 23);
		contentPane.add(button_2);
		
		JLabel lblSni = new JLabel("SN_I");
		lblSni.setBounds(10, 62, 63, 14);
		contentPane.add(lblSni);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(97, 59, 86, 20);
		contentPane.add(textField);
		
		Label label_1 = new Label("Description");
		label_1.setBounds(10, 82, 76, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Resolution");
		label_2.setBounds(10, 110, 76, 22);
		contentPane.add(label_2);
		
		textField_1 = new JSpinner();
		textField_1.setModel(new SpinnerNumberModel(150, 150, 300, 150));
		textField_1.setBounds(97, 109, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(97, 84, 86, 20);
		contentPane.add(textField_2);
		
		Label label_3 = new Label("NbPcConnected");
		label_3.setBounds(10, 135, 81, 22);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(97, 137, 86, 20);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 45, 401, 171);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"SN_O", "Designation", "Employe", "Disconnect"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
	}
}
