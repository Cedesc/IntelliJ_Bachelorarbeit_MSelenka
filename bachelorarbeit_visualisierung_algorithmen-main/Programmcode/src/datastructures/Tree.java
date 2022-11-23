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

    public void addLeaf(int parentIndex, Object leafValue) {
        MyNode leafToBeAdded = tree.createNewNode(leafValue);
        AddLeaf addLeaf = new AddLeaf(this.infoTree, parentIndex, leafToBeAdded);
        this.infoTree.addLeaf(parentIndex, leafValue);
        this.algorithm.appendCommandOrder(addLeaf);
    }

    public void addLeaf(int parentIndex) {
        addLeaf(parentIndex, null);
    }

    public void changeValue(int nodeIndex, Object newValue) {
        ChangeValue changeValue = new ChangeValue(this.infoTree, nodeIndex, newValue);
        this.infoTree.changeValue(nodeIndex, newValue);
        this.algorithm.appendCommandOrder(changeValue);
    }

    public void deleteLeaf(int leafIndex) {
        DeleteLeaf deleteLeaf = new DeleteLeaf(this.infoTree, leafIndex);
        this.infoTree.deleteLeaf(leafIndex);
        this.algorithm.appendCommandOrder(deleteLeaf);
    }

    public void changeParent(int childIndex, int newParentIndex) {
        ChangeParent changeParent = new ChangeParent(this.infoTree, childIndex, newParentIndex);
        this.infoTree.changeParent(childIndex, newParentIndex);
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
