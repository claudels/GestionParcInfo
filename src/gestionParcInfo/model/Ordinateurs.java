package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;

public class Ordinateurs extends ModelList<Ordinateur> {
	
	/**
	 * Temps avant le changement d'un ordinateur.
	 */
	private static final int tempsChangementOrdinateurAnnees = 4;
	private static final int tempsChangementOrdinateurJours = 0;
	
	/**
	 * Temps de restituion d'un ancien ordinateur
	 */
	private static final int tempsRestitutionOrdinateurJours = 14;
	
	/**
	 * Temps changement ordinateur converti en millisecondes
	 */
	private static final long timeInMsBeforeChange = Ordinateurs.tempsChangementOrdinateurAnnees * (31536000000L)
			+ Ordinateurs.tempsChangementOrdinateurJours * (86400000L);
	
	/**
	 * Temps restitution ordinateur converti en millisecondes
	 */
	private static final long timeInMsBeforeReturn =  Ordinateurs.tempsRestitutionOrdinateurJours * (86400000L);
	
	public Ordinateurs(ArrayList<Ordinateur> ordinateurs, Observer obs) {
		super(ordinateurs, obs);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Renvoie un ordinateur selon son numéro de série
	 * @param sno Numéro de série
	 * @return Ordinateur
	 */
	public Ordinateur findBySN(String sno){
		Ordinateur result = null;
		
		for(Ordinateur ordi : this.getItems()) {
			if(ordi.getSn().equals(sno))
				result = ordi;
		}
		
		return result;
	}
	
	/**
	 * Vérifier si un ordinateur doit être rendu
	 * @param ordinateur Ordinateur sur lequel effectuer la vérification
	 * @return true si l'ordinateur doit être rendu, false sinon
	 */
	public boolean ordinateurMustBeChanged(Ordinateur ordinateur) {
		boolean result = false;
		
		if(ordinateur.getProprietaire() != null && ordinateur.getDateAttribution() != null) {
			if(ordinateur.getDateAttribution() != null && 
					ordinateur.getDateRestitution() != null && 
					this.findOrdinateursByEmploye(ordinateur.getProprietaire()).size() == 1 &&
					(System.currentTimeMillis() - ordinateur.getDateAttribution().getTime()) >= timeInMsBeforeChange) {
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Cherche les d'ordinateurs assignés à un Employé
	 * @param employe
	 * @return La liste des ordinateurs
	 */
	public List<Ordinateur> findOrdinateursByEmploye(Employe employe) {
		return this.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getProprietaire() != null && employe.getMatricule().equals(ordinateur.getProprietaire().getMatricule()))
				.collect(Collectors.toList());
	}
	

	/**
	 * Détermine si un ordinateur doit être rendu. Un employé à 15 jours pour rendre un pc après l'attribution d'un nouveau
	 * @param ordinateur
	 * @return true si l'ordinateur doit être rendu, false sinon
	 */
	public boolean ordinateurMustBeReturned(Ordinateur ordinateur) {
		boolean result = false;
		
		Employe employe = ordinateur.getProprietaire();
		
		//On cherche si l'employé à un PC qui est valide (ne devant pas être changé) et si ca fait plus de 15 jours qu'il l'a
		//et que l'ordinateur passé en paramètre n'est pas restitué; alors l'ordinateur en paramètre doit ête restitué
		if(employe != null && ordinateur.getDateAttribution() != null) {
			for(Ordinateur ordinateurEmploye : findOrdinateursByEmploye(employe)) {
				if(!this.ordinateurMustBeChanged(ordinateurEmploye) &&
						ordinateur.getDateRestitution() == null &&
						ordinateurEmploye.getDateAttribution() != null &&
						(System.currentTimeMillis() - ordinateurEmploye.getDateAttribution().getTime()) >= timeInMsBeforeReturn) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
