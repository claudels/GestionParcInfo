package gestionParcInfo.controller;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;
import gestionParcInfo.model.OrdinateurServeurLinks;
import gestionParcInfo.model.Serveurs;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheServeur;
import gestionParcInfo.view.tab.ServeurTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServeurController implements ActionListener, WindowListener, MouseListener {

	private ServeurTab servTab;
	private Serveurs serveurs;
	private FicheServeur ficheServeur;
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	/**
	 * Constructeur du controleur des serveurs.
	 * @param servTab Onglet des serveurs
	 * @param serveurs Modèle des serveurs
	 * @param ordinateurServeurLinks Modèle des liens entre les ordinateurs et les serveurs
	 */
	public ServeurController(ServeurTab servTab, Serveurs serveurs, OrdinateurServeurLinks ordinateurServeurLinks) {
		this.servTab = servTab;
		this.serveurs = serveurs;
		this.ordinateurServeurLinks = ordinateurServeurLinks;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.servTab.getBtnAJouter()) {
			System.out.println("Ajouter Serveur");
			
			//Création du formulaire
			if (this.ficheServeur == null) {
				this.ficheServeur = new FicheServeur(Fiche.State.CREATION);
				ficheServeur.setVisible(true);
				
				//Ajout des listeners
				this.ficheServeur.addWindowListener(this);
				this.ficheServeur.getBtnSauver().addActionListener(this);
			
			} else {
				this.ficheServeur.toFront();
			}
		} else if (e.getSource() == this.servTab.getBtnSupprimer()){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				
				
				for (String sns : this.servTab.getSNsServeursSelected()) {
					System.out.println("Suppression : " + sns);
					//Récupération de l'ordinateur dans la base
					Serveur currentServeur = serveurs.findBySn(sns);
					
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
		} else if (e.getSource() == this.ficheServeur.getBtnSauver()) {
			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Serveur serveur = null;
				
				if (this.ficheServeur.getCurrentState() == Fiche.State.CREATION) {
					serveur = new Serveur(this.ficheServeur.getSN(), this.ficheServeur.getDesignation(), this.ficheServeur.getMemoire());
					
					//Persistance du serveur et ajout au modèle
					serveur.create(conn);
					serveurs.addItem(serveur);
				} else if (this.ficheServeur.getCurrentState() == Fiche.State.MODIFICATION) {
					serveur = this.serveurs.findBySn(this.ficheServeur.getSN());
					
					//Suppression des liens à supprimer
					for (OrdinateurServeurLink ordinateurServeurLink : this.ficheServeur.getLinksToDelete()) {
						ordinateurServeurLink.remove(conn);
						this.ordinateurServeurLinks.removeItem(ordinateurServeurLink);
					}
				}
				
				serveur.setDesignation(this.ficheServeur.getDesignation());
				serveur.setMemoire(this.ficheServeur.getMemoire());
				
				//Persistance de l'ordinateur et ajout au modèle
				serveur.update(conn);
				serveurs.updateItem(serveur);
				
				
				conn.close();
				this.ficheServeur.dispose();
				this.ficheServeur = null;
			} catch (SQLException e1) {
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
		if (e.getSource() == this.ficheServeur) {
			this.ficheServeur = null;
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.servTab.getTblServeur()) {
			if (e.getClickCount() == 2) {
				System.out.println("DoubleClick on table serveur");
				//Création du formulaire
				if (this.ficheServeur == null) {
					Serveur serveur = this.serveurs.findBySn(this.servTab.getSnServeurClicked());
					
					this.ficheServeur = new FicheServeur(Fiche.State.VISUALISATION, serveur, this.serveurs, this.ordinateurServeurLinks);
					ficheServeur.setVisible(true);
					
					//Ajout des listeners
					this.ficheServeur.addWindowListener(this);
					this.ficheServeur.getBtnSauver().addActionListener(this);
				} else {
					this.ficheServeur.toFront();
				}
		   }
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
