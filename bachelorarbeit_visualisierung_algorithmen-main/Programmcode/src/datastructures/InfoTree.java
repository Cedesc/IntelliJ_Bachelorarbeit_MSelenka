package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import supportClasses.treeClasses.MyNode;
import supportClasses.treeClasses.MyTree;
import supportClasses.types;

import visualization.TreeVisualization;

public class InfoTree extends AbstractDatastructure {

    private MyTree treeContent;
    private types type;
    private TreeVisualization treeVisualization;
    private AbstractAlgorithm algorithm;

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

}
