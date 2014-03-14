package com.sag.business.test;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sag.business.model.Domaine;
import com.sag.business.service.DomaineDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:sag-servlet.xml"})
public class DomaineDaoImplTest {
	

	final Context initial;
	final DomaineDao domaineDao;

    /**
     * 
     * @throws NamingException
     */
    public DomaineDaoImplTest() throws NamingException {
        initial = new InitialContext();
        Object o = initial
                .lookup("java:global/classpath.ear/SAG/domaineDao!com.sag.business.service.DomaineDao");
        assertTrue(o instanceof DomaineDao);
        domaineDao = (DomaineDao) o;
    }
    
//	@BeforeClass
//	public static void init(){
//		
//	}
	
	/**
     * 
     * @throws NamingException
     */

	@Test
	public void testSauvagarder() {
		//Test ajout
		Domaine domaine = new Domaine("Informatique");
		domaine = domaineDao.sauvegarder(domaine);
		System.out.println("domaine ajouté : " + domaine);
		Domaine savedDomaine = domaineDao.chercherParID(domaine.getId());
		System.out.println("domaine récupéré : " + savedDomaine);
		assertEquals(domaine, savedDomaine);
		
		//test modification
		savedDomaine.setNom("Cinéma");
		System.out.println("domaine modifié : " + savedDomaine);
		domaineDao.sauvegarder(savedDomaine);
		Domaine modifiedDomaine = domaineDao.chercherParID(savedDomaine.getId());
		System.out.println("domaine récupéré : " + modifiedDomaine);
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
