package com.jdc.ees.jpa1;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Customer Class that map with Customer Table
 * 
 * @author minlwin
 * @since 2017
 */
@Entity
public class Customer implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String name;
	private String phone;
	private String email;
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Customer() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
