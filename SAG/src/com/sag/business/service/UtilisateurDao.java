package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;

/**
 * @version 1
 * @author Benjamin MIRETTI
 * Interface d'objet d'accès aux données Utilisateur.
 */
 
public interface UtilisateurDao {
	
	/**
	 * Récupérer un utlisateur par son id
	 * @param id
	 * @return Utilisateur
	 */
	public Utilisateur chercherParID(int id);
	
	/**
	 * Récupérer un utilisateur par son email
	 * @param email
	 * @return Utilsateur
	 */
	public Utilisateur chercherParEmail(String email);
	
	/**
	 * Récupérer des utilsateur par statut
	 * @param statut
	 * @return Collection<Utilsateur>
	 */
	public Collection<Utilisateur> chercherParStatut(StatutUtilisateur statut);
	
	/**
	 * Récupérer tous les utilsateur
	 * @return Collection<Utilsateur>
	 */

	public Collection<Utilisateur> chercherTous();
	
	/**
	 * Récupérer tous les utilsateur par block de offset avec une limite
	 * @return Collection<Utilsateur>
	 * @param int offset : Index de début pour le résultat (commence à 0)
	 * @param int limite : Nombre max de résultats à retourner
	 */
	public Collection<Utilisateur> chercherTous(int offset , int limite);
	
	/**
	 * Sauvagarder un utilsateur, 
	 * @param utilisateur
	 * @return Collection<Utilsateur>
	 */
	public Utilisateur sauvegarder(Utilisateur utilisateur);
	
	/**
	 * Supprimer un utilisateur, 
	 * @param id
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

