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

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.repository.ImprimanteRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheImprimante;
import gestionParcInfo.view.tab.ImprimanteTab;

public class ImprimanteController implements ActionListener, WindowListener, MouseListener {

	private ImprimanteTab imprimanteTab;
	private Imprimantes imprimantes;
	private FicheImprimante ficheImprimante;
	private Ordinateurs ordinateurs;
	private Imprimante imprimante;
	public ImprimanteController(ImprimanteTab imprimanteTab,Imprimantes imprimantes,Ordinateurs ordinateurs) {
		this.ordinateurs = ordinateurs;
		this.imprimanteTab = imprimanteTab;
		this.imprimantes = imprimantes;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == imprimanteTab.getBtnAjouter()) {
				System.out.println("Ajouter Imprimante");
				
				//Création du formulaire
				if (this.ficheImprimante == null) {
					this.ficheImprimante = new FicheImprimante(Fiche.State.CREATION);
					ficheImprimante.setVisible(true);
					
					//Ajout des listeners
					this.ficheImprimante.addWindowListener(this);
					this.ficheImprimante.getBtnSauver().addActionListener(this);
					this.ficheImprimante.getBtnDeconnecter().addActionListener(this.ficheImprimante);
				
				} else {
					this.ficheImprimante.toFront();
				}
		} else if (e.getSource() == imprimanteTab.getBtnSupprimer()) {
		  try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				ImprimanteRepository ordiRepo = new ImprimanteRepository(conn);
				System.out.println("Supprimer Imprimante");
				
				for (String sni : this.imprimanteTab.getSNsImprimanteSelected()) {
					
					//Récupération de l'ordinateur dans la base
					System.out.println("Suppression : " + sni);
					Imprimante currentImprimante = imprimantes.findBySN(sni);
					//Suppression de l'ordinateur et persistance dans la base
					currentImprimante.remove(conn);
					
					//Mise à Suppression du modèle des ordinateurs
					this.imprimantes.removeItem(currentImprimante);
				}
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == this.ficheImprimante.getBtnSauver() 
		    && (this.ficheImprimante.getCurrentState() == Fiche.State.CREATION 
		    || this.ficheImprimante.getCurrentState() == Fiche.State.MODIFICATION)) {
			System.out.println("Sauver imprimante");

			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Imprimante imprimante = null;
				
				if (this.ficheImprimante.getCurrentState() == Fiche.State.CREATION) {
					imprimante = new Imprimante(this.ficheImprimante.getSN(), this.ficheImprimante.getDesignation(),this.ficheImprimante.getResolution());

					
					//Persistance de l'ordinateur et ajout au modèle
					imprimante.create(conn);
					imprimantes.addItem(imprimante);
					
				}else if (this.ficheImprimante.getCurrentState() == Fiche.State.MODIFICATION) {
					imprimante = this.imprimantes.findBySN(this.ficheImprimante.getSN());
				
				
				imprimante.setDesignation(this.ficheImprimante.getDesignation());
				imprimante.setResolution(this.ficheImprimante.getResolution());
				
				for (Ordinateur ordinateur : this.ficheImprimante.getDisconnectedOrdinateurs()) {
					ordinateur.setImprimante(null);
					ordinateur.update(conn);
					
				}
				//Persistance de l'ordinateur et ajout au modèle
				imprimante.update(conn);
				imprimantes.updateItem(imprimante);
				
				conn.close();
				this.ficheImprimante.dispose();
				this.ficheImprimante = null;
				}
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
	public void windowClosed(WindowEvent arg0) {
		if (arg0.getSource() == this.ficheImprimante) {
			this.ficheImprimante = null;
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
		if (e.getSource() == this.imprimanteTab.getTableImprimante()) {
			if (e.getClickCount() == 2) {
				System.out.println("DoubleClick on table imprimante");
				//Création du formulaire
				if (this.ficheImprimante == null) {
					Imprimante imprimante = this.imprimantes.findBySN(this.imprimanteTab.getSNImprimanteClicked());
					System.out.println(imprimante.getSn());
					this.ficheImprimante = new FicheImprimante(Fiche.State.VISUALISATION,imprimante,this.ordinateurs,this.imprimantes);
					ficheImprimante.setVisible(true);
					
					//Ajout des listeners
					this.ficheImprimante.addWindowListener(this);		
					this.ficheImprimante.getBtnSauver().addActionListener(this);
					this.ficheImprimante.getBtnDeconnecter().addActionListener(this.ficheImprimante);
				}else {
					this.ficheImprimante.toFront();
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
