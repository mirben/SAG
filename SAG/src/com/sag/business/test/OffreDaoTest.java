package com.sag.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.Type;
import com.sag.business.service.OffreDao;

@Transactional
public class OffreDaoTest {
	private static Context initial;
	private static OffreDao offreDao;
	private static Vector<Offre> offresTest;
	
	@AfterClass
	public static void clean(){
		for (Offre curOffre : offresTest) {
			offreDao.supprimer(curOffre.getId());
		}
		//On nettoie aussi les jeux de test des autres lcasses de test utilisées dans l'ordre inverse d'initialisation
		EntrepriseDaoTest.clean();
		DomaineDaoTest.clean();
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
        DomaineDaoTest.init();
        EntrepriseDaoTest.init();
        
		//Ajout de deux offres pour les tests
		//Utilise Sauvegarder donc si le test ne passe pas, cette partie ne fonctionne pas
		Calendar dateA = Calendar.getInstance();
		Calendar dateF = Calendar.getInstance();
		dateF.add(Calendar.DAY_OF_MONTH, 5);
        //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAjout = new Date(dateA.getTimeInMillis());
		Date dateFin = new Date(dateF.getTimeInMillis());
		
        
        offresTest = new Vector<Offre>();
		Offre testOffre1, testOffre2;

		
		testOffre1 = new Offre("offreTest1", "Description offre 1 DomaineTest1", Type.CONCRET, 5, 10, 100.0, StatutOffre.ACTIVE,
								dateAjout, dateFin, dateAjout, "www.test.com", EntrepriseDaoTest.entreprisesTest.firstElement(),
								EntrepriseDaoTest.entreprisesTest.firstElement(), null, null, null );
		testOffre2 = new Offre("offreTest2", "Description offre 2", Type.CONCRET, 5, 10, 100.0, StatutOffre.ACTIVE,
				dateAjout, dateFin, dateAjout, "www.test.com", EntrepriseDaoTest.entreprisesTest.firstElement(),
				EntrepriseDaoTest.entreprisesTest.firstElement(), null, null, null );
		offresTest.add(offreDao.sauvegarder(testOffre1));
		offresTest.add(offreDao.sauvegarder(testOffre2));
	}
    
	@Test
	public void testSauvagarder() {
		System.out.println("**** Test de la méthode testSauvegarder ****");
		Calendar dateA = Calendar.getInstance();
		Calendar dateF = Calendar.getInstance();
		dateF.add(Calendar.DAY_OF_MONTH, 5);
        //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAjout = new Date(dateA.getTimeInMillis());
		Date dateFin = new Date(dateF.getTimeInMillis());
		//Test ajout
		Offre offre = new Offre("offreTest3", "Description offre 3", Type.CONCRET, 5, 10, 100.0, StatutOffre.ACTIVE,
				dateAjout, dateFin, dateAjout, "www.test.com", EntrepriseDaoTest.entreprisesTest.firstElement(),
				EntrepriseDaoTest.entreprisesTest.firstElement(), null, null, null );
		offre = offreDao.sauvegarder(offre);
		System.out.println("offre ajoutée : " + offre);
		Offre savedOffre = offreDao.chercherParID(offre.getId());
		System.out.println("offre récupérée : " + savedOffre);
		assertEquals(offre, savedOffre);
		
		//test modification
		savedOffre.setDescription("Description modifiée");
		System.out.println("offre modifiée : " + savedOffre);
		offreDao.sauvegarder(savedOffre);
		Offre modifiedOffre = offreDao.chercherParID(savedOffre.getId());
		System.out.println("offre récupérée : " + modifiedOffre);
		assertEquals(savedOffre, modifiedOffre);

		offresTest.add(modifiedOffre);
	}
	
	@Test
	public void testChercherParID() {
		System.out.println("**** Test de la méthode ChercherParID ****");
		Offre offre = offresTest.firstElement();
		System.out.println("offre à trouver : " + offre);
		Offre fetchedOffre = offreDao.chercherParID(offre.getId());
		System.out.println("offre trouvée : " + fetchedOffre);
		assertEquals(offre, fetchedOffre);
		
		//Test avec un ID inexistant
		assertNull(offreDao.chercherParID(-99));
	}

	@Test
	public void testChercherTous() {
		System.out.println("**** Test de la méthode ChercherTous ****");
		System.out.println("Nombre minimal d'offres à trouver : " + offresTest.size());
		Collection<Offre> fetchedOffers = offreDao.chercherTous();
		assertTrue(fetchedOffers.size() >= offresTest.size() );
	}

	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode ChercherTous avec bornes ****");
		System.out.println("Nombre de maximum d'offres à trouver : 2");
		Collection<Offre> fetchedOffers = offreDao.chercherTous(1,2);
		int actual = fetchedOffers.size();
		int expected = 2;
		assertEquals(expected, actual);
	}

	@Test
	public void testChercherParMotCle(){
		System.out.println("**** Test de la méthode ChercherParMotCle ****");
		System.out.println("Nombre minimal d'offres à trouver : 1" );
		Collection<Offre> fetchedOffers = offreDao.chercherParMotCle("DomaineTest1");
		System.out.println(fetchedOffers);
		assertTrue(fetchedOffers.size() >= 1 );
	}
	
	@Test
	public void testChercherParMotCleFaux(){
		//Test avec un nom inexistant
	assertNull(offreDao.chercherParMotCle("BlaBla"));
	}

	@Test
	public void testSupprimer() {
		System.out.println("**** Test de la méthode Supprimer ****");
		Offre offre = offresTest.firstElement();
		//Test suppression valide
		Collection<Offre> fetchedOffers = offreDao.chercherTous();
		int expected = fetchedOffers.size();
		System.out.println("Nombre d'offres avant suppression : " + expected--);
		System.out.println("Id de l'offre à supprimer : " +offre.getId());
		assertTrue(offreDao.supprimer(offre.getId()));
		fetchedOffers = offreDao.chercherTous();
		System.out.println("Nombre d'offres après suppression : " + fetchedOffers.size());
		assertEquals(expected, fetchedOffers.size());
		
	}
	
	@Test
	public void testSupprimerErreur(){
		assertFalse(offreDao.supprimer(-99));
	}

}
