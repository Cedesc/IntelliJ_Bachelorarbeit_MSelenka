package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeSimple extends AbstractAlgorithm {

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
        tree.addLeaf(node00, node01);
        tree.addLeaf(node00, node02);
        tree.addLeaf(node00, node03);
        tree.addLeaf(node00, node04);

        // add leaf to tree on level 2
        tree.addLeaf(node01, node05);
        tree.addLeaf(node01, node06);
        tree.addLeaf(node01, node07);
        tree.addLeaf(node02, node08);
        tree.addLeaf(node02, node09);
        tree.addLeaf(node03, node10);

        tree.changeValue(node07, 107);

        // add leaf to tree on level 3
        tree.addLeaf(node06, node11);
        tree.addLeaf(node08, node12);
        tree.addLeaf(node09, node13);

        // change values
        tree.changeValue(node00, 100);
        tree.changeValue(node05, 105);

    }

}
