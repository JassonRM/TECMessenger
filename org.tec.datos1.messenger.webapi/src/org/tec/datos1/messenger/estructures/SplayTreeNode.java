package org.tec.datos1.messenger.estructures;

public class SplayTreeNode <T extends Comparable<T>>{
	
    private SplayTreeNode<T> left;
    private SplayTreeNode<T> right;
    private SplayTreeNode<T> parent;
    T value;

    public SplayTreeNode(T Value)
    {
        this(Value, null, null, null);
    } 

    public SplayTreeNode(T Value,SplayTreeNode<T> parent)
    {
        this(Value, null, null, parent);
    }
    
    public SplayTreeNode(T Value, SplayTreeNode<T> Left, SplayTreeNode<T> Right, SplayTreeNode<T> Parent)
    {
        left = Left;
        right = Right;
        parent = Parent;
        value = Value;         
    }	

	public SplayTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(SplayTreeNode<T> Left) {
		left = Left;
		if (Left != null)
			Left.setParent(this);
	}

	public SplayTreeNode<T> getRight() {
		return right;
	}

	public void setRight(SplayTreeNode<T> Right) {
		right = Right;
		if (Right != null)
			Right.setParent(this);
	}

	public SplayTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(SplayTreeNode<T> Parent) {
		parent = Parent;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T Value) {
		value = Value;
	}    
}
