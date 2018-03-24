package gestionParcInfo.model;

import java.util.ArrayList;
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
	
	public Serveurs(ArrayList<Serveur> serveurs, OrdinateurServeurLinks ordinateurServeurLinks, Observer obs) {
		super(serveurs, obs);
		
		//Initilisation des listes
		this.ordinateurServeurLinks = ordinateurServeurLinks;
		this.chargesServeurs = new HashMap<Serveur, Double>();
		this.nbOrdisConnectes = new HashMap<Serveur, Long>();
		
		//Initialise la charge et le nombre d'ordinateur connect�s pour chaque serveur
		for(Serveur serveur : this.getItems()) {
			this.chargesServeurs.put(serveur, this.calculerChargeServeur(serveur));
			this.nbOrdisConnectes.put(serveur, this.countOrdinateursLinked(serveur));
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Ajoute un serveur au mod�le
	 */
	@Override
	public void addItem(Serveur item) {
		this.chargesServeurs.put(item, this.calculerChargeServeur(item));
		this.nbOrdisConnectes.put(item, this.countOrdinateursLinked(item));
		super.addItem(item);
	}
	
	@Override
	public void updateItem(Serveur item) throws IndexOutOfBoundsException {
		this.chargesServeurs.put(item, this.calculerChargeServeur(item));
		this.nbOrdisConnectes.put(item, this.countOrdinateursLinked(item));
		super.updateItem(item);
	}
	
	@Override
	public boolean removeItem(Serveur item) {
		this.chargesServeurs.remove(item);
		this.nbOrdisConnectes.remove(item);
		return super.removeItem(item);
	}
	
	public HashMap<Serveur, Double> getChargesServeurs() {
		return chargesServeurs;
	}
	
	public HashMap<Serveur, Long> getNbOrdisConnectes() {
		return nbOrdisConnectes;
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
				.filter(ordinateurServeurLink -> ordinateurServeurLink.getServeur().getSn().equals(serveur.getSn()))
				.mapToLong(ordinateurServeurLink -> ordinateurServeurLink.getQuota()).sum();
		
		return (double)((double)sommeQuotas/(double)serveur.getMemoire());
	}
}
