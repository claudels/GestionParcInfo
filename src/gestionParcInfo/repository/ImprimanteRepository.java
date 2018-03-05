package gestionParcInfo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionParcInfo.entity.Imprimante;

public class ImprimanteRepository extends Repository {
	
	private static final String SQL_FIND_SN = "SELECT * FROM Imprimante WHERE sn_i=?";

	public ImprimanteRepository(Connection conn) {
		super(conn);
	}

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
}
