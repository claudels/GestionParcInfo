package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.tab.EmployeTab;

public class EmployeController implements ActionListener {
	
	private EmployeTab employeTab;
	private Employes employes;
	
	public EmployeController(EmployeTab employeTab, Employes employes) {
		this.employeTab = employeTab;
		this.employes = employes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == employeTab.getBtnAjouter()) {
			System.out.println("Ajouter employé");
		}
		else if(e.getSource() == employeTab.getBtnAlerter()) {
			System.out.println("Alerter employé");
		}
		else if(e.getSource() == employeTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				EmployeRepository employeRepo = new EmployeRepository(conn);
				System.out.println("Supprimer employe");
				
				for(String matricule : this.employeTab.getMatEmployeSelected()) {
					
					//Récupération de l'employe dans la base
					System.out.println("Suppression : " + matricule);
					Employe currentEmploye = employes.findByMatricule(matricule);
					
					//Suppression de l'employe et persistance dans la base
					currentEmploye.remove(conn);
					
					//Mise à Suppression du modèle des employés
					this.employes.removeItem(currentEmploye);
				}
				conn.close();
			}catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		}
	}

}
