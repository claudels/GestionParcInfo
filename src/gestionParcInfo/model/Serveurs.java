package gestionParcInfo.model;

import gestionParcInfo.entity.Serveur;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Modèle des serveurs.
 * @author Sebastien Claudel
 */
public class Serveurs extends ModelList<Serveur> {
	
	/**
	 * Modèle qui réprésente les liens entre les serveurs et les ordinateurs.
	 */
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	/**
	 * Constructeur du modèle des serveurs.
	 * @param serveurs Serveurs déjà existants à insérer dans le modèle
	 * @param ordinateurServeurLinks Modèle des liens
	 * @param obs Observateur du modèle
	 */
	public Serveurs(ArrayList<Serveur> serveurs, OrdinateurServeurLinks ordinateurServeurLinks, Observer obs) {
		super(serveurs, obs);
		
		this.ordinateurServeurLinks = ordinateurServeurLinks;

		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Compte le nombre d'ordinateurs connectés à un serveur.
	 * @param serveur Serveur pour lequel compter les ordinateurs
	 * @return long Nombre d'ordinateurs connectés
	 */
	public long countOrdinateursLinked(Serveur serveur) {
		return this.ordinateurServeurLinks.findBySns(serveur.getSn()).size();
	}
	
	/**
	 * Calcule la charge théorique maximale du serveur (somme quotas ordinateurs/mémoire).
	 * @param serveur Serveur pour lequel calculer la charge
	 * @return Un pourcentage compris entre 0.0 et 1.0
	 */
	public double calculerChargeServeur(Serveur serveur) {	
		return (double)(((double)calculerSommeQuotas(serveur) / 1024) / (double)serveur.getMemoire());
	}
	
	/**
	 * Retourne la somme des quotas des ordinateurs reliés au serveur (en MegaOctets).
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
	 * Chercher un serveur par son numéro de série.
	 * @param sns Numéro de série
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
