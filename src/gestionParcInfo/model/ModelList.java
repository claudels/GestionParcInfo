package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ModelList<T> extends Observable{
	private ArrayList<T> items;
	
	public ModelList(ArrayList<T> items) {
		this.items = items;
		this.setChanged();
		this.notifyObservers();
	}
	
	public ModelList(ArrayList<T> items, Observer obs) {
		this.addObserver(obs);
		this.items = items;
	}
	
	public ArrayList<T> getItems() {
		return items;
	}
	
	public void addItem(T item) {
		this.items.add(item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	public void updateItem(T item) throws IndexOutOfBoundsException {
		int index = this.items.indexOf(item);
		this.items.set(index, item);
		this.setChanged();
		this.notifyObservers(item);
	}
	
	public boolean removeItem(T item) {
		boolean result = this.items.remove(item);
		
		this.setChanged();
		this.notifyObservers(item);
		
		return result;
	}
}
