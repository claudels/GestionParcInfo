package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.ImprimanteRepository;

public class TUPersistanceImprimante {

	public void TU_Create_Imprimante() {
		Connection conn = null;
		Imprimante impr = new Imprimante("12","TestPersist",50);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			impr.create(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Update_Imprimante() {
		Connection conn = null;
		Imprimante impr = new Imprimante("1","TestPersist",50);	
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			
			impr.update(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Remove_Imprimante() {
		Connection conn = null;
		Imprimante impr = new Imprimante("1","TestPersist",50);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			impr.remove(conn);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
