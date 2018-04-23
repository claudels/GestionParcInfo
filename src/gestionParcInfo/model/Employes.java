package gestionParcInfo.model;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Mod�le des employ�s.
 * @author Sebastien Claudel
 */
public class Employes extends ModelList<Employe> {
	
	private Ordinateurs ordinateurs;

	/**
	 * Constructeur du mod�le des Employes.
	 * @param employes Employes existants
	 * @param ordinateurs Mod�le des ordinateurs
	 * @param obs Observateur du mod�le
	 */
	public Employes(ArrayList<Employe> employes, Ordinateurs ordinateurs, Observer obs) {
		super(employes, obs);
		
		this.ordinateurs = ordinateurs;

		this.setChanged();
		this.notifyObservers();
	}
	
	public int getNbOrdisOfEmploye(Employe employe) {
		return ordinateurs.findOrdinateursByEmploye(employe).size();
	}
	
	/**
	 * Compte le nombre d'ordinateur � retourner d'un employ�.
	 * @param employe Employe pour lequel compter.
	 * @return Nombre d'ordinateur � changer
	 */
	public int getNbOrdisARetournerOfEmploye(Employe employe) {
		int nbOrdisAChanger = 0;
		
		List<Ordinateur> ordinateursOfEmploye = ordinateurs.findOrdinateursByEmploye(employe);
		
		for (Ordinateur ordinateur : ordinateursOfEmploye) {
			if (ordinateurs.ordinateurMustBeReturned(ordinateur)) {
				nbOrdisAChanger++;
			}	
		}
		
		return nbOrdisAChanger;
	}
	
	/**
	 * Chercher un employ� par matricule.
	 * @param matricule Matricule de l'employ�
	 * @return Employe
	 */
	public Employe findByMatricule(String matricule) {
		Employe result = null;
		
		for (Employe employe : this.getItems()) {
			if (employe.getMatricule().equals(matricule)) {
				result = employe;
			}
		}
		
		return result;
	}
}
