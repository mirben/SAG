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
import com.sag.business.model.Role;
import com.sag.business.model.StatutUtilisateur;
import com.sag.business.model.Utilisateur;
import com.sag.business.service.DomaineDao;
import com.sag.business.service.EntrepriseDao;
import com.sag.business.service.EtudiantDao;
import com.sag.business.service.UtilisateurDao;

/**
 * Classe du contrôleur des utilisateurs
 * @author Joël KARCHER
 * @author Benjamin MIRETTI
 * 
 */
@Controller()
@RequestMapping("/")
public class ControlUtilisateur {

	/**
	 * Dao des étudiants
	 */
	@EJB(mappedName = "java:global/SAG/etudiantDao!com.sag.business.service.EtudiantDao")
	EtudiantDao etuDao;
	/**
	 * Dao des entreprises
	 */
	@EJB(mappedName = "java:global/SAG/entrepriseDao!com.sag.business.service.EntrepriseDao")
	EntrepriseDao entDao;
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
	 * Objet chargé des logs 
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * méthode générant un attribut du modèle représentant l'utilisateur authentifié
	 * @param p utilisateur spring authentifié
	 * @return l'objet Utilisateur correspondant à l'utilisateur authentifié
	 */
	@ModelAttribute("user_co")
	Utilisateur username(Principal p) {
		if(getAuthority().equals("ROLE_ENTR"))
			return entDao.chercherParEmail(p.getName());
		return etuDao.chercherParEnt(p.getName());
	}

	/**
	 * méthode générant un attribut du modèle représentant la liste des domaines
	 * @return La liste de tous les domaines
	 */
	@ModelAttribute("domains")
	Collection<Domaine> domaines() {
		return domDao.chercherTous();
	}

