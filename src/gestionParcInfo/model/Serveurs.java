package gestionParcInfo.model;

import gestionParcInfo.entity.Serveur;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Mod�le des serveurs.
 * @author Sebastien Claudel
 */
public class Serveurs extends ModelList<Serveur> {
	
	/**
	 * Mod�le qui r�pr�sente les liens entre les serveurs et les ordinateurs.
	 */
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	/**
	 * Constructeur du mod�le des serveurs.
	 * @param serveurs Serveurs d�j� existants � ins�rer dans le mod�le
	 * @param ordinateurServeurLinks Mod�le des liens
	 * @param obs Observateur du mod�le
	 */
	public Serveurs(ArrayList<Serveur> serveurs, OrdinateurServeurLinks ordinateurServeurLinks, Observer obs) {
		super(serveurs, obs);
		
		this.ordinateurServeurLinks = ordinateurServeurLinks;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Compte le nombre d'ordinateurs connect�s � un serveur.
	 * @param serveur Serveur pour lequel compter les ordinateurs
	 * @return long Nombre d'ordinateurs connect�s
	 */
	public long countOrdinateursLinked(Serveur serveur) {
		return this.ordinateurServeurLinks.findBySns(serveur.getSn()).size();
	}
	
	/**
	 * Calcule la charge th�orique maximale du serveur (somme quotas ordinateurs/m�moire).
	 * @param serveur Serveur pour lequel calculer la charge
	 * @return Un pourcentage compris entre 0.0 et 1.0
	 */
	public double calculerChargeServeur(Serveur serveur) {	
		return (double)(((double)calculerSommeQuotas(serveur) / 1024) / (double)serveur.getMemoire());
	}
	
	/**
	 * Retourne la somme des quotas des ordinateurs reli�s au serveur (en MegaOctets).
	 * @param serveur Serveur pour lequel calculer la somme
	 * @return long Somme des quotas
	 */
	public long calculerSommeQuotas(Serveur serveur) {
		return this.ordinateurServeurLinks.findBySns(serveur.getSn())
				.parallelStream()
				.mapToLong(ordinateurServeurLink -> ordinateurServeurLink.getQuota())
				.sum();
	}

	/**
	 * Chercher un serveur par son num�ro de s�rie.
	 * @param sns Num�ro de s�rie
	 * @return Serveur
	 */
	public Serveur findBySn(String sns) {
		Serveur result = null;
		
		for (Serveur serveur : this.getItems()) {
			if (serveur.getSn().equals(sns)) {
				result = serveur;
			}
		}
		
		return result;
	}

	
}
