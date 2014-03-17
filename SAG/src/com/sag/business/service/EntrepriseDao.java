package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;

/**
 * @version 1
 * @author tuan
 * Interface d'objet d'accès aux données Entreprise.
 */
public interface EntrepriseDao{
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
	 * * @param int offset : Index de début pour le résultat (commence à 0)
	 * @param int limite : Nombre max de résultats à retourner
	 */
	public Collection<Entreprise> chercherTous(int offset , int limite);
	 /**
	  * Sauvagarder un entreprise.
	  * @param entreprise
	  * @return Entreprise
	  */
	public Entreprise sauvegarder(Entreprise entreprise);
	
	/**
	  * Supprimer une entreprise.
	  * @param entreprise
	  * @return Boolean
	  */
	public Boolean supprimer(int id);
	
	/**
	 * Chercher un rôle pour créer un nouvel utilsateur, 
	 * @param id
	 * @return Role : le rôle correspondant à l'id
	 */
	public Role chercherRoleParID(int id);
	
}
