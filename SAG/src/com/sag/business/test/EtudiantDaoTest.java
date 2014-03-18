package com.sag.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EtudiantDao;

/**
 * Classe de test des méthode de EtudiantDao
 * 
 * @version 1
 * @author tuan
 * 
 */
public class EtudiantDaoTest {
	private static Context initial;
	private static EtudiantDao etudiantDao;
	private static Vector<Etudiant> etudiantsTest;

	Etudiant etudiant = new Etudiant();

	@AfterClass
	public static void clean() {
		for (Etudiant curEtd : etudiantsTest) {
			etudiantDao.supprimer(curEtd.getId());
		}
		DomaineDaoTest.clean();
	}

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
				"http://fholan.com", "Master 2 Politique", null);
		etud2 = new Etudiant("etdt2@lmail.com", StatutUtilisateur.INACTIF,
				etudiantDao.chercherRoleParID(2), "e54321", "SARKOZY",
				"Nicolas", date2, "20 rue Enfer 13001 Marseille",
				"http://ncolas.com", "Master 1 Politique", null);

		etudiantsTest.add(etudiantDao.sauvegarder(etud1));
		etudiantsTest.add(etudiantDao.sauvegarder(etud2));
		System.out.println(etudiantsTest.firstElement());
	}


	@Test
	public void testSauvagarder() {
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
		System.out.println("actual ************************** : " + expected.getId() + ":" + actual);
		assertEquals(expected, actual);
		
		// Test modification
		expected.setNom("ANELKA");
		actual = etudiantDao.sauvegarder(expected);
		assertEquals(actual.getNom(), "ANELKA");


		etudiantDao.supprimer(expected.getId());

	}
	

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

	@Ignore
	@Test
	public void testChercherParDomaine() {
		
		assertTrue(etudiantDao.chercherParDomaine("DomaineTest1").contains(etudiantsTest.firstElement()));
	}

	@Ignore("no tested")
	
	@Test
	public void testChercherParStatut() {
		assertTrue(etudiantDao.chercherParStatut(StatutUtilisateur.ACTIF)
				.contains(etudiant));
	}

	@Ignore("no tested")
	@Test
	public void testChercherTous() {
		assertTrue(etudiantDao.chercherTous().contains(etudiant));
	}

	@Ignore("no tested")
	@Test
	public void testChercherTousIntInt() {
		assertTrue(etudiantDao.chercherTous(1, 5).size() > 4);
	}

	@Ignore("no tested")
	@Test
	public void testSupprimer() {
		etudiantDao.supprimer(etudiant.getId());
		assertNull(etudiantDao.chercherParEnt(etudiant.getLogENT()));
	}

}
