package datastructures;

import abstractAlgorithm.AbstractAlgorithm;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import supportClasses.types;

import visualization.TreeVisualization;

public class InfoTree extends AbstractDatastructure {

    private MyTree treeContent;
    private final types type;
    private TreeVisualization treeVisualization;
    private final AbstractAlgorithm algorithm;

    public InfoTree(AbstractAlgorithm abstractAlgorithm, types type, MyNode root) {
        this.treeContent = new MyTree(root);
        this.type = type;
        this.algorithm = abstractAlgorithm;
    }

    public void createTree(MyNode root) {
        // since MyNode is mutable, the created nodes has to be cleared (remove all set attributes)
        treeContent.clearTree();

        this.treeContent = new MyTree(root);
        if (this.treeVisualization != null) {
            this.treeVisualization.createTree(this, root);
        }
    }

    public void addLeaf(int parentIndex, Object leafValue) {
        MyNode parent = getNodeByIndex(parentIndex);

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
        MyNode node = getNodeByIndex(nodeIndex);

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
            this.treeVisualization.changeValue(this, node, oldValue, newValue);
        }
    }

    public void deleteLeaf(int leafIndex) {
        MyNode leaf = getNodeByIndex(leafIndex);

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
            this.treeVisualization.deleteLeaf(this, leaf);
        }
    }

    public void changeParent(int childIndex, int newParentIndex) {
        MyNode child = getNodeByIndex(childIndex);
        MyNode parent = getNodeByIndex(newParentIndex);

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
            this.treeVisualization.changeParent(this, child, parent);
        }
    }

    public int getIndexByValue(Object value) {
        // get index
        int index = this.treeContent.getIndexByValue(value);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.getIndexByValue(this, value, index);
        }

        return index;
    }

    public MyNode getNodeByIndex(int index) {
        // get node
        MyNode searchedNode = this.treeContent.getNodeByIndex(index);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.getNodeByIndex(this, index, searchedNode);
        }

        return searchedNode;
    }


    /**
     * {@link #deleteLeaf(int)} cannot be used, because the index count wouldn't go down.
     * @param leafIndex index of the previously added leaf
     */
    public void undoAddLeaf(int leafIndex) {
        MyNode leaf = getNodeByIndex(leafIndex);

        // delete the leaf
        this.treeContent.undoAddLeaf(leaf);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.deleteLeaf(this, leaf);
        }
    }


    /**
     * Tests if the given value is the same as the node type.
     * @param value given value to test
     * @return True if they are the same, false otherwise.
     */
    public boolean sameType(Object value){
        String simpleTypeName  = value.getClass().getSimpleName();
        if (type.equals(types.NUMBER)){
            return simpleTypeName.equals("Integer") || simpleTypeName.equals("Double") || simpleTypeName.equals("Float");
        }
        else if (type.equals(types.STRING)){
            return simpleTypeName.equals("String") || simpleTypeName.equals("Character");
        }
        else if (type.equals(types.BOOLEAN)){
            return simpleTypeName.equals("Boolean");
        }
        else {
            System.out.println("Warning! Unknown Type!");
            return false;
        }
    }

    /**
     * @return The number of levels of the tree.
     */
    public int getNumberOfLevels(){
        return this.treeContent.getNumberOfLevels();
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
