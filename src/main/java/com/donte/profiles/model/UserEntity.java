package com.donte.profiles.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class UserEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String name;
	private String email;
	@JsonIgnore
	private String pass;
	private Boolean active;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = {
	@JoinColumn(name = "USER_ID") }, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;

	@PreUpdate
	public void preUpdate() {
		updated = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		created = atual;
		updated = atual;
	}

	

}
