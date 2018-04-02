package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.Ordinateur;
import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

public class Serveurs extends ModelList<Serveur> {
	
	/**
	 * Modèle qui réprésente les liens entre les serveurs et les ordinateurs
	 */
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	public Serveurs(ArrayList<Serveur> serveurs, OrdinateurServeurLinks ordinateurServeurLinks, Observer obs) {
		super(serveurs, obs);
		
		this.ordinateurServeurLinks = ordinateurServeurLinks;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Compte le nombre d'ordinateurs connectés à un serveur
	 * @param serveur Serveur pour lequel compter les ordinateurs
	 * @return long Nombre d'ordinateurs connectés
	 */
	public long countOrdinateursLinked(Serveur serveur) {
		return this.ordinateurServeurLinks.findBySNS(serveur.getSn()).size();
	}
	
	/**
	 * Calcule la charge théorique maximale du serveur (somme quotas ordinateurs/mémoire)
	 * @param serveur Serveur pour lequel calculer la charge
	 * @return Un pourcentage compris entre 0.0 et 1.0
	 */
	public double calculerChargeServeur(Serveur serveur) {	
		return (double)(((double)calculerSommeQuotas(serveur)/1024)/(double)serveur.getMemoire());
	}
	
	/**
	 * Retourne la somme des quotas des ordinateurs reliés au serveur (en MegaOctets
	 * @param serveur
	 * @return
	 */
	public long calculerSommeQuotas(Serveur serveur) {
		return this.ordinateurServeurLinks.findBySNS(serveur.getSn())
				.parallelStream()
				.mapToLong(ordinateurServeurLink -> ordinateurServeurLink.getQuota())
				.sum();
	}

	public Serveur findBySN(String sns) {
		Serveur result = null;
		
		for(Serveur serveur : this.getItems()) {
			if(serveur.getSn().equals(sns))
				result = serveur;
		}
		
		return result;
	}

	
}
