package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Etudiant;
import com.sag.business.model.Statut;

/**
 * @version 1
 * @author tuan
 * Interface d'objet d'accès aux données Etudiant.
 */
 
public interface EtudiantDao {
	
	/**
	 * Récupérer un etudiant par son id
	 * @param id
	 * @return Etudiant
	 */
	Etudiant chercherParID(int idEnt);
	/**
	 * Récupérer un etudiant par son idEnt
	 * @param idEnt
	 * @return Etudiant
	 */
	Etudiant chercherParEnt(int idEnt);
	
	/**
	 * Récupérer des étudiants par domaine
	 * @param domaine
	 * @return Collection<Etudiant>
	 */
	Collection<Etudiant> chercherParDomaine(String domaine);
	
	/**
	 * Récupérer des étudiants par statut
	 * @param domaine
	 * @return Collection<Etudiant>
	 */
	Collection<Etudiant> chercherParStatut(Statut statut);
	
	/**
	 * Récupérer tous les étudiants
	 * @param domaine
	 * @return Collection<Etudiant>
	 */
	Collection<Etudiant> chercherTous();
	
	/**
	 * Récupérer des étudiants par bloc de offset à limite
	 * @param domaine
	 * @return Collection<Etudiant>
	 */
	Collection<Etudiant> chercherTous(int offset , int limite);
	
	/**
	 * Sauvagarder un étudiant, 
	 * @param etudiant
	 * @return Collection<Etudiant>
	 */
	Etudiant sauvagarder(Etudiant etudiant);
	
	/**
	 * Supprimer un étudiant, 
	 * @param id
	 * @return Booleanvvdvvvf
	 */
	Boolean supprimer(int id);
	
}

