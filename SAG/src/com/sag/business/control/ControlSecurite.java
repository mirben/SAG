package com.sag.business.control;

import javax.ejb.EJB;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sag.business.model.Etudiant;
import com.sag.business.service.EtudiantDao;

/**
 * 
 * @author Joël Karcher
 *
 */
@Controller()
@RequestMapping("/")
public class ControlSecurite {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	
	/**
	 * Créer un etudiant
	 * 
	 * @return L'etudiant crée
	 */
	@ModelAttribute
	public Etudiant newUser(@RequestParam(value = "id", required = false) Integer studentNumber) {
		Etudiant e = new Etudiant();
		logger.info("new user = " + e);
		return e;
	}
	
	/**
	 * Méthode mappé sur /login et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("Request login mapping");
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
	 * Méthode mappé sur /register et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, register
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute @Valid Etudiant e,Model model) {
		if (e != null){
			model.addAttribute("user", e);
			return "register";
		}
		return "login";
	}
	
	/**
	 * Méthode mappé sur /register et les requêtes POST Crée un etudiant
	 * 
	 * @param d
	 *            L'etudiant recupéré
	 * @return Redirection vers un autre mapping, login
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveEtudiant(@ModelAttribute Etudiant etu, BindingResult result,
			Model model) {
//		if (result.hasErrors()) {
//			return "register";
//		}
		if(etu==null) return "redirect:register";
		logger.info("save student " + etu.getNom());
		try {
			etuDao.sauvegarder(etu);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'utilisateur déjà utilisé");
			return "register";
		}
		return "redirect:login";
	}
	
	/**
	 * Méthode mappé sur /home et les requêtes GET
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, home
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String accueil(Model model) {
		return "home";
	}
}
