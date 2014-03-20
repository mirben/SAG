package com.sag.business.control;

import java.util.Collection;
import java.util.Vector;

import javax.ejb.EJB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sag.business.model.Domaine;
import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.OffreDao;
import com.sag.business.service.UtilisateurDao;

@Controller()
@RequestMapping("/")
public class ControlAdmin {
	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao companyDao;
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	@EJB(mappedName = "java:global/SAG/offreDao!com.sag.business.service.OffreDao")
	OffreDao offerDao;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Méthode mappé sur /admin et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, admin
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		model.addAttribute("user_co", uco);
		Collection<Offre> coffer = offerDao.chercherTous();
		Collection<Offre> cofferv = new Vector<Offre>();
		Collection<Offre> cofferw = new Vector<Offre>();
		
		for (Offre offre : coffer) {
			StatutOffre s = offre.getStatut();
			if(s==StatutOffre.ACTIVE || s==StatutOffre.TERMINEE)
				cofferv.add(offre);
			else if(offre.getStatut()==StatutOffre.ENVOYEE)
				cofferw.add(offre);
		}
		model.addAttribute("offers_validated", cofferv);
		model.addAttribute("offers_waiting", cofferw);
		
		Collection<Domaine> cdom =  domDao.chercherTous();
		model.addAttribute("domains", cdom);
		
		Collection<Etudiant> cetuact = etuDao.chercherParStatut(StatutUtilisateur.ACTIF);
		model.addAttribute("users_enabled", cetuact);
		
		Collection<Etudiant> cetuinact = etuDao.chercherParStatut(StatutUtilisateur.INACTIF);
		model.addAttribute("users_waiting", cetuinact);
		
		Collection<Entreprise> ccomp = companyDao.chercherTous();
		model.addAttribute("companys", ccomp);
		
		return "admin";
	}
}
