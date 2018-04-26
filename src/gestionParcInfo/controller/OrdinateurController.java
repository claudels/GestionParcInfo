package gestionParcInfo.controller;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

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

/**
 * Controleur des ordinateurs.
 * @author Sebastien Claudel
 */
public class OrdinateurController implements ActionListener, WindowListener, MouseListener {
	
	private OrdinateurTab ordiTab;
	private Ordinateurs ordinateurs;
	private Serveurs serveurs;
	private FicheOrdinateur ficheOrdinateur;
	private Employes employes;
	private OrdinateurServeurLinks ordinateurServeurLinks;
	private Imprimantes imprimantes;
	
	/**
	 * Constructeur du controleur des ordinateurs.
	 * @param ordiTab Onglet des ordinateurs
	 * @param ordinateurs Modèle des ordinateurs
	 * @param serveurs Modèle des serveurs
	 * @param employes Modèle des employés
	 * @param ordinateurServeurLinks Modèle des liens
	 * @param imprimantes Modèle des imprimantes
	 */
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
		if (e.getSource() == this.ordiTab.getBtnAjouter()) {
			System.out.println("Ajouter ordinateur");
			
			//Création du formulaire
			if (this.ficheOrdinateur == null) {
				this.ficheOrdinateur = new FicheOrdinateur(Fiche.State.CREATION, this.employes, this.serveurs, this.imprimantes);
				ficheOrdinateur.setVisible(true);
				
				//Ajout des listeners
				this.ficheOrdinateur.addWindowListener(this);
				this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnConnecterImprimante().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnDeconnecterImprimante().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnDeconnecterServeurs().addActionListener(this.ficheOrdinateur);
				this.ficheOrdinateur.getBtnSauver().addActionListener(this);
			} else {
				this.ficheOrdinateur.toFront();
			}
					
		} else if (e.getSource() == this.ordiTab.getBtnRetourner()) {			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				
				for (String sno : this.ordiTab.getSNsOrdinateursSelected()) {
					//Récupération de l'ordinateur dans la base
					Ordinateur currentOrdinateur = ordinateurs.findBySn(sno);
				
					//Mise à jour de l'ordinateur et persistance dans la base
					Employe employe = currentOrdinateur.getProprietaire();
					currentOrdinateur.setDateRestitution(new Date());
					currentOrdinateur.setProprietaire(null);
					currentOrdinateur.update(conn);
					
					//Mise à jour des modèles
					this.employes.updateItem(employe);
					this.ordinateurs.updateItem(currentOrdinateur);
				}
				
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == this.ordiTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				System.out.println("Supprimer ordinateur");
				
				for (String sno : this.ordiTab.getSNsOrdinateursSelected()) {
					
					//Récupération de l'ordinateur dans la base
					System.out.println("Suppression : " + sno);
					Ordinateur currentOrdinateur = ordinateurs.findBySn(sno);
					
					//Suppression des liens
					for (OrdinateurServeurLink link : this.ordinateurServeurLinks.findBySno(sno)) {
					  this.ordinateurServeurLinks.removeItem(link);
					  this.serveurs.updateItem(link.getServeur());
					  link.remove(conn);
					}
					
					//Suppression de l'ordinateur et persistance dans la base
					currentOrdinateur.remove(conn);
					
					//Mise à Suppression du modèle des ordinateurs
					this.ordinateurs.removeItem(currentOrdinateur);
				}
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.ficheOrdinateur.getBtnSauver() 
		    && (this.ficheOrdinateur.getCurrentState() == Fiche.State.CREATION 
		    || this.ficheOrdinateur.getCurrentState() == Fiche.State.MODIFICATION)
		    && this.ficheOrdinateur.validateData()) {
			System.out.println("Sauver ordinateur");

			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Ordinateur ordinateur = null;
				
				if (this.ficheOrdinateur.getCurrentState() == Fiche.State.CREATION) {
					ordinateur = new Ordinateur(this.ficheOrdinateur.getSn(), this.ficheOrdinateur.getDesignation(), this.ficheOrdinateur.getRam(), this.ficheOrdinateur.getCpu());
					
					if (this.ficheOrdinateur.getProprietaire() != null) {
						ordinateur.setProprietaire(this.ficheOrdinateur.getProprietaire());
						ordinateur.setDateAttribution(new Date());
					}
					
					//Persistance de l'ordinateur et ajout au modèle
					ordinateur.create(conn);
					ordinateurs.addItem(ordinateur);
				} else if (this.ficheOrdinateur.getCurrentState() == Fiche.State.MODIFICATION) {
					ordinateur = this.ordinateurs.findBySn(this.ficheOrdinateur.getSn());
					
					if (ordinateur.getProprietaire() != this.ficheOrdinateur.getProprietaire()) {
						ordinateur.setProprietaire(this.ficheOrdinateur.getProprietaire());
					}
					
					//Suppression des liens à supprimer
					for (Serveur serveur : this.ficheOrdinateur.getDeletedLinks()) {
						OrdinateurServeurLink linkToDelete = this.ordinateurServeurLinks.findBySnoAndSns(ordinateur.getSn(), serveur.getSn());
						linkToDelete.remove(conn);
						this.ordinateurServeurLinks.removeItem(linkToDelete);
					}
				}
				
				ordinateur.setCpu(this.ficheOrdinateur.getCpu());
				ordinateur.setDesignation(this.ficheOrdinateur.getDesignation());
				ordinateur.setRam(this.ficheOrdinateur.getRam());
				ordinateur.setImprimante(this.ficheOrdinateur.getImprimante());
				
				//MAJ du modèle des imprimantes
				if (ordinateur.getImprimante() != null) {
				  this.imprimantes.updateItem(ordinateur.getImprimante());
				}
				
				//Persistance de l'ordinateur et ajout au modèle
				ordinateur.update(conn);
				ordinateurs.updateItem(ordinateur);
				
				//Persistance des liens et ajouts au modèle
				for (Entry<Serveur, Integer> entry : this.ficheOrdinateur.getAddedLinks().entrySet()) {
					OrdinateurServeurLink newLink = new OrdinateurServeurLink(ordinateur, entry.getKey(), entry.getValue());
					newLink.create(conn);
					this.ordinateurServeurLinks.addItem(newLink);
					this.serveurs.updateItem(entry.getKey());
				}
				
				conn.close();
				this.ficheOrdinateur.dispose();
				this.ficheOrdinateur = null;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (e.getSource() == this.ficheOrdinateur) {
			this.ficheOrdinateur = null;
		}
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
		if (e.getSource() == this.ordiTab.getTableOrdinateur()) {
			if (e.getClickCount() == 2) {
				System.out.println("DoubleClick on table ordinateur");
				//Création du formulaire
				if (this.ficheOrdinateur == null) {
					Ordinateur ordinateur = this.ordinateurs.findBySn(this.ordiTab.getSnOrdinateurClicked());
					
					this.ficheOrdinateur = new FicheOrdinateur(Fiche.State.VISUALISATION, ordinateur, this.employes, this.ordinateurServeurLinks, this.serveurs, this.ordinateurs, this.imprimantes);
					ficheOrdinateur.setVisible(true);
					
					//Ajout des listeners
					this.ficheOrdinateur.addWindowListener(this);
					this.ficheOrdinateur.getBtnConnecterServeurs().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnConnecterImprimante().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnDeconnecterImprimante().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnDeconnecterServeurs().addActionListener(this.ficheOrdinateur);
					this.ficheOrdinateur.getBtnSauver().addActionListener(this);
				} else {
					this.ficheOrdinateur.toFront();
				}
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
