package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Repository<T> {
	protected PreparedStatement pstmt;
	protected Connection conn;
	
	/**
	 * Cr�er un nouveau Repository.
	 * @param conn Connection � utiliser pour le repo
	 * 
	 */
	public Repository(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * R�cup�re l'ensemble des entit�s dans la base.
	 * @return ArrayList qui contient l'ensemble des entit�s de type T
	 * 
	 * @throws SQLException
	 * 
	 */
	public abstract ArrayList<T> getAll() throws SQLException;
}
