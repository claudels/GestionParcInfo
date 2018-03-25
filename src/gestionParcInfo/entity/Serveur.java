package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestionParcInfo.repository.ImprimanteRepository;

public class Serveur extends Entity {
	
	private static final String SQL_INSERT = "INSERT INTO Serveur VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Serveur SET designation=?, memoire=? WHERE sn_s=?";
	private static final String SQL_DELETE = "DELETE FROM Serveur WHERE sn_s=?";	
	
	private String sn;
	private String designation;
	private int memoire;
	
	public Serveur(String sn, String designation, int memoire) {
		this.sn = sn;
		this.designation = designation;
		this.memoire = memoire;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getMemoire() {
		return memoire;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setMemoire(int memoire) {
		this.memoire = memoire;
	}
	


	@Override
	public void remove(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Serveur.SQL_DELETE);
		this.pstmt.setString(1, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();	
	}

	@Override
	public void create(Connection conn) throws SQLException {
		//Prépare la requête et l'éxécute
		this.pstmt = conn.prepareStatement(Serveur.SQL_INSERT);
		this.pstmt.setString(1, this.sn);
		this.pstmt.setString(2, this.designation);
		this.pstmt.setInt(3, this.memoire);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void update(Connection conn) throws SQLException {
		//Prépare la requete et l'éxécute
		this.pstmt = conn.prepareStatement(Serveur.SQL_UPDATE);	
		this.pstmt.setString(1, this.designation);
		this.pstmt.setInt(2, this.memoire);
		this.pstmt.setString(3, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
}