package supportClasses.treeClasses;

import java.util.ArrayList;

/**
 * Representation of a tree using {@link MyNode}.
 */
public class MyTree {

    /**
     * Root node of the tree.
     */
    private MyNode root;

    /**
     * Integer that count with the number of nodes, for giving each node a unique index.
     */
    private int nextIndex;



    // Constructors
    public MyTree(MyNode root) {
        this.root = root;
        this.nextIndex = 1;
    }

    public MyTree() {
        this.nextIndex = 0;
        this.root = new MyNode(getNextIndex());
    }

    public MyTree(Object rootValue) {
        this.nextIndex = 0;
        this.root = new MyNode(getNextIndex(), rootValue);
    }



    // Methods

    /**
     * @param parent the node to which the new leaf is attached
     * @param leafValue value of the new leaf
     */
    public MyNode addLeaf(MyNode parent, Object leafValue) {
        MyNode leafToBeAdded = new MyNode(getNextIndex(), leafValue);
        parent.addChild(leafToBeAdded);
        return leafToBeAdded;
    }

    /**
     * {@link #deleteLeaf(MyNode)} isn't enough for undoing {@link #addLeaf(MyNode, Object)}, because the index count
     * doesn't go down.
     * @param leaf previously added leaf
     */
    public void undoAddLeaf(MyNode leaf) {
        // delete the leaf
        deleteLeaf(leaf);

        // decrement the index counter
        nextIndex--;
    }

    /**
     * @param leaf node to be deleted
     */
    public void deleteLeaf(MyNode leaf) {
        // check if the given node is a true leaf
        if (! leaf.isLeaf()) {
            System.out.println("Warning! Tried to delete an inner node!");
            return;
        }

        // cut the connection between the child and its parent
        leaf.cutFromParent();
    }

    /**
     * {@link #addLeaf(MyNode, Object)} isn't enough for undoing {@link #deleteLeaf(MyNode)}, because the index count
     * would raise.
     * @param oldIndex index the deleted node had
     * @param oldParent parent the deleted node had
     * @param oldNodeValue value of the deleted node
     */
    public MyNode undoDeleteLeaf(int oldIndex, MyNode oldParent, Object oldNodeValue) {
        MyNode leafToBeAdded = new MyNode(oldIndex, oldNodeValue);
        oldParent.addChild(leafToBeAdded);
        return leafToBeAdded;
    }

    /**
     * @param childNode node whose parent is changed
     * @param newParent the new parent of the childNode
     */
    public void changeParent(MyNode childNode, MyNode newParent) {

        // if the given childNode is the root, it has no parent
        if (childNode.getParent() == null) {
            // change the root
            this.setRoot(childNode.findHighestAncestor());
        }
        else {
            // cut the connection between the child node and its old parent node
            childNode.cutFromParent();
        }

        // add the child node to the new parent
        newParent.addChild(childNode);
    }

    /**
     * @param node node whose value is changed
     * @param newValue new value of the node
     */
    public void changeValue(MyNode node, Object newValue) {
        node.changeValue(newValue);
    }

    /**
     * @param value searched value
     * @return Index with the given value. If there is no node with the given value, it returns -1.
     */
    public int getIndexByValue(Object value) {
        return getIndexByValue_R(value, root);
    }

    /**
     * Recursive helper function for {@link #getIndexByValue(Object)}
     * @param value searched value
     * @param consideredNode currently considered node
     * @return Index with the given value. If there is no node with the given value, it returns -1.
     */
    private int getIndexByValue_R(Object value, MyNode consideredNode) {

        // if the considered node has the searched value, return its index
        if (consideredNode.getValue() == value) {
            return consideredNode.getIndex();
        }
        else {
            // call the method recursively on each child
            ArrayList<MyNode> children = consideredNode.getAllChildren();
            int returnedIndex;
            for (MyNode child : children) {
                returnedIndex = getIndexByValue_R(value, child);
                if (returnedIndex >= 0) {
                    return returnedIndex;
                }
            }
        }
        // if the considered node is a leaf and hasn't the searched value, return -1
        return -1;
    }

    /**
     * @param index searched index
     * @return Node with the given index. If no node has the index, the method returns null.
     */
    public MyNode getNodeByIndex(int index) {
        return getNodeByIndex_R(root, index);
    }

    /**
     * Recursive helper function for {@link #getNodeByIndex(int)}
     * @param consideredNode current node
     * @param index searched index
     * @return Node with the given index. If no node has the index, the method returns null.
     */
    private MyNode getNodeByIndex_R(MyNode consideredNode, int index) {

        // if the considered node has the searched value, return its index
        if (consideredNode.getIndex() == index) {
            return consideredNode;
        }
        else {
            // call the method recursively on each child
            ArrayList<MyNode> children = consideredNode.getAllChildren();
            MyNode returnedNode;
            for (MyNode child : children) {
                returnedNode = getNodeByIndex_R(child, index);
                if (returnedNode != null) {
                    return returnedNode;
                }
            }
        }
        // if no node with the given index exists, return null
        return null;
    }

