package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public class Serveur implements IEntity {
	private String sn;
	private String designation;
	private int memoire;
	
	public Serveur(String sn, String designation, int memoire) {
		this.sn = sn;
		this.designation = designation;
		this.memoire = memoire;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getMemoire() {
		return memoire;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setMemoire(int memoire) {
		this.memoire = memoire;
	}
	
	@Override
	public void persist(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
