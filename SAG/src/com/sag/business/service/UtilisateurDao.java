package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;

/**
 * Interface d'objet d'accès aux données Utilisateur.
 * @author Benjamin MIRETTI
 * 
 */
 
public interface UtilisateurDao {
	
	/**
	 * Récupérer un utlisateur par son id
	 * @param id L'identifiant de l'utilisateur à rechercher
	 * @return L'utilisateur correspondant à id
	 */
	public Utilisateur chercherParID(int id);
	
	/**
	 * Récupérer un utilisateur par son email
	 * @param email L'adresse mail de l'utilisateur à rechercher
	 * @return L'utilisateur correspondant
	 */
	public Utilisateur chercherParEmail(String email);
	
	/**
	 * Récupérer une liste d'utilsateurs par statut
	 * @param statut Le statut des utilisateurs à rechercher
	 * @return La liste des utilisateurs correspondant à statut
	 */
	public Collection<Utilisateur> chercherParStatut(StatutUtilisateur statut);
	
	/**
	 * Récupérer tous les utilsateur
	 * @return La liste de tous les utilisateurs
	 */
	public Collection<Utilisateur> chercherTous();
	
	/**
	 * Récupérer tous les utilsateur par block de offset avec une limite
	 * @param offset indice du premier objet à récupérer
	 * @param limite Le nombre d'objets max à récupérer
	 * @return Les entreprises correspondantes
	 */
	public Collection<Utilisateur> chercherTous(int offset , int limite);
	
	/**
	 * Ajoute un utilisateur si il est nouveau ou le sauvegarde si il a été modifié
	 * 
	 * @param utilisateur L'utilisateur à sauvegarder
	 * @return L'utilisateur sauvegardé en base
	 */
	public Utilisateur sauvegarder(Utilisateur utilisateur);
	
	/**
	 * Supprimer un utilisateur par son id 
	 * @param id L'Id de l'utilisateur à supprimer
	 * @return confirmation de suppression true, sinon false
	 */
	public Boolean supprimer(int id);
	
	/**
	 * Chercher un rôle pour créer un nouvel utilsateur 
	 * @param id l'id du rôle à récupérer
	 * @return Role le rôle correspondant à l'id
	 */
	public Role chercherRoleParID(int id);
	
}

