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

import org.junit.BeforeClass;
import org.junit.Test;

import com.sag.business.model.Domaine;
import com.sag.business.model.Etudiant;
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;

public class EtudiantDaoTest {
	static EtudiantDao etudiantDao;
	Context initial;

	Etudiant etudiant = new Etudiant();

	@BeforeClass
	public void init() {
		Role role = new Role();
		role.setId(2);
		role.setNom("utilisateur");

		etudiant.setAdresse("168 avenue de Luminy, 13009 Marseille");
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;

		try {
			date = sdf.parse("16/03/1985");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		etudiant.setDateNaiss(date);
		etudiant.setEmail("email@hotmail.com");
		etudiant.setFormation("Master Informatique");
		etudiant.setLogENT("x12546");
		etudiant.setNom("HOLLANDE");
		etudiant.setRole(role);
		etudiant.setSiteWeb("www.elyse.com");
		etudiant.setStatut(StatutUtilisateur.ACTIF);

		etudiantDao.sauvagarder(etudiant);

		 Domaine domain = new Domaine();
	}

	public EtudiantDaoTest() throws NamingException {
		initial = new InitialContext();
		Object o = initial
				.lookup("java:global/classpath.ear/SAG/dao!com.sag.business.model.EtudiantDao");
		Assert.assertTrue(o instanceof EntrepriseDao);
		etudiantDao = (EtudiantDao) o;
	}
	@Test
	public void testChercherParEnt() {
		assertTrue(etudiant == etudiantDao.chercherParEnt("x12546"));
	}

	@Test
	public void testChercherParDomaine() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherParStatut() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherTous() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherTousIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSauvagarder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimer() {
		fail("Not yet implemented");
	}

}