	/**
	 * Créer une entreprise, à partir de la base de données si l'argument
	 * existe, ou ex-nihilo sinon
	 * 
	 * @param id L'indentifiant de l'entreprise
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
		c.setRole(entDao.chercherRoleParID(3));
		c.setStatut(StatutUtilisateur.ACTIF);
		logger.info("new company = " + c);

		return c;
	}

	/**
	 * Créer un etudiant, à partir de la base de données si l'argument existe,
	 * ou ex-nihilo sinon
	 * 
	 * @param id L'indentifiant de l'etudiant
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
		// Ajout du rôle étudiant par défaut et du statut désactivé
		e.setRole(etuDao.chercherRoleParID(2));
		e.setStatut(StatutUtilisateur.INACTIF);
		return e;
	}

	/**
	 * Méthode mappé sur /delete_user et les requêtes GET Supprime un etudiant
	 * 
	 * @param studentNumber L'identifiant de l'etudiant à supprimer
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String deleteStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber, Model model) {
		if(etuDao.supprimer(studentNumber)){
			logger.info("delete student " + studentNumber);
			return "redirect:admin";
		}
		model.addAttribute("erreur", "Cet utilisateur n'existe pas");
		return "admin";
	}

	/**
	 * Méthode mappé sur /disable_user et les requêtes GET Désactive un etudiant
	 * 
	 * @param studentNumber L'identifiant de l'etudiant à désactiver.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/disable_user", method = RequestMethod.GET)
	public String disableStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber, Model model) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		if(e == null){
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "admin";
		}
		e.setStatut(StatutUtilisateur.INACTIF);
		etuDao.sauvegarder(e);
		logger.info("disable student " + studentNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /enable_user et les requêtes GET Active un etudiant
	 * 
	 * @param studentNumber L'identifiant de l'etudiant à activer.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/enable_user", method = RequestMethod.GET)
	public String enableStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber, Model model) {
		Etudiant e = etuDao.chercherParID(studentNumber);
		if(e == null){
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "admin";
		}
		e.setStatut(StatutUtilisateur.ACTIF);
		etuDao.sauvegarder(e);
		logger.info("enable student " + studentNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /switch_role_user et les requêtes GET change le rôle
	 * d'un etudiant
	 * 
	 * @param studentNumber L'identifiant de l'etudiant à modifier.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/switch_role_user", method = RequestMethod.GET)
	public String switchRoleStudent(
			@RequestParam(value = "id", required = true) Integer studentNumber,
			Model model) {
		Etudiant e = etuDao.chercherParID(studentNumber);

		if (e == null) {
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "redirect:admin";
		}

		switch (e.getRole().getId()) {
		case 1:
			e.setRole(etuDao.chercherRoleParID(2));
			break;
		case 2:
			e.setRole(etuDao.chercherRoleParID(1));
			break;
		default:
			e.setRole(etuDao.chercherRoleParID(2));
			break;
		}

		etuDao.sauvegarder(e);
		logger.info("switch role student " + studentNumber + ":" + e.getRole());
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /detail_user et les requêtes GET affiche les détails
	 * d'un etudiant
	 * 
	 * @param studentNumber L'identifiant de l'etudiant à afficher.
	 * @param model L'objet Model de spring
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_user", method = RequestMethod.GET)
	public String detailStudent(Model model) {
		Utilisateur userCo = (Utilisateur) model.asMap().get("user_co");
		if(userCo == null || !(userCo instanceof Etudiant)){
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "home";
		}
		Etudiant etuCo = (Etudiant) userCo;
		model.addAttribute("etudiant", etuCo);
		model.addAttribute(
				"roles",
				Arrays.asList(etuDao.chercherRoleParID(1),
						etuDao.chercherRoleParID(2),
						etuDao.chercherRoleParID(3)));
		logger.info("detail student " + etuCo);
		return "detail_user";
	}

	/**
	 * Méthode mappé sur /detail_user et les requêtes POST Modifie ou crée un
	 * etudiant
	 * 
	 * @param ent L'etudiant recupérée
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_user", method = RequestMethod.POST)
	public String saveCompany(@ModelAttribute @Valid Etudiant etu,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "detail_user";
		}
		if (etu == null)
			return "redirect:admin";
		logger.info("save detail student " + etu.getNom());
		try {
			etuDao.sauvegarder(etu);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'etudiant déjà utilisé");
			return "detail_user";
		}
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /edit_user et les requêtes GET Modifie ou crée un
	 * etudiant
	 * 
	 * @param e L'etudiant recupéré
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, new_user, ou redirection vers la
	 *         page admin si etudiant null
	 */
	@RequestMapping(value = "/edit_user", method = RequestMethod.GET)
	public String editStudent(@ModelAttribute Etudiant e, Model model) {
		if (e != null) {
			model.addAttribute("etudiant", e);
			model.addAttribute(
					"roles",
					Arrays.asList(etuDao.chercherRoleParID(1),
							etuDao.chercherRoleParID(2),
							etuDao.chercherRoleParID(3)));
			return "new_user";
		}
		model.addAttribute("erreur", "Cet utilisateur n'existe pas");
		return "home";
	}

