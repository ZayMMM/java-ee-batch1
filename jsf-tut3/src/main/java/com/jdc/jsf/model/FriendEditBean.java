package com.jdc.jsf.model;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Friend;
import com.jdc.jsf.entity.Friend.Group;
import com.jdc.jsf.entity.Friend.Interest;
import com.jdc.jsf.service.FriendService;

@Model
public class FriendEditBean {
	
	private Group [] groups;
	private Interest [] interests;
	private Friend friend;
	
	@EJB
	private FriendService service;
	
	@PostConstruct
	private void init() {
		groups = Group.values();
		interests = Interest.values();
		friend = new Friend();
	}
	
	public String save() {
		service.create(friend);
		return "/index?faces-redirect=true";
	}

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public Interest[] getInterests() {
		return interests;
	}

	public void setInterests(Interest[] interests) {
		this.interests = interests;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

}
