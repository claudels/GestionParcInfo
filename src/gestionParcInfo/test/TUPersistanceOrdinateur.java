package gestionParcInfo.test;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.ImprimanteRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class TUPersistanceOrdinateur {
	
	
	public void TU_Create_Ordinateur(Connection conn) throws SQLException {
		
			
		EmployeRepository employerepo = new EmployeRepository(conn);
		ImprimanteRepository imprrepo = new ImprimanteRepository(conn);
		
		Imprimante impr = imprrepo.findBySn("1");
		Employe employe = employerepo.findByMatricule("DUPOMA");
		
		
		Ordinateur ordi = new Ordinateur("1503","TestPersist",50,(float) 3.2);
		ordi.setCpu(55);
		ordi.setImprimante(impr);
		ordi.setProprietaire(employe);
		ordi.setRam(4);
		try {
			ordi.setDateAttribution(Ordinateur.dateFormatterJavaToOracle.parse("14/11/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ordi.create(conn);
			
	}
	
	public void TU_Update_Ordinateur(Connection conn) throws SQLException {
		
		EmployeRepository employerepo = new EmployeRepository(conn);
		ImprimanteRepository imprrepo = new ImprimanteRepository(conn);
		
		Imprimante impr = imprrepo.findBySn("1");
		Employe employe = employerepo.findByMatricule("DUPOMA");
		
		
		Ordinateur ordi = new Ordinateur("105","TestPersistUpdate2",60,(float) 3.2);
		ordi.setCpu(55);
		ordi.setImprimante(impr);
		ordi.setProprietaire(employe);
		ordi.setRam(4);
		try {
			ordi.setDateAttribution(Ordinateur.dateFormatterJavaToOracle.parse("14/11/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ordi.update(conn);
	}
	
	public void TU_Remove_Ordinateur(Connection conn) throws SQLException {
		
		//Employe employe = new Employe("DUPOMA1","Dupont", "Martine", "dupontmar@email.com");
	
		EmployeRepository employerepo = new EmployeRepository(conn);
		ImprimanteRepository imprrepo = new ImprimanteRepository(conn);
		
		Imprimante impr = imprrepo.findBySn("1");
		Employe employe = employerepo.findByMatricule("DUPOMA");
		
		
		Ordinateur ordi = new Ordinateur("1503","TestPersistUpdate",60,(float) 3.2);
		ordi.setCpu(55);
		ordi.setImprimante(impr);
		ordi.setProprietaire(employe);
		ordi.setRam(4);
		try {
			ordi.setDateAttribution(Ordinateur.dateFormatterJavaToOracle.parse("14/11/1998"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ordi.remove(conn);
	}
	
	
	
}
