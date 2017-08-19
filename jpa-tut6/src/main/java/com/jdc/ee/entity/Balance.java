package com.jdc.ee.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name="Balance.getAll", query="select c from Balance c"),
	@NamedQuery(name="Balance.getAllCount", query="select count(c) from Balance c"),
	@NamedQuery(name="Balance.findByName", query="select c from Balance c where c.member.name = :name")
})
public class Balance implements Serializable {

	public enum Type {
		Income, Expenses
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private int amount;
	@Column(name = "reference_date")
	@Temporal(DATE)
	private Date date;
	private String details;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Member member;
	private Type type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
}
