package com.sag.business.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.service.DomaineDao;

/**
 * Classe de test pour DomaineDaoImpl
 * @author Benjamin MIRETTI
 */
public class DomaineDaoTest {
	
	/**
	 *Le contexte de l'application 
	 */
	private static Context initial;
	
	/**
	 * La Dao des domaines
	 */
	private static DomaineDao domaineDao;
	
	/**
	 * Liste des domaines de test
	 */
	public static Vector<Domaine> domainesTest;
	
	/**
	 * Méthode Afterclass qui nettoie les données de test inséré dans la base
	 */
	@AfterClass
	public static void clean(){
		for (Domaine curDom : domainesTest) {
			domaineDao.supprimer(curDom.getId());
		}
	}
	
    /**
     * Méthode Beforeclass qui récupère la Dao et initialise les données de test
     * @throws NamingException
     */
    @BeforeClass
	public static void init()throws NamingException{
        initial = new InitialContext();
        Object o = initial
                .lookup("java:global/classpath.ear/SAG/domaineDao!com.sag.business.service.DomaineDao");
        assertTrue(o instanceof DomaineDao);
        domaineDao = (DomaineDao) o;
        
		//Ajout de deux domaines pour les tests
		//Utilise Sauvegarder donc si le test ne passe pas, cette partie ne fonctionne pas
		domainesTest = new Vector<Domaine>();
		Domaine testDom1, testDom2;
		testDom1 = new Domaine("DomaineTest1");
		testDom2 = new Domaine("DomaineTest2");
		domainesTest.add(domaineDao.sauvegarder(testDom1));
		domainesTest.add(domaineDao.sauvegarder(testDom2));
	}
	

	/**
	 * Méthode de test pour sauvegarder()
	 */
	@Test
	public void testSauvegarder() {
		System.out.println("**** Test de la méthode testSauvegarder ****");
		//Test ajout
		Domaine domaine = new Domaine("Test sauvegarder");
		domaine = domaineDao.sauvegarder(domaine);
		System.out.println("domaine ajouté : " + domaine);
		Domaine savedDomaine = domaineDao.chercherParID(domaine.getId());
		System.out.println("domaine récupéré : " + savedDomaine);
		assertEquals(domaine, savedDomaine);
		
		//test modification
		savedDomaine.setNom("Cinéma");
		System.out.println("domaine modifié : " + savedDomaine);
		domaineDao.sauvegarder(savedDomaine);
		Domaine modifiedDomaine = domaineDao.chercherParID(savedDomaine.getId());
		System.out.println("domaine récupéré : " + modifiedDomaine);
		assertEquals(savedDomaine, modifiedDomaine);

		domainesTest.add(modifiedDomaine);
	}
	
	/**
	 * Méthode de test pour chercherParID()
	 */
	@Test
	public void testChercherParID() {
		System.out.println("**** Test de la méthode ChercherParID ****");
		Domaine domaine = domainesTest.firstElement();
		System.out.println("domaine à trouver : " + domaine);
		Domaine fetchedDomaine = domaineDao.chercherParID(domaine.getId());
		System.out.println("domaine trouvé : " + fetchedDomaine);
		assertEquals(domaine, fetchedDomaine);
		
		//Test avec un ID inexistant
		assertNull(domaineDao.chercherParID(-99));
	}
	
	/**
	 * Méthode de test pour chercherParNom()
	 */
	@Test
	public void testChercherParNom(){
		System.out.println("**** Test de la méthode ChercherParNom ****");
		Domaine domaine = domainesTest.firstElement();
		System.out.println("domaine à trouver : " + domaine);
		Domaine fetchedDomaine = domaineDao.chercherParNom(domaine.getNom());
		System.out.println("domaine trouvé : " + fetchedDomaine);
		assertEquals(domaine, fetchedDomaine);
	}

	/**
	 * Méthode de test pour chercherParNom() avec valeurs erronées
	 */
	@Test
	public void testChercherParNomFaux(){
		//Test avec un nom inexistant
	assertNull(domaineDao.chercherParNom("BlaBla"));
	}
	
	/**
	 * Méthode de test pour chercherTous()
	 */
	@Test
	public void testChercherTous() {
		System.out.println("**** Test de la méthode ChercherTous ****");
		System.out.println("Nombre minimal de domaines à trouver : " + domainesTest.size());
		Collection<Domaine> fetchedDomains = domaineDao.chercherTous();
		assertTrue(fetchedDomains.size() >= domainesTest.size() );
	}

	/**
	 * Méthode de test pour chercherParTous(int, int)
	 */
	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode ChercherTous avec bornes ****");
		System.out.println("Nombre de maximum de domaines à trouver : 2");
		Collection<Domaine> fetchedDomains = domaineDao.chercherTous(1,2);
		int actual = fetchedDomains.size();
		int expected = 2;
		assertEquals(expected, actual);
	}

	/**
	 * Méthode de test pour supprimer()
	 */
	@Test
	public void testSupprimer() {
		System.out.println("**** Test de la méthode Supprimer ****");
		Domaine domaine = domainesTest.firstElement();
		//Test suppression valide
		Collection<Domaine> fetchedDomains = domaineDao.chercherTous();
		int expected = fetchedDomains.size();
		System.out.println("Nombre de domaines avant suppression : " + expected--);
		System.out.println("Id du domaine à supprimer : " +domaine.getId());
		assertTrue(domaineDao.supprimer(domaine.getId()));
		fetchedDomains = domaineDao.chercherTous();
		System.out.println("Nombre de domaines après suppression : " + fetchedDomains.size());
		assertEquals(expected, fetchedDomains.size());
		
	}
	
	/**
	 * Méthode de test pour supprimer() avec valeurs erronées
	 */
	@Test
	public void testSupprimerErreur(){
		assertFalse(domaineDao.supprimer(-99));
	}
	
}
