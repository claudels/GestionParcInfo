package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdinateurServeurLink extends Entity {
	
	private static final String SQL_INSERT = "INSERT INTO Reseau VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Reseau SET quota=? WHERE sn_o=? and sn_s=?";
	private static final String SQL_DELETE = "DELETE FROM Reseau WHERE sn_o=? and sn_s=?";	
	
	private Ordinateur ordinateur;
	private Serveur serveur;
	private int quota = -1;
	
	/**
	 * Constructeur d'un lien entre un ordinateur et un serveur.
	 * @param ordinateur Ordinateur à connecter au serveur
	 * @param serveur Serveur à connecter à l'ordinateur
	 * @param quota Quota en Mo alloué à l'ordinateur
	 */
	public OrdinateurServeurLink(Ordinateur ordinateur, Serveur serveur, int quota) {
		this.ordinateur = ordinateur;
		this.serveur = serveur;
		this.quota = quota;
	}
	
	public Ordinateur getOrdinateur() {
		return ordinateur;
	}
	
	public Serveur getServeur() {
		return serveur;
	}
	
	public int getQuota() {
		return quota;
	}
	
	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	@Override
	public void remove(Connection conn) throws SQLException {
			this.pstmt = conn.prepareStatement(OrdinateurServeurLink.SQL_DELETE);
			this.pstmt.setString(1, this.ordinateur.getSn());
			this.pstmt.setString(2, this.serveur.getSn());
			this.pstmt.executeUpdate();
			this.pstmt.close();
	}

	@Override
	public void create(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(OrdinateurServeurLink.SQL_INSERT);
		this.pstmt.setInt(1, this.quota);
		this.pstmt.setString(2, this.ordinateur.getSn());
		this.pstmt.setString(3, this.serveur.getSn());
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
			
	@Override
	public void update(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(OrdinateurServeurLink.SQL_UPDATE);
		this.pstmt.setInt(1, this.quota);
		this.pstmt.setString(2, this.ordinateur.getSn());
		this.pstmt.setString(3, this.serveur.getSn());
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}
}