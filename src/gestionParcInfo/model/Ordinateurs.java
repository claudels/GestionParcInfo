package gestionParcInfo.model;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Mod�le des ordinateurs.
 * @author Sebastien Claudel
 */
public class Ordinateurs extends ModelList<Ordinateur> {
	
	/**
	 * Temps avant le changement d'un ordinateur.
	 */
	private static final int tempsChangementOrdinateurAnnees = 4;
	private static final int tempsChangementOrdinateurJours = 0;
	
	/**
	 * Temps de restituion d'un ancien ordinateur.
	 */
	private static final int tempsRestitutionOrdinateurJours = 14;
	
	/**
	 * Temps changement ordinateur converti en millisecondes.
	 */
	private static final long timeInMsBeforeChange = Ordinateurs.tempsChangementOrdinateurAnnees * (31536000000L)
			+ Ordinateurs.tempsChangementOrdinateurJours * (86400000L);
	
	/**
	 * Temps restitution ordinateur converti en millisecondes.
	 */
	private static final long timeInMsBeforeReturn =  Ordinateurs.tempsRestitutionOrdinateurJours * (86400000L);
	
	/**
	 * Cr�ation du mod�le des ordinateurs.
	 * @param ordinateurs Ordinateur d�j� existants � ajouter au mod�le
	 * @param obs Observateur du mod�le
	 */
	public Ordinateurs(ArrayList<Ordinateur> ordinateurs, Observer obs) {
		super(ordinateurs, obs);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Renvoie un ordinateur selon son num�ro de s�rie.
	 * @param sno Num�ro de s�rie
	 * @return Ordinateur
	 */
	public Ordinateur findBySn(String sno) {
		Ordinateur result = null;
		
		for (Ordinateur ordi : this.getItems()) {
			if (ordi.getSn().equals(sno)) {
				result = ordi;
			}
		}
		
		return result;
	}
	
	/**
	 * V�rifier si un ordinateur doit �tre chang�.
	 * @param ordinateur Ordinateur sur lequel effectuer la v�rification
	 * @return true si l'ordinateur doit �tre chang�, false sinon
	 */
	public boolean ordinateurMustBeChanged(Ordinateur ordinateur) {
		boolean result = false;
		
		if (ordinateur.getProprietaire() != null && ordinateur.getDateAttribution() != null) {
			if (ordinateur.getDateRestitution() != null 
					&& this.findOrdinateursByEmploye(ordinateur.getProprietaire()).size() == 1 
					&& (System.currentTimeMillis() - ordinateur.getDateAttribution().getTime()) >= timeInMsBeforeChange) {
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Cherche les d'ordinateurs assign�s � un Employ�.
	 * @param employe Employe pour lequel recherche les ordinateurs
	 * @return La liste des ordinateurs
	 */
	public List<Ordinateur> findOrdinateursByEmploye(Employe employe) {
		return this.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getProprietaire() != null 
				&& employe.getMatricule().equals(ordinateur.getProprietaire().getMatricule()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Liste les ordinateurs pouvant �tre asign�s.
	 * @return Ordinateur
	 */
	public List<Ordinateur> findOrdinateursAvailable() {
		return this.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getProprietaire() == null 
				&& ordinateur.getDateRestitution() == null)
				.collect(Collectors.toList());
	}
	
	/**
	 * Cherche les ordinateurs � partir de l'imprimante connect�e � ceux-ci.
	 * @param imprimante Imprimante
	 * @return List Liste des ordinateurs
	 */
	public List<Ordinateur> findOrdinateursByImprimante(Imprimante imprimante) {
		return this.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getImprimante() != null 
				&& imprimante.getSn().equals(ordinateur.getImprimante().getSn()))
				.collect(Collectors.toList());
	}
	

	/**
	 * D�termine si un ordinateur doit �tre rendu. Un employ� � 15 jours pour rendre un pc apr�s l'attribution d'un nouveau
	 * @param ordinateur Ordinateur 
	 * @return true si l'ordinateur doit �tre rendu, false sinon
	 */
	public boolean ordinateurMustBeReturned(Ordinateur ordinateur) {
		boolean result = false;
		
		Employe employe = ordinateur.getProprietaire();
		
		//On cherche si l'employ� � un PC qui est valide (ne devant pas �tre chang�) et si ca fait plus de 15 jours qu'il l'a
		//et que l'ordinateur pass� en param�tre n'est pas restitu�; alors l'ordinateur en param�tre doit �te restitu�
		if (employe != null && ordinateur.getDateAttribution() != null 
		    && (System.currentTimeMillis() - ordinateur.getDateAttribution().getTime()) >= timeInMsBeforeChange) {
			for (Ordinateur ordinateurEmploye : findOrdinateursByEmploye(employe)) {
				if (!ordinateur.equals(ordinateurEmploye) 
				    && !this.ordinateurMustBeChanged(ordinateurEmploye) 
						&& ordinateur.getDateRestitution() == null 
						&& ordinateurEmploye.getDateAttribution() != null 
						&& (System.currentTimeMillis() - ordinateurEmploye.getDateAttribution().getTime()) >= timeInMsBeforeReturn) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
