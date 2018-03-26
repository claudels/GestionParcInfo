package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observer;

import gestionParcInfo.entity.Imprimante;

public class Imprimantes extends ModelList<Imprimante> {
	
	private Ordinateurs ordinateurs;
	
	public Imprimantes(ArrayList<Imprimante> imprimantes, Ordinateurs ordinateurs, Observer obs) {
		super(imprimantes, obs);
		
		this.ordinateurs = ordinateurs;
		
		this.setChanged();
		this.notifyObservers();
	}

	public long countOrdinateurs(Imprimante imprimante) {
		return ordinateurs.getItems()
				.parallelStream()
				.filter(ordinateur -> ordinateur.getImprimante() != null && ordinateur.getImprimante().getSn().equals(imprimante.getSn()))
				.count();
	}
}
