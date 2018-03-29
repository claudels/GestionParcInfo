package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;
import gestionParcInfo.entity.Ordinateur;

public class Alertes extends ModelList<Alerte> {

	public Alertes(ArrayList<Alerte> alertes, Observer obs) {
		super(alertes, obs);

		this.setChanged();
		this.notifyObservers();
	}
	
	public List<Alerte> findAlerteByEmploye(Employe employe) {
		return this.getItems()
				.parallelStream()
				.filter(Alerte -> employe.getMatricule().equals(Alerte.getEmploye().getMatricule()))
				.collect(Collectors.toList());
	}
	
	public Alerte findByCode(int code){
		Alerte result = null;
		
		for(Alerte alerte : this.getItems()) {
			if(alerte.getId() == (code))
				result = alerte;
		}
		
		return result;
	}
}
