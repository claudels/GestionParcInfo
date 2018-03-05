package Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Interface.IEntity;
import Repository.EmployeRepository;

public class Alerte implements IEntity{
	
	private static final String SQL_INSERT = "INSERT INTO Alerte VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Alerte SET message=?, employe=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM Alerte WHERE id=?";	
	
	private int id =-1;
	private String message;
	private Employe employe;
	
	
	
	public Alerte(String message, Employe employe) {
		this.message = message;
		this.employe = employe;
	}
	
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

	@Override
	public void persist(Connection conn) throws SQLException {
		//Si l'id est null, alors il s'agit d'une nouvelle Alerte
		if (id == -1) {
			
			AlerteRepository AlerteRepo = new AlerteRepository(conn);
			int AlerteCounter = AlerteRepo.;
			
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

		
	}

	@Override
	public void remove(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
	}


	
}

	