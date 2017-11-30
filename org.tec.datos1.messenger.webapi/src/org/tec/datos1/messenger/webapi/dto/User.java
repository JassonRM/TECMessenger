package org.tec.datos1.messenger.webapi.dto;

import java.util.ArrayList;

import org.tec.datos1.messenger.estructures.AVLTree;
import org.tec.datos1.messenger.estructures.BTree;
import org.tec.datos1.messenger.estructures.BinaryTree;
import org.tec.datos1.messenger.estructures.SplayTree;
import org.tec.datos1.messenger.webapi.resources.Auth;

public class User implements Comparable<User>{
	String username;
	String ipAddress;
	String path;
	
	public SplayTree<Message> sentMessages = new SplayTree<>();
	public AVLTree<Message> errorSentMessages = new AVLTree<>();
	public BinaryTree<Message> bridgeMessages = new BinaryTree<>();
	public BTree<Message> receivedMessages = new BTree<>(5);
	
	private ArrayList<String> files = new ArrayList<>();
	ArrayList<Message> toReceive = new ArrayList<>();;
	public Boolean changes = false;

	public User(String user,String Ip) {
		this.username = user;
		this.ipAddress = Ip;
	}

	public void swichChange() {
		if(changes) {
			changes = false;
		}else {
			changes = true;
		}
	}
	public void addFileName(String file) {
		getFiles().add(file);
	}
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

	public ArrayList<String> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void fillMessages() {
		for (Message message: Auth.allMessages) {
			if (message.getReceiver().equals(username)) {
				receivedMessages.append(message);
			}else if (message.getSender().equals(username)) {
				sentMessages.append(message);
			}else{
				for(String path : message.getPath()) {
					if (path.equals(username)) {
						bridgeMessages.append(message);
					}
				}	
			}
		}
		
	}
	public ArrayList<Message> searchMessages(String buscado) {
		ArrayList<Message> result = new ArrayList<>();
		sentMessages.searchMessages(buscado,result);
		errorSentMessages.searchMessages(buscado,result);
		bridgeMessages.searchMessages(buscado,result);
		receivedMessages.searchMessages(buscado,result);
		return result;
	}
}
