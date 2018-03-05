package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionParcInfo.entity.Serveur;

public class ServeurRepository extends Repository {
	private static final String SQL_FIND_SN = "SELECT * FROM Serveur WHERE sn_s=?";

	public ServeurRepository(Connection conn) {
		super(conn);
	}

	public Serveur findBySN(String sn) throws SQLException{
		ResultSet rs = null;
		Serveur serveur = null;
		
		this.pstmt = this.conn.prepareStatement(ServeurRepository.SQL_FIND_SN);
		this.pstmt.setString(1, sn);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			serveur = new Serveur(rs.getString(1), rs.getString(2), rs.getInt(3));
		}
		
		return serveur;
	}
}
