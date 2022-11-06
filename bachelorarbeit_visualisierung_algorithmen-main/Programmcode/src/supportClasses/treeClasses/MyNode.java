package supportClasses.treeClasses;

import java.util.ArrayList;

/**
 * Representation of a node in {@link MyTree}.
 */
public class MyNode {

    /**
     * Unique index for each node.
     */
    public int index;
    /**
     * Payload of the node.
     */
    public int value;
    /**
     * Parent of the node.
     */
    public MyNode parent;
    /**
     * The node, that has the same parent and is on the right of this node.
     */
    public MyNode rightBrother;
    /**
     * The child that is most left.
     */
    public MyNode leftChild;
    /**
     * The x-Coordinate for the visualization of the node.
     */
    public int xCoordinate;
    /**
     * The y-Coordinate for the visualization of the node.
     */
    public int yCoordinate;


    public MyNode(int index, int value, MyNode parent, MyNode rightBrother, MyNode leftChild, int xCoordinate,
                  int yCoordinate) {
        this.index = index;
        this.value = value;
        this.parent = parent;
        this.rightBrother = rightBrother;
        this.leftChild = leftChild;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public MyNode(int index, int value, int xCoordinate, int yCoordinate) {
        this.index = index;
        this.value = value;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * @param newChild node to be added
     */
    public void addChild(MyNode newChild) {
        MyNode consideredNode = this.leftChild;
        // if the node has no children, add the new node as the first one
        if (consideredNode == null) {
            this.leftChild = newChild;
        }
        else {
            // Go to the rightmost brother,...
            while (consideredNode.hasRightBrother()) {
                consideredNode = consideredNode.rightBrother;
            }
            // ... add to it the new node as a right brother...
            consideredNode.rightBrother = newChild;
            //... and add this node as the parent node.
            newChild.parent = this;
        }
    }

    /**
     * Using {@link #addChild(MyNode)} with varargs.
     * @param newChildren nodes to be added
     */
    public void addChildren(MyNode... newChildren) {
        for (MyNode newChild : newChildren) {
            this.addChild(newChild);
        }
    }

    /**
     * Changing the payload of the node.
     * @param newValue the new value for the node
     */
    public void changeValue(int newValue) {
        value = newValue;
    }

    /**
     * @return Whether the node has a right brother or not.
     */
    public Boolean hasRightBrother() {
        return rightBrother != null;
    }

    /**
     * @return Whether the node has a parent or not.
     */
    public Boolean hasParent() {
        return parent != null;
    }

    /**
     * @return Whether the node is a leaf or not.
     */
    public Boolean isLeaf() {
        return leftChild == null;
    }

    /**
     * @return The level on which this node is located in the tree.
     */
    public int getLevel() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

    /**
     * @param x new x-Coordinate
     */
    public void setX(int x) {
        xCoordinate = x;
    }

    /**
     * @param y new y-Coordinate
     */
    public void setY(int y) {
        yCoordinate = y;
    }

    /**
     * @param x new x-Coordinate
     * @param y new y-Coordinate
     */
    public void setXAndY(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Needed for {@link MyTree#positioning(int, int)}.
     * @return The sum of all x-Coordinates of the node's children.
     */
    public int getSumOfChildrenXCoordinates() {
        int result = 0;
        for (MyNode child : this.getAllChildren()) {
            result += child.xCoordinate;
        }
        return result;
    }

    /**
     * Needed for {@link MyTree#positioning(int, int)}.
     * @return The mean of the x-Coordinates of the node's middle children.
     */
    public int getMiddleChildrenXCoordinate() {
        int numberOfChildren = this.getNumberOfChildren();
        ArrayList<MyNode> children = this.getAllChildren();

        // if the number of children is odd, return the x-coordinate of the middle children
        if (numberOfChildren % 2 == 1) {
            return children.get(numberOfChildren / 2).xCoordinate;
        }
        else {
            // if the number is even, return the mean of the two middle children
            return (children.get(numberOfChildren / 2).xCoordinate
                    + children.get(numberOfChildren / 2 + 1).xCoordinate) / 2;
        }

    }

    /**
     * @return Number of children.
     */
    public int getNumberOfChildren() {
        return this.getAllChildren().size();
    }

    // TODO: 06.11.2022 write doc
    public String getValueAsString() {
        return String.valueOf(this.value);
    }

    /**
     * @return The depth of the node relative to the given root, -1 if the root is no ancestor.
     */
    public int getDepth(MyNode root) {
        MyNode consideredNode = this;
        int counter = 0;

        while (consideredNode != root) {
            if (consideredNode == null) {
                return -1;
            }
            consideredNode = consideredNode.parent;
            counter++;
        }

        return counter;
    }

    /**
     * @return All children of the node.
     */
    public ArrayList<MyNode> getAllChildren() {
        MyNode consideredNode = this;
        ArrayList<MyNode> children = new ArrayList<>();

        if (! consideredNode.isLeaf()) {
            consideredNode = consideredNode.leftChild;
            children.add(consideredNode);
            while (consideredNode.hasRightBrother()) {
                consideredNode = consideredNode.rightBrother;
                children.add(consideredNode);
            }
        }

        return children;
    }

}
