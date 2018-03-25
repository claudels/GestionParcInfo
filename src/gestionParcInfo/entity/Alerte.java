package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestionParcInfo.repository.AlerteRepository;

public class Alerte extends Entity{
	//TODO:FLO MODIFIER LES REQUETES
	private static final String SQL_INSERT = "INSERT INTO Alerte VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Alerte SET message=?, MATRICULE=? WHERE code=?";
	private static final String SQL_DELETE = "DELETE FROM Alerte WHERE code=?";	
	
	private int id =-1;
	private String message;
	private Employe employe;
	
	
	/**
	 * Constructeur pour la création d'une nouvelle Alerte
	 * @param message Message de l'alerte
	 * @param employe Employé associé à l'alerte
	 */
	public Alerte(String message, Employe employe) {
		this.message = message;
		this.employe = employe;
	}
	
	/**
	 * Constructeur pour la création d'une Alerte déjà existence en base
	 * @param id Identifiant de l'Alerte dans la base
	 * @param message Message de l'alerte
	 * @param employe Employé associé à l'Alerte
	 */
	public Alerte(int id,String message, Employe employe) {
		this.id= id;
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

	
	public void create(Connection conn) throws SQLException {
		AlerteRepository AlerteRepo = new AlerteRepository(conn);
		this.id = AlerteRepo.getMaxId() + 1;
		
		//Prépare la requête et l'éxécute
		this.pstmt = conn.prepareStatement(Alerte.SQL_INSERT);
		this.pstmt.setInt(1, this.id);
		this.pstmt.setString(2, this.message);
		this.pstmt.setString(3, this.employe.getMatricule());
		this.pstmt.executeUpdate();
		this.pstmt.close();
		
	}
	
	public void update(Connection conn) throws SQLException {
		//Prépare la requete et l'éxécute
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

	