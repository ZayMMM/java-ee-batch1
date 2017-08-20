package com.jdc.ee.jpa.repo;

import java.util.Map;

public interface Searchable {
	String where();
	Map<String, Object> params();
}
