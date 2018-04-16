package gestionParcInfo.test;

import gestionParcInfo.entity.Employe;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Constructeur du jeux de tests.
 * @author Florian
 *
 */
public class TuPersistenceEmploye {
	/**
	 * test création d'un employé.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuCreateEmploye(Connection conn) throws SQLException {
		
		Employe employe = new Employe("DUPOMA2","Dupont", "Martine", "dupontmar@email.com");
		employe.create(conn);

	}
	
	/**
	 * test update employe.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuUpdateEmploye(Connection conn) throws SQLException {
	
		Employe employe = new Employe("DUPOMA2","Dupont", "Marie", "dupontmar@email.com");
		employe.update(conn);

	}
	
	/**
	 * test suppression d'un employé.
	 * @param conn
	 * 
	 * @throws SQLException
	 * 
	 */
	public void tuRemoveEmploy(Connection conn) throws SQLException {
		
		Employe employe = new Employe("DUPOMA1","Dupont", "Martine", "dupontmar@email.com");
		employe.remove(conn);

	}
	
	
	

}