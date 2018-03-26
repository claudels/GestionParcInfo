package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.tab.ImprimanteTab;

public class ImprimanteController implements ActionListener {

	private ImprimanteTab imprimanteTab;
	
	public ImprimanteController(ImprimanteTab imprimanteTab) {
		this.imprimanteTab = imprimanteTab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == imprimanteTab.getBtnAjouter()) {
			System.out.println("Ajout imprimante");
		}else if(e.getSource() == imprimanteTab.getBtnSupprimer()) {
			System.out.println("Suppression imprimante");
		}
	}

}
