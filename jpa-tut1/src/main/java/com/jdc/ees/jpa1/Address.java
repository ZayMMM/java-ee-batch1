package com.jdc.ees.jpa1;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Column;

@Entity
@SecondaryTables({
	@SecondaryTable(name="Division"),
	@SecondaryTable(name="Township")
})
@SuppressWarnings("serial")
public class Address implements Serializable{

	@Id
	private int id;
	private String address;
	@Column(table = "Township")
	private String township;
	@Column(table = "Division")
	private String division;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

}
