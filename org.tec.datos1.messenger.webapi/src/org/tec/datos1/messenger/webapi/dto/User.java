package org.tec.datos1.messenger.webapi.dto;

import java.util.ArrayList;

public class User implements Comparable<User>{
	String username;
	String ipAddress;
	String path;
	ArrayList<Message> toReceive = new ArrayList<>();;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void addMessage(Message newMessage) {
		toReceive.add(newMessage);
	}
	public void deleteMessage(Message toDelete) {
		toReceive.remove(toDelete);
	}
	public ArrayList<Message> getMessages() {
		return this.toReceive;
	}
	public void clearMessages() {
		toReceive.clear();
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
