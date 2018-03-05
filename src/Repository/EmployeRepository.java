package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Employe;

public class EmployeRepository {
	
	private static final String SQL_COUNT_MATRICULE = "SELECT * FROM Employe WHERE matricule LIKE ?";
	private static final String SQL_FIND_MATRICULE = "SELECT * FROM Employe WHERE matricule=?";
	
	private PreparedStatement pstmt;
	private Connection conn;
	
	public EmployeRepository(Connection conn) {
		this.conn = conn;
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
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_COUNT_MATRICULE);
		this.pstmt.setString(1, matricule);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		while(rs.next()) {
			employe = new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
	}
}
