package com.sag.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.bval.constraints.Email;
import org.apache.bval.constraints.NotEmpty;

/**
 * Classe entité correspondant aux Utilisateurs
 * @author Benjamin MIRETTI
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur implements Serializable{

	/**
	 * Id pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'Id de l'Utilisateur
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * L'adresse mail de l'Utilisateur
	 */
	@NotEmpty(message = "Le mail est obligatoire")
	@Email(message = "Email invalide")
	@Column(nullable = false, unique = true)
	private String email;
	
	/**
	 * Le statut de l'Utilisateur
	 */
	private StatutUtilisateur statut;
	
	/**
	 * Le rôle de l'Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	private Role role;
	
	/**
	 * Constructeur vide d'Utilisateur
	 */
	public Utilisateur(){
		super();
	}
	
	/**
	 * Constructeur d'Utilisateur
	 * @param email L'adresse mail de l'Utilisateur
	 * @param statut Le statut de l'Utilisateur
	 * @param role Le rôle de l'Utilisateur
	 */
	public Utilisateur(String email, StatutUtilisateur statut, Role role) {
		super();
		this.email = email;
		this.statut = statut;
		this.role = role;
	}
	
	/**
	 * 
	 * @return L'Id de l'Utilisateur
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id L'Id à associer à l'Utilisateur
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return L'adresse mail de l'Utilisateur
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email L'adresse mail à associer à l'Utilisateur
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @return Le statut de l'Utilisateur
	 */
	public StatutUtilisateur getStatut() {
		return statut;
	}
	
	/**
	 * 
	 * @param statut Le statut à associer à l'Utilisateur
	 */
	public void setStatut(StatutUtilisateur statut) {
		this.statut = statut;
	}
	
	/**
	 * 
	 * @return Le rôle de l'Utilisateur
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * 
	 * @param role Le rôle à associer à l'Utilisateur
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return une String décrivant l'objet
	 */
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", email=" + email + ", statut="
				+ statut + ", role=" + role + "]";
	}

	/**
	 * @return un int représentant le hashcode de l'objet
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (statut != other.statut)
			return false;
		return true;
	}
	
	
}