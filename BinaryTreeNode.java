/* Matthew Jordan (Mrj3dd)
 * Source Used: 1) http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 *              2) http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
 */


import java.io.Serializable;
import java.util.Stack;

@SuppressWarnings("serial")
public class BinaryTreeNode<T> implements Serializable {
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	private T data;
	String returnString = "";
	
	public BinaryTreeNode(){
		this(null,null,null);
	}
	
	public BinaryTreeNode(T theData){
		this(theData,null,null);
	}
	
	public BinaryTreeNode(T theData, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}
	
	
	public int size(){
		int size = 0;
		if(left != null){
			size = size + left.size();
		}
		
		if(right != null){
			size = size + right.size();
		}
		return size + 1;
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	@SuppressWarnings("unchecked")
	public BinaryTreeNode<T> deepCopy(){
		BinaryTreeNode<T> deepCopy = new BinaryTreeNode<T>();
		deepCopy.setLeft(this.getLeft());
		deepCopy.setRight(this.getRight());
		@SuppressWarnings("rawtypes")
		Stack<BinaryTreeNode> s2 = new Stack<BinaryTreeNode>();
		BinaryTreeNode<T> newNode = this.getLeft();
		BinaryTreeNode<T> deepNode = deepCopy;
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
			BinaryTreeNode<T> newNode1 = this.getRight();
			BinaryTreeNode<T> deepNode1 = deepCopy;
			while(newNode1 != null) {
				s2.push(newNode1);
				newNode1 = newNode1.getLeft();
				deepNode1.setLeft(newNode1);
				deepNode1 = deepNode1.getLeft();
				}
				while (s2.size() > 0) {
					newNode1 = s2.pop();
					deepNode1 = newNode1;
					if (newNode1.getRight() != null) {
						newNode1 = newNode1.getRight();
						if (newNode1 != null){
						deepNode1.setRight(newNode1);
						deepNode1 = deepNode1.getRight();
						}
						while (newNode1 != null) {
							s2.push(newNode1);
							newNode1 = newNode1.getLeft();
							deepNode1.setLeft(newNode1);
							deepNode1 = deepNode1.getLeft();
						}
					}	
				}
				deepCopy.setData(this.getData());
				deepCopy.setLeft(this.getLeft());
				if (this.getLeft() != null) {
				this.getLeft().setLeft(deepNode);
				}
				deepCopy.setRight(this.getRight());
				if (this.getRight() != null)
				this.getRight().setLeft(deepNode1);
				return deepCopy;
	}
	@Override
    public boolean equals(Object o){	
        if(o instanceof BinaryTreeNode != true) {
            return false;
            }
        @SuppressWarnings("unchecked")
		BinaryTreeNode<T> node = (BinaryTreeNode<T>) o;
        if (this.data == node.data) {
            if (this.inOrder().equals(node.inOrder())){
            return true;
            }
        }
		return false;
}
	
	public int height(){
		if(this.getLeft() == null) {
			if(this.getRight() == null) {
				return 1;
			}
			else {
				return this.getRight().height() + 1;
			}
		}
		if(this.getRight()== null) {
		
			if(this.getLeft() == null) {
				return 0;
			}
			else {
				return this.getLeft().height() + 1;
			}
		}
		return Math.max(this.getLeft().height() + 1, this.getRight().height() + 1);
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
	
	public void mirror(){
		mirror(this);
	}
	public BinaryTreeNode<T> mirror(BinaryTreeNode<T> insert) { //USED SOURCE 2 HERE
		if (insert == null) {
			return insert;
		}
		BinaryTreeNode<T> leftSide = mirror(insert.getLeft());
		BinaryTreeNode<T> rightSide = mirror(insert.getRight());
		insert.setLeft(rightSide);
		insert.setRight(leftSide);
		return insert;
	}
	public String inOrder() {
		return inOrder(this);
	}

	public String inOrder(BinaryTreeNode<T> nodular){ // (Used Source 1 here) 
		if (nodular == null) {
			return "";
		}
		inOrder(nodular.getLeft());
		returnString = returnString + "(" + nodular.getData() + ")";
		inOrder(nodular.getRight());
		return returnString;
	}
}