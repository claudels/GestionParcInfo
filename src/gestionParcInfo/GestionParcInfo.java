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
import gestionParcInfo.test.TUPersistanceOrdinateur;
import gestionParcInfo.test.TUPersistanceOrdinateurServeurLink;

import gestionParcInfo.test.TuPersistanceImprimante;

import gestionParcInfo.test.TuPersistanceServeur;
import gestionParcInfo.test.TuPersistenceEmploye;
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
				
			
			//Instanciation des modèles
			OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
			ordinateurs = new Ordinateurs(ordiRepo.getAll(), ordiTab);
			
			OrdinateurServeurLinkRepository oslRepo = new OrdinateurServeurLinkRepository(conn);
			ordinateurServeurLinks = new OrdinateurServeurLinks(oslRepo.getAll());
			
			ServeurRepository serveurRepository = new ServeurRepository(conn);
			serveurs = new Serveurs(serveurRepository.getAll(), ordinateurServeurLinks, serveurTab);
			
			EmployeRepository employeRepository = new EmployeRepository(conn);
			employes = new Employes(employeRepository.getAll(), ordinateurs, employeTab);
			
			ImprimanteRepository imprimanteRepository = new ImprimanteRepository(conn);
			imprimantes = new Imprimantes(imprimanteRepository.getAll(), ordinateurs, imprimanteTab);
			
			AlerteRepository alerteRepository = new AlerteRepository(conn);
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

		ServeurController servController = new ServeurController(serveurTab,serveurs, ordinateurServeurLinks, ordinateurs);
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
		
		TuPersistenceEmploye tuPersistemploye = new TuPersistenceEmploye();
		TUPersistanceOrdinateur tuPersistordi = new TUPersistanceOrdinateur();
		TuPersistanceImprimante tuPersistimpr = new TuPersistanceImprimante();
		TUPersistanceAlerte tuPersistalerte = new TUPersistanceAlerte();
		TuPersistanceServeur tuPersistserv = new TuPersistanceServeur();
		TUPersistanceOrdinateurServeurLink tuPersistordiserv = new TUPersistanceOrdinateurServeurLink();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
			
			tuPersistemploye.tuCreateEmploye(conn);
			tuPersistemploye.tuUpdateEmploye(conn);
			tuPersistemploye.tuRemoveEmploy(conn);
			
			tuPersistordi.TU_Create_Ordinateur(conn);
			tuPersistordi.TU_Update_Ordinateur(conn); 
			tuPersistordi.TU_Remove_Ordinateur(conn);
			
			tuPersistimpr.tuCreateImprimante(conn);  
			tuPersistimpr.tuUpdateImprimante(conn);
			tuPersistimpr.tuRemoveImprima(conn);
			
			tuPersistalerte.TU_Create_Alerte(conn); 
			tuPersistalerte.TU_Update_Alerte(conn); 
			tuPersistalerte.TU_Remove_Alerte(conn); 
			
			tuPersistserv.tuCreateServeur(conn); 
			tuPersistserv.tuUpdateServeur(conn); 
			tuPersistserv.tuRemoveServeur(conn); 
			
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
