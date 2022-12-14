package supportClasses.treeClasses;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Representation of a node in {@link MyTree}.
 */
public class MyNode {

    /**
     * Unique index for each node.
     */
    private final int index;
    /**
     * Payload of the node.
     */
    private Object value;
    /**
     * Parent of the node.
     */
    private MyNode parent;
    /**
     * The node, that has the same parent and is on the right of this node.
     */
    private MyNode rightBrother;
    /**
     * The child that is most left.
     */
    private MyNode leftChild;
    /**
     * The x-Coordinate for the visualization of the node.
     */
    private int xCoordinate;
    /**
     * The y-Coordinate for the visualization of the node.
     */
    private int yCoordinate;



    // Constructors

    public MyNode(int index, Object value) {
        this.index = index;
        setValue(value);
        this.xCoordinate = 0;
        this.yCoordinate = 0;
    }

    public MyNode(int index) {
        this.index = index;
        setValue(null);
        this.xCoordinate = 0;
        this.yCoordinate = 0;
    }



    // Methods

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
            // ... add to it the new node as a right brother
            consideredNode.rightBrother = newChild;
        }
        // add this node as the parent node
        newChild.parent = this;
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
     * Cut the connection between this node and the given childToBeCut. If childToBeCut is no child of the node, the
     * method will be terminated and a warning will be printed.
     * @param childToBeCut child that has to be cut
     */
    public void cutChild(MyNode childToBeCut) {
        // if the leaf is the leftChild of the parent
        if (this.getLeftChild() == childToBeCut) {
            // cut the connection from the parent to the node
            this.setLeftChild(childToBeCut.getRightBrother());
        }
        // if the leaf isn't the left child, search after the immediate left sibling of the leaf
        else {
            // get left child of the parent (can't be null or the given leaf)
            MyNode firstNode = this.getLeftChild();
            // get second child, the right brother of the left child (can't be null)
            MyNode secondNode = firstNode.getRightBrother();

            // search the immediate left sibling of the leaf
            while (secondNode != childToBeCut) {
                firstNode = secondNode;
                secondNode = secondNode.getRightBrother();
                if (secondNode == null) {
                    // if childToBeCut is no child of the node, the method will be terminated
                    System.out.println("Warning! The given node isn't a child of the node.");
                    return;
                }
            }

            // cut the connection from the left sibling to the node
            firstNode.setRightBrother(childToBeCut.getRightBrother());
        }
        // cut the connection to the parent and the right brother
        childToBeCut.setParent(null);
        childToBeCut.setRightBrother(null);
    }

    /**
     * Cut the connection between this node and its parent.
     */
    public void cutFromParent() {
        this.getParent().cutChild(this);
    }

    /**
     * Changing the payload of the node.
     * @param newValue the new value for the node
     */
    public void changeValue(Object newValue) {
        setValue(newValue);
    }

    /**
     * @return The highest ancestor of the node.
     */
    public MyNode findHighestAncestor() {
        MyNode consideredNode = this;

        while(consideredNode.hasParent()) {
            consideredNode = consideredNode.getParent();
        }

        return consideredNode;
    }

    /**
     * Removes all attributes.
     */
    public void clear() {
        setValue(null);
        setParent(null);
        setRightBrother(null);
        setLeftChild(null);
        setXAndY(0, 0);
    }

    /**
     * @return The index of the node.
     */
    @Override
    public String toString() {
        return "Index: " + this.index;
    }



    // Setter

    public void setLeftChild(MyNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightBrother(MyNode rightBrother) {
        this.rightBrother = rightBrother;
    }

    public void setParent(MyNode parent) {
        this.parent = parent;
    }

    public void setValue(Object value) {
        this.value = Objects.requireNonNullElseGet(value, ValueNullObject::new);
    }

    public void setX(int x) {
        xCoordinate = x;
    }

    public void setY(int y) {
        yCoordinate = y;
    }

    public void setXAndY(int x, int y) {
        setX(x);
        setY(y);
    }



    // Getter

    /**
     * Needed for {@link MyTree#positioning(int, int)}.
     * @return The mean of the x-Coordinates of the node's middle children.
     */
    public int getMiddleChildrenXCoordinate() {
        int numberOfChildren = this.getNumberOfChildren();
        ArrayList<MyNode> children = this.getAllChildren();

        if (isLeaf()) {
            System.out.println("Warning! Node has no children!");
            return 0;
        }

        // if the number of children is odd, return the x-coordinate of the middle children
        if (numberOfChildren % 2 == 1) {
            return children.get(numberOfChildren / 2).xCoordinate;
        }
        else {
            // if the number is even, return the mean of the two middle children
            return (children.get(numberOfChildren / 2 - 1).xCoordinate
                    + children.get(numberOfChildren / 2).xCoordinate) / 2;
        }

    }

    public int getNumberOfChildren() {
        return this.getAllChildren().size();
    }

    public Object getValue() {
        return value;
    }

    public String getValueAsString() {
        return String.valueOf(this.value);
    }

    public int getIndex() {
        return index;
    }

    public String getIndexAsString() {
        return String.valueOf(this.index);
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

    public MyNode getLeftChild() {
        return this.leftChild;
    }

    public MyNode getRightBrother() {
        return this.rightBrother;
    }

    public MyNode getParent() {
        return this.parent;
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

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }



    // Has- and Is- Methods

    public Boolean hasRightBrother() {
        return rightBrother != null;
    }

    public Boolean hasParent() {
        return parent != null;
    }

    /**
     * @return Whether the node has a left child, so it's a leaf, or not.
     */
    public Boolean isLeaf() {
        return leftChild == null;
    }

}
