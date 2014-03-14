package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;

/**
 * @version 1
 * @author tuan
 * Interface d'objet d'accès aux données Etudiant.
 */
 
public interface EtudiantDao {
	
	/**
	 * Récupérer un entreprise par son id
	 * @param id
	 * @return Entreprise
	 */
	public Etudiant chercherParID(int id);
	
	/**
	 * Récupérer un etudiant par son idEnt
	 * @param idEnt
	 * @return Etudiant
	 */
	public Etudiant chercherParEnt(String string);
	
	/**
	 * Récupérer des étudiants par domaine
	 * @param domaine
	 * @return Collection<Etudiant>
	 */
	public Collection<Etudiant> chercherParDomaine(String domaine);
	
	/**
	 * Récupérer des étudiants par statut
	 * @param statut
	 * @return Collection<Etudiant>
	 */
	public Collection<Etudiant> chercherParStatut(StatutUtilisateur statut);
	
	/**
	 * Récupérer tous les étudiants
	 * @return Collection<Etudiant>
	 */

	public Collection<Etudiant> chercherTous();
	
	/**
	 * Récupérer tous les étudiants par block de offset à limite
	 * @return Collection<Etudiant>
	 * @param int offset : Index de début pour le résultat (commence à 0)
	 * @param int limite : Nombre max de résultats à retourner
	 */
	public Collection<Etudiant> chercherTous(int offset , int limite);
	
	/**
	 * Sauvagarder un étudiant, 
	 * @param etudiant
	 * @return Collection<Etudiant>
	 */
	public Etudiant sauvegarder(Etudiant etudiant);
	
	/**
	 * Supprimer un étudiant, 
	 * @param id
	 * @return Boolean
	 */
	public Boolean supprimer(int id);
	
}

