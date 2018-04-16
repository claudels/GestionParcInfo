package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.SQLException;

import gestionParcInfo.entity.Employe;

public class TUPersistenceEmploye {
	
	public void TU_Create_Employe(Connection conn) throws SQLException {
		
		Employe employe = new Employe("DUPOMA2","Dupont", "Martine", "dupontmar@email.com");
		employe.create(conn);

	}
	
	public void TU_Update_Employe(Connection conn) throws SQLException {
	
		Employe employe = new Employe("DUPOMA2","Dupont", "Marie", "dupontmar@email.com");
		employe.update(conn);

	}
	
	public void TU_Remove_Employe(Connection conn) throws SQLException {
		
		Employe employe = new Employe("DUPOMA1","Dupont", "Martine", "dupontmar@email.com");
		employe.remove(conn);

	}
	
	
	

}