package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;;

public class OrdinateurRepository extends Repository<Ordinateur> {
	private static final String SQL_FIND_SN = "SELECT * FROM Ordinateur WHERE sn_o=?";
	private static final String SQL_GET_ALL ="SELECT * FROM Ordinateur";

	public OrdinateurRepository(Connection conn) {
		super(conn);
	}
	
	/**
	 * Récuperer un Ordinateur dans la base
	 * @param sn Numéro de série de l'ordinateur
	 * @return L'objet Ordinateur correspondant
	 * @throws SQLException
	 */
	public Ordinateur findBySN(String sn) throws SQLException{
		ResultSet rs = null;
		Ordinateur ordinateur = null;
		
		this.pstmt = this.conn.prepareStatement(OrdinateurRepository.SQL_FIND_SN);
		this.pstmt.setString(1, sn);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			//Récupération de l'imprimante associé (si il y en a une)
			ImprimanteRepository imprimanteRepo = new ImprimanteRepository(conn);
			Imprimante imprimante = imprimanteRepo.findBySN(rs.getString(5));
			
			//Récupération de l'employé associé (si il y en a un)
			EmployeRepository employeRepo = new EmployeRepository(conn);
			Employe associatedEmploye = employeRepo.findByMatricule(rs.getString(6));
			
			try {
				if(rs.getString(8)==null)
				ordinateur = new Ordinateur(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), imprimante, associatedEmploye, Ordinateur.dateFormatter.parse(rs.getString(7)),null);
				else
					ordinateur = new Ordinateur(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), imprimante, associatedEmploye, Ordinateur.dateFormatter.parse(rs.getString(7)), Ordinateur.dateFormatter.parse(rs.getString(8)));	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ordinateur;
	}

	@Override
	public ArrayList<Ordinateur> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Ordinateur> ordinateurs = new ArrayList<>();
		
		this.pstmt = this.conn.prepareStatement(OrdinateurRepository.SQL_GET_ALL);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			//Récupération de l'imprimante associé (si il y en a une)
			ImprimanteRepository imprimanteRepo = new ImprimanteRepository(conn);
			Imprimante imprimante = imprimanteRepo.findBySN(rs.getString(5));
			
			//Récupération de l'employé associé (si il y en a un)
			EmployeRepository employeRepo = new EmployeRepository(conn);
			Employe associatedEmploye = employeRepo.findByMatricule(rs.getString(6));
			
			try {
				ordinateurs.add(new Ordinateur(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), imprimante, associatedEmploye, Ordinateur.dateFormatter.parse(rs.getString(7)), Ordinateur.dateFormatter.parse(rs.getString(8))));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ordinateurs;
	}
}
