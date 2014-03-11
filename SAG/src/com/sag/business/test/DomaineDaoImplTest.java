package com.sag.business.test;

import static org.junit.Assert.*;


import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.service.DomaineDaoImpl;

public class DomaineDaoImplTest {
	DomaineDaoImpl domaineDao;
	

	@Test
	public void testChercherParID() {
		System.out.println(domaineDao.chercherParID(1));
		assertNotNull(domaineDao.chercherParID(1));
		
	}

	@Test
	public void testChercherTous() {
		System.out.println(domaineDao.chercherTous());

		assertFalse(1 == domaineDao.chercherTous().size());

	}

	@Test
	public void testChercherTousIntInt() {
		System.out.println(domaineDao.chercherTous(1,5));
		int actual = domaineDao.chercherTous(1,5).size();
		int expected = 5;
		assertEquals(expected, actual);
	}

	@Test
	public void testSauvagarder() {
		Domaine domaine = new Domaine();
		domaine.setNom("Informatique");
		domaineDao.sauvagarder(domaine);
		
		
	}

	@Test
	public void testSupprimer() {
		fail("Not yet implemented");
	}

}
