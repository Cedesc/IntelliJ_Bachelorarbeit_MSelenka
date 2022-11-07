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
        // TODO: 04.11.2022 Implementation
    }

    /**
     * @param leaf node to be deleted
     */
    public void deleteLeaf(MyNode leaf) {
        // TODO: 04.11.2022 Implementation
    }

    /**
     * @param node node whose value is changed
     * @param newValue new value of the node
     */
    public void changeValue(MyNode node, int newValue) {
        // TODO: 04.11.2022 Implementation
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
                    // position the node in the middle over the children (or over the child in the middle?)
                    x = node.getSumOfChildrenXCoordinates() / node.getNumberOfChildren();
                    node.setXAndY(x, y);
                }
            }
        }

    }

    public MyNode getRoot() {
        return root;
    }
}
