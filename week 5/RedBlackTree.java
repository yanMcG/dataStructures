//package com.adsg.tree;

/**
 * This is a "generic" Binary Search Tree - know the definition of what a BST is!
 * 
 * NOTE: To allow for our objects to be inserted (and found) properly they have to be COMPARED
 * to the objects in the tree. This is why we have <T extends Comparable<T>> instead of 
 * just <T> : We are effectively saying that the objects which can be stored MUST implement
 * the Comparable interface.
 * 
 * NOTE: Our Node class is an inner class in an inner class at the bottom of the code.
 * 
 * @author dermot.hegarty
 *
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> {
	/**
	 * Reference to the root of the tree
	 */
	public Node root;

	/**
	 * This is the public insert method, i.e. the one that the outside world will invoke.
	 * It then kicks off a recursive method to "walk" through down through the tree - this is 
	 * possible because each sub-tree is itself a tree.
	 * @param value Object to insert into the tree
	 */
	public void insert(T value){
		Node node = new Node(value); // Create the Node to add

		//Special case that cannot be handled recursively
		if ( root == null ) {
			root = node;
			// Do not set root black here; handleRedBlack will do it
			handleRedBlack(root);
			return;
		}

		//Initially we start at the root. Each subsequent recursive call will be to a 
		//left or right subtree.
		insertRec(root, node);
		handleRedBlack(node);
	}

    
	/**
	 * 
	 * @param subTreeRoot The SubTree to insert into
	 * @param node The Node that we wish to insert
	 */
	protected void insertRec(Node subTreeRoot, Node node){

		//Note the call to the compareTo() method. This is only possible if our objects implement
		//the Comparable interface.
		if ( node.value.compareTo(subTreeRoot.value) < 0){

			//This is our terminal case for recursion. We should be going left but there is 
			//no leaf node there so that is obviously where we must insert
			if ( subTreeRoot.left == null ){
				subTreeRoot.left = node;
				node.parent = subTreeRoot; // Set parent
				return;
			}
			else{ // Note that this allows duplicates!
				
				//Now our new "root" is the left subTree
				insertRec(subTreeRoot.left, node);
			}
		}
		//Same logic for the right subtree
		else{
			if (subTreeRoot.right == null){
				subTreeRoot.right = node;
				node.parent = subTreeRoot; // Set parent
				return;
			}
			else{
				insertRec(subTreeRoot.right, node);
			}
		}
	}
	
	
	/**
	 * Should traverse the tree "in-order." See the notes
	 */
	public void inOrderTraversal()
	{
		//start at the root and recurse
		recInOrderTraversal(root);
	}
	
	public void preOrderTraversal()
	{
		//start at the root and recurse
		recPreOrderTraversal(root);
	}
	
	public void postOrderTraversal()
	{
		//start at the root and recurse
		recPostOrderTraversal(root);
	}
	
	/**
	 * This allows us to recursively process the tree "in-order". Note that it is protected
	 * @param subTreeRoot
	 */
	protected void recInOrderTraversal(Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recInOrderTraversal(subTreeRoot.left);
		processNode(subTreeRoot);
		recInOrderTraversal(subTreeRoot.right);
	}
	
	protected void recPreOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		processNode(subTreeRoot);
		recPreOrderTraversal(subTreeRoot.left);
		recPreOrderTraversal(subTreeRoot.right);
	}
	
	protected void recPostOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recPostOrderTraversal(subTreeRoot.left);
		recPostOrderTraversal(subTreeRoot.right);
		processNode(subTreeRoot);
	}
	
	/** 
	 * Do some "work" on the node - here we just print it out 
	 * @param currNode
	 */
	protected void processNode(Node currNode)
	{
		System.out.println(currNode.toString());
	}
	
	/**
	 * 
	 * @return The number of nodes in the tree
	 */
	public int countNodes()
	{
		return recCountNodes(root);
	}
	
	
	/**
	 * Note: This is a practical example of a simple usage of pre-order traversal
	 * @param subTreeRoot
	 * @return
	 */
	protected int recCountNodes(Node subTreeRoot)
	{
		if (subTreeRoot == null) return 0;
		
		//Look at the pre-order. "Count this node and THEN count the left and right 
		//subtrees recursively
		return 1 + recCountNodes(subTreeRoot.left) + recCountNodes(subTreeRoot.right);
	}
	
	/////////////////////////////////////////////////////////////////
	/**
	 * Our Node contains a value and a reference to the left and right subtrees (initially null)
	 * @author dermot.hegarty
	 *
	 */
	public class Node {
		public T value; //value is the actual object that we are storing
		public Node left;
		public Node right;
        public Node parent; // New field for parent
        public boolean nodeColourRed; // New field for red-black

		public Node(T value) {
			this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.nodeColourRed = true; // New nodes are red by default
		}

		@Override
		public String toString() {
			String colour = nodeColourRed ? "Red" : "Black";
			return "Node [value=" + value + ", nodeColour=" + colour + "]";
		}
	}



