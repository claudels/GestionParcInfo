package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.repository.OrdinateurServeurLinkRepository;
import gestionParcInfo.repository.ServeurRepository;

public class TUPersistanceOrdinateurServeurLink {
	
	Ordinateur newOrdi;
	Serveur serveur;

	public void TU_Create_OrdinateurServeurLink(Connection conn) throws SQLException {
		newOrdi = new Ordinateur("erzgeeze", "designation", 4500, 3.5f);
		newOrdi.create(conn);
		serveur = new Serveur("stazareg", "string", 17);
		serveur.create(conn);
		
		OrdinateurServeurLink ordiserv = new OrdinateurServeurLink(newOrdi,serveur,1);
		ordiserv.create(conn);
			
	}
	
	public void TU_Update_OrdinateurServeurLink(Connection conn) throws SQLException {
		OrdinateurServeurLinkRepository oslRepo = new OrdinateurServeurLinkRepository(conn);
		OrdinateurServeurLink osl = oslRepo.findByOrdinateurAndServeur(newOrdi, serveur);
		
		osl.setQuota(150);
		
		osl.update(conn);
	}
	
	public void TU_Remove_OrdinateurServeurLink(Connection conn) throws SQLException {
		OrdinateurServeurLinkRepository oslRepo = new OrdinateurServeurLinkRepository(conn);
		OrdinateurServeurLink osl = oslRepo.findByOrdinateurAndServeur(newOrdi, serveur);
		
		osl.remove(conn);
		newOrdi.remove(conn);
		serveur.remove(conn);
	}

	
}
