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
     * @param newLeaf new node, added as a leaf
     */
    public void addLeaf(MyNode newLeaf) {
        // TODO: 04.11.2022 Implementation
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
     * @return Array of all leafs of the tree.
     */
    public ArrayList<MyNode> getLeafs() {
        // For the method "positioning" is it mandatory to have the leafs in the right order from left to right.
        // This is possible with depth first search.
        // TODO: 04.11.2022 Implementation
        return null;
    }

    /**
     * @return Total number of levels of the tree.
     */
    public int getNumberOfLevels() {
        // TODO: 04.11.2022 Implementation
        return 0;
    }

    /**
     * @param level considered level
     * @return Array of all nodes of the considered level.
     */
    public ArrayList<MyNode> getNodesOfLevel(int level) {
        // TODO: 04.11.2022 Implementation
        return null;
    }

    /**
     * Changes the x- and y-coordinates of all nodes for a good layout of the tree.
     * @param xDistance standard x distance between nodes
     * @param yDistance standard y distance between nodes
     */
    public void positioning(int xDistance, int yDistance) {

        ArrayList<MyNode> leafs = getLeafs();
        int numberOfLevels = getNumberOfLevels();
        int y = numberOfLevels * yDistance;
        int x = 0;

        // Set the position of all leafs like there were all on the lowest level.
        // The leafs that aren't on the lowest level will be updated later, but the x coordinate should remain the same.
        for(MyNode leaf : leafs) {
            leaf.setXAndY(x, y);
            // to the next position
            x += xDistance;
        }

        for(int level = numberOfLevels ; level > 0 ; level--) {
            y -= yDistance;

            for(MyNode node : getNodesOfLevel(level)) {
                if (node.isLeaf()) {
                    node.setY(y);
                }
                else {
                    // position the node in the middle over the children (or over the child in the middle?)
                    x = node.getSumOfChildrenXPositions();
                    node.setXAndY(x, y);
                }
            }
        }

    }

}
