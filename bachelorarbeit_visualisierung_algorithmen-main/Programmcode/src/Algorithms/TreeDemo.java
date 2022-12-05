package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeDemo extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree
        Tree tree = create_Tree(types.NUMBER, 0);

        // add leafs to tree on level 1
        tree.addLeaf(0, 1);
        tree.addLeaf(0, 2);
        tree.addLeaf(0, 3);
        tree.addLeaf(0, 4);

        // add leaf to tree on level 2
        tree.addLeaf(1, 5);
        tree.addLeaf(1, 6);
        tree.addLeaf(1, 7);
        tree.addLeaf(2, 8);
        tree.addLeaf(2, 9);
        tree.addLeaf(3, 10);

        // add leaf to tree on level 3
        tree.addLeaf(6, 11);
        tree.addLeaf(8, 12);
        tree.addLeaf(9, 13);

    }

}
