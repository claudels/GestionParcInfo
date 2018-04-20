package gestionParcInfo.view.fiche;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.view.AssignerOrdinateur;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


/**
 * Création de la fiche Employe.
 * @author Florian Lemarquand
 *
 */
public class FicheEmploye extends Fiche implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	public Date today = Calendar.getInstance().getTime();
	public long nbOfDays;
	DefaultTableModel tableModel;
	private static final String[] columnsNames = {"SN_O", "Désignation", "A changer", "A retourner", "Temps d'utilisation"};
	private ArrayList<Ordinateur> assignedOrdinateurs;
	//labels statiques
	private JLabel staticLblMatricule;
	private JLabel staticLblNom;
	private JLabel staticLblPrenom; 
	private JLabel staticLblEmail;
	private JLabel staticLblTitle;
	private JLabel staticLblOrdinateur;

	//TextFields
	private JTextField tfPrenom; 
	private JTextField tfNom;
	private JTextField tfMatricule;
	private JTextField tfEmail;
	
	//Boutons
	private JButton btnAssignerOrdinateur;

	
	//Table ordinateurs
	private JScrollPane scrlOrdinateurs;
	private JTable tblOrdinateurs;
	private AssignerOrdinateur assignerOrdiForm;
	private Ordinateurs ordinateurs;
	
	/**
	 * Constructeur de la fiche Employe lors de sa création.
	 * @param initialState
	 * 
	 */
	public FicheEmploye(Fiche.State initialState) {
		super(initialState);
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(FicheEmploye.columnsNames);
		
		initComponents();
		this.changeState(initialState);
		
	}

	/**
	 * Constructeur de la ficheEmploye lors de sa modification.
	 * @param initialState
	 * 
	 * @param employe
	 * 
	 * @param employes
	 * 
	 * @param ordinateurs
	 * 
	 */
	public FicheEmploye(Fiche.State initialState,Employe employe,Employes employes,Ordinateurs ordinateurs) {
		this(initialState);
		
		this.ordinateurs = ordinateurs;
		this.assignedOrdinateurs = new ArrayList<Ordinateur>();
		this.tfMatricule.setText(employe.getMatricule());
		this.tfNom.setText(employe.getNom());
		this.tfPrenom.setText(employe.getPrenom());
		this.tfEmail.setText(employe.getEmail());
	
		for (Ordinateur ordinateur : ordinateurs.findOrdinateursByEmploye(employe)) {
			Object[] rowData = new Object[FicheEmploye.columnsNames.length];
			rowData[0] = ordinateur.getSn();
			rowData[1] = ordinateur.getDesignation();
			rowData[2] = ordinateurs.ordinateurMustBeChanged(ordinateur);
			rowData[3] = ordinateurs.ordinateurMustBeReturned(ordinateur);
			
			String tempsUtilisation = "0";
			if (ordinateur.countJoursUtilisation() != null) {
				tempsUtilisation = Long.toString(ordinateur.countJoursUtilisation());
			}
			rowData[4] = tempsUtilisation;
			
			this.tableModel.addRow(rowData);
		}
	}
		

	
	public String getMatricule() {
		return tfMatricule.getText();
	}
	
	public JButton getAssignerOrdinateur() {
		return btnAssignerOrdinateur;
	}
	
	public String getNom() {
		return tfNom.getText();
	}
	
	public ArrayList<Ordinateur> getAssignedOrdinateurs() {
		return assignedOrdinateurs;
	}
	
	public String getPrenom() {
		return tfPrenom.getText();
	}
	
	public String getEmail() {
		return tfEmail.getText();
	}
	
	@Override
	protected void changeState(State newState) {
		super.changeState(newState);
		
		//Interdit à la création
		this.tglbtnMode.setEnabled(newState != Fiche.State.CREATION);
		

		//Interdit à la visualisation
		this.tfMatricule.setEditable(newState != Fiche.State.VISUALISATION);
		this.tfNom.setEditable(newState != Fiche.State.VISUALISATION);
		this.tfPrenom.setEditable(newState != Fiche.State.VISUALISATION);
		this.tfEmail.setEditable(newState != Fiche.State.VISUALISATION);
		this.btnAssignerOrdinateur.setEnabled(newState != Fiche.State.VISUALISATION);
		

		//Autorisé pour création
		this.tfMatricule.setEditable(newState == Fiche.State.CREATION);
		
	}
	
	/**
	 * Ecoute des actions.
	 */
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		
		if (this.assignerOrdiForm != null && e.getSource() == this.assignerOrdiForm.getBtnAssigner()) {
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
	
	/**
	 * Initialisation des objets qui composent la fenetre.
	 */
	public void initComponents() {
		
		//Configuration fenêtre
		setTitle("Employé");
		setBounds(100, 100, 558, 357);
		
		
		//Configuration label statiques
		staticLblMatricule = new JLabel("Matricule : ");
		staticLblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblMatricule.setBounds(10, 62, 75, 14);
		contentPane.add(staticLblMatricule);
		
		staticLblNom = new JLabel("Nom : ");
		staticLblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblNom.setBounds(10, 82, 76, 22);
		contentPane.add(staticLblNom);
		
		staticLblPrenom = new JLabel("Prénom : ");
		staticLblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblPrenom.setBounds(272, 57, 76, 22);
		contentPane.add(staticLblPrenom);
		
		staticLblEmail = new JLabel("E-Mail : ");
		staticLblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblEmail.setBounds(266, 82, 81, 22);
		contentPane.add(staticLblEmail);
		
		staticLblTitle = new JLabel("Employé");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(206, 0, 185, 32);
		contentPane.add(staticLblTitle);
		
		staticLblOrdinateur = new JLabel("Ordinateurs assignés : ");
		staticLblOrdinateur.setBounds(10, 128, 173, 22);
		contentPane.add(staticLblOrdinateur);
		
		//Configuration TextFields
		tfMatricule = new JTextField();
		tfMatricule.setEditable(false);
		tfMatricule.setColumns(10);
		tfMatricule.setBounds(97, 59, 157, 20);
		contentPane.add(tfMatricule);
		
		tfPrenom = new JTextField();
		tfPrenom.setEditable(false);
		tfPrenom.setBounds(359, 56, 157, 20);
		contentPane.add(tfPrenom);
		
		tfNom = new JTextField();
		tfNom.setEditable(false);
		tfNom.setColumns(10);
		tfNom.setBounds(97, 84, 157, 20);
		contentPane.add(tfNom);
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(359, 84, 157, 20);
		contentPane.add(tfEmail);
		
		//Configuration boutons
	
		btnAnnuler.setBounds(193, 274, this.btnAnnuler.getWidth(), this.btnAnnuler.getHeight());
		tglbtnMode.setBounds(284, 274, this.tglbtnMode.getWidth(), this.tglbtnMode.getHeight());
		btnSauver.setBounds(423, 274, this.btnSauver.getWidth(), this.btnSauver.getHeight());
		
		btnAssignerOrdinateur = new JButton("Assigner un ordinateur");
		btnAssignerOrdinateur.setEnabled(true);
		btnAssignerOrdinateur.setBounds(10, 224, 198, 25);
		contentPane.add(btnAssignerOrdinateur);
		
		//Configuration tableau ordinateurs
		scrlOrdinateurs = new JScrollPane();
		scrlOrdinateurs.setBounds(12, 156, 519, 63);
		contentPane.add(scrlOrdinateurs);
		
		tblOrdinateurs = new JTable();
		tblOrdinateurs.setFillsViewportHeight(true);
		tblOrdinateurs.setModel(this.tableModel);
		scrlOrdinateurs.setViewportView(tblOrdinateurs);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (e.getSource() == this.assignerOrdiForm) {
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
