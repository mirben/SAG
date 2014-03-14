package com.sag.business.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Vector;

import com.sag.business.model.Domaine;
import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EntrepriseDao;
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
	public void init() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/etudiantDao!com.sag.business.service.EtudiantDao");

		assertTrue(o instanceof EtudiantDao);
		etudiantDao = (EtudiantDao) o;
		etudiantsTest = new Vector<Etudiant>();

		Etudiant etud1, etud2;

		Date date1 = com.sag.business.test.util.string2Date("13/01/2000");
		Date date2 = com.sag.business.test.util.string2Date("13/03/2005");

		etud1 = new Etudiant("etud1@gmail.com", StatutUtilisateur.ACTIF,
				etudiantDao.chercherRoleParID(1), "e12458", "SIMPSON", "Beth",
				date1, "13, rue de l'Enfer, 131313 Paradis", "sit.com", "M2ISL",
				"Informatique");
	}

	public EtudiantDaoTest() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/dao!com.sag.business.model.EtudiantDao");
		Assert.assertTrue(o instanceof EntrepriseDao);
		etudiantDao = (EtudiantDao) o;
	}

	@Test
	public void testChercherParID() {
		System.out.println(etudiantDao.chercherParID(etudiant.getId()));
		assertTrue(etudiant == etudiantDao.chercherParID(etudiant.getId()));
	}

	@Test
	public void testChercherParEnt() {
		assertTrue(etudiant == etudiantDao.chercherParEnt("x12546"));
	}

	@Test
	public void testChercherParDomaine() {
		assertTrue(etudiantDao.chercherParDomaine("Cinémat").contains(etudiant));
	}

	@Test
	public void testChercherParStatut() {
		assertTrue(etudiantDao.chercherParStatut(StatutUtilisateur.ACTIF)
				.contains(etudiant));
	}

	@Test
	public void testChercherTous() {
		assertTrue(etudiantDao.chercherTous().contains(etudiant));
	}

	@Test
	public void testChercherTousIntInt() {
		assertTrue(etudiantDao.chercherTous(1, 5).size() > 4);
	}

	@Test
	public void testSauvagarder() {
		etudiant.setNom("SARKOZY");
		Etudiant actual = etudiantDao.sauvegarder(etudiant);
		assertEquals("SARKOZY", actual.getNom());
	}

	@Test
	public void testSupprimer() {
		etudiantDao.supprimer(etudiant.getId());
		assertNull(etudiantDao.chercherParEnt(etudiant.getLogENT()));
	}

}
