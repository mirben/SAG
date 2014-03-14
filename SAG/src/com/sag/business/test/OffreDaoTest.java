package com.sag.business.test;

import static org.junit.Assert.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.model.Etudiant;
import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.Type;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.OffreDao;

public class OffreDaoTest {
	static EtudiantDao etudiantDao;
	static OffreDao offreDao;
	Context initial;

	Offre offre = new Offre();
	@BeforeClass
	public void init() {
		Etudiant etudiant = etudiantDao.chercherParID(1);
		
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateAjout = null;
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateAjout = sdf.parse("22/01/2014");
			dateDebut = sdf.parse("30/01/2014");
			dateFin = sdf.parse("28/4/2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		offre.setDateAjout(dateAjout);
		offre.setDateDebut(dateDebut);
		offre.setDateFin(dateFin);
		offre.setDescription("Un produit bidon");
		
		@SuppressWarnings("unchecked")
		Set<Domaine> domaines =  (Set<Domaine>) new ArrayList<Domaine>();
		domaines.add(new Domaine("Cinéma"));
		offre.setDomaines(domaines);
		offre.setEmetteur(etudiant);
		offre.setParticipantsMax(100);
		offre.setParticipantsMin(70);
		offre.setPrix(500);
		offre.setSiteWeb("www.siteWeb.net");
		offre.setStatut(StatutOffre.BROUILLON);
		offre.setTitre("Finding Némo");
		offre.setType(Type.THEORIQUE);
		
		offreDao.sauvegarder(offre);
	}
		
	public OffreDaoTest() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/dao!com.sag.business.model.OffreDao");
		Assert.assertTrue(o instanceof OffreDao);
		offreDao = (OffreDao) o;
	}


	@Test
	public void testChercherParID() {
		System.out.println(offreDao.chercherParID(offre.getId()));
		assertTrue(offre == offreDao.chercherParID(offre.getId()));
	}

	@Test
	public void testChercherTous() {
		assertTrue(offreDao.chercherTous().contains(offre));

	}

	@Test
	public void testChercherTousIntInt() {
		assertTrue(offreDao.chercherTous(1, 5).size() > 4);
	}

	@Test
	public void testSauvagarder() {
		offre.setDescription("Un produit modifié");
		Offre actual = offreDao.sauvegarder(offre);
		assertEquals("Un produit modifié", actual.getDescription());
	}

	@Test
	public void testSupprimer() {
		offreDao.supprimer(offre.getId());
		assertNull(offreDao.chercherParID(offre.getId()));

	}

}
