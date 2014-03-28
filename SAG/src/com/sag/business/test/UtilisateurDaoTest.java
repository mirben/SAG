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

import com.sag.business.model.Domaine;
import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.UtilisateurDao;

/**
 * Classe de test pour UtilisateurDaoImpl
 * @author Benjamin MIRETTI
 */
public class UtilisateurDaoTest {

	/**
	 *Le contexte de l'application 
	 */
	private static Context initial;
	
	/**
	 * La Dao des utilisateurs
	 */
	private static UtilisateurDao utilisateurDao;
	
	/**
	 * Liste des utilisateurs de test
	 */
	private static Vector<Utilisateur> utilisateursTest;

	Utilisateur utilisateur = new Utilisateur();

	/**
	 * Méthode Afterclass qui nettoie les données de test inséré dans la base
	 */
	@AfterClass
	public static void clean() {
		for (Utilisateur curEtd : utilisateursTest) {
			utilisateurDao.supprimer(curEtd.getId());
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

	/**
	 * Méthode de test pour sauvegarder()
	 */
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

	/**
	 * Méthode de test pour chercherParID()
	 */
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

	/**
	 * Méthode de test pour chercherParEmail()
	 */
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

	/**
	 * Méthode de test pour chercherParStatut()
	 */
	@Test
	public void testChercherParStatut() {
		System.out
				.println("**** Test de la méthode chercherChercherParStatut ****");
		Collection<Utilisateur> experted = utilisateurDao.chercherParStatut(StatutUtilisateur.ACTIF);

		System.out.println(experted.size());
		assertTrue(experted.contains(utilisateursTest.firstElement()));
	}

	/**
	 * Méthode de test pour chercherTous()
	 */
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

	/**
	 * Méthode de test pour chercherTous(int, int)
	 */
	@Test
	public void testChercherTousIntInt() {
		System.out.println("**** Test de la méthode chercherTous [a, b] ****");
		System.out.println("Nombre de maximum d'utilisateurs à trouver : 2");

		int actual = utilisateurDao.chercherTous(1, 2).size();
		int expected = 2;
		System.err.println("expected : " + expected);
		System.err.println("actual : " + actual);
		assertEquals(expected, actual);
	}

	/**
	 * Méthode de test pour supprimer()
	 */
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

	/**
	 * Méthode de test pour chercherRoleParID()
	 */
	@Test
	public void testChercherRoleParID() {
		System.out.println("**** Test de la méthode ChercherRoleParID  ****");

		// test non trouvé
		assertNull(utilisateurDao.chercherRoleParID(0));
	}

}
