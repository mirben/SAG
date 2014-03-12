package com.sag.business.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Domaine;
/**
 * @version1
 * @author NGUYEN Tuan
 *
 */
@Repository("domaineDao")
@Transactional(readOnly = true)
public class DomaineDaoImpl implements DomaineDao {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public Domaine chercherParID(int id) {
		return em.find(Domaine.class, id);

	}

	@Override
	public Domaine chercherParNom(String nom) {
		return (Domaine) em
				.createQuery("select d from Domaine d where d.nom = nom")
				.setParameter("nom", nom).getSingleResult();
	}

	
	@Override
	public Collection<Domaine> chercherTous() {
		return em.createQuery("FROM Domaine", Domaine.class).getResultList();
	}

	@Override
	public Collection<Domaine> chercherTous(int offset, int limite) {
		return em.createQuery("FROM Domaine", Domaine.class)
				.setFirstResult(offset).setMaxResults(limite).getResultList();

	}

	@Override
    @Transactional(readOnly = false)
	public Domaine sauvagarder(Domaine domaine) {
		return em.merge(domaine);
	}

	@Override
    @Transactional(readOnly = false)
	public Boolean supprimer(int id) {
		em.remove(id);
		return (chercherParID(id) == null);
	}

}
