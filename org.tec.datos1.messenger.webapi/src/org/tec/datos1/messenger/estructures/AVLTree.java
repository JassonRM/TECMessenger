package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;

import org.tec.datos1.messenger.webapi.dto.Message;

public class AVLTree<T extends Comparable<T>>{
	private AVLTreeNode<T> root;
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(AVLTreeNode<T> Root) {
		setRoot(Root);
	}

	public AVLTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(AVLTreeNode<T> root) {
		this.root = root;
	}
	
	private Integer validateHeight(AVLTreeNode<T> node){
		if (node == null) {
			return 0;
		}
		return node.getHeight(); 
	}
	
	public void append(T value) {
		root = appendRecursive(value,root);
		
	}
	
	private AVLTreeNode<T> appendRecursive(T value, AVLTreeNode<T> node) {
		if (node == null) {
			node = new AVLTreeNode<T>(value);
		}
		else if(value.compareTo(node.getValue()) < 0) {
			node.setLeft(appendRecursive(value, node.getLeft()));
		}
		else if (value.compareTo(node.getValue()) > 0) {
			node.setRight(appendRecursive(value, node.getRight()));
		}
		node = balance(node);
		node.calculateHeight();
		return node;
	}
	
	public AVLTreeNode<T> search(T value){
		return searchRecursive(value,root);
	}
	
	private AVLTreeNode<T> searchRecursive(T value, AVLTreeNode<T> node) {
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
		deleteRecursive(value, root);
	}

	private AVLTreeNode<T> deleteRecursive(T value, AVLTreeNode<T> node) {
		
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
			if (node == root) {
				node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
				root = node;
			}else {
				node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
			}
			
			
		}
		
		node = balance(node);
		return node;
	}
	
	private AVLTreeNode<T> findMin(AVLTreeNode<T> node) {
		if (node.getLeft() == null) {
			return node;
		}
		return findMin(node.getLeft());
	}
	
	private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
		
		if (node == null) {
			return null;
		}
		
		Integer balance = validateHeight(node.getLeft())
				- validateHeight(node.getRight());
		if (balance == 2){
			if (node.getLeft().getLeft() != null) {
				if (node == root){
					root = node.getLeft();
				}
				node = node.rotateLeftChild();
				
			}
			else {
				if (node == root){
					root = node.getLeft().getRight();
				}
				node = node.rotateDoubleLeft();
			}
		}
		else if (balance == -2) {
			if (node.getRight().getRight() != null) {
				if (node == root) {
					root = node.getRight();
				} 
				node = node.rotateRightChild();
			}
			else {
				if (node == root) {
					root = node.getRight().getLeft();
				}
				node = node.rotateDoubleRight();
			}
		}
		node.calculateHeight();
		return node;
	}

	public void searchMessages(String buscado, ArrayList<Message> result) {
		searchMessagesAux(buscado,result,root);
		
	}

	private void searchMessagesAux(String buscado, ArrayList<Message> result, AVLTreeNode<T> node) {
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
