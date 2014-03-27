/**
 * @author Benjamin MIRETTI
 * @author Tuan NGUYEN (annotations de validation)
 */
package com.sag.business.model;

import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.bval.constraints.NotEmpty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe entité correspondant aux étudiants
 * @author Benjamin MIRETTI
 * @author Tuan NGUYEN (annotations de validation)
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID_U")
public class Etudiant extends Utilisateur {

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le login ENT de létudiant
	 */
	@Column(nullable = false, unique = true)
	@NotNull(message = "L'identifiant ENT est obligatoire")
	@Size(min = 6, message = "L'identifiant ENT est obligatoire")
	private String logENT;

	/**
	 * Le nom de l'étudiant
	 */
	@NotEmpty(message = "Le nom est obligatoire")
	private String nom;

	/**
	 * Le prénom de l'étudiant
	 */
	@NotEmpty(message = "Le prenom est obligatoire")
	private String prenom;

	/**
	 * La date de naissance de l'étudiant
	 */
	@NotNull(message = "La date de naissance est incorrecte")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "La date de naissance doit être passé")
	private Date dateNaiss;

	/**
	 * L'adresse de l'étudiant
	 */
	@NotEmpty(message = "L'adresse est obligatoire")
	private String adresse;

	/**
	 * L'adresse du site de l'étudiant
	 */
	@URL(message = "Le site internet est incorrect")
	private String siteWeb;

	/**
	 * La formation de l'étudiant
	 */
	@NotNull(message = "La formation est obligatoire")
	@NotEmpty(message = "La formation est obligatoire")
	private String formation;

	/**
	 * La liste des domaines de l'étudiant
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Domaine> domaines;

	/**
	 * Constructeur vide d'Etudiant
	 */
	public Etudiant() {
		super();
		domaines = new Vector<Domaine>();
	}
	
	/**
	 * Constructeur d'Etudiant
	 * @param email L'adresse mail de l'étudiant
	 * @param statut Le statut de l'étudiant
	 * @param role Le rôle de l'étudiant
	 * @param logENT Le login ENT de l'étudiant
	 * @param nom Le nom de l'étudiant
	 * @param prenom Le prénom de l'étudiant
	 * @param dateNaiss La date de naissance de l'étudiant
	 * @param adresse L'adresse de l'étudiant
	 * @param siteWeb L'adresse du site de l'étudiant
	 * @param formation La formation de l'étudiant
	 * @param domaines La liste des domaines de l'étudiant
	 */
	public Etudiant(String email, StatutUtilisateur statut, Role role,
			String logENT, String nom, String prenom, Date dateNaiss,
			String adresse, String siteWeb, String formation,
			Collection<Domaine> domaines) {
		super(email, statut, role);
		this.logENT = logENT;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.adresse = adresse;
		this.siteWeb = siteWeb;
		this.formation = formation;
		this.domaines = (domaines != null) ? domaines : new Vector<Domaine>();
	}

	/**
	 * 
	 * @return Le login ENT de l'étudiant
	 */
	public String getLogENT() {
		return logENT;
	}

	/**
	 * 
	 * @param logENT Le login ENT à attribuer à l'étudiant
	 */
	public void setLogENT(String logENT) {
		this.logENT = logENT;
	}

	/**
	 * 
	 * @return Le nom de l'étudiant
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom Le nom à attribuer à l'étudiant
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return Le prénom de l'étudiant
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * 
	 * @param prenom Le prénom à attribuer à l'étudiant
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * 
	 * @return La date de naissance de l'étudiant
	 */
	public Date getDateNaiss() {
		return dateNaiss;
	}

	/**
	 * 
	 * @param dateNaiss La date de naissance à attribuer à l'étudiant
	 */
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	/**
	 * 
	 * @return L'adresse de l'étudiant
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * 
	 * @param adresse l'adresse à attribuer à l'étudiant
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * 
	 * @return L'adresse du site de l'étudiant
	 */
	public String getSiteWeb() {
		return siteWeb;
	}

	/**
	 * 
	 * @param siteWeb L'adresse de site à attribuer à l'étudiant 
	 */
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	/**
	 * 
	 * @return La formation de l'étudiant
	 */
	public String getFormation() {
		return formation;
	}

	/**
	 * 
	 * @param formation La formation à attribuer à l'étudiant
	 */
	public void setFormation(String formation) {
		this.formation = formation;
	}

	/**
	 * 
	 * @return La liste des domaines de l'étudiant
	 */
	public Collection<Domaine> getDomaines() {
		return domaines;
	}

	/**
	 * 
	 * @param domaines La liste des domaines à attribuer à l'étudiant
	 */
	public void setDomaines(Collection<Domaine> domaines) {
		this.domaines = domaines;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Etudiant [logENT=" + logENT + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaiss=" + dateNaiss + ", adresse=" + adresse
				+ ", siteWeb=" + siteWeb + ", formation=" + formation
				+ ", domaines=" + domaines + ", getId()=" + getId()
				+ ", getEmail()=" + getEmail() + ", getStatut()=" + getStatut()
				+ ", getRole()=" + getRole() + "]";
	}

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((dateNaiss == null) ? 0 : dateNaiss.hashCode());
		result = prime * result
				+ ((domaines == null) ? 0 : domaines.hashCode());
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((logENT == null) ? 0 : logENT.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((siteWeb == null) ? 0 : siteWeb.hashCode());
		return result;
	}

	/**
	 * @param obj L'objet auquel l'objet courant sera comparé
	 * @return un booléen définissant si l'objet est égal à celui passé en paramètre
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (dateNaiss == null) {
			if (other.dateNaiss != null)
				return false;
		} else if (!dateNaiss.toString().equals(other.dateNaiss.toString()))
			return false;
		if (domaines == null) {
			if (other.domaines != null)
				return false;
		} else if (!(domaines.containsAll(other.domaines) && other.domaines
				.containsAll(domaines)))
			return false;
		if (formation == null) {
			if (other.formation != null)
				return false;
		} else if (!formation.equals(other.formation))
			return false;
		if (logENT == null) {
			if (other.logENT != null)
				return false;
		} else if (!logENT.equals(other.logENT))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (siteWeb == null) {
			if (other.siteWeb != null)
				return false;
		} else if (!siteWeb.equals(other.siteWeb))
			return false;
		return true;
	}

}