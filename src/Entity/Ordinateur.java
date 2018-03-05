package Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Interface.IEntity;

public class Ordinateur implements IEntity{
	private String sn;
	private String designation;
	private int ram;
	private float cpu;
	private Imprimante imprimante;
	private Employe proprietaire;
	private Date dateAttribution;
	private Date dateRestitution;
	private ArrayList<OrdinateurServeurLink> liensServeur;
	
	public Ordinateur() {
		// TODO Auto-generated constructor stub
	}
	
	public float getCpu() {
		return cpu;
	}
	
	public Date getDateAttribution() {
		return dateAttribution;
	}
	
	public Date getDateRestitution() {
		return dateRestitution;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public Imprimante getImprimante() {
		return imprimante;
	}
	
	public OrdinateurServeurLink[] getLiensServeur() {
		return (OrdinateurServeurLink[])liensServeur.toArray();
	}
	
	public Employe getProprietaire() {
		return proprietaire;
	}
	
	public int getRam() {
		return ram;
	}
	
	public String getSn() {
		return sn;
	}
	
	public void setCpu(float cpu) {
		this.cpu = cpu;
	}
	
	public void setDateAttribution(Date dateAttribution) {
		this.dateAttribution = dateAttribution;
	}
	
	public void setDateRestitution(Date dateRestitution) {
		this.dateRestitution = dateRestitution;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public void setImprimante(Imprimante imprimante) {
		this.imprimante = imprimante;
	}
	
	public void setProprietaire(Employe proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public void addLienServeur(OrdinateurServeurLink lienServeur) {
		this.liensServeur.add(lienServeur);
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
