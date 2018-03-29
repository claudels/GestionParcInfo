package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.repository.AlerteRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.tab.AlerteTab;
import gestionParcInfo.view.tab.EmployeTab;

public class AlerteController implements ActionListener {
	
	private AlerteTab alerteTab;
	private Alertes alertes;
	
	public AlerteController(AlerteTab alerteTab, Alertes alertes) {
		this.alerteTab = alerteTab;
		this.alertes = alertes;
	}

	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == alerteTab.getBtnSupprimer()) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
					Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
					AlerteRepository alerteRepo = new AlerteRepository(conn);
					System.out.println("Supprimer alerte");
					
					for(int code : this.alerteTab.getCodeAlerteSelected()) {
						
						//Récupération de l'ordinateur dans la base
						System.out.println("Suppression : " + code);
						Alerte currentAlerte = alertes.findByCode(code);
						
						//Suppression de l'ordinateur et persistance dans la base
						currentAlerte.remove(conn);
						
						//Mise à Suppression du modèle des ordinateurs
						this.alertes.removeItem(currentAlerte);
					}
					conn.close();
				}catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
	}
}
