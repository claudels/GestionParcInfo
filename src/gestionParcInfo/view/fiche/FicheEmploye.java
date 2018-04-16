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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.view.AssignerOrdinateur;
import gestionParcInfo.view.ConnexionImprimante;
import gestionParcInfo.view.fiche.Fiche.State;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;

public class FicheEmploye extends Fiche implements ActionListener, WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Date today = Calendar.getInstance().getTime();
	public long nbOfDays;
	DefaultTableModel tableModel;
	private static final String[] columnsNames = {"SN_O", "D\u00E9signation", "A changer", "A retourner", "Temps d'utilisation"};
	private ArrayList<Ordinateur> assignedOrdinateurs;
	//labels statiques
	private JLabel staticLBL_matricule, staticLBL_nom,staticLBL_prenom, staticLBL_email, staticLBL_title, staticLBL_ordinateurs;

	//TextFields
	private JTextField TF_prenom, TF_nom, TF_matricule, TF_email;
	
	//Boutons
	private JButton BTN_assignerOrdinateur;

	
	//Table ordinateurs
	private JScrollPane SCRLLPANE_ordinateurs;
	private JTable TABLE_ordinateurs;
	private AssignerOrdinateur assignerOrdiForm;
	private Ordinateurs ordinateurs;
	private Employe employe;
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public FicheEmploye(Fiche.State initialState) {
		super(initialState);
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(FicheEmploye.columnsNames);
		
		initComponents();
		this.changeState(initialState);
		
	}
	
	public FicheEmploye(Fiche.State initialState,Employe employe,Employes employes,Ordinateurs ordinateurs) {
		this(initialState);
		
		this.ordinateurs = ordinateurs;
		this.employe = employe;
		this.assignedOrdinateurs = new ArrayList<Ordinateur>();
		this.TF_matricule.setText(employe.getMatricule());
		this.TF_nom.setText(employe.getNom());
		this.TF_prenom.setText(employe.getPrenom());
		this.TF_email.setText(employe.getEmail());
	
		for(Ordinateur ordinateur : ordinateurs.findOrdinateursByEmploye(employe)) {
			Object[] rowData = new Object[FicheEmploye.columnsNames.length];
			rowData[0] = ordinateur.getSn();
			rowData[1] = ordinateur.getDesignation();
			rowData[2] = ordinateurs.ordinateurMustBeChanged(ordinateur);
			rowData[3] = ordinateurs.ordinateurMustBeReturned(ordinateur);
			
			String tempsUtilisation = "0";
			if(ordinateur.countJoursUtilisation() != null)
				tempsUtilisation = Long.toString(ordinateur.countJoursUtilisation());
			
			rowData[4] = tempsUtilisation;
			
			this.tableModel.addRow(rowData);
		}
	}
		

	
	public String getMatricule() {
		return TF_matricule.getText();
	}
	
	public JButton getAssignerOrdinateur() {
		return BTN_assignerOrdinateur;
	}
	public String getNom() {
		return TF_nom.getText();
	}
	
	public ArrayList<Ordinateur> getAssignedOrdinateurs(){
		return assignedOrdinateurs;
	}
	public String getPrenom() {
		return TF_prenom.getText();
	}
	
	public String getEmail() {
		return TF_email.getText();
	}
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		

		//Interdit à la visualisation
		this.TF_matricule.setEditable(newState != Fiche.State.VISUALISATION);
		this.TF_nom.setEditable(newState != Fiche.State.VISUALISATION);
		this.TF_prenom.setEditable(newState != Fiche.State.VISUALISATION);
		this.TF_email.setEditable(newState != Fiche.State.VISUALISATION);
		this.BTN_assignerOrdinateur.setEnabled(newState != Fiche.State.VISUALISATION);
		

		//Autorisé pour création
		this.TF_matricule.setEditable(newState == Fiche.State.CREATION);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(e.getSource() == this.BTN_assignerOrdinateur) {
			
			if(this.assignerOrdiForm == null) {
				//On ouvre le formulaire
				this.assignerOrdiForm = new AssignerOrdinateur(this.ordinateurs, this,this.employe);
				this.assignerOrdiForm.getBtnAssigner().addActionListener(this);
				this.assignerOrdiForm.addWindowListener(this);
				this.assignerOrdiForm.setVisible(true);
			}
			
			this.assignerOrdiForm.toFront();
		}
		if(this.assignerOrdiForm != null && e.getSource() == this.assignerOrdiForm.getBtnAssigner()){
			System.out.println("test");
			Ordinateur ordinateur = this.assignerOrdiForm.getSelectedOrdinateur();
			
			//On met à jour la table
			Object[] rowData = new Object[FicheEmploye.columnsNames.length];
			rowData[0] = ordinateur.getSn();
			rowData[1] = ordinateur.getDesignation();
			rowData[2] = ordinateurs.ordinateurMustBeChanged(ordinateur);
			rowData[3] = ordinateurs.ordinateurMustBeReturned(ordinateur);
			rowData[3] = ordinateurs.ordinateurMustBeReturned(ordinateur);
			rowData[3] = ordinateurs.ordinateurMustBeChanged(ordinateur);
			rowData[4] = ordinateurs.ordinateurMustBeReturned(ordinateur);
			
			
			this.assignedOrdinateurs.add(ordinateur);
			this.tableModel.addRow(rowData);
			//Fermeture du formulaire
			this.assignerOrdiForm.dispose();
			this.assignerOrdiForm = null;
		}
	}
	public void initComponents() {
		
		//Configuration fenêtre
		setTitle("Employ\u00E9");
		setBounds(100, 100, 558, 357);
		
		
		//Configuration label statiques
		staticLBL_matricule = new JLabel("Matricule : ");
		staticLBL_matricule.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_matricule.setBounds(10, 62, 75, 14);
		contentPane.add(staticLBL_matricule);
		
		staticLBL_nom = new JLabel("Nom : ");
		staticLBL_nom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_nom.setBounds(10, 82, 76, 22);
		contentPane.add(staticLBL_nom);
		
		staticLBL_prenom = new JLabel("Pr\u00E9nom : ");
		staticLBL_prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_prenom.setBounds(272, 57, 76, 22);
		contentPane.add(staticLBL_prenom);
		
		staticLBL_email = new JLabel("E-Mail : ");
		staticLBL_email.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLBL_email.setBounds(266, 82, 81, 22);
		contentPane.add(staticLBL_email);
		
		staticLBL_title = new JLabel("Employ\u00E9");
		staticLBL_title.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLBL_title.setBounds(206, 0, 185, 32);
		contentPane.add(staticLBL_title);
		
		staticLBL_ordinateurs = new JLabel("Ordinateurs assign\u00E9s : ");
		staticLBL_ordinateurs.setBounds(10, 128, 173, 22);
		contentPane.add(staticLBL_ordinateurs);
		
		//Configuration TextFields
		TF_matricule = new JTextField();
		TF_matricule.setEditable(false);
		TF_matricule.setColumns(10);
		TF_matricule.setBounds(97, 59, 157, 20);
		contentPane.add(TF_matricule);
		
		TF_prenom = new JTextField();
		TF_prenom.setEditable(false);
		TF_prenom.setBounds(359, 56, 157, 20);
		contentPane.add(TF_prenom);
		
		TF_nom = new JTextField();
		TF_nom.setEditable(false);
		TF_nom.setColumns(10);
		TF_nom.setBounds(97, 84, 157, 20);
		contentPane.add(TF_nom);
		
		TF_email = new JTextField();
		TF_email.setEditable(false);
		TF_email.setColumns(10);
		TF_email.setBounds(359, 84, 157, 20);
		contentPane.add(TF_email);
		
		//Configuration boutons
	
		btnAnnuler.setBounds(193, 274, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(284, 274, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(423, 274, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		BTN_assignerOrdinateur = new JButton("Assigner un ordinateur");
		BTN_assignerOrdinateur.setEnabled(true);
		BTN_assignerOrdinateur.setBounds(10, 224, 198, 25);
		contentPane.add(BTN_assignerOrdinateur);
		
		//Configuration tableau ordinateurs
		SCRLLPANE_ordinateurs = new JScrollPane();
		SCRLLPANE_ordinateurs.setBounds(12, 156, 519, 63);
		contentPane.add(SCRLLPANE_ordinateurs);
		
		TABLE_ordinateurs = new JTable();
		TABLE_ordinateurs.setFillsViewportHeight(true);
		TABLE_ordinateurs.setModel(this.tableModel);
		SCRLLPANE_ordinateurs.setViewportView(TABLE_ordinateurs);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getSource() == this.assignerOrdiForm) {
			this.assignerOrdiForm = null;
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
