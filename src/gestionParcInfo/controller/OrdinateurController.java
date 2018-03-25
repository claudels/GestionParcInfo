package gestionParcInfo.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

public class OrdinateurController implements ActionListener{
	
	private OrdinateurTab ordiTab;
	private Ordinateurs ordinateurs;
	
	public OrdinateurController(OrdinateurTab ordiTab, Ordinateurs ordinateurs) {
		this.ordiTab = ordiTab;
		this.ordinateurs = ordinateurs;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == this.ordiTab.getBtnAjouter()) {
			System.out.println("Ajouter ordinateur");
			
			//Création du formulaire
			FicheOrdinateur ficheOrdi = new FicheOrdinateur(Fiche.State.CREATION);
			ficheOrdi.setVisible(true);
			
			//TODO: Le reste ?
		}else if(e.getSource() == this.ordiTab.getBtnRetourner()){			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
				
				for(String sno : this.ordiTab.getSNsOrdinateursSelected()) {
					System.out.println("Retour : " + sno);
					//Récupération de l'ordinateur dans la base
					Ordinateur currentOrdinateur = ordinateurs.findBySN(sno);
					
					//Mise à jour de l'ordinateur et persistance dans la base
					currentOrdinateur.setDateRestitution(new Date());
					currentOrdinateur.update(conn);
					
					//Mise à jour du modèle des ordinateurs
					this.ordinateurs.updateItem(currentOrdinateur);
				}
				
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == this.ordiTab.getBtnSupprimer()){
			System.out.println("Supprimer ordinateur");
			for(String sno : this.ordiTab.getSNsOrdinateursSelected()) {
				System.out.println("Suppression : " + sno);
				//TODO: Pour chaque SN_O, supprimer de la base l'ordi correspondant
				//TODO: Si la suppression s'est bien passée, on le supprime du modèle
			}
		}
	}
	
}
