package com.sag.business.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		Offre o = chercherParID(id);
		if(o != null)
		{
			em.remove(o);
			return (chercherParID(id) == null);
		}
		return false;
	}

	@Override
	public Collection<Offre> chercherParMotCle(String mots) {
		List<String> listMots = Arrays.asList(mots.trim().split(" "));
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT O")
		.append("FROM Offre O, Offre_Domaine OD, Domaine D")
		.append("WHERE OD.Offre_id = O.id AND OD.DOMAINE_ID = D.Id")
		.append("AND (");
		int i = 0;
		while(i < listMots.size())
		{
			if(listMots.get(i).isEmpty()){
				listMots.remove(i);
				continue;
			}
			sb.append("upper(titre) LIKE '%:mot" + i +"%' ")
			.append("OR upper(description) LIKE '%:mot" + i +"%' ")
			.append("OR upper(D.nom) LIKE '%:mot" + i +"%' OR ");
			++i;
		}
		;
		TypedQuery<Offre> q = em
				.createQuery(sb.toString().replaceFirst("OR $", ")"), Offre.class);
	 	for(i = 0; i < listMots.size(); ++i){
	 		q.setParameter("mot"+i, listMots.get(i));
	 	}
	 	return q.getResultList();
	}

}
