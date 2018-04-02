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

import gestionParcInfo.view.fiche.Fiche.State;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

public class FicheServeur extends Fiche {
	
	
	//Champs texte
	private JTextField tfNumeroDeSerie, tfDesignation;
	
	//Tableaux
	private JTable tblServeur;
	
	//labels statiques
	private JLabel staticLblUniteMemoire, staticLblNumeroDeSerie, staticLblOrdinateursConnectés, staticLblTitle, staticLbldesignation, staticLblCharge, staticLblPourcent, staticLblNbOrdinateursConnectés;
	
	//labels dynamique
	private JLabel lblBnOrdinateursConnectés, lblMemoire,lblCharge;
	
	//Bouttons
	private JButton btnDeconnecter; 
	
	
	//Spinner
	private JSpinner spinMemoire;
	
	//ScrollPane
	private JScrollPane scrlpaneOrdinateursConnectés;
	
	
	/**
	 * Create the frame.
	 */
	public FicheServeur(Fiche.State initialState) {
		super(initialState);
		initComponents();
		this.changeState(initialState);
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		this.btnDeconnecter.setVisible(newState != Fiche.State.CREATION);
		
		
		//Interdit à la visualisation
		this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
		this.tfNumeroDeSerie.setEnabled(newState != Fiche.State.VISUALISATION);
		this.spinMemoire.setEnabled(newState != Fiche.State.VISUALISATION);
		
		
		
		//Autorisé pour création
		this.tfNumeroDeSerie.setEditable(newState == Fiche.State.CREATION);
		
	}
	public void initComponents() {
		setTitle("Serveur");
		setBounds(100, 100, 830, 446);
	
		
		//Configuration des labels statiques
		staticLblNumeroDeSerie = new JLabel("Num\u00E9ro de s\u00E9rie :");
		staticLblNumeroDeSerie.setBounds(53, 198, 123, 23);
		contentPane.add(staticLblNumeroDeSerie);
		
		staticLblOrdinateursConnectés = new JLabel("Ordinateurs connect\u00E9s : ");
		staticLblOrdinateursConnectés.setBounds(241, 30, 147, 22);
		contentPane.add(staticLblOrdinateursConnectés);
		
		staticLblTitle = new JLabel("Serveur");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(12, 0, 100, 32);
		contentPane.add(staticLblTitle);
		
		staticLbldesignation = new JLabel("D\u00E9signation :");
		staticLbldesignation.setBounds(53, 254, 123, 23);
		contentPane.add(staticLbldesignation);
		
		staticLblCharge = new JLabel("Charge \r\n(Somme quotas / M\u00E9moire) :");
		staticLblCharge.setBounds(12, 59, 223, 23);
		contentPane.add(staticLblCharge);
		
		staticLblPourcent = new JLabel("%");
		staticLblPourcent.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblPourcent.setBounds(118, 88, 47, 32);
		contentPane.add(staticLblPourcent);
		
		staticLblNbOrdinateursConnectés = new JLabel("Nombre d'ordinateurs connect\u00E9s : ");
		staticLblNbOrdinateursConnectés.setBounds(12, 126, 206, 22);
		contentPane.add(staticLblNbOrdinateursConnectés);
		
		staticLblUniteMemoire = new JLabel("Go");
		staticLblUniteMemoire.setBounds(180, 333, 24, 16);
		contentPane.add(staticLblUniteMemoire);
		
		//Configuration des label dynamique
		lblBnOrdinateursConnectés = new JLabel("#####");
		lblBnOrdinateursConnectés.setHorizontalAlignment(SwingConstants.CENTER);
		lblBnOrdinateursConnectés.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBnOrdinateursConnectés.setBounds(12, 154, 192, 32);
		contentPane.add(lblBnOrdinateursConnectés);
		
		lblMemoire = new JLabel("M\u00E9moire : ");
		lblMemoire.setBounds(53, 311, 76, 22);
		contentPane.add(lblMemoire);
		
		lblCharge = new JLabel("##");
		lblCharge.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCharge.setBounds(69, 88, 47, 32);
		contentPane.add(lblCharge);
		
		//Configuration des bouttons	
	
		
		btnDeconnecter = new JButton("D\u00E9connecter");
		btnDeconnecter.setEnabled(true);
		btnDeconnecter.setBounds(688, 30, 105, 23);
		contentPane.add(btnDeconnecter);
		
		btnAnnuler.setBounds(458, 366, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(549, 366, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(688, 366, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		//Configuration des champs de texte
		tfNumeroDeSerie = new JTextField();
		tfNumeroDeSerie.setEditable(false);
		tfNumeroDeSerie.setColumns(10);
		tfNumeroDeSerie.setBounds(53, 221, 123, 20);
		contentPane.add(tfNumeroDeSerie);
		
		tfDesignation = new JTextField();
		tfDesignation.setEditable(false);
		tfDesignation.setColumns(10);
		tfDesignation.setBounds(53, 278, 123, 20);
		contentPane.add(tfDesignation);
		
		//Configuration des spinner
		spinMemoire = new JSpinner();
		spinMemoire.setEnabled(false);
		spinMemoire.setModel(new SpinnerListModel(new String[] {"3000", "5000", "10000"}));
		spinMemoire.setBounds(53, 333, 123, 20);
		contentPane.add(spinMemoire);
		
		//Configuration des ScrollPane
		scrlpaneOrdinateursConnectés = new JScrollPane();
		scrlpaneOrdinateursConnectés.setBounds(240, 59, 553, 294);
		contentPane.add(scrlpaneOrdinateursConnectés);
		
		//Configuration des tableaux
		tblServeur = new JTable();
		tblServeur.setRowSelectionAllowed(false);
		tblServeur.setFillsViewportHeight(true);
		tblServeur.setModel(new DefaultTableModel(
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
				"SN_O", "Description", "Quota (Mo)", "Employe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblServeur.getColumnModel().getColumn(0).setPreferredWidth(150);
		tblServeur.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrlpaneOrdinateursConnectés.setViewportView(tblServeur);
	}
}
