package Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Interface.IEntity;
import Repository.EmployeRepository;

public class Employe implements IEntity{
	private static final String SQL_INSERT = "INSERT INTO Employe VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Employe SET nom=?, prenom=?, email=? WHERE matricule=?";
	private static final String SQL_DELETE = "DELETE FROM Employe WHERE matricule=?";
	
	private PreparedStatement pstmt;
	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private ArrayList<Ordinateur> ordinateurs;
	private ArrayList<Alerte> alertes;
	
	//Constructeur par défaut
	public Employe() {
		this.ordinateurs = new ArrayList<>();
		this.alertes = new ArrayList<>();
	}
	
	public Employe(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.ordinateurs = new ArrayList<>();
		this.alertes = new ArrayList<>();
	}
	
	//Constructeur par recopie
	public Employe(String matricule, String nom, String prenom, String email, ArrayList<Ordinateur> ordinateurs, ArrayList<Alerte> alertes) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.ordinateurs = ordinateurs;
		this.alertes = alertes;
	}
	
	public Alerte[] getAlertes() {
		return (Alerte[])alertes.toArray();
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
	
	public Ordinateur[] getOrdinateurs() {
		return (Ordinateur[])ordinateurs.toArray();
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void addAlerte(Alerte alerte) {
		this.alertes.add(alerte);
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
	
	public void addOrdinateur(Ordinateur ordinateur) {
		this.ordinateurs.add(ordinateur);
	}

	@Override
	public void persist(Connection conn) throws SQLException {
		//Si le matricule est null, alors il s'agit d'un nouvel employé
		if (matricule == null) {
			//On définit le matricule comme la concaténation du nom et du prénom
			String concatenedMatricule = this.nom.substring(0, 4).toUpperCase() + this.prenom.substring(0, 2).toUpperCase();
			
			EmployeRepository employeRepo = new EmployeRepository(conn);
			int matriculeCounter = employeRepo.countEmployeByMatriculePattern(concatenedMatricule);
			
			//On ajoute un nombre au matricule si le matricule existe déjà en base
			if(matriculeCounter == 0)
				this.matricule = concatenedMatricule;
			else
				this.matricule = concatenedMatricule + matriculeCounter;
			
			//Prépare la requête et l'éxécute
			this.pstmt = conn.prepareStatement(Employe.SQL_INSERT);
			this.pstmt.setString(1, this.matricule);
			this.pstmt.setString(2, this.nom);
			this.pstmt.setString(3, this.prenom);
			this.pstmt.setString(4, this.email);
			this.pstmt.executeUpdate();
		}
		//Sinon, le matricule est existant, il faut le mettre à jour
		else {
			//Prépare la requete et l'éxécute
			this.pstmt = conn.prepareStatement(Employe.SQL_UPDATE);
			this.pstmt.setString(1, this.nom);
			this.pstmt.setString(2, this.prenom);
			this.pstmt.setString(3, this.email);
			this.pstmt.setString(4, this.matricule);
			this.pstmt.executeUpdate();
		}
		
		this.pstmt.close();
	}

	@Override
	public void remove(Connection conn) throws Exception {
		if(matricule != null) {
			this.pstmt = conn.prepareStatement(Employe.SQL_DELETE);
			this.pstmt.setString(1, this.matricule);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		}else {
			throw new Exception("L'employé n'a jamais été persisté");
		}
	}

	

}
