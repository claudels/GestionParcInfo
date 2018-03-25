package gestionParcInfo;

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

import gestionParcInfo.controller.EmployeController;
import gestionParcInfo.controller.OrdinateurController;
import gestionParcInfo.controller.ServeurController;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.repository.OrdinateurServeurLinkRepository;
import gestionParcInfo.repository.ServeurRepository;
import gestionParcInfo.test.TUPersistanceAlerte;
import gestionParcInfo.test.TUPersistanceImprimante;

public class GestionParcInfo {
	public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String dbUsername = "parcinfo";
	public static final String dbPassword = "network";
	
	public static void main(String[] args) {
		//Modèles
		Ordinateurs ordinateurs = null;
		Serveurs serveurs = null;
		OrdinateurServeurLinks ordinateurServeurLinks = null;
		Employes employes = null;
		
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
			
			//Instanciation des modèles
			ordinateurs = new Ordinateurs(ordiRepo.getAll(), ordiTab);
			ordinateurServeurLinks = new OrdinateurServeurLinks(oslRepo.getAll());
			serveurs = new Serveurs(serveurRepository.getAll(), ordinateurServeurLinks, serveurTab);
			employes = new Employes(employeRepository.getAll(), ordinateurs, employeTab);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Controleurs
		OrdinateurController ordiController = new OrdinateurController(ordiTab, ordinateurs);
		ServeurController servController = new ServeurController(serveurTab);
		EmployeController employeController = new EmployeController(employeTab);
		
		//Add ordis listeners
		ordiTab.getBtnAjouter().addActionListener(ordiController);
		ordiTab.getBtnRetourner().addActionListener(ordiController);
		ordiTab.getBtnSupprimer().addActionListener(ordiController);
		
		//Add serveurs listeners
		serveurTab.getBtnAJouter().addActionListener(servController);
		serveurTab.getBtnSupprimer().addActionListener(servController);
		
		//Add emoloye listeners
		employeTab.getBtnAjouter().addActionListener(employeController);
		employeTab.getBtnAlerter().addActionListener(employeController);
		employeTab.getBtnSupprimer().addActionListener(employeController);
		
		//Affichage de l'IHM principale
		GestionParc gestionParcIHM = new GestionParc(ordiTab, imprimanteTab, serveurTab, employeTab, alerteTab);
		gestionParcIHM.setVisible(true);
		
		//Execution des tests de persistence
		executeTests();
	}
	
	private static void executeTests() {
		Connection conn = null;
		
		TUPersistenceEmploye tu_persistemploye = new TUPersistenceEmploye();
		TUPersistanceOrdinateur tu_persistordi = new TUPersistanceOrdinateur();
		TUPersistanceImprimante tu_persistimpr = new TUPersistanceImprimante();
		TUPersistanceAlerte tu_persistalerte = new TUPersistanceAlerte();
		TUPersistanceServeur tu_persistserv = new TUPersistanceServeur();
		TUPersistanceOrdinateurServeurLink tu_persistordiserv = new TUPersistanceOrdinateurServeurLink();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
			
			tu_persistemploye.TU_Create_Employe(conn);
			tu_persistemploye.TU_Update_Employe(conn);
			tu_persistemploye.TU_Remove_Employe(conn);
			
			tu_persistordi.TU_Create_Ordinateur(conn);
			tu_persistordi.TU_Update_Ordinateur(conn); 
			tu_persistordi.TU_Remove_Ordinateur(conn);
			
			tu_persistimpr.TU_Create_Imprimante(conn);  
			tu_persistimpr.TU_Update_Imprimante(conn);
			tu_persistimpr.TU_Remove_Imprimante(conn);
			
			tu_persistalerte.TU_Create_Alerte(conn); 
			tu_persistalerte.TU_Update_Alerte(conn); 
			tu_persistalerte.TU_Remove_Alerte(conn); 
			
			tu_persistserv.TU_Create_Serveur(conn); 
			tu_persistserv.TU_Update_Serveur(conn); 
			tu_persistserv.TU_Remove_Serveur(conn); 
			
			tu_persistordiserv.TU_Create_OrdinateurServeurLink(conn); 
			tu_persistordiserv.TU_Update_OrdinateurServeurLink(conn);
			tu_persistordiserv.TU_Remove_OrdinateurServeurLink(conn); 
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
