package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Modèle d'une imprimante.
 * @author Florian Lemarquand
 */
public class Imprimante extends Entity {
	
	private static final String SQL_INSERT = "INSERT INTO Imprimante VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Imprimante SET designation=?,resolution=? WHERE sn_i=?";
	private static final String SQL_DELETE = "DELETE FROM Imprimante WHERE sn_i=?";	
	
	private String sn;
	private String designation;
	private int resolution;
	
	/**
	 * Creation d'une imprimante (Déjà présente en base ou pas).
	 * @param sn Numéro de série de l'imprimante
	 * @param designation Désignation de l'imprimante
	 * @param resolution Résolution de l'imprimante (Entre 150 et 300)
	 */
	public Imprimante(String sn, String designation, int resolution) {
		this.sn = sn;
		this.designation = designation;
		this.resolution = resolution;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	@Override		
	public void remove(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Imprimante.SQL_DELETE);
		this.pstmt.setString(1, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void create(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		//Prépare la requête et l'éxécute
		this.pstmt = conn.prepareStatement(Imprimante.SQL_INSERT);
		this.pstmt.setString(1, this.sn);
		this.pstmt.setString(2, this.designation);
		this.pstmt.setInt(3, this.resolution);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void update(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Imprimante.SQL_UPDATE);
		this.pstmt.setString(1, this.designation);
		this.pstmt.setInt(2, this.resolution);
		this.pstmt.setString(3, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
		
	}
	
}
