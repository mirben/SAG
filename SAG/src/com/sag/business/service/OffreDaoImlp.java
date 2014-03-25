package com.sag.business.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sag.business.model.Image;
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

//	public void sauvegarder_image(Image img){
//		if(img!=null)
//			em.persist(img);
//	}
//	
//	public Image chercherImageParUrl(String url){
//		try {
//			return em.createQuery("SELECT I FROM Image I WHERE I.url = :url", Image.class).setParameter("url", url).getSingleResult();
//		}
//		catch(NoResultException e){
//			return null;
//		}
//	}
	
	@Override
	public Offre sauvegarder(Offre offre) {
//		Collection<Image> ldefimg = new Vector<Image>();
//		for (Image img : offre.getImages()){
//			Image img_bdd = chercherImageParUrl(img.getUrl());
//			if(img_bdd==null){
//				sauvegarder_image(img);
//				ldefimg.add(img);
//			}
//			else{
//				ldefimg.add(img);
//			}
//		}
//		offre.setImages(ldefimg);
		
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
		mots.toUpperCase();
		System.out.println(mots);
		List<String> listMots = Arrays.asList(mots.trim().split(" "));
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT O ")
		.append("FROM Offre O LEFT JOIN O.domaines as D ")
		.append("WHERE ");
		int i = 0;
		while(i < listMots.size())
		{
			System.out.println("Mot" + i + " = " +listMots.get(i));
			if(listMots.get(i).isEmpty()){
				listMots.remove(i);
				continue;
			}
			sb.append("upper(titre) LIKE :mot" + i +" ")
			.append("OR upper(description) LIKE :mot" + i +" ")
			.append("OR upper(D.nom) LIKE :mot" + i +" OR ");
			++i;
		}
		;
		TypedQuery<Offre> q = em
				.createQuery(sb.toString().replaceFirst("OR $", ""), Offre.class);
	 	for(i = 0; i < listMots.size(); ++i){
	 		q.setParameter("mot"+i, "%"+listMots.get(i)+"%");
	 	}
	 	return q.getResultList();
	}

}
