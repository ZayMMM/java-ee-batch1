package com.jdc.ee.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="Member.getAll", query="select c from Member c"),
	@NamedQuery(name="Member.getAllCount", query="select count(c) from Member c"),
	@NamedQuery(name="Member.findByName", query="select c from Member c where c.name = :name")
})
public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	
	private String name;
	
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
