package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Etudiant;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;

/**
 * Interface d'objet d'accès aux données Etudiant.
 * @version 1
 * @author Tuan NGUYEN
 * 
 */
public interface EtudiantDao {
	
	/**
	 * Récupérer un etudiant par son id
	 * @param id L'identifiant de l'etudiant à rechercher
	 * @return L'Etudiant correspondant à id
	 */
	public Etudiant chercherParID(int id);
	
	/**
	 * Récupérer un etudiant par son idEnt
	 * @param idEnt L'identifiant ENT de l'étudiant à récupérer
	 * @return L'Etudiant correspondant à idEnt 
	 */
	public Etudiant chercherParEnt(String idEnt);
	
	/**
	 * Récupérer des étudiants par domaine
	 * @param domaine Le domaine des étudiants à récupérer
	 * @return La liste des Etudiants de domaine
	 */
	public Collection<Etudiant> chercherParDomaine(String domaine);
	
	/**
	 * Récupérer des étudiants par statut
	 * @param statut Le statut des étudiants à récupérer
	 * @return La liste des Etudiants possédant statut
	 */
	public Collection<Etudiant> chercherParStatut(StatutUtilisateur statut);
	
	/**
	 * Récupérer tous les étudiants
	 * @return La liste de tous les étudiants
	 */

	public Collection<Etudiant> chercherTous();
	
	/**
	 * Récupérer tous les étudiants par block de offset à limite
	 * @param offset indice du premier objet à récupérer
	 * @param limite Le nombre d'objets max à récupérer
	 * @return Les entreprises correspondantes
	 */
	public Collection<Etudiant> chercherTous(int offset , int limite);
	
	/**
	 * Ajoute un étudiant si il est nouveau ou le sauvegarde si il a été modifié
	 * 
	 * @param etudiant L'etudiant à sauvegarder
	 * @return L'étudiant sauvegardé en base
	 */
	public Etudiant sauvegarder(Etudiant etudiant);
	
	/**
	 * Supprimer un étudiant par son id 
	 * @param id L'Id de l'étudiant à supprimer
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

