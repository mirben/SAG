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

import com.sag.business.model.Etudiant;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;

/**
 * Implémentation de EtudiantDao
 * @author NGUYEN Tuan
 * @author MIRETTI Benjamin
 *
 */
@Remote(value = EtudiantDao.class)
@Stateless(name = "etudiantDao", description = "Dao pour étudiant")
@Startup
public class EtudiantDaoImpl implements EtudiantDao {

	@PersistenceContext(unitName = "SAG_PU")
	private EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("Dao init : " + this);
		System.out.println("em = " + em);
	}

	@Override
	public Etudiant chercherParID(int id) {
		return em.find(Etudiant.class, id);
	}

	@Override
	public Etudiant chercherParEnt(String logENT) {
		TypedQuery<Etudiant> q = em.createQuery(
				"select e from Etudiant e where e.logENT = :logENT",
				Etudiant.class).setParameter("logENT", logENT);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Collection<Etudiant> chercherParDomaine(String nom_domaine) {
		TypedQuery<Etudiant> q = em
				.createQuery("SELECT e FROM Etudiant e left join e.domaines as d WHERE d.nom = :nom_d",
						Etudiant.class)
				.setParameter("nom_d", nom_domaine);
		return q.getResultList();
	}

	@Override
	public Collection<Etudiant> chercherParStatut(StatutUtilisateur statut) {
		TypedQuery<Etudiant> q = em.createQuery(
				"select e from Etudiant e where e.statut = :statut",
				Etudiant.class).setParameter("statut", statut);
		return q.getResultList();
	}

	@Override
	public Collection<Etudiant> chercherTous() {
		return em.createQuery("FROM Etudiant", Etudiant.class).getResultList();
	}

	@Override
	public Collection<Etudiant> chercherTous(int offset, int limite) {
		return em.createQuery("FROM Etudiant", Etudiant.class)
				.setFirstResult(offset).setMaxResults(limite).getResultList();
	}

	@Override
	public Etudiant sauvegarder(Etudiant etudiant) {
		return em.merge(etudiant);
	}

	@Override
	public Boolean supprimer(int id) {
		Etudiant e = chercherParID(id);
		if (e != null) {
			em.remove(e);
			return (chercherParID(id) == null);
		}
		return false;
	}

	/**
	 * Chercher un rôle pour créer un nouvel utilsateur,
	 * 
	 * @param id
	 * @return Role : le rôle correspondant à l'id
	 */
	@Override
	public Role chercherRoleParID(int id) {
		return em.find(Role.class, id);
	}

}
