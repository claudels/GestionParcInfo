package gestionParcInfo.model;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Mod�le des alertes.
 * @author Florian Lemarquand
 */
public class Alertes extends ModelList<Alerte> {

  /**
   * Constructeur du mod�le des alertes.
   * @param alertes Liste des alertes d�j� existantes
   * @param obs Observateur du mod�le
   */
  public Alertes(ArrayList<Alerte> alertes, Observer obs) {
  	super(alertes, obs);
  
  	this.setChanged();
  	this.notifyObservers();
  }
	
  /**
   * Chercher les alertes d'un employ�.
   * @param employe Employe pour lequel chercher
   * @return ArrayList des Alerte
   */
  public List<Alerte> findAlerteByEmploye(Employe employe) {
  	return this.getItems()
  			.parallelStream()
  			.filter(Alerte -> employe.getMatricule().equals(Alerte.getEmploye().getMatricule()))
  			.collect(Collectors.toList());
  }
	
  /**
   * Chercher une Alerte avec son Id.
   * @param code Id de l'Alerte
   * @return Alerte
   */
  public Alerte findByCode(int code) {
  	Alerte result = null;
  	
  	for (Alerte alerte : this.getItems()) {
  		if (alerte.getId() == (code)) {
  			result = alerte;
  		}
  	}
  	
  	return result;
  }
}
