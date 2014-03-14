package com.sag.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="ID_U")
public class Entreprise extends Utilisateur{

	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String siret;
	private String adresse;
	private String siteWeb;
	
	@Column(nullable = false)
	private String password;
	
	public Entreprise(){
		super();
	}

	public Entreprise(String email, StatutUtilisateur statut, Role role, String nom, String siret, String adresse, String siteWeb,
			String password) {
		super(email, statut, role);
		this.nom = nom;
		this.siret = siret;
		this.adresse = adresse;
		this.siteWeb = siteWeb;
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Entreprise [nom=" + nom + ", siret=" + siret + ", adresse="
				+ adresse + ", siteWeb=" + siteWeb + ", password=" + password
				+ ", getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getStatut()=" + getStatut() + ", getRole()=" + getRole()
				+ "]";
	}

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
