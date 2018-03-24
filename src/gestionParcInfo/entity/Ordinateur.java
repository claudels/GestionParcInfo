package gestionParcInfo.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gestionParcInfo.repository.ImprimanteRepository;

public class Ordinateur implements IEntity{
	
	private static final String SQL_INSERT = "INSERT INTO Ordinateur VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE Ordinateur SET designation=?, ram=?, cpu=?, sn_i=?, dateAttribution=?, dateRestituion=?, matricule=?  WHERE sn_o=?";
	private static final String SQL_DELETE = "DELETE FROM Ordinateur WHERE sn_o=?";	
	
	public static final SimpleDateFormat dateFormatterOracleToJava = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	public static final SimpleDateFormat dateFormatterJavaToOracle = new SimpleDateFormat("dd/MM/yyyy");
	
	private PreparedStatement pstmt;
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
	public void remove(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Ordinateur.SQL_DELETE);
		this.pstmt.setString(1, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void create(Connection conn) throws SQLException {
		//Prépare la requête et l'éxécute
		this.pstmt = conn.prepareStatement(Ordinateur.SQL_INSERT);
		this.pstmt.setString(1, this.sn);
		this.pstmt.setString(2, this.designation);
		this.pstmt.setInt(3, this.ram);
		this.pstmt.setFloat(4, this.cpu);
		
		if(this.imprimante != null)
			this.pstmt.setString(5, this.imprimante.getSn());
		else
			this.pstmt.setString(5, null);
		
		if (this.dateAttribution != null)
			this.pstmt.setString(6, Ordinateur.dateFormatterJavaToOracle.format(this.dateAttribution));
		else
			this.pstmt.setString(6, null);
		
		if (this.dateRestitution != null)
			this.pstmt.setString(7, Ordinateur.dateFormatterJavaToOracle.format(this.dateRestitution));
		else
			this.pstmt.setString(7, null);
		
		if(this.proprietaire != null)
			this.pstmt.setString(8, this.proprietaire.getMatricule());
		else
			this.pstmt.setString(8, null);
		
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public void update(Connection conn) throws SQLException {
		this.pstmt = conn.prepareStatement(Ordinateur.SQL_UPDATE);
		this.pstmt.setString(1, this.designation);
		this.pstmt.setInt(2, this.ram);
		this.pstmt.setFloat(3, this.cpu);
		
		if(this.imprimante != null)
			this.pstmt.setString(4, this.imprimante.getSn());
		else
			this.pstmt.setString(4, null);
			
		this.pstmt.setString(5, Ordinateur.dateFormatterJavaToOracle.format(this.dateAttribution));
		if (dateRestitution != null)
			this.pstmt.setString(6, Ordinateur.dateFormatterJavaToOracle.format(this.dateRestitution));
		else
			this.pstmt.setString(6,null);
		this.pstmt.setString(7, this.proprietaire.getMatricule());
		this.pstmt.setString(8, this.sn);
		this.pstmt.executeUpdate();
		this.pstmt.close();
		
	}
}