//FIND MINIMUM VALUE IN TREE
    public T findMinimum()
 {
 return recFindMinimum(root);
 }

 private T recFindMinimum (Node subTreeRoot)
 {
    if (subTreeRoot == null) {
        return null; // or throw exception if preferred
    }
    if (subTreeRoot.left == null) {
        return subTreeRoot.value;
    }
    return recFindMinimum(subTreeRoot.left);
 }





// FIND MAX VALU IN THE TREE
 public T findMaximum()
 {
	 return recFindMaximum(root);
 }

 private T recFindMaximum (Node subTreeRoot)
 {
	 while(subTreeRoot.right != null) {
         subTreeRoot = subTreeRoot.right;
     }
     return subTreeRoot.value;
 }





//FIND A VALUE IN THE TREE
 public T find(T searchVal)
 {
 //start at the root and recurse
 return recFind(root, searchVal);
 }

 private T recFind(Node subTreeRoot, T searchVal)
 {
    if (subTreeRoot == null) {
        return null; // Not found
    }
    int cmp = searchVal.compareTo(subTreeRoot.value);
    if (cmp == 0) {
        return subTreeRoot.value; // Found
    } else if (cmp < 0) {
        return recFind(subTreeRoot.left, searchVal); // Search left
    } else {
        return recFind(subTreeRoot.right, searchVal); // Search right
    }
 }


//RORTATE TREE LEFT
 public void rotateTreeLeft()
 {
	 root = rotateSubTreeLeft(root);
 }

 //ROTATE TREE RIGHT
 public void rotateTreeRight()
 {
	 root = rotateSubTreeRight(root);
 }

 protected Node rotateSubTreeLeft(Node subTreeRoot)
 {
	 if (subTreeRoot == null || subTreeRoot.right == null) {
		 return subTreeRoot; // Cannot perform rotation
	 }
	 Node pivot = subTreeRoot.right;
	 subTreeRoot.right = pivot.left;
	 pivot.left = subTreeRoot;
	 return pivot;
 }

 protected Node rotateSubTreeRight(Node subTreeRoot)
 {
	 if (subTreeRoot == null || subTreeRoot.left == null) {
		 return subTreeRoot; // Cannot perform rotation
	 }
	 Node pivot = subTreeRoot.left;
	 subTreeRoot.left = pivot.right;
	 pivot.right = subTreeRoot;
	 return pivot;
 }

 // Add handleRedBlack method (basic version for single insert)
 protected void handleRedBlack(Node node) {
    if (node == root) {
        node.nodeColourRed = false; // Root is always black
        return;
    }
    if (node.parent == null || node.parent.parent == null) {
        return; // Prevent NullPointerException for root or shallow nodes
    }
	// Red Uncle scenario and other balancing logic to be added here
	// Define uncle node
	Node uncle = null;
	if (node.parent != null && node.parent.parent != null) {
		if (node.parent == node.parent.parent.left) {
			uncle = node.parent.parent.right;
		} else {
			uncle = node.parent.parent.left;
		}
	}
	//Check if uncle is Black (four subcases to handle)
	else if((uncle == null) || !uncle.nodeColourRed) {
		//Left Left Case
		if((node == node.parent.left) && (node.parent == node.parent.parent.left)) {
			rotateSubTreeRight(node.parent.parent);
			node.parent.nodeColourRed = false;
			node.parent.right.nodeColourRed = true;
		}
		//Right Right Case
		else if((node == node.parent.right) && (node.parent == node.parent.parent.right)) {
			rotateSubTreeLeft(node.parent.parent);
			node.parent.nodeColourRed = false;
			node.parent.left.nodeColourRed = true;
		}
		//Left Right Case
		else if((node == node.parent.right) && (node.parent == node.parent.parent.left)) {
			rotateSubTreeLeft(node.parent);
			rotateSubTreeRight(node.parent);
			node.nodeColourRed = false;
			node.left.nodeColourRed = true;
			node.right.nodeColourRed = true;
		}
		//Right Left Case
		else if((node == node.parent.left) && (node.parent == node.parent.parent.right)) {
			rotateSubTreeRight(node.parent);
			rotateSubTreeLeft(node.parent);
			node.nodeColourRed = false;
			node.left.nodeColourRed = true;
			node.right.nodeColourRed = true;
		}

}
 }
}