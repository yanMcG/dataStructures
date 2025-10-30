//package com.adsg.tree.tester;

//import com.adsg.tree.RedBlackTree;

public class RedBlackConsoleTester{
    public static void main(String[] args){
        RedBlackTree<Integer> myTree = new RedBlackTree<Integer>();
        myTree.insert(2);
        myTree.insert(1);
        myTree.insert(3);
        myTree.insert(5);
        //Node.toString();
        myTree.preOrderTraversal();

    }
}