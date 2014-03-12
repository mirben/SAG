package com.sag.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private StatutUtilisateur statut;
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	private Role role;
	
	public Utilisateur(){
		super();
	}
	
	public Utilisateur(int id, String email, StatutUtilisateur statut, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.statut = statut;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StatutUtilisateur getStatut() {
		return statut;
	}
	public void setStatut(StatutUtilisateur statut) {
		this.statut = statut;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
