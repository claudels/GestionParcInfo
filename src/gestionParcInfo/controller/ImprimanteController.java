package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Imprimantes;
import gestionParcInfo.repository.ImprimanteRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheImprimante;
import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.ImprimanteTab;

public class ImprimanteController implements ActionListener, WindowListener {

	private ImprimanteTab imprimanteTab;
	private Imprimantes imprimantes;
	private FicheImprimante ficheImprimante;
	public ImprimanteController(ImprimanteTab imprimanteTab,Imprimantes imprimantes) {
		this.imprimanteTab = imprimanteTab;
		this.imprimantes = imprimantes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == imprimanteTab.getBtnAjouter()) {
				System.out.println("Ajouter Imprimante");
				
				//Création du formulaire
				if(this.ficheImprimante == null) {
					this.ficheImprimante = new FicheImprimante(Fiche.State.MODIFICATION);
					ficheImprimante.setVisible(true);
					
					//Ajout des listeners
					this.ficheImprimante.addWindowListener(this);
				
				}else {
					this.ficheImprimante.toFront();
				}
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
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if(arg0.getSource() == this.ficheImprimante)
			this.ficheImprimante = null;
		
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
