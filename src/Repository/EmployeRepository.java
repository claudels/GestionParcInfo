package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Employe;

public class EmployeRepository {
	
	private static final String SQL_COUNT_MATRICULE = "SELECT * FROM Employe WHERE matricule LIKE ?";
	
	private PreparedStatement pstmt;
	private Connection conn;
	
	public EmployeRepository(Connection conn) {
		this.conn = conn;
	}
	
	public int countEmployeByMatriculePattern(String matriculePattern) throws SQLException {
		int counter = 0;
		ResultSet rs = null;
		
		//On pr�pare la requete pour r�cup�rer toutes les lignes venant du matricule de l'employ� courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_COUNT_MATRICULE);
		this.pstmt.setString(1, '%' + matriculePattern + '%');
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de r�sultats
		while(rs.next())
			counter++;
		
		return counter;
	}
}
