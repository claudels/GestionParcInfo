package gestionParcInfo.model;

import java.util.ArrayList;
import java.util.Observer;
import java.util.stream.Collectors;

import gestionParcInfo.entity.OrdinateurServeurLink;

public class OrdinateurServeurLinks extends ModelList<OrdinateurServeurLink> {
	
	public OrdinateurServeurLinks(ArrayList<OrdinateurServeurLink> ordinateurServeurLinks) {
		super(ordinateurServeurLinks);
	}
	
	public OrdinateurServeurLink findBySNOAndSNS(String sno, String sns) {
		return this.getItems()
				.parallelStream()
				.filter(link -> link.getOrdinateur().getSn().equals(sno) && link.getServeur().getSn().equals(sns))
				.findAny()
				.get();
	}
	
	public ArrayList<OrdinateurServeurLink> findBySNO(String sno) {
		return (ArrayList<OrdinateurServeurLink>) this.getItems()
				.parallelStream()
				.filter(link -> link.getOrdinateur().getSn().equals(sno))
				.collect(Collectors.toList());
	}
	
	public ArrayList<OrdinateurServeurLink> findBySNS(String sns) {
		return (ArrayList<OrdinateurServeurLink>) this.getItems()
				.parallelStream()
				.filter(link -> link.getServeur().getSn().equals(sns))
				.collect(Collectors.toList());
	}
}
