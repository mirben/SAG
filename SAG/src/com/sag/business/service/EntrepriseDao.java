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
	 * Récupérer un entreprise par son id
	 * @param id
	 * @return Entreprise
	 */
	Entreprise chercherParID(int id);
	
	/**
	 * Récupérer un entreprise par son email
	 * @param email
	 * @return Entreprise
	 */
	Entreprise chercherParEmail(String email);
	
	/**
	 * Récupérer toutes entreprise
	 * @param email
	 * @return Entreprise
	 */
	Collection<Entreprise> chercherTous();
	
	/**
	 * Récupérer des entreprises 
	 * @param email
	 * @return Entreprise
	 */
	Collection<Entreprise> chercherTous(int offset , int limite);
	 /**
	  * Sauvagarder un entreprise.
	  * @param entreprise
	  * @return Entreprise
	  */
	Entreprise sauvagarder(Entreprise entreprise);
	Boolean supprimer(int id);
	
}
