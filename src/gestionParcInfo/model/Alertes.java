package gestionParcInfo.model;

import gestionParcInfo.entity.Alerte;
import gestionParcInfo.entity.Employe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Modèle des alertes.
 * @author Florian Lemarquand
 */
public class Alertes extends ModelList<Alerte> {

  /**
   * Constructeur du modèle des alertes.
   * @param alertes Liste des alertes déjà existantes
   * @param obs Observateur du modèle
   */
  public Alertes(ArrayList<Alerte> alertes, Observer obs) {
  	super(alertes, obs);
  
  	this.setChanged();
  	this.notifyObservers();
  }
	
  /**
   * Chercher les alertes d'un employé.
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
