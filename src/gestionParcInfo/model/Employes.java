package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;

public class Employes extends ModelList<Employe>{
	
	private Ordinateurs ordinateurs;

	public Employes(ArrayList<Employe> employes, Ordinateurs ordinateurs, Observer obs) {
		super(employes, obs);
		
		this.ordinateurs = ordinateurs;

		this.setChanged();
		this.notifyObservers();
	}
	
	public int getNbOrdisOfEmploye(Employe employe) {
		return ordinateurs.findOrdinateursByEmploye(employe).size();
	}
	
	public int getNbOrdisAChangerOfEmploye(Employe employe) {
		int nbOrdisAChanger = 0;
		
		List<Ordinateur> ordinateursOfEmploye = ordinateurs.findOrdinateursByEmploye(employe);
		
		for(Ordinateur ordinateur : ordinateursOfEmploye) {
			if(ordinateurs.ordinateurMustBeChanged(ordinateur)) {
				nbOrdisAChanger++;
			}	
		}
		
		return nbOrdisAChanger;
	}
	public Employe findByMatricule(String matricule){
		Employe result = null;
		
		for(Employe employe : this.getItems()) {
			if(employe.getMatricule().equals(matricule))
				result = employe;
		}
		
		return result;
	}
}
