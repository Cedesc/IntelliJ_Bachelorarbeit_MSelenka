package Algorithms.otherTreeAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeDegreeOne extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        MyNode node0 = new MyNode(0, 0);
        MyNode node1 = new MyNode(1, 11);
        MyNode node2 = new MyNode(2, 22);
        MyNode node3 = new MyNode(3, 33);
        MyNode node4 = new MyNode(4, 44);
        MyNode node5 = new MyNode(5, 55);

        Tree tree = create_Tree(types.NUMBER, node0);

        tree.addLeaf(0, node1);
        tree.addLeaf(1, node2);
        tree.addLeaf(2, node3);
        tree.addLeaf(3, node4);
        tree.addLeaf(4, node5);

    }

}
