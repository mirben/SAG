package com.sag.business.service;

import java.util.Collection;

import com.sag.business.model.Offre;

/**
 * @author tuan
 *
 */
public interface OffreDao {

	Offre chercherParID(int id);

	Collection<Offre> chercherTous();

	Collection<Offre> chercherTous(int offset, int limite);

	Offre sauvagarder(Offre domaine);

	Boolean supprimer(int id);

}
