package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.treeCommands.AddLeaf;
import commands.treeCommands.ChangeValue;
import commands.treeCommands.CreateTree;
import supportClasses.treeClasses.MyNode;
import supportClasses.types;

public class Tree extends AbstractDatastructure {

    private final AbstractAlgorithm algorithm;
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
        // TODO: 21.11.2022 delete afterwards
//        this.infoTree.addLeaf(parent, newLeaf);
        this.algorithm.appendCommandOrder(addLeaf);
    }

    public void changeValue(MyNode node, int newValue) {
        ChangeValue changeValue = new ChangeValue(this.infoTree, node, newValue);
        this.algorithm.appendCommandOrder(changeValue);
    }

}
