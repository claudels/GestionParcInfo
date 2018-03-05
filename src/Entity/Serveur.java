package Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Interface.IEntity;

public class Serveur implements IEntity {
	private String sn;
	private String designation;
	private int memoire;
	private ArrayList<OrdinateurServeurLink> liensOrdinateur;
	
	public Serveur() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public OrdinateurServeurLink[] getLiensOrdinateur() {
		return (OrdinateurServeurLink[])liensOrdinateur.toArray();
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
	
	public void addLiensOrdinateur(OrdinateurServeurLink lienOrdinateur) {
		this.liensOrdinateur.add(lienOrdinateur);
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
