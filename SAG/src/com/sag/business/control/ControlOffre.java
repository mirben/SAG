package com.sag.business.control;

import java.util.Collection;
import java.util.Vector;

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
import com.sag.business.model.Etudiant;
import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
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
public class ControlOffre {

	@EJB(mappedName = "java:global/SAG/offreDao!com.sag.business.service.OffreDao")
	OffreDao offerDao;
	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etudiantDao;


	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Méthode mappé sur /search_offers et les requêtes GET Recherche les offres
	 * correspondantes au mot clé
	 * 
	 * @param keyword
	 *            Le mot clé à rechercher
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list
	 */
	@RequestMapping(value = "/search_offers", method = RequestMethod.GET)
	public String searchOffers(
			@RequestParam(value = "key", required = true) String keyword,
			Model model) {
		Collection<Offre> offersmatch = offerDao.chercherParMotCle(keyword);
		model.addAttribute("offers", offersmatch);
		logger.info("search offers with " + keyword);
		return "list";
	}
	
	/**
	 * Méthode mappé sur /list_offer et les requêtes GET Recherche les offres
	 * 
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list
	 */
	@RequestMapping(value = "/list_offer", method = RequestMethod.GET)
	public String listOffers(Model model) {
		Collection<Offre> offers = offerDao.chercherTous();
		model.addAttribute("offers", offers);
		logger.info("get offer's list ");
		return "list";
	}
	
	/**
	 * Méthode mappé sur /domain_list et les requêtes GET Recherche les offres
	 * du domaine
	 * 
	 * @param domaine
	 *            L'identifiant du domaine des offres retournées
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, domain_list
	 */
	@RequestMapping(value = "/domain_list", method = RequestMethod.GET)
	public String listOffersByDomain(
			@RequestParam(value = "idd", required = true) Integer domaineNumber,
			Model model) {
		Domaine d = domDao.chercherParID(domaineNumber);
		Collection<Offre> offers = offerDao.chercherTous();
		Collection<Offre> offersdom = new Vector<Offre>();
		for (Offre offre : offers) {
			if (offre.getDomaines().contains(d))
				offersdom.add(offre);
		}
		model.addAttribute("offers_domaine", offersdom);
		model.addAttribute("domaine_courant", d);
		logger.info("get domain's offers " + domaineNumber);
		return "domain_list";
	}

	/**
	 * Méthode mappé sur /offer_propose et les requêtes GET Recherche les offres
	 * proposées par l'utilisateur connecté
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
			if (offre.getEmetteur().equals(uco))
				offersprop.add(offre);
		}
		model.addAttribute("offer_propose", offersprop);
		model.addAttribute("user_co", uco);
		//logger.info("get user's offers " + uco.getId());
		return "list_propose";
	}

	/**
	 * Méthode créer une nouvelle offre, ou récupérer une offre exsite
	 * 
	 * @param idOffre
	 * @return
	 */
	@ModelAttribute
	public Offre newOffre(
			@RequestParam(value = "id", required = false) Integer idOffre) {
		if (idOffre != null) {
			logger.info("offre trouvé" + idOffre);
			return offerDao.chercherParID(idOffre);
		}
		Offre o = new Offre();
		logger.info("new product = " + o);
		return o;
	}

	/**
	 * 
	 * Méthode mappé sur /edit_offer et les requêtes GET mettre une offre dans
	 * dans la formule edition
	 * 
	 * @param offre
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit_offer", method = RequestMethod.GET)
	public String editOffre(
			@RequestParam(value = "id", required = true) int idOffre,
			Model model) {
		Offre offre = offerDao.chercherParID(idOffre);
		model.addAttribute("offer", offre);

		return "new_offre";
	}

	/**
	 * Méthode mappé sur /disable_offer et les requêtes GET désactivé une offre
	 * 
	 * @param idOffre
	 * @return
	 */
	@RequestMapping(value = "/disable_offer", method = RequestMethod.GET)
	public String desactiverOffre(
			@RequestParam(value = "id", required = true) int idOffre) {
		Offre offre = offerDao.chercherParID(idOffre);
		offre.setStatut(StatutOffre.TERMINEE);
		offerDao.sauvegarder(offre);
		logger.info("offer désactivé" + offre);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /delete_offer et les requêtes GET supprimer une offre
	 * 
	 * @param idOffre
	 * @return
	 */
	@RequestMapping(value = "/delete_offer", method = RequestMethod.GET)
	public String supprimerOffre(
			@RequestParam(value = "id", required = true) int idOffre) {

		offerDao.supprimer(idOffre);
		logger.info("offer supprimé" + idOffre);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /détail_offer et les requêtes GET supprimer une offre
	 * 
	 * @param idOffre
	 * @return
	 */
	@RequestMapping(value = "/detail_offer", method = RequestMethod.GET)
	public String detailOffre(
			@RequestParam(value = "id", required = true) int idOffre,
			Model model) {
		Offre offre = offerDao.chercherParID(idOffre);
		model.addAttribute(offre);
		logger.info("offer détail" + offre);

		return "detail_offre";
	}

	/**
	 * Méthode mappé sur /save_offer et les requêtes POST Sauvagarder une offre
	 * en brouillon
	 * 
	 * @param offre
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/save_offer", method = RequestMethod.POST)
	public String sauvegardeOffre(@ModelAttribute Offre offre, Model model,
			BindingResult result) {

		offre.setStatut(StatutOffre.BROUILLON);
		if (result.hasErrors()) {
			return "offer_propose";
		}
		
		offerDao.sauvegarder(offre);
		logger.info("offre sauvagardé " + offre);

		Offre offer = offerDao.sauvegarder(offre);
		model.addAttribute("offer", offer);
		
		
		
		return "offer_propose";
	}

	/**
	 * ??????page de retour pas sure????? Méthode mappé sur /propose_offre et les
	 * requêtes POST Sauvagarder une offre envoyé
	 * 
	 * @param offre
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/propose_offre", method = RequestMethod.POST)
	public String proposerOffre(@ModelAttribute Offre offre, Model model,
			BindingResult result) {
		
		Utilisateur enprs = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		if(enprs != null){
			offre.setEmetteur(enprs);
		} else {
			Etudiant etud = etudiantDao.chercherParEnt(SecurityContextHolder
					.getContext().getAuthentication().getName());
			offre.setEmetteur(etud);
		}
		
		offre.setStatut(StatutOffre.ENVOYEE);
		if (result.hasErrors()) {
			return "offer_propose";
		}
		Offre offer = offerDao.sauvegarder(offre);
		model.addAttribute("offer", offer);
		logger.info("offre sauvagardé " + offre);

		return "offer_propose";
	}

}
