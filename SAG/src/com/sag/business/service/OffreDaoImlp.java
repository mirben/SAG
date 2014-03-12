package com.sag.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Offre;

/**
 * classe implémentation de OfferDao
 * 
 * @version 1
 * @author tuan
 * 
 */

@Repository("OffreDao")
@Transactional(readOnly = true)
public class OffreDaoImlp implements OffreDao {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public Offre chercherParID(int id) {

		return em.find(Offre.class, id);

	}

	@Override
	public Collection<Offre> chercherTous() {
		return em.createQuery("FROM Offre", Offre.class).getResultList();

	}

	@Override
	public Collection<Offre> chercherTous(int offset, int limite) {
		return em.createQuery("FROM Offre", Offre.class).setFirstResult(offset)
				.setMaxResults(limite).getResultList();
	}

	@Override
	@Transactional(readOnly = false)
	public Offre sauvagarder(Offre offre) {
		return em.merge(offre);
	}

	@Override
	public Boolean supprimer(int id) {
		em.remove(id);
		return (chercherParID(id) == null);
	}

	@Override
	public Collection<Offre> chercherParMotCle(String mot) {
		Collection<Offre> offres = chercherTous();
		Collection<Offre> results = new ArrayList<Offre>();
		Iterator<Offre> it = offres.iterator();
		while (it.hasNext()) {
			Offre offre = it.next();
			if (offre.getTitre().contains(mot)
					|| offre.getDescription().contains(mot))
				results.add(offre);
		}
		return results;
	}

}
