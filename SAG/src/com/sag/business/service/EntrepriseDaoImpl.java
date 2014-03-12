package com.sag.business.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Entreprise;

/**
 * @version1
 * @author NGUYEN Tuan
 *
 */
@Repository("entrepriseDao")
@Transactional(readOnly = true)
public class EntrepriseDaoImpl implements EntrepriseDao{
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Entreprise chercherParID(int id) {
		return em.find(Entreprise.class, id);
	}

	@Override
	public Entreprise chercherParEmail(String email) {
		 return (Entreprise) em
			.createQuery("select e from Entreprise e where e.email = email")
			.setParameter("email", email).getSingleResult();
	}

	@Override
	public Collection<Entreprise> chercherTous() {
		return em.createQuery("FROM Entreprise", Entreprise.class).getResultList();
	}

	@Override
	public Collection<Entreprise> chercherTous(int offset, int limite) {
		return em.createQuery("FROM Entreprise", Entreprise.class)
				.setFirstResult(offset).setMaxResults(limite).getResultList();

	}

	@Override
	public Entreprise sauvagarder(Entreprise entreprise) {
		return em.merge(entreprise);
	}

	@Override
	public Boolean supprimer(int id) {
		em.remove(id);
		return (chercherParID(id) == null);
	}

}
