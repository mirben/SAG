package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Offre;
/**
 * @version 1
 * @author tuan
 * Interface d'objet d'accès aux données Etudiant.
 */
public interface OffreDao {

	/**
	 * Récupérer une offre par son id
	 * @param id
	 * @return Etudiant
	 */
	Offre chercherParID(int id);
	

	/**
	 * Récupérer tous les offres
	 * @return Collection<Offre>
	 */

	Collection<Offre> chercherTous();

	/**
	 * Récupérer tous les étudiants par block de offset à limite
	 * @return Collection<Offre>
	 */
	Collection<Offre> chercherTous(int offset, int limite);

	/**
	 * Sauvagarder une offre
	 * @param offre
	 * @return
	 */
	Offre sauvagarder(Offre offre);

	/**
	 * Supprimer une offre
	 * @param id
	 * @return Boolean
	 */
	Boolean supprimer(int id);

}
