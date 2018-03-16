package gestionParcInfo.controller;

import gestionParcInfo.test.TUPersistanceOrdinateur;
import gestionParcInfo.test.TUPersistanceOrdinateurServeurLink;
import gestionParcInfo.test.TUPersistanceServeur;
import gestionParcInfo.test.TUPersistenceEmploye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.test.TUPersistanceAlerte;
import gestionParcInfo.test.TUPersistanceImprimante;

public class GestionParcInfo {
	//Modèle
	OrdinateurServeurLinks ordinateurServeurLinks = new OrdinateurServeurLinks();
	Serveurs serveurs = new Serveurs(ordinateurServeurLinks);
	
	public static void main(String[] args) {
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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","parcinfo","network");
			
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
