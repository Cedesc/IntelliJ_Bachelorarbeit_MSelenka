package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import supportClasses.types;

import visualization.TreeVisualization;

public class InfoTree extends AbstractDataStructure {

    private MyTree treeContent;
    private final types type;
    private TreeVisualization treeVisualization;
    private final AbstractAlgorithm algorithm;

    public InfoTree(AbstractAlgorithm abstractAlgorithm, types type, MyNode root) {
        this.treeContent = new MyTree(root);
        this.type = type;
        this.algorithm = abstractAlgorithm;
    }

    public void createTree(MyNode root, Object rootValue) {
        // since MyNode is mutable, the created nodes has to be cleared (remove all set attributes)
        treeContent.clearTree();

        // after clearing the value of the node has to be set again
        root.setValue(rootValue);

        this.treeContent = new MyTree(root);
        if (this.treeVisualization != null) {
            this.treeVisualization.createTree(this);
        }
    }

    public void deleteTree() {
        treeContent.clearTree();
        if (this.treeVisualization != null) {
            this.treeVisualization.deleteTree(this);
        }
    }

    public void addLeaf(int parentIndex, Object leafValue) {
        MyNode parent = this.treeContent.getNodeByIndex(parentIndex);

        // check if parent is null
        if (parent == null) {
            this.algorithm.setErrorString("Error function 'addLeaf' of a tree : \nParent is null.");
            return;
        }

        // add the leaf
        MyNode newLeaf = this.treeContent.addLeaf(parent, leafValue);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.addLeaf(this, parent, newLeaf);
        }
    }

    public void changeValue(int nodeIndex, Object newValue) {
        MyNode node = this.treeContent.getNodeByIndex(nodeIndex);

        // check if the node is null
        if (node == null) {
            this.algorithm.setErrorString("Error function 'changeValue' of a tree : \nNode is null.");
            return;
        }

        // save the old value
        Object oldValue = node.getValue();

        // change the value
        this.treeContent.changeValue(node, newValue);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.changeValue(this, node, oldValue);
        }
    }

    public void deleteLeaf(int leafIndex) {
        MyNode leaf = this.treeContent.getNodeByIndex(leafIndex);

        // check if the node is null
        if (leaf == null) {
            this.algorithm.setErrorString("Error function 'deleteLeaf' of a tree : \nLeaf is null.");
            return;
        }
        // check if the node is a leaf
        if (! leaf.isLeaf()) {
            this.algorithm.setErrorString("Error function 'deleteLeaf' of a tree : \nNode is not a leaf.");
            return;
        }

        // delete the leaf
        this.treeContent.deleteLeaf(leaf);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.deleteLeaf();
        }
    }

    public void changeParent(int childIndex, int newParentIndex) {
        MyNode child = this.treeContent.getNodeByIndex(childIndex);
        MyNode parent = this.treeContent.getNodeByIndex(newParentIndex);

        // check if the childNode or the new parent is null
        if (child == null) {
            this.algorithm.setErrorString("Error function 'changeParent' of a tree : \nChild is null.");
            return;
        }
        if (parent == null) {
            this.algorithm.setErrorString("Error function 'changeParent' of a tree : \nParent is null.");
            return;
        }

        // change the parent
        this.treeContent.changeParent(child, parent);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.changeParent();
        }
    }

    public int getIndexByValue(Object value) {
        // get index
        int index = this.treeContent.getIndexByValue(value);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.getIndexByValue(this, index);
        }

        return index;
    }

    public MyNode getNodeByIndex(int index) {
        // get node
        MyNode searchedNode = this.treeContent.getNodeByIndex(index);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.getNodeByIndex(this, searchedNode);
        }

        return searchedNode;
    }

    /**
     * {@link #deleteLeaf(int)} cannot be used, because the index count wouldn't go down.
     * @param leafIndex index of the previously added leaf
     */
    public void undoAddLeaf(int leafIndex) {
        MyNode leaf = this.treeContent.getNodeByIndex(leafIndex);

        // delete the leaf
        this.treeContent.undoAddLeaf(leaf);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.deleteLeaf();
        }
    }

    /**
     * {@link #addLeaf(int, Object)} cannot be used, because the index count would raise.
     * @param oldIndex index the deleted node had
     * @param oldParentIndex index of the parent of the deleted node
     * @param oldNodeValue value of the deleted node
     */
    public void undoDeleteLeaf(int oldIndex, int oldParentIndex, Object oldNodeValue) {
        MyNode parent = this.treeContent.getNodeByIndex(oldParentIndex);

        // add the leaf
        MyNode newLeaf = this.treeContent.undoDeleteLeaf(oldIndex, parent, oldNodeValue);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.addLeaf(this, parent, newLeaf);
        }
    }


    public TreeVisualization getTreeVisualization() {
        return this.treeVisualization;
    }

    public void setTreeVisualization(TreeVisualization treeVisualization) {
        this.treeVisualization = treeVisualization;
    }

    /**
     * @return The type of the nodes in the tree.
     */
    public types getType() {
        return this.type;
    }

    /**
     * @return The included tree.
     */
    public MyTree getTreeContent() {
        return this.treeContent;
    }

}
