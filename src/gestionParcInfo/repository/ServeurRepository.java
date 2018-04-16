package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionParcInfo.entity.Serveur;

public class ServeurRepository extends Repository<Serveur> {
	private static final String SQL_FIND_SN = "SELECT * FROM Serveur WHERE sn_s=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Serveur";


	public ServeurRepository(Connection conn) {
		super(conn);
	}

	/**
	 * Récupérer un Serveur dans la base
	 * @param sn Numéro de série du serveur
	 * @return L'objet Serveur correspondant
	 * @throws SQLException
	 */
	public Serveur findBySN(String sn) throws SQLException{
		ResultSet rs = null;
		Serveur serveur = null;
		
		this.pstmt = this.conn.prepareStatement(ServeurRepository.SQL_FIND_SN);
		this.pstmt.setString(1, sn);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			serveur = new Serveur(rs.getString(1), rs.getString(2), rs.getLong(3));
		}
		
		pstmt.close();
		rs.close();
		
		return serveur;
	}

	@Override
	public ArrayList<Serveur> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Serveur> serveurs = new ArrayList<>();
		
		this.pstmt = this.conn.prepareStatement(ServeurRepository.SQL_GET_ALL);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			serveurs.add(new Serveur(rs.getString(1), rs.getString(2), rs.getLong(3)));
		}
		
		pstmt.close();
		rs.close();
		
		return serveurs;
	}
}
