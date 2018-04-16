package gestionParcInfo.test;

import gestionParcInfo.entity.Imprimante;

import java.sql.Connection;
import java.sql.SQLException;



public class TuPersistanceImprimante {
	/**
	 * test création Imprimantes.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuCreateImprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);
		
		impr.create(conn);	
	}
	
	/**
	 * test update imprimante.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuUpdateImprimante(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);	
			
		impr.update(conn);
	}
	
	/**
	 * test remove imprimante.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuRemoveImprima(Connection conn) throws SQLException {
		Imprimante impr = new Imprimante("1000","TestPersist",50);
		
		impr.remove(conn);
			
	}
}
