package gestionParcInfo.repository;

import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Gestion du repository des réseaux
 * @author Florian Lemarquand
 *
 */
public class OrdinateurServeurLinkRepository extends Repository<OrdinateurServeurLink> {
	private static final String SQL_FIND_ORDI_SERVEUR = "SELECT * FROM Reseau WHERE SN_O=? AND SN_S=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Reseau";

	public OrdinateurServeurLinkRepository(Connection conn) {
		super(conn);
	}
	
	/**
	 * Récupérer un Lien dans la base.
	 * @param ordinateur Ordinateur correspondant au lien
	 * 
	 * @param serveur Serveur correspondant au lien
	 * 
	 * @return L'objet OrdinateruServeurLink correspondant
	 * 
	 * @throws SQLException
	 * 
	 */
	public OrdinateurServeurLink findByOrdinateurAndServeur(Ordinateur ordinateur, Serveur serveur) throws SQLException {
		OrdinateurServeurLink ordinateurServeurLink = null;
		this.pstmt = this.conn.prepareStatement(OrdinateurServeurLinkRepository.SQL_FIND_ORDI_SERVEUR);
		this.pstmt.setString(1, ordinateur.getSn());
		this.pstmt.setString(2, serveur.getSn());
		
		ResultSet rs = null;
		rs = this.pstmt.executeQuery();
		 
		while (rs.next()) {
			ordinateurServeurLink = new OrdinateurServeurLink(ordinateur, serveur, rs.getInt(1));
		}
		
		pstmt.close();
		rs.close();
		
		return ordinateurServeurLink;
	}

	@Override
	public ArrayList<OrdinateurServeurLink> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<OrdinateurServeurLink> ordinateurServeurLinks = new ArrayList<>();
		
		this.pstmt = this.conn.prepareStatement(OrdinateurServeurLinkRepository.SQL_GET_ALL);
		rs = this.pstmt.executeQuery();
		 
		while (rs.next()) {
			//Récupération de l'ordinateur
			OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
			Ordinateur ordinateur = ordiRepo.findBySn(rs.getString(2));
			
			//Récupération du serveur
			ServeurRepository serveurRepo = new ServeurRepository(conn);
			Serveur serveur = serveurRepo.findBySn(rs.getString(3));
			
			ordinateurServeurLinks.add(new OrdinateurServeurLink(ordinateur, serveur, rs.getInt(1)));
		}
		
		pstmt.close();
		rs.close();
		
		return ordinateurServeurLinks;
	}

	

}
