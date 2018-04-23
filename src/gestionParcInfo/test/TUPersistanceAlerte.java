package gestionParcInfo.test;

import java.sql.Connection;
import java.sql.SQLException;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.repository.EmployeRepository;

public class TUPersistanceAlerte {
	
	public void TU_Create_Alerte(Connection conn) throws SQLException {
	
		EmployeRepository employerepo = new EmployeRepository(conn);
		Employe employe = employerepo.findByMatricule("DUPOMA");
		Alerte alerte = new Alerte("TestPersist",employe);
		alerte.create(conn);
			
	}
	
	public void TU_Update_Alerte(Connection conn) throws SQLException {
		
		EmployeRepository employerepo = new EmployeRepository(conn);
		Employe employe = employerepo.findByMatricule("DUPOMA");
		Alerte alerte = new Alerte(94,"TestPersist5",employe);
		alerte.update(conn);
		
	}
	
	public void TU_Remove_Alerte(Connection conn) throws SQLException {
		EmployeRepository employerepo = new EmployeRepository(conn);
		Employe employe = employerepo.findByMatricule("DUPOMA");
		Alerte alerte = new Alerte(94,"TestPersist",employe);
		alerte.remove(conn);
			
	}

}
