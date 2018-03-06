package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public interface IEntity {

	
	/**
	 * Créer l'entité dans la base
	 * @param conn Connection à utiliser
	 * 
	 */
	public abstract void create(Connection conn) throws SQLException;
	
	/**
	 * Modifier l'entité dans la base
	 * @param conn Connection à utiliser
	 * 
	 */
	public abstract void update(Connection conn) throws SQLException;
	
	/**
	 * Supprimer l'entité de la base
	 * @param conn Connection à utiliser
	 * @throws Exception 
	 */
	public abstract void remove(Connection conn) throws SQLException;
	
	
}
