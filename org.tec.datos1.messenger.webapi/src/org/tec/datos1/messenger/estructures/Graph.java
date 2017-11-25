package org.tec.datos1.messenger.estructures;

import java.util.Arrays;
import java.util.LinkedList;

public class Graph<T extends Comparable<T>> {
	
	private LinkedList<Vertex<T>> vertices = new LinkedList<>();
	
	
	public LinkedList<Vertex<T>> getVertices() {
		return vertices;
	}

	public void setVertices(LinkedList<Vertex<T>> vertices) {
		this.vertices = vertices;
	}

	public void addVertex(T newVertex) {
		addVertex(newVertex,null,0);
	}
	
	public void addVertex(T newVertex, T adjacentVertex, int weight) {
		int index = 0;
		for (Vertex<T> current : vertices) {
			if (current.getValue().compareTo(newVertex) >= 0) {
				break;
			}
			index++;
		}
		Vertex<T> secondVertex = new Vertex<>(newVertex);
		vertices.add(index,secondVertex);
		if (adjacentVertex == null) {
			return;
		}
		for (Vertex<T> current : vertices) {
			if (current.getValue().compareTo(adjacentVertex) == 0) {
				current.addEdge(new Edge<T>(secondVertex, weight));
			}
		}
		
	}
	public void addEdge(Vertex<T> firstVertex, Vertex<T> secondVertex, int weight) {
		for (Vertex<T> current : vertices) {
			if (current.equals(firstVertex)) {
				current.addEdge(new Edge<T>(secondVertex,weight));
			}
		}
	}
	
	public Vertex<T> getVertex(T value) {
		Vertex<T> result = null;
		for (Vertex<T> current : vertices) {
			if((current.getValue()).compareTo(value) == 0) {
				result = current;
				break;
			}
		}
		return result;
	}
	
	public void removeVertex(T vertex) {
		
		Vertex<T> nodeToDelete = null;
		for (Vertex<T> currentVertex : vertices) {
			if (currentVertex.getValue().compareTo(vertex) == 0) {
				nodeToDelete = currentVertex;
				continue;
			}
			LinkedList<Edge<T>> edges = currentVertex.getEdges();
			for (Edge<T> currentEdge: edges) {
				if (currentEdge.getSecondVertex().getValue().compareTo(vertex) == 0) {
					edges.remove(currentEdge);
				}
			}
		}
		if (nodeToDelete != null) {
			vertices.remove(nodeToDelete);
		}
	}
	public void removeEdge(Vertex<T> firstVertex, Vertex<T> secondVertex) {
		for (Vertex<T> currentVertex : vertices) {
			if (currentVertex.equals(firstVertex)) {
				LinkedList<Edge<T>> edges = currentVertex.getEdges();
				for (Edge<T> currentEdge :edges) {
					if (currentEdge.getSecondVertex().equals(secondVertex)) {
						edges.remove(currentEdge);
					}
				}
					
			}
		}
	}
	
	public boolean[][] Warshall(){
		int matrixSize = vertices.size();
		boolean[][] matrix = setWarshallMatrix(matrixSize);
		
		for (int i = 0; i < matrixSize; i++) {
			
			for (int j = 0; j < matrixSize; j++) {
				
				for (int k = 0; k < matrixSize; k++ ) {
					
					if (!matrix[j][k] && matrix[j][i] && matrix[i][k]) {
						matrix[j][k] = true;
					}
				}
			}
		}
		return matrix;
		
	}
	
