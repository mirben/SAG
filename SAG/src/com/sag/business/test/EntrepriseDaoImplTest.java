package com.sag.business.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Entreprise;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EntrepriseDao;

public class EntrepriseDaoImplTest {
	static EntrepriseDao entrepriseDao;
	Entreprise e = new Entreprise();
	@BeforeClass
	public void setUp() {
		Role role = new Role();
		role.setId(1);
		role.setNom("moderateur");
		
		e.setAdresse("10 rue Antoine, 13001 Marseille");
		e.setEmail("fnac@gmail.com");
		e.setNom("fnac");
		e.setPassword("000000");
		e.setRole(role);
		e.setSiret("an125");
		e.setSiteWeb("www.fnac.com");
		e.setStatut(StatutUtilisateur.ACTIF);
		entrepriseDao.sauvagarder(e);

	}

	@Test
	public void testChercherParID() {
		System.out.println(entrepriseDao.chercherParID(e.getId()));
		assertTrue(e == entrepriseDao.chercherParID(e.getId()));
	}

	@Test
	public void testChercherParEmail() {
		System.out.println(entrepriseDao.chercherParEmail("fnac@gmail.com"));
		assertTrue(e == entrepriseDao.chercherParEmail("fnac@gmail.com"));
	}

	@Test
	public void testChercherTous() {
		assertTrue(entrepriseDao.chercherTous().size() > 0);
	}

	@Test
	public void testChercherTousIntInt() {
		assertTrue(entrepriseDao.chercherTous(1,5).size() > 0);

	}

	@Test
	public void testSauvagarder() {
		Role role = new Role();
		role.setId(2);
		role.setNom("utilisateur");
		
		e.setAdresse("10 rue Falque, 13006 Marseille");
		e.setEmail("amazone@gmail.com");
		e.setNom("amazone");
		e.setPassword("000000");
		e.setRole(role);
		e.setSiret("cbr600");
		e.setSiteWeb("www.fnac.com");
		e.setStatut(StatutUtilisateur.INACTIF);
		assertTrue(e == entrepriseDao.sauvagarder(e));
	}

	@Test
	public void testSupprimer() {
		entrepriseDao.supprimer(e.getId());
		assertTrue(entrepriseDao.chercherParID(e.getId()) == null);
	}

}
