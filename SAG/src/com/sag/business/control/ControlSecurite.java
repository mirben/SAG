package com.sag.business.control;

import javax.ejb.EJB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sag.business.service.DomaineDao;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.OffreDao;
import com.sag.business.service.UtilisateurDao;

/**
 * 
 * @author Joël Karcher
 *
 */
@Controller()
@RequestMapping("/")
public class ControlSecurite {
	
	@EJB(mappedName = "java:global/SAG/DomaineDaoImpl!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/UtilisateurDaoImpl!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	@EJB(mappedName = "java:global/SAG/EntrepriseDaoImpl!com.sag.business.service.EntrepriseDao")
	EntrepriseDao companyDao;
	@EJB(mappedName = "java:global/SAG/EtudiantDaoImpl!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	@EJB(mappedName = "java:global/SAG/OffreDaoImpl!com.sag.business.service.OffreDao")
	OffreDao offerDao;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Méthode mappé sur /login et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	/**
	 * Méthode mappé sur /accessdenied et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, denied
	 */
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	/**
	 * Méthode mappé sur /logoutconfirmn et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, logout_confirm
	 */
	@RequestMapping(value = "/logoutconfirm", method = RequestMethod.GET)
	public String logout(Model model) {
		return "logout_confirm";
	}
	
	/**
	 * Méthode mappé sur /logoutconfirmn et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, logout_confirm
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		//Modification prévues ce lundi 17-03
		return "admin";
	}
}
