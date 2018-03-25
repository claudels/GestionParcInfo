package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;

public abstract class Entity {

	protected PreparedStatement pstmt;
	
	/**
	 * Cr�er l'entit� dans la base
	 * @param conn Connection � utiliser
	 * 
	 */
	public abstract void create(Connection conn) throws SQLException;
	
	/**
	 * Modifier l'entit� dans la base
	 * @param conn Connection � utiliser
	 * 
	 */
	public abstract void update(Connection conn) throws SQLException;
	
	/**
	 * Supprimer l'entit� de la base
	 * @param conn Connection � utiliser
	 * @throws Exception 
	 */
	public abstract void remove(Connection conn) throws SQLException;
	
	
}
