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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name="ID_U")
public class Etudiant extends Utilisateur{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	
	@NotNull(message = "Le nom est obligatoire")
	@Size(min = 6, message = "Le nom est obligatoire")
	private String logENT;
	
	@NotNull(message = "Le nom est obligatoire")
	@Size(min = 1, message = "Le nom est obligatoire")
	private String nom; 
	
	@NotNull(message = "Le nom est obligatoire")
	@Size(min = 1, message = "Le nom est obligatoire")
	private String prenom;
	
	//@Temporal(TemporalType.DATE)
	@NotNull(message = "La date de naissance est obligatoire")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "La date de naissance doit être passé")
	private Date dateNaiss;
	private String adresse;
	private String siteWeb;
	private String formation;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Domaine> domaines; 
	
	public Etudiant(){
		super();
		domaines = new Vector<Domaine>();
	}
	
	public Etudiant(String email, StatutUtilisateur statut, Role role, String logENT, String nom, String prenom, Date dateNaiss,
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
	
	public String getLogENT() {
		return logENT;
	}
	public void setLogENT(String logENT) {
		this.logENT = logENT;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
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
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}

	public Collection<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(Collection<Domaine> domaines) {
		this.domaines = domaines;
	}

	@Override
	public String toString() {
		return "Etudiant [logENT=" + logENT + ", nom=" + nom + ", prenom="
				+ prenom + ", dateNaiss=" + dateNaiss + ", adresse=" + adresse
				+ ", siteWeb=" + siteWeb + ", formation=" + formation
				+ ", domaines=" + domaines + ", getId()=" + getId()
				+ ", getEmail()=" + getEmail() + ", getStatut()=" + getStatut()
				+ ", getRole()=" + getRole() + "]";
	}

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
		} else if (!(domaines.containsAll(other.domaines) && other.domaines.containsAll(domaines)))
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
