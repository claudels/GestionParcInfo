package gestionParcInfo.test;

import gestionParcInfo.entity.Serveur;

import java.sql.Connection;
import java.sql.SQLException;



public class TuPersistanceServeur {

	/**
	 * test création serveur.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuCreateServeur(Connection conn) throws SQLException {
		
			Serveur serv = new Serveur("100","TestPersist",4);
			serv.create(conn);
	}
	
	/**
	 * test update serveur.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuUpdateServeur(Connection conn) throws  SQLException {
		
			Serveur serv = new Serveur("100","TestPersistUpdate",1000);
			serv.update(conn);
	
	}
	
	public void tuRemoveServeur(Connection conn) throws SQLException {
			Serveur serv = new Serveur("100","TestPersist",1000);
			serv.remove(conn);
	}

	
}
