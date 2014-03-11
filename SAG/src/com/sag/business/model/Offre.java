package com.sag.business.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Offre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titre;
	private String description;
	private Type type;
	private int participantsMin;
	private int participantsMax;
	private double prix;
	private StatutOffre statut;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@Temporal(TemporalType.DATE)
	private Date dateAjout;
	
	private String siteWeb;
	
	@ManyToOne
	@JoinColumn(name="ID_EMETTEUR")
	@Column(nullable = false)
	private Utilisateur emetteur;
	
	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Entreprise fournisseur;
	
	@ManyToMany
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "ETUDIANT_ID"))
	private Set<Etudiant> participants;
	
	@ManyToMany
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "DOMAINE_ID"))
	private Set<Domaine> domaines;
	
	@OneToMany
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "PHOTO_ID"))
	private Set<Image> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getParticipantsMin() {
		return participantsMin;
	}

	public void setParticipantsMin(int participantsMin) {
		this.participantsMin = participantsMin;
	}

	public int getParticipantsMax() {
		return participantsMax;
	}

	public void setParticipantsMax(int participantsMax) {
		this.participantsMax = participantsMax;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public StatutOffre getStatut() {
		return statut;
	}

	public void setStatut(StatutOffre statut) {
		this.statut = statut;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public Utilisateur getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(Utilisateur emetteur) {
		this.emetteur = emetteur;
	}

	public Entreprise getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Entreprise fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Set<Etudiant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Etudiant> participants) {
		this.participants = participants;
	}

	public Set<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(Set<Domaine> domaines) {
		this.domaines = domaines;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
}
