package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observer;

import gestionParcInfo.entity.Imprimante;

public class Imprimantes extends ModelList<Imprimante> {
	
	public Imprimantes(ArrayList<Imprimante> imprimantes, Observer obs) {
		super(imprimantes, obs);
	}
}
