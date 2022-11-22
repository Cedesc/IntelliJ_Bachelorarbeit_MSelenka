package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.treeCommands.*;
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

    public void deleteLeaf(MyNode leaf) {
        DeleteLeaf deleteLeaf = new DeleteLeaf(this.infoTree, leaf);
        this.algorithm.appendCommandOrder(deleteLeaf);
    }

    public void changeParent(MyNode childNode, MyNode newParent) {
        ChangeParent changeParent = new ChangeParent(this.infoTree, childNode, newParent);
        this.algorithm.appendCommandOrder(changeParent);
    }

    public int getIndexByValue(int value) {
        int index = infoTree.getIndexByValue(value);
        GetIndexByValueTree getIndexByValueTree = new GetIndexByValueTree(this.infoTree, index, value);
        this.algorithm.appendCommandOrder(getIndexByValueTree);
        return index;
    }

    public MyNode getNodeByIndex() {
        // TODO: 22.11.2022 Implementation
        return null;
    }

}
