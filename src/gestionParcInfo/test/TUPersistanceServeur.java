package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.repository.EmployeRepository;

public class TUPersistanceServeur {

	public void TU_Create_Serveur(Connection conn) throws SQLException {
		
			Serveur serv = new Serveur("100","TestPersist",4);
			serv.create(conn);
	}
	
	public void TU_Update_Serveur(Connection conn) throws  SQLException{
		
			Serveur serv = new Serveur("100","TestPersistUpdate",1000);
			serv.update(conn);
	
	}
	
	public void TU_Remove_Serveur(Connection conn) throws SQLException {
			Serveur serv = new Serveur("100","TestPersist",1000);
			serv.remove(conn);
	}

	
}