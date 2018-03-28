package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.tab.AlerteTab;
import gestionParcInfo.view.tab.EmployeTab;

public class AlerteController implements ActionListener {
	
	private AlerteTab alerteTab;
	
	public AlerteController(AlerteTab alerteTab) {
		this.alerteTab = alerteTab;
	}

	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == alerteTab.getBtnSupprimer()) {
				System.out.println("Supprimer alerte");
		 }
	}

}
