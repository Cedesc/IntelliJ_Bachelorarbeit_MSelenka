package Algorithms;

import abstractAlgorithmus.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

import java.util.ArrayList;

public class TreeCompleteK_nary extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        int k = 3;
        int numberOfNodes = 5000;

        ArrayList<MyNode> nodes = new ArrayList<>(numberOfNodes);

        // create nodes
        for (int i = 0 ; i <= numberOfNodes ; i++) {
            nodes.add(new MyNode(i));
        }

        // create tree
        Tree tree = create_Tree(types.NUMBER, nodes.get(0));

        // add nodes as children
        for (int i = 0 ; i < numberOfNodes ; i++) {
            tree.addLeaf(nodes.get(i / k), nodes.get(i + 1));
        }

    }
}
