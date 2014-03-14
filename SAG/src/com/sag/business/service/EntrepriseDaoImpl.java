package com.sag.business.service;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;

/**
 * @version1
 * @author NGUYEN Tuan
 * @author MIRETTI Benjamin
 *
 */
@Remote(value = EntrepriseDao.class)
@Stateless(name = "entrepriseDao", description = "Dao pour entreprise")
@Startup
public class EntrepriseDaoImpl implements EntrepriseDao{
	
	@PersistenceContext(unitName = "SAG_PU")
	private EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("Dao init : " + this);
		System.out.println("em = " + em);
	}
	
	@Override
	public Entreprise chercherParID(int id) {
		return em.find(Entreprise.class, id);
	}

	@Override
	public Entreprise chercherParEmail(String email) {
		 TypedQuery<Entreprise> q = em
					.createQuery("select e from Entreprise e where e.email = :email", Entreprise.class)
		 			.setParameter("email", email);
		 try{
			 return q.getSingleResult();			
		 }catch(NoResultException e){
			 return null;
		 }
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
	public Entreprise sauvegarder(Entreprise entreprise) {
		return em.merge(entreprise);
	}

	@Override
	public Boolean supprimer(int id) {
		Entreprise e = chercherParID(id);
		if(e != null)
		{
			em.remove(e);
			return (chercherParID(id) == null);
		}
		return false;
	}
	
	//Méthode permettant de trouver des rôles pour les ajouts d'entreprises
	@Override
	public Role chercherRoleParID(int id) {
		return em.find(Role.class, id);
	}
}
