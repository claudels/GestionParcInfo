package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionParcInfo.entity.Employe;

public class EmployeRepository extends Repository {
	
	private static final String SQL_COUNT_MATRICULE = "SELECT * FROM Employe WHERE matricule LIKE ?";
	private static final String SQL_FIND_MATRICULE = "SELECT * FROM Employe WHERE matricule=?";
	
	public EmployeRepository(Connection conn) {
		super(conn);
	}
	
	public int countEmployeByMatriculePattern(String matriculePattern) throws SQLException {
		int counter = 0;
		ResultSet rs = null;
		
		//On prépare la requete pour récupérer toutes les lignes venant du matricule de l'employé courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_COUNT_MATRICULE);
		this.pstmt.setString(1, '%' + matriculePattern + '%');
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de résultats
		while(rs.next())
			counter++;
		
		return counter;
	}
	
	public Employe findByMatricule(String matricule) throws SQLException {
		ResultSet rs = null;
		Employe employe = null;
		
		//On prépare la requete pour récupérer toutes les lignes venant du matricule de l'employé courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_FIND_MATRICULE);
		this.pstmt.setString(1, matricule);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		while(rs.next()) {
			employe = new Employe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		
		return employe;
	}
}
