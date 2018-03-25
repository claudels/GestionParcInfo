package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.tab.EmployeTab;

public class EmployeController implements ActionListener {
	
	private EmployeTab employeTab;
	
	public EmployeController(EmployeTab employeTab) {
		this.employeTab = employeTab;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == employeTab.getBtnAjouter()) {
			System.out.println("Ajouter employ�");
		}
		else if(e.getSource() == employeTab.getBtnAlerter()) {
			System.out.println("Alerter employ�");
		}
		else if(e.getSource() == employeTab.getBtnSupprimer()) {
			System.out.println("Supprimer employ�");
		}
	}

}
