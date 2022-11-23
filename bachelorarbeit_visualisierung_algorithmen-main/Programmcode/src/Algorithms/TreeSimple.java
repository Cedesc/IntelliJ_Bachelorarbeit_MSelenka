package Algorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeSimple extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        // nodes for the tree
        MyNode node00 = new MyNode(0);

        // create tree
        Tree tree = create_Tree(types.NUMBER, node00);

        MyNode rootNode = tree.getNodeByIndex(0);

        // add leafs to tree on level 1
        tree.addLeaf(rootNode, 1);
        tree.addLeaf(rootNode, 2);
        tree.addLeaf(rootNode);
        tree.addLeaf(rootNode);

        // change value
        tree.changeValue(rootNode, 100);


        // add leaf to tree on level 2
        tree.addLeaf(tree.getNodeByIndex(1), 5);
        tree.addLeaf(tree.getNodeByIndex(1), 6);
        tree.addLeaf(tree.getNodeByIndex(1), 7);
        tree.addLeaf(tree.getNodeByIndex(2), 8);
        tree.addLeaf(tree.getNodeByIndex(2), 9);
        tree.addLeaf(tree.getNodeByIndex(3), 10);

        // change parent
//        tree.changeParent(tree.getNodeByIndex(7), tree.getNodeByIndex(0));
//        tree.changeParent(tree.getNodeByIndex(5), tree.getNodeByIndex(7));

        // change values
//        tree.changeValue(tree.getNodeByIndex(7), 107);
//        tree.changeValue(tree.getNodeByIndex(5), 105);

        // delete leafs
//        tree.deleteLeaf(tree.getNodeByIndex(10));

        // add leaf to tree on level 3
        tree.addLeaf(tree.getNodeByIndex(6), 11);
        tree.addLeaf(tree.getNodeByIndex(8), 12);
        tree.addLeaf(tree.getNodeByIndex(9), 13);

        // delete leafs
//        tree.deleteLeaf(tree.getNodeByIndex(11));
//        tree.deleteLeaf(tree.getNodeByIndex(6));
//        tree.deleteLeaf(tree.getNodeByIndex(5));
//        tree.deleteLeaf(tree.getNodeByIndex(7));
//        tree.deleteLeaf(tree.getNodeByIndex(1));

        // get indices by values
        int a = tree.getIndexByValue(100);
        int b = tree.getIndexByValue(0);
        int c = tree.getIndexByValue(3);
        int d = tree.getIndexByValue(5);
        int e = tree.getIndexByValue(1);
        if (a != 0 || b != -1 || c != -1 || d != 5 || e != 1) {
            System.out.println(
                    "a = 0 = " + a + "\n" +
                    "b = -1 = " + b + "\n" +
                    "c = -1 = " + c + "\n" +
                    "d = 5 = " + d + "\n" +
                    "e = 1 = " + e + "\n");
        }

    }

}
