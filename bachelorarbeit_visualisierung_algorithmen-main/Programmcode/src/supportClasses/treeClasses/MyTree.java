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
     * @param root root node of the tree
     */
    public MyTree(MyNode root) {
        this.root = root;
    }

    /**
     * @param parent the node to which the new leaf is attached
     * @param newLeaf new node, added as a leaf
     */
    public void addLeaf(MyNode parent, MyNode newLeaf) {
        parent.addChild(newLeaf);
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
     * @param leaf node to be deleted
     */
    public void deleteLeaf(MyNode leaf) {
        if (! leaf.isLeaf()) {
            System.out.println("Warning! Tried to delete an inner node!");
            return;
        }

        // cut the connection between the child and its parent
        leaf.cutFromParent();
    }

    /**
     * @param node node whose value is changed
     * @param newValue new value of the node
     */
    public void changeValue(MyNode node, int newValue) {
        node.changeValue(newValue);
    }

    /**
     * @param value searched value
     * @return Index with the given value. If there is no node with the given value, it returns -1.
     */
    public int getIndexByValue(int value) {
        return getIndexByValue(value, root);
    }

    /**
     * Recursive helper function for {@link #getIndexByValue(int)}
     * @param value searched value
     * @param consideredNode currently considered node
     * @return Index with the given value. If there is no node with the given value, it returns -1.
     */
    private int getIndexByValue(int value, MyNode consideredNode) {

        // if the considered node has the searched value, return its index
        if (consideredNode.getValue() == value) {
            return consideredNode.getIndex();
        }
        else {
            // call the method recursively on each child
            ArrayList<MyNode> children = consideredNode.getAllChildren();
            int returnedIndex;
            for (MyNode child : children) {
                returnedIndex = getIndexByValue(value, child);
                if (returnedIndex >= 0) {
                    return returnedIndex;
                }
            }
        }
        // if the considered node is a leaf and hasn't the searched value, return -1
        return -1;
    }

    /**
     * Using Depth-First-Search.
     * @return Array of all leafs of the tree.
     */
    public ArrayList<MyNode> getLeafsDFS() {
        return getLeafsDFS(root);
    }

    /**
     * Recursive helper function for {@link #getLeafsDFS()}.
     * @param consideredNode currently considered node
     */
    private ArrayList<MyNode> getLeafsDFS(MyNode consideredNode) {

        ArrayList<MyNode> foundNodes = new ArrayList<>();

        if (consideredNode.isLeaf()) {
            // if the node is a leaf add it to the result
            foundNodes.add(consideredNode);
        }
        // if the node isn't a leaf, call the function recursively for each of it's children
        for (MyNode child : consideredNode.getAllChildren()) {
            foundNodes.addAll(getLeafsDFS(child));
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
        return getNodesOfLevel(level, root);
    }

    /**
     * Recursive helper function for {@link #getNodesOfLevel(int)}.
     * @param level considered level, doesn't change in the recursive calls
     * @param consideredNode currently considered node
     */
    private ArrayList<MyNode> getNodesOfLevel(int level, MyNode consideredNode) {

        ArrayList<MyNode> foundNodes = new ArrayList<>();

        // the following two cases doesn't take effect if the searched depth is 0, so catch this special case first
        if (level == 0) {
            foundNodes.add(root);
            return foundNodes;
        }

        // if the node is higher than the searched depth plus one, the function will be called recursively on each child
        if (consideredNode.getDepth(root) < level - 1) {
            for (MyNode child : consideredNode.getAllChildren()) {
                foundNodes.addAll(getNodesOfLevel(level, child));
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

    public MyNode getRoot() {
        return root;
    }

    public void setRoot(MyNode newRoot) {
        root = newRoot;
    }

    /**
     * Removes all attributes from all nodes.
     */
    public void clearTree() {
        clearTree(this.root);
    }

    /**
     * Recursive helper function for {@link #clearTree()}.
     * @param currentNode currently considered node
     */
    private void clearTree(MyNode currentNode) {
        for (MyNode node : currentNode.getAllChildren()) {
            clearTree(node);
        }
        currentNode.clear();
    }

}
