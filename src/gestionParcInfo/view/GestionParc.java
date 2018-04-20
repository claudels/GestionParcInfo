package gestionParcInfo.view;

import gestionParcInfo.view.tab.AlerteTab;
import gestionParcInfo.view.tab.EmployeTab;
import gestionParcInfo.view.tab.ImprimanteTab;
import gestionParcInfo.view.tab.OrdinateurTab;
import gestionParcInfo.view.tab.ServeurTab;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.border.EmptyBorder;

/**
 * Vue prinicpale de l'application.
 * @author Sebastien Claudel
 *
 */
public class GestionParc extends JFrame {

  private static final long serialVersionUID = 1L;

  private JPanel contentPane;
	
	/**
	 * Création de la fenêtre principale du programme.
	 * @param ordiTab Onglet des ordinateurs
	 * @param imprTab Onglet des imprimantes
	 * @param servTab Onglet des serveurs
	 * @param emplTab Onglet des employés
	 * @param alerteTab Onglet des alertes
	 */
	public GestionParc(OrdinateurTab ordiTab, ImprimanteTab imprTab, ServeurTab servTab, EmployeTab emplTab, AlerteTab alerteTab) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 713, 450);
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Ordinateurs", null,ordiTab, null);		
		tabbedPane.addTab("Imprimantes", null, imprTab, null);		
		tabbedPane.addTab("Serveurs", null, servTab, null);
		tabbedPane.addTab("Employes", null, emplTab, null);
		tabbedPane.addTab("Alertes", null, alerteTab, null);
	}
	
}
