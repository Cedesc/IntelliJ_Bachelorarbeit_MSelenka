package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeTwice extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // nodes for tree1
        MyNode node10 = new MyNode(0);
        MyNode node11 = new MyNode(1);

        // create tree1
        Tree tree1 = create_Tree(types.NUMBER, node10);


        // nodes for the tree2
        MyNode node20 = new MyNode(0);
        MyNode node21 = new MyNode(1);
        MyNode node22 = new MyNode(2);
        MyNode node23 = new MyNode(3);
        MyNode node24 = new MyNode(4);
        MyNode node25 = new MyNode(5);

        // create tree2
        Tree tree2 = create_Tree(types.NUMBER, node20);

        // add leafs to tree2
        tree2.addLeaf(node20, node21);
        tree2.addLeaf(node20, node22);
        tree2.addLeaf(node20, node24);
        tree2.addLeaf(node22, node23);


        // add leaf to tree1
        tree1.addLeaf(node10, node11);


        // add leaf to tree2
        tree2.addLeaf(node23, node25);



    }

}
