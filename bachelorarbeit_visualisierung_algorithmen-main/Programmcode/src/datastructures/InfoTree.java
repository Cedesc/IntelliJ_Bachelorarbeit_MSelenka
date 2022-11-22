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
        this.treeContent = new MyTree(root);
        if (this.treeVisualization != null) {
            this.treeVisualization.createTree(this, root);
        }
    }

    public void addLeaf(MyNode parent, MyNode newLeaf) {
        // check if parent or newLeaf is null
        if (parent == null) {
            this.algorithm.setErrorString("Error function 'addLeaf' of a tree : \nParent is null.");
            return;
        }
        if (newLeaf == null) {
            this.algorithm.setErrorString("Error function 'addLeaf' of a tree : \nLeaf is null.");
            return;
        }

        // add the leaf
        this.treeContent.addLeaf(parent, newLeaf);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.addLeaf(this, parent, newLeaf);
        }
    }

    public void changeValue(MyNode node, int newValue) {
        // check if the node is null
        if (node == null) {
            this.algorithm.setErrorString("Error function 'changeValue' of a tree : \nNode is null.");
            return;
        }

        // save the old value
        int oldValue = node.getValue();

        // change the value
        this.treeContent.changeValue(node, newValue);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.changeValue(this, node, oldValue, newValue);
        }
    }

    public void deleteLeaf(MyNode leaf) {
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

    public void changeParent(MyNode childNode, MyNode newParent) {
        // check if the childNode or the new parent is null
        if (childNode == null) {
            this.algorithm.setErrorString("Error function 'changeParent' of a tree : \nChild is null.");
            return;
        }
        if (newParent == null) {
            this.algorithm.setErrorString("Error function 'changeParent' of a tree : \nParent is null.");
            return;
        }

        // change the parent
        this.treeContent.changeParent(childNode, newParent);

        // visualize the command
        if (this.treeVisualization != null) {
            this.treeVisualization.changeParent(this, childNode, newParent);
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
