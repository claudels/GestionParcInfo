package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observer;

import gestionParcInfo.entity.Employe;

public class Employes extends ModelList<Employe>{

	public Employes(ArrayList<Employe> employes) {
		super(employes);
	}
}
