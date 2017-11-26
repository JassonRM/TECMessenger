package org.tec.datos1.messenger.webapi.dto;

public class Message {
	private String receiver;
	private String sender;
	private String date;
	private String Audio;
	private String File;
	private String body;
	private String image;
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String destinatario) {
		this.receiver = destinatario;
	}
	public String getContent() {
		return body;
	}
	public void setContent(String content) {
		this.body = content;
	}
	public String getOthers() {
		return image;
	}
	public void setOthers(String others) {
		this.image = others;
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
}
