package org.tec.datos1.messenger.webapi.dto;

public class Link {
	String source;
	String target;
	int value;
	
	public Link(Node source, Node target, int value) {
		this.source = source.id;
		this.target = target.id;
		this.value = value;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
