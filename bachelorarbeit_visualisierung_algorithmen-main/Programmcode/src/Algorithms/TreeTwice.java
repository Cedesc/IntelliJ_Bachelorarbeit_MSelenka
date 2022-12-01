package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeTwice extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree1
        Tree tree1 = create_Tree(types.NUMBER, "1_0");

        // create tree2
        Tree tree2 = create_Tree(types.NUMBER, "2_0");

        // add leafs to tree2
        tree2.addLeaf(0, "2_1");
        tree2.addLeaf(0, "2_2");
        tree2.addLeaf(0, "2_4");
        tree2.addLeaf(2, "2_3");

        // add leaf to tree1
        tree1.addLeaf(0, "1_1");

        // add leaf to tree2
        tree2.addLeaf(3, "2_5");

    }

}
