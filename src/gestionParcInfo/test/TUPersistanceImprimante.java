package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.SQLException;

import gestionParcInfo.entity.Imprimante;

public class TUPersistanceImprimante {

	public void TU_Create_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);
		
		impr.create(conn);	
	}
	
	public void TU_Update_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);	
			
		impr.update(conn);
	}
	
	public void TU_Remove_Imprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);
		
		impr.remove(conn);
			
	}
}
