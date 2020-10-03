package com.aritra.media.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Location implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	@NotNull
	private String name;
	public Location(Long id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Location() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + "]";
	}

}
