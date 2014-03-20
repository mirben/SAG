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

import com.sag.business.model.Entreprise;
import com.sag.business.model.Etudiant;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;
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
public class ControlUtilisateur {

	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao entDao;
	@EJB(mappedName = "java:global/SAG/utilisateurDao!com.sag.business.service.UtilisateurDao")
	UtilisateurDao userDao;

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Créer une entreprise, à partir de la base de données si l'argument
	 * existe, ou ex-nihilo sinon
	 * 
	 * @param id
	 *            L'indentifiant de l'entreprise
	 * @return L'entreprise récupérée depuis la base de données
	 */
	@ModelAttribute
	public Entreprise newCompany(
			@RequestParam(value = "id", required = false) Integer companyNumber) {
		if (companyNumber != null) {
			logger.info("find company " + companyNumber);
			return entDao.chercherParID(companyNumber);
		}
		Entreprise c = new Entreprise();
		logger.info("new company = " + c);

		return c;
	}

	/**
	 * Créer un etudiant, à partir de la base de données si l'argument existe,
	 * ou ex-nihilo sinon
	 * 
	 * @param id
	 *            L'indentifiant de l'etudiant
	 * @return L'etudiant récupéré depuis la base de données
	 */
	@ModelAttribute
	public Etudiant newStudent(
			@RequestParam(value = "id", required = false) Integer studentNumber) {
		if (studentNumber != null) {
			logger.info("find student " + studentNumber);
			return etuDao.chercherParID(studentNumber);
		}
		Etudiant e = new Etudiant();
		logger.info("new student = " + e);
		//Ajout du rôle étudiant par défaut et du statut désactivé
		e.setRole(etuDao.chercherRoleParID(2));
		e.setStatut(StatutUtilisateur.INACTIF);
		return e;
	}

