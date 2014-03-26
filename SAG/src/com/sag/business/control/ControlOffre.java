package com.sag.business.control;

import static com.sag.business.control.Util.getAuthority;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javassist.expr.Instanceof;

import javax.ejb.EJB;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sag.business.model.Domaine;
import com.sag.business.model.Entreprise;

import com.sag.business.model.Etudiant;
import com.sag.business.model.Image;
import com.sag.business.model.Offre;
import com.sag.business.model.StatutOffre;
import com.sag.business.model.Utilisateur;
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
public class ControlOffre {

	@EJB(mappedName = "java:global/SAG/offreDao!com.sag.business.service.OffreDao")
	OffreDao offerDao;
	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etudiantDao;
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao entrepriseDao;

	protected final Log logger = LogFactory.getLog(getClass());

	@ModelAttribute("user_co")
	Utilisateur username(Principal p) {
		if(p != null)
		{
			if(getAuthority().equals("ROLE_ENTR"))
				return entrepriseDao.chercherParEmail(p.getName());
			Etudiant e = etudiantDao.chercherParEnt(p.getName());
			System.out.println("userco = " +e );
			return e;
		}
		return null;
	}

	@ModelAttribute("domains")
	Collection<Domaine> domaines() {
		return domDao.chercherTous();
	}

	/**
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Méthode créer une nouvelle offre, ou récupérer une offre existante
	 * 
	 * @param idOffre
	 * @return L'objet offre récupéré
	 */
	@ModelAttribute("offre")
	public Offre newOffre(@RequestParam(value = "id", required = false) Integer idOffre, Model model) {
		if (idOffre != null) {
			logger.info("offre trouvé" + idOffre);
			return offerDao.chercherParID(idOffre);
		}
		Offre o = new Offre();
		o.setDateAjout(new Date(Calendar.getInstance().getTimeInMillis()));
		o.setEmetteur((Utilisateur) model.asMap().get("user_co"));
		logger.info("----------------- new offer  = " + o);
		return o;
	}
	
	/**
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

		System.out.println("test -------------------------------------------");
		System.out.println("Je cherche par la clé");
		System.out.println("test -------------------------------------------");
		Collection<Offre> offersmatch = offerDao.chercherParMotCle(keyword);
		for (Offre offre : offersmatch) {
			if(offre.getStatut()!=StatutOffre.ACTIVE)
				offersmatch.remove(offre);
		}
		model.addAttribute("offers", offersmatch);
		logger.info("search offers with " + keyword);
		return "list";
	}

	/**
	 * -------------------------à modifier--------------------------- Méthode
	 * mappé sur /list_offer et les requêtes GET Recherche les offres
	 * 
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list
	 */
	@RequestMapping(value = "/list_offer", method = RequestMethod.GET)
	public String listOffers(Model model) {
		Collection<Offre> offers = offerDao.chercherTous();
		Collection<Offre> offersenvoie = new Vector<Offre>();

		if (!offers.isEmpty()) {

			for (Offre offre : offers) {
				if (offre.getStatut().equals(StatutOffre.ACTIVE))
					offersenvoie.add(offre);
			}
			if (!offersenvoie.isEmpty()) {
				model.addAttribute("offers", offersenvoie);
			}
		}

		// model.addAttribute("offers", offers);
		logger.info("get offer's list ");
		return "list";
	}

	/**
	 * ------------------------------------------------------------------------
	 * --- Méthode mappé sur /domain_list et les requêtes GET Recherche les
	 * offres du domaine
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
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * ++++++ Méthode mappé sur /offer_propose et les requêtes GET Recherche les
	 * offres proposées par l'utilisateur connecté
	 * 
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, list_propose
	 */
	@RequestMapping(value = "/offer_proposed", method = RequestMethod.GET)
	public String listOffersPropose(
			@ModelAttribute("user_co") Utilisateur userCo, Model model) {

		Collection<Offre> offers = offerDao.chercherTous();
		Collection<Offre> offersprop = new Vector<Offre>();

		if (!offers.isEmpty()) {
			for (Offre offre : offers) {
				if (offre.getEmetteur() != null
						&& offre.getEmetteur().getId() == userCo.getId())
					offersprop.add(offre);
			}
			if (!offersprop.isEmpty()) {
				model.addAttribute("offer_propose", offersprop);
			}
		}
		// model.addAttribute("user_co", uco);
		// logger.info("get user's offers " + uco.getId());
		return "list_propose";
	}



	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * ++++++ Méthode mappé sur /edit_offer et les requêtes GET mettre une offre
	 * dans dans la formule edition
	 * 
	 * @param offre
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit_offer", method = RequestMethod.GET)
	public String editOffre(@ModelAttribute("offre") Offre o, Model model) {
		Utilisateur userCo = (Utilisateur) model.asMap().get("user_co");
		Collection<Offre> offers = offerDao.chercherTous();
		if (offers.contains(o)) {
			if (o.getEmetteur().equals(userCo)){
				return "offer_propose";
			}
			else if(getAuthority().equals("ROLE_ADMIN"))
				return "new_offer";
			else
				return "denied";
		}
		if (getAuthority().equals("ROLE_ADMIN"))
			return "new_offer";
		else
			return "offer_propose";
	}

