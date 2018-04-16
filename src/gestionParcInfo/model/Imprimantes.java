package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;

public class Imprimantes extends ModelList<Imprimante> {
	
	private Ordinateurs ordinateurs;
	
	/**
	 * Cr�ation du mod�le des imprimantes.
	 * @param imprimantes Imprimantes existantes
	 * @param ordinateurs Mod�le des ordinateurs
	 * @param obs Observateur du mod�le
	 */
	public Imprimantes(ArrayList<Imprimante> imprimantes, Ordinateurs ordinateurs, Observer obs) {
		super(imprimantes, obs);
		
		this.ordinateurs = ordinateurs;
		
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Compte le nombre d'ordinateurs connect�s � l'imprimante.
	 * @param imprimante Imprimante pour laquelle compter
	 * @return int nombre d'ordinateurs connect�s
	 */
	public long countOrdinateurs(Imprimante imprimante) {
		return ordinateurs.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getImprimante() != null 
				&& ordinateur.getImprimante().getSn().equals(imprimante.getSn()))
				.count();
	}
	
	/**
	 * Cherche une imprimante par num�ro de s�rie.
	 * @param sni Num�ro de s�rie de l'imprimante
	 * @return Imprimante
	 */
	public Imprimante findBySn(String sni) {
		Imprimante result = null;
		
		for (Imprimante impr : this.getItems()) {
			if (impr.getSn().equals(sni)) {
				result = impr;
			}
		}
		
		return result;
	}
}
