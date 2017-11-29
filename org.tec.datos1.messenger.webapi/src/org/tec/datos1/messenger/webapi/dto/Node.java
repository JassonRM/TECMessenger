package org.tec.datos1.messenger.webapi.dto;

public class Node {
	String id;
	int group;
	
	public Node(String id, int group) {
		this.id = id;
		this.group = group;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
}
