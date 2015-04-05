package com.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Colis")
public class Colis {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String colisNumber; // format XXXX-XXXX-XXXX-XXXX

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String description;

	private String comment;

	// parameters
	private Double length; //longueur
	private Double width; //largeur
	private Double height; // heuteur
	private Double volume; //volume

	private Double weight; // kgnet

	private Date createdDate;

	final static int MAX_PERSON_COLIS = 3;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
			mappedBy="colisListe",
			fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Person> persons = new HashSet<Person>(0);

	private int createdBy;

	private boolean active = true;

	public Colis() {
		this.createdDate = new Date() ;
		this.colisNumber = UUID.randomUUID().toString();
	}

	public String getColisNumber() {
		return colisNumber;
	}

	public void setColisNumber(String colisNumero) {
		this.colisNumber = colisNumero;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public Set<Person> getPersons() throws CustomException {
		if (persons.size()>MAX_PERSON_COLIS) {
			throw new CustomException("Max persons by colis is not valid "+ persons.size());
		}
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public static int getMaxPersonColis() {
		return MAX_PERSON_COLIS;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
