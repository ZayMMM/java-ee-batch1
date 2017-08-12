package com.jdc.ee.entity;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Student extends Account {

	private int year;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
