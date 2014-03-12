package com.sag.business.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name="ID_U")
public class Etudiant extends Utilisateur{
	
	@Column(nullable = false, unique = true)
	private String logENT;
	private String nom; 
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaiss;
	private String adresse;
	private String siteWeb;
	private String formation;
	
	@ManyToMany
	private Set<Domaine> domaines; 
	
	public Etudiant(){
		super();
	}
	
	public Etudiant(String logENT, String nom, String prenom, Date dateNaiss,
			String adresse, String siteWeb, String formation,
			Set<Domaine> domaines) {
		super();
		this.logENT = logENT;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.adresse = adresse;
		this.siteWeb = siteWeb;
		this.formation = formation;
		this.domaines = domaines;
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
	

}
