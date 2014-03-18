package com.sag.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
	}

	@BeforeClass
	public static void init() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/etudiantDao!com.sag.business.service.EtudiantDao");

		assertTrue(o instanceof EtudiantDao);
		etudiantDao = (EtudiantDao) o;

		etudiantsTest = new Vector<Etudiant>();

		Etudiant etud1, etud2;

		Date date1 = com.sag.business.test.util.string2Date("13/01/2000");
		Date date2 = com.sag.business.test.util.string2Date("13/03/2005");

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

		// Test ajout8

		Date date = com.sag.business.test.util.string2Date("13/03/2005");
		Etudiant expected = new Etudiant("etdt3@lmail.com",
				StatutUtilisateur.INACTIF, etudiantDao.chercherRoleParID(1),
				"e45875", "SARKOZYT", "NicolaT", date,
				"20 rue Enfer 13001 Marseille", "http://ncolab.com",
				"Master 3 Politique", null);
		expected = etudiantDao.sauvegarder(expected);
		System.out.println("expected ************************** : "
				+ expected.getId() + ":" + expected);

		Etudiant actual = etudiantDao.chercherParID(expected.getId());
		System.out.println("actual ************************** : " + actual);
		assertEquals(expected, actual);

		etudiantDao.supprimer(expected.getId());

	}

	@Ignore("no tested")
	@Test
	public void testChercherParEnt() {
		assertTrue(etudiant == etudiantDao.chercherParEnt("x12546"));
	}

	@Ignore("no tested")
	@Test
	public void testChercherParDomaine() {
		assertTrue(etudiantDao.chercherParDomaine("Cinémat").contains(etudiant));
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
