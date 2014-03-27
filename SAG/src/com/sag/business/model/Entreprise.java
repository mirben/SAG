package com.sag.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Classe entité correspondant aux Entreprises
 * @author Benjamin MIRETTI
 *
 */
@Entity
@PrimaryKeyJoinColumn(name="ID_U")
public class Entreprise extends Utilisateur{

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le nom de l'entreprise
	 */
	private String nom;
	/**
	 * Le numéro SIRET de l'entreprise
	 */
	private String siret;
	/**
	 * L'adresse de l'entreprise
	 */
	private String adresse;
	/**
	 * L'adresse du site de l'entreprise
	 */
	private String siteWeb;
	
	/**
	 * Le mot de passe de l'entreprise
	 */
	@Column(nullable = false)
	private String password;
	
	/**
	 * Constructeur vide d'Entreprise
	 */
	public Entreprise(){
		super();
	}

	/**
	 * Constructeur d'Entreprise
	 * @param email L'adresse mail de l'entreprise
	 * @param statut Le statut de l'entreprise
	 * @param role Le rôle de l'entreprise
	 * @param nom Le nom de l'entreprise
	 * @param siret Le numéro SIRET de l'entreprise
	 * @param adresse L'adresse de l'entreprise
	 * @param siteWeb L'adresse du site de l'entreprise
	 * @param password Le mot de passe de l'entreprise
	 */
	public Entreprise(String email, StatutUtilisateur statut, Role role, String nom, String siret, String adresse, String siteWeb,
			String password) {
		super(email, statut, role);
		this.nom = nom;
		this.siret = siret;
		this.adresse = adresse;
		this.siteWeb = siteWeb;
		this.password = password;
	}

	/**
	 * 
	 * @return Le nom de l'entreprise
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom Le nom à attribuer à l'entreprise
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return Le numéro SIRET de l'entreprise
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * 
	 * @param siret Le numero SIRET à attribuer à l'entreprise
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}
	
	/**
	 * 
	 * @return L'adresse de l'entreprise
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * 
	 * @param adresse L'adresse à attribuer à l'entreprise
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * 
	 * @return L'adresse du site de l'entreprise
	 */
	public String getSiteWeb() {
		return siteWeb;
	}

	/**
	 * 
	 * @param siteWeb L'adresse de site à attribuer à l'entreprise
	 */
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	/**
	 * 
	 * @return Le mot de passe de l'entreprise
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password Le mot de passe à attribuer à l'entreprise
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Entreprise [nom=" + nom + ", siret=" + siret + ", adresse="
				+ adresse + ", siteWeb=" + siteWeb + ", password=" + password
				+ ", getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getStatut()=" + getStatut() + ", getRole()=" + getRole()
				+ "]";
	}

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((siret == null) ? 0 : siret.hashCode());
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
		Entreprise other = (Entreprise) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (siret == null) {
			if (other.siret != null)
				return false;
		} else if (!siret.equals(other.siret))
			return false;
		if (siteWeb == null) {
			if (other.siteWeb != null)
				return false;
		} else if (!siteWeb.equals(other.siteWeb))
			return false;
		return true;
	}	
	
}
