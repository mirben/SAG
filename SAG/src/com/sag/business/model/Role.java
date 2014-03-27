/**
 * @author Benjamin MIRETTI
 */
package com.sag.business.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe entité correspondant aux Rôles
 * @author Benjamin MIRETTI
 *
 */
@Entity
public class Role implements Serializable{

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'Id du Rôle
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * Le nom du rôle
	 */
	private String nom;
	
	/**
	 * Constructeur vide de Role
	 */
	public Role(){
		super();
	}

	/**
	 * Constructeur de Role
	 * @param nom Le nom du rôle
	 */
	public Role(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * 
	 * @return L'Id du Rôle
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id L'Id à attribuer au rôle
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Le nom du rôle
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom Le nom à attribuer au rôle
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + "]";
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
		Role other = (Role) obj;
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
