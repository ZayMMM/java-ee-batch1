package com.jdc.ee.repo;

import java.util.HashMap;
import java.util.Map;

import com.jdc.ee.entity.Balance;
import com.jdc.ee.jpa.repo.Joinable;
import com.jdc.ee.jpa.repo.Searchable;
import com.jdc.ee.jpa.repo.Sortable;

public class SearchMemberBalance implements Searchable, Joinable, Sortable {

	@Override
	public String sort() {
		return "order by t.name ";
	}

	@Override
	public String join() {
		return "join t.balances b ";
	}

	@Override
	public String where() {
		return "where b.amount > :amount and b.type = :type ";
	}

	@Override
	public Map<String, Object> params() {
		Map<String, Object> params = new HashMap<>();
		params.put("amount", 2000);
		params.put("type", Balance.Type.Income);
		return params;
	}


}
