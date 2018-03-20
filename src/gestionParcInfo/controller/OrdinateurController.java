package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.tab.OrdinateurTab;

public class OrdinateurController implements ActionListener{

	public OrdinateurController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		if(arg0.getActionCommand() == OrdinateurTab.commandeAJoutOrdi) {
			System.out.println("Ajouter ordinateur");
		}else if(arg0.getActionCommand() == OrdinateurTab.commandeRetournerOrdi){
			System.out.println("Retourner ordinateur");
		}else if(arg0.getActionCommand() == OrdinateurTab.commandeSuppressionOrdi){
			System.out.println("Supprimer ordinateur");
		}
	}
	
}
