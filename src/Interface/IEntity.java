package Interface;

import java.sql.Connection;
import java.sql.SQLException;

public interface IEntity {
	/**
	 * Sauvegarder l'entité dans la base (création ou modification)
	 * @param conn Connection à utiliser
	 */
	public abstract void persist(Connection conn) throws SQLException;
	
	/**
	 * Supprimer l'entité de la base
	 * @param conn Connection à utiliser
	 * @throws Exception 
	 */
	public abstract void remove(Connection conn) throws Exception;
}
