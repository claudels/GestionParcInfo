package gestionParcInfo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.OrdinateurServeurLink;
import gestionParcInfo.entity.Serveur;

public class Serveurs extends ModelList<Serveur> implements Observer {
	/**
	 * Lie les serveurs � leur charge respective
	 */
	private HashMap<Serveur, Double> chargesServeurs;
	
	/**
	 * Lie les serveur au nombre d'ordinateurs connect� � celui-ci
	 */
	private HashMap<Serveur, Long> nbOrdisConnectes;
	
	/**
	 * Mod�le qui r�pr�sente les liens entre les serveurs et les ordinateurs
	 */
	private OrdinateurServeurLinks ordinateurServeurLinks;
	
	public Serveurs(OrdinateurServeurLinks ordinateurServeurLinks) {
		super();
		
		//Initilisation des listes
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.chargesServeurs = new HashMap<Serveur, Double>();
		this.nbOrdisConnectes = new HashMap<Serveur, Long>();
		
		//Initialise la charge et le nombre d'ordinateur connect�s pour chaque serveur
		List<Serveur> serveurs = this.ordinateurServeurLinks.getItems()
		.parallelStream()
		.map(ordinateuServeurLink -> ordinateuServeurLink.getServeur())
		.collect(Collectors.toList());
		
		for(Serveur serveur : serveurs) {
			this.chargesServeurs.put(serveur, this.calculerChargeServeur(serveur));
			this.nbOrdisConnectes.put(serveur, this.countOrdinateursLinked(serveur));
		}
	}
	
	/**
	 * Ajoute un serveur au mod�le
	 */
	@Override
	public void addItem(Serveur item) {
		super.addItem(item);
		this.chargesServeurs.put(item, calculerChargeServeur(item));
	}

	@Override
	public void update(Observable obs, Object obj) {
		//Si L'Observable qui � chang� et le mod�le des liens r�seaux
		if(obs instanceof OrdinateurServeurLinks) {
			//Si on � transmis le lien, alors on recalcule la charge du serveur associ�
			if(obj instanceof OrdinateurServeurLink) {
				Serveur serveur = ((OrdinateurServeurLink)obj).getServeur();
				long nbOrdiConnectes = this.countOrdinateursLinked(serveur);
				//Si le serveur � des ordinateurs connect�s, on recalcule la charge et on met � jour le compteur d'ordinateurs
				//Sinon on le supprime de la table des charges et de la table des compteurs
				if(nbOrdiConnectes != 0) {
					this.chargesServeurs.put(serveur, this.calculerChargeServeur(serveur));
					this.nbOrdisConnectes.put(serveur, nbOrdiConnectes);
				}
				else {
					this.chargesServeurs.remove(serveur);
					this.nbOrdisConnectes.remove(serveur);
				}

			}
		}
	}
	
	/**
	 * Compte le nombre d'ordinateurs connect�s � un serveur
	 * @param serveur Serveur pour lequel compter les ordinateurs
	 * @return long Nombre d'ordinateurs connect�s
	 */
	private long countOrdinateursLinked(Serveur serveur) {
		return this.ordinateurServeurLinks.getItems()
		.parallelStream()
		.filter(ordinateurServeurLink -> ordinateurServeurLink.getServeur().equals(serveur))
		.count();
	}
	
	/**
	 * Calcule la charge th�orique maximale du serveur (somme quotas ordinateurs/m�moire)
	 * @param serveur Serveur pour lequel calculer la charge
	 * @return Un pourcentage compris entre 0.0 et 1.0
	 */
	private double calculerChargeServeur(Serveur serveur) {
		long sommeQuotas = this.ordinateurServeurLinks.getItems()
				.parallelStream()
				.filter(ordinateurServeurLink -> ordinateurServeurLink.getServeur().equals(serveur))
				.mapToLong(ordinateurServeurLink -> ordinateurServeurLink.getQuota()).sum();
		
		return (double)(sommeQuotas/serveur.getMemoire());
	}
}
