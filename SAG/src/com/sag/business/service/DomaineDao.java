package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Domaine;

public interface DomaineDao {
	Domaine chercherParID(int id);
	Collection<Domaine> chercherTous();
	Collection<Domaine> chercherTous(int offset , int limite);
	Domaine sauvagarder(Domaine domaine);
	Boolean supprimer(int id);
	
}
