package gestionParcInfo.model;

import gestionParcInfo.entity.OrdinateurServeurLink;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Modèle des liens entre les serveurs et les ordinateurs.
 * @author Florian Lemarquand
 */
public class OrdinateurServeurLinks extends ModelList<OrdinateurServeurLink> {
	
	public OrdinateurServeurLinks(ArrayList<OrdinateurServeurLink> ordinateurServeurLinks) {
		super(ordinateurServeurLinks);
	}
	
	/**
	 * Chercher un lien par numéros de séries ordinateurs et serveurs.
	 * @param sno Numéro de série de l'ordinateur
	 * @param sns Numéro de série du serveur
	 * @return OrdinateurServeurLink
	 */
	public OrdinateurServeurLink findBySnoAndSns(String sno, String sns) {
		return this.getItems()
				.parallelStream()
				.filter(link -> link.getOrdinateur().getSn().equals(sno) && link.getServeur().getSn().equals(sns))
				.findAny()
				.get();
	}
	
	/**
	 * Chercher les liens à partir du numéro de série de l'ordinateur.
	 * @param sno Numéro de série de l'ordinateur
	 * @return ArrayList Liste des liens
	 */
	public ArrayList<OrdinateurServeurLink> findBySno(String sno) {
		return (ArrayList<OrdinateurServeurLink>) this.getItems()
				.parallelStream()
				.filter(link -> link.getOrdinateur().getSn().equals(sno))
				.collect(Collectors.toList());
	}
	
	/**
	 * Chercher les lien à partir du numéro de série du serveur.
	 * @param sns Numéro de série du serveur
	 * @return ArrayList Liste des liens
	 */
	public ArrayList<OrdinateurServeurLink> findBySns(String sns) {
		return (ArrayList<OrdinateurServeurLink>) this.getItems()
				.parallelStream()
				.filter(link -> link.getServeur().getSn().equals(sns))
				.collect(Collectors.toList());
	}
}
