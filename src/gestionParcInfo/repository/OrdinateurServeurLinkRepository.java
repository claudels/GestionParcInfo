package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

public class OrdinateurServeurLinkRepository extends Repository {
	private static final String SQL_FIND_ORDI_SERVEUR = "SELECT * FROM Reseau WHERE sn_o=? AND sn_s=?";

	public OrdinateurServeurLinkRepository(Connection conn) {
		super(conn);
	}
	
	public OrdinateurServeurLink findByOrdinateurAndServeur(Ordinateur ordinateur, Serveur serveur) throws SQLException{
		ResultSet rs = null;
		OrdinateurServeurLink ordinateurServeurLink = null;
		
		this.pstmt = this.conn.prepareStatement(OrdinateurServeurLinkRepository.SQL_FIND_ORDI_SERVEUR);
		this.pstmt.setString(1, ordinateur.getSn());
		this.pstmt.setString(2, serveur.getSn());
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			ordinateurServeurLink = new OrdinateurServeurLink(ordinateur, serveur, rs.getInt(3));
		}
		
		return ordinateurServeurLink;
	}

}
