package org.tec.datos1.messenger.communication;

public class Message {
	private String destinatario;
	private String content;
	private String otros;
	
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getContent() {
		return content;
	}
	public void setConteto(String content) {
		this.content = content;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	
}
