package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public class Serveur extends Entity {
	
	private static final String SQL_INSERT = "INSERT INTO Serveur VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Serveur SET designation=?, memoire=? WHERE sn_s=?";
	private static final String SQL_DELETE = "DELETE FROM Serveur WHERE sn_s=?";	
	
	private String sn;
	private String designation;
	private long memoire;
	
	/**
	 * Constructeur d'un nouveau serveur.
	 * @param sn Numéro de série du serveur
	 * @param designation Désignation du serveur
	 * @param memoire Mémoire du serveur en Go
	 */
	public Serveur(String sn, String designation, long memoire) {
		this.sn = sn;
		this.designation = designation;
		this.memoire = memoire;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public long getMemoire() {
		return memoire;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setMemoire(long memoire) {
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
		this.pstmt.setLong(3, this.memoire);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void update(Connection conn) throws SQLException {
		//Prépare la requete et l'éxécute
		this.pstmt = conn.prepareStatement(Serveur.SQL_UPDATE);	
		this.pstmt.setString(1, this.designation);
		this.pstmt.setLong(2, this.memoire);
		this.pstmt.setString(3, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
}