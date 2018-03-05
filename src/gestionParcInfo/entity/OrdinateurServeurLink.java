package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdinateurServeurLink implements IEntity {
	private Ordinateur ordinateur;
	private Serveur serveur;
	private int quota;
	
	public OrdinateurServeurLink(Ordinateur ordinateur, Serveur serveur, int quota) {
		this.ordinateur = ordinateur;
		this.serveur = serveur;
		this.quota = quota;
	}
	
	public Ordinateur getOrdinateur() {
		return ordinateur;
	}
	
	public Serveur getServeur() {
		return serveur;
	}
	
	public int getQuota() {
		return quota;
	}
	
	public void setQuota(int quota) {
		this.quota = quota;
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
