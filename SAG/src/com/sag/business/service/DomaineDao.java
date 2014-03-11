package com.sag.business.service;

import java.util.Collection;

import com.sag.model.Domaine;

public interface DomaineDao {
	Domaine chercherParID(Id int);
	Collection<Domaine> chercherTous();
	Collection<Domaine> chercherTous(int offset , int limite);
	Domaine sauvagarder(Domaine domaine);
	Boolean supprimer(int id);
	
}
