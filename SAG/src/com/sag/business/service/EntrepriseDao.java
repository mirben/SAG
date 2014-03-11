package com.sag.business.service;

import java.util.Collection;

import com.sag.model.Entreprise;

public interface EntrepriseDao {
	Entreprise chercherParID(int id);
	Entreprise chercherParEmail(String email);
	Collection<Entreprise> chercherTous();
	Collection<Entreprise> chercherTous(int offset , int limite);
	Entreprise sauvagarder(Entreprise entreprise);
	Boolean supprimer(int id);
	
}
