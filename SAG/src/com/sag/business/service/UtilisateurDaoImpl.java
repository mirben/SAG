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

import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;

/**
 * @version 1
 * @author Benjamin MIRETTI
 * 
 */
@Remote(value = UtilisateurDao.class)
@Stateless(name = "utilisateurDao", description = "Dao pour utilisateur")
@Startup
public class UtilisateurDaoImpl implements UtilisateurDao{
	
	@PersistenceContext(unitName = "SAG_PU")
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		System.out.println("Dao init : " + this);
		System.out.println("em = " + em);
	}
	/**
	 * Récupérer un utlisateur par son id
	 * @param id
	 * @return Utilisateur
	 */
	@Override
	public Utilisateur chercherParID(int id){
		return em.find(Utilisateur.class, id);
	}
	
	/**
	 * Récupérer un utilisateur par son email
	 * @param email
	 * @return Utilsateur
	 */
	@Override
	public Utilisateur chercherParEmail(String email){
		 TypedQuery<Utilisateur> q = em
					.createQuery("select u from Utilisateur u where u.email = :email", Utilisateur.class)
		 			.setParameter("email", email);
		 try{
			 return q.getSingleResult();			
		 }catch(NoResultException e){
			 return null;
		 }
	}
	
	/**
	 * Récupérer des utilsateur par statut
	 * @param statut
	 * @return Collection<Utilsateur>
	 */
	@Override
	public Collection<Utilisateur> chercherParStatut(StatutUtilisateur statut) {
		TypedQuery<Utilisateur> q = em
				.createQuery("select u from Utilisateur u where u.statut = :statut", Utilisateur.class)
				.setParameter("statut", statut);
		return q.getResultList();			
	}
	
	/**
	 * Récupérer tous les utilsateur
	 * @return Collection<Utilsateur>
	 */
	@Override
	public Collection<Utilisateur> chercherTous(){
		return em.createQuery("FROM Utilisateur", Utilisateur.class).getResultList();
	}
	
	/**
	 * Récupérer tous les utilsateur par block de offset avec une limite
	 * @return Collection<Utilsateur>
	 * @param int offset : Index de début pour le résultat (commence à 0)
	 * @param int limite : Nombre max de résultats à retourner
	 */
	@Override
	public Collection<Utilisateur> chercherTous(int offset , int limite){
		return em.createQuery("FROM Utilisateur", Utilisateur.class)
				.setFirstResult(offset).setMaxResults(limite).getResultList();

	}
	
	/**
	 * Sauvagarder un utilsateur, 
	 * @param utilisateur
	 * @return Collection<Utilsateur>
	 */
	@Override
	public Utilisateur sauvegarder(Utilisateur utilisateur){
		return em.merge(utilisateur);
	}
	
	/**
	 * Supprimer un utilisateur, 
	 * @param id
	 * @return Boolean
	 */
	@Override
	public Boolean supprimer(int id){
		Utilisateur u = chercherParID(id);
		if(u != null)
		{
			em.remove(u);
			return (chercherParID(id) == null);
		}
		return false;
	}
	
	/**
	 * Chercher un rôle pour créer un nouvel utilsateur, 
	 * @param id
	 * @return Role : le rôle correspondant à l'id
	 */
	@Override
	public Role chercherRoleParID(int id) {
		return em.find(Role.class, id);
	}
	
}


