package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeDegreeOne extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        MyNode node0 = new MyNode(0, 0, 50, 0);
        MyNode node1 = new MyNode(1, 11, 50, 100);
        MyNode node2 = new MyNode(2, 22, 50, 200);
        MyNode node3 = new MyNode(3, 33, 50, 300);
        MyNode node4 = new MyNode(4, 44, 50, 400);
        MyNode node5 = new MyNode(5, 55, 50, 500);

        Tree tree = create_Tree(types.NUMBER, node0);

        tree.addLeaf(node0, node1);
        tree.addLeaf(node1, node2);
        tree.addLeaf(node2, node3);
        tree.addLeaf(node3, node4);
        tree.addLeaf(node4, node5);

    }

}
