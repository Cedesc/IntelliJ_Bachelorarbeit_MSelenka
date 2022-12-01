package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

import java.util.ArrayList;
import java.util.Random;

public class TreeRandom extends AbstractAlgorithm {

    /**
     * Number of nodes, the final tree should have.
     */
    final int numberOfNodes = 50;

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree
        Tree tree = create_Tree(types.NUMBER, 0);

        // fill tree with nodes
        for (int i = 1 ; i < numberOfNodes ; i++) {
            int randomInt = new Random().nextInt(i);
            tree.addLeaf(randomInt, i);
        }


    }
}
