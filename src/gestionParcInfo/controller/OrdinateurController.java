package gestionParcInfo.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

public class OrdinateurController implements ActionListener, WindowListener{
	
	private OrdinateurTab ordiTab;
	private Ordinateurs ordinateurs;
	private Serveurs serveurs;
	private FicheOrdinateur ficheOrdinateur;
	
	public OrdinateurController(OrdinateurTab ordiTab, Ordinateurs ordinateurs, Serveurs serveurs) {
		this.ordiTab = ordiTab;
		this.ordinateurs = ordinateurs;
		this.serveurs = serveurs;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == this.ordiTab.getBtnAjouter()) {
			System.out.println("Ajouter ordinateur");
			
			//Création du formulaire
			if(this.ficheOrdinateur == null) {
				this.ficheOrdinateur = new FicheOrdinateur(Fiche.State.CREATION);
				ficheOrdinateur.setVisible(true);
				
				//Ajout des listeners
				this.ficheOrdinateur.addWindowListener(this);
				this.ficheOrdinateur.getBtnConnecterImprimante().addActionListener(this);
				this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this);
				this.ficheOrdinateur.getBtnDeconnecterImprimante().addActionListener(this);
				this.ficheOrdinateur.getBtnDeconnecterServeurs().addActionListener(this);
			}else {
				this.ficheOrdinateur.toFront();
			}
					
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
		}else if(e.getSource() == this.ficheOrdinateur.getBtnConnecterImprimante()) {
			System.out.println("Connexion imprimante");
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnConnecterServeurs()) {
			System.out.println("Connexion serveur(s)");
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnDeconnecterImprimante()) {
			System.out.println("Déconnexion imprimante");
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnDeconnecterServeurs()) {
			System.out.println("Déconnexion serveur(s)");
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if(arg0.getSource() == this.ficheOrdinateur)
			this.ficheOrdinateur = null;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
