package com.sag.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.model.Etudiant;
import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.Type;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.OffreDao;
import com.sag.business.service.UtilisateurDao;

public class OffreDaoTest {
	private static Context initial;
	private static OffreDao offreDao;
	private static UtilisateurDao utilisateurDao;
	private static EtudiantDao etudiantDao;
	private static EntrepriseDao entrepriseDao;
	private static Vector<Offre> offresTest;
	
	@AfterClass
	public static void clean(){
		for (Offre curOffre : offresTest) {
			offreDao.supprimer(curOffre.getId());
		}
		//On nettoie aussi les jeux de test des autres lcasses de test utilisées dans l'ordre inverse d'initialisation
		DomaineDaoImplTest.clean();
	}
    /**
     * 
     * @throws NamingException
     */

    @BeforeClass
	public static void init() throws NamingException{
        initial = new InitialContext();
        Object o = initial
                .lookup("java:global/classpath.ear/SAG/offreDao!com.sag.business.service.OffreDao");
        assertTrue(o instanceof OffreDao);
        offreDao = (OffreDao) o;
        
        //On utilise le jeu de tests des autres classes de test
        DomaineDaoImplTest.init();
        EntrepriseDaoTest.init();
        
		//Ajout de deux offres pour les tests
		//Utilise Sauvegarder donc si le test ne passe pas, cette partie ne fonctionne pas
		offresTest = new Vector<Offre>();
		Offre testOffre1, testOffre2;
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 03, 17);
		
		testOffre1 = new Offre("offreTest1", "Description offre 1 DomaineTest1", Type.CONCRET, 5, 10, 100.0, StatutOffre.ACTIVE,
								Calendar.set(), calendar.getTime(), calendar.getTime(),  {
								}, );
		testOffre2 = new Offre();
		domainesTest.add(domaineDao.sauvegarder(testDom1));
		domainesTest.add(domaineDao.sauvegarder(testDom2));
	}
	

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
		domaines.add(new Offre("Cinéma"));
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
	public void testChercherParMotCle(){
		
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
