package org.tec.datos1.messenger.webapi.dto;

public class Graph {
	Node[] nodes;
	Link[] links;

	public Graph(Node[] nodes, Link[] links) {
		this.nodes = nodes;
		this.links = links;
	}

	public Node[] getNodes() {
		return nodes;
	}
	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}
	public Link[] getLinks() {
		return links;
	}
	public void setLinks(Link[] links) {
		this.links = links;
	}
}
