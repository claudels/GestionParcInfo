package gestionParcInfo;

import gestionParcInfo.controller.AlerteController;
import gestionParcInfo.controller.EmployeController;
import gestionParcInfo.controller.ImprimanteController;
import gestionParcInfo.controller.OrdinateurController;
import gestionParcInfo.controller.ServeurController;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.AlerteRepository;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.ImprimanteRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.repository.OrdinateurServeurLinkRepository;
import gestionParcInfo.repository.ServeurRepository;
import gestionParcInfo.test.TUPersistanceAlerte;
import gestionParcInfo.test.TUPersistanceImprimante;
import gestionParcInfo.test.TUPersistanceOrdinateur;
import gestionParcInfo.test.TUPersistanceOrdinateurServeurLink;
import gestionParcInfo.test.TUPersistanceServeur;
import gestionParcInfo.test.TUPersistenceEmploye;
import gestionParcInfo.view.GestionParc;
import gestionParcInfo.view.tab.AlerteTab;
import gestionParcInfo.view.tab.EmployeTab;
import gestionParcInfo.view.tab.ImprimanteTab;
import gestionParcInfo.view.tab.OrdinateurTab;
import gestionParcInfo.view.tab.ServeurTab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class GestionParcInfo {
	public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String dbUsername = "parcinfo";
	public static final String dbPassword = "network";
	
	/**
	 * Méthode principale du programme.
	 * @param args Arguments du programme
	 */
	public static void main(String[] args) {
		
		executeTests();
		
		//Modèles
		Ordinateurs ordinateurs = null;
		Serveurs serveurs = null;
		OrdinateurServeurLinks ordinateurServeurLinks = null;
		Employes employes = null;
		Imprimantes imprimantes = null;
		Alertes alertes = null;
		
		//Onglets du gestionnaire du parc
		OrdinateurTab ordiTab = new OrdinateurTab();
		ServeurTab serveurTab = new ServeurTab();
		AlerteTab alerteTab = new AlerteTab();
		EmployeTab employeTab = new EmployeTab();
		ImprimanteTab imprimanteTab = new ImprimanteTab();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
			
			//Instanciation des repo
			OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
			OrdinateurServeurLinkRepository oslRepo = new OrdinateurServeurLinkRepository(conn);
			ServeurRepository serveurRepository = new ServeurRepository(conn);
			EmployeRepository employeRepository = new EmployeRepository(conn);
			ImprimanteRepository imprimanteRepository = new ImprimanteRepository(conn);
			AlerteRepository alerteRepository = new AlerteRepository(conn);
			
			//Instanciation des modèles
			ordinateurs = new Ordinateurs(ordiRepo.getAll(), ordiTab);
			ordinateurServeurLinks = new OrdinateurServeurLinks(oslRepo.getAll());
			serveurs = new Serveurs(serveurRepository.getAll(), ordinateurServeurLinks, serveurTab);
			employes = new Employes(employeRepository.getAll(), ordinateurs, employeTab);
			imprimantes = new Imprimantes(imprimanteRepository.getAll(), ordinateurs, imprimanteTab);
			alertes = new Alertes(alerteRepository.getAll(), alerteTab);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Controleurs
		OrdinateurController ordiController = new OrdinateurController(ordiTab, ordinateurs, serveurs, employes, ordinateurServeurLinks, imprimantes);

		ServeurController servController = new ServeurController(serveurTab,serveurs, ordinateurServeurLinks);
		EmployeController employeController = new EmployeController(employeTab,employes,ordinateurs,alertes);
		ImprimanteController imprimanteController = new ImprimanteController(imprimanteTab,imprimantes,ordinateurs);
		AlerteController alerteController = new AlerteController(alerteTab,alertes);
		
		//Add ordis listeners
		ordiTab.getBtnAjouter().addActionListener(ordiController);
		ordiTab.getBtnRetourner().addActionListener(ordiController);
		ordiTab.getBtnSupprimer().addActionListener(ordiController);
		ordiTab.getTableOrdinateur().addMouseListener(ordiController);
		
		//Add serveurs listeners
		serveurTab.getBtnAJouter().addActionListener(servController);
		serveurTab.getBtnSupprimer().addActionListener(servController);
		serveurTab.getTblServeur().addMouseListener(servController);
		
		//Add employe listeners
		employeTab.getBtnAjouter().addActionListener(employeController);
		employeTab.getBtnAlerter().addActionListener(employeController);
		employeTab.getBtnSupprimer().addActionListener(employeController);
		employeTab.getTableEmploye().addMouseListener(employeController);
		
		//Add imprimante listeners
		imprimanteTab.getBtnAjouter().addActionListener(imprimanteController);
		imprimanteTab.getBtnSupprimer().addActionListener(imprimanteController);
		imprimanteTab.getTableImprimante().addMouseListener(imprimanteController);
		
		alerteTab.getBtnSupprimer().addActionListener(alerteController);
		
		//Affichage de l'IHM principale
		GestionParc gestionParcIhm = new GestionParc(ordiTab, imprimanteTab, serveurTab, employeTab, alerteTab);
		gestionParcIhm.setVisible(true);
		
		//Execution des tests de persistence
		
	}
	
	private static void executeTests() {
		Connection conn = null;
		
		TUPersistenceEmploye tuPersistemploye = new TUPersistenceEmploye();
		TUPersistanceOrdinateur tuPersistordi = new TUPersistanceOrdinateur();
		TUPersistanceImprimante tuPersistimpr = new TUPersistanceImprimante();
		TUPersistanceAlerte tuPersistalerte = new TUPersistanceAlerte();
		TUPersistanceServeur tuPersistserv = new TUPersistanceServeur();
		TUPersistanceOrdinateurServeurLink tuPersistordiserv = new TUPersistanceOrdinateurServeurLink();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
			
			tuPersistemploye.TU_Create_Employe(conn);
			tuPersistemploye.TU_Update_Employe(conn);
			tuPersistemploye.TU_Remove_Employe(conn);
			
			tuPersistordi.TU_Create_Ordinateur(conn);
			tuPersistordi.TU_Update_Ordinateur(conn); 
			tuPersistordi.TU_Remove_Ordinateur(conn);
			
			tuPersistimpr.TU_Create_Imprimante(conn);  
			tuPersistimpr.TU_Update_Imprimante(conn);
			tuPersistimpr.TU_Remove_Imprimante(conn);
			
			tuPersistalerte.TU_Create_Alerte(conn); 
			tuPersistalerte.TU_Update_Alerte(conn); 
			tuPersistalerte.TU_Remove_Alerte(conn); 
			
			tuPersistserv.TU_Create_Serveur(conn); 
			tuPersistserv.TU_Update_Serveur(conn); 
			tuPersistserv.TU_Remove_Serveur(conn); 
			
			tuPersistordiserv.TU_Create_OrdinateurServeurLink(conn); 
			tuPersistordiserv.TU_Update_OrdinateurServeurLink(conn);
			tuPersistordiserv.TU_Remove_OrdinateurServeurLink(conn); 
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
