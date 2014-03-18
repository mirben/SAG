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
import org.springframework.web.bind.annotation.RequestParam;

import com.sag.business.model.Domaine;
import com.sag.business.model.Offre;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
import com.sag.business.service.OffreDao;
import com.sag.business.service.UtilisateurDao;

/**
 * 
 * @author Joël Karcher
 *
 */
@Controller()
@RequestMapping("/SAG")
public class ControlOffre {
	
	@EJB(mappedName = "java:global/SAG/offreDao!com.sag.business.service.OffreDao")
	OffreDao offerDao;
	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Méthode mappé sur /search_offers et les requêtes GET Recherche les offres correspondantes au mot clé
	 * 
	 * @param keyword
	 *            Le mot clé à rechercher
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list
	 */
	@RequestMapping(value = "/search_offers", method = RequestMethod.GET)
	public String searchOffers(
			@RequestParam(value = "id", required = true) String keyword, Model model) {
		Collection<Offre> offersmatch = offerDao.chercherParMotCle(keyword);
		model.addAttribute("offers", offersmatch);
		logger.info("search offers with " + keyword);
		return "list";
	}
	
	/**
	 * Méthode mappé sur /domain_list et les requêtes GET Recherche les offres du domaine
	 * 
	 * @param domaine
	 *            L'identifiant du domaine des offres retournées
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, domain_list
	 */
	@RequestMapping(value = "/domain_list", method = RequestMethod.GET)
	public String listOffersByDomain(
			@RequestParam(value = "idd", required = true) Integer domaineNumber, Model model) {
		Domaine d = domDao.chercherParID(domaineNumber);
		Collection<Offre> offers = offerDao.chercherTous();
		Collection<Offre> offersdom = new Vector<Offre>();
		for (Offre offre : offers) {
			if(offre.getDomaines().contains(d))
				offersdom.add(offre);
		}
		model.addAttribute("offers_domaine", offersdom);
		model.addAttribute("domaine_courant", d);
		logger.info("get domain's offers " + domaineNumber);
		return "domain_list";
	}
	
	/**
	 * Méthode mappé sur /offer_propose et les requêtes GET Recherche les offres proposées par l'utilisateur connecté
	 * 
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list_propose
	 */
	@RequestMapping(value = "/offer_propose", method = RequestMethod.GET)
	public String listOffersPropose(Model model) {
		Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		Collection<Offre> offers = offerDao.chercherTous();
		Collection<Offre> offersprop = new Vector<Offre>();
		for (Offre offre : offers) {
			if(offre.getEmetteur().equals(uco))
				offersprop.add(offre);
		}
		model.addAttribute("offer_propose", offersprop);
		model.addAttribute("user_co", uco);
		logger.info("get user's offers " + uco.getId());
		return "list_propose";
	}
}
