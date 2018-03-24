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
	
	private List<Ordinateur> ordinateursAChanger;
	private List<Ordinateur> ordinateursARetourner;
	
	public Ordinateurs(ArrayList<Ordinateur> ordinateurs, Observer obs) {
		super(ordinateurs, obs);
		
		this.ordinateursAChanger = new ArrayList<>();
		this.ordinateursARetourner = new ArrayList<>();
		
		//On initialise les fonctionnalités supplémentaires du modèle
		for(Ordinateur ordinateur: this.getItems()) {
			if(this.ordinateurMustBeChanged(ordinateur))
				this.ordinateursAChanger.add(ordinateur);
			else if(this.ordinateurMustBeReturned(ordinateur))
				this.ordinateursARetourner.add(ordinateur);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public void addItem(Ordinateur item) {
		if(this.ordinateurMustBeChanged(item))
			this.ordinateursAChanger.add(item);
		else if(this.ordinateurMustBeReturned(item))
			this.ordinateursARetourner.add(item);
		
		super.addItem(item);
	}
	
	@Override
	public void updateItem(Ordinateur item) throws IndexOutOfBoundsException {
		boolean mustBeChanged = this.ordinateurMustBeChanged(item);
		boolean mustBeReturned = this.ordinateurMustBeReturned(item);
		
		if(mustBeChanged && !this.ordinateursAChanger.contains(item))
			this.ordinateursAChanger.add(item);
		if(!mustBeChanged && this.ordinateursAChanger.contains(item))
			this.ordinateursAChanger.remove(item);
		if(mustBeReturned && !this.ordinateursARetourner.contains(item))
			this.ordinateursARetourner.add(item);
		if(!mustBeReturned && this.ordinateursARetourner.contains(item))
			this.ordinateursARetourner.remove(item);
			
		super.updateItem(item);
	}
	
	@Override
	public boolean removeItem(Ordinateur item) {
			this.ordinateursAChanger.remove(item);
			this.ordinateursARetourner.remove(item);
		
		return super.removeItem(item);
	}
	
	public Ordinateur findBySN(String sno){
		Ordinateur result = null;
		
		for(Ordinateur ordi : this.getItems()) {
			if(ordi.getSn().equals(sno))
				result = ordi;
		}
		
		return result;
	}
	
	public List<Ordinateur> getOrdinateursAChanger() {
		return ordinateursAChanger;
	}
	
	public List<Ordinateur> getOrdinateursARetourner() {
		return ordinateursARetourner;
	}
	
	/**
	 * Vérifier si un ordinateur doit être rendu
	 * @param ordinateur Ordinateur sur lequel effectuer la vérification
	 * @return true si l'ordinateur doit être rendu, false sinon
	 */
	private boolean ordinateurMustBeChanged(Ordinateur ordinateur) {
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
	 * Compte le nombre d'ordinateurs assignés à un Employé
	 * @param employe
	 * @return Nombre d'ordinateurs
	 */
	private List<Ordinateur> findOrdinateursByEmploye(Employe employe) {
		return this.getItems()
				.parallelStream()
				.filter(ordinateur -> employe.equals(ordinateur.getProprietaire()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Détermine si un ordinateur doit être rendu. Un employé à 15 jours pour rendre un pc après l'attribution d'un nouveau
	 * @param ordinateur
	 * @return true si l'ordinateur doit être rendu, false sinon
	 */
	private boolean ordinateurMustBeReturned(Ordinateur ordinateur) {
		boolean result = false;
		
		Employe employe = ordinateur.getProprietaire();
		
		//On cherche si l'employé à un PC qui est valide (ne devant pas être changé) et si ca fait plus de 15 jours qu'il l'a
		//et que l'ordinateur passé en paramètre n'est pas restitué; alors l'ordinateur en paramètre doit ête restitué
		if(employe != null && ordinateur.getDateAttribution() != null) {
			for(Ordinateur ordinateurEmploye : findOrdinateursByEmploye(employe)) {
				if(!this.ordinateurMustBeChanged(ordinateurEmploye) &&
						ordinateur.getDateRestitution() == null &&
						(System.currentTimeMillis() - ordinateurEmploye.getDateAttribution().getTime()) >= timeInMsBeforeReturn) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
