package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ModelList<T> extends Observable{
	private ArrayList<T> items;
	
	/**
	 * Création d'un modèle de type T.
	 * @param items Elements à ajouter au modèle lors de l'initialisation
	 */
	public ModelList(ArrayList<T> items) {
		this.items = items;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Création d'un modèle de type T avec un observateur.
	 * @param items Elements à ajouter au modèle lors de l'initialisation
	 * @param obs Observateur du modèle
	 */
	public ModelList(ArrayList<T> items, Observer obs) {
		this.addObserver(obs);
		this.items = items;
	}
	
	public ArrayList<T> getItems() {
		return items;
	}
	
	/**
	 * Ajouter un élément au modèle.
	 * @param item Element à ajouter.
	 */
	public void addItem(T item) {
		this.items.add(item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	/**
	 * Mettre à jour un élément dans la modèle.
	 * @param item Element à modifier
	 * @throws IndexOutOfBoundsException Levée si l'élément n'est pas dans le modèle
	 */
	public void updateItem(T item) throws IndexOutOfBoundsException {
		int index = this.items.indexOf(item);
		this.items.set(index, item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	/**
	 * Supprimer un élément du modèle.
	 * @param item Element à supprimer
	 * @return true si la suppression est ok, false sinon
	 */
	public boolean removeItem(T item) {
		boolean result = this.items.remove(item);
		
		this.setChanged();
		this.notifyObservers(item);
		
		return result;
	}
}
