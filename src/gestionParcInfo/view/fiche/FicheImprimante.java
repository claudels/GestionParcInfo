package gestionParcInfo.view.fiche;
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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheImprimante extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JSpinner textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FicheImprimante frame = new FicheImprimante();
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
	public FicheImprimante() {
		setTitle("Imprimante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSni = new JLabel("SN_I : ");
		lblSni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSni.setBounds(22, 62, 63, 14);
		contentPane.add(lblSni);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(97, 59, 143, 20);
		contentPane.add(textField);
		
		Label label_1 = new Label("D\u00E9signation : ");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(-9, 82, 100, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("R\u00E9solution : ");
		label_2.setBounds(22, 108, 76, 22);
		contentPane.add(label_2);
		
		textField_1 = new JSpinner();
		textField_1.setEnabled(false);
		textField_1.setModel(new SpinnerNumberModel(150, 150, 300, 150));
		textField_1.setBounds(97, 110, 86, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(97, 84, 143, 20);
		contentPane.add(textField_2);
		
		Label label_3 = new Label("Nombre de PCs connect\u00E9s : ");
		label_3.setBounds(260, 56, 173, 22);
		contentPane.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 457, 349);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
				"SN_O", "Designation", "Employ\u00E9"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton button_3 = new JButton("Sauvegarder");
		button_3.setEnabled(false);
		button_3.setBounds(369, 566, 105, 23);
		contentPane.add(button_3);
		
		JToggleButton toggleButton = new JToggleButton("Mode visualisation");
		toggleButton.setBounds(230, 566, 137, 23);
		contentPane.add(toggleButton);
		
		JButton button_4 = new JButton("Annuler");
		button_4.setBounds(139, 566, 89, 23);
		contentPane.add(button_4);
		
		JButton button_2 = new JButton("D\u00E9connecter");
		button_2.setEnabled(false);
		button_2.setBounds(10, 524, 111, 25);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("#####");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(210, 84, 124, 20);
		contentPane.add(label);
		
		JLabel lblOrdinateurs = new JLabel("Ordinateurs");
		lblOrdinateurs.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdinateurs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrdinateurs.setBounds(340, 84, 124, 20);
		contentPane.add(lblOrdinateurs);
		
		Label label_4 = new Label("Ordinateurs connect\u00E9s : ");
		label_4.setBounds(10, 142, 173, 22);
		contentPane.add(label_4);
		
		JLabel lblImprimante = new JLabel("Imprimante");
		lblImprimante.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblImprimante.setBounds(163, 13, 185, 32);
		contentPane.add(lblImprimante);
	}
}
