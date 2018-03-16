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
import gestionParcInfo.repository.ServeurRepository;

public class TUPersistanceOrdinateurServeurLink {

	public void TU_Create_OrdinateurServeurLink() {
		Connection conn = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			OrdinateurRepository ordirepo = new OrdinateurRepository(conn);
			Ordinateur ordi = ordirepo.findBySN("10");
			ServeurRepository servrepo = new ServeurRepository(conn);
			Serveur serv = servrepo.findBySN("10");
			OrdinateurServeurLink ordiserv = new OrdinateurServeurLink(ordi,serv,1);
			ordiserv.create(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Update_OrdinateurServeurLink() {
		Connection conn = null;
		
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			OrdinateurRepository ordirepo = new OrdinateurRepository(conn);
			Ordinateur ordi = ordirepo.findBySN("10");
			ServeurRepository servrepo = new ServeurRepository(conn);
			Serveur serv = servrepo.findBySN("10");
			OrdinateurServeurLink ordiserv = new OrdinateurServeurLink(ordi,serv,1);
			ordiserv.update(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Remove_OrdinateurServeurLink() {
		Connection conn = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			OrdinateurRepository ordirepo = new OrdinateurRepository(conn);
			Ordinateur ordi = ordirepo.findBySN("10");
			ServeurRepository servrepo = new ServeurRepository(conn);
			Serveur serv = servrepo.findBySN("10");
			OrdinateurServeurLink ordiserv = new OrdinateurServeurLink(ordi,serv,1);
			ordiserv.remove(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}
