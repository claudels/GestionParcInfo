package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ModelList<T> extends Observable{
	private ArrayList<T> items;
	
	/**
	 * Cr�ation d'un mod�le de type T.
	 * @param items Elements � ajouter au mod�le lors de l'initialisation
	 */
	public ModelList(ArrayList<T> items) {
		this.items = items;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Cr�ation d'un mod�le de type T avec un observateur.
	 * @param items Elements � ajouter au mod�le lors de l'initialisation
	 * @param obs Observateur du mod�le
	 */
	public ModelList(ArrayList<T> items, Observer obs) {
		this.addObserver(obs);
		this.items = items;
	}
	
	public ArrayList<T> getItems() {
		return items;
	}
	
	/**
	 * Ajouter un �l�ment au mod�le.
	 * @param item Element � ajouter.
	 */
	public void addItem(T item) {
		this.items.add(item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	/**
	 * Mettre � jour un �l�ment dans la mod�le.
	 * @param item Element � modifier
	 * @throws IndexOutOfBoundsException Lev�e si l'�l�ment n'est pas dans le mod�le
	 */
	public void updateItem(T item) throws IndexOutOfBoundsException {
		int index = this.items.indexOf(item);
		this.items.set(index, item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	/**
	 * Supprimer un �l�ment du mod�le.
	 * @param item Element � supprimer
	 * @return true si la suppression est ok, false sinon
	 */
	public boolean removeItem(T item) {
		boolean result = this.items.remove(item);
		
		this.setChanged();
		this.notifyObservers(item);
		
		return result;
	}
}
