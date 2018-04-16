package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Repository<T> {
	protected PreparedStatement pstmt;
	protected Connection conn;
	
	/**
	 * Créer un nouveau Repository.
	 * @param conn Connection à utiliser pour le repo
	 * 
	 */
	public Repository(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Récupère l'ensemble des entités dans la base.
	 * @return ArrayList qui contient l'ensemble des entités de type T
	 * 
	 * @throws SQLException
	 * 
	 */
	public abstract ArrayList<T> getAll() throws SQLException;
}
