package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheImprimante;
import gestionParcInfo.view.fiche.FicheServeur;
import gestionParcInfo.view.tab.ServeurTab;

public class ServeurController implements ActionListener, WindowListener {

	private ServeurTab servTab;
	private Serveurs serveurs;
	private FicheServeur ficheServeur;
	
	public ServeurController(ServeurTab servTab, Serveurs serveurs) {
		this.servTab = servTab;
		this.serveurs = serveurs;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.servTab.getBtnAJouter()) {
			System.out.println("Ajouter Serveur");
			
			//Création du formulaire
			if(this.ficheServeur == null) {
				this.ficheServeur = new FicheServeur(Fiche.State.MODIFICATION);
				ficheServeur.setVisible(true);
				
				//Ajout des listeners
				this.ficheServeur.addWindowListener(this);
			
			}else {
				this.ficheServeur.toFront();
			}
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

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getSource() == this.ficheServeur)
			this.ficheServeur = null;
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
