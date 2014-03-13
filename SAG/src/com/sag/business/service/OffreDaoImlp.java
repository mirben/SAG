package com.sag.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Offre;

/**
 * classe implémentation de OfferDao
 * 
 * @version 1
 * @author NGUYENtuan
 * @author MIRETTI Benjamin
 */

@Remote(value = OffreDao.class)
@Stateless(name = "offreDao", description = "Dao pour offre")
@Startup
public class OffreDaoImlp implements OffreDao {

	@PersistenceContext(unitName = "SAG_PU")
	private EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("Dao init : " + this);
		System.out.println("em = " + em);
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
	public Offre sauvegarder(Offre offre) {
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
