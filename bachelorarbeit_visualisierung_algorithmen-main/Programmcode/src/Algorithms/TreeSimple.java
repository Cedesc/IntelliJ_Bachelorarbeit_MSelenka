package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.types;

public class TreeSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // create tree
        Tree tree = create_Tree(types.NUMBER, 0);

        // add leafs to tree on level 1
        tree.addLeaf(0, 1);
        tree.addLeaf(0, 2);
        tree.addLeaf(0);
        tree.addLeaf(0);

        // get nodes by index (for no reason)
        tree.getNodeByIndex(3);
        tree.getNodeByIndex(0);

        // change value
        tree.changeValue(0, 100);


        // add leaf to tree on level 2
        tree.addLeaf(1, 5);
        tree.addLeaf(1, 6);
        tree.addLeaf(1, 7);
        tree.addLeaf(2, 8);
        tree.addLeaf(2, 9);
        tree.addLeaf(3, 10);

        // change parent
        tree.changeParent(7, 0);
        tree.changeParent(5, 7);

        // change values
        tree.changeValue(7, 107);
        tree.changeValue(5, 105);

        // delete leafs
        tree.deleteLeaf(10);

        // add leaf to tree on level 3
        tree.addLeaf(6, 11);
        tree.addLeaf(8, 12);
        tree.addLeaf(9, 13);

        // delete leafs
        tree.deleteLeaf(11);
        tree.deleteLeaf(6);
        tree.deleteLeaf(5);
        tree.deleteLeaf(7);
        tree.deleteLeaf(1);

        // get indices by values
        int a = tree.getIndexByValue(100);
        int b = tree.getIndexByValue(0);
        int c = tree.getIndexByValue(3);
        int d = tree.getIndexByValue(5);
        int e = tree.getIndexByValue(1);
        if (a != 0 || b != -1 || c != -1 || d != -1 || e != -1) {
            System.out.println(
                    "a = 0 = " + a + "\n" +
                    "b = -1 = " + b + "\n" +
                    "c = -1 = " + c + "\n" +
                    "d = -1 = " + d + "\n" +
                    "e = -1 = " + e + "\n");
        }

    }

}
