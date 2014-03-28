package com.sag.business.control;

import static com.sag.business.control.Util.getAuthority;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

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
import com.sag.business.model.Etudiant;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.UtilisateurDao;

/**
 * Classe du contrôleur de sécurité
 * @author Joël KARCHER
 * @author Benjamin MIRETTI
 * 
 */
@Controller()
@RequestMapping("/")
public class ControlSecurite {

	/**
	 * Objet chargé des logs 
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Dao des étudiants
	 */
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	/**
	 * Dao des utilisateurs
	 */
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;
	/**
	 * Dao des domaines
	 */
	@EJB(mappedName = "java:global/SAG/domaineDao!com.sag.business.service.DomaineDao")
	DomaineDao domDao;
	/**
	 * Dao des entreprises
	 */
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao companyDao;
	
	/**
	 * méthode générant un attribut du modèle représentant l'utilisateur authentifié
	 * @param p utilisateur spring authentifié
	 * @return l'objet Utilisateur correspondant à l'utilisateur authentifié
	 */
	@ModelAttribute("user_co")
	Utilisateur username(Principal p) {
		if(p != null)
		{
			if(getAuthority().equals("ROLE_ENTR"))
				return companyDao.chercherParEmail(p.getName());
			return etuDao.chercherParEnt(p.getName());
		}
		return null;
	}
	
	/**
	 * méthode générant un attribut du modèle représentant la liste des domaines
	 * @return La liste de tous les domaines
	 */
	@ModelAttribute("domains")
	Collection<Domaine> domaines(){
		return domDao.chercherTous();
	}
	
	/**
	 * Créer un etudiant gardé dans le modèle
	 * 
	 * @return L'etudiant crée
	 */
	@ModelAttribute
	public Etudiant newUser(
			@RequestParam(value = "id", required = false) Integer studentNumber) {
		Etudiant e = new Etudiant();
		logger.info("new user = " + e);
		return e;
	}

	/**
	 * Méthode mappé sur /login et les requêtes GET
	 * 
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
	 * 
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
	 * 
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, logout_confirm
	 */
	@RequestMapping(value = "/logoutconfirm", method = RequestMethod.GET)
	public String logout(Model model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "logout_confirm";
	}

	/**
	 * Méthode mappé sur /register et les requêtes GET
	 * 
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, register
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute Etudiant e, Model model) {
		if (e != null) {
			model.addAttribute("etudiant", e);
			return "register";
		}
		return "login";
	}

	/**
	 * Méthode mappé sur /register et les requêtes POST Crée un etudiant
	 * 
	 * @param d L'etudiant recupéré
	 * @return Redirection vers un autre mapping, login
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveEtudiant(@ModelAttribute @Valid Etudiant etu,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		}
		if (etu == null)
			return "redirect:register";
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
	 * 
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, home
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String accueil(Model model) {
		return "home";
	}

	/**
	 * Classe anonyme récupérant les objets complexes du formulaire
	 */
	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(java.sql.Date.class, "dateNaiss",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy");
						java.util.Date date = null;
						try {
							date = sdf.parse(text);
							java.sql.Date dateSQL = null;
							dateSQL = new java.sql.Date(date.getTime());

							super.setValue(dateSQL);
						} catch (ParseException e) { // TODO Auto-generated
														// catch block
							e.printStackTrace();
						}
						
					}

					@Override
					public String getAsText() {
						if (getValue() == null) {
							return "";
						} else
							return new SimpleDateFormat("dd/MM/yyyy")
									.format((Date) getValue());
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

	}
}
