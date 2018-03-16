package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

public class OrdinateurServeurLinkRepository extends Repository<OrdinateurServeurLink> {
	private static final String SQL_FIND_ORDI_SERVEUR = "SELECT * FROM Reseau WHERE SN_O=? AND SN_S=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Reseau";

	public OrdinateurServeurLinkRepository(Connection conn) {
		super(conn);
	}
	
	/**
	 * Récupérer un Lien dans la base
	 * @param ordinateur Ordinateur correspondant au lien
	 * @param serveur Serveur correspondant au lien
	 * @return L'objet OrdinateruServeurLink correspondant
	 * @throws SQLException
	 */
	public OrdinateurServeurLink findByOrdinateurAndServeur(Ordinateur ordinateur, Serveur serveur) throws SQLException{
		ResultSet rs = null;
		OrdinateurServeurLink ordinateurServeurLink = null;
		
		this.pstmt = this.conn.prepareStatement(OrdinateurServeurLinkRepository.SQL_FIND_ORDI_SERVEUR);
		this.pstmt.setString(1, ordinateur.getSn());
		this.pstmt.setString(2, serveur.getSn());
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			ordinateurServeurLink = new OrdinateurServeurLink(ordinateur, serveur, rs.getInt(1));
		}
		
		return ordinateurServeurLink;
	}

	@Override
	public ArrayList<OrdinateurServeurLink> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<OrdinateurServeurLink> ordinateurServeurLinks = new ArrayList<>();
		
		this.pstmt = this.conn.prepareStatement(OrdinateurServeurLinkRepository.SQL_GET_ALL);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			//Récupération de l'ordinateur
			OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
			Ordinateur ordinateur = ordiRepo.findBySN(rs.getString(1));
			
			//Récupération du serveur
			ServeurRepository serveurRepo = new ServeurRepository(conn);
			Serveur serveur = serveurRepo.findBySN(rs.getString(2));
			
			ordinateurServeurLinks.add(new OrdinateurServeurLink(ordinateur, serveur, rs.getInt(3)));
		}
		
		return ordinateurServeurLinks;
	}

	

}
