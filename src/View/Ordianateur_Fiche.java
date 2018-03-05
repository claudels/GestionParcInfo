package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.Label;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Ordianateur_Fiche extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ordianateur_Fiche frame = new Ordianateur_Fiche();
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
	public Ordianateur_Fiche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(335, 11, 89, 23);
		contentPane.add(btnModify);
		
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setBounds(335, 244, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(236, 244, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblSno = new JLabel("SN_O");
		lblSno.setBounds(10, 62, 63, 14);
		contentPane.add(lblSno);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(97, 59, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Label label_1 = new Label("DateAttribution");
		label_1.setBounds(10, 82, 76, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("DateRestitution");
		label_2.setBounds(10, 110, 76, 22);
		contentPane.add(label_2);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(97, 109, 86, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(97, 84, 86, 20);
		contentPane.add(textField_7);
		
		Label label_4 = new Label("ToChange");
		label_4.setBounds(10, 135, 76, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("ToReturn");
		label_5.setBounds(10, 163, 76, 22);
		contentPane.add(label_5);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(236, 11, 89, 23);
		contentPane.add(btnReturn);
		
		JLabel label = new JLabel("Designation");
		label.setBounds(236, 65, 63, 14);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(0);
		textField_1.setBounds(323, 62, 86, 20);
		contentPane.add(textField_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(4, 4, 8, 4));
		spinner.setBounds(323, 113, 86, 20);
		contentPane.add(spinner);
		
		JLabel label_6 = new JLabel("RAM");
		label_6.setBounds(236, 116, 63, 14);
		contentPane.add(label_6);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(4096, 4096, 8192, 4096));
		spinner_1.setBounds(323, 138, 86, 20);
		contentPane.add(spinner_1);
		
		JLabel label_7 = new JLabel("CPU");
		label_7.setBounds(236, 141, 63, 14);
		contentPane.add(label_7);
		
		Label label_8 = new Label("SN_I");
		label_8.setBounds(236, 161, 62, 22);
		contentPane.add(label_8);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(323, 163, 86, 20);
		contentPane.add(textField_2);
		
		Label label_3 = new Label("BelongTo");
		label_3.setBounds(236, 84, 76, 22);
		contentPane.add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(323, 86, 86, 20);
		contentPane.add(comboBox);
		
		Label label_9 = new Label("ConnectedTo");
		label_9.setBounds(10, 189, 76, 22);
		contentPane.add(label_9);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(97, 191, 86, 20);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel = new JLabel("yes");
		lblNewLabel.setBounds(97, 143, 46, 14);
		/*int dateattrib=0,daterestitution=1;
		if(daterestitution!= 1)
			lblNewLabel.setText("No");
		else
			lblNewLabel.setText("Yes");
*/
		contentPane.add(lblNewLabel);
		
		JLabel label_10 = new JLabel("Yes");
		label_10.setBounds(97, 166, 46, 14);
		/*if(dateattrib!= 1)
			lblNewLabel.setText("No");
		else
			lblNewLabel.setText("Yes");
*/
	
		contentPane.add(label_10);
		
	
	}
}
