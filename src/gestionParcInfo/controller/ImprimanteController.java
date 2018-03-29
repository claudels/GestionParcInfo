package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.repository.ImprimanteRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.tab.ImprimanteTab;

public class ImprimanteController implements ActionListener {

	private ImprimanteTab imprimanteTab;
	private Imprimantes imprimantes;
	public ImprimanteController(ImprimanteTab imprimanteTab,Imprimantes imprimantes) {
		this.imprimanteTab = imprimanteTab;
		this.imprimantes = imprimantes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == imprimanteTab.getBtnAjouter()) {
			System.out.println("Ajout imprimante");
		}else if(e.getSource() == imprimanteTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				ImprimanteRepository ordiRepo = new ImprimanteRepository(conn);
				System.out.println("Supprimer Imprimante");
				
				for(String sni : this.imprimanteTab.getSNsImprimanteSelected()) {
					
					//Récupération de l'ordinateur dans la base
					System.out.println("Suppression : " + sni);
					Imprimante currentImprimante = imprimantes.findBySN(sni);
					//Suppression de l'ordinateur et persistance dans la base
					currentImprimante.remove(conn);
					
					//Mise à Suppression du modèle des ordinateurs
					this.imprimantes.removeItem(currentImprimante);
				}
				conn.close();
			}catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		}
	}

}
