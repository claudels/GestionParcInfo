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

	public void TU_Create_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("12","TestPersist",50);
		
		impr.create(conn);	
	}
	
	public void TU_Update_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1","TestPersist",50);	
			
		impr.update(conn);
	}
	
	public void TU_Remove_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1","TestPersist",50);
		
		impr.remove(conn);
			
	}
}