	public LinkedList<Vertex<T>> Floyd (Vertex<T> firstVertex, Vertex<T> secondVertex){
		int matrixSize = vertices.size();
		
		int i = vertices.indexOf(firstVertex);
		int j = vertices.indexOf(secondVertex);
		
		LinkedList<Vertex<T>> path = new LinkedList<>();
		path.add(firstVertex);
		
		Integer[][] matrix = setFloydMatrix(matrixSize);
		
		for (int k = 0; k <matrixSize; k++) {
			int alternativeWeight = matrix[i][k] + matrix[k][j];
			if (matrix[i][k] == Integer.MAX_VALUE || matrix[k][j] == Integer.MAX_VALUE) {
				continue;
			}
			else if (matrix[i][j] > alternativeWeight) {
				matrix[i][j] = alternativeWeight;
				path.add(vertices.get(k));
			}
		}
		
		path.add(secondVertex);
		return path;
	}
	
	public LinkedList<Vertex<T>> Dijkstra(Vertex<T> source) {
		if (source == null) {
			return null;
		}
		int verticesSize = vertices.size();
		LinkedList<Vertex<T>> previous = new LinkedList<>();
		for(int i = 0; i<verticesSize; i++) {
			previous.add(null);
		} 
		
		Integer[] distances = new Integer[verticesSize];
		Arrays.fill(distances,Integer.MAX_VALUE);
		distances[vertices.indexOf(source)] = 0;
		
		LinkedList<Vertex<T>> visited = new LinkedList<>();
		LinkedList<Vertex<T>> nonVisited = new LinkedList<>();
		
		nonVisited.add(source);
		
		while (visited.size() <= verticesSize) {
			Vertex<T> current = minWeight(nonVisited,distances);
			if (current == null) {break;}
			DijkstraAux(current, previous, distances,nonVisited,visited);
			visited.add(current);
		}
		return previous;
	}
	
	private void DijkstraAux(Vertex<T> analized, LinkedList<Vertex<T>> previous, Integer[] distances,LinkedList<Vertex<T>> nonVisited,LinkedList<Vertex<T>> visited) {
		nonVisited.remove(analized);
		for (Edge<T> current : analized.getEdges()) {
			int pos = vertices.indexOf(current.getSecondVertex());
			if (distances[pos] > current.getWeight() + distances[vertices.indexOf(analized)]) {
				distances[pos] = distances[vertices.indexOf(analized)] + current.getWeight();
				
				if (!visited.contains(current.getSecondVertex()) && !nonVisited.contains(current.getSecondVertex())) {
					nonVisited.add(current.getSecondVertex());
				}
				previous.set(pos, analized);
			}
		}
		
	}

	private Vertex<T> minWeight(LinkedList<Vertex<T>> Vertices,Integer[] distances) {
		if (Vertices.size() == 0) {
			return null;
		}
		
		Vertex<T> result = Vertices.get(0);
		int index = vertices.indexOf(result);
		
		for (Vertex<T> current : Vertices) {
			int currentIndex = vertices.indexOf(current);
			if (distances[index] > distances[currentIndex]) {
				result = current;
				index = currentIndex;
			}
		}
		return result;
	}

	
	private boolean[][] setWarshallMatrix(int matrixSize) {
		boolean[][] matrix = new boolean[matrixSize][matrixSize];
		
		int row = 0;
		for (Vertex<T> currentVertex: vertices) {
			for (Edge<T> edge : currentVertex.getEdges()) {
				int column = vertices.indexOf(edge.getSecondVertex());
				matrix[row][column] = true;
			}
			matrix[row][row] = true;
			row++;
		}
		return matrix;
	}

	private Integer[][] setFloydMatrix(int matrixSize) {
		Integer[][] matrix = new Integer[matrixSize][matrixSize];
		
		for (Integer[] row : matrix) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		int row = 0;
		for (Vertex<T> currentVertex: vertices) {
			for (Edge<T> edge : currentVertex.getEdges()) {
				int column = vertices.indexOf(edge.getSecondVertex());
				matrix[row][column] = edge.getWeight();
			}
			matrix[row][row] = 0;
			row++;
		}
		return matrix;
		
	}
	
	
	@Override
	public String toString() {
		String result = "";
		for (Vertex<T> current : vertices) {
			result += current.toString();
		}
		return result;
		
	}
	
}
