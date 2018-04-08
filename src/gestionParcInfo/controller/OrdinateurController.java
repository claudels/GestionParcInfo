package gestionParcInfo.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map.Entry;

import javax.swing.JButton;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.ConnexionServeur;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheImprimante;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

public class OrdinateurController implements ActionListener, WindowListener, MouseListener{
	
	private OrdinateurTab ordiTab;
	private Ordinateurs ordinateurs;
	private Serveurs serveurs;
	private FicheOrdinateur ficheOrdinateur;
	private Employes employes;
	private OrdinateurServeurLinks ordinateurServeurLinks;
	private Imprimantes imprimantes;
	
	public OrdinateurController(OrdinateurTab ordiTab, Ordinateurs ordinateurs, Serveurs serveurs, Employes employes, OrdinateurServeurLinks ordinateurServeurLinks, Imprimantes imprimantes) {
		this.ordiTab = ordiTab;
		this.ordinateurs = ordinateurs;
		this.serveurs = serveurs;
		this.employes = employes;
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.imprimantes = imprimantes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == this.ordiTab.getBtnAjouter()) {
			System.out.println("Ajouter ordinateur");
			
			//Création du formulaire
			if(this.ficheOrdinateur == null) {
				this.ficheOrdinateur = new FicheOrdinateur(this.employes, this.ordinateurServeurLinks, this.serveurs, this.imprimantes);
				ficheOrdinateur.setVisible(true);
				
				//Ajout des listeners
				this.ficheOrdinateur.addWindowListener(this);
				this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnConnecterImprimante().addActionListener(this);
				this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this);
				this.ficheOrdinateur.getBtnDeconnecterImprimante().addActionListener(this);
				this.ficheOrdinateur.getBtnDeconnecterServeurs().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnSauver().addActionListener(this);
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
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				OrdinateurRepository ordiRepo = new OrdinateurRepository(conn);
				System.out.println("Supprimer ordinateur");
				
				for(String sno : this.ordiTab.getSNsOrdinateursSelected()) {
					
					//Récupération de l'ordinateur dans la base
					System.out.println("Suppression : " + sno);
					Ordinateur currentOrdinateur = ordinateurs.findBySN(sno);
					
					//Suppression de l'ordinateur et persistance dans la base
					currentOrdinateur.remove(conn);
					
					//Mise à Suppression du modèle des ordinateurs
					this.ordinateurs.removeItem(currentOrdinateur);
				}
				conn.close();
			}catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnDeconnecterImprimante()) {
			System.out.println("Déconnexion imprimante");
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnDeconnecterServeurs()) {
			System.out.println("Déconnexion serveur(s)");
		}
		else if(e.getSource() == this.ficheOrdinateur.getBtnSauver()) {
			System.out.println("Sauver ordinateur");
			
			switch(this.ficheOrdinateur.getCurrentState()) {
			case CREATION:
				if(this.ficheOrdinateur.getSN() != null && this.ficheOrdinateur.getDesignation() != null) {
					try {
						Connection conn;
						conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
						Ordinateur newOrdinateur = new Ordinateur(this.ficheOrdinateur.getSN(), this.ficheOrdinateur.getDesignation(), this.ficheOrdinateur.getRAM(), this.ficheOrdinateur.getCPU());
						
						if(this.ficheOrdinateur.getProprietaire() != null) {
							newOrdinateur.setProprietaire(this.ficheOrdinateur.getProprietaire());
							newOrdinateur.setDateAttribution(new Date());
						}
						
						//Persistance de l'ordinateur et ajout au modèle
						newOrdinateur.create(conn);
						ordinateurs.addItem(newOrdinateur);
						
						//Persistance des liens et ajouts au modèle
						for(Entry<Serveur, Integer> entry : this.ficheOrdinateur.getAddedLinks().entrySet()) {
							OrdinateurServeurLink newLink = new OrdinateurServeurLink(newOrdinateur, entry.getKey(), entry.getValue());
							newLink.create(conn);
							this.ordinateurServeurLinks.addItem(newLink);
							
						}
						
						conn.close();
						this.ficheOrdinateur.dispose();
						this.ficheOrdinateur = null;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			case MODIFICATION:
				if(this.ficheOrdinateur.getSN() != null && this.ficheOrdinateur.getDesignation() != null) {
					try {
						Connection conn;
						conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
						Ordinateur ordinateur = this.ordinateurs.findBySN(this.ficheOrdinateur.getSN());
						
						if(!ordinateur.getProprietaire().equals(this.ficheOrdinateur.getProprietaire())) {
							ordinateur.setProprietaire(this.ficheOrdinateur.getProprietaire());
							ordinateur.setDateAttribution(new Date());
							ordinateur.setDateRestitution(null);
						}
						
						ordinateur.setCpu(this.ficheOrdinateur.getCPU());
						ordinateur.setDesignation(this.ficheOrdinateur.getDesignation());
						ordinateur.setRam(this.ficheOrdinateur.getRAM());
						
						//Persistance de l'ordinateur et ajout au modèle
						ordinateur.update(conn);
						ordinateurs.updateItem(ordinateur);
						
						//Suppression des liens à supprimer
						for(Serveur serveur : this.ficheOrdinateur.getDeletedLinks()) {
							OrdinateurServeurLink linkToDelete = this.ordinateurServeurLinks.findBySNOAndSNS(ordinateur.getSn(), serveur.getSn());
							linkToDelete.remove(conn);
							this.ordinateurServeurLinks.removeItem(linkToDelete);
						}
						
						//Persistance des liens et ajouts au modèle
						for(Entry<Serveur, Integer> entry : this.ficheOrdinateur.getAddedLinks().entrySet()) {
							OrdinateurServeurLink newLink = new OrdinateurServeurLink(ordinateur, entry.getKey(), entry.getValue());
							newLink.create(conn);
							this.ordinateurServeurLinks.addItem(newLink);
						}
						
						conn.close();
						this.ficheOrdinateur.dispose();
						this.ficheOrdinateur = null;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			case VISUALISATION:
				break;
				
			}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.ordiTab.getTableOrdinateur())
			if (e.getClickCount() == 2) {
				System.out.println("DoubleClick on table ordinateur");
				//Création du formulaire
				if(this.ficheOrdinateur == null) {
					Ordinateur ordinateur = this.ordinateurs.findBySN(this.ordiTab.getSNOrdinateurClicked());
					
					this.ficheOrdinateur = new FicheOrdinateur(Fiche.State.VISUALISATION, ordinateur, this.employes, this.ordinateurServeurLinks, this.serveurs, this.ordinateurs, this.imprimantes);
					ficheOrdinateur.setVisible(true);
					
					//Ajout des listeners
					this.ficheOrdinateur.addWindowListener(this);
					this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnConnecterImprimante().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnDeconnecterImprimante().addActionListener(this);
					this.ficheOrdinateur.getBtnDeconnecterServeurs().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnSauver().addActionListener(this);
				}else {
					this.ficheOrdinateur.toFront();
				}
		   }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
