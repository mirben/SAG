package com.sag.business.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EtudiantDao;

/**
 * Classe de test des méthode de EtudiantDao
 * 
 * @author Tuan NGUYEN
 * 
 */
public class EtudiantDaoTest {
	
	/**
	 *Le contexte de l'application 
	 */
	private static Context initial;
	
	/**
	 * La Dao des étudiants
	 */
	private static EtudiantDao etudiantDao;
	
	/**
	 * Liste des étudiants de test
	 */
	private static Vector<Etudiant> etudiantsTest;

	Etudiant etudiant = new Etudiant();

	/**
	 * Méthode Afterclass qui nettoie les données de test inséré dans la base
	 */
	@AfterClass
	public static void clean() {
		for (Etudiant curEtd : etudiantsTest) {
			etudiantDao.supprimer(curEtd.getId());
		}
		DomaineDaoTest.clean();
	}

        /**
         * Méthode Beforeclass qui récupère la Dao et initialise les données de test
         * @throws NamingException
         */
	@BeforeClass
	public static void init() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/etudiantDao!com.sag.business.service.EtudiantDao");

		assertTrue(o instanceof EtudiantDao);
		etudiantDao = (EtudiantDao) o;

		DomaineDaoTest.init();
		etudiantsTest = new Vector<Etudiant>();

		Etudiant etud1, etud2;

		Date date1 = new Date(Calendar.getInstance().getTimeInMillis());
		Date date2 = new Date(Calendar.getInstance().getTimeInMillis());

		etud1 = new Etudiant("etdt1@lmail.com", StatutUtilisateur.ACTIF,
				etudiantDao.chercherRoleParID(1), "e12345", "HOLLANDE",
				"Françcois", date1, "13 rue Enfer 13001 Marseille",
				"http://fholan.com", "Master 2 Politique",
				DomaineDaoTest.domainesTest);
		etud2 = new Etudiant("etdt2@lmail.com", StatutUtilisateur.INACTIF,
				etudiantDao.chercherRoleParID(2), "e54321", "SARKOZY",
				"Nicolas", date2, "20 rue Enfer 13001 Marseille",
				"http://ncolas.com", "Master 1 Politique",
				DomaineDaoTest.domainesTest);

		etudiantsTest.add(etudiantDao.sauvegarder(etud1));
		etudiantsTest.add(etudiantDao.sauvegarder(etud2));
		System.out.println(etudiantsTest.firstElement());
	}

	/**
	 * Méthode de test pour sauvegarder()
	 */
	@Test
	public void testSauvegarder() {
		System.out.println("**** Test de la méthode sauvagarder ****");

		// Test ajouté

		Date date = new Date(Calendar.getInstance().getTimeInMillis());

		Etudiant expected = new Etudiant("etdt3@lmail.com",
				StatutUtilisateur.INACTIF, etudiantDao.chercherRoleParID(1),
				"e45875", "SARKOZYT", "NicolaT", date,
				"20 rue Enfer 13001 Marseille", "http://ncolab.com",
				"Master 3 Politique", DomaineDaoTest.domainesTest);
		expected = etudiantDao.sauvegarder(expected);

		Etudiant actual = etudiantDao.chercherParID(expected.getId());
		System.out.println("expected ************************** : "
				+ expected.getId() + ":" + expected);
		System.out.println("actual ************************** : "
				+ expected.getId() + ":" + actual);
		assertEquals(expected, actual);

		// Test modification
		expected.setNom("ANELKA");
		actual = etudiantDao.sauvegarder(expected);
		assertEquals(actual.getNom(), "ANELKA");

		etudiantDao.supprimer(expected.getId());

	}

	/**
	 * Méthode de test pour chercherParID()
	 */
	@Test
	public void testChercherParID() {
		System.out.println("**** Test de la méthode chercherParID ****");

		Etudiant actual = etudiantsTest.firstElement();
		Etudiant expected = etudiantDao.chercherParID(actual.getId());

		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(etudiantDao.chercherParID(0));

	}
	
	/**
	 * Méthode de test pour chercherParEnt()
	 */
	@Test
	public void testChercherParEnt() {
		System.out.println("**** Test de la méthode chercherParLogENT ****");

		Etudiant actual = etudiantsTest.firstElement();
		Etudiant expected = etudiantDao.chercherParEnt(actual.getLogENT());

		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(etudiantDao.chercherParEnt("nob000"));
	}

	/**
	 * Méthode de test pour chercherParDomaine()
	 */
	@Test
	public void testChercherParDomaine() {
		System.out.println("**** Test de la méthode chercherParDomaine ****");

		Collection<Etudiant> etudiants = etudiantDao
				.chercherParDomaine("DomaineTest1");
		Iterator<Etudiant> it = etudiants.iterator();

		while (it.hasNext()) // tant que j'ai un element non parcouru
		{
			System.err.println(it.next());
			// mes opérations
		}
		assertTrue(etudiantDao.chercherParDomaine("DomaineTest1").contains(
				etudiantsTest.firstElement()));
	}

	/**
	 * Méthode de test pour chercherParStatut()
	 */
	@Test
	public void testChercherParStatut() {
		System.out
				.println("**** Test de la méthode chercherChercherParStatut ****");
		Collection<Etudiant> experted = etudiantDao
				.chercherParStatut(StatutUtilisateur.ACTIF);

		System.out.println(experted.size());
		assertTrue(experted.contains(etudiantsTest.firstElement()));

		experted = etudiantDao.chercherParStatut(StatutUtilisateur.INACTIF);
		assertTrue(experted.contains(etudiantsTest.elementAt(1)));
	}

	/**
	 * Méthode de test pour chercherTous()
	 */
	@Test
	public void testChercherTous() {
		System.out.println("**** Test de la méthode chercherTous ****");

		assertTrue(etudiantDao.chercherTous().size() >= etudiantsTest.size());
	}

	/**
	 * Méthode de test pour chercherTous(int, int)
	 */
	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode chercherTous [a, b] ****");
		int expected = etudiantDao.chercherTous().size();

		int actual = etudiantDao.chercherTous(0, expected).size();
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
		Date date = new Date(Calendar.getInstance().getTimeInMillis());

		Etudiant expected = new Etudiant("etdt3@lmail.com",
				StatutUtilisateur.INACTIF, etudiantDao.chercherRoleParID(1),
				"e45875", "SARKOZYT", "NicolaT", date,
				"20 rue Enfer 13001 Marseille", "http://ncolab.com",
				"Master 3 Politique", DomaineDaoTest.domainesTest);
		expected = etudiantDao.sauvegarder(expected);

		assertNotNull(etudiantDao.chercherParID(expected.getId()));
		System.out.println("expected avant supprimer: " + expected);

		etudiantDao.supprimer(expected.getId());
		System.out.println("expected après supprimer: " + expected);

		assertNull(etudiantDao.chercherParID(expected.getId()));

		assertFalse(etudiantDao.supprimer(0));
	}

	/**
	 * Méthode de test pour chercherRoleParID()
	 */
	@Test
	public void TestChercherRoleParID() {
		System.out.println("**** Test de la méthode ChercherRoleParID  ****");

		// test non trouvé
		assertNull(etudiantDao.chercherRoleParID(0));

	}
}
