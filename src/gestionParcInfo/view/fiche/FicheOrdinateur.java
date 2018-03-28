package gestionParcInfo.view.fiche;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FicheOrdinateur extends Fiche {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Tableaux
	private JScrollPane scrllpaneImprimante, scrllpaneServeurs;
	private JTable tableImprimante, tblServeurs;
	
	//ComboBox
	private JComboBox<String> cmbboxAssignedTo;

	//Spinners
	private JSpinner spinnerRAM, spinnerCPU;
	
	//TextFields
	private JTextField tfSNO, tfDesignation, tfDateAttribution, tfDateRestitution;
	
	//Labels statiques
	private JLabel staticLblUniteTemps, staticLblTitle, staticLblSNO, staticLblDateAttribution, staticLblDateRestitution, staticLblImprimanteTitle, staticLblServeursTitle, staticLblAChanger, staticLblARetourner, staticLblTempsUtilisation, staticLblAssignedTo, staticLblDesignation, staticLblCPU, staticLblRAM;

	//Labels dynamiques
	private JLabel lblAChanger, lblJoursUtilisation, lblARetourner;
	
	//Boutons
	private JButton btnConnecterImprimante, btnDeconnecterImprimante, btnConnecterServeurs, btnDeconnecterServeurs;
	
	/**
	 * Create the frame.
	 */
	public FicheOrdinateur(Fiche.State initialState) {
		super(initialState);
		initComponents();
		this.changeState(initialState);
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		switch(newState) {
		case CREATION:
			//Interdit à la création
			this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
			this.lblAChanger.setVisible(newState != Fiche.State.CREATION);
			this.lblARetourner.setVisible(newState != Fiche.State.CREATION);
			this.lblJoursUtilisation.setText((newState == Fiche.State.CREATION)?"0":"##");
			this.staticLblAChanger.setVisible(newState != Fiche.State.CREATION);
			this.staticLblARetourner.setVisible(newState != Fiche.State.CREATION);
			
			//Interdit à la visualisation
			this.tfDesignation.setEditable(newState != Fiche.State.VISUALISATION);
			this.cmbboxAssignedTo.setEditable(newState != Fiche.State.VISUALISATION);
			this.spinnerCPU.setEnabled(newState != Fiche.State.VISUALISATION);
			this.spinnerRAM.setEnabled(newState != Fiche.State.VISUALISATION);
			
			//Autorisé pour création
			this.tfSNO.setEditable(newState == Fiche.State.CREATION);
			
			break;
		case MODIFICATION:
			break;
		case VISUALISATION:
			break;
		}
	}
	
	public JButton getBtnConnecterImprimante() {
		return btnConnecterImprimante;
	}
	
	public JButton getBtnConnecterServeurs() {
		return btnConnecterServeurs;
	}
	
	public JButton getBtnDeconnecterImprimante() {
		return btnDeconnecterImprimante;
	}
	
	public JButton getBtnDeconnecterServeurs() {
		return btnDeconnecterServeurs;
	}
	
	public void initComponents() {
		
		//Configuration de la fenêtre
		setTitle("Ordinateur");
		setBounds(100, 100, 608, 715);
		
		//Configuration des labels statiques
		staticLblSNO = new JLabel("SN_O :");
		staticLblSNO.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblSNO.setBounds(64, 62, 63, 14);
		contentPane.add(staticLblSNO);
		
		staticLblDateAttribution = new JLabel("Date d'attribution :");
		staticLblDateAttribution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDateAttribution.setBounds(12, 82, 117, 22);
		contentPane.add(staticLblDateAttribution);
		
		staticLblDateRestitution = new JLabel("Date de resitution :");
		staticLblDateRestitution.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDateRestitution.setBounds(0, 110, 128, 22);
		contentPane.add(staticLblDateRestitution);
		
		staticLblAChanger = new JLabel("A changer :");
		staticLblAChanger.setBounds(53, 184, 76, 22);
		contentPane.add(staticLblAChanger);
		
		staticLblARetourner = new JLabel("A retourner : ");
		staticLblARetourner.setBounds(223, 184, 81, 22);
		contentPane.add(staticLblARetourner);
		
		staticLblDesignation = new JLabel("Designation : ");
		staticLblDesignation.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblDesignation.setBounds(43, 151, 86, 20);
		contentPane.add(staticLblDesignation);
		
		staticLblCPU = new JLabel("CPU : ");
		staticLblCPU.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblCPU.setBounds(346, 114, 63, 14);
		contentPane.add(staticLblCPU);
		
		staticLblAssignedTo = new JLabel("Assign\u00E9 \u00E0 : ");
		staticLblAssignedTo.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblAssignedTo.setBounds(328, 54, 81, 22);
		contentPane.add(staticLblAssignedTo);
		
		staticLblRAM = new JLabel(" RAM : ");
		staticLblRAM.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblRAM.setBounds(346, 86, 63, 14);
		contentPane.add(staticLblRAM);
		
		staticLblTempsUtilisation = new JLabel("Temps d'utilisation :");
		staticLblTempsUtilisation.setBounds(406, 151, 147, 20);
		contentPane.add(staticLblTempsUtilisation);
		
		staticLblImprimanteTitle = new JLabel("Imprimante connect\u00E9e :");
		staticLblImprimanteTitle.setBounds(10, 248, 169, 22);
		contentPane.add(staticLblImprimanteTitle);
		
		staticLblServeursTitle = new JLabel("Serveurs connect\u00E9s :");
		staticLblServeursTitle.setBounds(10, 346, 169, 22);
		contentPane.add(staticLblServeursTitle);
		
		staticLblUniteTemps = new JLabel("Jours");
		staticLblUniteTemps.setHorizontalAlignment(SwingConstants.LEFT);
		staticLblUniteTemps.setFont(new Font("Tahoma", Font.BOLD, 18));
		staticLblUniteTemps.setBounds(487, 184, 63, 20);
		contentPane.add(staticLblUniteTemps);
		
		staticLblTitle = new JLabel("Ordinateur");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(219, 13, 154, 32);
		contentPane.add(staticLblTitle);
		
		//Configuration TextFields
		tfSNO = new JTextField();
		tfSNO.setEditable(false);
		tfSNO.setBounds(139, 59, 117, 20);
		contentPane.add(tfSNO);
		tfSNO.setColumns(10);
		
		tfDesignation = new JTextField();
		tfDesignation.setEditable(false);
		tfDesignation.setColumns(0);
		tfDesignation.setBounds(139, 151, 234, 20);
		contentPane.add(tfDesignation);
		
		tfDateAttribution = new JTextField();
		tfDateAttribution.setEditable(false);
		tfDateAttribution.setColumns(10);
		tfDateAttribution.setBounds(139, 84, 117, 20);
		contentPane.add(tfDateAttribution);
		
		tfDateRestitution = new JTextField();
		tfDateRestitution.setEditable(false);
		tfDateRestitution.setColumns(10);
		tfDateRestitution.setBounds(139, 110, 117, 20);
		contentPane.add(tfDateRestitution);
		
		//Configuration Spinners
		spinnerRAM = new JSpinner();
		spinnerRAM.setEnabled(false);
		spinnerRAM.setModel(new SpinnerNumberModel(4, 4, 8, 4));
		spinnerRAM.setBounds(421, 84, 117, 20);
		contentPane.add(spinnerRAM);
		
		spinnerCPU = new JSpinner();
		spinnerCPU.setEnabled(false);
		spinnerCPU.setModel(new SpinnerNumberModel(4096, 4096, 8192, 4096));
		spinnerCPU.setBounds(421, 112, 117, 20);
		contentPane.add(spinnerCPU);
		
		//Configuration ComboBox
		cmbboxAssignedTo = new JComboBox<>();
		cmbboxAssignedTo.setEnabled(false);
		cmbboxAssignedTo.setEditable(true);
		cmbboxAssignedTo.setBounds(421, 55, 117, 20);
		contentPane.add(cmbboxAssignedTo);
		
		//Configuration Labels dynamiques
		lblAChanger = new JLabel("NON");
		lblAChanger.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAChanger.setBounds(135, 184, 63, 20);
		contentPane.add(lblAChanger);
		
		lblARetourner = new JLabel("NON");
		lblARetourner.setHorizontalAlignment(SwingConstants.RIGHT);
		lblARetourner.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblARetourner.setBounds(284, 184, 63, 20);
		contentPane.add(lblARetourner);
		
		lblJoursUtilisation = new JLabel("#####");
		lblJoursUtilisation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJoursUtilisation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblJoursUtilisation.setBounds(396, 184, 86, 20);
		contentPane.add(lblJoursUtilisation);
		
		//Configuration des tableaux
		scrllpaneImprimante = new JScrollPane();
		scrllpaneImprimante.setBounds(10, 276, 452, 51);
		contentPane.add(scrllpaneImprimante);
		
		scrllpaneServeurs = new JScrollPane();
		scrllpaneServeurs.setBounds(10, 374, 567, 180);
		contentPane.add(scrllpaneServeurs);
		
		tableImprimante = new JTable();
		tableImprimante.setFillsViewportHeight(true);
		tableImprimante.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"SN_I", "D\u00E9signation", "R\u00E9solution"
			}
		));
		tableImprimante.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrllpaneImprimante.setViewportView(tableImprimante);
		
		tblServeurs = new JTable();
		tblServeurs.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"SN_S", "D\u00E9signation", "Charge"
			}
		));
		tblServeurs.getColumnModel().getColumn(0).setPreferredWidth(150);
		tblServeurs.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblServeurs.getColumnModel().getColumn(2).setPreferredWidth(70);
		scrllpaneServeurs.setViewportView(tblServeurs);
		
		//Configuration des boutons
		btnAnnuler.setBounds(242, 632, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(333, 632, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(472, 632, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		btnConnecterImprimante = new JButton("Connecter");
		btnConnecterImprimante.setBounds(466, 276, 111, 25);
		contentPane.add(btnConnecterImprimante);
		
		btnDeconnecterImprimante = new JButton("D\u00E9connecter");
		btnDeconnecterImprimante.setBounds(466, 302, 111, 25);
		contentPane.add(btnDeconnecterImprimante);
		
		btnConnecterServeurs = new JButton("Connecter");
		btnConnecterServeurs.setBounds(10, 564, 111, 25);
		contentPane.add(btnConnecterServeurs);
		
		btnDeconnecterServeurs = new JButton("D\u00E9connecter");
		btnDeconnecterServeurs.setBounds(127, 564, 111, 25);
		contentPane.add(btnDeconnecterServeurs);
	}
}
