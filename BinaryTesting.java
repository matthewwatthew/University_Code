/*Matthew Jordan (Mrj3dd)
 * Assignment 4- Interfaces
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class BinaryTesting {
	BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1);
	BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2);
	BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(3);
	BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(4);
	BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(5);
	BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(6);
	BinaryTreeNode<Integer> node7 = new BinaryTreeNode<Integer>(7);
	BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8);
	BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9);
	BinaryTreeNode<Integer> node10 = new BinaryTreeNode<Integer>(10);
	BinaryTreeNode<Integer> node11 = new BinaryTreeNode<Integer>(11);
	BinaryTreeNode<Integer> node12 = new BinaryTreeNode<Integer>(12);
	BinaryTreeNode<Integer> node13 = new BinaryTreeNode<Integer>(13);
	BinaryTreeNode<Integer> node14 = new BinaryTreeNode<Integer>(14);
	BinaryTreeNode<Integer> node15 = new BinaryTreeNode<Integer>(15);
	BinaryTreeNode<Integer> node31 = new BinaryTreeNode<Integer>(1);
	BinaryTreeNode<Integer> node32 = new BinaryTreeNode<Integer>(2);
	BinaryTreeNode<Integer> node33 = new BinaryTreeNode<Integer>(3);
	BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
	BinaryTree<Integer> tree4 = new BinaryTree<Integer>();
	BinaryTree<Integer> tree3 = new BinaryTree<Integer>();
	@Test
	public void testNodeEquals1() {
		node1.setData(10);
		node3.setData(10);
		assertTrue(node1.equals(node3));
	}
	@Test
	public void testNodeEquals2() {
		node1.setData(10);
		node3.setData(11);
		assertFalse(node1.equals(node3));
	}
	@Test
	public void testTreeEquals1() {
		tree3.setRoot(node1);
		tree1.setRoot(node31);
		node1.setLeft(node2);
		node1.setRight(node3);
		node31.setLeft(node32);
		node31.setRight(node33);
		assertTrue(tree1.equals(tree3));
	}
	@Test
	public void testTreeEquals2() {
		assertTrue(tree1.equals(tree3));
	}
	@Test
	public void testTreeDeepCopy1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
		tree2 = tree1.deepCopy();
		assertFalse(tree1.equals(tree2));
	}
	@Test
	public void testTreeDeepCopy2() {
		tree3 = tree1.deepCopy();
		assertTrue(tree1.equals(tree3));
	}
	@Test
	public void testNodeDeepCopy1() {
		node1.setLeft(node2);
		node1.setRight(node3);
		node3.setLeft(node9);
		node3.setRight(node7);
		node2.setLeft(node10);
		node2.setRight(node11);
		node11.setLeft(node15);
		node15.setRight(node13);
		BinaryTreeNode<Integer> newNode = node1.deepCopy();
		assertTrue(node1.equals(newNode));
	}
	@Test
	public void testNodeDeepCopy2() {
		BinaryTreeNode<Integer> newNode = node1.deepCopy();
		assertTrue(node1.equals(newNode));
	}
	@Test
	public void treeCombine1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		tree3.setRoot(node4);
		node4.setLeft(node5);
		node4.setRight(node6);
		BinaryTree<Integer> weirdTree = tree1.combine(node7, tree3, true);
		assertTrue(weirdTree.inOrder().equals("(2)(1)(3)(7)(5)(4)(6)"));
	}
	@Test
	public void treeCombine2() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		tree3.setRoot(node4);
		node4.setLeft(node5);
		node4.setRight(node6);
		BinaryTree<Integer> weirdTree = tree1.combine(node7, tree3, false);
		assertTrue(weirdTree.inOrder().equals("(5)(4)(6)(7)(2)(1)(3)"));
	}
	@Test
	public void testTreeSize1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		assertTrue(tree1.size() == 8);
	}
	@Test
	public void testTreeSize2() {
		assertTrue(tree1.size() == 0);
	}
	@Test
	public void testNodeSize1() {
		assertTrue(node1.size() == 1);
	}
	@Test
	public void testNodeSize2() {
		node1.setLeft(node12);
		node1.setRight(node11);
		assertTrue(node1.size() == 3);
	}

	@Test
	public void testTreeHeight1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		assertTrue(tree1.height() == 5);
	}
	@Test
	public void testTreeHeight2() {
		assertTrue(tree1.height() == 0);
	}
	@Test
	public void testNodeHeight1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		assertTrue(node2.height() == 4);
	}
	@Test
	public void testNodeHeight2() {
		assertTrue(node1.height() == 1);
	}
	@Test
	public void testTreeMirror1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		tree1.mirror();
		assertTrue(tree1.inOrder().equals("(3)(1)(5)(2)(4)"));
	}
	@Test
	public void testTreeMirror2() {
		tree1.setRoot(node1);
		tree1.mirror();
		assertTrue(tree1.inOrder().equals("(1)"));
	}
	@Test
	public void testNodeMirror1() {
		node1.setLeft(node3);
		node1.setRight(node2);
		node2.setLeft(node5);
		node2.setRight(node4);
		node1.mirror();
		assertTrue(node1.inOrder().equals("(4)(2)(5)(1)(3)"));
	}
	@Test
	public void testNodeMirror2() {
		node1.mirror();
		assertTrue(node1.inOrder().equals("(1)"));
	}
	
	@Test
	public void testFullTree1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		node3.setRight(node7);
		node4.setLeft(node8);
		node4.setRight(node9);
		node5.setLeft(node10);
		node5.setRight(node11);
		node6.setLeft(node12);
		node6.setRight(node13);
		node7.setLeft(node14);
		node7.setRight(node15);
		assertTrue(tree1.full());
	}
	@Test
	public void testFullTree2() {
		tree1.setRoot(node10);
		node10.setLeft(node1);
		assertFalse(tree1.full());
	}
	@Test
	public void testFullNode1() {
		assertTrue(node1.full());
	}
	@Test
	public void testFullNode2() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		assertTrue(node1.full());
	}

	@Test
	public void testTreeInOrder1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		assertTrue(tree1.inOrder().equals("(4)(2)(8)(7)(5)(1)(6)(3)"));
	}
	@Test
	public void testTreeInOrder2() {
		assertTrue(tree1.inOrder().equals(""));
	}
	@Test
	public void testNodeInOrder1() {
		tree1.setRoot(node1);
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		node7.setLeft(node8);
		node3.setLeft(node6);
		assertTrue(node2.inOrder().equals("(4)(2)(8)(7)(5)"));
	}
	@Test
	public void testNodeInOrder2() {
		tree1.setRoot(node1);
		assertTrue(node1.inOrder().equals("(1)"));
}
}
	