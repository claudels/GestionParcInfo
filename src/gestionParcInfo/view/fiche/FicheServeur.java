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
import javax.swing.SpinnerListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

public class FicheServeur extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FicheServeur frame = new FicheServeur();
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
	public FicheServeur() {
		setTitle("Serveur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setEnabled(false);
		btnSauvegarder.setBounds(688, 366, 105, 23);
		contentPane.add(btnSauvegarder);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(458, 366, 89, 23);
		contentPane.add(btnAnnuler);
		
		JLabel lblSns = new JLabel("Num\u00E9ro de s\u00E9rie :");
		lblSns.setBounds(53, 198, 123, 23);
		contentPane.add(lblSns);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(53, 221, 123, 20);
		contentPane.add(textField);
		
		Label label_2 = new Label("M\u00E9moire : ");
		label_2.setBounds(53, 311, 76, 22);
		contentPane.add(label_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerListModel(new String[] {"3000", "5000", "10000"}));
		spinner.setBounds(53, 333, 123, 20);
		contentPane.add(spinner);
		
		Label label_3 = new Label("Ordinateurs connect\u00E9s : ");
		label_3.setBounds(241, 30, 147, 22);
		contentPane.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 59, 553, 294);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
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
				"SN_O", "Description", "Quota", "Employe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane.setViewportView(table);
		
		JLabel lblServeur = new JLabel("Serveur");
		lblServeur.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblServeur.setBounds(12, 0, 100, 32);
		contentPane.add(lblServeur);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(53, 278, 123, 20);
		contentPane.add(textField_1);
		
		JLabel lblDsignation = new JLabel("D\u00E9signation :");
		lblDsignation.setBounds(53, 254, 123, 23);
		contentPane.add(lblDsignation);
		
		JLabel lblChargesommeQuotas = new JLabel("Charge \r\n(Somme quotas / M\u00E9moire) :");
		lblChargesommeQuotas.setBounds(12, 59, 223, 23);
		contentPane.add(lblChargesommeQuotas);
		
		JLabel label = new JLabel("##");
		label.setFont(new Font("Tahoma", Font.BOLD, 26));
		label.setBounds(69, 88, 47, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		label_1.setBounds(118, 88, 47, 32);
		contentPane.add(label_1);
		
		Label label_4 = new Label("Nombre d'ordinateurs connect\u00E9s : ");
		label_4.setBounds(12, 126, 206, 22);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("#####");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 26));
		label_5.setBounds(12, 154, 192, 32);
		contentPane.add(label_5);
		
		JToggleButton tglbtnModeVisualisation = new JToggleButton("Mode visualisation");
		tglbtnModeVisualisation.setBounds(549, 366, 137, 23);
		contentPane.add(tglbtnModeVisualisation);
		
		JButton btnDconnecter = new JButton("D\u00E9connecter");
		btnDconnecter.setEnabled(false);
		btnDconnecter.setBounds(688, 30, 105, 23);
		contentPane.add(btnDconnecter);
	}
}
