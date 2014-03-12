package com.sag.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Qualifier;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sag.business.model.Domaine;
import com.sag.business.service.DomaineDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:**/sag-servlet.xml"})
public class DomaineDaoImplTest {
	

	@Autowired
	private DomaineDao domaineDao;
	
	@BeforeClass
	public static void init(){
		
	}
	
	/**
     * 
     * @throws NamingException
     */

	@Test
	public void testSauvagarder() {
		//Test ajout
		Domaine domaine = new Domaine("Informatique");
		domaineDao.sauvagarder(domaine);
		System.out.println(domaine);
		Domaine savedDomaine = domaineDao.chercherParID(domaine.getId());
		assertEquals(domaine, savedDomaine);
		
		//test modification
		savedDomaine.setNom("Cinéma");
		domaineDao.sauvagarder(savedDomaine);
		Domaine modifiedDomaine = domaineDao.chercherParID(savedDomaine.getId());
		assertEquals(savedDomaine, modifiedDomaine);

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
