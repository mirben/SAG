/**
 * @author Benjamin MIRETTI
 * @author Tuan NGUYEN (annotations de validation)
 */
package com.sag.business.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.bval.constraints.NotEmpty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe entité correspondant aux Offres
 * @author Benjamin MIRETTI
 * @author Tuan NGUYEN (annotations de validation)
 *
 */
@Entity
public class Offre implements Serializable {

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * L'Id de l'offre
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * Le titre de l'offre
	 */
	@NotEmpty(message = "Le titre est obligatoire")
	private String titre;

	/**
	 * La description de l'offre
	 */
	@NotEmpty(message = "La description est obligatoire")
	private String description;

	/**
	 * Le type de l'offre
	 */
	@Enumerated(EnumType.ORDINAL)
	private Type type;

	/**
	 * Le nombre de participants minimum pour l'offre
	 */
	@Min(0)
	private int participantsMin;
	
	/**
	 * Le nombre de participants maximum pour l'offre
	 */
	@Min(0)
	private int participantsMax;

	/**
	 * Le prix de l'offre
	 */
	@Min(0)
	private double prix;

	/**
	 * Le statut de l'offre
	 */
	@Enumerated(EnumType.ORDINAL)
	private StatutOffre statut;

	/**
	 * La date de début de l'offre
	 */
	@NotNull(message = "La date de début est incorrecte")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @Past(message = "La date de naissance doit être passé")
	private Date dateDebut;

	/**
	 * La date de fin de l'offre
	 */
	@NotNull(message = "La date de fin est incorrecte")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @Past(message = "La date de naissance doit être passé")
	private Date dateFin;

	/**
	 * La date d'ajout de l'offre
	 */
	private Date dateAjout;

	/**
	 * L'adresse web externe associée à l'offre
	 */
	private String siteWeb;

	/**
	 * L'Utilisateur émetteur de l'offre
	 */
	@ManyToOne
	@JoinColumn(name = "ID_EMETTEUR")
	private Utilisateur emetteur;

	/**
	 * Le fournisseur de l'offre
	 */
	@ManyToOne
	@JoinColumn(name = "ID_FOURNISSEUR")
	private Entreprise fournisseur;

