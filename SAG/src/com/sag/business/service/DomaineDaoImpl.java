package com.sag.business.service;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sag.business.model.Domaine;
/**
 * @version1
 * @author NGUYEN Tuan
 * @author MIRETTI Benjamin
 *
 */
@Remote(value = DomaineDao.class)
@Stateless(name = "domaineDao", description = "Dao pour domaines")
@Startup
public class DomaineDaoImpl implements DomaineDao {
	
	@PersistenceContext(unitName = "SAG_PU")
	private EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("Dao init : " + this);
		System.out.println("em = " + em);
	}
	
	@Override
	public Domaine chercherParID(int id) {
		return em.find(Domaine.class, id);

	}

	@Override
	public Domaine chercherParNom(String nom){
		TypedQuery<Domaine> q= em.createQuery("select d from Domaine d where d.nom = :nom", Domaine.class);
		q.setParameter("nom", nom);
		try{
			Domaine d = q.getSingleResult();
			return d;
		}catch(NoResultException e){
			return null;
		}
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
	public Domaine sauvegarder(Domaine domaine) {
		return em.merge(domaine);
	}

	@Override
	public Boolean supprimer(int id){
		Domaine d = chercherParID(id);
		if(d != null)
		{
			em.remove(chercherParID(id));
			return (chercherParID(id) == null);
		}
		return false;
	}
}
