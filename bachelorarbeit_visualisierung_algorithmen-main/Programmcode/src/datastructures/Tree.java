package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.treeCommands.AddLeaf;
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

    public void addLeaf(MyNode parent, MyNode newLeaf) {
        AddLeaf addLeaf = new AddLeaf(this.infoTree, parent, newLeaf);
//        this.infoTree.addLeaf(parent, newLeaf);
        this.algorithm.appendCommandOrder(addLeaf);
    }

}
