package org.tec.datos1.messenger.estructures;

public class AVLTreeNode<T extends Comparable<T>> {

	private T value;
	private AVLTreeNode<T> right;
	private AVLTreeNode<T> left;
	private Integer height;

	public AVLTreeNode(T Value, AVLTreeNode<T> Right, AVLTreeNode<T> Left, Integer Height) {
		value = Value;
		right = Right;
		left = Left;
		height = Height;
	}

	public AVLTreeNode(T Value) {
		this(Value, null, null,0);
		
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public AVLTreeNode<T> getRight() {
		return right;
	}

	public AVLTreeNode<T> getLeft() {
		return left;
	}

	public void setRight(AVLTreeNode<T> Right) {
		right = Right;
	}

	public void setLeft(AVLTreeNode<T> Left) {
		left = Left;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public boolean hasChildren() {
		return (right != null || left != null);
	}
	
	public void calculateHeight() {
		
		Integer newHeight;
		if (!hasChildren()) {
			newHeight = 0;
		}else if (right == null) {
			newHeight = left.getHeight();
		}else if (left == null) {
			newHeight = right.getHeight();
		}else {
			newHeight = (right.getHeight() > left.getHeight() 
					? right.getHeight():left.getHeight()); 
		}
		setHeight(newHeight + 1);
	}
	
	public AVLTreeNode<T> rotateLeftChild() {
		AVLTreeNode<T> n1 = left;
        left = n1.right;
        n1.right = this;
        this.calculateHeight();
        n1.calculateHeight();
        return n1;
	}
	
	public AVLTreeNode<T> rotateDoubleLeft(){
		left = left.rotateRightChild();
		return this.rotateLeftChild();
	}

	public AVLTreeNode<T> rotateRightChild() {
		AVLTreeNode<T> n1 = right;
        right = n1.left;
        n1.left = this;
        this.calculateHeight();
        n1.calculateHeight();
        return n1;
	}
	
	public AVLTreeNode<T> rotateDoubleRight(){
		right = right.rotateLeftChild();
		return this.rotateRightChild();
	}
	
}	

