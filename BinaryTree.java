// Matthew Jordan (mrj3dd) 
// Assignment 4.
// Sources Used- Source 1- https://stackoverflow.com/questions/728360/how-do-i-correctly-clone-a-javascript-object
//               Source 2- http://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

import java.util.Stack;
public class BinaryTree<T>{
	
	int finalValue = 0;
	private BinaryTreeNode<T> root;
	private String stingy = "";

	public BinaryTree() {
		this(null);
	}

	public BinaryTree(BinaryTreeNode<T> newRoot) {
		this.root = newRoot;
	}
	
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof BinaryTree != true) {
			return false;
		}
		BinaryTree<T> node = (BinaryTree<T>) o;
		if (node.getRoot() == null){
			if (this.getRoot() == null){
				return true;
			}
			else {
				return false;
			}
		}
		if(this.getRoot().equals(node.getRoot())) {
			if (this.inOrder().equals(node.inOrder())){
				if(this.size() == node.size()) {
					return true;
		}
			}
		}
		return false;
		}

	public BinaryTree<T> deepCopy() { // SOURCE 1
		BinaryTree<T> deepCopy = new BinaryTree<T>();
		deepCopy.setRoot(this.getRoot());
		Stack<BinaryTreeNode> s2 = new Stack<BinaryTreeNode>();
		BinaryTreeNode<T> newNode = this.root;
		BinaryTreeNode<T> deepNode = deepCopy.root;
		while(newNode != null) {
			s2.push(newNode);
			newNode = newNode.getLeft();
			deepNode.setLeft(newNode);
			deepNode = deepNode.getLeft();
			}
			while (s2.size() > 0) {
				newNode = s2.pop();
				deepNode = newNode;
				if (newNode.getRight() != null) {
					newNode = newNode.getRight();
					if (newNode != null){
					deepNode.setRight(newNode);
					deepNode = deepNode.getRight();
					}
					while (newNode != null) {
						s2.push(newNode);
						newNode = newNode.getLeft();
						deepNode.setLeft(newNode);
						deepNode = deepNode.getLeft();
					}
				}
			}
		return deepCopy;
		
	}

	public BinaryTree<T> combine(BinaryTreeNode<T> newRoot, BinaryTree<T> t,
			boolean left) {
		BinaryTreeNode<T> newRoot1 = newRoot.deepCopy(); 
		BinaryTree<T> finalTree = new BinaryTree<T>();
		finalTree.setRoot(newRoot1);
		if (left == true) {
			BinaryTree<T> node1 = t.deepCopy();
			BinaryTree<T> node2 = this.deepCopy();
			newRoot1.setRight(node1.getRoot());
			newRoot1.setLeft(node2.getRoot());
		}
		if (left == false) {
			BinaryTree<T> node1 = t.deepCopy();
			BinaryTree<T> node2 = this.deepCopy();
			newRoot1.setLeft(node1.getRoot());
			newRoot1.setRight(node2.getRoot());
		}
		return finalTree;
	}
	
	public int size(){
		int size = 0;
		if (this.getRoot() == null) {
				return 0;
			}
		if (root.getLeft() != null) {
			size = size + root.getLeft().size();
		}
		if(root.getRight() != null) {
			size = size + root.getRight().size();
		}
		return size + 1;
	}
	
	public int height(){
		int height1 = 1;
		int height2 = 1;
		if (root == null) {
			return 0;
		}
		if(root.getLeft() == null) {
			height1 = 0;
		}
		if (root.getRight() == null) {
			height2 = 0;
		}
		if (height1 != 0) {
		height1 = height1 + root.getLeft().height();
		}
		if (height2 != 0) {
		height2 = height2 + root.getRight().height();
		}
		if(height1 > height2) {
			return (height1);
		}
		else {
			return height2;
		}
		
	}
	
	public boolean full(){
		int x = 1;
		int counter = 1;
		int value = this.height();
		while (counter != value) {
			x = (x * 2) + 1;
			counter = counter + 1;
		}
		if (x == this.size()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void mirror() {
		this.root = mirror(root);
	}	
	
	public BinaryTreeNode<T> mirror(BinaryTreeNode<T> insert){
		if (insert == null) {
			return insert;
		}
		BinaryTreeNode<T> leftSide = mirror(insert.getLeft());
		BinaryTreeNode<T> rightSide = mirror(insert.getRight());
		insert.setLeft(rightSide);
		insert.setRight(leftSide);
		return insert;
		}
	
	public String inOrder(){ // SOURCE 2
		stingy = "";
		if (this.root == null) {
			return stingy;
		}
		Stack<BinaryTreeNode> s1 = new Stack<BinaryTreeNode>();
		BinaryTreeNode<T> dode = this.root;
		while (dode != null) {
			s1.push(dode);
			dode = dode.getLeft();
		}
		while (s1.size() > 0) {
			dode = s1.pop();
			stingy = stingy + "(" + dode.getData() + ")";
			if (dode.getRight() != null) {
				dode = dode.getRight();
				
				while (dode != null) {
					s1.push(dode);
					dode = dode.getLeft();
				}
			}
		}
		return stingy;
	}
}