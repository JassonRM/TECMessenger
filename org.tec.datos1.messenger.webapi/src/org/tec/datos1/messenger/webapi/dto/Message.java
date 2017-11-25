package org.tec.datos1.messenger.dto;

public class Message {
	private String receiver;
	private String content;
	private String others;
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String destinatario) {
		this.receiver = destinatario;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
}
