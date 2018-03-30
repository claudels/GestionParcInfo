package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Employes;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.repository.OrdinateurRepository;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheEmploye;
import gestionParcInfo.view.fiche.FicheImprimante;
import gestionParcInfo.view.tab.EmployeTab;

public class EmployeController implements ActionListener, WindowListener {
	
	private EmployeTab employeTab;
	private Employes employes;
	private FicheEmploye ficheEmploye;
	
	public EmployeController(EmployeTab employeTab, Employes employes) {
		this.employeTab = employeTab;
		this.employes = employes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == employeTab.getBtnAjouter()) {
			System.out.println("Ajouter Employe");
			
			//Création du formulaire
			if(this.ficheEmploye == null) {
				this.ficheEmploye = new FicheEmploye(Fiche.State.MODIFICATION);
				ficheEmploye.setVisible(true);
				
				//Ajout des listeners
				this.ficheEmploye.addWindowListener(this);
			
			}else {
				this.ficheEmploye.toFront();
			}
		}
		else if(e.getSource() == employeTab.getBtnAlerter()) {
			System.out.println("Alerter employé");
		}
		else if(e.getSource() == employeTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				EmployeRepository employeRepo = new EmployeRepository(conn);
				System.out.println("Supprimer employe");
				
				for(String matricule : this.employeTab.getMatEmployeSelected()) {
					
					//Récupération de l'employe dans la base
					System.out.println("Suppression : " + matricule);
					Employe currentEmploye = employes.findByMatricule(matricule);
					
					//Suppression de l'employe et persistance dans la base
					currentEmploye.remove(conn);
					
					//Mise à Suppression du modèle des employés
					this.employes.removeItem(currentEmploye);
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
		if(arg0.getSource() == this.ficheEmploye)
			this.ficheEmploye = null;
		
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
