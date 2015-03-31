package com.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.annotations.Phone;
import com.enumurations.Gender;


@Entity
@Table(name = "Person") 
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotBlank
	private String firstname;

	@NotNull
	@NotBlank
	private String name;
    
	private String country;

	@Size(min=10, max=155)
	private String adress;

	@NotNull
	@NotBlank
	@Email
	@Column(name="email", unique=true)
	private String email;

	@Column(name="created_at")
	private final Date createdDate;

	private Gender gender;

	@NotNull
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Past
	private Date birthday;

	@NotNull
	@NotBlank
	@Phone
	private String phone;

	private boolean active = true;

	@OneToOne(cascade = CascadeType.ALL)
	private Role role = new Role();

	@NotBlank
	@NotNull
	@Column(name="login",unique=true)
	private String login;

	@NotBlank
	@NotNull
	private String password;

	public Person () {
		this.createdDate = new Date();
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adressString) {
		this.adress = adressString;
	}

	public int getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
