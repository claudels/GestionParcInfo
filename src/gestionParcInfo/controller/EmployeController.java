package gestionParcInfo.controller;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Ordinateurs;
import gestionParcInfo.repository.EmployeRepository;
import gestionParcInfo.view.AlerterEmploye;
import gestionParcInfo.view.fiche.Fiche;
import gestionParcInfo.view.fiche.FicheEmploye;
import gestionParcInfo.view.tab.EmployeTab;

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

public class EmployeController implements ActionListener, WindowListener, MouseListener {
	
	private EmployeTab employeTab;
	private Employes employes;
	private FicheEmploye ficheEmploye;
	private Ordinateurs ordinateurs;
	private AlerterEmploye alerterEmployeForm;
	private Alertes alertes;
	
	/**
	 * Constructeur du controleur des employés.
	 * @param employeTab Onglet des employés
	 * @param employes Modèle des employés
	 * @param ordinateurs Modèle des ordinateurs
	 */

	public EmployeController(EmployeTab employeTab, Employes employes,Ordinateurs ordinateurs,Alertes alertes) {
		this.employeTab = employeTab;
		this.employes = employes;
		this.ordinateurs = ordinateurs;
		this.alertes = alertes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
  	if (e.getSource() == employeTab.getBtnAjouter()) {
  		System.out.println("Ajouter Employe");
  		
  		//Création du formulaire
  		if (this.ficheEmploye == null) {
  			this.ficheEmploye = new FicheEmploye(Fiche.State.CREATION);
  			ficheEmploye.setVisible(true);
  			
  			//Ajout des listeners
  			this.ficheEmploye.addWindowListener(this);
  			this.ficheEmploye.getBtnSauver().addActionListener(this);
  			this.ficheEmploye.getAssignerOrdinateur().addActionListener(this.ficheEmploye);
  		
  			
  		
  		} else {
  			this.ficheEmploye.toFront();
  		}
  	} else if (e.getSource() == employeTab.getBtnAlerter()) {
			System.out.println("Alerter employé");
		} else if (e.getSource() == employeTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				EmployeRepository employeRepo = new EmployeRepository(conn);
				System.out.println("Supprimer employe");
				
				for (String matricule : this.employeTab.getMatriculeEmployeSelected()) {
					
					//Récupération de l'employe dans la base
					System.out.println("Suppression : " + matricule);
					Employe currentEmploye = employes.findByMatricule(matricule);
					
					//Suppression de l'employe et persistance dans la base
					currentEmploye.remove(conn);
					
					//Mise à Suppression du modèle des employés
					this.employes.removeItem(currentEmploye);
				}
				conn.close();
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		} else if (e.getSource() == this.ficheEmploye.getBtnSauver() 
		    && (this.ficheEmploye.getCurrentState() == Fiche.State.CREATION 
		    || this.ficheEmploye.getCurrentState() == Fiche.State.MODIFICATION)) {
			System.out.println("Sauver Employe");

			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Employe employe = null;
				
				if (this.ficheEmploye.getCurrentState() == Fiche.State.CREATION) {
					employe = new Employe(this.ficheEmploye.getMatricule(), this.ficheEmploye.getNom(),this.ficheEmploye.getPrenom(),this.ficheEmploye.getEmail());

					//Persistance de l'ordinateur et ajout au modèle
					employe.create(conn);
					employes.addItem(employe);
					
				} else if (this.ficheEmploye.getCurrentState() == Fiche.State.MODIFICATION) {
					employe = this.employes.findByMatricule(this.ficheEmploye.getMatricule());
					employe.setNom(this.ficheEmploye.getNom());
					employe.setPrenom(this.ficheEmploye.getPrenom());
					employe.setEmail(this.ficheEmploye.getEmail());
					
					for (Ordinateur ordinateur : this.ficheEmploye.getAssignedOrdinateurs()) {
						ordinateur.setProprietaire(employe);
						ordinateur.setDateAttribution(new Date());
						System.out.println("Demande d'assignation");
						ordinateur.update(conn);
						ordinateurs.updateItem(ordinateur);
					}
					
					employe.update(conn);
					employes.updateItem(employe);
					
					conn.close();
					this.ficheEmploye.dispose();
					this.ficheEmploye = null;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.alerterEmployeForm.getBtnAlerter()) {
			System.out.println("Alerter Employe");
			this.alerterEmployeForm.addWindowListener(this);
			this.alerterEmployeForm.getBtnAlerter().addActionListener(this);
			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Alerte alerte;

					for (Employe employe : this.employeTab.getSelectedEmploye()) {
						alerte = new Alerte(alerterEmployeForm.getMessage(),employe);
						alerte.create(conn);
						alertes.addItem(alerte);
					}
								
					conn.close();
					this.ficheEmploye.dispose();
					this.ficheEmploye = null;
				
		
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
		if (arg0.getSource() == this.ficheEmploye) {
			this.ficheEmploye = null;
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
		
		if (e.getSource() == this.employeTab.getTableEmploye()) {
			System.out.println("Clicked");
			if (e.getClickCount() == 2) {
				System.out.println("DoubleClick on table Employe");
				//Création du formulaire
				if (this.ficheEmploye == null) {
					Employe employe = this.employes.findByMatricule(this.employeTab.getMatriculeEmployeClicked());
					
					System.out.println(employe.getMatricule());
					this.ficheEmploye = new FicheEmploye(Fiche.State.VISUALISATION, employe, this.employes,this.ordinateurs);
					ficheEmploye.setVisible(true);
					
					//Ajout des listeners
					this.ficheEmploye.addWindowListener(this);
					this.ficheEmploye.getBtnSauver().addActionListener(this);
					this.ficheEmploye.getAssignerOrdinateur().addActionListener(this.ficheEmploye);
					this.alerterEmployeForm.addWindowListener(this);
					this.alerterEmployeForm.getBtnAlerter().addActionListener(this);
					
				} else {
					this.ficheEmploye.toFront();
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
