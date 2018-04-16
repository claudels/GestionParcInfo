package gestionParcInfo.entity;

import gestionParcInfo.repository.AlerteRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Objet qui repr�sente une Alerte.
 * @author seb
 *
 */
public class Alerte extends Entity {
	private static final String SQL_INSERT = "INSERT INTO Alerte VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Alerte SET message=?, MATRICULE=? WHERE code=?";
	private static final String SQL_DELETE = "DELETE FROM Alerte WHERE code=?";	
	
	private int id = -1;
	private String message;
	private Employe employe;
	
	
	/**
	 * Constructeur pour la cr�ation d'une nouvelle Alerte.
	 * @param message Message de l'alerte
	 * @param employe Employ� associ� � l'alerte
	 */
	public Alerte(String message, Employe employe) {
		this.message = message;
		this.employe = employe;
	}
	
	/**
	 * Constructeur pour la cr�ation d'une Alerte d�j� existante en base.
	 * @param id Identifiant de l'Alerte dans la base
	 * @param message Message de l'alerte
	 * @param employe Employ� associ� � l'Alerte
	 */
	public Alerte(int id,String message, Employe employe) {
		this.id = id;
		this.message = message;
		this.employe = employe;
	}
	 
	public String getMessage() {
		return message;
	}
	
	public Employe getEmploye() {
		return employe;
	}
	
	public int getId() {
		return id;
	}

	/**
	 * Cr�ation d'un nouvel enregistrement.
	 * @param conn Connexion � utiliser pour la cr�ation.
	 */
	public void create(Connection conn) throws SQLException {
		AlerteRepository alerteRepo = new AlerteRepository(conn);
		this.id = alerteRepo.getMaxId() + 1;
		
		//Pr�pare la requ�te et l'�x�cute
		this.pstmt = conn.prepareStatement(Alerte.SQL_INSERT);
		this.pstmt.setInt(1, this.id);
		this.pstmt.setString(2, this.message);
		this.pstmt.setString(3, this.employe.getMatricule());
		this.pstmt.executeUpdate();
		this.pstmt.close();
		
	}
	
	/**
	 * Mise � jour de l'enregistrement dans la base.
	 * @param conn Connexion � utiliser
	 */
	public void update(Connection conn) throws SQLException {
		//Pr�pare la requete et l'�x�cute
		this.pstmt = conn.prepareStatement(Alerte.SQL_UPDATE);
		this.pstmt.setString(1, this.message);
		this.pstmt.setString(2, this.employe.getMatricule());
		this.pstmt.setInt(3, this.id);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
	
	
	@Override
	public void remove(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Alerte.SQL_DELETE);
		this.pstmt.setInt(1, this.id);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
}

	