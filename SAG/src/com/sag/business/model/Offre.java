package com.sag.business.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Offre implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	
	//@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	//@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	//@Temporal(TemporalType.DATE)
	private Date dateAjout;
	
	private String siteWeb;
	
	@ManyToOne
	@JoinColumn(name="ID_EMETTEUR")
	private Utilisateur emetteur;
	
	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Entreprise fournisseur;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "ETUDIANT_ID"))
	private Collection<Etudiant> participants;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "DOMAINE_ID"))
	private Collection<Domaine> domaines;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns=@JoinColumn(name= "PHOTO_ID"))
	private Collection<Image> images;
	
	public Offre(){
		super();
		participants = new Vector<Etudiant>();
		domaines = new Vector<Domaine>();
		images = new Vector<Image>();
	}

	public Offre(String titre, String description, Type type,
			int participantsMin, int participantsMax, double prix,
			StatutOffre statut, Date dateDebut, Date dateFin, Date dateAjout,
			String siteWeb, Utilisateur emetteur, Entreprise fournisseur,
			Set<Etudiant> participants, Set<Domaine> domaines, Set<Image> images) {
		super();
		this.titre = titre;
		this.description = description;
		this.type = type;
		this.participantsMin = participantsMin;
		this.participantsMax = participantsMax;
		this.prix = prix;
		this.statut = statut;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateAjout = dateAjout;
		this.siteWeb = siteWeb;
		this.emetteur = emetteur;
		this.fournisseur = fournisseur;
		this.participants = (participants != null) ? participants : new Vector<Etudiant>();
		this.domaines = (domaines != null) ? domaines : new Vector<Domaine>();
		this.images = (images != null) ? images : new Vector<Image>();
	}

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

	public Collection<Etudiant> getParticipants() {
		return participants;
	}

	public void setParticipants(Collection<Etudiant> participants) {
		this.participants = participants;
	}

	public Collection<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(Collection<Domaine> domaines) {
		this.domaines = domaines;
	}

	public Collection<Image> getImages() {
		return images;
	}

	public void setImages(Collection<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Offre [id=" + id + ", titre=" + titre + ", description="
				+ description + ", type=" + type + ", participantsMin="
				+ participantsMin + ", participantsMax=" + participantsMax
				+ ", prix=" + prix + ", statut=" + statut + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin + ", dateAjout="
				+ dateAjout + ", siteWeb=" + siteWeb + ", emetteur=" + emetteur
				+ ", fournisseur=" + fournisseur + ", participants="
				+ participants + ", domaines=" + domaines + ", images="
				+ images + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateAjout == null) ? 0 : dateAjout.hashCode());
		result = prime * result
				+ ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((domaines == null) ? 0 : domaines.hashCode());
		result = prime * result
				+ ((emetteur == null) ? 0 : emetteur.hashCode());
		result = prime * result
				+ ((fournisseur == null) ? 0 : fournisseur.hashCode());
		result = prime * result + id;
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result
				+ ((participants == null) ? 0 : participants.hashCode());
		result = prime * result + participantsMax;
		result = prime * result + participantsMin;
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((siteWeb == null) ? 0 : siteWeb.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offre other = (Offre) obj;
		if (dateAjout == null) {
			if (other.dateAjout != null)
				return false;
		} else if (!dateAjout.equals(other.dateAjout))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (domaines == null) {
			if (other.domaines != null)
				return false;
		} else if (!domaines.equals(other.domaines))
			return false;
		if (emetteur == null) {
			if (other.emetteur != null)
				return false;
		} else if (!emetteur.equals(other.emetteur))
			return false;
		if (fournisseur == null) {
			if (other.fournisseur != null)
				return false;
		} else if (!fournisseur.equals(other.fournisseur))
			return false;
		if (id != other.id)
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		if (participantsMax != other.participantsMax)
			return false;
		if (participantsMin != other.participantsMin)
			return false;
		if (Double.doubleToLongBits(prix) != Double
				.doubleToLongBits(other.prix))
			return false;
		if (siteWeb == null) {
			if (other.siteWeb != null)
				return false;
		} else if (!siteWeb.equals(other.siteWeb))
			return false;
		if (statut != other.statut)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
