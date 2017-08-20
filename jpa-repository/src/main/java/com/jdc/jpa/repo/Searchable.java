package com.jdc.jpa.repo;

import java.util.Map;

public interface Searchable {
	String where();
	Map<String, Object> params();
}