    /**
     * Using Depth-First-Search.
     * @return Array of all leafs of the tree.
     */
    public ArrayList<MyNode> getLeafsDFS() {
        return getLeafsDFS_R(root);
    }

    /**
     * Recursive helper function for {@link #getLeafsDFS()}.
     * @param consideredNode currently considered node
     */
    private ArrayList<MyNode> getLeafsDFS_R(MyNode consideredNode) {

        ArrayList<MyNode> foundNodes = new ArrayList<>();

        if (consideredNode.isLeaf()) {
            // if the node is a leaf add it to the result
            foundNodes.add(consideredNode);
        }
        // if the node isn't a leaf, call the function recursively for each of it's children
        for (MyNode child : consideredNode.getAllChildren()) {
            foundNodes.addAll(getLeafsDFS_R(child));
        }

        return foundNodes;
    }

    /**
     * Calculates the maximal depth of the tree, since the maximal depth + 1 is the number of tree levels.
     * For example, a tree consisting of only the root, has one level.
     * @return Total number of levels of the tree.
     */
    public int getNumberOfLevels() {
        int maxDepth = -1;
        for (MyNode leaf : getLeafsDFS()) {
            int depth = leaf.getDepth(root);
            if (depth > maxDepth)
                maxDepth = depth;
        }

        // if the maximal depth is lower than 0, it's an error, so the depth (-1) is returned here too
        if (maxDepth < 0)
            return maxDepth;
        // if the maximal depth is >= 0, the result is maxDepth + 1, since the number of levels is required
        else
            return maxDepth + 1;
    }

    /**
     * The level enumeration starts with 0.
     * @param level considered level
     * @return Array of all nodes of the considered level.
     */
    public ArrayList<MyNode> getNodesOfLevel(int level) {
        return getNodesOfLevel_R(level, root);
    }

    /**
     * Recursive helper function for {@link #getNodesOfLevel(int)}.
     * @param level considered level, doesn't change in the recursive calls
     * @param consideredNode currently considered node
     */
    private ArrayList<MyNode> getNodesOfLevel_R(int level, MyNode consideredNode) {

        ArrayList<MyNode> foundNodes = new ArrayList<>();

        // the following two cases doesn't take effect if the searched depth is 0, so catch this special case first
        if (level == 0) {
            foundNodes.add(root);
            return foundNodes;
        }

        // if the node is higher than the searched depth plus one, the function will be called recursively on each child
        if (consideredNode.getDepth(root) < level - 1) {
            for (MyNode child : consideredNode.getAllChildren()) {
                foundNodes.addAll(getNodesOfLevel_R(level, child));
            }
        }
        // if the considered node is exactly one level above the given, all of its children are on the correct level,
        // so they are added to the foundNodes
        if (consideredNode.getDepth(root) == level - 1) {
            foundNodes.addAll(consideredNode.getAllChildren());
        }

        return foundNodes;
    }

    /**
     * Changes the x- and y-coordinates of all nodes for a good layout of the tree.
     * @param xDistance standard x distance between nodes
     * @param yDistance standard y distance between nodes
     */
    public void positioning(int xDistance, int yDistance) {

        // It's mandatory to have the leafs in the right order from left to right.
        ArrayList<MyNode> leafs = getLeafsDFS();
        int numberOfLevels = getNumberOfLevels();
        if (numberOfLevels < 1)
            System.out.println("Warning! Invalid tree, it has no nodes.");
        int y = numberOfLevels * yDistance;
        int x = 0;

        // Set the position of all leafs like there were all on the lowest level.
        // The leafs that aren't on the lowest level will be updated later, but the x coordinate should remain the same.
        for(MyNode leaf : leafs) {
            leaf.setXAndY(x, y);
            // to the next position
            x += xDistance;
        }

        for(int level = numberOfLevels - 1 ; level >= 0 ; level--) {
            y -= yDistance;

            for(MyNode node : getNodesOfLevel(level)) {
                if (node.isLeaf()) {
                    node.setY(y);
                }
                else {
                    // position the node over the middle child or children
                    x = node.getMiddleChildrenXCoordinate();
                    node.setXAndY(x, y);
                }
            }
        }

    }

    /**
     * Removes all attributes from all nodes.
     */
    public void clearTree() {
        clearTree_R(this.root);
    }

    /**
     * Recursive helper function for {@link #clearTree()}.
     * @param currentNode currently considered node
     */
    private void clearTree_R(MyNode currentNode) {
        for (MyNode node : currentNode.getAllChildren()) {
            clearTree_R(node);
        }
        currentNode.clear();
    }

    public MyNode createNewNode(Object nodeValue) {
        return new MyNode(getNextIndex(), nodeValue);
    }



    // Setter

    public void setRoot(MyNode newRoot) {
        root = newRoot;
    }



    // Getter

    public MyNode getRoot() {
        return root;
    }

    /**
     * @return Current value of {@link #nextIndex} and increment it afterwards.
     */
    private int getNextIndex() {
        return nextIndex++;
    }

}
