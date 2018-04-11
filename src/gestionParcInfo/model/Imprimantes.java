package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Imprimante;
import gestionParcInfo.entity.Ordinateur;

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
	
	public List<Imprimante> findEmployeByImprimante(Employe employe) {
		return this.getItems()
				.parallelStream()
				.filter(imprimante -> imprimante.getProprietaire() != null && employe.getMatricule().equals(imprimante.getProprietaire().getMatricule()))
				.collect(Collectors.toList());
	}
	
	public Imprimante findBySN(String sni){
		Imprimante result = null;
		
		for(Imprimante impr : this.getItems()) {
			if(impr.getSn().equals(sni))
				result = impr;
		}
		
		return result;
	}
}
