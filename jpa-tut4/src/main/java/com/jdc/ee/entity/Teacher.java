package com.jdc.ee.entity;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Teacher extends Account {
	
	private String post;

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

}
