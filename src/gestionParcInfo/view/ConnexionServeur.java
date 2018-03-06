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
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;

public class ConnexionServeur extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionServeur frame = new ConnexionServeur();
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
	public ConnexionServeur() {
		setTitle("Employ\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToggleButton toggleButton = new JToggleButton("Mode visualisation");
		toggleButton.setBounds(394, 495, 137, 23);
		contentPane.add(toggleButton);
		
		JButton button = new JButton("Annuler");
		button.setBounds(303, 495, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Sauvegarder");
		button_1.setEnabled(false);
		button_1.setBounds(533, 495, 105, 23);
		contentPane.add(button_1);
		
		JLabel lblEmploy = new JLabel("Connexions serveurs");
		lblEmploy.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEmploy.setBounds(127, 0, 294, 32);
		contentPane.add(lblEmploy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 199, 624, 240);
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
			},
			new String[] {
				"SN_S", "D\u00E9signation", "PCs connect\u00E9s", "Charge", "M\u00E9moire disponible"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		scrollPane.setViewportView(table);
		
		Label label = new Label("S\u00E9l\u00E9ctionnez un ou plusieurs serveurs puis cliquez sur le bouton \"Connecter\" : ");
		label.setBounds(10, 171, 487, 22);
		contentPane.add(label);
		
		JLabel lblSno = new JLabel("SN_O : ");
		lblSno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSno.setBounds(125, 66, 75, 14);
		contentPane.add(lblSno);
		
		JLabel label_2 = new JLabel("###########");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(206, 65, 115, 16);
		contentPane.add(label_2);
		
		JLabel lblDsignation = new JLabel("D\u00E9signation : ");
		lblDsignation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDsignation.setBounds(97, 94, 103, 14);
		contentPane.add(lblDsignation);
		
		JLabel label_4 = new JLabel("############################");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(206, 93, 215, 16);
		contentPane.add(label_4);
		
		JLabel lblPropritaire = new JLabel("Propri\u00E9taire : ");
		lblPropritaire.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPropritaire.setBounds(111, 124, 89, 14);
		contentPane.add(lblPropritaire);
		
		JLabel label_6 = new JLabel("###########");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(206, 123, 125, 16);
		contentPane.add(label_6);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(100, null, 1000000, 50));
		spinner.setBounds(466, 448, 137, 22);
		contentPane.add(spinner);
		
		JLabel lblQuotas = new JLabel("Quotas : ");
		lblQuotas.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuotas.setBounds(409, 452, 69, 14);
		contentPane.add(lblQuotas);
		
		JLabel lblMo = new JLabel("MO");
		lblMo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMo.setBounds(615, 452, 19, 14);
		contentPane.add(lblMo);
	}
}