	/**
	 * Méthode mappé sur /delete_user et les requêtes GET Supprime un etudiant
	 * 
	 * @param studentNumber
	 *            L'identifiant de l'etudiant à supprimer
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String deleteStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber) {
		etuDao.supprimer(studentNumber);
		logger.info("delete student " + studentNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /disable_user et les requêtes GET Désactive un etudiant
	 * 
	 * @param studentNumber
	 *            L'identifiant de l'etudiant à désactiver.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/disable_user", method = RequestMethod.GET)
	public String disableStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		e.setStatut(StatutUtilisateur.INACTIF);
		etuDao.sauvegarder(e);
		logger.info("disable student " + studentNumber);
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /enable_user et les requêtes GET Active un etudiant
	 * 
	 * @param studentNumber
	 *            L'identifiant de l'etudiant à activer.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/enable_user", method = RequestMethod.GET)
	public String enableStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		e.setStatut(StatutUtilisateur.ACTIF);
		etuDao.sauvegarder(e);
		logger.info("enable student " + studentNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /switch_role_user et les requêtes GET change le rôle d'un etudiant
	 * 
	 * @param studentNumber
	 *            L'identifiant de l'etudiant à modifier.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/switch_role_user", method = RequestMethod.GET)
	public String switchRoleStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		
		switch (e.getRole().getId()) {
			case 1: e.setRole(etuDao.chercherRoleParID(2));
			case 2: e.setRole(etuDao.chercherRoleParID(1));
			default: e.setRole(etuDao.chercherRoleParID(2));
		}
		
		etuDao.sauvegarder(e);
		logger.info("switch role student " + studentNumber);
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /detail_user et les requêtes GET affiche les détails d'un etudiant
	 * 
	 * @param studentNumber
	 *            L'identifiant de l'etudiant à afficher.     
	 *  @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_user", method = RequestMethod.GET)
	public String detailStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber, Model model) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		model.addAttribute("user", e);
		logger.info("detail student " + studentNumber);
		return "detail_user";
	}
	
	/**
	 * Méthode mappé sur /edit_user et les requêtes GET Modifie ou crée un etudiant
	 * 
	 * @param e
	 *            L'etudiant recupéré
	 * @param model
	 *            L'objet Model de spring
	 * @return Le nom de la jsp à afficher, new_user, ou redirection vers la
	 *          page admin si etudiant null
	 */
	@RequestMapping(value = "/edit_user", method = RequestMethod.GET)
	public String editStudent(@ModelAttribute Etudiant e, Model model) {
		Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		model.addAttribute("user_co", uco);
		if (e != null){
			model.addAttribute("user", e);
			return "new_user";
		}
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /edit_user et les requêtes POST Modifie ou crée un etudiant
	 * 
	 * @param etu
	 *            L'etudiant recupéré
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/edit_user", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute Etudiant etu, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "new_user";
		}
		if(etu==null) return "redirect:admin";
		logger.info("save student " + etu.getNom());
		try {
			etuDao.sauvegarder(etu);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'utilisateur déjà utilisé");
			Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
					.getContext().getAuthentication().getName());
			model.addAttribute("user_co", uco);
			return "new_user";
		}
		return "redirect:admin";
	}
	
	
	/**
	 * Méthode mappé sur /delete_company et les requêtes GET Supprime une entreprise
	 * 
	 * @param companyNumber
	 *            L'identifiant de l'entreprise à supprimer
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/delete_company", method = RequestMethod.GET)
	public String deleteCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber) {
		entDao.supprimer(companyNumber);
		logger.info("delete company " + companyNumber);
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /disable_company et les requêtes GET Désactive une entreprise
	 * 
	 * @param companyNumber
	 *            L'identifiant de l'entreprise à désactiver.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/disable_company", method = RequestMethod.GET)
	public String disableCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber) {
		Entreprise e = entDao.chercherParID(companyNumber);
		e.setStatut(StatutUtilisateur.INACTIF);
		entDao.sauvegarder(e);
		logger.info("disable company " + companyNumber);
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /enable_company et les requêtes GET Active une entreprise
	 * 
	 * @param companyNumber
	 *            L'identifiant de l'entreprise à activer.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/enable_company", method = RequestMethod.GET)
	public String enableCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber) {
		Entreprise e = entDao.chercherParID(companyNumber);
		e.setStatut(StatutUtilisateur.ACTIF);
		entDao.sauvegarder(e);
		logger.info("enable company " + companyNumber);
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /detail_company et les requêtes GET affiche les détails d'une entreprise
	 * 
	 * @param companyNumber
	 *            L'identifiant de l'entreprise à afficher.
	 * @param model
	 *            L'objet Model de spring
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_company", method = RequestMethod.GET)
	public String detailCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber, Model model) {
		Entreprise e = entDao.chercherParID(companyNumber);
		model.addAttribute("company", e);
		logger.info("detail company " + companyNumber);
		return "detail_company";
	}
	
	/**
	 * Méthode mappé sur /edit_company et les requêtes GET Modifie ou crée une entreprise
	 * 
	 * @param e
	 *            L'entreprise recupérée
	 * @param model
	 *            L'objet Model de spring
	 * @return Le nom de la jsp à afficher, new_company, ou redirection vers la
	 *          page admin si entreprise null
	 */
	@RequestMapping(value = "/edit_company", method = RequestMethod.GET)
	public String editCompany(@ModelAttribute Entreprise e, Model model) {
		Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
				.getContext().getAuthentication().getName());
		model.addAttribute("user_co", uco);
		if (e != null){
			model.addAttribute("company", e);
			return "new_company";
		}
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /edit_company et les requêtes POST Modifie ou crée une entreprise
	 * 
	 * @param ent
	 *            L'entreprise recupérée
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/edit_company", method = RequestMethod.POST)
	public String saveCompany(@ModelAttribute Entreprise ent, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "new_company";
		}
		if(ent==null) return "redirect:admin";
		logger.info("save company " + ent.getNom());
		try {
			entDao.sauvegarder(ent);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'entreprise déjà utilisé");
			Utilisateur uco = userDao.chercherParEmail(SecurityContextHolder
					.getContext().getAuthentication().getName());
			model.addAttribute("user_co", uco);
			return "new_company";
		}
		return "redirect:admin";
	}
}
