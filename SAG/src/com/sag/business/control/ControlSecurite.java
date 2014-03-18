package com.sag.business.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Joël Karcher
 *
 */
@Controller()
@RequestMapping("/SAG")
public class ControlSecurite {
	
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
}
