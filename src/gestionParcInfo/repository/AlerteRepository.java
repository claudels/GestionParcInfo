package gestionParcInfo.repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;

public class AlerteRepository extends Repository {
	private static final String SQL_FIND_ID = "SELECT * FROM Alerte WHERE id=?";
	
	public AlerteRepository(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public int getLastId(String matriculePattern) throws SQLException {
		//TODO: Flo a terminer
		/*int counter = 0;
		ResultSet rs = null;
		
		//On prépare la requete pour récupérer toutes les lignes venant du matricule de l'employé courant
		this.pstmt = this.conn.prepareStatement(AlerteRepository.SQL_COUNT_MATRICULE);
		this.pstmt.setString(1, '%' + matriculePattern + '%');
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de résultats
		while(rs.next())
			counter++;
		
		return counter;*/
		
		return 0;
	}
	
	public Alerte findById(int id) throws SQLException{
		ResultSet rs = null;
		Alerte alerte = null;
		
		//On prépare la requete pour récupérer toutes les lignes venant du matricule de l'employé courant
		this.pstmt = this.conn.prepareStatement(AlerteRepository.SQL_FIND_ID);
		this.pstmt.setInt(1, id);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de résultats
		while(rs.next()) {
			//On récupère l'employé associé à l'Alerte
			EmployeRepository employeRepo = new EmployeRepository(conn);
			Employe employeAssociated = employeRepo.findByMatricule(rs.getString(3));
			
			alerte = new Alerte(rs.getInt(1), rs.getString(2), employeAssociated);
		}
		
		return alerte;
	};
}

