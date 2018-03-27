package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

public class Serveurs extends ModelList<Serveur> {
	
	/**
	 * Mod�le qui r�pr�sente les liens entre les serveurs et les ordinateurs
	 */
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	public Serveurs(ArrayList<Serveur> serveurs, OrdinateurServeurLinks ordinateurServeurLinks, Observer obs) {
		super(serveurs, obs);
		
		this.ordinateurServeurLinks = ordinateurServeurLinks;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Compte le nombre d'ordinateurs connect�s � un serveur
	 * @param serveur Serveur pour lequel compter les ordinateurs
	 * @return long Nombre d'ordinateurs connect�s
	 */
	public long countOrdinateursLinked(Serveur serveur) {
		return this.ordinateurServeurLinks.getItems()
		.parallelStream()
		.filter(ordinateurServeurLink -> ordinateurServeurLink.getServeur().getSn().equals(serveur.getSn()))
		.count();
	}
	
	/**
	 * Calcule la charge th�orique maximale du serveur (somme quotas ordinateurs/m�moire)
	 * @param serveur Serveur pour lequel calculer la charge
	 * @return Un pourcentage compris entre 0.0 et 1.0
	 */
	public double calculerChargeServeur(Serveur serveur) {
		long sommeQuotas = this.ordinateurServeurLinks.getItems()
				.parallelStream()
				.filter(ordinateurServeurLink -> ordinateurServeurLink.getServeur().getSn().equals(serveur.getSn()))
				.mapToLong(ordinateurServeurLink -> ordinateurServeurLink.getQuota())
				.sum();
		
		return (double)((double)sommeQuotas/(double)serveur.getMemoire());
	}
}