	/**
	 * La liste des participants à l'offre
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "ETUDIANT_ID"))
	private Collection<Etudiant> participants;

	/**
	 * La liste des domaines de l'offre
	 */
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "DOMAINE_ID"))
	private Collection<Domaine> domaines;

	/**
	 * La liste des images de l'offre
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(inverseJoinColumns = @JoinColumn(name = "PHOTO_ID"))
	private Collection<Image> images;

	/**
	 * Constructeur vide d'Offre
	 */
	public Offre() {
		super();
		participants = new Vector<Etudiant>();
		domaines = new Vector<Domaine>();
		images = new Vector<Image>();
	}

	/**
	 * Constructeur d'Offre
	 * @param titre Le titre de l'offre
	 * @param description La description de l'offre
	 * @param type Le type de l'offre
	 * @param participantsMin Le nombre de participants minimum pour l'offre
	 * @param participantsMax Le nombre de participants maximum pour l'offre
	 * @param prix Le prix de l'offre
	 * @param statut Le statut de l'offre
	 * @param dateDebut La date de début de l'offre
	 * @param dateFin La date de fin de l'offre
	 * @param dateAjout La date d'ajout de l'offre
	 * @param siteWeb L'adresse web externe associée à l'offre
	 * @param emetteur L'Utilisateur émetteur de l'offre
	 * @param fournisseur Le fournisseur de l'offre
	 * @param participants La liste des participants à l'offre
	 * @param domaines La liste des domaines de l'offre
	 * @param images La liste des images de l'offre
	 */
	public Offre(String titre, String description, Type type,
			int participantsMin, int participantsMax, double prix,
			StatutOffre statut, Date dateDebut, Date dateFin, Date dateAjout,
			String siteWeb, Utilisateur emetteur, Entreprise fournisseur,
			Collection<Etudiant> participants, Collection<Domaine> domaines,
			Collection<Image> images) {
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
		this.participants = (participants != null) ? participants
				: new Vector<Etudiant>();
		this.domaines = (domaines != null) ? domaines : new Vector<Domaine>();
		this.images = (images != null) ? images : new Vector<Image>();
	}

	/**
	 * 
	 * @return L'Id de l'offre
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id L'Id à attribuer à l'offre
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Le titre de l'offre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * 
	 * @param titre Le titre à attribuer à l'offre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * 
	 * @return description La description de l'offre
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description La description à attribuer à l'offre
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return Le type de l'offre
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type Le type à attribuer à l'offre
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 
	 * @return Le nombre de participants minimum pour l'offre
	 */
	public int getParticipantsMin() {
		return participantsMin;
	}

	/**
	 * 
	 * @param participantsMin La nombre de participants minimum à attribuer à l'offre
	 */
	public void setParticipantsMin(int participantsMin) {
		this.participantsMin = participantsMin;
	}

	/**
	 * 
	 * @return Le nombre de participants maximum pour l'offre
	 */
	public int getParticipantsMax() {
		return participantsMax;
	}

	/**
	 * 
	 * @param participantsMax Le nombre de participants maximum à attribuer à l'offre
	 */
	public void setParticipantsMax(int participantsMax) {
		this.participantsMax = participantsMax;
	}

	/**
	 * 
	 * @return Le prix de l'offre
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * 
	 * @param prix La prix à attribuer à l'offre
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * 
	 * @return Le statut de l'offre
	 */
	public StatutOffre getStatut() {
		return statut;
	}

	/**
	 * 
	 * @param statut Le statut à attribuer à l'offre
	 */
	public void setStatut(StatutOffre statut) {
		this.statut = statut;
	}

	/**
	 * 
	 * @return La date de début de l'offre
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * 
	 * @param dateDebut La date de début à attribuer à l'offre
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * 
	 * @return La date de fin de l'offre
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * 
	 * @param dateFin La date de fin à attribuer à l'offre
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * 
	 * @return La date d'ajout de l'offre
	 */
	public Date getDateAjout() {
		return dateAjout;
	}

	/**
	 * 
	 * @param dateAjout La date d'ajout à attribuer à l'offre
	 */
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	/**
	 * 
	 * @return L'adresse web externe associée à l'offre
	 */
	public String getSiteWeb() {
		return siteWeb;
	}

	/**
	 * 
	 * @param siteWeb L'adresse web externe à attribuer à l'offre
	 */
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	/**
	 * 
	 * @return L'Utilisateur émetteur de l'offre
	 */
	public Utilisateur getEmetteur() {
		return emetteur;
	}

	/**
	 * 
	 * @param emetteur L'Utilisateur émetteur à attribuer à l'offre
	 */
	public void setEmetteur(Utilisateur emetteur) {
		this.emetteur = emetteur;
	}

	/**
	 * 
	 * @return Le fournisseur de l'offre
	 */
	public Entreprise getFournisseur() {
		return fournisseur;
	}

	/**
	 * 
	 * @param fournisseur Le fournisseur à attribuer à l'offre
	 */
	public void setFournisseur(Entreprise fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * 
	 * @return La liste des participants à l'offre
	 */
	public Collection<Etudiant> getParticipants() {
		return participants;
	}

	/**
	 * 
	 * @param participants La liste des participants à attribuer à l'offre
	 */
	public void setParticipants(Collection<Etudiant> participants) {
		this.participants = participants;
	}

	/**
	 * 
	 * @return La liste des domaines de l'offre
	 */
	public Collection<Domaine> getDomaines() {
		return domaines;
	}

	/**
	 * 
	 * @param domaines La liste des domaines à attribuer à l'offre
	 */
	public void setDomaines(Collection<Domaine> domaines) {
		this.domaines = domaines;
	}

	/**
	 * 
	 * @return La liste des images de l'offre
	 */
	public Collection<Image> getImages() {
		return images;
	}

	/**
	 * 
	 * @param images La liste des images à attribuer à l'offre
	 */
	public void setImages(Collection<Image> images) {
		this.images = images;
	}

	/**
	 * @return une String décrivant l'objet
	 */
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

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
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

	/**
	 * @param obj L'objet auquel l'objet courant sera comparé
	 * @return un booléen définissant si l'objet est égal à celui passé en paramètre
	 */
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
		} else if (!dateAjout.toString().equals(other.dateAjout.toString()))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.toString().equals(other.dateDebut.toString()))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.toString().equals(other.dateFin.toString()))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (domaines == null) {
			if (other.domaines != null)
				return false;
		} else if (!(domaines.containsAll(other.domaines) && other.domaines
				.containsAll(domaines)))
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
		} else if (!(images.containsAll(other.images) && other.images
				.containsAll(images)))
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!(participants.containsAll(other.participants) && other.participants
				.containsAll(participants)))
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
	
//	@AssertTrue(message="Dates incorrects")
//	public boolean checkDate(){
//		if(dateDebut.before(dateAjout))
//			return false;
//		if(dateFin.before(dateDebut))
//			return false;
//		return true;
//	}
//	
//	@AssertTrue(message="Nombre de participants incorrects")
//	public boolean checkNbParticipants(){
//		if(participantsMax<participantsMin)
//			return false;
//		return true;
//	}
}