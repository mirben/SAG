package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;

/**
 * @version 1
 * @author tuan
 * Interface d'objet d'accès aux données Entreprise.
 */
public interface EntrepriseDao {
	/**
	 * Récupérer un entreprise par son id
	 * @param id
	 * @return Entreprise
	 */
	public Entreprise chercherParID(int id);
	
	/**
	 * Récupérer un entreprise par son email
	 * @param email
	 * @return Entreprise
	 */
	public Entreprise chercherParEmail(String email);
	
	/**
	 * Récupérer toutes entreprises
	 * @return Collection<Entreprise>
	 */
	public Collection<Entreprise> chercherTous();
	
	/**
	 * Récupérer des entreprises par bloc de offset à limite
	 * @param offset, limite
	 * @return Collection<Entreprise>
	 */
	Collection<Entreprise> chercherTous(int offset , int limite);
	 /**
	  * Sauvagarder un entreprise.
	  * @param entreprise
	  * @return Entreprise
	  */
	Entreprise sauvegarder(Entreprise entreprise);
	
	/**
	  * Supprimer une entreprise.
	  * @param entreprise
	  * @return Boolean
	  */
	Boolean supprimer(int id);
	
	Role chercherRoleParID(int id);
	
}
