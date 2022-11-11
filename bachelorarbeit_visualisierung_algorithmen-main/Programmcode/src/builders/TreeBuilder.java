package builders;

import abstractAlgorithm.AbstractAlgorithm;
import datastructures.InfoTree;
import datastructures.Tree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class TreeBuilder {

    public Tree createTree(AbstractAlgorithm abstractAlgorithm, InfoTree infoTree, types type, MyNode root){
        return new Tree(abstractAlgorithm, infoTree, type, root);
    }

    public InfoTree createInfoTree(AbstractAlgorithm abstractAlgorithm, types type, MyNode root){
        return new InfoTree(abstractAlgorithm, type, root);
    }

}
