package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeDegreeOne extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        MyNode node0 = new MyNode(0, 0, 0, 50);
        MyNode node1 = new MyNode(1, 11, 100, 50);
        MyNode node2 = new MyNode(2, 22, 200, 50);
        MyNode node3 = new MyNode(3, 33, 300, 50);
        MyNode node4 = new MyNode(4, 44, 400, 50);
        MyNode node5 = new MyNode(5, 55, 500, 50);

        Tree tree = create_Tree(types.NUMBER, node0);

        tree.addLeaf(node0, node1);
        tree.addLeaf(node1, node2);
        tree.addLeaf(node2, node3);
        tree.addLeaf(node3, node4);
        tree.addLeaf(node4, node5);

    }

}
