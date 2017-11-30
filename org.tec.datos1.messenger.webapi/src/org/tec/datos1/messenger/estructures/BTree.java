package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;
import java.util.List;

import org.tec.datos1.messenger.webapi.dto.Message;

public class BTree<T extends Comparable<T>> {
	
	private BTreePage<T> root;
	
	public BTree(Integer size) {
		BTreePage<T> Root = new BTreePage<>();
		Root.setSize(3);
		setRoot(Root);
	}
	
	public BTree(BTreePage<T> Root) {
		setRoot(Root);
	}

	public BTreePage<T> getRoot() {
		return root;
	}

	public void setRoot(BTreePage<T> root) {
		this.root = root;
	}
	
	public void append(T value) {
		if (root == null) {
			return;
		}
		else if (root.getSize() == -1){
			System.err.println("Must Set Tree's Size");
			return;
		}
		appendRecursive(value,root);
	}
	
	private void appendRecursive(T value, BTreePage<T> page){
		Integer pos = 0;
		for( T key : page.getKeys()) {
			if (value.compareTo(key) < 0) {
				break;
			}
			pos++;
		}
		if (page.isLeaf()) {
			page.addKey(value);
		}
		else {
			appendRecursive(value, page.getBranches().get(pos));
		}
		
		balance(page);
		
	}

	private void balance(BTreePage<T> page) {
		if (page.getKeys().size() > page.getSize()-1) {
			Double pos = Math.ceil((page.getSize()-1)/2);
			T movingElement = page.getKeys().get(pos.intValue());
			
			if (page.getParent() == null) {
				BTreePage<T> newParent = new BTreePage<>(root.getSize());
				setRoot(newParent);
				newParent.addKey(movingElement);
				newParent.setBranches(page.split(pos.intValue(),newParent));
			}
			else {
				BTreePage<T> parent = page.getParent();
				Integer index = parent.addKey(movingElement);
				parent.replaceBranch(page,pos.intValue(), index);
			}
		}
	}
	
	public T search(T value) {
		if (root == null){
			return null;
		}
		return searchRecursive(value, root);
	}
	
	private T searchRecursive(T value, BTreePage<T> page) {
		Integer pos = 0;
		for( T key : page.getKeys()) {
			if (value.compareTo(key) == 0) {
				return key;
			}
			else if (value.compareTo(key) < 0) {
				break;
			}
			pos++;
		}
		if (page.isLeaf()) {
			return null;
		}
		else {
			return searchRecursive(value, page.getBranches().get(pos));
		}
		
	}
	
	public void delete(T value) {
		if (root == null) {
			return;
		}
		deleteRecursive(value,root);
	}

	private void deleteRecursive(T value, BTreePage<T> page) {}
	
	public void searchMessages(String buscado, ArrayList<Message> result) {
		searchMessagesAux(buscado,result,root);
		
	}

	private void searchMessagesAux(String buscado, ArrayList<Message> result, BTreePage<T> node) {
		if (node == null) {return;}
		
		@SuppressWarnings("unchecked")
		List<Message> nodes = (List<Message>) node.getKeys();
		if (nodes == null) {return;}
		for (Message node2 : nodes) {
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
		}
		for (BTreePage<T> branch : node.getBranches()) {
			searchMessagesAux(buscado, result,branch);
		}
		
		
		
	}
	
}