	/**
	 * Méthode mappé sur /edit_offer et les requêtes POST Sauvegarder une offre
	 * en brouillon
	 * 
	 * @param offre
	 * @param result
	 * @param model
	 *            L'objet Model de spring
	 * @return
	 */
	@RequestMapping(value = "/edit_offer", method = RequestMethod.POST)
	public String editOffre(@ModelAttribute @Valid Offre offre,
			BindingResult result, Model model) {
		System.out.println("hello edit offre");
		if (result.hasErrors() || offre == null) {
			return "new_offer";
		}

		logger.info("save offer " + offre.getTitre());
		try {
			offerDao.sauvegarder(offre);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Impossible de sauvegarder l'offre");
			Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
					.getContext().getAuthentication().getName());
			model.addAttribute("user_co", uco);
			return "new_offer";
		}

		return "redirect:admin";
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
		if (!getAuthority().equals("ROLE_ADMIN")) {
			return "redirect:home";
		}

		Offre offre = offerDao.chercherParID(idOffre);
		offre.setStatut(StatutOffre.ENVOYEE);
		offerDao.sauvegarder(offre);
		logger.info("offer désactivé" + offre);
		return "redirect:admin";
	}

	/**
	 * 
	 * @param idOffre
	 * @return
	 */

	@RequestMapping(value = "/join_offer", method = RequestMethod.GET)
	public String joingner_offer(@ModelAttribute("user_co") Utilisateur userCo,
			@RequestParam(value = "ido", required = true) int idOffre,
			Model model) {

		System.out.println("Je suis dans Join");
		Offre offre = offerDao.chercherParID(idOffre);
		if (offre.getParticipants().contains(userCo)) {
			// model.addAttribute("message", "Vous avez déjà participé.");
			String link = "detail_offer?id=" + offre.getId();
			return "redirect:" + link;

		}

		if ((userCo instanceof Etudiant)
				&& (offre.getParticipants().size() < offre.getParticipantsMax())) {
			offre.getParticipants().add((Etudiant) userCo);
			offerDao.sauvegarder(offre);
			model.addAttribute("message", "Votre paricipation est sauvée.");

		}

		String link = "detail_offer?id=" + offre.getId();

		return "redirect:" + link;
	}

	@RequestMapping(value = "/giveup_offer", method = RequestMethod.GET)
	public String unparticipe_offer(
			@ModelAttribute("user_co") Utilisateur userCo,
			@RequestParam(value = "ido", required = true) int idOffre,
			Model model) {

		System.out.println("Je suis dans Join");
		Offre offre = offerDao.chercherParID(idOffre);

		if ((userCo instanceof Etudiant)
				&& offre.getParticipants().contains(userCo)) {
			offre.getParticipants().remove((Etudiant) userCo);
			offerDao.sauvegarder(offre);
			System.out.println("fick fdsf dsqfqf" + offre.getParticipants());
			model.addAttribute("message", "Votre paricipation est annullé.");

		}

		String link = "detail_offer?id=" + offre.getId();

		return "redirect:" + link;
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
		model.addAttribute("offer", offre);
		if (offre.getParticipants().contains(
				(Etudiant) model.asMap().get("user_co")))
			model.addAttribute("participe", true);
		else
			model.addAttribute("participe", false);
		logger.info("offer détail" + offre);

		return "detail_offre";
	}

	/**
	 * Méthode mappé sur /valid_offer et les requêtes GET valider une offre
	 * 
	 * 
	 * @param idOffre
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/valid_offer", method = RequestMethod.GET)
	public String validOffre(
			@RequestParam(value = "id", required = true) int idOffre,
			Model model) {
		System.out.println("Je suis dans valid 1");
		Offre offre = offerDao.chercherParID(idOffre);
		offre.setStatut(StatutOffre.ACTIVE);
		System.out.println("Je suis dans valid 2");

		try {
			System.out.println("Je suis dans valid 3");
			offerDao.sauvegarder(offre);
			logger.info("offer détail" + offre);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Impossible de sauvegarder l'offre");
		}
		model.addAttribute(offre);

		return "redirect:admin";

	}

	/**
	 * * Méthode mappé sur /propose_offre et les requêtes GET
	 * 
	 * 
	 * @param o
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/propose_offre", method = RequestMethod.GET)
	public String proposeOffre(@ModelAttribute Offre o, Model model) {
		System.out.println("Je suis dans la GET");
		Entreprise entreprise = entrepriseDao
				.chercherParEmail(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		model.addAttribute("entreprise", entreprise);

		if (o != null) {
			model.addAttribute("offre", o);
			return "offer_propose";
		}
		return "offer_propose";
	}

	/**
	 * Méthode mappé sur /propose_offre et les requêtes POST sauvegarder un
	 * offre envoé
	 * 
	 * 
	 * @param offre
	 * @param model
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/propose_offre", method = RequestMethod.POST)
	public String proposeOffre(@ModelAttribute @Valid Offre offre,
			BindingResult result, Model model, @RequestParam String action) {

		int idUser = 0;
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (username.contains("@")) {
			idUser = entrepriseDao.chercherParEmail(
					SecurityContextHolder.getContext().getAuthentication()
							.getName()).getId();
		} else {
			idUser = etudiantDao.chercherParEnt(username).getId();
		}
		offre.setEmetteur(userDao.chercherParID(idUser));

		if (action.equals("Envoyer")) {
			offre.setStatut(StatutOffre.ENVOYEE);

		} else if (action.equals("Enregistrer")) {
			offre.setStatut(StatutOffre.BROUILLON);
		}

		Calendar calendar = Calendar.getInstance();
		offre.setDateAjout(new java.sql.Date(calendar.getTimeInMillis()));

		if (result.hasErrors() || offre == null) {
			return "offer_propose";
		}

		logger.info("offre sauvegardée " + offre);
		try {
			offerDao.sauvegarder(offre);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Impossible de sauvegarder l'offre");
			return "offer_propose";
		}

		return "redirect:offer_proposed";
	}

	/**
	 * Méthode mappé sur /save_offer et les requêtes POST Sauvagarder une offre
	 * en brouillon
	 * 
	 * @param offre
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/propose_offre", method = RequestMethod.POST, params = "enregistre")
	public String sauvegardeOffre(@ModelAttribute @Valid Offre offre,
			BindingResult result, Model model) {

		int idUser = 0;
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (username.contains("@")) {
			idUser = entrepriseDao.chercherParEmail(
					SecurityContextHolder.getContext().getAuthentication()
							.getName()).getId();
		} else {
			idUser = etudiantDao.chercherParEnt(username).getId();
		}
		offre.setEmetteur(userDao.chercherParID(idUser));

		offre.setStatut(StatutOffre.BROUILLON);

		if (result.hasErrors() || offre == null) {
			return "offer_propose";
		}

		logger.info("offre sauvagardé " + offre);
		try {
			offerDao.sauvegarder(offre);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Impossible de sauvegarder l'offre");
			return "offer_propose";
		}

		return "offer_propose";
	}

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(java.sql.Date.class, "dateDebut",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						java.util.Date date = null;
						try {
							date = sdf.parse(text);
							java.sql.Date dateSQL = null;
							dateSQL = new java.sql.Date(date.getTime());
							super.setValue(dateSQL);
						} catch (ParseException e) {
							super.setValue(null);
							e.printStackTrace();
						}

					}

					@Override
					public String getAsText() {
						if (getValue() == null) {
							return "";
						} else
							return new SimpleDateFormat("yyyy-MM-dd")
									.format((Date) getValue());
					}
				});

		b.registerCustomEditor(java.sql.Date.class, "dateFin",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						java.util.Date date = null;
						try {
							date = sdf.parse(text);
							java.sql.Date dateSQL = null;
							dateSQL = new java.sql.Date(date.getTime());

							super.setValue(dateSQL);
						} catch (ParseException e) {
							super.setValue(null);
							e.printStackTrace();
						}

					}

					@Override
					public String getAsText() {
						if (getValue() == null) {
							return "";
						} else
							return new SimpleDateFormat("yyyy-MM-dd")
									.format((Date) getValue());
					}
				});

		b.registerCustomEditor(com.sag.business.model.Entreprise.class,
				"fournisseur", new PropertyEditorSupport() {
					@Override
					public void setAsText(String id) {
						if (id == "" || id == null) {
							super.setValue(null);
						} else {
							try {
								int i = Integer.parseInt(id);
								Entreprise eprs = entrepriseDao
										.chercherParID(i);
								super.setValue(eprs);
							} catch (NumberFormatException e) {
								super.setValue(null);
							}

						}

					}

					@Override
					public String getAsText() {
						if (getValue() == null) {
							return null;
						} else {
							Entreprise entps = (Entreprise) getValue();
							return String.valueOf(entps.getId());
						}
					}

				});

		b.registerCustomEditor(Collection.class, "domaines",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						List<String> listIdDom = Arrays.asList(text.split(","));
						Collection<Domaine> listDom = new Vector<Domaine>();
						for (String curId : listIdDom) {
							listDom.add(domDao.chercherParID(Integer
									.parseInt(curId)));
						}
						super.setValue(listDom);
					}
				});
		
		b.registerCustomEditor(Collection.class, "images",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						System.out.println(text);
						List<String> listUrlImg = Arrays.asList(text.split(","));
						Collection<Image> listImg = new Vector<Image>();
						for (String curUrl : listUrlImg) {
							Image nimg = new Image(0,curUrl);
							listImg.add(nimg);
						}
						super.setValue(listImg);
					}
				});

	}
}
