package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Entreprise;

/**
 * @author tuan
 * 
 * Interface d'objet d'accès aux données Entreprise.
 */
public interface EntrepriseDao {
	/**
	 * 
	 * @param id
	 * @return Entreprise
	 */
	Entreprise chercherParID(int id);
	Entreprise chercherParEmail(String email);
	Collection<Entreprise> chercherTous();
	Collection<Entreprise> chercherTous(int offset , int limite);
	Entreprise sauvagarder(Entreprise entreprise);
	Boolean supprimer(int id);
	
}
