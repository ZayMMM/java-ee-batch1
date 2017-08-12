package com.jdc.ee.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;

@Entity
@SuppressWarnings("serial")
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String name;
	private double unitPrice;

	@ManyToMany
	@JoinTable(name = "ITEM_CATEGORY", joinColumns = @JoinColumn(name = "item"), inverseJoinColumns = @JoinColumn(name = "category"))
	private List<Category> categories;

	@ElementCollection
	@CollectionTable(name = "TAGS")
	private Set<String> tags;
	
	@ElementCollection
	@CollectionTable(name = "RATES")
	@MapKeyColumn(name="userId")
	private Map<String, Integer> rates;

	private Security security;

	public Map<String, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<String, Integer> rates) {
		this.rates = rates;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
