package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestionParcInfo.repository.ImprimanteRepository;

public class OrdinateurServeurLink implements IEntity {
	
	private static final String SQL_INSERT = "INSERT INTO Reseau VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Reseau SET quota=? WHERE sn_o=? and sn_s=?";
	private static final String SQL_DELETE = "DELETE FROM Reseau WHERE sn_o=? and sn_s=?";	
	
	private PreparedStatement pstmt;
	private Ordinateur ordinateur;
	private Serveur serveur;
	private int quota = -1;
	
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