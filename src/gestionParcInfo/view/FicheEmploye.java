package gestionParcInfo.view;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FicheEmploye extends JFrame {

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
					FicheEmploye frame = new FicheEmploye();
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
	public FicheEmploye() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Modify");
		button.setBounds(335, 11, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Save");
		button_1.setEnabled(false);
		button_1.setBounds(335, 227, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.setBounds(236, 227, 89, 23);
		contentPane.add(button_2);
		
		JLabel lblMatricule = new JLabel("Matricule");
		lblMatricule.setBounds(10, 62, 63, 14);
		contentPane.add(lblMatricule);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(97, 59, 86, 20);
		contentPane.add(textField);
		
		Label label_1 = new Label("Name");
		label_1.setBounds(10, 82, 76, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(10, 110, 76, 22);
		contentPane.add(label_2);
		
		JTextField spinner = new JTextField();
		spinner.setBounds(97, 109, 86, 20);
		contentPane.add(spinner);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(97, 84, 86, 20);
		contentPane.add(textField_1);
		
		Label label_3 = new Label("E-Mail");
		label_3.setBounds(10, 135, 81, 22);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(97, 137, 86, 20);
		contentPane.add(textField_2);
		
		Label label = new Label("Own");
		label.setBounds(10, 178, 81, 22);
		contentPane.add(label);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
			},
			new String[] {
				""
			}
		));
		table.setTableHeader(null);
		table.setBounds(97, 168, 86, 32);
		contentPane.add(table);
	}

}
