package org.tec.datos1.messenger.webapi.dto;

import java.util.ArrayList;

public class Message implements Comparable<Message>{
	private String receiver;
	private String sender;
	private String date;
	private ArrayList<String> path ;
	private String Audio;
	private String File;
	private String body;
	private String image;
	public ArrayList<String> getPath() {
		return this.path;
	}
	public void setPath(ArrayList<String> lista) {
		 this.path =lista ;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String destinatario) {
		this.receiver = destinatario;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAudio() {
		return Audio;
	}
	public void setAudio(String audio) {
		Audio = audio;
	}
	public String getFile() {
		return File;
	}
	public void setFile(String file) {
		File = file;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int compareTo(Message object) {
		if (object.receiver.compareTo(receiver) != 0) {
			return object.receiver.compareTo(receiver);
		}else if(object.sender.compareTo(sender) != 0) {
			return object.sender.compareTo(sender);
		}else if(object.date.compareTo(date) != 0) {
			return object.date.compareTo(date);
		}else if(object.Audio.compareTo(Audio) != 0) {
			return object.Audio.compareTo(Audio);
		}else if(object.File.compareTo(File) != 0) {
			return object.File.compareTo(File);
		}else if(object.body.compareTo(body) != 0) {
			return object.body.compareTo(body);
		}else if(object.image.compareTo(image) != 0) {
			return object.image.compareTo(image);
		}else {
			return 0;
		}
	}
}
