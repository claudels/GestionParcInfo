package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;

public class Imprimante implements IEntity{
	private String sn;
	private String designation;
	private int resolution;
	
	/**
	 * Creation d'une imprimante (Déjà présente en base ou pas)
	 * @param sn Numéro de série de l'imprimante
	 * @param designation Désignation de l'imprimante
	 * @param resolution Résolution de l'imprimante (Entre 150 et 300)
	 */
	public Imprimante(String sn, String designation, int resolution) {
		this.sn = sn;
		this.designation = designation;
		this.resolution = resolution;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setResolution(int resolution) {
		this.resolution = resolution;
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
