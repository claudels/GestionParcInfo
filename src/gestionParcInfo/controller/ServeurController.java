package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.tab.ServeurTab;

public class ServeurController implements ActionListener {

	private ServeurTab servTab;
	
	public ServeurController(ServeurTab servTab) {
		this.servTab = servTab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.servTab.getBtnAJouter()) {
			System.out.println("Ajouter serveur");
		}else if(e.getSource() == this.servTab.getBtnSupprimer()){
			System.out.println("Supprimer serveur");
		}
	}

}
