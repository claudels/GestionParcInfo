package gestionParcInfo.controller;

import gestionParcInfo.GestionParcInfo;
import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.model.Alertes;
import gestionParcInfo.model.Employes;
import gestionParcInfo.model.Ordinateurs;
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

import javax.swing.JOptionPane;

/**
 * Controleur des employés
 * @author Florian Lemarquand
 */
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
  			this.ficheEmploye = new FicheEmploye(Fiche.State.CREATION, employes, ordinateurs);
  			ficheEmploye.setVisible(true);
  			
  			//Ajout des listeners
  			this.ficheEmploye.addWindowListener(this);
  			this.ficheEmploye.getAssignerOrdinateur().addActionListener(this.ficheEmploye);
  			this.ficheEmploye.getBtnSauver().addActionListener(this);
  		
  		} else {
  			this.ficheEmploye.toFront();
  		}
  	} else if (e.getSource() == employeTab.getBtnAlerter()) {
  	  if (this.employeTab.getMatriculeEmployeSelected().size() > 0) {
  			this.alerterEmployeForm = new AlerterEmploye();
  			this.alerterEmployeForm.setVisible(true);
  			this.alerterEmployeForm.getBtnAlerter().addActionListener(this);
  			this.alerterEmployeForm.addWindowListener(this);
  	  } else {
  	    JOptionPane.showMessageDialog(null, "Vous devez séléctionnez les employés avant de pouvoir leurs envoyer une Alerte.", "Attention", JOptionPane.WARNING_MESSAGE);
  	  }
			
		} else if (e.getSource() == employeTab.getBtnSupprimer()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				System.out.println("Supprimer employe");
				
				for (String matricule : this.employeTab.getMatriculeEmployeSelected()) {
					
					//Récupération de l'employe dans la base
					System.out.println("Suppression : " + matricule);
					Employe currentEmploye = employes.findByMatricule(matricule);
					
					//Desattribution des ordinateurs en relation avec l'employé
					for(Ordinateur currentOrdi : ordinateurs.findOrdinateursByEmploye(currentEmploye)) {
					  currentOrdi.setProprietaire(null);
					  ordinateurs.updateItem(currentOrdi);
					  currentOrdi.update(conn);
					}
					
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
		} else if (this.ficheEmploye != null 
		    && e.getSource() == this.ficheEmploye.getBtnSauver()
		    && (this.ficheEmploye.getCurrentState() == Fiche.State.CREATION 
		    || this.ficheEmploye.getCurrentState() == Fiche.State.MODIFICATION)
		    && this.ficheEmploye.validateData()) {
			System.out.println("Sauver Employe");

			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Employe employe = null;
				
				if (this.ficheEmploye.getCurrentState() == Fiche.State.CREATION) {
					employe = new Employe(this.ficheEmploye.getMatricule(), this.ficheEmploye.getNom(),this.ficheEmploye.getPrenom(),this.ficheEmploye.getEmail());

					//Persistance de employé et ajout au modèle
					employe.create(conn);
					employes.addItem(employe);
					
				} else if (this.ficheEmploye.getCurrentState() == Fiche.State.MODIFICATION) {
					employe = this.employes.findByMatricule(this.ficheEmploye.getMatricule());
					employe.setNom(this.ficheEmploye.getNom());
					employe.setPrenom(this.ficheEmploye.getPrenom());
					employe.setEmail(this.ficheEmploye.getEmail());
					
					employe.update(conn);
					employes.updateItem(employe);
					
					
				}
				
				for (Ordinateur ordinateur : this.ficheEmploye.getAssignedOrdinateurs()) {
          ordinateur.setProprietaire(employe);
          
          if(ordinateur.getDateAttribution() == null) {
            ordinateur.setDateAttribution(new Date());
          }
          
          ordinateur.update(conn);
          ordinateurs.updateItem(ordinateur);
        }
				
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.ficheEmploye.dispose();
      this.ficheEmploye = null;
		} else if (this.alerterEmployeForm != null && e.getSource() == this.alerterEmployeForm.getBtnAlerter()) {
			this.alerterEmployeForm.getBtnAlerter().addActionListener(this);
			Connection conn;
			try {
				conn = DriverManager.getConnection(GestionParcInfo.dbUrl, GestionParcInfo.dbUsername, GestionParcInfo.dbPassword);
				Alerte alerte;

					for (String matricule : this.employeTab.getMatriculeEmployeSelected()) {
						alerte = new Alerte(alerterEmployeForm.getMessage(),employes.findByMatricule(matricule));
						alerte.create(conn);
						alertes.addItem(alerte);
					}
								
					conn.close();
					this.alerterEmployeForm.dispose();
					this.alerterEmployeForm = null;
				
		
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
		if (e.getSource() == this.ficheEmploye) {
			this.ficheEmploye = null;
		} else if (e.getSource() == this.alerterEmployeForm) {
		  this.alerterEmployeForm = null;
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
