package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Offre;

/**
 * Interface d'objet d'accès aux données Etudiant.
 * 
 * @version 1
 * @author tuan
 * 
 */
public interface OffreDao {

	/**
	 * Récupérer une offre par son id
	 * 
	 * @param id
	 * @return Etudiant
	 */
	public Offre chercherParID(int id);

	/**
	 * Récupérer tous les offres lié à mot clé
	 * 
	 * @return Collection<Offre>
	 */
	
	public Collection<Offre> chercherParMotCle(String mot);

	/**
	 * Récupérer tous les offres
	 * 
	 * @return Collection<Offre>
	 */

	public Collection<Offre> chercherTous();

	/**
	 * Récupérer tous les étudiants par block de offset à limite
	 * 
	 * @return Collection<Offre>
	 */
	public Collection<Offre> chercherTous(int offset, int limite);

	/**
	 * Sauvagarder une offre
	 * 
	 * @param offre
	 * @return
	 */
	public Offre sauvegarder(Offre offre);

	/**
	 * Supprimer une offre
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean supprimer(int id);

}
