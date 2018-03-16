package gestionParcInfo.controller;

import gestionParcInfo.test.TUPersistanceOrdinateur;
import gestionParcInfo.test.TUPersistanceOrdinateurServeurLink;
import gestionParcInfo.test.TUPersistanceServeur;
import gestionParcInfo.test.TUPersistenceEmploye;
import gestionParcInfo.test.TUPersistanceAlerte;
import gestionParcInfo.test.TUPersistanceImprimante;

public class GestionParcInfo {
	public static void main(String[] args) {
		TUPersistenceEmploye tu_persistemploye = new TUPersistenceEmploye();
		TUPersistanceOrdinateur tu_persistordi = new TUPersistanceOrdinateur();
		TUPersistanceImprimante tu_persistimpr = new TUPersistanceImprimante();
		TUPersistanceAlerte tu_persistalerte = new TUPersistanceAlerte();
		TUPersistanceServeur tu_persistserv = new TUPersistanceServeur();
		TUPersistanceOrdinateurServeurLink tu_persistordiserv = new TUPersistanceOrdinateurServeurLink();
		
		//tu_persist.TU_Create_Employe();
		//tu_persist.TU_Update_Employe();
		//tu_persist.TU_Remove_Employe();
		//tu_persistordi.TU_Create_Ordinateur();
		//tu_persistordi.TU_Update_Ordinateur(); 
		//tu_persistordi.TU_Remove_Ordinateur();
		//tu_persistimpr.TU_Create_Imprimante();  
		//tu_persistimpr.TU_Update_Imprimante();
		//tu_persistimpr.TU_Remove_Imprimante(); // Pas tester (create doit être disponible d'abord)
		//tu_persistalerte.TU_Create_Alerte(); 
		//tu_persistalerte.TU_Update_Alerte(); 
		//tu_persistalerte.TU_Remove_Alerte(); 
		//tu_persistserv.TU_Create_Serveur(); 
		//tu_persistserv.TU_Update_Serveur(); 
		//tu_persistserv.TU_Remove_Serveur(); 
		//tu_persistordiserv.TU_Create_OrdinateurServeurLink(); Ne fonctionne pas, propablement un pb de pattern lors de la récup dans la bdd
		//tu_persistordiserv.TU_Update_OrdinateurServeurLink(); Pas testé
		//tu_persistordiserv.TU_Remove_OrdinateurServeurLink(); pas testé
	}
}
