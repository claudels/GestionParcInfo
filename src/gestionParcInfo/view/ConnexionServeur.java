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
	
	//Fenetre principale
	private JPanel contentPane;
	
	//Tableaux
	private JTable tableConnexionServeur;

	//Labels statiques
	private JLabel staticLblTitle;
	private JLabel staticLblSubtitle;
	private JLabel staticLblSNO;
	private JLabel staticLblDesignation, staticLblProprietaire; 
	private JLabel staticLblQuota;

	//Labels dynamique
	private JLabel lblSNO;
	private JLabel lblDesignation;
	private JLabel lblProprietaire;
	private JLabel staticLblUniteQuota;
	
	//Boutons
	private JButton btnAnnuler;
	private JButton btnSauvegarder;
	private JToggleButton tglbtnMode;
	
	//Spinner
	private JSpinner spinnerQuota ;
	
	//ScrollPane
	private JScrollPane scrllpaneConnexionServeur;
	
	public ConnexionServeur() {
		this.initComponents();
	}
	
	private void initComponents() {
		setTitle("Employ\u00E9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configuration des labels statiques
		staticLblTitle = new JLabel("Connexions serveurs");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(127, 0, 294, 32);
		contentPane.add(staticLblTitle);
		
		staticLblSubtitle = new JLabel("S\u00E9l\u00E9ctionnez un ou plusieurs serveurs puis cliquez sur le bouton \"Connecter\" : ");
		staticLblSubtitle.setBounds(10, 171, 487, 22);
		contentPane.add(staticLblSubtitle);
		
		staticLblSNO = new JLabel("SN_O : ");
		staticLblSNO.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSNO.setBounds(125, 66, 75, 14);
		contentPane.add(staticLblSNO);
		
		staticLblDesignation = new JLabel("D\u00E9signation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(97, 94, 103, 14);
		contentPane.add(staticLblDesignation);
		
		staticLblProprietaire = new JLabel("Propri\u00E9taire : ");
		staticLblProprietaire.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblProprietaire.setBounds(111, 124, 89, 14);
		contentPane.add(staticLblProprietaire);
		
		staticLblQuota = new JLabel("Quotas : ");
		staticLblQuota.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblQuota.setBounds(409, 452, 69, 14);
		contentPane.add(staticLblQuota);
		
		//Configuration des labels dynamique
		lblSNO = new JLabel("###########");
		lblSNO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSNO.setBounds(206, 65, 115, 16);
		contentPane.add(lblSNO);
		
		lblDesignation = new JLabel("############################");
		lblDesignation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDesignation.setBounds(206, 93, 215, 16);
		contentPane.add(lblDesignation);
		
		lblProprietaire = new JLabel("###########");
		lblProprietaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProprietaire.setBounds(206, 123, 125, 16);
		contentPane.add(lblProprietaire);
		
		staticLblUniteQuota = new JLabel("MO");
		staticLblUniteQuota.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblUniteQuota.setBounds(615, 452, 19, 14);
		contentPane.add(staticLblUniteQuota);
		
		//Configuration des bouttons toggles
		tglbtnMode = new JToggleButton("Mode visualisation");
		tglbtnMode.setBounds(394, 495, 137, 23);
		contentPane.add(tglbtnMode);
		
		//Configuration des bouttons
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(303, 495, 89, 23);
		contentPane.add(btnAnnuler);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setEnabled(false);
		btnSauvegarder.setBounds(533, 495, 105, 23);
		contentPane.add(btnSauvegarder);
		
		//Configuration des spinner
		spinnerQuota = new JSpinner();
		spinnerQuota.setModel(new SpinnerNumberModel(100, null, 1000000, 50));
		spinnerQuota.setBounds(466, 448, 137, 22);
		contentPane.add(spinnerQuota);
		
		//Configuration du scroll pane
		scrllpaneConnexionServeur = new JScrollPane();
		scrllpaneConnexionServeur.setBounds(12, 199, 624, 240);
		contentPane.add(scrllpaneConnexionServeur);
		
		//Configuration du tableau
		tableConnexionServeur = new JTable();
		tableConnexionServeur.setModel(new DefaultTableModel(
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
		tableConnexionServeur.getColumnModel().getColumn(1).setPreferredWidth(132);
		tableConnexionServeur.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableConnexionServeur.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableConnexionServeur.getColumnModel().getColumn(4).setPreferredWidth(120);
		scrllpaneConnexionServeur.setViewportView(tableConnexionServeur);
	}
}
