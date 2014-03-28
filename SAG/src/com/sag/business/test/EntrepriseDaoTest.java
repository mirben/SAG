package com.sag.business.test;

import static org.junit.Assert.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EntrepriseDao;

/**
 * Classe de test des méthode de EntrepriseDaoImpl
 * 
 * @author Tuan NGUYEN
 * 
 */
public class EntrepriseDaoTest {

       /**
	*Le contexte de l'application
	*/
	private static Context initial;
	
       /**
	* La Dao des entreprises
	*/
	private static EntrepriseDao entrepriseDao;
	
	/**
	 * Liste des entreprises de test
	 */
	public static Vector<Entreprise> entreprisesTest;

	/**
	 * Méthode Afterclass qui nettoie les données de test inséré dans la base
	 */
	@AfterClass
	public static void clean() {
		for (Entreprise curEtpr : entreprisesTest) {
			entrepriseDao.supprimer(curEtpr.getId());
		}
	}

	/**
	 * Méthode Beforeclass qui récupère la Dao et initialise les données de test
	 * @throws NamingException
	 */
	@BeforeClass
	public static void init() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao");
		assertTrue(o instanceof EntrepriseDao);
		entrepriseDao = (EntrepriseDao) o;

		
		entreprisesTest = new Vector<Entreprise>();
		Entreprise testEtpr1, testEtpr2;

		Role role1 = entrepriseDao.chercherRoleParID(1);
		Role role2 = entrepriseDao.chercherRoleParID(2);

		testEtpr1 = new Entreprise("email1@gmail.com", StatutUtilisateur.ACTIF,
				role1, "fnac", "f123", "11 rue BABPU 13006 Marseille",
				"http://www.fnac.com", "012345");
		testEtpr2 = new Entreprise("email2@gmail.com", StatutUtilisateur.ACTIF,
				role2, "amazon", "a345", "11 rue Colorado 13011 Cololoco",
				"http://www.amazon.com", "124566");
		entreprisesTest.add(entrepriseDao.sauvegarder(testEtpr1));
		entreprisesTest.add(entrepriseDao.sauvegarder(testEtpr2));

	}
	
	/**
	 * Méthode de test pour sauvegarder()
	 */
	@Test
	public void testSauvegarder() {
		System.out.println("**** Test de la méthode sauvagarder ****");

		// Test ajout
		Role role = entrepriseDao.chercherRoleParID(1);

		Entreprise expected = new Entreprise("lol@lolmail.com",
				StatutUtilisateur.ACTIF, role, "entrepriseNom", "en12458",
				"11 rue Paradis 45785 Hell", "http://www.entrepriseNom.com",
				"012345");
		expected = entrepriseDao.sauvegarder(expected);
		Entreprise actual = entrepriseDao.chercherParID(expected.getId());
		System.out.print("expected ************************** : " + expected);
		System.out.print("actual ************************** : " + actual);
		assertEquals(expected, actual);

		// test modification
		expected.setNom("facModif");
		actual = entrepriseDao.sauvegarder(expected);
		System.out.println(actual);
		assertEquals(actual.getNom(), "facModif");

		entrepriseDao.supprimer(expected.getId());

	}

	/**
	 * Méthode de test pour chercherParID()
	 */
	@Test
	public void testChercherParID() {
		System.out.println("**** Test de la méthode chercherParID ****");

		Entreprise actual = entreprisesTest.firstElement();
		Entreprise expected = entrepriseDao.chercherParID(actual.getId());

		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(entrepriseDao.chercherParID(0));

	}

	/**
	 * Méthode de test pour chercherParEmail()
	 */
	@Test
	public void testChercherParEmail() {
		System.out.println("**** Test de la méthode chercherParEmail ****");

		Entreprise actual = entreprisesTest.firstElement();
		Entreprise expected = entrepriseDao.chercherParEmail(actual.getEmail());

		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(entrepriseDao.chercherParEmail("nop@nop.com"));
	}

	/**
	 * Méthode de test pour chercherTous()
	 */
	@Test
	public void testChercherTous() {
		System.out.println("**** Test de la méthode chercherTous ****");

		assertTrue(entrepriseDao.chercherTous().size() >= entreprisesTest
				.size());
	}

	/**
	 * Méthode de test pour chercherTous(int, int)
	 */
	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode chercherTous [a, b] ****");
		int expected = entrepriseDao.chercherTous().size();

		int actual = entrepriseDao.chercherTous(0, expected).size();
		System.err.println("expected : " + expected);
		System.err.println("actual : " + actual);
		assertEquals(expected, actual);

	}

	/**
	 * Méthode de test pour supprimer()
	 */
	@Test
	public void testSupprimer() {
		System.out.println("**** Test de la méthode Supprimer  ****");

		Entreprise entreprise = new Entreprise("nikki@gmail.com",
				StatutUtilisateur.ACTIF, entrepriseDao.chercherRoleParID(1),
				"nikki", "nikki125478", "11 rue Colorado 13011 Cololoco",
				"http://www.nikki.com", "4587465");
		Entreprise expected = entrepriseDao.sauvegarder(entreprise);
		assertNotNull(entrepriseDao.chercherParID(expected.getId()));
		System.out.println("expected avant supprimer: " + expected);

		entrepriseDao.supprimer(expected.getId());
		System.out.println("expected après supprimer: " + expected);

		assertNull(entrepriseDao.chercherParID(expected.getId()));

		assertFalse(entrepriseDao.supprimer(0));
	}

	/**
	 * Méthode de test pour chercherRoleParID()
	 */
	public void testChercherRoleParID() {
		
		System.out.println("**** Test de la méthode ChercherRoleParID ****");

		String actual = "ROLE_ADMIN";
		String expected = entrepriseDao.chercherRoleParID(1).getNom();
		//test trouvé
		assertEquals(expected, actual);
		actual = "ROLE_USER";
		expected = entrepriseDao.chercherRoleParID(2).getNom();
		//test trouvé

		assertEquals(expected, actual);
		
		Role object = entrepriseDao.chercherRoleParID(0);
		//test non trouvé

		assertNull(object);

	}

}
