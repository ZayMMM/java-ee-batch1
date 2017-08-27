package com.jdc.jsf.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jpa.repo.Searchable;
import com.jdc.jsf.entity.Friend;
import com.jdc.jsf.entity.Friend.Group;
import com.jdc.jsf.service.FriendService;

@Model
public class FriendListBean implements Searchable {

	private Group[] groups;
	private List<Friend> friend;

	private Group schGroup;
	private String schPhone;
	private String schName;
	
	@EJB
	private FriendService service;
	
	@PostConstruct
	private void init() {
		groups = Group.values();
		search();
	}
	
	public String search() {
		friend = service.find(this);
		return null;
	}

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public List<Friend> getFriend() {
		return friend;
	}

	public void setFriend(List<Friend> friend) {
		this.friend = friend;
	}

	public Group getSchGroup() {
		return schGroup;
	}

	public void setSchGroup(Group schGroup) {
		this.schGroup = schGroup;
	}

	public String getSchPhone() {
		return schPhone;
	}

	public void setSchPhone(String schPhone) {
		this.schPhone = schPhone;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	@Override
	public String where() {
		
		StringBuffer sb = new StringBuffer();
		
		if(null != schGroup) {
			sb.append("and t.group = :group ");
		}
		
		if(null != schPhone && !schPhone.isEmpty()) {
			sb.append("and t.phone like :phone ");
		}
		
		if(null != schName && !schName.isEmpty()) {
			sb.append("and upper(t.name) like upper(:name) ");
		}

		return sb.length() > 0 ? sb.insert(0, "where 1 = 1 ").toString() : "";
	}

	@Override
	public Map<String, Object> params() {
		
		Map<String, Object> params = new HashMap<>();
		
		if(null != schGroup) {
			params.put("group", schGroup);
		}
		
		if(null != schPhone && !schPhone.isEmpty()) {
			params.put("phone", schPhone.concat("%"));
		}
		
		if(null != schName && !schName.isEmpty()) {
			params.put("name", schName.concat("%"));
		}
		
		return params;
	}

}
