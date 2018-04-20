package gestionParcInfo.entity;

import gestionParcInfo.repository.EmployeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Mod�le d'un employ�.
 * @author Sebastien Claudel
 */
public class Employe extends Entity {
	private static final String SQL_INSERT = "INSERT INTO Employe VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Employe SET nom=?, prenom=?, email=? WHERE matricule=?";
	private static final String SQL_DELETE = "DELETE FROM Employe WHERE matricule=?";
	
	private PreparedStatement pstmt;
	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	
	/**
	 * Cr�ation d'un nouvel Employe non pr�sent en base.
	 * @param nom Nom de l'employe
	 * @param prenom Prenom de l'employe
	 * @param email Email de l'employe
	 */
	public Employe(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	/**
	 * Constructeur d'un Employe d�j� pr�sent en base.
	 * @param matricule Matricule de l'employe dans la base
	 * @param nom Nom de l'employe
	 * @param prenom Prenom de l'employe
	 * @param email Email de l'employe
	 */
	public Employe(String matricule, String nom, String prenom, String email) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMatricule() {
		return matricule;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public void remove(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Employe.SQL_DELETE);
		this.pstmt.setString(1, this.matricule);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void create(Connection conn) throws SQLException {
		//On d�finit le matricule comme la concat�nation du nom et du pr�nom
		String concatenedMatricule = this.nom.substring(0, 4).toUpperCase() + this.prenom.substring(0, 2).toUpperCase();
		
		EmployeRepository employeRepo = new EmployeRepository(conn);
		int matriculeCounter = employeRepo.countEmployeByMatriculePattern(concatenedMatricule);
		
		//On ajoute un nombre au matricule si le matricule existe d�j� en base
		if (matriculeCounter == 0) {
			this.matricule = concatenedMatricule;
		} else {
			this.matricule = concatenedMatricule + matriculeCounter;
		}
		
		//Pr�pare la requ�te et l'�x�cute
		this.pstmt = conn.prepareStatement(Employe.SQL_INSERT);
		this.pstmt.setString(1, this.matricule);
		this.pstmt.setString(2, this.nom);
		this.pstmt.setString(3, this.prenom);
		this.pstmt.setString(4, this.email);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void update(Connection conn) throws SQLException {
		//Pr�pare la requete et l'�x�cute
		this.pstmt = conn.prepareStatement(Employe.SQL_UPDATE);
		this.pstmt.setString(1, this.nom);
		this.pstmt.setString(2, this.prenom);
		this.pstmt.setString(3, this.email);
		this.pstmt.setString(4, this.matricule);
		this.pstmt.executeUpdate();	
		this.pstmt.close();
	}
}
