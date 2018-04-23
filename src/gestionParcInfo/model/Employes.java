package gestionParcInfo.model;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Modèle des employés.
 * @author Sebastien Claudel
 */
public class Employes extends ModelList<Employe> {
	
	private Ordinateurs ordinateurs;

	/**
	 * Constructeur du modèle des Employes.
	 * @param employes Employes existants
	 * @param ordinateurs Modèle des ordinateurs
	 * @param obs Observateur du modèle
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
	 * Compte le nombre d'ordinateur à retourner d'un employé.
	 * @param employe Employe pour lequel compter.
	 * @return Nombre d'ordinateur à changer
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
	 * Chercher un employé par matricule.
	 * @param matricule Matricule de l'employé
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
