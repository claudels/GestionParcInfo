package gestionParcInfo.repository;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AlerteRepository extends Repository<Alerte> {
	
	private static final String SQL_FIND_ID = "SELECT * FROM Alerte WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Alerte";
	private static final String SQL_GET_MAXID = "SELECT MAX(code) FROM Alerte";
	
	public AlerteRepository(Connection conn) {
		super(conn);
	  }
	
	public int getMaxId() throws SQLException {
		int id = -1;
		ResultSet rs = null;
		
		//On prépare la requete pour récupérer toutes les lignes de la table
		this.pstmt = this.conn.prepareStatement(AlerteRepository.SQL_GET_MAXID);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On recupere l'id qui sera attribué à la nouvelle adresse
		if(rs.next());
			id = rs.getInt(1);
		
		pstmt.close();
		rs.close();
		
		return id;	
		
	}
	
	/**
	 * Récupérer une Alerte dans la base
	 * @param id Identifiant de l'Alerte
	 * @return L'objet Alerte
	 * @throws SQLException
	 */
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
		
		pstmt.close();
		rs.close();
		
		return alerte;
	}

	@Override
	public ArrayList<Alerte> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Alerte> alertes = new ArrayList<>();
		
		//On prépare la requete pour récupérer toutes les lignes venant du matricule de l'employé courant
		this.pstmt = this.conn.prepareStatement(AlerteRepository.SQL_GET_ALL);

		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de résultats
		while(rs.next()) {
			//On récupère l'employé associé à l'Alerte
			EmployeRepository employeRepo = new EmployeRepository(conn);
			Employe employeAssociated = employeRepo.findByMatricule(rs.getString(3));
			
			alertes.add(new Alerte(rs.getInt(1), rs.getString(2), employeAssociated));
		}
		
		pstmt.close();
		rs.close();
		
		return alertes;
	};
}

