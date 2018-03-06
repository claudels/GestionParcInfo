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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;

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
		setTitle("Employ\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricule = new JLabel("Matricule : ");
		lblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricule.setBounds(10, 62, 75, 14);
		contentPane.add(lblMatricule);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(97, 59, 157, 20);
		contentPane.add(textField);
		
		Label label_1 = new Label("Nom : ");
		label_1.setAlignment(Label.RIGHT);
		label_1.setBounds(10, 82, 76, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Pr\u00E9nom : ");
		label_2.setAlignment(Label.RIGHT);
		label_2.setBounds(272, 57, 76, 22);
		contentPane.add(label_2);
		
		JTextField spinner = new JTextField();
		spinner.setEditable(false);
		spinner.setBounds(359, 56, 157, 20);
		contentPane.add(spinner);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(97, 84, 157, 20);
		contentPane.add(textField_1);
		
		Label label_3 = new Label("E-Mail : ");
		label_3.setAlignment(Label.RIGHT);
		label_3.setBounds(266, 82, 81, 22);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(359, 84, 157, 20);
		contentPane.add(textField_2);
		
		JToggleButton toggleButton = new JToggleButton("Mode visualisation");
		toggleButton.setBounds(284, 274, 137, 23);
		contentPane.add(toggleButton);
		
		JButton button = new JButton("Annuler");
		button.setBounds(193, 274, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Sauvegarder");
		button_1.setEnabled(false);
		button_1.setBounds(423, 274, 105, 23);
		contentPane.add(button_1);
		
		JLabel lblEmploy = new JLabel("Employ\u00E9");
		lblEmploy.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEmploy.setBounds(206, 0, 185, 32);
		contentPane.add(lblEmploy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 156, 519, 63);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"SN_O", "D\u00E9signation", "A changer", "A retourner", "Temps d'utilisation"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		scrollPane.setViewportView(table);
		
		Label label = new Label("Ordinateurs assign\u00E9s : ");
		label.setBounds(10, 128, 173, 22);
		contentPane.add(label);
		
		JButton btnAssignerUnOrdinateur = new JButton("Assigner un ordinateur");
		btnAssignerUnOrdinateur.setEnabled(false);
		btnAssignerUnOrdinateur.setBounds(10, 224, 198, 25);
		contentPane.add(btnAssignerUnOrdinateur);
	}

}
