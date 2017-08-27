package com.jdc.jsf.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("serial")
public class Friend implements Serializable {

	public enum Group {
		ClassMate {
			@Override
			public String toString() {
				return "Class Mate";
			}
		},
		University, Company, Other
	}

	public enum Interest {
		Internet, Facebook, IT, Arts, Music, Movie, Sports
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "group_name")
	private Group group;
	private String phone;
	private String email;
	private String remark;
	@Enumerated
	@ElementCollection
	private Set<Interest> interests;

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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

}
