package com.sag.business.control;

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
import com.sag.business.service.UtilisateurDao;

@Controller()
@RequestMapping("/")
public class ControlDomain {

	@EJB(mappedName = "java:global/SAG/DomaineDaoImpl!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/UtilisateurDaoImpl!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;

	protected final Log logger = LogFactory.getLog(getClass());
	
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
	
	@RequestMapping(value = "/edit_domain", method = RequestMethod.GET)
	public String editDomain(@ModelAttribute Domaine d, Model model) {
		Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		model.addAttribute("user_co", uco);
		if (d != null){
			model.addAttribute("domain", d);
			return "new_domain";
		}
		return "redirect:admin";
	}
	
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
			Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
					.getContext().getAuthentication().getName());
			model.addAttribute("user_co", uco);
			return "new_domain";
		}
		return "redirect:admin";
	}
	
	@RequestMapping(value = "/delete_domain", method = RequestMethod.GET)
	public String deleteDomain(
			@RequestParam(value = "id", required = true) Integer domaineNumber) {
		domDao.supprimer(domaineNumber);
		logger.info("delete domain " + domaineNumber);
		return "redirect:admin";
	}
	
	
}
