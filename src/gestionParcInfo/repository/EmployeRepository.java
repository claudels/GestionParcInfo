package gestionParcInfo.repository;

import gestionParcInfo.entity.Employe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class EmployeRepository extends Repository<Employe> {
	
	private static final String SQL_COUNT_MATRICULE = "SELECT * FROM Employe WHERE matricule LIKE ?";
	private static final String SQL_FIND_MATRICULE = "SELECT * FROM Employe WHERE matricule=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Employe";
	
	public EmployeRepository(Connection conn) {
		super(conn);
	}
	
	/**
	 * Compte le nombre d'employ�s o� leurs matricule contient un pattern pass� en param�tre .
	 * @param matriculePattern Le pattern � chercher dans les matricule
	 * 
	 * @return Le nombre d'employ� correspondant au pattern
	 * 
	 * @throws SQLException
	 * 
	 */
	public int countEmployeByMatriculePattern(String matriculePattern) throws SQLException {
		int counter = 0;
		ResultSet rs = null;
		
		//On pr�pare la requete pour r�cup�rer toutes les lignes venant du matricule de l'employ� courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_COUNT_MATRICULE);
		this.pstmt.setString(1, '%' + matriculePattern + '%');
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		//On compte le nombre de lignes de r�sultats
		while (rs.next()) {
			counter++;
		}
		
		pstmt.close();
		rs.close();
		
		return counter;
	}
	
	/**
	 * R�cup�rer un Employe dans la base.
	 * @param matricule Matricule de l'Employe � r�cup�rer
	 * 
	 * @return L'objet Employe correspondant
	 * 
	 * @throws SQLException
	 * 
	 */
	public Employe findByMatricule(String matricule) throws SQLException {
		ResultSet rs = null;
		Employe employe = null;
		
		//On pr�pare la requete pour r�cup�rer toutes les lignes venant du matricule de l'employ� courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_FIND_MATRICULE);
		this.pstmt.setString(1, matricule);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		while (rs.next()) {
			employe = new Employe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		
		pstmt.close();
		rs.close();
		
		return employe;
	}

	@Override
	public ArrayList<Employe> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Employe> employes = new ArrayList<>();
		
		//On pr�pare la requete pour r�cup�rer toutes les lignes venant du matricule de l'employ� courant
		this.pstmt = this.conn.prepareStatement(EmployeRepository.SQL_GET_ALL);
		
		//Execution de la requete
		rs = this.pstmt.executeQuery();
		
		while (rs.next()) {
			employes.add(new Employe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		pstmt.close();
		rs.close();
		
		return employes;
	}

	
}
