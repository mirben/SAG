package com.sag.business.control;

import static com.sag.business.control.Util.getAuthority;

import java.security.Principal;
import java.util.Collection;

import javax.ejb.EJB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sag.business.model.Domaine;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.UtilisateurDao;

/**
 * 
 * @author Joël Karcher
 *
 */
@Controller()
@RequestMapping("/")
public class ControlDomain {

	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao companyDao;

	protected final Log logger = LogFactory.getLog(getClass());
	
	@ModelAttribute("user_co")
	Utilisateur username(Principal p) {
		if(getAuthority() == "ROLE_ENTR")
			return companyDao.chercherParEmail(p.getName());
		return etuDao.chercherParEnt(p.getName());
	}
	
	@ModelAttribute("domains")
	Collection<Domaine> domaines(){
		return domDao.chercherTous();
	}
	
	/**
	 * Créer un domaine, à partir de la base de données si l'argument existe,
	 * ou ex-nihilo sinon
	 * 
	 * @param id
	 *            L'indentifiant du domaine
	 * @return Le domaine récupéré depuis la base de données
	 */
	@ModelAttribute
	public Domaine newDomain(
			@RequestParam(value = "id", required = false) Integer domaineNumber) {
		if (domaineNumber != null) {
			logger.info("find domain " + domaineNumber);
			return domDao.chercherParID(domaineNumber);
		}
		Domaine d = new Domaine();
		logger.info("new domain = " + d);
		d.setNom("");
		
		return d;
	}
	
	/**
	 * Méthode mappé sur /edit_domain et les requêtes GET Modifie ou crée un domaine
	 * 
	 * @param d
	 *            Le domaine recupéré
	 * @param model
	 *            L'objet Model de spring
	 * @return Le nom de la jsp à afficher, new_domain, ou redirection vers la
	 *          page admin si domaine null
	 */
	@RequestMapping(value = "/edit_domain", method = RequestMethod.GET)
	public String editDomain(@ModelAttribute Domaine d, Model model) {
		if (d != null){
			model.addAttribute("domain", d);
			return "new_domain";
		}
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /edit_domain et les requêtes POST Modifie ou crée un domaine
	 * 
	 * @param d
	 *            Le domaine recupéré
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/edit_domain", method = RequestMethod.POST)
	public String saveDomain(@ModelAttribute Domaine d, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "new_domain";
		}
		
		if(d==null) return "redirect:admin";
		logger.info("save domain " + d.getNom());
		try {
			domDao.sauvegarder(d);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom de domaine déjà utilisé");
			return "new_domain";
		}
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /delete_domain et les requêtes GET Supprime un domaine
	 * 
	 * @param domaineNumber
	 *            L'identifiant du domaine à supprimer
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/delete_domain", method = RequestMethod.GET)
	public String deleteDomain(
			@RequestParam(value = "id", required = true) Integer domaineNumber) {
		domDao.supprimer(domaineNumber);
		logger.info("delete domain " + domaineNumber);
		return "redirect:admin";
	}
}
