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
	@NamedQuery(name="Category.getAll", query="select c from Category c"),
	@NamedQuery(name="Category.getAllCount", query="select count(c) from Category c"),
	@NamedQuery(name="Category.findByName", query="select c from Category c where c.category = :name")
})
public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
