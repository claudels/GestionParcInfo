package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionParcInfo.entity.Imprimante;

public class ImprimanteRepository extends Repository<Imprimante> {
	
	private static final String SQL_FIND_SN = "SELECT * FROM Imprimante WHERE sn_i=?";
	private static final String SQL_GET_ALL = "SELECT * FROM Imprimante";


	public ImprimanteRepository(Connection conn) {
		super(conn);
	}

	/**
	 * Récupérer une Imprimante dans la base
	 * @param sn Numéro de série de l'Imprimante
	 * @return L'objet Imprimante correspondante
	 * @throws SQLException
	 */
	public Imprimante findBySN(String sn) throws SQLException{
		ResultSet rs = null;
		Imprimante imprimante = null;
		
		this.pstmt = this.conn.prepareStatement(ImprimanteRepository.SQL_FIND_SN);
		this.pstmt.setString(1, sn);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			imprimante = new Imprimante(rs.getString(1), rs.getString(2), rs.getInt(3));
		}
		
		return imprimante;
	}

	@Override
	public ArrayList<Imprimante> getAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Imprimante> imprimantes = new ArrayList<>();
		
		this.pstmt = this.conn.prepareStatement(ImprimanteRepository.SQL_GET_ALL);
		rs = this.pstmt.executeQuery();
		 
		while(rs.next()) {
			imprimantes.add(new Imprimante(rs.getString(1), rs.getString(2), rs.getInt(3)));
		}
		
		return imprimantes;
	}
	
	
}
