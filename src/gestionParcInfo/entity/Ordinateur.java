package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ordinateur implements IEntity{
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private String sn;
	private String designation;
	private int ram;
	private float cpu;
	private Imprimante imprimante;
	private Employe proprietaire;
	private Date dateAttribution;
	private Date dateRestitution;
	
	/**
	 * Création d'un nouvel ordinateur sans propriétaire
	 * @param sn
	 * @param designation
	 * @param ram
	 * @param cpu
	 */
	public Ordinateur(String sn, String designation, int ram, float cpu) {
		this.sn = sn;
		this.designation = designation;
		this.ram = ram;
		this.cpu = cpu;
	}
	
	/**
	 * Création d'un ordinateur par recopie 
	 * @param sn
	 * @param designation
	 * @param ram
	 * @param cpu
	 * @param imprimante
	 * @param proprietaire
	 * @param dateAttribution
	 * @param dateRestitution
	 */
	public Ordinateur(String sn, String designation, int ram, float cpu, Imprimante imprimante, Employe proprietaire, Date dateAttribution, Date dateRestitution) {
		this.sn = sn;
		this.designation = designation;
		this.ram = ram;
		this.cpu = cpu;
		this.imprimante = imprimante;
		this.proprietaire = proprietaire;
		this.dateAttribution = dateAttribution;
		this.dateRestitution = dateRestitution;
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
	
	@Override
	public void persist(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
