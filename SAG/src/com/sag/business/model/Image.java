package com.sag.business.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe entité correspondant aux Images
 * @author Benjamin MIRETTI
 *
 */
@Entity
public class Image implements Serializable{

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id de l'image
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * L'URL de l'image
	 */
	private String url;
	
	/**
	 * Constructeur vide d'Image
	 */
	public Image(){
		super();
	}

	/**
	 * Constructeur d'Image
	 * @param id L'Id de l'image
	 * @param url L'URL de l'image
	 */
	public Image(int id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	/**
	 * 
	 * @return L'Id de l'image
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id L'Id à attribuer à l'image
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return L'URL de l'image
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url L'URL à attribuer à l'image
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Image [id=" + id + ", url=" + url + "]";
	}

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
}
