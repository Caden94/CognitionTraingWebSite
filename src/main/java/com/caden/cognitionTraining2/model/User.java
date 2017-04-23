package com.caden.cognitionTraining2.model;

import java.util.Set;

import javax.persistence.*;

/* 
 * @Table maps the entity with the table. 
 * If no @Table is defined, the default value is used: the class name of the entity.
 */

@Entity
@Table(name = "user")
public class User {

	/*
	 * @Column maps the entity's field with the table's column. If @Column is
	 * omitted, the default value is used: the field name of the entity.
	 */

	private int id;
	private String username;
	private String password;
	private String passwordConfirm;
	private Set<Role> roles;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * mappedBy indicates the entity is the inverse of the relationship.
	 */

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
