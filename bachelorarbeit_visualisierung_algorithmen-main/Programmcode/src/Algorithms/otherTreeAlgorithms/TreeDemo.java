package Algorithms.otherTreeAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeDemo extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // nodes for the tree
        MyNode node00 = new MyNode(0);
        MyNode node01 = new MyNode(1);
        MyNode node02 = new MyNode(2);
        MyNode node03 = new MyNode(3);
        MyNode node04 = new MyNode(4);
        MyNode node05 = new MyNode(5);
        MyNode node06 = new MyNode(6);
        MyNode node07 = new MyNode(7);
        MyNode node08 = new MyNode(8);
        MyNode node09 = new MyNode(9);
        MyNode node10 = new MyNode(10);
        MyNode node11 = new MyNode(11);
        MyNode node12 = new MyNode(12);
        MyNode node13 = new MyNode(13);


        // create tree
        Tree tree = create_Tree(types.NUMBER, node00);

        // add leafs to tree on level 1
        tree.addLeaf(0, node01);
        tree.addLeaf(0, node02);
        tree.addLeaf(0, node03);
        tree.addLeaf(0, node04);

        // add leaf to tree on level 2
        tree.addLeaf(1, node05);
        tree.addLeaf(1, node06);
        tree.addLeaf(1, node07);
        tree.addLeaf(2, node08);
        tree.addLeaf(2, node09);
        tree.addLeaf(3, node10);

        // add leaf to tree on level 3
        tree.addLeaf(6, node11);
        tree.addLeaf(8, node12);
        tree.addLeaf(9, node13);

    }

}
