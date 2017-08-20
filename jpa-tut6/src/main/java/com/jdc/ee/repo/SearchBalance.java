package com.jdc.ee.repo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jdc.ee.entity.Balance.Type;
import com.jdc.ee.jpa.repo.Searchable;
import com.jdc.ee.jpa.repo.Sortable;

public class SearchBalance implements Searchable, Sortable {

	private Date from;
	private Date to;
	private Type type;
	private String name;

	public SearchBalance(Date from, Date to, Type type, String name) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.name = name;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String sort() {
		return "order by t.date desc";
	}

	@Override
	public String where() {
		
		StringBuffer sb = new StringBuffer();
		
		if(null != from) {
			sb.append("and t.date >= :from ");
		}
		
		if(null != to) {
			sb.append("and t.date <= :to ");
		}

		if(null != type) {
			sb.append("and t.type = :type ");
		}

		if(null != name) {
			sb.append("and upper(t.member.name) like upper(:name) ");
		}
		
		return sb.length() == 0 ? "" : sb.insert(0, "where 1 = 1 ").toString();
	}

	@Override
	public Map<String, Object> params() {
		
		Map<String, Object> params = new HashMap<>();
		
		if(null != from) {
			params.put("from", from);
		}
		
		if(null != to) {
			params.put("to", to);
		}

		if(null != type) {
			params.put("type", type);
		}

		if(null != name) {
			params.put("name", name.concat("%"));
		}

		return params;
	}

}
