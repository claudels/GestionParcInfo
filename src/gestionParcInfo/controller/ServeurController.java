package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.tab.ServeurTab;

public class ServeurController implements ActionListener {

	private ServeurTab servTab;
	private Serveurs serveurs;
	
	public ServeurController(ServeurTab servTab, Serveurs serveurs) {
		this.servTab = servTab;
		this.serveurs = serveurs;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.servTab.getBtnAJouter()) {
			System.out.println("Ajouter serveur");
		}else if(e.getSource() == this.servTab.getBtnSupprimer()){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				
				
				for(String sns : this.servTab.getSNsServeursSelected()) {
					System.out.println("Suppression : " + sns);
					//Récupération de l'ordinateur dans la base
					Serveur currentServeur = serveurs.findBySN(sns);
					
					//Mise à jour de l'ordinateur et persistance dans la base	
					currentServeur.remove(conn);
					
					//Mise à jour du modèle des ordinateurs
					this.serveurs.removeItem(currentServeur);
				}
			
			conn.close();
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
	}
}
