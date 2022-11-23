package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import commands.treeCommands.*;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import supportClasses.types;

public class Tree extends AbstractDatastructure {

    private final AbstractAlgorithm algorithm;
    public InfoTree infoTree;
    private types type;
    private MyTree tree;

    public Tree(AbstractAlgorithm abstractAlgorithm, InfoTree infoTree, types type, MyNode root){
        this.algorithm = abstractAlgorithm;
        this.infoTree = infoTree;
        this.type = type;
        this.tree = new MyTree(root);
        CreateTree createTree = new CreateTree(this.infoTree, type, root);
        this.algorithm.appendCommandOrder(createTree);
    }

    public void addLeaf(MyNode parent, Object leafValue) {
        MyNode leafToBeAdded = tree.createNewNode(leafValue);
        AddLeaf addLeaf = new AddLeaf(this.infoTree, parent, leafToBeAdded);
        this.infoTree.addLeaf(parent, leafValue);
        this.algorithm.appendCommandOrder(addLeaf);
    }

    public void addLeaf(MyNode parent) {
        addLeaf(parent, null);
    }

    public void changeValue(MyNode node, int newValue) {
        ChangeValue changeValue = new ChangeValue(this.infoTree, node, newValue);
        this.infoTree.changeValue(node, newValue);
        this.algorithm.appendCommandOrder(changeValue);
    }

    public void deleteLeaf(MyNode leaf) {
        DeleteLeaf deleteLeaf = new DeleteLeaf(this.infoTree, leaf);
        this.infoTree.deleteLeaf(leaf);
        this.algorithm.appendCommandOrder(deleteLeaf);
    }

    public void changeParent(MyNode childNode, MyNode newParent) {
        ChangeParent changeParent = new ChangeParent(this.infoTree, childNode, newParent);
        this.infoTree.changeParent(childNode, newParent);
        this.algorithm.appendCommandOrder(changeParent);
    }

    public int getIndexByValue(Object value) {
        int index = infoTree.getIndexByValue(value);
        GetIndexByValueTree getIndexByValueTree = new GetIndexByValueTree(this.infoTree, index, value);
        this.algorithm.appendCommandOrder(getIndexByValueTree);
        return index;
    }

    public MyNode getNodeByIndex(int index) {
        MyNode searchedNode = infoTree.getNodeByIndex(index);
        GetNodeByIndex getNodeByIndex = new GetNodeByIndex(this.infoTree, searchedNode, index);
        this.algorithm.appendCommandOrder(getNodeByIndex);
        return searchedNode;
    }

}
