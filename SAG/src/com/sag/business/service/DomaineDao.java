package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Domaine;

/**
 * @version 1
 * @author tuan 
 * Interface d'objet d'accès aux données Domaine.
 */

public interface DomaineDao {

	/**
	 * Récupérer un domaine par son id
	 * 
	 * @param id
	 * @return Domaine
	 */
	Domaine chercherParID(int id);

	/**
	 * Récupérer un domaine par son nom
	 * 
	 * @return Collection<Domaine>
	 * @throws Exception 
	 */

	public Domaine chercherParNom(String nom);

	/**
	 * Récupérer toutes domaines
	 * 
	 * @return Collection<Domaine>
	 */

	Collection<Domaine> chercherTous();

	/**
	 * Récupérer des domaines par bloc de offset à limite
	 * 
	 * @param offset
	 *            , limite
	 * @return Collection<Domaine>
	 */

	Collection<Domaine> chercherTous(int offset, int limite);

	/**
	 * Ajoute un domaine si il est nouveau ou le sauvegarde si il a été modifié
	 * 
	 * @param domaine
	 * @return Domaine
	 */
	Domaine sauvegarder(Domaine domaine);

	/**
	 * Supprimer une domaine
	 * 
	 * @param id
	 * @return Boolean
	 */
	Boolean supprimer(int id);

}
