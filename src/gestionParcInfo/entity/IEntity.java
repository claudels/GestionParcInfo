package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public interface IEntity {

	
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
