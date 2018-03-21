package gestionParcInfo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionParcInfo.view.fiche.FicheOrdinateur;
import gestionParcInfo.view.tab.OrdinateurTab;

public class OrdinateurController implements ActionListener{
	
	private OrdinateurTab ordiTab;
	
	public OrdinateurController(OrdinateurTab ordiTab) {
		this.ordiTab = ordiTab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == this.ordiTab.getBtnAjouter()) {
			System.out.println("Ajouter ordinateur");
			
			//Création du formulaire
			FicheOrdinateur ficheOrdi = new FicheOrdinateur();
			ficheOrdi.setVisible(true);
			
			//TODO: Le reste ?
		}else if(e.getSource() == this.ordiTab.getBtnRetourner()){
			System.out.println("Retourner ordinateur");
			for(String sno : this.ordiTab.getSNsOrdinateursSelected()) {
				System.out.println("Retour : " + sno);
				//TODO: Pour chaque SN_O, Modifier la base pour qu'il soit retourné
				//TODO: Si la MAJ est ok, on met à jour le modèle
			}
		}else if(e.getSource() == this.ordiTab.getBtnSupprimer()){
			System.out.println("Supprimer ordinateur");
			for(String sno : this.ordiTab.getSNsOrdinateursSelected()) {
				System.out.println("Suppression : " + sno);
				//TODO: Pour chaque SN_O, supprimer de la base l'ordi correspondant
				//TODO: Si la suppression s'est bien passée, on le supprime du modèle
			}
		}
	}
	
}
