package gestionParcInfo.view;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.view.fiche.FicheEmploye;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class AssignerOrdinateur extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"SN_O", "Désignation", "Ram","CPU"};
	
	private JPanel contentPane;
	
	//Tableaux
	private JScrollPane scrllPaneAssignerOrdinateur;
	private JTable tableAssignerOrdinateur;
	private DefaultTableModel tableModel;

	//Label statique
	private JLabel staticLblMatricule;
	private JLabel staticLblPrenom;
	private JLabel staticLblEmail;
	private JLabel staticLblSubtitle;
	private JLabel staticLblTitle;
	private JLabel staticLblNom;
	
	//Label dynamique
	private JLabel lblMatricule;
	private JLabel lblEmail;
	private JLabel lblNom;
	private JLabel lblPrenom;
	
	//Boutons
	private JButton btnAnnuler;
	private JButton btnAssigner;
	private Ordinateurs ordinateurs;
	private FicheEmploye ficheEmploye;
	private Employe employe;
	
	/**
	 * Constructeur de la fiche d'assignation d'un ordinateur.
	 * @param ordinateurs
	 * 
	 * @param ficheEmploye
	 * 
	 * @param employe
	 * 
	 */
	public AssignerOrdinateur(Ordinateurs ordinateurs, FicheEmploye ficheEmploye,Employe employe) {
		this.ordinateurs = ordinateurs;
		this.ficheEmploye = ficheEmploye;
		this.employe = employe;
		
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(AssignerOrdinateur.columnNames);
		for (Ordinateur ordinateur : ordinateurs.findOrdinateursAvailable()) {
			Object[] rowData = new Object[AssignerOrdinateur.columnNames.length];
			rowData[0] = ordinateur.getSn();
			rowData[1] = ordinateur.getDesignation();
			rowData[2] = ordinateur.getRam();
			rowData[3] = ordinateur.getCpu();
			this.tableModel.addRow(rowData);
			this.tableModel.fireTableDataChanged();
		}
		
		initComponents();
		lblMatricule.setText(this.ficheEmploye.getMatricule());
		lblNom.setText(this.ficheEmploye.getNom());
		lblPrenom.setText(this.ficheEmploye.getPrenom());
		lblEmail.setText(this.ficheEmploye.getEmail());
	}
	
	/**
	 * Retourne les ordinateur sélectionné.
	 * @return
	 */
	public Ordinateur getSelectedOrdinateur() {
	  int column = this.tableModel.findColumn(AssignerOrdinateur.columnNames[0]);
		int columnSnsIndex = this.tableAssignerOrdinateur.convertColumnIndexToView(column);
		int rowIndex = this.tableAssignerOrdinateur.getSelectedRow();
		return this.ordinateurs.findBySn((String)this.tableModel.getValueAt(rowIndex, columnSnsIndex));
	}
	
	public JButton getBtnAssigner() {
		return btnAssigner;
	}
	
	private void initComponents() {
		//Configuration fenetre
		setTitle("Assigner ordinateur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configurations des labels statiques
		staticLblMatricule = new JLabel("Matricule : ");
		staticLblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblMatricule.setBounds(10, 62, 75, 14);
		contentPane.add(staticLblMatricule);
		
		staticLblPrenom = new JLabel("Prénom : ");
		staticLblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblPrenom.setBounds(305, 89, 75, 14);
		contentPane.add(staticLblPrenom);
		
		staticLblEmail = new JLabel("E-mail : ");
		staticLblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblEmail.setBounds(10, 90, 75, 14);
		contentPane.add(staticLblEmail);
		
		staticLblSubtitle = new JLabel("Séctionnez un ordinateur et cliquez sur le bouton \"Assigner\" : ");
		staticLblSubtitle.setBounds(10, 128, 377, 22);
		contentPane.add(staticLblSubtitle);
		
		staticLblTitle = new JLabel("Assigner un ordinateur");
		staticLblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		staticLblTitle.setBounds(108, 0, 351, 32);
		contentPane.add(staticLblTitle);
		
		staticLblNom = new JLabel("Nom : ");
		staticLblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		staticLblNom.setBounds(305, 61, 75, 14);
		contentPane.add(staticLblNom);
		
		
		//Configuration des labels dynamique
		lblMatricule = new JLabel("###########");
		lblMatricule.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMatricule.setBounds(92, 61, 129, 16);
		contentPane.add(lblMatricule);
		
		
		
		lblEmail = new JLabel("############################");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(92, 89, 215, 16);
		contentPane.add(lblEmail);
		
		lblNom = new JLabel("###########");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNom.setBounds(387, 60, 144, 16);
		contentPane.add(lblNom);
		
		
		
		lblPrenom = new JLabel("###########");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrenom.setBounds(387, 88, 144, 16);
		contentPane.add(lblPrenom);
		
		//Configuration des boutons
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(325, 517, 89, 23);
		contentPane.add(btnAnnuler);
		
		btnAssigner = new JButton("Assigner");
		btnAssigner.setBounds(426, 517, 105, 23);
		contentPane.add(btnAssigner);
		
		
		//Configuration du ScrollPane
		scrllPaneAssignerOrdinateur = new JScrollPane();
		scrllPaneAssignerOrdinateur.setBounds(12, 156, 519, 345);
		contentPane.add(scrllPaneAssignerOrdinateur);
		
		//Configuration du tableau
		tableAssignerOrdinateur = new JTable();
		tableAssignerOrdinateur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAssignerOrdinateur.setModel(this.tableModel);
		tableAssignerOrdinateur.getColumnModel().getColumn(1).setPreferredWidth(132);
		tableAssignerOrdinateur.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableAssignerOrdinateur.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrllPaneAssignerOrdinateur.setViewportView(tableAssignerOrdinateur);
	}
}
