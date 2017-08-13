package com.jdc.ee.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class State implements Serializable {

	public State() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "state", orphanRemoval = true)
	private List<Township> townships;

	public List<Township> getTownships() {
		return townships;
	}

	public void setTownships(List<Township> townships) {
		this.townships = townships;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}