	/**
	 * Méthode mappé sur /edit_user et les requêtes POST Modifie ou crée un
	 * etudiant
	 * 
	 * @param etu L'etudiant recupéré
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/edit_user", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute @Valid Etudiant etu,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new_user";
		}
		if (etu == null)
			return "redirect:admin";
		logger.info("save student " + etu.getNom());
		try {
			etuDao.sauvegarder(etu);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'utilisateur déjà utilisé");
			return "new_user";
		}
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /delete_company et les requêtes GET Supprime une
	 * entreprise
	 * 
	 * @param companyNumber L'identifiant de l'entreprise à supprimer
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/delete_company", method = RequestMethod.GET)
	public String deleteCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber, Model model) {
		if(entDao.supprimer(companyNumber)){
			logger.info("delete company " + companyNumber);
			return "redirect:admin";
		}
		model.addAttribute("erreur", "Cet utilisateur n'existe pas");
		return "admin";
	}

	/**
	 * Méthode mappé sur /disable_company et les requêtes GET Désactive une
	 * entreprise
	 * 
	 * @param companyNumber L'identifiant de l'entreprise à désactiver.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/disable_company", method = RequestMethod.GET)
	public String disableCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber, Model model) {
		Entreprise e = entDao.chercherParID(companyNumber);
		if (e == null) {
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "redirect:admin";
		}
		e.setStatut(StatutUtilisateur.INACTIF);
		entDao.sauvegarder(e);
		logger.info("disable company " + companyNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /enable_company et les requêtes GET Active une
	 * entreprise
	 * 
	 * @param companyNumber L'identifiant de l'entreprise à activer.
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/enable_company", method = RequestMethod.GET)
	public String enableCompany(
			@RequestParam(value = "id", required = true) Integer companyNumber, Model model) {
		Entreprise e = entDao.chercherParID(companyNumber);
		if (e == null) {
			model.addAttribute("erreur", "Cet utilisateur n'existe pas");
			return "redirect:admin";
		}
		e.setStatut(StatutUtilisateur.ACTIF);
		entDao.sauvegarder(e);
		logger.info("enable company " + companyNumber);
		return "redirect:admin";
	}

	/**
	 * Méthode mappé sur /detail_company et les requêtes GET affiche les détails
	 * d'une entreprise
	 * 
	 * @param companyNumber L'identifiant de l'entreprise à afficher.
	 * @param model L'objet Model de spring
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_company", method = RequestMethod.GET)
	public String detailCompany(@ModelAttribute Entreprise e, Model model) {
		if (e != null) {
			model.addAttribute("entreprise", e);
			logger.info("detail company " + e);
			return "detail_company";
		}
		model.addAttribute("erreur", "Cet utilisateur n'existe pas");
		return "home";

	}

	/**
	 * Méthode mappé sur /detail_company et les requêtes POST Modifie ou crée une
	 * entreprise
	 * 
	 * @param ent L'entreprise recupérée
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/detail_company", method = RequestMethod.POST)
	public String detailCompany(@ModelAttribute Entreprise ent,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "detail_company";
		}
		if (ent == null)
			return "redirect:admin";
		logger.info("save company " + ent.getNom());
		try {
			entDao.sauvegarder(ent);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'entreprise déjà utilisé");
			return "detail_company";
		}
		return "redirect:admin";
	}
	
	/**
	 * Méthode mappé sur /edit_company et les requêtes GET Modifie ou crée une
	 * entreprise
	 * 
	 * @param e L'entreprise recupérée
	 * @param model L'objet Model de spring
	 * @return Le nom de la jsp à afficher, new_company, ou redirection vers la
	 *         page admin si entreprise null
	 */
	@RequestMapping(value = "/edit_company", method = RequestMethod.GET)
	public String editCompany(@ModelAttribute Entreprise e, Model model) {
		if (e != null) {
			model.addAttribute("entreprise", e);
			return "new_company";
		}
		model.addAttribute("erreur", "Cet utilisateur n'existe pas");
		return "home";
	}

	/**
	 * Méthode mappé sur /edit_company et les requêtes POST Modifie ou crée une
	 * entreprise
	 * 
	 * @param ent L'entreprise recupérée
	 * @return Redirection vers un autre mapping, admin
	 */
	@RequestMapping(value = "/edit_company", method = RequestMethod.POST)
	public String saveCompany(@ModelAttribute Entreprise ent,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "new_company";
		}
		if (ent == null)
			return "redirect:admin";
		
		ent.setStatut(StatutUtilisateur.ACTIF);
		logger.info("save company " + ent.getNom());
		try {
			entDao.sauvegarder(ent);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("erreur", "Nom d'entreprise déjà utilisé");
			return "new_company";
		}
		return "redirect:admin";
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
								"yyyy-MM-dd");
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
							return new SimpleDateFormat("yyyy-MM-dd")
									.format((Date) getValue());
					}
				});
		
		b.registerCustomEditor(Role.class, "role",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						super.setValue(userDao.chercherRoleParID(Integer.parseInt(text)));
					}
				});
		
		b.registerCustomEditor(Collection.class, "domaines",
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						List<String> listIdDom = Arrays.asList(text.split(","));
						Collection<Domaine> listDom = new Vector<Domaine>();
						for (String curId : listIdDom) {
							listDom.add(domDao.chercherParID(Integer.parseInt(curId)));
						}
						super.setValue(listDom);
					}
				});
	}
}
