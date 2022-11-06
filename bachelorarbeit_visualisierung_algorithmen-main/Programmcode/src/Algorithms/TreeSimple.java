package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

//        Tree tree1 = create_Tree(types.NUMBER,
//                new MyNode(0, 33, null, null, null, 15, 15));

        MyNode node4 =
                new MyNode(4, 444, 300, 100);
        MyNode node3 =
                new MyNode(3, 333, 200, 200);
        MyNode node2 =
                new MyNode(2, 222, 200, 100);
        MyNode node1 =
                new MyNode(1, 111, 50, 100);
        MyNode node0 =
                new MyNode(0, 24, 100, 10);


        Tree tree2 = create_Tree(types.NUMBER, node0);

        tree2.addLeaf(node0, node1);
        tree2.addLeaf(node0, node2);
        tree2.addLeaf(node0, node4);
        tree2.addLeaf(node2, node3);

        MyNode node5 =
                new MyNode(5, 555, 100, 400);

        tree2.addLeaf(node3, node5);

    }

}
