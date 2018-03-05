package Entity;

import java.sql.Connection;
import java.sql.SQLException;

import Interface.IEntity;

public class Alerte implements IEntity{
	private int id;
	private String contenu;
	private Employe employe;
	
	public Alerte() {
		// TODO Auto-generated constructor stub
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public Employe getEmploye() {
		return employe;
	}
	
	public int getId() {
		return id;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@Override
	public void persist(Connection conn) throws SQLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Connection conn) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
}
