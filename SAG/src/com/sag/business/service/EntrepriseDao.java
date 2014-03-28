package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;

/**
 * Interface d'objet d'accès aux données Entreprise.
 * @version 1
 * @author Tuan NGUYEN
 * 
 */
public interface EntrepriseDao{
	/**
	 * Récupérer une entreprise par son id
	 * @param id L'identifiant de l'entreprise à rechercher
	 * @return L'Entreprise correspondant à id
	 */
	public Entreprise chercherParID(int id);
	
	/**
	 * Récupérer un entreprise par son email
	 * @param email L'email de l'entreprise à récupérer
	 * @return L'Entreprise correspondant à email 
	 */
	public Entreprise chercherParEmail(String email);
	
	/**
	 * Récupérer toutes entreprises
	 * @return La liste de toutes les entreprises
	 */
	public Collection<Entreprise> chercherTous();
	
	/**
	 * Récupérer des entreprises par bloc de offset à limite
	 * @param offset indice du premier objet à récupérer
	 * @param limite Le nombre d'objets max à récupérer
	 * @return Les entreprises correspondantes
	 */
	public Collection<Entreprise> chercherTous(int offset , int limite);

	/**
	 * Ajoute une entreprise si elle est nouvelle ou la sauvegarde si elle a été modifiée
	 * 
	 * @param entreprise L'entreprise à sauvegarder
	 * @return L'entreprise sauvegardé en base
	 */
	public Entreprise sauvegarder(Entreprise entreprise);
	
	/**
	  * Supprimer une entreprise par son id
	 * @param id L'id de l'entreprise à supprimer
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
