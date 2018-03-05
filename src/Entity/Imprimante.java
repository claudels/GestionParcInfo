package Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Interface.IEntity;

public class Imprimante implements IEntity{
	private String sn;
	private String designation;
	private int resolution;
	private ArrayList<Ordinateur> ordinateurs;
	
	public Imprimante() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public Ordinateur[] getOrdinateurs() {
		return (Ordinateur[])ordinateurs.toArray();
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
	
	public void addOrdinateur(Ordinateur ordinateur) {
		this.ordinateurs.add(ordinateur);
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
