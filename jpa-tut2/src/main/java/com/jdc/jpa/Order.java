package com.jdc.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import static javax.persistence.TemporalType.DATE;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Lob;
import javax.persistence.Basic;
import static javax.persistence.FetchType.LAZY;

@Entity
@SuppressWarnings("serial")
@Table(name = "ORDER_TBL")
public class Order implements Serializable{

	@Transient
	private boolean checks;
	
	@EmbeddedId
	private OrderPK id;
	private String name;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "quentity")
	private int count;
	private double tax;
	
	@Temporal(DATE)
	private Date orderDate;
	@Enumerated(STRING)
	private Status status;
	
	@Lob
	@Basic(fetch = LAZY)
	private String remark;
	
	public boolean isChecks() {
		return checks;
	}

	public void setChecks(boolean checks) {
		this.checks = checks;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public OrderPK getId() {
		return id;
	}

	public void setId(OrderPK id) {
		this.id = id;
	}

	public enum Status {
		Accept, Cencel, Shipped, Finished
	}
}
