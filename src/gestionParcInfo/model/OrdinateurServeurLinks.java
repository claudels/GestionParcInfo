package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observer;

import gestionParcInfo.entity.OrdinateurServeurLink;

public class OrdinateurServeurLinks extends ModelList<OrdinateurServeurLink> {
	
	public OrdinateurServeurLinks(ArrayList<OrdinateurServeurLink> ordinateurServeurLinks, Observer obs) {
		super(ordinateurServeurLinks, obs);
	}
}
