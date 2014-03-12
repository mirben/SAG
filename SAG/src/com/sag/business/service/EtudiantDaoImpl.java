package com.sag.business.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;

/**
 * @version 1
 * @author tuan 
 * Interface d'objet d'accès aux données Etudiant.
 */

@Repository("entrepriseDao")
@Transactional(readOnly = true)
public class EtudiantDaoImpl implements EtudiantDao {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


	@Override
	public Etudiant chercherParID(int id) {
		return em.find(Etudiant.class, id);
	}
	
	@Override
	public Etudiant chercherParEnt(String logENT) {
		return (Etudiant) em
				.createQuery("select e from Etudiant e where e.logENT = logENT")
				.setParameter("logENT", logENT).getSingleResult();
	}

	@Override
	public Collection<Etudiant> chercherParDomaine(String nom_domaine) {
		// TODO Auto-generated method stub
		return (Collection<Etudiant>) em.createQuery(
				"SELECT e FROM Etudiant e,Etudiant_Domaine ed, Domaine d WHERE ed.Etudiant_ID_U = e.id and ed.domaine_id = d.id and d.nom = :nom_d",
				Etudiant.class).setParameter("nom_d", nom_domaine)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Etudiant> chercherParStatut(StatutUtilisateur statut) {
		return (Collection<Etudiant>) em
				.createQuery("select e from Etudiant e where e.statut = statut")
				.setParameter("statut", statut).getResultList();
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
	public Etudiant sauvagarder(Etudiant etudiant) {
				return em.merge(etudiant);
	}

	@Override
	public Boolean supprimer(int id) {
		em.remove(id);
		return null;
	}


}
