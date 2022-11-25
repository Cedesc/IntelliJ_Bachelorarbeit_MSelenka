package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.types;

public class TreeReallySimple  extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree
        Tree tree = create_Tree(types.NUMBER, 10);

//        Tree tree2 = create_Tree(types.NUMBER, 20);

        // add leafs to tree on level 1
        tree.addLeaf(0, 1);
        tree.addLeaf(0);
        tree.addLeaf(0);

        // change values
        tree.changeValue(2, 222);

        // get nodes by index (for no reason)
        tree.getNodeByIndex(1);
        tree.getNodeByIndex(1337);

        // get indices by values
        int a = tree.getIndexByValue(1);

        int b = tree.getIndexByValue(222);

        // delete leafs
        tree.deleteLeaf(1);

        // change parent
        tree.changeParent(2, 3);

    }

}
