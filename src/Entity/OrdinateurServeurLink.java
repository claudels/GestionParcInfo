package Entity;

import java.sql.Connection;
import java.sql.SQLException;

import Interface.IEntity;

public class OrdinateurServeurLink implements IEntity {
	private Ordinateur ordinateur;
	private Serveur serveur;
	private int quota;
	
	public OrdinateurServeurLink() {
		// TODO Auto-generated constructor stub
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
	
	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}
	
	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
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
