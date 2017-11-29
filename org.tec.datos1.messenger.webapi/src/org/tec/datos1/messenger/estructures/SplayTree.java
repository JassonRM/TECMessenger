package org.tec.datos1.messenger.estructures;

public class SplayTree<T extends Comparable<T>> {
	
    private SplayTreeNode<T> root;

    public SplayTree() {
        setRoot(null);
    }

    public SplayTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(SplayTreeNode<T> root) {
		this.root = root;
	}

	public boolean isEmpty() {
        return getRoot() == null;
    }

    public void clear() {
        setRoot(null);
    }

    public void append(T Value){
		if (getRoot() == null) {
			setRoot(new SplayTreeNode<T>(Value));
			return;
		}
		setRoot(appendRecursive(Value, getRoot()));
		search(Value);
	}

	private SplayTreeNode<T> appendRecursive(T value, SplayTreeNode<T> node) {
		if (node == null) {
			node = new SplayTreeNode<T>(value);
		} else if (node.getValue().compareTo(value) > 0) {
			node.setLeft(appendRecursive(value, node.getLeft()));
		} else if (node.getValue().compareTo(value) < 0) {
			node.setRight(appendRecursive(value, node.getRight()));
		}
		return node;
		
	}
	
	public SplayTreeNode<T> search(T value) {
		SplayTreeNode<T> result = searchRecursive(value,root);
		
		if(result != null) {
			Splay(result);
		}
		return result;
	}

	private SplayTreeNode<T> searchRecursive(T value, SplayTreeNode<T> node) {
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
	
	private SplayTreeNode<T> deleteRecursive(T value, SplayTreeNode<T> node) {
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
	
	private SplayTreeNode<T> findMin(SplayTreeNode<T> node) {
		if (node.getLeft() == null) {
			return node;
		}
		return findMin(node.getLeft());
	}
	
	
    private void Splay(SplayTreeNode<T> node){
    	
        while (node.getParent() != null){
        	
            SplayTreeNode<T> Parent = node.getParent();
            SplayTreeNode<T> GrandParent = Parent.getParent();
            
            if (GrandParent == null){
                if (Parent.getLeft() == node) {
                    Parent = rotateLeftChild(Parent);
                }
                else {
                    Parent = rotateRightChild(Parent);
                }
                setRoot(Parent);
            } 
			else if (Parent.getLeft() == node) {
				if (GrandParent.getLeft() == Parent) {
					GrandParent = ZigZigLeft(GrandParent); 
				} else {
					GrandParent = ZigZagRight(GrandParent);
				}
				setRoot(GrandParent);
			} else {
				if (GrandParent.getLeft() == Parent) {
					GrandParent = ZigZagLeft(GrandParent);
				} else {
					GrandParent = ZigZigRight(GrandParent);
				}
				setRoot(GrandParent);
			}
            
            
        }
        
	}


	private SplayTreeNode<T> rotateRightChild(SplayTreeNode<T> node) {
		SplayTreeNode<T> rightChild = node.getRight();  
        node.setRight(rightChild.getLeft());
        
        SplayTreeNode<T> parent = node.getParent();
        rightChild.setParent(parent);
        
        if (parent != null) {
        	if (parent.getLeft() == node) {
        		node.getParent().setLeft(rightChild);
        	}else {
        		node.getParent().setRight(rightChild);
        	}
        }
        rightChild.setLeft(node);
        return rightChild;  
	}

	private SplayTreeNode<T> rotateLeftChild(SplayTreeNode<T> node) {
		SplayTreeNode<T> leftChild = node.getLeft();  
        node.setLeft(leftChild.getRight());
        SplayTreeNode<T> parent = node.getParent();
        leftChild.setParent(parent);
        if (parent != null) {
        	if (parent.getLeft() == node) {
        		node.getParent().setLeft(leftChild);
        	}else {
        		node.getParent().setRight(leftChild);
        	}
        }
        leftChild.setRight(node);
        return leftChild;  
	}
	
	private SplayTreeNode<T> ZigZigLeft(SplayTreeNode<T> node) {
		node = rotateLeftChild(node);  
        node = rotateLeftChild(node);  
        return node;           
	}
	
	private SplayTreeNode<T> ZigZagLeft(SplayTreeNode<T> node) {
        SplayTreeNode<T> auxiliar = node.getLeft();  
        node.setLeft(rotateRightChild(auxiliar));  
        node = rotateLeftChild(node);
        return node;          
	}
	
	private SplayTreeNode<T> ZigZigRight(SplayTreeNode<T> node) {
		node = rotateRightChild(node);
		node = rotateRightChild(node);
        return node;
	}
	
	private SplayTreeNode<T> ZigZagRight(SplayTreeNode<T> node) {
		SplayTreeNode<T> auxiliar = node.getRight();  
        node.setRight(rotateLeftChild(auxiliar));  
        node = rotateRightChild(node);
        return node;
	}
	
}

