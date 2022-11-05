package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        Tree tree1 = create_Tree(types.NUMBER,
                new MyNode(0, 33, null, null, null, 15, 15));

    }

}
