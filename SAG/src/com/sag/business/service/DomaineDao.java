package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Domaine;

/**
 * Interface d'accès aux données Domaine
 * @author Tuan NGUYEN
 * 
 */

public interface DomaineDao {

	/**
	 * Récupérer un domaine par son id
	 * 
	 * @param id
	 * @return Domaine
	 */
	public Domaine chercherParID(int id);

	/**
	 * Récupérer un domaine par son nom
	 * 
	 * @return Domaine
	 * @throws Exception 
	 */

	public Domaine chercherParNom(String nom);

	/**
	 * Récupérer tous les domaines
	 * 
	 * @return Collection<Domaine>
	 */

	public Collection<Domaine> chercherTous();

	/**
	 * Récupérer limite domaines à partir de offset
	 * 
	 * @param offset indice du premier objet à récupérer
	 * @param limite Le nombre d'objets max à récupérer
	 * @return Collection<Domaine> Les domaines correspondant
	 */

	public Collection<Domaine> chercherTous(int offset, int limite);

	/**
	 * Ajoute un domaine si il est nouveau ou le sauvegarde si il a été modifié
	 * 
	 * @param domaine Le domaine à sauvegarder
	 * @return Domaine Le domaine sauvegardé en base
	 */
	public Domaine sauvegarder(Domaine domaine);

	/**
	 * Supprimer une domaine par son id
	 * 
	 * @param id L'id du domaine à supprimer
	 * @return Boolean confirmation de suppression true, sinon false
	 */
	public Boolean supprimer(int id);

}
