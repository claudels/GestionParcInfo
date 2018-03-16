package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.repository.EmployeRepository;

public class TUPersistanceServeur {

	public void TU_Create_Serveur() {
		Connection conn = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			Serveur serv = new Serveur("100","TestPersist",4);
			serv.create(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Update_Serveur() {
		Connection conn = null;
		
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			Serveur serv = new Serveur("100","TestPersistUpdate",1000);
			serv.update(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Remove_Serveur() {
		Connection conn = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			Serveur serv = new Serveur("100","TestPersist",1000);
			serv.remove(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}
