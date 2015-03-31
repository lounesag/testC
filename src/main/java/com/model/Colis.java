package com.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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

@Entity
@Table(name ="Colis")
public class Colis {

	@Id
	@Column(name = "colis_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String colisNumero; // format XXXX-XXXX-XXXX-XXXX

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

//	@ManyToMany(mappedBy="colisL")
//	private Set<Person> persons = new HashSet<Person>(MAX_PERSON_COLIS);

	public Colis() {
		this.createdDate = new Date() ;
	}

	public String getColisNumero() {
		return colisNumero;
	}

	public void setColisNumero(String colisNumero) {
		this.colisNumero = colisNumero;
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

//	public Set<Person> getPersons() throws CustomException {
//		if (persons.size()>MAX_PERSON_COLIS) {
//			throw new CustomException("Max persons by colis is not valid "+ persons.size());
//		}
//		return persons;
//	}
//
//	public void setPersons(Set<Person> persons) {
//		this.persons = persons;
//	}

}
