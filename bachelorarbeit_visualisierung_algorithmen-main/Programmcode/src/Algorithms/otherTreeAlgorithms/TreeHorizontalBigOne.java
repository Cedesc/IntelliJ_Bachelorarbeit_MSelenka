package Algorithms.otherTreeAlgorithms;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

import java.util.ArrayList;

public class TreeHorizontalBigOne extends AbstractAlgorithm {

    @Override
    public void executeAlgorithm() throws InterruptedException {

        int numberOfNodes = 30;

        ArrayList<MyNode> nodes = new ArrayList<>(numberOfNodes);

        // create nodes
        for (int i = 0 ; i <= numberOfNodes ; i++) {
            nodes.add(new MyNode(i));
        }

        // create tree
        Tree tree = create_Tree(types.NUMBER, nodes.get(0));

        // add nodes as children
        for (int i = 1 ; i <= numberOfNodes ; i++) {
            tree.addLeaf(i, nodes.get(i));
        }

    }
}
