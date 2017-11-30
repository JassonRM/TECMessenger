package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;

import org.tec.datos1.messenger.webapi.dto.Message;

public class BinaryTree<T extends Comparable<T>> {
	
	private BinaryTreeNode<T> root;
	
	public BinaryTree() {
		this(null); 
	}
	
	public BinaryTree(BinaryTreeNode<T> Root) {
		root = Root;
	}
	
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public void append(T Value) {
		
		if (root == null) {
			root = new BinaryTreeNode<T>(Value);
			return;
		}
		root = appendRecursive(Value,root);
		
	}

	private BinaryTreeNode<T> appendRecursive(T value, BinaryTreeNode<T> node) {
		if (node == null) {
			node = new BinaryTreeNode<T>(value);
		}
		else if (node.getValue().compareTo(value) > 0) {
			node.setLeft(appendRecursive(value, node.getLeft()));
		}
		else if (node.getValue().compareTo(value) < 0) {
			node.setRight(appendRecursive(value, node.getRight()));
		}
		return node;
	}
	
	public BinaryTreeNode<T> search(T value) {
		return searchRecursive(value,root);
	}

	private BinaryTreeNode<T> searchRecursive(T value, BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		else if (node.getValue().compareTo(value) > 0) {
			return searchRecursive(value, node.getLeft());
		}
		else if (node.getValue().compareTo(value) < 0) {
			return searchRecursive(value, node.getRight());
		}
		return node;
	}
	
	public void delete(T value) {
		root = deleteRecursive(value, root);
	}
	
	private BinaryTreeNode<T> deleteRecursive(T value, BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		else if (node.getValue().compareTo(value) > 0) {
			node.setLeft(deleteRecursive(value, node.getLeft()));
		}
		else if (node.getValue().compareTo(value) < 0) {
			node.setRight(deleteRecursive(value, node.getRight()));
		} 
		else if (node.getLeft() != null && node.getRight() != null) {
			T replace = findMin(node.getRight()).getValue();
			
			node.setValue(replace);
			node.setRight(deleteRecursive(replace,node.getRight()));
		}
		else {
			node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
		}
		return node;
	}

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
		if (node.getLeft() == null) {
			return node;
		}
		return findMin(node.getLeft());
	}
	
	public void searchMessages(String buscado, ArrayList<Message> result) {
		searchMessagesAux(buscado,result,root);
		
	}

	private void searchMessagesAux(String buscado, ArrayList<Message> result, BinaryTreeNode<T> node) {
		if (node == null) {return;}
		Message node2 = (Message)node.getValue();
		if (buscado.equals(node2.getReceiver()) || buscado.equals(node2.getSender()) 
				|| buscado.equals(node2.getAudio()) || buscado.equals(node2.getImage())
				|| buscado.equals(node2.getFile()) || buscado.equals(node2.getDate())){
			result.add(node2);
		}else {
			for (String word : node2.getBody().split(" ")) {
				if (buscado.equals(word)) {
					result.add(node2);
					break;
				}
			}
		}
		searchMessagesAux(buscado, result,node.getLeft());
		searchMessagesAux(buscado, result,node.getRight());
		
	}

	
}
