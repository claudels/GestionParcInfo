package gestionParcInfo.controller;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Alerte;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.view.tab.AlerteTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Controleur des alertes.
 * @author Florian Lemarquand
 */
public class AlerteController implements ActionListener {
	
	private AlerteTab alerteTab;
	private Alertes alertes;
	
	/**
	 * Constructeur du controleur des alertes.
	 * @param alerteTab Onglet des alertes
	 * @param alertes alertes déjà existantes à insérer dans le modèle
	 */
	public AlerteController(AlerteTab alerteTab, Alertes alertes) {
		this.alerteTab = alerteTab;
		this.alertes = alertes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == alerteTab.getBtnSupprimer()) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
					Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
					System.out.println("Supprimer alerte");
					
					for (int code : this.alerteTab.getCodeAlerteSelected()) {
						
						//Récupération de l'ordinateur dans la base
						System.out.println("Suppression : " + code);
						Alerte currentAlerte = alertes.findByCode(code);
						
						//Suppression de l'ordinateur et persistance dans la base
						currentAlerte.remove(conn);
						
						//Mise à Suppression du modèle des ordinateurs
						this.alertes.removeItem(currentAlerte);
					}
					conn.close();
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
		 }
	}
}
