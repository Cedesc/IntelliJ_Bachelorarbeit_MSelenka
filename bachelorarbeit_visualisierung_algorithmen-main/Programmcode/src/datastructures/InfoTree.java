package datastructures;

import abstractAlgorithmus.AbstractAlgorithm;
import supportClasses.treeClasses.MyTree;
import supportClasses.types;

import visualization.TreeVisualization;

public class InfoTree extends AbstractDatastructure {

    private MyTree content;
    private types type;
    private TreeVisualization treeVisualization;
    private AbstractAlgorithm algorithm;

    public InfoTree(AbstractAlgorithm abstractAlgorithm, types type) {
        this.content = new MyTree(null);
        this.type = type;
        this.algorithm = abstractAlgorithm;
    }

    // tests if the given value is the same as the array type
    // returns true if they are the same, if not it returns false
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

    // returns the number of levels of the tree
    public int getSize(){
        return this.content.getNumberOfLevels();
    }

    public TreeVisualization getTreeVisualization() {
        return this.treeVisualization;
    }

    public void setTreeVisualization(TreeVisualization treeVisualization) {
        this.treeVisualization = treeVisualization;
    }

    // returns the type of the nodes in the tree
    public types getType() {
        return this.type;
    }

}
