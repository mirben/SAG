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
	 * @param id L'identifiant de l'offre à rechercher
	 * @return L'Offre correspondant à id
	 */
	public Offre chercherParID(int id);

	/**
	 * Récupérer tous les offres contenant des mots clés
	 * @param mots Les mots clés à rechercher
	 * @return La liste des offres contenant mots
	 */
	public Collection<Offre> chercherParMotCle(String mots);

	/**
	 * Récupérer tous les offres
	 * 
	 * @return La liste de toutes les offres
	 */
	public Collection<Offre> chercherTous();

	/**
	 * Récupérer des offres par bloc de offset à limite
	 * @param offset indice du premier objet à récupérer
	 * @param limite Le nombre d'objets max à récupérer
	 * @return Les offres correspondantes
	 */
	public Collection<Offre> chercherTous(int offset, int limite);

	/**
	 * Ajoute une offre si elle est nouvelle ou la sauvegarde si elle a été modifiée
	 * 
	 * @param offre L'offre à sauvegarder
	 * @return L'offre sauvegardé en base
	 */
	public Offre sauvegarder(Offre offre);

	/**
	 * Supprimer une offre par son id
	 * 
	 * @param id L'id de l'offre à supprimer
	 * @return confirmation de suppression true, sinon false
	 */
	public Boolean supprimer(int id);

}
