package com.sag.business.test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.service.DomaineDao;

public class DomaineDaoImplTest {
	
	final Context initial;
	final DomaineDao domaineDao;
	
	@BeforeClass
	public void init(){
		
	}
	
	/**
     * 
     * @throws NamingException
     */
    public DomaineDaoImplTest() throws NamingException {
        initial = new InitialContext();
        Object o = initial
                .lookup("java:global/classpath.ear/SAG/dao!com.sag.business.model.DomaineDao");
        Assert.assertTrue(o instanceof DomaineDao);
        domaineDao = (DomaineDao) o;
    }

	@Test
	public void testSauvagarder() {
		Domaine domaine = new Domaine("Informatique");
		domaineDao.sauvagarder(domaine);
		System.out.println(domaine.);

	}
	
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
	public void testSupprimer() {
		fail("Not yet implemented");
	}

}
