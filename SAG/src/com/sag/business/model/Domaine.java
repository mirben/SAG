package com.sag.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe entité correspondant aux Domaines
 * @author Benjamin MIRETTI
 *
 */
@Entity
public class Domaine implements Serializable{

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id du domaine
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * Nom du domaine
	 */
	@Column(unique = true)
	private String nom;
	
	/**
	 * Constructeur vide de Domaine
	 */
	public Domaine(){
		super();
	}
	
	/**
	 * Constructeur de Domaine
	 * @param nom Le nom du domaine
	 */
	public Domaine(String nom) {
		super();
		this.nom = nom;
	}
	
	/**
	 * 
	 * @return L'Id du domaine 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id L'Id à attribuler au domaine
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Le nom du domaine
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * @param nom Le nom à attribue au domaine
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Domaine [id=" + id + ", nom=" + nom + "]";
	}

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Domaine other = (Domaine) obj;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
	
}
