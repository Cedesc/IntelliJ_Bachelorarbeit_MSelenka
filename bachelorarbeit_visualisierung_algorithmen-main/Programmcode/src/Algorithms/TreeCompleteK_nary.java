package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

import java.util.ArrayList;

public class TreeCompleteK_nary extends AbstractAlgorithm {

    /**
     * Number of children, each node should have.
     */
    final int k = 5;

    /**
     * Number of nodes, the final tree should have.
     */
    final int numberOfNodes = 500;

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree
        Tree tree = create_Tree(types.NUMBER, 0);

        // add nodes as children
        for (int i = 0 ; i < numberOfNodes ; i++) {
            tree.addLeaf(i / k, i + 1);
        }

    }
}
