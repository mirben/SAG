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

import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.UtilisateurDao;

public class UtilisateurDaoTest {

	private static Context initial;
	private static UtilisateurDao utilisateurDao;
	private static Vector<Utilisateur> utilisateursTest;

	Utilisateur utilisateur = new Utilisateur();

	@AfterClass
	public static void clean() {
		for (Utilisateur curEtd : utilisateursTest) {
			utilisateurDao.supprimer(curEtd.getId());
		}
		DomaineDaoTest.clean();
	}

	@BeforeClass
	public static void init() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao");

		assertTrue(o instanceof UtilisateurDao);
		utilisateurDao = (UtilisateurDao) o;

		DomaineDaoTest.init();
		utilisateursTest = new Vector<Utilisateur>();

		Utilisateur user1, user2;
		user1 = new Utilisateur("concac1@cc.com", StatutUtilisateur.ACTIF,
				utilisateurDao.chercherRoleParID(1));
		user2 = new Utilisateur("concac2@cc.com", StatutUtilisateur.ACTIF,
				utilisateurDao.chercherRoleParID(1));

		utilisateursTest.add(utilisateurDao.sauvegarder(user1));
		utilisateursTest.add(utilisateurDao.sauvegarder(user2));

	}

	@Test
	public void testSauvegarder() {
		System.out.println("**** Test de la méthode sauvagarder ****");
		Utilisateur expected = new Utilisateur("concac@cc.com",
				StatutUtilisateur.INACTIF, utilisateurDao.chercherRoleParID(2));

		// test ajout
		expected = utilisateurDao.sauvegarder(expected);
		Utilisateur actual = utilisateurDao.chercherParID(expected.getId());

		assertEquals(expected, actual);

		// test modif
		expected.setEmail("fuck@cc.com");
		actual = utilisateurDao.sauvegarder(expected);
		assertEquals("fuck@cc.com", actual.getEmail());
		utilisateurDao.supprimer(actual.getId());


	}

	@Test
	public void testChercherParID() {
		System.out.println("**** Test de la méthode chercherParID ****");
		Utilisateur actual = utilisateursTest.firstElement();
		Utilisateur expected = utilisateurDao.chercherParID(actual.getId());

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(utilisateurDao.chercherParID(0));

	}

	@Test
	public void testChercherParEmail() {
		System.out.println("**** Test de la méthode chercherParEmail ****");

		Utilisateur actual = utilisateursTest.firstElement();
		Utilisateur expected = utilisateurDao.chercherParEmail(actual
				.getEmail());

		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);

		// test trouvé
		assertEquals(expected, actual);

		// test non trouvé
		assertNull(utilisateurDao.chercherParEmail("nop@nop.com"));
	}

	@Test
	public void testChercherParStatut() {
		System.out
				.println("**** Test de la méthode chercherChercherParStatut ****");
		Collection<Utilisateur> experted = utilisateurDao.chercherParStatut(StatutUtilisateur.ACTIF);

		System.out.println(experted.size());
		assertTrue(experted.contains(utilisateursTest.firstElement()));
	}

	@Test
	public void testChercherTous() {
		
		Collection<Utilisateur> users = utilisateurDao.chercherTous();
		Iterator<Utilisateur> it = users.iterator();

		while (it.hasNext()) // tant que j'ai un element non parcouru
		{
			System.err.println("------Utilisateur -------");
			System.err.println(it.next());
			// mes opérations
		}
		assertTrue(utilisateurDao.chercherTous().size() >= utilisateursTest.size());

	}

	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode chercherTous [a, b] ****");
		int expected = utilisateurDao.chercherTous().size();
		Collection<Utilisateur> users = utilisateurDao.chercherTous();
		Iterator<Utilisateur> it = users.iterator();

		while (it.hasNext()) // tant que j'ai un element non parcouru
		{
			System.err.println("------Utilisateur -------");
			System.err.println(it.next());
			// mes opérations
		}

		Collection<Utilisateur> users1 = utilisateurDao.chercherTous(0, 1);

		int actual = utilisateurDao.chercherTous(0, 2).size();
		System.err.println("expected : " + expected);
		System.err.println("actual : " + actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testSupprimer() {
		System.out.println("**** Test de la méthode sauvagarder ****");
		Utilisateur user = new Utilisateur("blala@cc.com",
				StatutUtilisateur.INACTIF, utilisateurDao.chercherRoleParID(2));

		
		Utilisateur expected = utilisateurDao.sauvegarder(user);


		assertNotNull(utilisateurDao.chercherParID(expected.getId()));
		System.out.println("expected avant supprimer: " + expected);

		utilisateurDao.supprimer(expected.getId());
		System.out.println("expected après supprimer: " + expected);

		assertNull(utilisateurDao.chercherParID(expected.getId()));

		assertFalse(utilisateurDao.supprimer(0));
	}

	@Test
	public void testChercherRoleParID() {
		System.out.println("**** Test de la méthode ChercherRoleParID  ****");

		// test non trouvé
		assertNull(utilisateurDao.chercherRoleParID(0));
	}

}
