package gestionParcInfo.test;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.view.GestionParc;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.ImprimanteRepository;
import gestionParcInfo.repository.Repository;

public class TUPersistenceEmploye {
	
	public void TU_Create_Employe() {
		Connection conn = null;
		
		Employe employe = new Employe("DUPOMA1","Dupont", "Martine", "dupontmar@email.com");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			employe.create(conn);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Update_Employe() {
		Connection conn = null;
		
		Employe employe = new Employe("DUPOMA1","Dupont", "Marie", "dupontmar@email.com");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			employe.update(conn);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void TU_Remove_Employe() {
		Connection conn = null;
		
		Employe employe = new Employe("DUPOMA1","Dupont", "Martine", "dupontmar@email.com");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Florian","network");
			employe.remove(conn);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	

}