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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AssignerOrdinateur extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignerOrdinateur frame = new AssignerOrdinateur();
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
	public AssignerOrdinateur() {
		setTitle("Assigner ordinateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricule = new JLabel("Matricule : ");
		lblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricule.setBounds(10, 62, 75, 14);
		contentPane.add(lblMatricule);
		
		JButton button = new JButton("Annuler");
		button.setBounds(325, 517, 89, 23);
		contentPane.add(button);
		
		JButton btnAssigner = new JButton("Assigner");
		btnAssigner.setBounds(426, 517, 105, 23);
		contentPane.add(btnAssigner);
		
		JLabel lblEmploy = new JLabel("Assigner un ordinateur");
		lblEmploy.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEmploy.setBounds(108, 0, 351, 32);
		contentPane.add(lblEmploy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 156, 519, 345);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"SN_O", "D\u00E9signation", "RAM", "CPU"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		Label label = new Label("S\u00E9l\u00E9ctionnez un ordinateur et cliquez sur le bouton \"Assigner\" : ");
		label.setBounds(10, 128, 377, 22);
		contentPane.add(label);
		
		JLabel label_4 = new JLabel("###########");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(92, 61, 129, 16);
		contentPane.add(label_4);
		
		JLabel lblEmail = new JLabel("E-mail : ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 90, 75, 14);
		contentPane.add(lblEmail);
		
		JLabel label_2 = new JLabel("############################");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(92, 89, 215, 16);
		contentPane.add(label_2);
		
		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(305, 61, 75, 14);
		contentPane.add(lblNom);
		
		JLabel label_5 = new JLabel("###########");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBounds(387, 60, 144, 16);
		contentPane.add(label_5);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom : ");
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setBounds(305, 89, 75, 14);
		contentPane.add(lblPrnom);
		
		JLabel label_7 = new JLabel("###########");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(387, 88, 144, 16);
		contentPane.add(label_7);
	}

}
