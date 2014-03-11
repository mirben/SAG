package com.sag.business.service;


import java.util.Collection;

import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;

/**
 * @author tuan
 *
 */
public interface EtudiantDao {
	
	Etudiant chercherParID(int id);
	Etudiant chercherParEnt(int id);
	Collection<Etudiant> chercherParDomaine(String domaine);
	Collection<Etudiant> chercherParStatut(StatutUtilisateur statut);
	Collection<Etudiant> chercherTous();
	Collection<Etudiant> chercherTous(int offset , int limite);
	Etudiant sauvagarder(Etudiant etudiant);
	Boolean supprimer(int id);
	
}

