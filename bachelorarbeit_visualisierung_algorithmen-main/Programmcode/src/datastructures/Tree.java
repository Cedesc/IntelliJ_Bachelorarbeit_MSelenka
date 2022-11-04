package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import commands.treeCommands.CreateTree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class Tree extends AbstractDatastructure {

    private AbstractAlgorithm algorithm;
    public InfoTree infoTree;
    private types type;
    private MyNode root;

    public Tree(AbstractAlgorithm abstractAlgorithm, InfoTree infoTree, types type, MyNode root){
        this.algorithm = abstractAlgorithm;
        this.infoTree = infoTree;
        this.type = type;
        this.root = root;
        CreateTree createTree = new CreateTree(this.infoTree, type, root);
        this.algorithm.appendCommandOrder(createTree);
    }

}
