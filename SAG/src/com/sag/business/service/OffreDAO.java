package com.sag.business.service;

public interface OffreDAO {
	
		Offre chercherParID(Id int);
		Collection<Offre> chercherTous();
		Collection<Offre> chercherTous(int offset , int limite);
		Offre sauvagarder(Offre domaine);
		Boolean supprimer(int id);
		
	
}
