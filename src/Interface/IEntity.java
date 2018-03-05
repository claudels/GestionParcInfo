package Interface;

import java.sql.Connection;
import java.sql.SQLException;

public interface IEntity {
	/**
	 * Sauvegarder l'entit� dans la base (cr�ation ou modification)
	 * @param conn Connection � utiliser
	 */
	public abstract void persist(Connection conn) throws SQLException;
	
	/**
	 * Supprimer l'entit� de la base
	 * @param conn Connection � utiliser
	 * @throws Exception 
	 */
	public abstract void remove(Connection conn) throws Exception;
}
